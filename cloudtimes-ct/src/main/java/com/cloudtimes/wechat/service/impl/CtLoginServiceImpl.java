package com.cloudtimes.wechat.service.impl;

import com.alibaba.fastjson2.JSONObject;
import com.cloudtimes.account.domain.CtUser;
import com.cloudtimes.account.mapper.CtUserMapper;
import com.cloudtimes.common.annotation.DataSource;
import com.cloudtimes.common.enums.DataSourceType;
import com.cloudtimes.common.exception.ServiceException;
import com.cloudtimes.common.utils.StringUtils;
import com.cloudtimes.common.utils.ip.IpUtils;
import com.cloudtimes.partner.weixin.ICtWeixinApiService;
import com.cloudtimes.wechat.service.ICtLoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 小程序用户登录Service业务层处理
 *
 * @author wangxp
 * @date 2023-02-02
 */
@DataSource(DataSourceType.CT)
@Service
public class CtLoginServiceImpl implements ICtLoginService {
    @Autowired
    private ICtWeixinApiService weixinApiService;

    @Autowired
    private CtUserMapper userMapper;

    @Override
    public boolean checkCustomerNew(String loginCode) {
        Map<String, Object> userSession = weixinApiService.getUserSession(loginCode);
        if (StringUtils.isNotEmpty(StringUtils.getStringFromObjectMap(userSession, "errcode"))) {
            throw new ServiceException(userSession.get("errmsg").toString());
        }
        String openId = StringUtils.getStringFromObjectMap(userSession, "openid");
        CtUser user = userMapper.selectCtUserByWxOpenId(openId);
        if (user == null) {
            return true;
        }
        return false;
    }

    @Override
    public Map<String, Object> customerLogin(String loginCode, String phoneCode, String loginIp) {
        Map<String, Object> userSession = weixinApiService.getUserSession(loginCode);
        if (StringUtils.isNotEmpty(StringUtils.getStringFromObjectMap(userSession, "errcode"))) {
            throw new ServiceException(userSession.get("errmsg").toString());
        }
        String openId = StringUtils.getStringFromObjectMap(userSession, "openid");
        String unionId = StringUtils.getStringFromObjectMap(userSession, "unionid");
        CtUser user = userMapper.selectCtUserByWxOpenId(openId);
        Map<String, Object> retMap = new HashMap<>();
        if (user == null) {
            //新用户流程,获取手机号
            Map<String, Object> userPhoneInfo = weixinApiService.getUserPhoneInfo(phoneCode);
            if (StringUtils.isNotEmpty(StringUtils.getStringFromObjectMap(userPhoneInfo, "errcode"))) {
                throw new ServiceException(userPhoneInfo.get("errmsg").toString());
            }
            String phoneInfoStr = StringUtils.getStringFromObjectMap(userPhoneInfo, "phone_info");
            Map<String, String> phoneInfo = JSONObject.parseObject(phoneInfoStr, Map.class);
            String phoneNumber = phoneInfo.get("purePhoneNumber");
            CtUser newUser = new CtUser();
            newUser.setWxOpenId(openId);
            newUser.setWxUnionId(unionId);
            newUser.setMobile(phoneNumber);
            newUser.setMoneyAmount(new BigDecimal(0));
            newUser.setScoreAmount(new BigDecimal(0));
            newUser.setCreditScore(80L);
            newUser.setViolationCount(0L);
            newUser.setIsAgent("0");
            newUser.setIsShopBoss("0");
            newUser.setLastLoginIp(loginIp);
            newUser.setLastLoginTime(new Date());
            newUser.setCustomerState("0");
            newUser.setCreateDate(new Date());
            newUser.setDelFlag("0");
            int rows = userMapper.insertCtUser(newUser);
            if (rows < 1) {
                throw new ServiceException("新增用户失败");
            }
            retMap.put("id", newUser.getId());
            retMap.put("moneyAmount", "0");
            retMap.put("scoreAmount", "0");
            retMap.put("creditScore", "80");
        } else {
            retMap.put("id", user.getId());
            retMap.put("moneyAmount", user.getMoneyAmount().toString());
            retMap.put("scoreAmount", user.getScoreAmount().toString());
            retMap.put("creditScore", user.getCreditScore().toString());
        }
        return retMap;
    }
}

