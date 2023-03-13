package com.cloudtimes.app.controller.cash;

import com.cloudtimes.app.constant.PrefixPathConstants;
import com.cloudtimes.common.core.domain.ApiResult;
import com.cloudtimes.common.core.domain.entity.AuthUser;
import com.cloudtimes.common.enums.ChannelType;
import com.cloudtimes.common.utils.JWTManager;
import com.cloudtimes.common.utils.StringUtils;
import com.cloudtimes.hardwaredevice.service.ICtStoreService;
import com.cloudtimes.business.cash.service.ICtCashLoginService;
import com.cloudtimes.business.cash.service.domain.CashLoginCheckResp;
import com.cloudtimes.business.cash.service.domain.CashLoginReq;
import com.cloudtimes.business.cash.service.domain.CashLoginResp;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "收银机登录相关接口")
@RestController
@RequestMapping(PrefixPathConstants.CASH_PATH_PREFIX + "/login")
public class CashLoginController {
    @Autowired
    private ICtCashLoginService loginService;
    @Autowired
    private JWTManager jwtManager;
    @Autowired
    private ICtStoreService storeService;


    /**
     * 收银机登录校验接口
     *
     * @param param
     * @return
     */
    @Operation(summary = "收银机登录校验接口")
    @PostMapping("/check")
    public ApiResult<CashLoginCheckResp> loginCheck(@RequestBody CashLoginReq param) {
        if (StringUtils.isEmpty(param.getDeviceSerial())) {
            return new ApiResult().error("设备序列号不能为空");
        }
        CashLoginCheckResp loginCheckResp = loginService.checkCashNew(param);
        return new ApiResult().success(loginCheckResp);
    }

    /**
     * 收银机登录接口
     *
     * @param param
     * @return
     */
    @Operation(summary = "收银机登录接口")
    @PostMapping("")
    public ApiResult<CashLoginResp> login(@RequestBody CashLoginReq param) {
        if (StringUtils.isEmpty(param.getDeviceSerial())) {
            return new ApiResult().error("设备序列号不能为空");
        }
        // 收银机登录服务
        CashLoginResp loginResp = loginService.cashLogin(param);
        // 封装返回参数
        //获取token,时效为永久
        String token = jwtManager.createToken(new AuthUser(loginResp.getDeviceId(), ChannelType.CASH), 0);
        loginResp.setAccessToken(token);
        return new ApiResult().success(loginResp);
    }
}
