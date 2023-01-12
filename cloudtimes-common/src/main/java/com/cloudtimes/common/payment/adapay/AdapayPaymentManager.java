package com.cloudtimes.common.payment.adapay;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.huifu.adapay.Adapay;
import com.huifu.adapay.core.exception.BaseAdaPayException;
import com.huifu.adapay.model.*;
import com.cloudtimes.common.core.redis.RedisCache;
import com.cloudtimes.common.exception.ServiceException;
import com.cloudtimes.common.utils.NumberUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/*
 * adapay 支付处理类
 */
@Component
@ConfigurationProperties(prefix = "adapay")
public class AdapayPaymentManager {

    private boolean debug;
    private boolean prodMode;
    private String apiKey;
    private String mockApiKey;
    private String rsaPrivateKey;
    private String appId;
    private String wxAppId;


    private static Logger log = LoggerFactory.getLogger(AdapayPaymentManager.class);

    @Autowired
    private RedisCache redisCache;


    @PostConstruct
    public void init() {
        MerConfig merConfig = new MerConfig();
        merConfig.setApiKey(apiKey);
        merConfig.setApiMockKey(mockApiKey);
        merConfig.setRSAPrivateKey(rsaPrivateKey);
        try {
            Adapay.initWithMerConfig(merConfig);
            log.info("adapay 支付工具初始化完成..." + JSONObject.toJSONString(merConfig));
        } catch (Exception e) {
            e.printStackTrace();
            log.error("adapay 支付工具初始化失败...", e);
        }
    }


    public Map<String, Object> createAdminWXPayment(String notifyUrl, String orderNo, BigDecimal amount) {
        Map<String, Object> paymentParams = new HashMap<>(10);
        paymentParams.put("adapay_func_code", "wxpay.createOrder");
        paymentParams.put("app_id", appId);
        paymentParams.put("order_no", orderNo);
        paymentParams.put("pay_channel", "wx_lite");
        paymentParams.put("pay_amt", NumberUtils.bigDecimaFormatString(amount));
        paymentParams.put("pay_mode", "delay");
        paymentParams.put("goods_title", "内部商品");
        paymentParams.put("goods_desc", "不对外购买的内部商品");
        paymentParams.put("currency", "cny");
        paymentParams.put("notify_url", notifyUrl);

        //调用sdk方法，创建支付，得到支付对象
        Map<String, Object> payment = new HashMap<>();
        try {
            payment = AdapayCommon.requestAdapayUits(paymentParams);
        } catch (BaseAdaPayException e) {
            e.printStackTrace();
        }
        log.info("请求Adapay微信小程序支付返回参数：" + JSONObject.toJSONString(payment));
        return payment;
    }


    public Map<String, Object> createFengzhangPayment(String paymentId, String memberCode, String orderNo, BigDecimal amount) {
        Map<String, Object> confirmParams = new HashMap<>();
        confirmParams.put("payment_id", paymentId);
        confirmParams.put("order_no", orderNo);
        confirmParams.put("confirm_amt", NumberUtils.bigDecimaFormatString(amount));
        List<Map<String, String>> memberList = new ArrayList<>();
        Map<String, String> divMember = new HashMap<>(3);
        divMember.put("member_id", memberCode);
        divMember.put("amount", NumberUtils.bigDecimaFormatString(amount));
        divMember.put("fee_flag", "Y");
        memberList.add(divMember);
        confirmParams.put("div_members", memberList);

        //调用sdk方法，创建支付，得到支付对象
        Map<String, Object> payment = new HashMap<>();
        try {
            payment = PaymentConfirm.create(confirmParams);
        } catch (BaseAdaPayException e) {
            e.printStackTrace();
        }
        log.info("请求Adapay分账给取现用户用于取现返回参数：" + JSONObject.toJSONString(payment));
        return payment;
    }


    public Map<String, Object> createWXPayment(JSONObject configJson, String notifyUrl, String orderNo, String goodTitle, String goodDesc, BigDecimal amount) {
        Map<String, Object> paymentParams = new HashMap<>(10);
        paymentParams.put("adapay_func_code", "wxpay.createOrder");
        paymentParams.put("app_id", appId);
        paymentParams.put("order_no", orderNo);
        paymentParams.put("pay_channel", configJson.getString("pay_channel"));
        paymentParams.put("pay_amt", NumberUtils.bigDecimaFormatString(amount));
        paymentParams.put("goods_title", goodTitle);
        paymentParams.put("goods_desc", goodDesc);
        paymentParams.put("currency", "cny");
        paymentParams.put("notify_url", notifyUrl);

        //调用sdk方法，创建支付，得到支付对象
        Map<String, Object> payment = new HashMap<>();
        try {
            payment = AdapayCommon.requestAdapayUits(paymentParams);
            redisCache.setCacheObject(orderNo, 1, 5, TimeUnit.MINUTES);
        } catch (BaseAdaPayException e) {
            e.printStackTrace();
        }
        log.info("请求Adapay微信小程序支付返回参数：" + JSONObject.toJSONString(payment));
        return payment;
    }

    public Map<String, Object> createAdminZFBPayment(String notifyUrl, String orderNo, BigDecimal amount) {
        Map<String, Object> paymentParams = new HashMap<>(10);
        paymentParams.put("app_id", appId);
        paymentParams.put("order_no", orderNo);
        paymentParams.put("pay_channel", "alipay");
        paymentParams.put("pay_amt", NumberUtils.bigDecimaFormatString(amount));
        paymentParams.put("pay_mode", "delay");
        paymentParams.put("goods_title", "内部商品");
        paymentParams.put("goods_desc", "不对外购买的内部商品");
        paymentParams.put("notify_url", notifyUrl);

        //调用sdk方法，创建支付，得到支付对象
        Map<String, Object> payment = new HashMap<>();
        try {
            payment = Payment.create(paymentParams);
            log.info("请求Adapay支付宝支付返回参数：[{}]", JSONObject.toJSONString(payment));
            if (!payment.get("status").toString().equals("succeeded")) {
                throw new ServiceException("创建支付失败,请稍后重试");
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new ServiceException(e.getMessage());
        }
        return payment;
    }


    public Map<String, Object> createAdminOnlinePayment(String memberCode, String notifyUrl, String orderNo, BigDecimal amount) {
        Map<String, Object> checkoutParams = new HashMap<String, Object>();
        checkoutParams.put("order_no", orderNo);
        checkoutParams.put("pay_channel", "fast_pay");
        checkoutParams.put("member_id", memberCode);
        checkoutParams.put("pay_amt", NumberUtils.bigDecimaFormatString(amount));
        checkoutParams.put("app_id", appId);
        checkoutParams.put("currency", "cny");
        checkoutParams.put("goods_title", "内部商品");
        checkoutParams.put("goods_desc", "不对外购买的内部商品");
        checkoutParams.put("notify_url", notifyUrl);
        checkoutParams.put("pay_mode", "delay");

        Map<String, Object> response = new HashMap<>();

        //调用sdk方法，创建支付，得到支付对象
        try {
            response = Checkout.create(checkoutParams);
            log.info("请求Adapay在线网银支付返回参数：[{}]", JSONObject.toJSONString(response));
            if (!response.get("status").toString().equals("succeeded")) {
                throw new ServiceException("创建支付失败,请稍后重试");
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new ServiceException(e.getMessage());
        }
        return response;
    }

    public Map<String, Object> createZFBPayment(JSONObject configJson, String notifyUrl, String orderNo, String goodTitle, String goodDesc, BigDecimal amount) {
        Map<String, Object> paymentParams = new HashMap<>(10);
        paymentParams.put("app_id", appId);
        paymentParams.put("order_no", orderNo);
        paymentParams.put("pay_channel", configJson.getString("pay_channel"));
        paymentParams.put("pay_amt", NumberUtils.bigDecimaFormatString(amount));
        paymentParams.put("goods_title", goodTitle);
        paymentParams.put("goods_desc", goodDesc);
        paymentParams.put("notify_url", notifyUrl);

        //调用sdk方法，创建支付，得到支付对象
        Map<String, Object> payment = new HashMap<>();
        try {
            payment = Payment.create(paymentParams);
            log.info("请求Adapay支付宝支付返回参数：[{}]", JSONObject.toJSONString(payment));
            if (!payment.get("status").toString().equals("succeeded")) {
                throw new ServiceException("创建支付失败,请稍后重试");
            }
            redisCache.setCacheObject(orderNo, 1, 5, TimeUnit.MINUTES);
        } catch (Exception e) {
            e.printStackTrace();
            throw new ServiceException(e.getMessage());
        }
        return payment;
    }

    public Map<String, Object> createFastPayment(JSONObject configJson, String memberCode, String notifyUrl, String orderNo, String goodTitle, String goodDesc, BigDecimal amount) {
        Map<String, Object> paymentParams = new HashMap<>(10);
        paymentParams.put("app_id", appId);
        paymentParams.put("order_no", orderNo);
        paymentParams.put("member_id", memberCode);
        paymentParams.put("pay_channel", configJson.getString("pay_channel"));
        paymentParams.put("pay_amt", NumberUtils.bigDecimaFormatString(amount));
        paymentParams.put("goods_title", goodTitle);
        paymentParams.put("goods_desc", goodDesc);
        paymentParams.put("notify_url", notifyUrl);

        //调用sdk方法，创建支付，得到支付对象
        Map<String, Object> payment = new HashMap<>();
        try {
            payment = Checkout.create(paymentParams);
            log.info("创建网银支付对象payment[{}]", JSONObject.toJSONString(payment));
            if (!payment.get("status").toString().equals("succeeded")) {
                throw new ServiceException("创建网银支付失败,请稍后重试");
            }
            redisCache.setCacheObject(orderNo, 1, 5, TimeUnit.MINUTES);
        } catch (Exception e) {
            e.printStackTrace();
            throw new ServiceException(e.getMessage());
        }
        return payment;
    }

    public Map<String, Object> executerCreateMember(String memberCode, String nickName) {
        Map<String, Object> memberParams = new HashMap<String, Object>(2);
        memberParams.put("member_id", memberCode);
        memberParams.put("app_id", appId);
        memberParams.put("nickname", nickName);
        log.info("创建用户，请求参数：" + JSON.toJSONString(memberParams));
        Map<String, Object> member = null;
        try {
            member = Member.create(memberParams);
            log.info("创建用户，返回参数：" + JSON.toJSONString(member));
        } catch (BaseAdaPayException e) {
            e.printStackTrace();
        }
        return member;
    }

    public Map<String, Object> executerCreateRealname(String memberCode, String telNo, String realName, String idNumber, int sex) {
        Map<String, Object> memberParams = new HashMap<String, Object>(10);
        memberParams.put("adapay_func_code", "members.realname");
        memberParams.put("member_id", memberCode);
        memberParams.put("app_id", appId);
        memberParams.put("gender", sex == 0 ? "FEMALE" : "MALE");
        memberParams.put("tel_no", telNo);
        memberParams.put("user_name", realName);
        memberParams.put("cert_type", "00");
        memberParams.put("cert_id", idNumber);
        Map<String, Object> member = null;
        try {
            member = AdapayCommon.requestAdapay(memberParams);
            log.info("实名用户认证，返回参数：" + JSON.toJSONString(member));
        } catch (BaseAdaPayException e) {
            e.printStackTrace();
        }
        return member;
    }

    public Map<String, Object> executerCardApply(long bankType, String memberCode, String telNo, String cardNo, String vipCode, String expiration) {
        Map<String, Object> applyParam = new HashMap<String, Object>();
        applyParam.put("app_id", appId);
        applyParam.put("member_id", memberCode);
        applyParam.put("card_id", cardNo);
        applyParam.put("tel_no", telNo);
        if (bankType == 1) {
            applyParam.put("vip_code", vipCode);
            applyParam.put("expiration", expiration);
        }

        Map<String, Object> member = null;
        try {
            member = FastPay.cardApply(applyParam);
            log.info("用户绑定银行卡，返回参数：" + JSON.toJSONString(member));
        } catch (BaseAdaPayException e) {
            e.printStackTrace();
        }
        return member;
    }

    public Map<String, Object> executerCreateSettleAccount(String memberCode, String cardId, String bankId, String telNo, String cardName, String cardNo) {
        Map<String, Object> settleCountParams = new HashMap<String, Object>(4);
        Map<String, Object> accountInfo = new HashMap<String, Object>(9);
        accountInfo.put("card_id", cardNo);
        accountInfo.put("card_name", cardName);
        accountInfo.put("tel_no", telNo);
        accountInfo.put("bank_acct_type", "2");
        accountInfo.put("cert_id", cardId);
        accountInfo.put("cert_type", "00");
        accountInfo.put("bank_code", bankId);

        settleCountParams.put("member_id", memberCode);
        settleCountParams.put("app_id", appId);
        settleCountParams.put("channel", "bank_account");
        settleCountParams.put("account_info", accountInfo);

        Map<String, Object> settleCount = null;
        try {
            settleCount = SettleAccount.create(settleCountParams);
            log.info("用户绑定银行卡，返回参数：" + JSON.toJSONString(settleCount));
        } catch (BaseAdaPayException e) {
            e.printStackTrace();
        }
        return settleCount;
    }

    public Map<String, Object> executerDeleteSettleAccount(String memberCode, String settleAccountId) {
        Map<String, Object> settleCountParams = new HashMap<String, Object>(2);
        settleCountParams.put("settle_account_id", settleAccountId);
        settleCountParams.put("member_id", memberCode);
        settleCountParams.put("app_id", appId);
        Map<String, Object> settleCount = null;
        try {
            settleCount = SettleAccount.delete(settleCountParams);
            log.info("删除用户结算账户，返回参数：" + JSON.toJSONString(settleCount));
        } catch (BaseAdaPayException e) {
            e.printStackTrace();
        }
        return settleCount;
    }

    public Map<String, Object> executerCash(String memberCode, String orderNo, String cashType, BigDecimal cashAmount, String notifyUrl) {
        Map<String, Object> cashParam = new HashMap<String, Object>(5);
        cashParam.put("order_no", orderNo);
        cashParam.put("app_id", appId);
        cashParam.put("cash_type", cashType);
        cashParam.put("cash_amt", NumberUtils.bigDecimaFormatString(cashAmount));
        cashParam.put("member_id", memberCode);
        cashParam.put("notify_url", notifyUrl);
        cashParam.put("fee_mode", "I"); //手续费收取模式：O-商户手续费账户扣取手续费，I-交易金额中扣取手续费；值为空时，默认值为I；

        Map<String, Object> cash = null;
        try {
            cash = Drawcash.create(cashParam);
            log.info("用户取现，返回参数：" + JSON.toJSONString(cash));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return cash;
    }

    public Map<String, Object> executerCashSettle(String memberCode, String orderNo, String cashType, BigDecimal cashAmount, String notifyUrl) {

        System.out.println("======取现开始======");
        System.out.println("===请求参数===");
        System.out.println("会员ID：" + memberCode);
        System.out.println("取现订单：" + orderNo);
        System.out.println("到账类型：" + cashType);
        System.out.println("取现金额：" + cashAmount);
        System.out.println("三方异步通知地址：" + notifyUrl);
        System.out.println("===参数结束===");

        Map<String, Object> resultMap = null;

        resultMap = this.executerCash(memberCode, orderNo, cashType, cashAmount, notifyUrl);
        if (!resultMap.get("status").toString().equals("succeeded")) {
            log.info("处理用户取现失败，处理转账失败！AdapayResp:" + JSON.toJSONString(resultMap));
        } else {
            log.info("转账成功! memberCode[" + memberCode + "]    AdapayResp[" + JSON.toJSONString(resultMap) + "]");
        }

        System.out.println("======取现结束======");
        return resultMap;
    }


    public Map<String, Object> executerBalancePay(String memberCode, String orderNo, BigDecimal amount, String desc) {
        Map<String, Object> balanceParam = new HashMap<String, Object>(4);
        balanceParam.put("app_id", appId);
        balanceParam.put("adapay_func_code", "settle_accounts.balancePay");
        balanceParam.put("order_no", orderNo);
        balanceParam.put("out_member_id", memberCode);
        balanceParam.put("in_member_id", "0");
        balanceParam.put("trans_amt", NumberUtils.bigDecimaFormatString(amount));
        balanceParam.put("goods_title", "碳积分");
        balanceParam.put("goods_desc", desc);

        Map<String, Object> paymentResult = null;
        try {
            paymentResult = AdapayCommon.requestAdapay(balanceParam);
            log.info("商户基本账户余划转给用户结提现，返回参数：" + JSON.toJSONString(paymentResult));
        } catch (BaseAdaPayException e) {
            e.printStackTrace();
        }
        return paymentResult;
    }


    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getWxAppId() {
        return wxAppId;
    }

    public void setWxAppId(String wxAppId) {
        this.wxAppId = wxAppId;
    }

    public boolean isDebug() {
        return debug;
    }

    public void setDebug(boolean debug) {
        this.debug = debug;
    }

    public boolean isProdMode() {
        return prodMode;
    }

    public void setProdMode(boolean prodMode) {
        this.prodMode = prodMode;
    }

    public String getApiKey() {
        return apiKey;
    }

    public void setApiKey(String apiKey) {
        this.apiKey = apiKey;
    }

    public String getMockApiKey() {
        return mockApiKey;
    }

    public void setMockApiKey(String mockApiKey) {
        this.mockApiKey = mockApiKey;
    }

    public String getRsaPrivateKey() {
        return rsaPrivateKey;
    }

    public void setRsaPrivateKey(String rsaPrivateKey) {
        this.rsaPrivateKey = rsaPrivateKey;
    }
}
