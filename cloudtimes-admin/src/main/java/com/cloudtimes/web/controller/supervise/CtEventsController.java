package com.cloudtimes.web.controller.supervise;

import com.cloudtimes.common.annotation.Log;
import com.cloudtimes.common.core.controller.BaseController;
import com.cloudtimes.common.core.domain.AjaxResult;
import com.cloudtimes.common.core.page.TableDataInfo;
import com.cloudtimes.common.enums.BusinessType;
import com.cloudtimes.common.utils.poi.ExcelUtil;
import com.cloudtimes.supervise.domain.CtEvents;
import com.cloudtimes.supervise.service.ICtEventsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 事件Controller
 *
 * @author wangxp
 * @date 2023-02-07
 */
@RestController
@RequestMapping("/supervise/events")
public class CtEventsController extends BaseController {
    @Autowired
    private ICtEventsService ctEventsService;

    /**
     * 查询事件列表
     */
    @PreAuthorize("@ss.hasPermi('supervise:events:list')")
    @GetMapping("/list")
    public TableDataInfo list(CtEvents ctEvents) {
        startPage();
        List<CtEvents> list = ctEventsService.selectCtEventsList(ctEvents);
        return getDataTable(list);
    }

    /**
     * 导出事件列表
     */
    @PreAuthorize("@ss.hasPermi('supervise:events:export')")
    @Log(title = "事件", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, CtEvents ctEvents) {
        List<CtEvents> list = ctEventsService.selectCtEventsList(ctEvents);
        ExcelUtil<CtEvents> util = new ExcelUtil<CtEvents>(CtEvents.class);
        util.exportExcel(response, list, "事件数据");
    }

    /**
     * 获取事件详细信息
     */
    @PreAuthorize("@ss.hasPermi('supervise:events:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") String id) {
        return AjaxResult.success(ctEventsService.selectCtEventsById(id));
    }

    /**
     * 新增事件
     */
    @PreAuthorize("@ss.hasPermi('supervise:events:add')")
    @Log(title = "事件", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody CtEvents ctEvents) {
        return toAjax(ctEventsService.insertCtEvents(ctEvents));
    }

    /**
     * 修改事件
     */
    @PreAuthorize("@ss.hasPermi('supervise:events:edit')")
    @Log(title = "事件", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody CtEvents ctEvents) {
        return toAjax(ctEventsService.updateCtEvents(ctEvents));
    }

    /**
     * 删除事件
     */
    @PreAuthorize("@ss.hasPermi('supervise:events:remove')")
    @Log(title = "事件", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable String[] ids) {
        return toAjax(ctEventsService.deleteCtEventsByIds(ids));
    }
}
