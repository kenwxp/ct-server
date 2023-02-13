package com.cloudtimes.app.controller.test;


import com.cloudtimes.account.domain.CtUser;
import com.cloudtimes.account.service.ICtUserService;
import com.cloudtimes.common.core.controller.BaseController;
import com.cloudtimes.common.core.domain.AjaxResult;
import com.cloudtimes.common.core.domain.entity.AuthUser;
import com.cloudtimes.common.mq.CallDoData;
import com.cloudtimes.common.utils.JWTManager;
import com.cloudtimes.common.utils.XmlUtils;
import com.cloudtimes.hardwaredevice.domain.CtDevice;
import com.cloudtimes.hardwaredevice.domain.CtDeviceDoor;
import com.cloudtimes.hardwaredevice.mapper.CtDeviceDoorMapper;
import com.cloudtimes.mq.service.CashMqSender;
import com.cloudtimes.partner.hik.service.ICtHikApiService;
import com.cloudtimes.partner.weixin.ICtWeixinApiService;
import com.cloudtimes.partner.weixin.domain.WxpayfaceAuthInfoResp;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
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
    private ICtWeixinApiService weixinApiService;
    @Autowired
    private JWTManager jwtManager;
    @Autowired
    private ICtUserService userService;
    @Autowired
    private CtDeviceDoorMapper ctDeviceDoorMapper;
    @Autowired
    private CashMqSender cashMqSender;

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
        cashMqSender.notifyCashDutyStatus("199976cb-a786-11ed-8957-0242ac110003", "1");
        return AjaxResult.success();
    }

    @ApiOperation("测试xml解析")
    @GetMapping(value = "/xml")
    public AjaxResult testXML() {
        CallDoData callDoData = new CallDoData();
        callDoData.setDoJoin("1");
        String s1 = XmlUtils.formatXml(callDoData);
        System.out.println(s1);
        CallDoData data = XmlUtils.parseXml(s1, CallDoData.class);
        System.out.println(data);
        return AjaxResult.success();
    }


    @ApiOperation("获取刷脸凭证")
    @GetMapping(value = "/face/auth/{rawdata}")
    public AjaxResult testFaceAuthInfo(@PathVariable("rawdata") String rawdata) {
        WxpayfaceAuthInfoResp wxpayfaceAuthInfo = weixinApiService.getWxpayfaceAuthInfo("199976cb-a786-11ed-8957-0242ac110003", "测试店", "ZDXLVP75870772", rawdata);
        return AjaxResult.success(wxpayfaceAuthInfo);
    }
}


