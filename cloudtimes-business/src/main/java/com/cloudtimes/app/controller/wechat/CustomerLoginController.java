package com.cloudtimes.app.controller.wechat;


import com.cloudtimes.account.domain.CtUser;
import com.cloudtimes.app.controller.wechat.model.LoginCheckReq;
import com.cloudtimes.app.controller.wechat.model.LoginCheckResp;
import com.cloudtimes.app.controller.wechat.model.LoginReq;
import com.cloudtimes.app.controller.wechat.model.LoginResp;
import com.cloudtimes.app.manager.JWTManager;
import com.cloudtimes.common.constant.HttpCode;
import com.cloudtimes.common.core.domain.AjaxResult;
import com.cloudtimes.common.core.domain.entity.AuthUser;
import com.cloudtimes.common.utils.ip.IpUtils;
import com.cloudtimes.serving.wechat.service.ICtCustomerLoginService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@Api(tags = "小程序登录相关接口")
@RestController
@RequestMapping("/auth/customer")
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
    public AjaxResult loginCheck(@RequestBody LoginCheckReq param) {
        boolean isNewCustomer = loginService.checkCustomerNew(param.getLoginCode());
        LoginCheckResp loginCheckResp = new LoginCheckResp();
        loginCheckResp.setIsNew(isNewCustomer ? "1" : "0");
        return new AjaxResult(HttpCode.OK, "", loginCheckResp);
    }

    /**
     * 小程序用户登录接口
     *
     * @param param
     * @return
     */
    @ApiOperation("小程序用户登录接口")
    @PostMapping("/login")
    public AjaxResult login(@RequestBody LoginReq param, HttpServletRequest request) {
        String loginIp = IpUtils.getIpAddr(request);
        CtUser customerInfo = loginService.customerLogin(param.getLoginCode(), param.getPhoneCode(), loginIp);
        LoginResp loginResp = new LoginResp();
        // 封装返回参数
        //获取token
        String token = jwtManager.createToken(new AuthUser(customerInfo.getId()));
        loginResp.setAccessToken(token);
        return new AjaxResult(HttpCode.OK, "", loginResp);
    }

}
