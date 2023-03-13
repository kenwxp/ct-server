package com.cloudtimes.business.wechat.service.impl;

import com.cloudtimes.account.domain.CtUser;
import com.cloudtimes.account.domain.CtUserAssets;
import com.cloudtimes.account.mapper.CtUserAssetsMapper;
import com.cloudtimes.account.mapper.CtUserMapper;
import com.cloudtimes.common.annotation.DataSource;
import com.cloudtimes.common.enums.DataSourceType;
import com.cloudtimes.common.exception.ServiceException;
import com.cloudtimes.common.utils.StringUtils;
import com.cloudtimes.partner.weixin.ICtWeixinApiService;
import com.cloudtimes.business.wechat.domain.MpLoginCheckResp;
import com.cloudtimes.business.wechat.domain.MpLoginReq;
import com.cloudtimes.business.wechat.domain.MpLoginResp;
import com.cloudtimes.business.wechat.service.ICtCustomerLoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Map;

/**
 * 小程序用户登录Service业务层处理
 *
 * @author wangxp
 * @date 2023-02-02
 */
@DataSource(DataSourceType.CT)
@Service
public class CtCustomerLoginServiceImpl implements ICtCustomerLoginService {
    @Autowired
    private ICtWeixinApiService weixinApiService;

    @Autowired
    private CtUserMapper userMapper;
    @Autowired
    private CtUserAssetsMapper userAssetsMapper;

    @Override
    public MpLoginCheckResp checkCustomerNew(String loginCode) {
        Map<String, Object> userSession = weixinApiService.getUserSession(loginCode);
        if (StringUtils.isNotEmpty(StringUtils.getStringFromObjectMap(userSession, "errcode"))) {
            throw new ServiceException(userSession.get("errmsg").toString());
        }
        String openId = StringUtils.getStringFromObjectMap(userSession, "openid");
        CtUser user = userMapper.selectCtUserByWxOpenId(openId);
        MpLoginCheckResp loginCheckResp = new MpLoginCheckResp();
        loginCheckResp.setIsNew("0");
        if (user == null) {
            loginCheckResp.setIsNew("1");
        }
        return loginCheckResp;
    }

    @Override
    @Transactional
    public MpLoginResp customerLogin(MpLoginReq param, String loginIp) {
        String loginCode = param.getLoginCode();
        String phoneCode = param.getPhoneCode();
        Map<String, Object> userSession = weixinApiService.getUserSession(loginCode);
        if (StringUtils.isNotEmpty(StringUtils.getStringFromObjectMap(userSession, "errcode"))) {
            throw new ServiceException(userSession.get("errmsg").toString());
        }
        String openId = StringUtils.getStringFromObjectMap(userSession, "openid");
        String unionId = StringUtils.getStringFromObjectMap(userSession, "unionid");
        CtUser wxUser = userMapper.selectCtUserByWxOpenId(openId);
        MpLoginResp loginResp = new MpLoginResp();
        if (wxUser == null) {
            //新用户流程,获取手机号
            Map<String, Object> userPhoneInfo = weixinApiService.getUserPhoneInfo(phoneCode);
            String errCode = StringUtils.getStringFromObjectMap(userPhoneInfo, "errcode");
            if (StringUtils.isNotEmpty(errCode) && !"0".equals(errCode)) {
                throw new ServiceException(userPhoneInfo.get("errmsg").toString());
            }
            Map<String, String> phoneInfo = (Map<String, String>) userPhoneInfo.get("phone_info");
            String phoneNumber = phoneInfo.get("purePhoneNumber");
            // 校验店家端用户是否存在
            CtUser userByMobile = userMapper.selectCtUserByMobile(phoneNumber);
            // 校验代理端用户是否存在
            CtUser userByUnionId = userMapper.selectCtUserByWxUnionId(unionId);
            CtUser dbUser = null;
            if (userByMobile != null) {
                dbUser = userByMobile;
                dbUser.setWxOpenId(openId);
                dbUser.setWxUnionId(unionId);
            } else {
                if (userByUnionId != null){
                    dbUser = userByUnionId;
                    dbUser.setWxOpenId(openId);
                }
            }
            if (dbUser == null) {
                CtUser newUser = new CtUser();
                newUser.setWxOpenId(openId);
                newUser.setWxUnionId(unionId);
                newUser.setMobile(phoneNumber);
                newUser.setViolationCount(0L);
                newUser.setIsAgent("0");
                newUser.setIsShopBoss("0");
                newUser.setLastLoginIp(loginIp);
                newUser.setLastLoginTime(new Date());
                newUser.setCustomerState("0");
                newUser.setCreateDate(new Date());
                newUser.setDelFlag("0");
                if (userMapper.insertCtUser(newUser) < 1) {
                    throw new ServiceException("新增用户失败");
                }
                // 新增用户资产
                CtUserAssets newUserAssets = new CtUserAssets();
                newUserAssets.setUserId(newUser.getId());
                newUserAssets.setCustomerCashAmount(BigDecimal.valueOf(0));
                newUserAssets.setCustomerTotalWithdrawal(BigDecimal.valueOf(0));
                newUserAssets.setCustomerScorePoints(BigDecimal.valueOf(0));
                newUserAssets.setCustomerCreditScore(80L);
                if (userAssetsMapper.insertCtUserAssets(newUserAssets) < 1) {
                    throw new ServiceException("新增用户资产失败");
                }
                loginResp.setUserId(newUser.getId());
            } else {
                //此流程为，当前unionId在本系统已有其它身份，现在增加顾客身份
//                dbUser.setId(dbUser.getId());
//                dbUser.setWxOpenId(openId);
//                dbUser.setWxUnionId(unionId);
//                dbUser.setMobile(phoneNumber);
                dbUser.setCustomerState("0");
                if (userMapper.updateCtUser(dbUser) < 1) {
                    throw new ServiceException("更新用户失败");
                }
                //获取用户资产表信息并更新customer 部分的参数
                CtUserAssets dbUserAssets = userAssetsMapper.selectCtUserAssetsById(dbUser.getId());
                if (dbUserAssets == null) {
                    dbUserAssets = new CtUserAssets();
                    dbUserAssets.setUserId(dbUser.getId());
                    dbUserAssets.setCustomerCashAmount(BigDecimal.valueOf(0));
                    dbUserAssets.setCustomerTotalWithdrawal(BigDecimal.valueOf(0));
                    dbUserAssets.setCustomerScorePoints(BigDecimal.valueOf(0));
                    dbUserAssets.setCustomerCreditScore(80L);
                    if (userAssetsMapper.insertCtUserAssets(dbUserAssets) < 1) {
                        throw new ServiceException("新增用户资产失败");
                    }
                }
                loginResp.setUserId(dbUser.getId());
            }
            return loginResp;
        } else {
            if ("2".equals(wxUser.getCustomerState())) {
                throw new ServiceException("当前用户已被禁用");
            }
            loginResp.setUserId(wxUser.getId());
            return loginResp;
        }
    }
}

