package com.cloudtimes.app.controller.wechat;


import com.cloudtimes.account.domain.CtUser;
import com.cloudtimes.app.controller.wechat.model.LoginCheckReq;
import com.cloudtimes.app.controller.wechat.model.LoginCheckResp;
import com.cloudtimes.app.controller.wechat.model.LoginReq;
import com.cloudtimes.app.controller.wechat.model.LoginResp;
import com.cloudtimes.app.manager.JWTManager;
import com.cloudtimes.common.core.domain.AjaxResult;
import com.cloudtimes.app.models.ApiResult;
import com.cloudtimes.common.core.domain.entity.AuthUser;
import com.cloudtimes.common.enums.ChannelType;
import com.cloudtimes.common.utils.ip.IpUtils;
import com.cloudtimes.serving.wechat.service.ICtCustomerLoginService;
import com.github.xiaoymin.knife4j.annotations.DynamicParameters;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@Api(tags = "小程序登录相关接口")
@RestController
@RequestMapping("/mapp/login")
public class CustomerLoginController {

    @Autowired
    private ICtCustomerLoginService loginService;
    @Autowired
    private JWTManager jwtManager;

    /**
     * 小程序用户登录校验接口
     *
     * @param param
     * @return
     */
    @ApiOperation("小程序用户登录校验")
    @PostMapping("/check")
//    @ApiResponses(
//            @ApiResponse(code = 200, message = "成功", response = LoginCheckResp.class)
//    )
    public ApiResult<LoginCheckResp> loginCheck(@RequestBody LoginCheckReq param) {
        boolean isNewCustomer = loginService.checkCustomerNew(param.getLoginCode());
        LoginCheckResp loginCheckResp = new LoginCheckResp();
        loginCheckResp.setIsNew(isNewCustomer ? "1" : "0");
        return new ApiResult().success(loginCheckResp);
    }

    /**
     * 小程序用户登录接口
     *
     * @param param
     * @return
     */
    @ApiOperation("小程序用户登录接口")
    @PostMapping("")
    @ApiResponses(
            @ApiResponse(code = 200, message = "成功", response = AjaxResult.class)
    )
    public AjaxResult login(@RequestBody LoginReq param, HttpServletRequest request) {
        String loginIp = IpUtils.getIpAddr(request);
        CtUser customerInfo = loginService.customerLogin(param.getLoginCode(), param.getPhoneCode(), loginIp);
        LoginResp loginResp = new LoginResp();
        // 封装返回参数
        //获取token
        String token = jwtManager.createToken(new AuthUser(customerInfo.getId(), ChannelType.WECHAT.getCode()));
        loginResp.setAccessToken(token);
        return AjaxResult.success(loginResp);
    }

}
