package com.cloudtimes.test;

import com.alibaba.fastjson.JSONObject;
import com.huifu.adapay.core.exception.BaseAdaPayException;
import com.huifu.adapay.model.AdapayCommon;
import com.cloudtimes.common.payment.adapay.AdapayPaymentManager;
import com.cloudtimes.system.service.ISysConfigService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AppSpringbootTest {


    @Autowired
    private AdapayPaymentManager adapayPaymentManager;

    @Autowired
    private ISysConfigService configService;


    @Test
    public void testAdapayPayment() {
        Map<String, Object> paymentParams = new HashMap<>(10);
        paymentParams.put("adapay_func_code", "wxpay.createOrder");
        paymentParams.put("app_id", adapayPaymentManager.getAppId());
        paymentParams.put("order_no", System.currentTimeMillis() + "");
        paymentParams.put("pay_channel", "wx_lite");
        paymentParams.put("pay_amt", 0.01);
        paymentParams.put("goods_title", "测试商品");
        paymentParams.put("goods_desc", "测试商品");
        paymentParams.put("currency", "cny");
        paymentParams.put("notify_url", "http://www.baidu.com");

//        Map<String, Object> expendParams = new HashMap<>(2);
//        expendParams.put("open_id","coRrdQt-xtS-JnNw5pb2kuCixatek");
//        paymentParams.put("expend", expendParams);

        //调用sdk方法，创建支付，得到支付对象
        Map<String, Object> payment = new HashMap<>();
        try {
            payment = AdapayCommon.requestAdapayUits(paymentParams);
        } catch (BaseAdaPayException e) {
            e.printStackTrace();
        }

        System.out.println("result:" + JSONObject.toJSONString(payment));
    }

    @Test
    public void testAdapayZFBPayment() {
        String jsonString = "{" +
                "\"appId\": \"app_f257335e-b45a-4365-8fc5-6685c985dc67\"," +
                "\"wxAppId\": \"wx572d6b1bf8a7e139\"," +
                "\"callbackURL\": \"https://www.weixin.com\"," +
                "        \"pay_channel\":\"alipay\"," +
                "        \"merchantKey\":\"api_live_45eb98d2-9f47-4298-b6f7-88e736df0d80\"" +
                "}";

        JSONObject configJson = JSONObject.parseObject(jsonString);
        Map<String, Object> resultMap = adapayPaymentManager.createZFBPayment(configJson, configService.selectConfigByKey("app.payment.notify.url"), "20221201584786", "元宝枫调和油3升装1桶", "3升装1桶", BigDecimal.valueOf(0.01D));

        System.out.println("result:" + JSONObject.toJSONString(resultMap));
    }


    @Test
    public void testAdapayFastPayment() {
        String jsonString = "{" +
                "\"appId\": \"app_f257335e-b45a-4365-8fc5-6685c985dc67\"," +
                "\"wxAppId\": \"wx572d6b1bf8a7e139\"," +
                "\"callbackURL\": \"https://www.weixin.com\"," +
                "        \"pay_channel\":\"fast_pay\"," +
                "        \"merchantKey\":\"api_live_45eb98d2-9f47-4298-b6f7-88e736df0d80\"" +
                "}";

        // JSONObject configJson = JSONObject.parseObject(jsonString);
        // Map<String, Object> resultMap = adapayPaymentManager.createFastPayment(configJson, configService.selectConfigByKey("app.payment.notify.url"), "20221201584712", "元宝枫调和油3升装1桶", "3升装1桶", BigDecimal.valueOf(0.01D));

        //   System.out.println("result:" + JSONObject.toJSONString(resultMap));
    }


    @Test
    public void testAdapayCreateMember() {

        Map<String, Object> map = adapayPaymentManager.executerCreateMember("888888", "test001");

        System.out.println("result:" + JSONObject.toJSONString(map));
    }
}
