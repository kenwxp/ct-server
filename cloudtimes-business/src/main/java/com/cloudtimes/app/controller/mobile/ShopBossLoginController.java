package com.cloudtimes.app.controller.mobile;

import com.alibaba.druid.util.StringUtils;
import com.cloudtimes.account.domain.CtUser;
import com.cloudtimes.app.controller.mobile.model.ChangePasswordReq;
import com.cloudtimes.app.controller.mobile.model.LoginReq;
import com.cloudtimes.app.controller.mobile.model.LoginResp;
import com.cloudtimes.app.controller.mobile.model.RegisterReq;
import com.cloudtimes.app.manager.JWTManager;
import com.cloudtimes.common.constant.HttpCode;
import com.cloudtimes.common.core.domain.AjaxResult;
import com.cloudtimes.common.core.domain.entity.AuthUser;
import com.cloudtimes.common.enums.ChannelType;
import com.cloudtimes.common.utils.AuthUtils;
import com.cloudtimes.common.utils.ip.IpUtils;
import com.cloudtimes.serving.mobile.service.ICtShopBossLoginService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@Api(tags = "店家app登录相关接口")
@RestController
@RequestMapping("/mobile")
public class ShopBossLoginController {
    @Autowired
    private ICtShopBossLoginService loginService;
    @Autowired
    private JWTManager jwtManager;

    @ApiOperation("用户注册")
    @PostMapping("/register")
    public AjaxResult register(@RequestBody RegisterReq param, HttpServletRequest request) {
        loginService.shopBossRegister(
                param.getPhone(),
                param.getPassword(),
                param.getAccount(),
                param.getNickName());
        return AjaxResult.success();
    }

    @ApiOperation("用户登录")
    @PostMapping("/login")
    public AjaxResult login(@RequestBody LoginReq param, HttpServletRequest request) {
        CtUser ctUser = loginService.shopBossLogin(param.getPhone(), param.getPassword(), IpUtils.getIpAddr(request));
        String token = jwtManager.createToken(new AuthUser(ctUser.getId(), ChannelType.MOBILE.getCode()));
        LoginResp loginResp = new LoginResp();
        loginResp.setToken(token);
        return AjaxResult.success(loginResp);
    }

    @ApiOperation("用户修改密码")
    @PostMapping("/password/change")
    public AjaxResult changePassword(@RequestBody ChangePasswordReq param) {
        AuthUser authUser = AuthUtils.getObject();
        if (StringUtils.equals(authUser.getChannelType(), ChannelType.MOBILE.getCode())) {
            return AjaxResult.error("渠道类型不匹配");
        }
        return loginService.changePassword(authUser.getId(), param.getPasswordNew(), param.getPasswordOld()) ? AjaxResult.success() : AjaxResult.error();
    }


}
