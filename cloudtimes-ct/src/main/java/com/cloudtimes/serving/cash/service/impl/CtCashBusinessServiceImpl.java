package com.cloudtimes.serving.cash.service.impl;

import com.cloudtimes.common.exception.ServiceException;
import com.cloudtimes.hardwaredevice.domain.CtDevice;
import com.cloudtimes.hardwaredevice.domain.CtDeviceCash;
import com.cloudtimes.hardwaredevice.domain.CtStore;
import com.cloudtimes.hardwaredevice.mapper.CtDeviceCashMapper;
import com.cloudtimes.hardwaredevice.mapper.CtDeviceMapper;
import com.cloudtimes.hardwaredevice.mapper.CtStoreMapper;
import com.cloudtimes.partner.pay.shouqianba.domain.AuthInfoData;
import com.cloudtimes.partner.pay.shouqianba.domain.BuzResponse;
import com.cloudtimes.partner.pay.shouqianba.service.ICtShouqianbaApiService;
import com.cloudtimes.partner.weixin.ICtWeixinFaceApiService;
import com.cloudtimes.partner.weixin.domain.WxpayfaceAuthInfoResp;
import com.cloudtimes.product.domain.CtShopProduct;
import com.cloudtimes.product.mapper.CtShopProductMapper;
import com.cloudtimes.serving.cash.service.ICtCashBusinessService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class CtCashBusinessServiceImpl implements ICtCashBusinessService {
    @Autowired
    private ICtWeixinFaceApiService weixinFaceApiService;
    @Autowired
    private ICtShouqianbaApiService shouqianbaApiService;
    @Autowired
    private CtDeviceMapper deviceMapper;
    @Autowired
    private CtStoreMapper storeMapper;
    @Autowired
    private CtDeviceCashMapper deviceCashMapper;
    @Autowired
    private CtShopProductMapper shopProductMapper;

    /**
     * 获取刷脸凭证
     *
     * @param deviceId 设备id
     * @param rawdata  rawdata
     * @param authType 凭证类型 1-微信 2-收钱吧
     * @return
     */
    @Override
    public AuthInfoData getFaceAuthInfo(String deviceId, String rawdata, String authType) {
        CtDevice dbDevice = deviceMapper.selectCtDeviceById(deviceId);
        if (dbDevice == null) {
            throw new ServiceException("无法获取设备信息");
        }
        if (StringUtils.isEmpty(dbDevice.getStoreId())) {
            throw new ServiceException("当前设备未绑定门店");
        }
        CtStore dbStore = storeMapper.selectCtStoreById(dbDevice.getStoreId());
        if (dbDevice == null) {
            throw new ServiceException("无法获取门店信息");
        }
        if (StringUtils.equals(authType, "1")) {
            WxpayfaceAuthInfoResp resp = weixinFaceApiService.getWxpayfaceAuthInfo(dbStore.getStoreNo(), dbStore.getName(), dbDevice.getDeviceCode(), rawdata);
            if (resp == null) {
                throw new ServiceException("获取刷脸信息失败");
            }
            if (StringUtils.equals("SUCCESS", resp.getReturnCode())) {
                AuthInfoData authInfoData = new AuthInfoData();
                authInfoData.setAppid(resp.getAppid());
                authInfoData.setSubAppid(resp.getSubAppid());
                authInfoData.setMchId(resp.getMchId());
                authInfoData.setSubMchId(resp.getSubMchId());
                authInfoData.setExpiresIn(resp.getExpiresIn());
                authInfoData.setAuthinfo(resp.getAuthinfo());
                return authInfoData;
            } else {
                throw new ServiceException(resp.getReturnMsg());
            }
        } else {
            CtDeviceCash dbDeviceCash = deviceCashMapper.selectCtDeviceCashById(deviceId);
            if (dbDeviceCash == null) {
                throw new ServiceException("无法获取设备收钱吧参数");
            }
            AuthInfoData wxPayFaceAuthInfo = shouqianbaApiService.getWxPayFaceAuthInfo(rawdata, dbDeviceCash.getTerminalSn(), dbDeviceCash.getTerminalKey());
            return wxPayFaceAuthInfo;
        }
    }

    /**
     * 获取单号和顾客信息
     *
     * @param deviceId
     * @param token
     * @return map
     * orderId
     * phone
     */
    @Override
    public Map<String, String> getOrderId(String deviceId, String token) {
        return null;
    }

    @Override
    public List<CtShopProduct> getProductList(String deviceId) {
        CtDevice dbDevice = deviceMapper.selectCtDeviceById(deviceId);
        if (dbDevice == null) {
            throw new ServiceException("无法获取设备信息");
        }
        if (StringUtils.isEmpty(dbDevice.getStoreId())) {
            throw new ServiceException("当前设备未绑定门店");
        }
        CtStore ctStore = storeMapper.selectCtStoreById(dbDevice.getStoreId());
        if (ctStore == null) {
            throw new ServiceException("无法获取门店信息");
        }
        CtShopProduct ctShopProduct = new CtShopProduct();
        ctShopProduct.setShopNo(ctStore.getStoreNo());
        return shopProductMapper.selectCtShopProductList(ctShopProduct);
    }


}
