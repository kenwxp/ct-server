package com.cloudtimes.serving.common;

import com.cloudtimes.cache.CtTaskCache;
import com.cloudtimes.cache.CtCustomerServiceCache;
import com.cloudtimes.common.enums.AcceptTaskType;
import com.cloudtimes.common.exception.ServiceException;
import com.cloudtimes.common.enums.OpenDoorOption;
import com.cloudtimes.common.utils.StringUtils;
import com.cloudtimes.hardwaredevice.domain.CtOpenDoorLogs;
import com.cloudtimes.hardwaredevice.domain.CtStore;
import com.cloudtimes.hardwaredevice.mapper.CtOpenDoorLogsMapper;
import com.cloudtimes.hardwaredevice.mapper.CtStoreMapper;
import com.cloudtimes.supervise.domain.CtOrder;
import com.cloudtimes.supervise.domain.CtTask;
import com.cloudtimes.supervise.mapper.CtTaskMapper;
import com.cloudtimes.supervise.domain.CtCustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class CtTaskInnerService {
    @Autowired
    private CtStoreMapper storeMapper;
    @Autowired
    private CtTaskMapper taskMapper;
    @Autowired
    private CtTaskCache taskCache;
    @Autowired
    private CtOpenDoorLogsMapper openDoorLogsMapper;

    /**
     * 门店分配任务
     *
     * @param storeId
     * @return
     */
    public CtTask distributeTask(String storeId, String doorLogId) {
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
                newTask.setStoreName(dbStore.getName());
                newTask.setContactName(dbStore.getContactName());
                newTask.setContactPhone(dbStore.getContactPhone());
                // 分配客服
                String staffCode = distributeStaff();
                if (StringUtils.isEmpty(staffCode)) {
                    throw new ServiceException("当前无值守员在岗");
                }
                newTask.setStaffCode(staffCode);
                newTask.setTaskType("0");
                newTask.setDescText("门禁触发产生任务");
                newTask.setStartTime(new Date());
                newTask.setEndTime(new Date());
                newTask.setIsExceptional("0");
                if (StringUtils.isEmpty(doorLogId)) {
                    newTask.setDescText("顾客扫码或扫脸生产任务");
                    // 开门日志获取
                    CtOpenDoorLogs openLog = openDoorLogsMapper.selectLatestOpenDoorLogByStore(storeId, OpenDoorOption.TRIGGER_OPEN_DOOR.getCode());
                    if (openLog != null) {
                        doorLogId = openLog.getId();
                    }
                }
                newTask.setDoorLogId(doorLogId);
                newTask.setSuperviseArea("");
                newTask.setState("0");
                newTask.setDelFlag("0");
                newTask.setCreateTime(new Date());
                newTask.setUpdateTime(new Date());
                newTask.setCreateTime(new Date());
                newTask.setUpdateTime(new Date());
                // 设置任务锁
                newTask.setOpenLock(false);
                if (taskMapper.insertCtTask(newTask) < 1) {
                    throw new ServiceException("新增任务失败");
                }
                // 放入内存
                taskCache.setCacheTask(newTask);
                // todo 发送新任务提醒给客服
                return newTask;
            } else {
                // todo 发送进客提醒给客服
                return dbTasks.get(0);
            }
        }
        return null;
    }

    @Autowired
    private CtCustomerServiceCache customerServiceCache;


    /**
     * 分配值守员
     *
     * @return
     */
    public String distributeStaff() {
        // 获取全部客服人员
        List<CtCustomerService> allCustomerServiceList = customerServiceCache.getAllCtCustomerServiceList();
        // 遍历每个客服人员，根据接单标志，当前任务数，分配期望任务量最少的人员
        Long pick = 0L;
        BigDecimal maxWeight = new BigDecimal(0);
        for (CtCustomerService customerService :
                allCustomerServiceList) {
            if (StringUtils.equals(customerService.getAcceptState(), AcceptTaskType.START.getCode())) {
                // 接单中的客服进行分配
                Map<String, CtTask> taskMap = taskCache.getAllTasksOfStaff(String.valueOf(customerService.getServiceId()));
                if (StringUtils.isEmpty(taskMap)) {
                    // 未分配任务的客服直接分配
                    return String.valueOf(customerService.getServiceId());
                }
                int taskCount = taskMap.size();
                int orderCount = 0;
                for (CtTask task :
                        taskMap.values()) {
                    Map<String, CtOrder> ordersByTask = taskCache.getOrdersByTask(task.getId());
                    if (!StringUtils.isEmpty(ordersByTask)) {
                        orderCount = orderCount + ordersByTask.size();
                    }
                }
                Long maxAcceptTask = customerService.getMaxAcceptTask();
                Long maxAcceptOrder = customerService.getMaxAcceptOrder();
                if (taskCount <= maxAcceptTask && orderCount <= maxAcceptOrder) {
                    //计算权重,取权重最大的客服
                    BigDecimal weight = new BigDecimal(maxAcceptTask).subtract(new BigDecimal(taskCount)).divide(new BigDecimal(maxAcceptTask));
                    if (weight.compareTo(maxWeight) == 1) {
                        maxWeight = weight;
                        pick = customerService.getServiceId();
                    }
                }
            }
        }
        if (pick != 0) {
            return String.valueOf(pick);
        }
        return "";
    }
}
