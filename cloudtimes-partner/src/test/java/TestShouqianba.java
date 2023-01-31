import com.cloudtimes.common.utils.SHA1withRSAUtil;
import com.cloudtimes.common.utils.sign.Base64;
import com.cloudtimes.partner.pay.shouqianba.service.ICtShouqianbaApiService;
import com.cloudtimes.partner.pay.shouqianba.service.ICtShouqianbaCisApiService;
import com.cloudtimes.partner.pay.shouqianba.service.impl.CtShouqianbaApiService;
import com.cloudtimes.partner.pay.shouqianba.service.impl.CtShouqianbaCisApiService;
import org.junit.Test;

import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.security.*;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class TestShouqianba {


    @Test
    public void test1() {
        ICtShouqianbaApiService service = new CtShouqianbaApiService();
        Map<String, Object> result = service.activateTerminal("test002", "61359037");
        Map<String, String> response = (Map<String, String>) result.get("biz_response");
        System.out.println(response.get("terminal_sn"));
        System.out.println(response.get("terminal_key"));
        //{
        //    "result_code": "200",
        //    "biz_response": {
        //        "terminal_sn": "100051020027440980",
        //        "terminal_key": "759ac9b16958129d3ee5707991114d8a",
        //        "merchant_sn": "1680005823147",
        //        "merchant_name": "雅安云时代商业管理有限公司",
        //        "store_sn": "1580000006195487",
        //        "store_name": "云时代测试运营店"
        //    }
        //}
    }

    @Test
    public void test2() {
        ICtShouqianbaApiService service = new CtShouqianbaApiService();
        Map<String, Object> result = service.checkinTerminal("test003", "100051020027440980", "759ac9b16958129d3ee5707991114d8a");
        Map<String, String> response = (Map<String, String>) result.get("biz_response");
        System.out.println(response.get("terminal_sn"));    //100051020027440980
        System.out.println(response.get("terminal_key")); //aeee41ee3f908d9d1f9bb5a0dcae03e7
    }

    @Test
    public void test3() {
        ICtShouqianbaApiService service = new CtShouqianbaApiService();
        Map<String, String> paramMap = new HashMap<>();
        paramMap.put("terminal_sn", "100051020027440980"); //收钱吧终端ID	收钱吧终端ID，不超过32位的纯数字
        paramMap.put("client_sn", "testorder0000004"); //商户系统订单号	必须在商户系统内唯一；且长度不超过32字节
        paramMap.put("total_amount", "2"); //交易总金额	以分为单位,不超过10位纯数字字符串,超过1亿元的收款请使用银行转账
        paramMap.put("dynamic_id", "134612202685626274"); //条码内容	不超过32字节
        paramMap.put("subject", "测试"); //交易简介	本次交易的简要介绍
        paramMap.put("operator", "0001"); //门店操作员	发起本次交易的操作员
        paramMap.put("notify_url", ""); //回调	支付回调的地址
        Map<String, Object> result = service.b2cPay(paramMap, "aeee41ee3f908d9d1f9bb5a0dcae03e7");
        Map<String, String> response = (Map<String, String>) result.get("biz_response");
        //7895282218633583
        //testorder0000004
    }

    @Test
    public void test4() {
        ICtShouqianbaApiService service = new CtShouqianbaApiService();
        Map<String, Object> result = service.queryPayOrder("7895282218633583", "", "100051020027440980", "aeee41ee3f908d9d1f9bb5a0dcae03e7");
        Map<String, Object> response = (Map<String, Object>) result.get("biz_response");
        System.out.println(response);
        Map<String, String> data = (Map<String, String>) response.get("data");
        System.out.println(data);
        System.out.println(data.get("status"));
        //7895282218633583
    }

    @Test
    public void test5() {
        ICtShouqianbaApiService service = new CtShouqianbaApiService();
        Map<String, Object> result = service.cancelPayOrder("7895282218633583", "", "100051020027440980", "aeee41ee3f908d9d1f9bb5a0dcae03e7");
        Map<String, Object> response = (Map<String, Object>) result.get("biz_response");
        System.out.println(response);
        Map<String, String> data = (Map<String, String>) response.get("data");
        System.out.println(data);
        //7895282218633583
    }

    @Test
    public void test6() {
        ICtShouqianbaCisApiService service = new CtShouqianbaCisApiService();
        service.queryMerchantsApply("ZSEJIP78812769");
    }


}
