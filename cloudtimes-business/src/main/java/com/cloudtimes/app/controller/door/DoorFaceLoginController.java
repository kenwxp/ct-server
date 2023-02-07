package com.cloudtimes.app.controller.door;

import com.cloudtimes.app.controller.door.model.DoorFaceLoginCheckReq;
import com.cloudtimes.app.controller.door.model.DoorFaceLoginCheckResp;
import com.cloudtimes.app.controller.door.model.DoorFaceLoginReq;
import com.cloudtimes.app.controller.door.model.DoorFaceLoginResp;
import com.cloudtimes.app.manager.JWTManager;
import com.cloudtimes.common.core.domain.AjaxResult;
import com.cloudtimes.common.core.domain.entity.AuthUser;
import com.cloudtimes.common.utils.StringUtils;
import com.cloudtimes.enums.DeviceType;
import com.cloudtimes.hardwaredevice.domain.CtDevice;
import com.cloudtimes.serving.door.service.ICtDoorFaceLoginService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = "门禁刷脸登录相关接口")
@RestController
@RequestMapping("/door/login")
public class DoorFaceLoginController {
    @Autowired
    private ICtDoorFaceLoginService loginService;
    @Autowired
    private JWTManager jwtManager;

    /**
     * 门禁刷脸登录校验接口
     *
     * @param param
     * @return
     */
    @ApiOperation("门禁刷脸登录校验接口")
    @PostMapping("/check")
    public AjaxResult loginCheck(@RequestBody DoorFaceLoginCheckReq param) {
        if (StringUtils.isEmpty(param.getDeviceSerial())) {
            return AjaxResult.error("设备序列号不能为空");
        }
        boolean isNew = loginService.checkDoorFaceNew(param.getDeviceSerial());
        DoorFaceLoginCheckResp loginCheckResp = new DoorFaceLoginCheckResp();
        loginCheckResp.setIsNew(isNew ? "1" : "0");
        return AjaxResult.success(loginCheckResp);
    }

    /**
     * 门禁刷脸登录接口
     *
     * @param param
     * @return
     */
    @ApiOperation("门禁刷脸登录接口")
    @PostMapping("")
    public AjaxResult login(@RequestBody DoorFaceLoginReq param) {
        if (StringUtils.isEmpty(param.getDeviceSerial())) {
            return AjaxResult.error("设备序列号不能为空");
        }
        // 门禁刷脸登录服务
        CtDevice deviceInfo = loginService.doorFaceLogin(param.getDeviceSerial(), param.getDeviceName(), param.getShopNo());
        DoorFaceLoginResp loginResp = new DoorFaceLoginResp();
        // 封装返回参数
        //获取token,时效为永久
        String token = jwtManager.createToken(new AuthUser(deviceInfo.getId()), 0);
        loginResp.setAccessToken(token);
        return AjaxResult.success(loginResp);
    }
}
