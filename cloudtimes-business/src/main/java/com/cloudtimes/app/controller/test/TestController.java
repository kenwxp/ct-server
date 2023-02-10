package com.cloudtimes.app.controller.test;


import com.cloudtimes.account.domain.CtUser;
import com.cloudtimes.account.service.ICtUserService;
import com.cloudtimes.app.manager.JWTManager;
import com.cloudtimes.common.constant.RocketMQConstants;
import com.cloudtimes.common.core.controller.BaseController;
import com.cloudtimes.common.core.domain.AjaxResult;
import com.cloudtimes.common.core.domain.entity.AuthUser;
import com.cloudtimes.hardwaredevice.domain.CtDevice;
import com.cloudtimes.hardwaredevice.domain.CtDeviceDoor;
import com.cloudtimes.hardwaredevice.mapper.CtDeviceDoorMapper;
import com.cloudtimes.common.mq.MessageBody;
import com.cloudtimes.partner.hik.service.ICtHikApiService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

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
    @Autowired
    private ICtUserService userService;
    @Autowired
    private CtDeviceDoorMapper ctDeviceDoorMapper;
    @Autowired
    private RocketMQTemplate rocketMQTemplate;

    private static Logger log = LoggerFactory.getLogger(TestController.class);

    @ApiOperation("获取hik token")
    @PostMapping("/hik/token/get")
    public AjaxResult hikToken() {
        String result = hikApiService.getAccessToken();
        log.info(result);
        return AjaxResult.success(result);
    }

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
        String token = jwtManager.createToken(new AuthUser(id, "test"));
        return AjaxResult.success(token);
    }

    @ApiOperation("新增用户测试")
    @GetMapping(value = "/user/insert")
    public AjaxResult insertUser() {
        CtUser ctUser = new CtUser();
        ctUser.setNickName("wxp");
        ctUser.setCreateDate(new Date());
        userService.insertCtUser(ctUser);
        System.out.println(ctUser.getId());
        CtUser ctUsers = userService.selectCtUserById(ctUser.getId());
        return AjaxResult.success(ctUsers);
    }

    @ApiOperation("测试门禁密码mapper")
    @GetMapping(value = "/select/door")
    public AjaxResult selectDoorList() {
        List<CtDeviceDoor> ctDeviceDoors = ctDeviceDoorMapper.selectCtDeviceDoorListByStoreId(new CtDevice());
        return AjaxResult.success(ctDeviceDoors);
    }

    @ApiOperation("测试发送mq")
    @GetMapping(value = "/send/mq")
    public AjaxResult sendMQ() {
        log.info("接受发送mq的请求");
        MessageBody messageBody = new MessageBody();
        messageBody.setPayload("你好");
        rocketMQTemplate.convertAndSend(RocketMQConstants.WS_TOPIC_DEVICE, messageBody);
        return AjaxResult.success();
    }
}


