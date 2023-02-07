package com.cloudtimes.app.controller.cash;

import com.cloudtimes.app.controller.cash.model.CashLoginCheckReq;
import com.cloudtimes.app.controller.cash.model.CashLoginCheckResp;
import com.cloudtimes.app.controller.cash.model.CashLoginReq;
import com.cloudtimes.app.controller.cash.model.CashLoginResp;
import com.cloudtimes.app.manager.JWTManager;
import com.cloudtimes.common.core.domain.AjaxResult;
import com.cloudtimes.common.core.domain.entity.AuthUser;
import com.cloudtimes.common.utils.StringUtils;
import com.cloudtimes.enums.DeviceType;
import com.cloudtimes.hardwaredevice.domain.CtDevice;
import com.cloudtimes.serving.cash.service.ICtCashLoginService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = "收银机登录相关接口")
@RestController
@RequestMapping("/cash/login")
public class CashLoginController {
    @Autowired
    private ICtCashLoginService loginService;
    @Autowired
    private JWTManager jwtManager;

    /**
     * 收银机登录校验接口
     *
     * @param param
     * @return
     */
    @ApiOperation("收银机登录校验接口")
    @PostMapping("/check")
    public AjaxResult loginCheck(@RequestBody CashLoginCheckReq param) {
        if (StringUtils.isEmpty(param.getDeviceSerial())) {
            return AjaxResult.error("设备序列号不能为空");
        }
        boolean isNew = loginService.checkCashNew(param.getDeviceSerial());
        CashLoginCheckResp loginCheckResp = new CashLoginCheckResp();
        loginCheckResp.setIsNew(isNew ? "1" : "0");
        return AjaxResult.success(loginCheckResp);
    }

    /**
     * 收银机登录接口
     *
     * @param param
     * @return
     */
    @ApiOperation("收银机登录接口")
    @PostMapping("")
    public AjaxResult login(@RequestBody CashLoginReq param) {
        if (StringUtils.isEmpty(param.getDeviceSerial())) {
            return AjaxResult.error("设备序列号不能为空");
        }
        // 收银机登录服务
        CtDevice deviceInfo = loginService.cashLogin(param.getDeviceSerial(), param.getDeviceName(), param.getShopNo());
        CashLoginResp loginResp = new CashLoginResp();
        // 封装返回参数
        //获取token,时效为永久
        String token = jwtManager.createToken(new AuthUser(deviceInfo.getId()), 0);
        loginResp.setAccessToken(token);
        return AjaxResult.success(loginResp);
    }
}