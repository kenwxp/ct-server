package com.cloudtimes.app.controller.auth;


import com.cloudtimes.account.domain.CtUser;
import com.cloudtimes.account.service.ICtUserService;
import com.cloudtimes.app.manager.CaptchaManager;
import com.cloudtimes.common.annotation.RateLimiter;
import com.cloudtimes.common.constant.Constants;
import com.cloudtimes.common.constant.UserConstants;
import com.cloudtimes.common.core.controller.BaseController;
import com.cloudtimes.common.core.domain.AjaxResult;
import com.cloudtimes.common.core.domain.model.LoginBody;
import com.cloudtimes.common.core.domain.model.RegisterBody;
import com.cloudtimes.common.core.redis.RedisCache;
import com.cloudtimes.common.enums.LimitType;
import com.cloudtimes.common.exception.ServiceException;
import com.cloudtimes.common.utils.DateUtils;
import com.cloudtimes.common.utils.SecurityUtils;
import com.cloudtimes.common.utils.ServletUtils;
import com.cloudtimes.common.utils.StringUtils;
import com.cloudtimes.common.utils.ip.IpUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * 会员Controller
 *
 * @author polo
 * @date 2022-1-30
 */
@Api(tags = "用户登录和注册接口")
@RestController
@RequestMapping("/auth/user")
public class UserLoginAndRegisterController extends BaseController {
    @Autowired
    private ICtUserService ctUserService;

    @Autowired
    private RedisCache redisCache;

    @Autowired
    private CaptchaManager smsManager;

    private static Logger log = LoggerFactory.getLogger(UserLoginAndRegisterController.class);

    /**
     * 顾客用户登录
     *
     * @param loginBody 登录信息
     * @return 结果
     */
    @ApiOperation("顾客用户登录")
    @PostMapping("/customerLogin")
    public AjaxResult login(@RequestBody LoginBody loginBody, HttpServletRequest request) {
        AjaxResult ajax = AjaxResult.success();
        // 生成令牌
        String token = login(loginBody.getAccount(), loginBody.getPassword(), loginBody.getCode(), loginBody.getUuid());
        ajax.put(Constants.TOKEN, token);
        return ajax;
    }

    @RateLimiter(limitType = LimitType.IP, count = 10, time = 60)
    @ApiOperation("顾客用户注册")
    @PostMapping("/register")
    public AjaxResult register(@RequestBody RegisterBody user) {
        String msg = runRegister(user);
        return AjaxResult.success(msg);
    }


    /**
     * 注册
     */
    private String runRegister(RegisterBody registerBody) {
        String msg = "", account = registerBody.getAccount(), password = registerBody.getPassword();
        smsManager.checkSMSCaptcha(registerBody.getUuid(), account, registerBody.getCode());

        if (StringUtils.isEmpty(account)) {
            msg = "帐号不能为空";
            throw new ServiceException(msg);
        } else if (StringUtils.isEmpty(password)) {
            msg = "密码不能为空";
            throw new ServiceException(msg);
        } else if (account.length() < UserConstants.USERNAME_MIN_LENGTH
                || account.length() > UserConstants.USERNAME_MAX_LENGTH) {
            msg = "账户长度必须11字符";
            throw new ServiceException(msg);
        } else if (password.length() < UserConstants.PASSWORD_MIN_LENGTH
                || password.length() > UserConstants.PASSWORD_MAX_LENGTH) {
            msg = "密码长度必须在6到16个字符之间";
            throw new ServiceException(msg);
        } else if (ctUserService.selectCtUserByAccount(account) != null) {
            msg = "保存用户'" + account + "'失败，注册账号已存在";
            throw new ServiceException(msg);
        } else {

            CtUser user = new CtUser();
            user.setAccount(account);
            /** TODO 未完成*/
            user.setPassword(SecurityUtils.encryptPassword(registerBody.getPassword()));
            boolean regFlag = ctUserService.insertCtUser(user) > 0;
            if (!regFlag) {
                msg = "注册失败,请联系管理人员";
                throw new ServiceException(msg);
            } else {
            }
        }
        return msg;
    }


    private String login(String username, String password, String code, String uuid) {
        smsManager.checkCodeCaptcha(code, uuid);
        // 生成token
        return "";
    }


    /**
     * 记录登录信息
     *
     * @param userCode 用户ID
     */
    private void recordLoginInfo(String userCode) {
        CtUser ctUser = new CtUser();
        ctUser.setLastLoginIp(IpUtils.getIpAddr(ServletUtils.getRequest()));
        ctUser.setLastLoginTime(DateUtils.getNowDate());
        ctUserService.updateCtUser(ctUser);
    }
}


