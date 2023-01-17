package com.cloudtimes.web.controller.hardwaredevice;

import java.util.List;
import javax.servlet.http.HttpServletResponse;
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
import com.cloudtimes.hardwaredevice.domain.CtDevice;
import com.cloudtimes.hardwaredevice.service.ICtDeviceService;
import com.cloudtimes.common.utils.poi.ExcelUtil;
import com.cloudtimes.common.core.page.TableDataInfo;

/**
 * 电子设备Controller
 * 
 * @author tank
 * @date 2023-01-17
 */
@RestController
@RequestMapping("/hardwaredevice/ctdevice")
public class CtDeviceController extends BaseController
{
    @Autowired
    private ICtDeviceService ctDeviceService;

    /**
     * 查询电子设备列表
     */
    @PreAuthorize("@ss.hasPermi('hardwaredevice:ctdevice:list')")
    @GetMapping("/list")
    public TableDataInfo list(CtDevice ctDevice)
    {
        startPage();
        List<CtDevice> list = ctDeviceService.selectCtDeviceList(ctDevice);
        return getDataTable(list);
    }

    /**
     * 导出电子设备列表
     */
    @PreAuthorize("@ss.hasPermi('hardwaredevice:ctdevice:export')")
    @Log(title = "电子设备", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, CtDevice ctDevice)
    {
        List<CtDevice> list = ctDeviceService.selectCtDeviceList(ctDevice);
        ExcelUtil<CtDevice> util = new ExcelUtil<CtDevice>(CtDevice.class);
        util.exportExcel(response, list, "电子设备数据");
    }

    /**
     * 获取电子设备详细信息
     */
    @PreAuthorize("@ss.hasPermi('hardwaredevice:ctdevice:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(ctDeviceService.selectCtDeviceById(id));
    }

    /**
     * 新增电子设备
     */
    @PreAuthorize("@ss.hasPermi('hardwaredevice:ctdevice:add')")
    @Log(title = "电子设备", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody CtDevice ctDevice)
    {
        return toAjax(ctDeviceService.insertCtDevice(ctDevice));
    }

    /**
     * 修改电子设备
     */
    @PreAuthorize("@ss.hasPermi('hardwaredevice:ctdevice:edit')")
    @Log(title = "电子设备", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody CtDevice ctDevice)
    {
        return toAjax(ctDeviceService.updateCtDevice(ctDevice));
    }

    /**
     * 删除电子设备
     */
    @PreAuthorize("@ss.hasPermi('hardwaredevice:ctdevice:remove')")
    @Log(title = "电子设备", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(ctDeviceService.deleteCtDeviceByIds(ids));
    }
}
