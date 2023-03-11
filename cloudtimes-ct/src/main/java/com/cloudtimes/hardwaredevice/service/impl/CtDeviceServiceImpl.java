package com.cloudtimes.hardwaredevice.service.impl;

import com.alibaba.fastjson2.JSONObject;
import com.cloudtimes.cache.CtStoreVideoCache;
import com.cloudtimes.common.NoUtils;
import com.cloudtimes.common.annotation.DataSource;
import com.cloudtimes.common.enums.DataSourceType;
import com.cloudtimes.common.enums.YesNoState;
import com.cloudtimes.common.exception.ServiceException;
import com.cloudtimes.common.utils.DateUtils;
import com.cloudtimes.enums.DeviceType;
import com.cloudtimes.enums.PayWay;
import com.cloudtimes.enums.PayeeType;
import com.cloudtimes.hardwaredevice.domain.DeviceActivateReq;
import com.cloudtimes.hardwaredevice.domain.CtDevice;
import com.cloudtimes.hardwaredevice.domain.CtPayment;
import com.cloudtimes.hardwaredevice.domain.DeviceOnlineReq;
import com.cloudtimes.hardwaredevice.mapper.CtDeviceMapper;
import com.cloudtimes.hardwaredevice.mapper.CtPaymentMapper;
import com.cloudtimes.hardwaredevice.service.ICtDeviceService;
import com.cloudtimes.partner.hik.domain.HikCodeEnum;
import com.cloudtimes.partner.hik.domain.HikCommonResp;
import com.cloudtimes.partner.hik.domain.HikConstant;
import com.cloudtimes.partner.hik.service.ICtHikApiService;
import com.cloudtimes.partner.pay.shouqianba.domain.ActivateResponse;
import com.cloudtimes.partner.pay.shouqianba.service.ICtShouqianbaApiService;
import com.cloudtimes.serving.cash.service.domain.ShouqianbaParam;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 电子设备Service业务层处理
 *
 * @author tank
 * @date 2023-01-17
 */
@DataSource(DataSourceType.CT)
@Service
@Slf4j
public class CtDeviceServiceImpl implements ICtDeviceService {
    @Autowired
    private CtDeviceMapper ctDeviceMapper;
    @Autowired
    private CtPaymentMapper paymentMapper;
    @Autowired
    private ICtShouqianbaApiService shouqianbaApiService;
    @Autowired
    private ICtHikApiService hikApiService;

    /**
     * 查询电子设备
     *
     * @param id 电子设备主键
     * @return 电子设备
     */
    @Override
    public CtDevice selectCtDeviceById(String id) {
        return ctDeviceMapper.selectCtDeviceById(id);
    }

    /**
     * 查询电子设备列表
     *
     * @param ctDevice 电子设备
     * @return 电子设备
     */
    @Override
    public List<CtDevice> selectCtDeviceList(CtDevice ctDevice) {
        return ctDeviceMapper.selectCtDeviceList(ctDevice);
    }

    /**
     * 新增电子设备
     *
     * @param ctDevice 电子设备
     * @return 结果
     */
    @Override
    public int insertCtDevice(CtDevice ctDevice) {
        ctDevice.setCreateTime(DateUtils.getNowDate());
        return ctDeviceMapper.insertCtDevice(ctDevice);
    }

    /**
     * 修改电子设备
     *
     * @param ctDevice 电子设备
     * @return 结果
     */
    @Override
    public int updateCtDevice(CtDevice ctDevice) {
        ctDevice.setUpdateTime(DateUtils.getNowDate());
        return ctDeviceMapper.updateCtDevice(ctDevice);
    }

    @Autowired
    private CtStoreVideoCache videoCache;

    /**
     * 批量删除电子设备
     *
     * @param ids 需要删除的电子设备主键
     * @return 结果
     */
    @Override
    public int deleteCtDeviceByIds(String[] ids) {
        for (String id :
                ids) {
            videoCache.deleteCacheVideo(id);
        }
        return ctDeviceMapper.deleteCtDeviceByIds(ids);
    }

    /**
     * 删除电子设备信息
     *
     * @param id 电子设备主键
     * @return 结果
     */
    @Transactional
    @Override
    public int deleteCtDeviceById(String id) {
        // 添加删除支付渠道表逻辑
        CtDevice device = ctDeviceMapper.selectCtDeviceById(id);
        if (StringUtils.equals(device.getDeviceType(), DeviceType.CASH.getCode())) {
            CtPayment ctPayment = new CtPayment();
            ctPayment.setPayeeId(id);
            ctPayment.setPayeeType(PayeeType.CASH.getCode());
            ctPayment.setPayWay(PayWay.SHOU_QIAN_BA.getCode());
            paymentMapper.deleteCtPaymentByUniqueKey(ctPayment);
        }
        return ctDeviceMapper.deleteCtDeviceById(id);
    }

    /**
     * 激活设备
     *
     * @param param 电子设备
     * @return 结果
     */
    @Override
    public int activateCtDevice(DeviceActivateReq param) {
        CtDevice device = ctDeviceMapper.selectCtDeviceById(param.getDeviceId());
        if (device == null) {
            throw new ServiceException("无法获取设备信息");
        }
        if (!StringUtils.equals(device.getDeviceType(), DeviceType.CASH.getCode())) {
            throw new ServiceException("设备类型必须是收银机");
        }
        if (StringUtils.equals(device.getIsActivated(), YesNoState.Yes.getCode())) {
            throw new ServiceException("当前收银机已激活，请勿重复激活");
        }
        String deviceNo = NoUtils.genDeviceCode();
        ActivateResponse activateResponse = shouqianbaApiService.activateTerminal(deviceNo, param.getCode());
        if (activateResponse == null) {
            throw new ServiceException("调用收钱吧激活接口失败");
        }
        log.info("activateResponse:" + activateResponse);
        String terminalSn = activateResponse.getTerminalSn();
        String terminalKey = activateResponse.getTerminalKey();

        CtPayment paymentParam = paymentMapper.selectCtPaymentByUniqueKey(param.getDeviceId(), PayeeType.CASH.getCode(), PayWay.SHOU_QIAN_BA.getCode());
        ShouqianbaParam newParam = new ShouqianbaParam();
        newParam.setDeviceNo(deviceNo);
        newParam.setTerminalSn(terminalSn);
        newParam.setTerminalKey(terminalKey);
        if (paymentParam != null) {
            //复用
            paymentParam.setPayParams(JSONObject.toJSONString(newParam));
            paymentParam.setUpdateTime(DateUtils.getNowDate());
            paymentMapper.updateCtPayment(paymentParam);
        } else {
            paymentParam = new CtPayment();
            paymentParam.setPayeeId(device.getId());
            paymentParam.setPayeeType(PayeeType.CASH.getCode());
            paymentParam.setPayWay(PayWay.SHOU_QIAN_BA.getCode());
            paymentParam.setPayParams(JSONObject.toJSONString(newParam));
            paymentParam.setPayDesc("收钱吧支付参数");
            paymentParam.setEnabled(YesNoState.Yes.getCode());
            paymentParam.setDelFlag(YesNoState.No.getCode());
            paymentParam.setCreateTime(DateUtils.getNowDate());
            paymentParam.setUpdateTime(DateUtils.getNowDate());
            paymentMapper.insertCtPayment(paymentParam);
        }
        //更新收银机激活状态
        device.setIsActivated(YesNoState.Yes.getCode());
        ctDeviceMapper.updateCtDevice(device);
        return ctDeviceMapper.updateCtDevice(device);
    }

    /**
     * 摄像设备上线
     *
     * @param param 电子设备
     * @return 结果
     */
    @Override
    public int onlineCtDevice(DeviceOnlineReq param) {
        CtDevice device = ctDeviceMapper.selectCtDeviceById(param.getDeviceId());
        if (device == null) {
            throw new ServiceException("无法获取设备信息");
        }
        if (!StringUtils.equals(device.getDeviceType(), DeviceType.CAMERA.getCode()) && !StringUtils.equals(device.getDeviceType(), DeviceType.NVR_CAMERA.getCode())) {
            throw new ServiceException("设备类型必须是摄像头或nvr摄像头");
        }
        if (StringUtils.equals(param.getOption(), YesNoState.Yes.getCode())) {
            HikCommonResp hikCommonResp = hikApiService.addDevice(device.getDeviceSerial(), device.getValidateCode());
            if (hikCommonResp == null) {
                throw new ServiceException("调用上线接口失败");
            }
            if (!StringUtils.equals(hikCommonResp.getCode(), HikCodeEnum.CODE200.getCode())
                    && !StringUtils.equals(hikCommonResp.getCode(), HikCodeEnum.CODE20017.getCode())) {
                // 已上线则忽略
                throw new ServiceException("上线失败: " + hikCommonResp.getMsg());
            }
            // 上线操作
            if (StringUtils.equals(device.getDeviceType(), DeviceType.CAMERA.getCode())) {
                //普通摄像头逻辑
                //解密设备
                hikCommonResp = hikApiService.setDeviceEncrypt(device.getDeviceSerial(), device.getValidateCode(), false);
                if (hikCommonResp == null) {
                    throw new ServiceException("调用解密接口失败");
                }
                if (!StringUtils.equals(hikCommonResp.getCode(), HikCodeEnum.CODE200.getCode())
                        && !StringUtils.equals(hikCommonResp.getCode(), HikCodeEnum.CODE60016.getCode())) {
                    // 未加密则忽略
                    throw new ServiceException("调用解密接口: " + hikCommonResp.getMsg());
                }
                device.setIsOnline(YesNoState.Yes.getCode());
                device.setLastOnlineTime(DateUtils.getNowDate());
                ctDeviceMapper.updateCtDevice(device);
            } else if (StringUtils.equals(device.getDeviceType(), DeviceType.NVR_CAMERA.getCode())) {
                CtDevice query = new CtDevice();
                query.setDeviceType(DeviceType.NVR_CAMERA.getCode());
                query.setDeviceSerial(device.getDeviceSerial());
                query.setDelFlag(YesNoState.No.getCode());
                List<CtDevice> nvrCameraList = ctDeviceMapper.selectCtDeviceList(query);
                for (CtDevice updateDevice :
                        nvrCameraList) {
                    updateDevice.setIsOnline(YesNoState.Yes.getCode());
                    updateDevice.setLastOnlineTime(DateUtils.getNowDate());
                    ctDeviceMapper.updateCtDevice(updateDevice);
                }
            }
        } else if (StringUtils.equals(param.getOption(), YesNoState.No.getCode())) {
            HikCommonResp hikCommonResp = hikApiService.deleteDevice(device.getDeviceSerial());
            if (hikCommonResp == null) {
                throw new ServiceException("调用下线接口失败");
            }
            if (!StringUtils.equals(hikCommonResp.getCode(), HikCodeEnum.CODE200.getCode())
                    && !StringUtils.equals(hikCommonResp.getCode(), HikCodeEnum.CODE20018.getCode())) {
                // 已下线忽略
                throw new ServiceException("下线失败: " + hikCommonResp.getMsg());
            }
            // 下线操作
            if (StringUtils.equals(device.getDeviceType(), DeviceType.CAMERA.getCode())) {
                //普通摄像头逻辑
                device.setIsOnline(YesNoState.No.getCode());
                device.setLastOfflineTime(DateUtils.getNowDate());
                ctDeviceMapper.updateCtDevice(device);
            } else if (StringUtils.equals(device.getDeviceType(), DeviceType.NVR_CAMERA.getCode())) {
                CtDevice query = new CtDevice();
                query.setDeviceType(DeviceType.NVR_CAMERA.getCode());
                query.setDeviceSerial(device.getDeviceSerial());
                query.setDelFlag(YesNoState.No.getCode());
                List<CtDevice> nvrCameraList = ctDeviceMapper.selectCtDeviceList(query);
                for (CtDevice updateDevice :
                        nvrCameraList) {
                    updateDevice.setIsOnline(YesNoState.No.getCode());
                    updateDevice.setLastOfflineTime(DateUtils.getNowDate());
                    ctDeviceMapper.updateCtDevice(updateDevice);
                }
            }
        }
        return 0;
    }
}
