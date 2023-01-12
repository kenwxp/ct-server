package com.cloudtimes.app.controller;

import com.cloudtimes.app.security.context.AuthenticationContextHolder;
import com.cloudtimes.app.security.service.AppTokenService;
import com.cloudtimes.common.annotation.RateLimiter;
import com.cloudtimes.common.constant.CacheConstants;
import com.cloudtimes.common.constant.Constants;
import com.cloudtimes.common.constant.UserConstants;
import com.cloudtimes.common.core.controller.BaseController;
import com.cloudtimes.common.core.domain.AjaxResult;
import com.cloudtimes.common.core.domain.entity.YbfMember;
import com.cloudtimes.common.core.domain.model.AppLoginUser;
import com.cloudtimes.common.core.domain.model.LoginBody;
import com.cloudtimes.common.core.domain.model.RegisterBody;
import com.cloudtimes.common.core.redis.RedisCache;
import com.cloudtimes.common.enums.LimitType;
import com.cloudtimes.common.exception.ServiceException;
import com.cloudtimes.common.exception.user.CaptchaException;
import com.cloudtimes.common.exception.user.CaptchaExpireException;
import com.cloudtimes.common.exception.user.UserPasswordNotMatchException;
import com.cloudtimes.common.payment.adapay.AdapayPaymentManager;
import com.cloudtimes.common.utils.*;
import com.cloudtimes.common.utils.ip.IpUtils;
import com.cloudtimes.common.utils.uuid.IdUtils;
import com.cloudtimes.common.utils.uuid.Seq;
import com.cloudtimes.system.service.ISysConfigService;
import com.cloudtimes.ybf.service.IYbfGoodsOrderRewardService;
import com.cloudtimes.ybf.service.IYbfMemberFriendshipService;
import com.cloudtimes.ybf.service.IYbfMemberGameService;
import com.cloudtimes.ybf.service.IYbfMemberService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * 会员Controller
 *
 * @author polo
 * @date 2022-09-01
 */
@Api("会员登录和注册接口")
@RestController
public class AppLoginAndRegisterController extends BaseController {
    @Autowired
    private IYbfMemberService ybfMemberService;

    @Autowired
    private AppTokenService tokenService;

    @Autowired
    private RedisCache redisCache;

    @Resource
    private AuthenticationManager authenticationManager;

    @Autowired
    private IYbfMemberFriendshipService memberFriendshipService;

    @Autowired
    private IYbfGoodsOrderRewardService goodsOrderRewardService;

    @Autowired
    private IYbfMemberGameService memberGameService;

    @Autowired
    private ISysConfigService configService;

    @Autowired
    private AdapayPaymentManager adapayPaymentManager;

    private static Logger log = LoggerFactory.getLogger(AppLoginAndRegisterController.class);

    /**
     * 登录方法
     *
     * @param loginBody 登录信息
     * @return 结果
     */
    @ApiOperation("会员登录")
    @PostMapping("/login")
    public AjaxResult login(@RequestBody LoginBody loginBody, HttpServletRequest request) {
        AjaxResult ajax = AjaxResult.success();
        // 生成令牌
        String token = login(loginBody.getUsername(), loginBody.getPassword(), loginBody.getCode(), loginBody.getUuid());
        ajax.put(Constants.TOKEN, token);
        return ajax;
    }

    @RateLimiter(limitType = LimitType.IP, count = 10, time = 60)
    @ApiOperation("会员注册")
    @PostMapping("/register")
    public AjaxResult register(@RequestBody RegisterBody user) {
        String msg = runRegister(user);
        return AjaxResult.success(msg);
    }


    @RateLimiter(limitType = LimitType.IP, count = 10, time = 60)
    @ApiOperation("会员找回密码手机身份认证")
    @PostMapping("/findPwdCheckMobile")
    public AjaxResult findPwdCheckMobile(@RequestBody RegisterBody user) {

        validateSMS(user.getUsername(), user.getCode(), user.getUuid());

        String uuid = IdUtils.simpleUUID();
        String verifyKey = CacheConstants.UPDATE_PWD_KEY + "_" + uuid;

        redisCache.setCacheObject(verifyKey, user.getUsername(), Constants.CAPTCHA_EXPIRATION, TimeUnit.MINUTES);
        AjaxResult ajax = AjaxResult.success("认证成功！");
        ajax.put("uuid", uuid);
        return ajax;
    }

    @RateLimiter(limitType = LimitType.IP, count = 10, time = 60)
    @ApiOperation("会员找回密码-重置密码")
    @PostMapping("/findUpdatePwd")
    public AjaxResult findUpdatePwd(String uuid, String password) {

        String verifyKey = CacheConstants.UPDATE_PWD_KEY + "_" + uuid;

        String username = redisCache.getCacheObject(verifyKey);
        if (StringUtils.isEmpty(username)) {
            throw new ServiceException("重置密码失效！");
        }

        redisCache.deleteObject(verifyKey);

        YbfMember member = ybfMemberService.selectYbfMemberByUsername(username);
        member.setPassword(SecurityUtils.encryptPassword(password));
        ybfMemberService.updateYbfMember(member);

        AjaxResult ajax = AjaxResult.success("重置密码成功！");
        return ajax;
    }

    /**
     * 注册
     */
    private String runRegister(RegisterBody registerBody) {
        String msg = "", username = registerBody.getUsername(), password = registerBody.getPassword();

        validateSMS(username, registerBody.getCode(), registerBody.getUuid());

        if (StringUtils.isEmpty(username)) {
            msg = "用户名不能为空";
            throw new ServiceException(msg);
        } else if (StringUtils.isEmpty(password)) {
            msg = "用户密码不能为空";
            throw new ServiceException(msg);
        } else if (username.length() < UserConstants.USERNAME_MIN_LENGTH
                || username.length() > UserConstants.USERNAME_MAX_LENGTH) {
            msg = "账户长度必须11字符";
            throw new ServiceException(msg);
        } else if (password.length() < UserConstants.PASSWORD_MIN_LENGTH
                || password.length() > UserConstants.PASSWORD_MAX_LENGTH) {
            msg = "密码长度必须在6到16个字符之间";
            throw new ServiceException(msg);
        } else if (ybfMemberService.checkUserNameUnique(username) > 0) {
            msg = "保存用户'" + username + "'失败，注册账号已存在";
            throw new ServiceException(msg);
        } else {
            YbfMember friend = null;
            if (registerBody.getInviteCode() != null) {
                friend = ybfMemberService.selectYbfMemberByInviteCode(registerBody.getInviteCode());
            }

            YbfMember ybfMember = new YbfMember();
            ybfMember.setUsername(username);
            ybfMember.setNickName(getRandomNickName());
            ybfMember.setArea(registerBody.getArea());
            ybfMember.setParentInviteCode(registerBody.getInviteCode());
            ybfMember.setIsVip(0);
            ybfMember.setIsAgent(0);
            ybfMember.setPassword(SecurityUtils.encryptPassword(registerBody.getPassword()));
            boolean regFlag = ybfMemberService.insertYbfMember(ybfMember) > 0;
            if (!regFlag) {
                msg = "注册失败,请联系管理人员";
                throw new ServiceException(msg);
            } else {
                //    AsyncManager.me().execute(AsyncFactory.recordLogininfor(username, Constants.REGISTER,
                //  MessageUtils.message("user.register.success")));
                msg = MessageUtils.message("user.register.success");
                if (friend != null) {
                    memberFriendshipService.createFriendship(ybfMember.getUsername(), friend.getUsername());
                    goodsOrderRewardService.updateInviteFirend(friend.getUsername());
                    memberGameService.inviteFriendRewardScore(friend.getUsername());
                }

//                int enabled = Integer.parseInt(configService.selectConfigByKey("adapay.realRegister.enabled"));
//                if (enabled == 1) {
//                    Map<String, Object> resultMap = adapayPaymentManager.executerCreateMember(ybfMember.getMemberCode(), ybfMember.getNickName());
//                    if (resultMap.get("status").equals("succeeded")) {
//                        ybfMember.setAdapayRegister(1);
//                        ybfMemberService.updateYbfMember(ybfMember);
//                    }
//                }
            }
        }
        return msg;
    }

    public String getRandomNickName() {
        String newRandomNickName = null;
        YbfMember member = null;
        do {
            newRandomNickName = NameUtils.randomName(true, 4);
            member = ybfMemberService.selectYbfMemberByNickName(newRandomNickName);
        } while (member != null);

        return newRandomNickName;
    }

    /**
     * 校验验证码
     *
     * @param code 验证码
     * @param uuid 唯一标识
     * @return 结果
     */
    public void validateCaptcha(String code, String uuid) {
        String verifyKey = CacheConstants.CAPTCHA_CODE_KEY + StringUtils.nvl(uuid, "");
        String captcha = redisCache.getCacheObject(verifyKey);
        redisCache.deleteObject(verifyKey);
        if (captcha == null) {
            throw new CaptchaExpireException();
        }
        if (!code.equalsIgnoreCase(captcha)) {
            throw new CaptchaException();
        }
    }

    private String login(String username, String password, String code, String uuid) {
        validateCaptcha(code, uuid);
        // 用户验证
        Authentication authentication = null;
        try {
            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(username, password);
            AuthenticationContextHolder.setContext(authenticationToken);
            // 该方法会去调用UserDetailsServiceImpl.loadUserByUsername
            authentication = authenticationManager.authenticate(authenticationToken);
        } catch (Exception e) {
            if (e instanceof BadCredentialsException) {
                //  AsyncManager.me().execute(AsyncFactory.recordLogininfor(username, Constants.LOGIN_FAIL, MessageUtils.message("user.password.not.match")));
                throw new UserPasswordNotMatchException();
            } else {
                // AsyncManager.me().execute(AsyncFactory.recordLogininfor(username, Constants.LOGIN_FAIL, e.getMessage()));
                throw new ServiceException(e.getMessage());
            }
        } finally {
            AuthenticationContextHolder.clearContext();
        }
        // AsyncManager.me().execute(AsyncFactory.recordLogininfor(username, Constants.LOGIN_SUCCESS, MessageUtils.message("user.login.success")));
        AppLoginUser loginUser = (AppLoginUser) authentication.getPrincipal();
        recordLoginInfo(loginUser.getUserId());
        // 生成token
        return tokenService.createToken(loginUser);
    }

    /**
     * 校验短信验证码
     *
     * @param username 用户名
     * @param code     验证码
     * @param uuid     唯一标识
     * @return 结果
     */
    private void validateSMS(String username, String code, String uuid) {
        String verifyKey = CacheConstants.SMS_CODE_KEY + "_" + username + "_" + StringUtils.nvl(uuid, "");
        String captcha = redisCache.getCacheObject(verifyKey);
        redisCache.deleteObject(verifyKey);
        if (captcha == null) {
            //  AsyncManager.me().execute(AsyncFactory.recordLogininfor(username, Constants.LOGIN_FAIL, MessageUtils.message("user.jcaptcha.expire")));
            throw new CaptchaExpireException();
        }

        if (!code.equalsIgnoreCase(captcha)) {
            //     AsyncManager.me().execute(AsyncFactory.recordLogininfor(username, Constants.LOGIN_FAIL, MessageUtils.message("user.jcaptcha.error")));
            throw new CaptchaException();
        }
    }

    /**
     * 记录登录信息
     *
     * @param userId 用户ID
     */
    private void recordLoginInfo(Long userId) {
        YbfMember ybfMember = ybfMemberService.selectYbfMemberById(userId);
        ybfMember.setLoginIp(IpUtils.getIpAddr(ServletUtils.getRequest()));
        ybfMember.setLoginDate(DateUtils.getNowDate());
        ybfMemberService.updateYbfMember(ybfMember);
    }
}


