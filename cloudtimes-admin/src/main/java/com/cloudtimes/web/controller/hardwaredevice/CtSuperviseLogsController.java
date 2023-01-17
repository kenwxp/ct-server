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
import com.cloudtimes.hardwaredevice.domain.CtSuperviseLogs;
import com.cloudtimes.hardwaredevice.service.ICtSuperviseLogsService;
import com.cloudtimes.common.utils.poi.ExcelUtil;
import com.cloudtimes.common.core.page.TableDataInfo;

/**
 * 申请值守日志Controller
 * 
 * @author tank
 * @date 2023-01-17
 */
@RestController
@RequestMapping("/hardwaredevice/ctsuperviselogs")
public class CtSuperviseLogsController extends BaseController
{
    @Autowired
    private ICtSuperviseLogsService ctSuperviseLogsService;

    /**
     * 查询申请值守日志列表
     */
    @PreAuthorize("@ss.hasPermi('hardwaredevice:ctsuperviselogs:list')")
    @GetMapping("/list")
    public TableDataInfo list(CtSuperviseLogs ctSuperviseLogs)
    {
        startPage();
        List<CtSuperviseLogs> list = ctSuperviseLogsService.selectCtSuperviseLogsList(ctSuperviseLogs);
        return getDataTable(list);
    }

    /**
     * 导出申请值守日志列表
     */
    @PreAuthorize("@ss.hasPermi('hardwaredevice:ctsuperviselogs:export')")
    @Log(title = "申请值守日志", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, CtSuperviseLogs ctSuperviseLogs)
    {
        List<CtSuperviseLogs> list = ctSuperviseLogsService.selectCtSuperviseLogsList(ctSuperviseLogs);
        ExcelUtil<CtSuperviseLogs> util = new ExcelUtil<CtSuperviseLogs>(CtSuperviseLogs.class);
        util.exportExcel(response, list, "申请值守日志数据");
    }

    /**
     * 获取申请值守日志详细信息
     */
    @PreAuthorize("@ss.hasPermi('hardwaredevice:ctsuperviselogs:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(ctSuperviseLogsService.selectCtSuperviseLogsById(id));
    }

    /**
     * 新增申请值守日志
     */
    @PreAuthorize("@ss.hasPermi('hardwaredevice:ctsuperviselogs:add')")
    @Log(title = "申请值守日志", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody CtSuperviseLogs ctSuperviseLogs)
    {
        return toAjax(ctSuperviseLogsService.insertCtSuperviseLogs(ctSuperviseLogs));
    }

    /**
     * 修改申请值守日志
     */
    @PreAuthorize("@ss.hasPermi('hardwaredevice:ctsuperviselogs:edit')")
    @Log(title = "申请值守日志", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody CtSuperviseLogs ctSuperviseLogs)
    {
        return toAjax(ctSuperviseLogsService.updateCtSuperviseLogs(ctSuperviseLogs));
    }

    /**
     * 删除申请值守日志
     */
    @PreAuthorize("@ss.hasPermi('hardwaredevice:ctsuperviselogs:remove')")
    @Log(title = "申请值守日志", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(ctSuperviseLogsService.deleteCtSuperviseLogsByIds(ids));
    }
}
