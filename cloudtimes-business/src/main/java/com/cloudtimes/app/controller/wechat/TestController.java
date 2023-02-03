package com.cloudtimes.app.controller.wechat;


import com.cloudtimes.app.manager.JWTManager;
import com.cloudtimes.common.core.controller.BaseController;
import com.cloudtimes.common.core.domain.AjaxResult;
import com.cloudtimes.common.core.domain.entity.AuthUser;
import com.cloudtimes.partner.hik.service.ICtHikApiService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 测试Controller
 *
 * @author wangxp
 * @date 2023-02-02
 */
@Api(tags = "测试接口")
@RestController
@RequestMapping("/test")
public class TestController extends BaseController {

    @Autowired
    private ICtHikApiService hikApiService;
    @Autowired
    private JWTManager jwtManager;
    private static Logger log = LoggerFactory.getLogger(TestController.class);

    @ApiOperation("查询设备信息")
    @PostMapping("/hik/device/info")
    public AjaxResult login() {
        String result = hikApiService.getDeviceInfo("G28019093");
        log.info(result);
        return AjaxResult.success(result);
    }

    @ApiOperation("获取jwt")
    @GetMapping(value = "/token/get/{id}")
    public AjaxResult getInfo(@PathVariable("id") String id) {
        String token = jwtManager.createToken(new AuthUser(id));
        return AjaxResult.success(token);
    }

}


