package com.cloudtimes.app.process.cash;

import com.cloudtimes.app.process.BaseEventProcess;
import com.cloudtimes.common.core.domain.entity.AuthUser;
import com.cloudtimes.common.utils.StringUtils;
import com.cloudtimes.hardwaredevice.domain.CtDevice;
import com.cloudtimes.hardwaredevice.domain.CtStore;
import com.cloudtimes.hardwaredevice.mapper.CtDeviceMapper;
import com.cloudtimes.hardwaredevice.mapper.CtStoreMapper;
import com.cloudtimes.mq.CtWebMqSenderService;
import com.cloudtimes.supervise.domain.CtEvents;
import com.cloudtimes.supervise.domain.CtTask;
import com.cloudtimes.supervise.mapper.CtTaskMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 收银机呼叫客服处理
 */
@Slf4j
@Component("CALL-REQ")
public class CallReqEventProcess implements BaseEventProcess {
    @Autowired
    private CtDeviceMapper deviceMapper;
    @Autowired
    private CtStoreMapper storeMapper;
    @Autowired
    private CtTaskMapper taskMapper;
    @Autowired
    private CtWebMqSenderService mqSenderService;

    @Override
    public String eventName() {
        return "收银机呼叫客服处理";
    }

    @Override
    public String process(AuthUser authUser, Object object) {
        //推送消息到后台
        CtDevice device = deviceMapper.selectCtDeviceById(authUser.getId());
        if (device != null && StringUtils.isNotEmpty(device.getStoreId())) {
            CtStore ctStore = storeMapper.selectCtStoreById(device.getStoreId());
            if (ctStore == null) {
                return "无法获取门店信息";
            }
            CtTask query = new CtTask();
            query.setStoreId(device.getStoreId());
            query.setState("0");
            List<CtTask> ctTasks = taskMapper.selectCtTaskList(query);
            if (ctTasks != null && ctTasks.size() > 0) {
                // 发送进客提醒给客服,及时响应
                for (CtTask task :
                        ctTasks) {
                    CtEvents newEvent = new CtEvents();
                    newEvent.setEventName("值守下线通知");
                    newEvent.setContent(ctStore.getName() + "有人呼叫客服，请及时打开语音沟通");
                    newEvent.setReceiver(task.getStaffCode());
                    newEvent.setTaskId(task.getId());
                    mqSenderService.sendCustomerServiceMessage(newEvent);
                }
            }
        } else {
            return "无法获取设备信息或设备未绑定门店";
        }
        return null;
    }
}
