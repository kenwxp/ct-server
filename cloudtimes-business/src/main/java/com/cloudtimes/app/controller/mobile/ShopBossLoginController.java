package com.cloudtimes.app.controller.mobile;

import com.cloudtimes.account.domain.CtUser;
import com.cloudtimes.app.controller.mobile.model.LoginReq;
import com.cloudtimes.app.controller.mobile.model.LoginResp;
import com.cloudtimes.app.controller.mobile.model.RegisterReq;
import com.cloudtimes.app.manager.JWTManager;
import com.cloudtimes.common.constant.HttpCode;
import com.cloudtimes.common.core.domain.AjaxResult;
import com.cloudtimes.common.core.domain.entity.AuthUser;
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
        return new AjaxResult(HttpCode.OK, "注册成功", null);
    }

    @ApiOperation("用户登录")
    @PostMapping("/login")
    public AjaxResult login(@RequestBody LoginReq param, HttpServletRequest request) {
        CtUser ctUser = loginService.shopBossLogin(param.getPhone(), param.getPassword(), IpUtils.getIpAddr(request));
        String token = jwtManager.createToken(new AuthUser(ctUser.getId()));
        LoginResp loginResp = new LoginResp();
        loginResp.setToken(token);
        return new AjaxResult(HttpCode.OK, "登录成功", loginResp);
    }

}
