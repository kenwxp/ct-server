package com.cloudtimes.partner.wiegand.impl;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONArray;
import com.alibaba.fastjson2.JSONObject;
import com.cloudtimes.common.utils.http.HttpUtils;
import com.cloudtimes.partner.config.PartnerConfig;
import com.cloudtimes.partner.hik.domain.HikConstant;
import com.cloudtimes.partner.wiegand.ICtWiegandApiService;

import java.util.HashMap;
import java.util.Map;

public class CtWiegandApiService implements ICtWiegandApiService {

    /**
     * 远程开门
     *
     * @param deviceSerial
     * @return
     */
    @Override
    public boolean remoteOpenDoor(int deviceSerial) {
        JSONObject reqObj = new JSONObject();
        reqObj.put("jsonrpc", "2.0");
        reqObj.put("method", "远程开门");
        reqObj.put("id", 1001);
        JSONArray paramArray = new JSONArray();
        JSONObject paramObj = new JSONObject();
        paramObj.put("设备序列号", deviceSerial);
        paramObj.put("门号", 1);
        paramArray.add(paramObj);
        reqObj.put("params", paramArray);
        return sendWiegandHttp(reqObj);
    }

    /**
     * 设置门禁密码
     *
     * @param deviceSerial 设备序列号
     * @param password     门禁号
     * @param once         单次有效
     * @param beginTime    生效时间 格式 yyyy-MM-dd hh:mm:ss
     * @param endTime      失效时间 格式 yyyy-MM-dd hh:mm:ss
     * @return
     */
    @Override
    public boolean settingAccess(int deviceSerial, int password, boolean once, String beginTime, String endTime) {
        JSONObject reqObj = new JSONObject();
        reqObj.put("jsonrpc", "2.0");
        reqObj.put("method", "权限添加或修改");
        reqObj.put("id", 4001);
        JSONArray paramArray = new JSONArray();
        JSONObject paramObj = new JSONObject();
        paramObj.put("设备序列号", deviceSerial);
        JSONArray accessArray = new JSONArray();
        JSONObject accessObj = new JSONObject();
        accessObj.put("1号门控制时段", 1);
        accessObj.put("2号门控制时段", 1);
        accessObj.put("3号门控制时段", 1);
        accessObj.put("4号门控制时段", 1);
        accessObj.put("起始日期时间", beginTime);
        accessObj.put("截止日期时间", endTime);
        if (once) {
            accessObj.put("只许一次有效", "启用");
        }
        JSONArray cardNoArray = new JSONArray();
        cardNoArray.add(password);
        accessObj.put("卡号", cardNoArray);
        accessArray.add(accessObj);
        paramObj.put("权限", accessArray);
        paramArray.add(paramObj);
        reqObj.put("params", paramArray);
        return sendWiegandHttp(reqObj);
    }

    /**
     * 删除门禁密码
     *
     * @param deviceSerial 设备序列号
     * @param password     密码
     * @return
     */
    @Override
    public boolean deleteAccess(int deviceSerial, int password) {
        JSONObject reqObj = new JSONObject();
        reqObj.put("jsonrpc", "2.0");
        reqObj.put("method", "权限删除");
        reqObj.put("id", 4002);
        JSONArray paramArray = new JSONArray();
        JSONObject paramObj = new JSONObject();
        paramObj.put("设备序列号", deviceSerial);
        JSONArray accessArray = new JSONArray();
        JSONObject accessObj = new JSONObject();
        JSONArray cardNoArray = new JSONArray();
        cardNoArray.add(password);
        accessObj.put("卡号", cardNoArray);
        accessArray.add(accessObj);
        paramObj.put("权限", accessArray);
        paramArray.add(paramObj);
        reqObj.put("params", paramArray);
        return sendWiegandHttp(reqObj);
    }

    /**
     * 设置门禁参数
     *
     * @param deviceSerial 设备序列号
     * @param mode         控制方式 0-在线 1-常开 2-常闭
     * @param delaySec     开门延时(秒) 默认3秒, 可设置25秒
     * @return boolean 是否成功
     */
    @Override
    public boolean settingParams(int deviceSerial, int mode, int delaySec) {
        JSONObject reqObj = new JSONObject();
        reqObj.put("jsonrpc", "2.0");
        reqObj.put("method", "设置门控制参数");
        reqObj.put("id", 1004);
        JSONArray paramArray = new JSONArray();
        JSONObject paramObj = new JSONObject();
        paramObj.put("设备序列号", deviceSerial);
        paramObj.put("门号", 1);
        if (mode == 0) {
            paramObj.put("控制方式", "在线");
        } else if (mode == 1) {
            paramObj.put("控制方式", "常开");
        } else if (mode == 2) {
            paramObj.put("控制方式", "常闭");
        }
        paramObj.put("开门延时(秒)", delaySec);
        paramArray.add(paramObj);
        reqObj.put("params", paramArray);
        return sendWiegandHttp(reqObj);
    }

    /**
     * 启用6位临时密码功能
     *
     * @param deviceSerial 设备序列号
     * @param enable       是否开启
     * @return
     */
    @Override
    public boolean enablePassword(int deviceSerial, boolean enable) {
        JSONObject reqObj = new JSONObject();
        reqObj.put("jsonrpc", "2.0");
        reqObj.put("method", "功能设置");
        reqObj.put("id", 1004);
        JSONArray paramArray = new JSONArray();
        JSONObject paramObj = new JSONObject();
        paramObj.put("设备序列号", deviceSerial);
        if (enable) {
            paramObj.put("输入6位临时密码作卡号", "启用");
        } else {
            paramObj.put("输入6位临时密码作卡号", "禁用");
        }
        paramArray.add(paramObj);
        reqObj.put("params", paramArray);
        return sendWiegandHttp(reqObj);
    }

    private boolean sendWiegandHttp(JSONObject params) {
        Map<String, String> headerMap = new HashMap<>();
        headerMap.put("Accept", "application/json");
        headerMap.put("Content-Type", "application/json");
        String result = "";
        try {
            result = HttpUtils.sendJsonPost("http://" + PartnerConfig.getWiegandConfig().get("http_host"), params.toString(), headerMap);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (!"".equals(result)) {
            JSONObject resultObj = JSON.parseObject(result);
            if (resultObj != null) {
                return resultObj.getBooleanValue("success", false);
            }
        }
        return false;
    }
}
