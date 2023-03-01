package com.cloudtimes.app.controller.mobile;

import com.alibaba.druid.util.StringUtils;
import com.cloudtimes.account.domain.CtUser;
import com.cloudtimes.app.controller.mobile.model.ChangePasswordReq;
import com.cloudtimes.app.controller.mobile.model.LoginReq;
import com.cloudtimes.app.controller.mobile.model.LoginResp;
import com.cloudtimes.app.controller.mobile.model.RegisterReq;
import com.cloudtimes.common.core.domain.ApiResult;
import com.cloudtimes.common.utils.JWTManager;
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
@RequestMapping("/mobile/login")
public class ShopBossLoginController {
    @Autowired
    private ICtShopBossLoginService loginService;
    @Autowired
    private JWTManager jwtManager;

    @ApiOperation("用户注册")
    @PostMapping("/register")
    public ApiResult register(@RequestBody RegisterReq param, HttpServletRequest request) {
        loginService.shopBossRegister(
                param.getPhone(),
                param.getPassword(),
                param.getAccount(),
                param.getNickName());
        return new ApiResult().success();
    }

    @ApiOperation("用户登录")
    @PostMapping("")
    public ApiResult<LoginResp> login(@RequestBody LoginReq param, HttpServletRequest request) {
        CtUser ctUser = loginService.shopBossLogin(param.getPhone(), param.getPassword(), IpUtils.getIpAddr(request));
        String token = jwtManager.createToken(new AuthUser(ctUser.getId(), ChannelType.MOBILE.getCode()));
        LoginResp loginResp = new LoginResp();
        loginResp.setToken(token);
        return new ApiResult().success(loginResp);
    }

}
