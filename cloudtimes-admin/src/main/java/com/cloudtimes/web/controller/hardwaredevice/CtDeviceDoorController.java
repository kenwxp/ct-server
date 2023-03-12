package com.cloudtimes.web.controller.hardwaredevice;

import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletResponse;

import com.cloudtimes.common.constant.RocketMQConstants;
import com.cloudtimes.common.enums.ChannelType;
import com.cloudtimes.common.enums.OpenDoorOption;
import com.cloudtimes.common.mq.CtRocketMqProducer;
import com.cloudtimes.common.mq.OpenDoorMqData;
import com.cloudtimes.common.utils.DateUtils;
import com.cloudtimes.hardwaredevice.domain.CtDevice;
import com.cloudtimes.hardwaredevice.domain.dto.CtDeviceDoorDto;
import com.cloudtimes.hardwaredevice.service.ICtDeviceService;
import com.cloudtimes.hardwaredevice.service.ICtStoreService;
import com.cloudtimes.mq.CtOpenDoorService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.cloudtimes.common.annotation.Log;
import com.cloudtimes.common.core.controller.BaseController;
import com.cloudtimes.common.core.domain.AjaxResult;
import com.cloudtimes.common.enums.BusinessType;
import com.cloudtimes.hardwaredevice.domain.CtDeviceDoor;
import com.cloudtimes.hardwaredevice.service.ICtDeviceDoorService;
import com.cloudtimes.common.utils.poi.ExcelUtil;
import com.cloudtimes.common.core.page.TableDataInfo;

/**
 * 门禁设备密码Controller
 *
 * @author tank
 * @date 2023-02-09
 */
@RestController
@RequestMapping("/hardwaredevice/ctdevicedoor")
public class CtDeviceDoorController extends BaseController {
    @Autowired
    private ICtDeviceDoorService ctDeviceDoorService;

    @Autowired
    private CtOpenDoorService openDoorService;

    @Autowired
    private ICtStoreService storeService;

    @Autowired
    private ICtDeviceService deviceService;

    @Autowired
    private CtRocketMqProducer producer;


    /**
     * 查询门禁设备密码列表
     */
    @PreAuthorize("@ss.hasPermi('hardwaredevice:ctdevicedoor:list')")
    @GetMapping("/list")
    public TableDataInfo list(CtDeviceDoor ctDeviceDoor) {
        startPage();
        List<CtDeviceDoorDto> list = ctDeviceDoorService.selectCtDeviceDoorListPlus(ctDeviceDoor);
        return getDataTable(list);
    }

    /**
     * 导出门禁设备密码列表
     */
    @PreAuthorize("@ss.hasPermi('hardwaredevice:ctdevicedoor:export')")
    @Log(title = "门禁设备密码", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, CtDeviceDoor ctDeviceDoor) {
        List<CtDeviceDoor> list = ctDeviceDoorService.selectCtDeviceDoorList(ctDeviceDoor);
        ExcelUtil<CtDeviceDoor> util = new ExcelUtil<CtDeviceDoor>(CtDeviceDoor.class);
        util.exportExcel(response, list, "门禁设备密码数据");
    }

    /**
     * 获取门禁设备密码详细信息
     */
    @PreAuthorize("@ss.hasPermi('hardwaredevice:ctdevicedoor:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") String id) {
        return AjaxResult.success(ctDeviceDoorService.selectCtDeviceDoorById(id));
    }

    /**
     * 新增门禁设备密码
     */
    @PreAuthorize("@ss.hasPermi('hardwaredevice:ctdevicedoor:add')")
    @Log(title = "门禁设备密码", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody CtDeviceDoor ctDeviceDoor) {

        CtDevice device = deviceService.selectCtDeviceById(ctDeviceDoor.getDeviceId());
        if (device == null) {
            return AjaxResult.error("该电子设备不存在");
        }

        CtDeviceDoor deviceDoor = new CtDeviceDoor();
        deviceDoor.setDeviceId(ctDeviceDoor.getDeviceId());
        List<CtDeviceDoor> existsList = ctDeviceDoorService.selectCtDeviceDoorList(deviceDoor);
        if (existsList.size() > 0) {
            return AjaxResult.error("该电子设备门禁已添加");
        }
        return toAjax(ctDeviceDoorService.insertCtDeviceDoor(ctDeviceDoor));
    }

    /**
     * 修改门禁设备密码
     */
    @PreAuthorize("@ss.hasPermi('hardwaredevice:ctdevicedoor:edit')")
    @Log(title = "门禁设备密码", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody CtDeviceDoor ctDeviceDoor) {
        return toAjax(ctDeviceDoorService.updateCtDeviceDoor(ctDeviceDoor));
    }

    /**
     * 删除门禁设备密码
     */
    @PreAuthorize("@ss.hasPermi('hardwaredevice:ctdevicedoor:remove')")
    @Log(title = "门禁设备密码", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable String[] ids) {
        return toAjax(ctDeviceDoorService.deleteCtDeviceDoorByIds(ids));
    }

    @GetMapping(value = "/emergencyOpenDoor/{id}")
    public AjaxResult emergencyOpenDoor(@PathVariable("id") String id) {
        CtDevice ctDevice = deviceService.selectCtDeviceById(id);
        OpenDoorMqData mqData = new OpenDoorMqData();
        mqData.setOption(OpenDoorOption.EMERGENCY_OPEN_DOOR);
        mqData.setStoreId(ctDevice.getStoreId());
        mqData.setUserId(getUserId().toString());
        mqData.setChannelType(ChannelType.WEB);
        producer.send(RocketMQConstants.CT_OPEN_DOOR, mqData);
        return AjaxResult.success("操作成功");
    }

    @GetMapping(value = "/setDoorAccess/{id}")
    public AjaxResult setDoorAccess(@PathVariable("id") String id) {
        CtDeviceDoor deviceDoor = ctDeviceDoorService.selectCtDeviceDoorById(id);
        CtDevice ctDevice = deviceService.selectCtDeviceById(deviceDoor.getDeviceId());
        Date now = DateUtils.getNowDate();
        String startTime = DateUtils.formatDateTime(DateUtils.getNowDate());
        String endTime = DateUtils.formatDateTime(DateUtils.addDays(now, 9999));

        String accessPassword = openDoorService.setDoorAccessPasswrd(ctDevice.getStoreId(), getUserId().toString(), ChannelType.WEB, false, startTime, endTime);
        deviceDoor.setAccessPassword(accessPassword);
        ctDeviceDoorService.updateCtDeviceDoor(deviceDoor);
        return AjaxResult.success("操作成功");
    }


}
