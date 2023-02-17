package com.cloudtimes.serving.common;

import com.cloudtimes.cache.CtTaskCache;
import com.cloudtimes.common.exception.ServiceException;
import com.cloudtimes.hardwaredevice.domain.CtStore;
import com.cloudtimes.hardwaredevice.mapper.CtStoreMapper;
import com.cloudtimes.supervise.domain.CtTask;
import com.cloudtimes.supervise.mapper.CtTaskMapper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class CtTaskInnerService {
    @Autowired
    private CtStoreMapper storeMapper;
    @Autowired
    private CtTaskMapper taskMapper;
    @Autowired
    private CtTaskCache taskCache;

    /**
     * 门店分配任务
     *
     * @param storeId
     * @return
     */
    public CtTask distributeTask(String storeId) {
        CtStore dbStore = storeMapper.selectCtStoreById(storeId);
        if (dbStore == null) {
            throw new ServiceException("无法获取门店信息");
        }
        if (StringUtils.equals(dbStore.getIsSupervise(), "1")) {
            // 值守中分配新任务
            CtTask query = new CtTask();
            query.setStoreId(dbStore.getId());
            query.setState("0");
            List<CtTask> dbTasks = taskMapper.selectCtTaskList(query);
            if (dbTasks == null || dbTasks.size() == 0) {
                CtTask newTask = new CtTask();
                newTask.setStoreId(storeId);
                // todo 分配客服
                String staffCode = distributeStaff();
                if (StringUtils.isEmpty(staffCode)) {
                    throw new ServiceException("当前无值守员在岗");
                }
                newTask.setStaffCode("");
                newTask.setTaskType("0");
                newTask.setDescText("");
                newTask.setStartTime(new Date());
                newTask.setEndTime(new Date());
                newTask.setIsExceptional("0");
                // todo 开门日志获取
                newTask.setDoorLogId("");
                newTask.setSuperviseArea("");
                newTask.setState("0");
                newTask.setDelFlag("0");
                newTask.setCreateTime(new Date());
                newTask.setUpdateTime(new Date());
                newTask.setCreateTime(new Date());
                newTask.setUpdateTime(new Date());
                if (taskMapper.insertCtTask(newTask) < 1) {
                    throw new ServiceException("新增任务失败");
                }
                // 放入内存
                taskCache.setCacheTask(newTask);
                return newTask;
            } else {
                return dbTasks.get(0);
            }
        }
        return null;
    }

    /**
     * 分配值守员
     *
     * @return
     */
    public String distributeStaff() {

        return "";
    }
}
