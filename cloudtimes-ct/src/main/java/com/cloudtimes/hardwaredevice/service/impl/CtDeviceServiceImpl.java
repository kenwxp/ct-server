package com.cloudtimes.hardwaredevice.service.impl;

import java.util.List;
import java.util.Map;

import com.alibaba.fastjson2.JSONObject;
import com.cloudtimes.common.exception.ServiceException;
import com.cloudtimes.common.utils.DateUtils;
import com.cloudtimes.common.utils.JacksonUtils;
import com.cloudtimes.common.utils.StringUtils;
import com.cloudtimes.enums.PayWay;
import com.cloudtimes.enums.PayeeType;
import com.cloudtimes.hardwaredevice.domain.ActivateDeviceReq;
import com.cloudtimes.hardwaredevice.domain.CtPayment;
import com.cloudtimes.hardwaredevice.mapper.CtPaymentMapper;
import com.cloudtimes.partner.pay.shouqianba.domain.BuzResponse;
import com.cloudtimes.partner.pay.shouqianba.domain.ShouqianbaConstant;
import com.cloudtimes.partner.pay.shouqianba.service.ICtShouqianbaApiService;
import com.cloudtimes.serving.cash.service.domain.ShouqianbaParam;
import com.ctc.wstx.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.cloudtimes.common.annotation.DataSource;
import com.cloudtimes.common.enums.DataSourceType;
import com.cloudtimes.hardwaredevice.mapper.CtDeviceMapper;
import com.cloudtimes.hardwaredevice.domain.CtDevice;
import com.cloudtimes.hardwaredevice.service.ICtDeviceService;

/**
 * 电子设备Service业务层处理
 *
 * @author tank
 * @date 2023-01-17
 */
@DataSource(DataSourceType.CT)
@Service
public class CtDeviceServiceImpl implements ICtDeviceService {
    @Autowired
    private CtDeviceMapper ctDeviceMapper;
    @Autowired
    private CtPaymentMapper paymentMapper;
    @Autowired
    private ICtShouqianbaApiService shouqianbaApiService;

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

    /**
     * 批量删除电子设备
     *
     * @param ids 需要删除的电子设备主键
     * @return 结果
     */
    @Override
    public int deleteCtDeviceByIds(String[] ids) {
        return ctDeviceMapper.deleteCtDeviceByIds(ids);
    }

    /**
     * 删除电子设备信息
     *
     * @param id 电子设备主键
     * @return 结果
     */
    @Override
    public int deleteCtDeviceById(Long id) {
        return ctDeviceMapper.deleteCtDeviceById(id);
    }

    /**
     * 新增电子设备
     *
     * @param param 电子设备
     * @return 结果
     */
    @Override
    public int activateCtDevice(ActivateDeviceReq param) {
        CtDevice device = ctDeviceMapper.selectCtDeviceById(param.getDeviceId());
        if (device == null) {
            throw new ServiceException("无法获取设备信息");
        }
        CtPayment paymentParam = paymentMapper.selectCtPaymentByUniqueKey(param.getDeviceId(), PayeeType.CASH.getCode(), PayWay.SHOU_QIAN_BA.getCode());
        if (paymentParam != null) {
            throw new ServiceException("当前收银机已激活，请勿重复激活");
        }
        BuzResponse buzResponse = shouqianbaApiService.activateTerminal(device.getDeviceCode(), param.getCode());
        if (buzResponse == null) {
            throw new ServiceException("调用收钱吧激活接口失败");
        }
        if (!StringUtils.equals(buzResponse.getResultCode(), ShouqianbaConstant.response200)) {
            throw new ServiceException("调用收钱吧激活接口失败:" + buzResponse.getErrorMessage());
        }
        if (buzResponse.getData() != null) {
            throw new ServiceException("调用收钱吧激活接口返回参数异常");
        }
        Map<String, String> map = JacksonUtils.convertObject(buzResponse.getData(), Map.class);
        if (map != null) {
            String terminalSn = map.get("terminal_sn");
            String terminalKey = map.get("terminal_key");
            ShouqianbaParam newParam = new ShouqianbaParam();
            newParam.setDeviceNo(device.getDeviceCode());
            newParam.setTerminalSn(terminalSn);
            newParam.setTerminalKey(terminalKey);
            CtPayment ctPayment = new CtPayment();
            ctPayment.setPayeeId(device.getDeviceCode());
            ctPayment.setPayeeType(PayeeType.CASH.getCode());
            ctPayment.setPayWay(PayWay.SHOU_QIAN_BA.getCode());
            ctPayment.setPayParams(JSONObject.toJSONString(newParam));
            ctPayment.setPayDesc("收钱吧支付参数");
            ctPayment.setEnabled("1");
            ctPayment.setDelFlag("0");
            ctPayment.setCreateTime(DateUtils.getNowDate());
            ctPayment.setUpdateTime(DateUtils.getNowDate());
            device.setIsActivited("1");
            ctDeviceMapper.updateCtDevice(device);
            return paymentMapper.insertCtPayment(ctPayment);
        }
        return 0;
    }
}
