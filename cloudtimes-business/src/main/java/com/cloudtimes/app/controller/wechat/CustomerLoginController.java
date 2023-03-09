package com.cloudtimes.app.controller.wechat;


import com.cloudtimes.app.constant.PrefixPathConstants;
import com.cloudtimes.serving.wechat.domain.MpLoginCheckReq;
import com.cloudtimes.serving.wechat.domain.MpLoginCheckResp;
import com.cloudtimes.serving.wechat.domain.MpLoginReq;
import com.cloudtimes.serving.wechat.domain.MpLoginResp;
import com.cloudtimes.common.core.domain.ApiResult;
import com.cloudtimes.common.core.domain.entity.AuthUser;
import com.cloudtimes.common.enums.ChannelType;
import com.cloudtimes.common.utils.JWTManager;
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
@RequestMapping(PrefixPathConstants.WX_MP_PATH_PREFIX + "/login")
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
    public ApiResult<MpLoginCheckResp> loginCheck(@RequestBody MpLoginCheckReq param) {
        MpLoginCheckResp loginCheckResp = loginService.checkCustomerNew(param.getLoginCode());
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
    public ApiResult<MpLoginResp> login(@RequestBody MpLoginReq param, HttpServletRequest request) {
        String loginIp = IpUtils.getIpAddr(request);
        MpLoginResp loginResp = loginService.customerLogin(param, loginIp);
        // 封装返回参数
        //获取token
        String token = jwtManager.createToken(new AuthUser(loginResp.getUserId(), ChannelType.WX_MP));
        loginResp.setAccessToken(token);
        return new ApiResult().success(loginResp);
    }

}
