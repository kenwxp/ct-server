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
import com.cloudtimes.hardwaredevice.domain.CtOpenDoorLogs;
import com.cloudtimes.hardwaredevice.service.ICtOpenDoorLogsService;
import com.cloudtimes.common.utils.poi.ExcelUtil;
import com.cloudtimes.common.core.page.TableDataInfo;

/**
 * 开门日志Controller
 * 
 * @author tank
 * @date 2023-01-17
 */
@RestController
@RequestMapping("/hardwaredevice/ctopendoorlogs")
public class CtOpenDoorLogsController extends BaseController
{
    @Autowired
    private ICtOpenDoorLogsService ctOpenDoorLogsService;

    /**
     * 查询开门日志列表
     */
    @PreAuthorize("@ss.hasPermi('hardwaredevice:ctopendoorlogs:list')")
    @GetMapping("/list")
    public TableDataInfo list(CtOpenDoorLogs ctOpenDoorLogs)
    {
        startPage();
        List<CtOpenDoorLogs> list = ctOpenDoorLogsService.selectCtOpenDoorLogsList(ctOpenDoorLogs);
        return getDataTable(list);
    }

    /**
     * 导出开门日志列表
     */
    @PreAuthorize("@ss.hasPermi('hardwaredevice:ctopendoorlogs:export')")
    @Log(title = "开门日志", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, CtOpenDoorLogs ctOpenDoorLogs)
    {
        List<CtOpenDoorLogs> list = ctOpenDoorLogsService.selectCtOpenDoorLogsList(ctOpenDoorLogs);
        ExcelUtil<CtOpenDoorLogs> util = new ExcelUtil<CtOpenDoorLogs>(CtOpenDoorLogs.class);
        util.exportExcel(response, list, "开门日志数据");
    }

    /**
     * 获取开门日志详细信息
     */
    @PreAuthorize("@ss.hasPermi('hardwaredevice:ctopendoorlogs:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(ctOpenDoorLogsService.selectCtOpenDoorLogsById(id));
    }

    /**
     * 新增开门日志
     */
    @PreAuthorize("@ss.hasPermi('hardwaredevice:ctopendoorlogs:add')")
    @Log(title = "开门日志", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody CtOpenDoorLogs ctOpenDoorLogs)
    {
        return toAjax(ctOpenDoorLogsService.insertCtOpenDoorLogs(ctOpenDoorLogs));
    }

    /**
     * 修改开门日志
     */
    @PreAuthorize("@ss.hasPermi('hardwaredevice:ctopendoorlogs:edit')")
    @Log(title = "开门日志", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody CtOpenDoorLogs ctOpenDoorLogs)
    {
        return toAjax(ctOpenDoorLogsService.updateCtOpenDoorLogs(ctOpenDoorLogs));
    }

    /**
     * 删除开门日志
     */
    @PreAuthorize("@ss.hasPermi('hardwaredevice:ctopendoorlogs:remove')")
    @Log(title = "开门日志", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(ctOpenDoorLogsService.deleteCtOpenDoorLogsByIds(ids));
    }
}
