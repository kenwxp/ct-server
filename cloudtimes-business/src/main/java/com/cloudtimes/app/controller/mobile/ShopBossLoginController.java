package com.cloudtimes.app.controller.mobile;

import com.cloudtimes.account.domain.CtUser;
import com.cloudtimes.app.constant.PrefixPathConstants;
import com.cloudtimes.serving.mobile.domain.LoginReq;
import com.cloudtimes.serving.mobile.domain.LoginResp;
import com.cloudtimes.serving.mobile.domain.RegisterReq;
import com.cloudtimes.common.core.domain.ApiResult;
import com.cloudtimes.common.utils.JWTManager;
import com.cloudtimes.common.core.domain.entity.AuthUser;
import com.cloudtimes.common.enums.ChannelType;
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
@RequestMapping(PrefixPathConstants.MOBILE_PATH_PREFIX + "/login")
public class ShopBossLoginController {
    @Autowired
    private ICtShopBossLoginService loginService;
    @Autowired
    private JWTManager jwtManager;

    @ApiOperation("用户注册")
    @PostMapping("/register")
    public ApiResult register(@RequestBody RegisterReq param) {
        loginService.shopBossRegister(param);
        return new ApiResult().success();
    }

    @ApiOperation("用户登录")
    @PostMapping("")
    public ApiResult<LoginResp> login(@RequestBody LoginReq param, HttpServletRequest request) {
        LoginResp loginResp = loginService.shopBossLogin(param, IpUtils.getIpAddr(request));
        String token = jwtManager.createToken(new AuthUser(loginResp.getId(), ChannelType.MOBILE));
        loginResp.setToken(token);
        return new ApiResult().success(loginResp);
    }

}
