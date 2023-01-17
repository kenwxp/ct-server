package com.cloudtimes.web.controller.resources;

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
import com.cloudtimes.resources.domain.CtMediaTemplate;
import com.cloudtimes.resources.service.ICtMediaTemplateService;
import com.cloudtimes.common.utils.poi.ExcelUtil;
import com.cloudtimes.common.core.page.TableDataInfo;

/**
 * 媒体资源模板Controller
 * 
 * @author tank
 * @date 2023-01-17
 */
@RestController
@RequestMapping("/resources/ctmediatemplate")
public class CtMediaTemplateController extends BaseController
{
    @Autowired
    private ICtMediaTemplateService ctMediaTemplateService;

    /**
     * 查询媒体资源模板列表
     */
    @PreAuthorize("@ss.hasPermi('resources:ctmediatemplate:list')")
    @GetMapping("/list")
    public TableDataInfo list(CtMediaTemplate ctMediaTemplate)
    {
        startPage();
        List<CtMediaTemplate> list = ctMediaTemplateService.selectCtMediaTemplateList(ctMediaTemplate);
        return getDataTable(list);
    }

    /**
     * 导出媒体资源模板列表
     */
    @PreAuthorize("@ss.hasPermi('resources:ctmediatemplate:export')")
    @Log(title = "媒体资源模板", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, CtMediaTemplate ctMediaTemplate)
    {
        List<CtMediaTemplate> list = ctMediaTemplateService.selectCtMediaTemplateList(ctMediaTemplate);
        ExcelUtil<CtMediaTemplate> util = new ExcelUtil<CtMediaTemplate>(CtMediaTemplate.class);
        util.exportExcel(response, list, "媒体资源模板数据");
    }

    /**
     * 获取媒体资源模板详细信息
     */
    @PreAuthorize("@ss.hasPermi('resources:ctmediatemplate:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(ctMediaTemplateService.selectCtMediaTemplateById(id));
    }

    /**
     * 新增媒体资源模板
     */
    @PreAuthorize("@ss.hasPermi('resources:ctmediatemplate:add')")
    @Log(title = "媒体资源模板", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody CtMediaTemplate ctMediaTemplate)
    {
        return toAjax(ctMediaTemplateService.insertCtMediaTemplate(ctMediaTemplate));
    }

    /**
     * 修改媒体资源模板
     */
    @PreAuthorize("@ss.hasPermi('resources:ctmediatemplate:edit')")
    @Log(title = "媒体资源模板", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody CtMediaTemplate ctMediaTemplate)
    {
        return toAjax(ctMediaTemplateService.updateCtMediaTemplate(ctMediaTemplate));
    }

    /**
     * 删除媒体资源模板
     */
    @PreAuthorize("@ss.hasPermi('resources:ctmediatemplate:remove')")
    @Log(title = "媒体资源模板", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(ctMediaTemplateService.deleteCtMediaTemplateByIds(ids));
    }
}
