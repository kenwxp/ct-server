package com.cloudtimes.app.controller.test;


import com.alibaba.fastjson.JSONObject;
import com.cloudtimes.account.domain.CtUser;
import com.cloudtimes.account.service.ICtUserService;
import com.cloudtimes.app.controller.test.model.FaceAuthInfoReq;
import com.cloudtimes.common.core.controller.BaseController;
import com.cloudtimes.common.core.domain.AjaxResult;
import com.cloudtimes.common.core.domain.entity.AuthUser;
import com.cloudtimes.common.enums.ChannelType;
import com.cloudtimes.common.mq.CallDoData;
import com.cloudtimes.common.utils.JWTManager;
import com.cloudtimes.common.utils.XmlUtils;
import com.cloudtimes.common.utils.file.FileReadUtils;
import com.cloudtimes.common.utils.sign.Base64;
import com.cloudtimes.hardwaredevice.domain.CtDevice;
import com.cloudtimes.hardwaredevice.domain.CtDeviceDoor;
import com.cloudtimes.hardwaredevice.mapper.CtDeviceDoorMapper;
import com.cloudtimes.mq.sender.CashMqSender;
import com.cloudtimes.partner.config.PartnerConfig;
import com.cloudtimes.partner.hik.service.ICtHikApiService;
import com.cloudtimes.partner.pay.shouqianba.domain.AuthInfoData;
import com.cloudtimes.partner.pay.shouqianba.domain.PayOrderData;
import com.cloudtimes.partner.pay.shouqianba.service.ICtShouqianbaApiService;
import com.cloudtimes.partner.weixin.ICtWeixinFaceApiService;
import com.cloudtimes.partner.weixin.ICtWeixinOfficialApiService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import me.zhyd.oauth.model.AuthCallback;
import me.zhyd.oauth.model.AuthResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.KeyFactory;
import java.security.interfaces.RSAPrivateKey;
import java.security.spec.PKCS8EncodedKeySpec;
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
    private ICtWeixinFaceApiService faceApiService;
    @Autowired
    private ICtShouqianbaApiService shouqianbaApiService;
    @Autowired
    private JWTManager jwtManager;
    @Autowired
    private ICtUserService userService;
    @Autowired
    private CtDeviceDoorMapper ctDeviceDoorMapper;
    @Autowired
    private CashMqSender cashMqSender;
    @Autowired
    private PartnerConfig config;


    @Autowired
    private ICtWeixinOfficialApiService weixinOfficialApiService;

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
    @PostMapping(value = "/face/auth")
    public AjaxResult testFaceAuthInfo(@RequestBody FaceAuthInfoReq info) {
//        WxpayfaceAuthInfoResp wxpayfaceAuthInfo = faceApiService.getWxpayfaceAuthInfo("199976cb-a786-11ed-8957-0242ac110003", "测试店", "ZDXLVP75870772", info.getRawdata());
        AuthInfoData wxPayFaceAuthInfo = shouqianbaApiService.getWxPayFaceAuthInfo(info.getRawdata(), "100051020027241143", "97c8682e0bd78a0fa4e5db056d10f694");
        if (wxPayFaceAuthInfo != null) {
            return AjaxResult.success(wxPayFaceAuthInfo);
        }
        return AjaxResult.success();
    }

    @ApiOperation("获取刷脸unionid")
    @PostMapping(value = "/face/unionid")
    public AjaxResult testFaceUnionId(@RequestBody FaceAuthInfoReq info) {
        String userUnionId = faceApiService.getWxpayfaceUserUnionId(info.getToken());
        System.out.println(userUnionId);
        return AjaxResult.success(userUnionId);
    }

    @ApiOperation("测试读取pem")
    @PostMapping(value = "/read/auth")
    public AjaxResult testReadPem() {
        RSAPrivateKey rsaPrivateKey = null;
        try {
            rsaPrivateKey = get();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return AjaxResult.success();
    }

    public RSAPrivateKey get() throws Exception {
        String s = FileReadUtils.readFileToString(config.getWxCertPrivatePemPath());
        s = s.replace("-----BEGIN PRIVATE KEY-----", "").replace("-----END PRIVATE KEY-----", "");
        byte[] keyBytes = new Base64().decode(s);
        PKCS8EncodedKeySpec privateKeySpec = new PKCS8EncodedKeySpec(keyBytes);
        KeyFactory kf = KeyFactory.getInstance("RSA");
        RSAPrivateKey pk = (RSAPrivateKey) kf.generatePrivate(privateKeySpec);
        return pk;
    }


    @ApiOperation("微信公众号授权登录")
    @GetMapping(value = "/official/auth")
    public AjaxResult testOfficialAuth(AuthCallback callback) {
        System.out.println(JSONObject.toJSONString(callback));
        // callback.setState(AuthStateUtils.createState());
        AuthResponse ds = weixinOfficialApiService.login(callback);

        System.out.println(JSONObject.toJSONString(ds));
        String token = jwtManager.createToken(new AuthUser("", ChannelType.WECHAT.getCode()));
        AjaxResult ajaxResult = AjaxResult.success("成功", token);
        ajaxResult.put("isAgent", false);
        ajaxResult.put("isApprove", true);
        ajaxResult.put("wxUserInfo", ds.getData());

        return ajaxResult;
    }


    @ApiOperation("微信公众号授权登录")
    @GetMapping(value = "/official/wxAuth")
    public void testOfficialAuth(HttpServletResponse response) {
        String url = weixinOfficialApiService.getWXAuthURL(null, null);
        try {
            response.sendRedirect(url);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }


    @ApiOperation("微信公众号授权登录-微信服务回调")
    @GetMapping(value = "/official/callback")
    public void testOfficialCallback(AuthCallback callback, HttpServletResponse response) throws IOException {
        AuthResponse ds = weixinOfficialApiService.login(callback);
        System.out.println(JSONObject.toJSONString(ds));
        try {
            //  response.sendRedirect("https://ctdev.htymeta.com/h5#/pages/login/wxLogin?type=wxauth");
            response.sendRedirect("https://ctdev.htymeta.com/h5");
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

    @ApiOperation("收钱吧设备激活")
    @PostMapping(value = "/device/active")
    public AjaxResult testActiveDevice() {
        PayOrderData order = shouqianbaApiService.queryPayOrder("7895282218633583", "", "100051020027440980", "aeee41ee3f908d9d1f9bb5a0dcae03e7");
        if (order != null) {
            return AjaxResult.success(order);
        }
        return AjaxResult.success();
    }
}


