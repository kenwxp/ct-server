import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.cloudtimes.partner.pay.shouqianba.domain.BuzResponse;
import com.cloudtimes.partner.pay.shouqianba.domain.CommonResp;
import com.cloudtimes.partner.pay.shouqianba.domain.PayOrderData;
import com.cloudtimes.partner.pay.shouqianba.service.ICtShouqianbaApiService;
import com.cloudtimes.partner.pay.shouqianba.service.ICtShouqianbaCisApiService;
import com.cloudtimes.partner.pay.shouqianba.service.impl.CtShouqianbaApiServiceImpl;
import com.cloudtimes.partner.pay.shouqianba.service.impl.CtShouqianbaCisApiServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

public class TestShouqianba {


    public void test1() {
        ICtShouqianbaApiService service = new CtShouqianbaApiServiceImpl();
        BuzResponse bz = service.activateTerminal("test003", "61359037");
        if (bz != null) {
            System.out.println(bz.getData());
        }

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

    public void test2() {
        ICtShouqianbaApiService service = new CtShouqianbaApiServiceImpl();
        BuzResponse bz = service.checkinTerminal("test003", "100051020027440980", "759ac9b16958129d3ee5707991114d8a");
        if (bz != null) {
            System.out.println(bz.getData());
        }
    }

    public void test3() {
        ICtShouqianbaApiService service = new CtShouqianbaApiServiceImpl();
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("terminal_sn", "100051020027440980"); //收钱吧终端ID	收钱吧终端ID，不超过32位的纯数字
        paramMap.put("client_sn", "testorder0000004"); //商户系统订单号	必须在商户系统内唯一；且长度不超过32字节
        paramMap.put("total_amount", "2"); //交易总金额	以分为单位,不超过10位纯数字字符串,超过1亿元的收款请使用银行转账
        paramMap.put("dynamic_id", "134612202685626274"); //条码内容	不超过32字节
        paramMap.put("subject", "测试"); //交易简介	本次交易的简要介绍
        paramMap.put("operator", "0001"); //门店操作员	发起本次交易的操作员
        paramMap.put("notify_url", ""); //回调	支付回调的地址
        CommonResp bz = service.b2cPay(paramMap, "aeee41ee3f908d9d1f9bb5a0dcae03e7");
        if (bz != null) {
            System.out.println(bz);
        }
    }

    public void test4() {
        ICtShouqianbaApiService service = new CtShouqianbaApiServiceImpl();
        PayOrderData payOrderData = service.queryPayOrder("7895282218633583", "", "100051020027440980", "aeee41ee3f908d9d1f9bb5a0dcae03e7");
        if (payOrderData != null) {
            System.out.println(payOrderData);
        }
        //7895282218633583
    }

    public void test5() {
        ICtShouqianbaApiService service = new CtShouqianbaApiServiceImpl();
        BuzResponse bz = service.cancelPayOrder("7895282218633583", "", "100051020027440980", "aeee41ee3f908d9d1f9bb5a0dcae03e7");
        if (bz != null) {
            System.out.println(bz.getData());
        }
        //7895282218633583
    }

    public void test6() {
        ICtShouqianbaCisApiService service = new CtShouqianbaCisApiServiceImpl();
        service.queryMerchantsApply("ZSEJIP78812769");
    }


}
