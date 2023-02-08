package com.cloudtimes.hardwaredevice.service.impl;

import java.util.List;

import com.cloudtimes.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.cloudtimes.common.annotation.DataSource;
import com.cloudtimes.common.enums.DataSourceType;
import com.cloudtimes.hardwaredevice.mapper.CtSuperviseLogsMapper;
import com.cloudtimes.hardwaredevice.domain.CtSuperviseLogs;
import com.cloudtimes.hardwaredevice.service.ICtSuperviseLogsService;

/**
 * 申请值守日志Service业务层处理
 *
 * @author tank
 * @date 2023-02-08
 */
@DataSource(DataSourceType.CT)
@Service
public class CtSuperviseLogsServiceImpl implements ICtSuperviseLogsService {
    @Autowired
    private CtSuperviseLogsMapper ctSuperviseLogsMapper;

    /**
     * 查询申请值守日志
     *
     * @param id 申请值守日志主键
     * @return 申请值守日志
     */
    @Override
    public CtSuperviseLogs selectCtSuperviseLogsById(String id) {
        return ctSuperviseLogsMapper.selectCtSuperviseLogsById(id);
    }

    /**
     * 查询申请值守日志列表
     *
     * @param ctSuperviseLogs 申请值守日志
     * @return 申请值守日志
     */
    @Override
    public List<CtSuperviseLogs> selectCtSuperviseLogsList(CtSuperviseLogs ctSuperviseLogs) {
        return ctSuperviseLogsMapper.selectCtSuperviseLogsList(ctSuperviseLogs);
    }

    /**
     * 新增申请值守日志
     *
     * @param ctSuperviseLogs 申请值守日志
     * @return 结果
     */
    @Override
    public int insertCtSuperviseLogs(CtSuperviseLogs ctSuperviseLogs) {
        ctSuperviseLogs.setCreateTime(DateUtils.getNowDate());
        return ctSuperviseLogsMapper.insertCtSuperviseLogs(ctSuperviseLogs);
    }

    /**
     * 修改申请值守日志
     *
     * @param ctSuperviseLogs 申请值守日志
     * @return 结果
     */
    @Override
    public int updateCtSuperviseLogs(CtSuperviseLogs ctSuperviseLogs) {
        ctSuperviseLogs.setUpdateTime(DateUtils.getNowDate());
        return ctSuperviseLogsMapper.updateCtSuperviseLogs(ctSuperviseLogs);
    }

    /**
     * 批量删除申请值守日志
     *
     * @param ids 需要删除的申请值守日志主键
     * @return 结果
     */
    @Override
    public int deleteCtSuperviseLogsByIds(String[] ids) {
        return ctSuperviseLogsMapper.deleteCtSuperviseLogsByIds(ids);
    }

    /**
     * 删除申请值守日志信息
     *
     * @param id 申请值守日志主键
     * @return 结果
     */
    @Override
    public int deleteCtSuperviseLogsById(String id) {
        return ctSuperviseLogsMapper.deleteCtSuperviseLogsById(id);
    }
}
