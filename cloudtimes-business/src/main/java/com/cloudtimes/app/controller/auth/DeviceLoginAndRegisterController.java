package com.cloudtimes.app.controller.auth;


import com.cloudtimes.common.core.controller.BaseController;
import com.cloudtimes.common.core.domain.AjaxResult;
import com.cloudtimes.common.core.domain.entity.AuthUser;
import com.cloudtimes.common.utils.AuthUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 设备登录和注册Controller
 *
 * @author polo
 * @date 2023-1-30
 */
@Api("设备登录和注册接口")
@RestController
@RequestMapping("/auth/device")
public class DeviceLoginAndRegisterController extends BaseController {


    private static Logger log = LoggerFactory.getLogger(DeviceLoginAndRegisterController.class);

    @ApiOperation("设备登录")
    @PostMapping("/login")
    public AjaxResult login() {
        return AjaxResult.success();
    }

    @ApiOperation("测试接口")
    @GetMapping("/test")
    public AjaxResult test() {
        return AjaxResult.success("测试接口访问成功");
    }

}


