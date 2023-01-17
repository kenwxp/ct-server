package com.cloudtimes.hardwaredevice.service;

import java.util.List;
import com.cloudtimes.hardwaredevice.domain.CtDeviceCash;

/**
 * 收银机特有信息Service接口
 * 
 * @author tank
 * @date 2023-01-17
 */
public interface ICtDeviceCashService 
{
    /**
     * 查询收银机特有信息
     * 
     * @param id 收银机特有信息主键
     * @return 收银机特有信息
     */
    public CtDeviceCash selectCtDeviceCashById(Long id);

    /**
     * 查询收银机特有信息列表
     * 
     * @param ctDeviceCash 收银机特有信息
     * @return 收银机特有信息集合
     */
    public List<CtDeviceCash> selectCtDeviceCashList(CtDeviceCash ctDeviceCash);

    /**
     * 新增收银机特有信息
     * 
     * @param ctDeviceCash 收银机特有信息
     * @return 结果
     */
    public int insertCtDeviceCash(CtDeviceCash ctDeviceCash);

    /**
     * 修改收银机特有信息
     * 
     * @param ctDeviceCash 收银机特有信息
     * @return 结果
     */
    public int updateCtDeviceCash(CtDeviceCash ctDeviceCash);

    /**
     * 批量删除收银机特有信息
     * 
     * @param ids 需要删除的收银机特有信息主键集合
     * @return 结果
     */
    public int deleteCtDeviceCashByIds(Long[] ids);

    /**
     * 删除收银机特有信息信息
     * 
     * @param id 收银机特有信息主键
     * @return 结果
     */
    public int deleteCtDeviceCashById(Long id);
}
