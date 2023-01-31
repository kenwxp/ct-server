package com.cloudtimes.partner.pay.shouqianba.service.impl;

import com.alibaba.fastjson2.JSONObject;
import com.cloudtimes.common.utils.http.HttpUtils;
import com.cloudtimes.common.utils.sign.Md5Utils;
import com.cloudtimes.partner.config.PartnerConfig;
import com.cloudtimes.partner.pay.shouqianba.domain.ShouqianbaConstant;
import com.cloudtimes.partner.pay.shouqianba.service.ICtShouqianbaApiService;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class CtShouqianbaApiService implements ICtShouqianbaApiService {
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
    public Map<String, Object> activateTerminal(String deviceNo, String code) {
        String appId = PartnerConfig.getShouqianbaConfig().get("app_id");
        String sn = PartnerConfig.getShouqianbaConfig().get("vendor_sn");
        String key = PartnerConfig.getShouqianbaConfig().get("vendor_key");
        JSONObject reqObj = new JSONObject();
        reqObj.put("app_id", appId);
        reqObj.put("device_id", deviceNo);
        reqObj.put("code", code);
        String resultStr = sendShouqianbaHttp(ShouqianbaConstant.activateTerminal, reqObj, sn, key);
        Map<String, Object> resultMap = JSONObject.parseObject(resultStr, Map.class);
        return resultMap;
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
    public Map<String, Object> checkinTerminal(String deviceNo, String terminalSN, String terminalKey) {
        JSONObject reqObj = new JSONObject();
        reqObj.put("terminal_sn", terminalSN);
        reqObj.put("device_id", deviceNo);
        String resultStr = sendShouqianbaHttp(ShouqianbaConstant.checkinTerminal, reqObj, terminalSN, terminalKey);
        Map<String, Object> resultMap = JSONObject.parseObject(resultStr, Map.class);
        return resultMap;
    }

    /**
     * 支付接口
     *
     * @param params map     输入参数
     *               "terminal_sn"  //收钱吧终端ID	收钱吧终端ID，不超过32位的纯数字
     *               "client_sn"    //商户系统订单号	必须在商户系统内唯一；且长度不超过32字节
     *               "total_amount" //交易总金额	以分为单位,不超过10位纯数字字符串,超过1亿元的收款请使用银行转账
     *               "dynamic_id"   //条码内容	不超过32字节
     *               "subject"      //交易简介	本次交易的简要介绍
     *               "operator"     //门店操作员	发起本次交易的操作员
     *               "notify_url"   //回调	支付回调的地址
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
    public Map<String, Object> b2cPay(Map<String, String> params, String terminalKey) {
        JSONObject reqObj = new JSONObject(params);
        String terminalSN = reqObj.getString("terminal_sn");
        if ("".equals(terminalSN)) {
            throw new RuntimeException("终端sn不能为空");
        }
        String resultStr = sendShouqianbaHttp(ShouqianbaConstant.b2CPay, reqObj, terminalSN, terminalKey);
        Map<String, Object> resultMap = JSONObject.parseObject(resultStr, Map.class);
        return resultMap;
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
    public Map<String, Object> queryPayOrder(String paySn, String billSerial, String terminalSN, String terminalKey) {
        JSONObject reqObj = new JSONObject();
        //sn与client_sn不能同时为空，优先按照sn查找订单，如果没有，再按照client_sn查询
        reqObj.put("terminal_sn", terminalSN);
        reqObj.put("sn", paySn);
        reqObj.put("client_sn", billSerial);
        String resultStr = sendShouqianbaHttp(ShouqianbaConstant.queryPayOrder, reqObj, terminalSN, terminalKey);
        Map<String, Object> resultMap = JSONObject.parseObject(resultStr, Map.class);
        return resultMap;
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
    public Map<String, Object> cancelPayOrder(String paySn, String billSerial, String terminalSN, String terminalKey) {
        JSONObject reqObj = new JSONObject();
        //sn与client_sn不能同时为空，优先按照sn查找订单，如果没有，再按照client_sn查询
        reqObj.put("terminal_sn", terminalSN);
        reqObj.put("sn", paySn);
        reqObj.put("client_sn", billSerial);
        String resultStr = sendShouqianbaHttp(ShouqianbaConstant.cancelPayOrder, reqObj, terminalSN, terminalKey);
        Map<String, Object> resultMap = JSONObject.parseObject(resultStr, Map.class);
        return resultMap;
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
}
