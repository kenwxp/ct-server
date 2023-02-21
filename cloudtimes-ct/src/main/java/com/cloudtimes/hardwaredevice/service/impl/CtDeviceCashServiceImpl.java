package com.cloudtimes.hardwaredevice.service.impl;

import java.util.List;
import com.cloudtimes.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.cloudtimes.common.annotation.DataSource;
import com.cloudtimes.common.enums.DataSourceType;
import com.cloudtimes.hardwaredevice.mapper.CtDeviceCashMapper;
import com.cloudtimes.hardwaredevice.domain.CtDeviceCash;
import com.cloudtimes.hardwaredevice.service.ICtDeviceCashService;

/**
 * 收银机特有信息Service业务层处理
 * 
 * @author tank
 * @date 2023-01-17
 */
@DataSource(DataSourceType.CT)
@Service
public class CtDeviceCashServiceImpl implements ICtDeviceCashService 
{
    @Autowired
    private CtDeviceCashMapper ctDeviceCashMapper;

    /**
     * 查询收银机特有信息
     * 
     * @param id 收银机特有信息主键
     * @return 收银机特有信息
     */
    @Override
    public CtDeviceCash selectCtDeviceCashById(String id)
    {
        return ctDeviceCashMapper.selectCtDeviceCashById(id);
    }

    /**
     * 查询收银机特有信息列表
     * 
     * @param ctDeviceCash 收银机特有信息
     * @return 收银机特有信息
     */
    @Override
    public List<CtDeviceCash> selectCtDeviceCashList(CtDeviceCash ctDeviceCash)
    {
        return ctDeviceCashMapper.selectCtDeviceCashList(ctDeviceCash);
    }

    /**
     * 新增收银机特有信息
     * 
     * @param ctDeviceCash 收银机特有信息
     * @return 结果
     */
    @Override
    public int insertCtDeviceCash(CtDeviceCash ctDeviceCash)
    {
        ctDeviceCash.setCreateTime(DateUtils.getNowDate());
        return ctDeviceCashMapper.insertCtDeviceCash(ctDeviceCash);
    }

    /**
     * 修改收银机特有信息
     * 
     * @param ctDeviceCash 收银机特有信息
     * @return 结果
     */
    @Override
    public int updateCtDeviceCash(CtDeviceCash ctDeviceCash)
    {
        ctDeviceCash.setUpdateTime(DateUtils.getNowDate());
        return ctDeviceCashMapper.updateCtDeviceCash(ctDeviceCash);
    }

    /**
     * 批量删除收银机特有信息
     * 
     * @param ids 需要删除的收银机特有信息主键
     * @return 结果
     */
    @Override
    public int deleteCtDeviceCashByIds(String[] ids)
    {
        return ctDeviceCashMapper.deleteCtDeviceCashByIds(ids);
    }

    /**
     * 删除收银机特有信息信息
     * 
     * @param id 收银机特有信息主键
     * @return 结果
     */
    @Override
    public int deleteCtDeviceCashById(String id)
    {
        return ctDeviceCashMapper.deleteCtDeviceCashById(id);
    }
}
