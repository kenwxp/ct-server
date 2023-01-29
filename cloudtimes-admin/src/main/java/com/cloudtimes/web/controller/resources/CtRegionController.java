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
import com.cloudtimes.resources.domain.CtRegion;
import com.cloudtimes.resources.service.ICtRegionService;
import com.cloudtimes.common.utils.poi.ExcelUtil;
import com.cloudtimes.common.core.page.TableDataInfo;

/**
 * 地区信息Controller
 * 
 * @author tank
 * @date 2023-01-17
 */
@RestController
@RequestMapping("/resources/ctregion")
public class CtRegionController extends BaseController
{
    @Autowired
    private ICtRegionService ctRegionService;

    /**
     * 查询地区信息列表
     */
    @PreAuthorize("@ss.hasPermi('resources:ctregion:list')")
    @GetMapping("/list")
    public TableDataInfo list(CtRegion ctRegion)
    {
        startPage();
        List<CtRegion> list = ctRegionService.selectCtRegionList(ctRegion);
        return getDataTable(list);
    }

    /**
     * 导出地区信息列表
     */
    @PreAuthorize("@ss.hasPermi('resources:ctregion:export')")
    @Log(title = "地区信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, CtRegion ctRegion)
    {
        List<CtRegion> list = ctRegionService.selectCtRegionList(ctRegion);
        ExcelUtil<CtRegion> util = new ExcelUtil<CtRegion>(CtRegion.class);
        util.exportExcel(response, list, "地区信息数据");
    }

    /**
     * 获取地区信息详细信息
     */
    @PreAuthorize("@ss.hasPermi('resources:ctregion:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(ctRegionService.selectCtRegionById(id));
    }

    /**
     * 新增地区信息
     */
    @PreAuthorize("@ss.hasPermi('resources:ctregion:add')")
    @Log(title = "地区信息", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody CtRegion ctRegion)
    {
        return toAjax(ctRegionService.insertCtRegion(ctRegion));
    }

    /**
     * 修改地区信息
     */
    @PreAuthorize("@ss.hasPermi('resources:ctregion:edit')")
    @Log(title = "地区信息", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody CtRegion ctRegion)
    {
        return toAjax(ctRegionService.updateCtRegion(ctRegion));
    }

    /**
     * 删除地区信息
     */
    @PreAuthorize("@ss.hasPermi('resources:ctregion:remove')")
    @Log(title = "地区信息", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(ctRegionService.deleteCtRegionByIds(ids));
    }
}
