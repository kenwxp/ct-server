package com.cloudtimes.web.controller.supervise;

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
import com.cloudtimes.supervise.domain.CtTask;
import com.cloudtimes.supervise.service.ICtTaskService;
import com.cloudtimes.common.utils.poi.ExcelUtil;
import com.cloudtimes.common.core.page.TableDataInfo;

/**
 * 值守任务Controller
 *
 * @author wangxp
 * @date 2023-02-07
 */
@RestController
@RequestMapping("/supervise/cttask")
public class CtTaskController extends BaseController {
    @Autowired
    private ICtTaskService ctTaskService;

    /**
     * 查询值守任务列表
     */
    @PreAuthorize("@ss.hasPermi('supervise:cttask:list')")
    @GetMapping("/list")
    public TableDataInfo list(CtTask ctTask) {
        startPage();
        List<CtTask> list = ctTaskService.selectCtTaskList(ctTask);
        return getDataTable(list);
    }

    /**
     * 导出值守任务列表
     */
    @PreAuthorize("@ss.hasPermi('supervise:cttask:export')")
    @Log(title = "值守任务", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, CtTask ctTask) {
        List<CtTask> list = ctTaskService.selectCtTaskList(ctTask);
        ExcelUtil<CtTask> util = new ExcelUtil<CtTask>(CtTask.class);
        util.exportExcel(response, list, "值守任务数据");
    }

    /**
     * 获取值守任务详细信息
     */
    @PreAuthorize("@ss.hasPermi('supervise:cttask:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") String id) {
        return AjaxResult.success(ctTaskService.selectCtTaskById(id));
    }

    /**
     * 新增值守任务
     */
    @PreAuthorize("@ss.hasPermi('supervise:cttask:add')")
    @Log(title = "值守任务", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody CtTask ctTask) {
        return toAjax(ctTaskService.insertCtTask(ctTask));
    }

    /**
     * 修改值守任务
     */
    @PreAuthorize("@ss.hasPermi('supervise:cttask:edit')")
    @Log(title = "值守任务", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody CtTask ctTask) {
        return toAjax(ctTaskService.updateCtTask(ctTask));
    }

    /**
     * 删除值守任务
     */
    @PreAuthorize("@ss.hasPermi('supervise:cttask:remove')")
    @Log(title = "值守任务", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable String[] ids) {
        return toAjax(ctTaskService.deleteCtTaskByIds(ids));
    }
}
