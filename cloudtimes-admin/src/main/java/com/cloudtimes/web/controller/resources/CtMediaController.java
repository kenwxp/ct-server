package com.cloudtimes.web.controller.resources;

import java.util.List;
import javax.servlet.http.HttpServletResponse;

import com.cloudtimes.common.utils.SecurityUtils;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
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
import com.cloudtimes.resources.domain.CtMedia;
import com.cloudtimes.resources.service.ICtMediaService;
import com.cloudtimes.common.utils.poi.ExcelUtil;
import com.cloudtimes.common.core.page.TableDataInfo;

/**
 * 媒体Controller
 *
 * @author tank
 * @date 2023-02-10
 */
@Tag(name = "媒体")
@RestController
@RequestMapping("/resources/ctmedia")
public class CtMediaController extends BaseController {
    @Autowired
    private ICtMediaService ctMediaService;

    /**
     * 查询媒体列表
     */
    @PreAuthorize("@ss.hasPermi('resources:ctmedia:list')")
    @GetMapping("/list")
    public TableDataInfo list(CtMedia ctMedia) {
        startPage();
        List<CtMedia> list = ctMediaService.selectCtMediaList(ctMedia);
        return getDataTable(list);
    }

    /**
     * 导出媒体列表
     */
    @PreAuthorize("@ss.hasPermi('resources:ctmedia:export')")
    @Log(title = "媒体", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, CtMedia ctMedia) {
        List<CtMedia> list = ctMediaService.selectCtMediaList(ctMedia);
        ExcelUtil<CtMedia> util = new ExcelUtil<CtMedia>(CtMedia.class);
        util.exportExcel(response, list, "媒体数据");
    }

    /**
     * 获取媒体详细信息
     */
    @Operation(summary = "获取媒体详情-resources:ctmedia:query")
    @PreAuthorize("@ss.hasPermi('resources:ctmedia:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") String id) {
        return AjaxResult.success(ctMediaService.selectCtMediaById(id));
    }

    /**
     * 新增媒体
     */
    @PreAuthorize("@ss.hasPermi('resources:ctmedia:add')")
    @Log(title = "媒体", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody CtMedia ctMedia) {
        return toAjax(ctMediaService.insertCtMedia(ctMedia));
    }

    /**
     * 修改媒体
     */
    @PreAuthorize("@ss.hasPermi('resources:ctmedia:edit')")
    @Log(title = "媒体", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody CtMedia ctMedia) {
        return toAjax(ctMediaService.updateCtMedia(ctMedia));
    }

    /**
     * 删除媒体
     */
    @PreAuthorize("@ss.hasPermi('resources:ctmedia:remove')")
    @Log(title = "媒体", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable String[] ids) {
        return toAjax(ctMediaService.deleteCtMediaByIds(ids));
    }
}
