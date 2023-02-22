package com.cloudtimes.partner.pay.shouqianba.service.impl;

import com.alibaba.fastjson2.JSONObject;
import com.cloudtimes.common.utils.JacksonUtils;
import com.cloudtimes.common.utils.http.HttpUtils;
import com.cloudtimes.common.utils.sign.Md5Utils;
import com.cloudtimes.partner.config.PartnerConfig;
import com.cloudtimes.partner.pay.shouqianba.domain.*;
import com.cloudtimes.partner.pay.shouqianba.service.ICtShouqianbaApiService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class CtShouqianbaApiServiceImpl implements ICtShouqianbaApiService {
    @Autowired
    private PartnerConfig config;

    private Logger log = LoggerFactory.getLogger(CtShouqianbaApiServiceImpl.class);

    /**
     * 设备终端激活
     *
     * @param deviceNo 设备号 同一个app_id下唯一
     * @param code     激活码
     * @return map
     * result_code String  返回码
     * error_code  String  错误码
     * error_message   String  错误信息
     * biz_response    map
     * biz_response.terminal_sn 终端sn
     * biz_response.terminal_key    终端key
     */
    @Override
    public BuzResponse activateTerminal(String deviceNo, String code) {
        String appId = config.getShouqianbaAppId();
        String sn = config.getShouqianbaVendorSn();
        String key = config.getShouqianbaVendorKey();
        JSONObject reqObj = new JSONObject();
        reqObj.put("app_id", appId);
        reqObj.put("device_id", deviceNo);
        reqObj.put("code", code);
        String resultStr = sendShouqianbaHttp(ShouqianbaConstant.activateTerminal, reqObj, sn, key);
        log.info(Thread.currentThread().getStackTrace()[1].getMethodName() + ": " + resultStr);
        return getBuzResponse(resultStr);
    }

    /**
     * 设备签到
     *
     * @param deviceNo    设备号 同一个app_id下唯一
     * @param terminalSN  终端sn
     * @param terminalKey 终端key
     * @return
     */
    @Override
    public BuzResponse checkinTerminal(String deviceNo, String terminalSN, String terminalKey) {
        JSONObject reqObj = new JSONObject();
        reqObj.put("terminal_sn", terminalSN);
        reqObj.put("device_id", deviceNo);
        String resultStr = sendShouqianbaHttp(ShouqianbaConstant.checkinTerminal, reqObj, terminalSN, terminalKey);
        log.info(Thread.currentThread().getStackTrace()[1].getMethodName() + ": " + resultStr);
        return getBuzResponse(resultStr);
    }

    /**
     * 支付接口(支持分账)
     *
     * @param params      map     输入参数
     *                    * "terminal_sn"  String //收钱吧终端ID	收钱吧终端ID，不超过32位的纯数字
     *                    * "client_sn"    String //商户系统订单号	必须在商户系统内唯一；且长度不超过32字节
     *                    * "total_amount" String //交易总金额	以分为单位,不超过10位纯数字字符串,超过1亿元的收款请使用银行转账
     *                    * "dynamic_id"   String //条码内容	不超过32字节
     *                    * "subject"      String //交易简介	本次交易的简要介绍
     *                    * "operator"     String //门店操作员	发起本次交易的操作员
     *                    * "notify_url"   String //回调	支付回调的地址
     *                    * "profit_sharing" Map   分账信息
     *                    * * "sharing_flag" String  //分账标识 0: 不分账 1：分账
     *                    * * "sharing_type" String  // 1: 按比例分 3: 按金额分
     *                    * * "model_id"     String  //分账模型编号
     *                    * * "sharing_notify_url"   String  //分账回调地址
     *                    * * "receivers" List Map   分账收款方
     *                    * * * id	String              // 收款方编号	string	N	id和client_sn二选一，不能同时为空
     *                    * * * client_sn   String      // 自定义收款方编号	string	N	id和client_sn二选一，不能同时为空
     *                    * * * ratio   String          // 分账比例%。最小 0.001%，最大100%	string	条件必填	sharing_type为1时必填。例：0.003%
     *                    * * * sharing_amount  String  // 分账金额，单位为分	string	条件必填	sharing_type为3时必填。例：20（即0.2元）
     * @param terminalKey
     * @return map
     * * result_code String  返回码
     * * error_code  String  错误码
     * * error_message   String  错误信息
     * * biz_response   map 业务返回
     * * * result_code String  返回码
     * * * error_code  String  错误码
     * * * error_message   String  错误信息
     * * * error_code_standard   String  特殊返回码
     * * * data   map  订单信息
     * * * * "sn"                  // Y 收钱吧系统内部唯一订单号         "7892259488292938"
     * * * * "client_sn"           // Y 商户系统订单号                  "7654321132"
     * * * * "trade_no"            // N 支付通道交易凭证号，只有支付成功时才有值返回  "2013112011001004330000121536"
     * * * * "status"              // Y 本次操作产生的流水的状态       "SUCCESS"
     * * * * "order_status"        // Y 当前订单状态                 "PAID"
     * * * * "payway"              // Y 一级支付方式，取值见附录《支付方式列表》  "1"
     * * * * "payway_name"         // Y 支付方式名称
     * * * * "sub_payway"          // Y 二级支付方式，取值见附录《二级支付方式列表》  "1"
     * * * * "payer_uid"           // N 支付平台（微信，支付宝）上的付款人ID   "2801003920293239230239"
     * * * * "payer_login"         // N 支付平台上(微信，支付宝)的付款人账号	支付宝    "134**3920"
     * * * * "total_amount"        // Y 本次交易总金额       "10000"
     * * * * "net_amount"          // Y 如果没有退款，这个字段等于total_amount。否则等于total_amount减去退款金额    "0"
     * * * * "settlement_amount"   // Y 本次支付金额   "10000"
     * * * * "subject"             // Y 本次交易概述   "Pizza"
     * * * * "finish_time"         // N 时间戳，只有order_status为最终状态时才会返回   "1449646835244"
     * * * * "channel_finish_time" // N 时间戳，只有支付成功时才有值返回    "1449646835244"
     * * * * "operator"            // Y 门店操作员    "张三丰"
     * * * * "reflect"             // N 透传参数    {"tips": "200"}
     */
    @Override
    public CommonResp b2cPay(B2CPayReq params, String terminalKey) {
        String terminalSN = params.getTerminalSN();
        if ("".equals(terminalSN)) {
            throw new RuntimeException("终端sn不能为空");
        }
        String resultStr = sendShouqianbaHttp(ShouqianbaConstant.b2CPay, JSONObject.parseObject(JacksonUtils.toString(params)), terminalSN, terminalKey);
        log.info(Thread.currentThread().getStackTrace()[1].getMethodName() + ": " + resultStr);
        return getCommonResp(resultStr);
    }

    /**
     * 查询支付订单信息接口
     *
     * @param paySn       收钱吧系统订单号	必须在商户系统内唯一；且长度不超过32字节
     * @param billSerial  商户系统订单号	必须在商户系统内唯一；且长度不超过32字节
     * @param terminalSN  收钱吧终端sn
     * @param terminalKey 收钱吧终端key
     * @return map
     * * result_code String  返回码
     * * error_code  String  错误码
     * * error_message   String  错误信息
     * * biz_response   map 业务返回
     * * * result_code String  返回码
     * * * error_code  String  错误码
     * * * error_message   String  错误信息
     * * * data   map  订单信息 同上
     */
    @Override
    public PayOrderData queryPayOrder(String paySn, String billSerial, String terminalSN, String terminalKey) {
        JSONObject reqObj = new JSONObject();
        //sn与client_sn不能同时为空，优先按照sn查找订单，如果没有，再按照client_sn查询
        reqObj.put("terminal_sn", terminalSN);
        reqObj.put("sn", paySn);
        reqObj.put("client_sn", billSerial);
        String resultStr = sendShouqianbaHttp(ShouqianbaConstant.queryPayOrder, reqObj, terminalSN, terminalKey);
        log.info(Thread.currentThread().getStackTrace()[1].getMethodName() + ": " + resultStr);
        return getData(resultStr, PayOrderData.class);
    }

    /**
     * 撤单接口
     *
     * @param paySn
     * @param billSerial
     * @param terminalSN
     * @param terminalKey
     * @return map
     * * result_code String  返回码
     * * error_code  String  错误码
     * * error_message   String  错误信息
     * * biz_response   map 业务返回
     * * * result_code String  返回码
     * * * error_code  String  错误码
     * * * error_message   String  错误信息
     * * * data   map  订单信息 同上
     */
    @Override
    public BuzResponse cancelPayOrder(String paySn, String billSerial, String terminalSN, String terminalKey) {
        JSONObject reqObj = new JSONObject();
        //sn与client_sn不能同时为空，优先按照sn查找订单，如果没有，再按照client_sn查询
        reqObj.put("terminal_sn", terminalSN);
        reqObj.put("sn", paySn);
        reqObj.put("client_sn", billSerial);
        String resultStr = sendShouqianbaHttp(ShouqianbaConstant.cancelPayOrder, reqObj, terminalSN, terminalKey);
        log.info(Thread.currentThread().getStackTrace()[1].getMethodName() + ": " + resultStr);
        return getBuzResponse(resultStr);
    }

    /**
     * 获取调用凭证接口
     *
     * @param rawData
     * @param terminalSN
     * @param terminalKey
     * @return
     */
    @Override
    public AuthInfoData getWxPayFaceAuthInfo(String rawData, String terminalSN, String terminalKey) {
        JSONObject reqObj = new JSONObject();
        //sn与client_sn不能同时为空，优先按照sn查找订单，如果没有，再按照client_sn查询
        reqObj.put("terminal_sn", terminalSN);
        reqObj.put("rawdata", rawData);
        String resultStr = sendShouqianbaHttp(ShouqianbaConstant.getWxpayFaceAuthinfo, reqObj, terminalSN, terminalKey);
        log.info(Thread.currentThread().getStackTrace()[1].getMethodName() + ": " + resultStr);
        return getData(resultStr, AuthInfoData.class);
    }

    private String sendShouqianbaHttp(String path, JSONObject bodyJson, String sn, String key) {
        String bodyStr = bodyJson.toString();
        String sign = Md5Utils.hash(bodyStr + key);
        Map<String, String> header = new HashMap<>();
        header.put("Authorization", sn + " " + sign);
        header.put("Content-Type", "application/json; charset=utf-8");
        try {
            return HttpUtils.sendJsonPost(ShouqianbaConstant.apiHost + path, bodyStr, header, 60000, 90000);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    /**
     * @param rawString
     * @return error
     * buzResponse
     */
    private CommonResp getCommonResp(String rawString) {
        return JacksonUtils.parseObject(rawString, CommonResp.class);
    }

    /**
     * @param rawString
     * @return error
     * buzResponse
     */
    private BuzResponse getBuzResponse(String rawString) {
        CommonResp commonResp = JacksonUtils.parseObject(rawString, CommonResp.class);
        if (commonResp != null) {
            return commonResp.getBizResponse();
        }
        return null;
    }


    private <T> T getData(String rawString, Class<T> tClass) {
        CommonResp commonResp = JacksonUtils.parseObject(rawString, CommonResp.class);
        if (commonResp != null) {
            BuzResponse bizResponse = commonResp.getBizResponse();
            if (bizResponse != null) {
                Object data = bizResponse.getData();
                if (data != null) {
                    return JacksonUtils.convertObject(bizResponse.getData(), tClass);
                }
            }
        }
        return null;
    }
}
