package com.cloudtimes.web.controller.store;

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
import com.cloudtimes.store.domain.CtStore;
import com.cloudtimes.store.service.ICtStoreService;
import com.cloudtimes.common.utils.poi.ExcelUtil;
import com.cloudtimes.common.core.page.TableDataInfo;

/**
 * 门店Controller
 * 
 * @author wangxp
 * @date 2023-01-13
 */
@RestController
@RequestMapping("/store/store")
public class CtStoreController extends BaseController
{
    @Autowired
    private ICtStoreService ctStoreService;

    /**
     * 查询门店列表
     */
    @PreAuthorize("@ss.hasPermi('store:store:list')")
    @GetMapping("/list")
    public TableDataInfo list(CtStore ctStore)
    {
        startPage();
        List<CtStore> list = ctStoreService.selectCtStoreList(ctStore);
        return getDataTable(list);
    }

    /**
     * 导出门店列表
     */
    @PreAuthorize("@ss.hasPermi('store:store:export')")
    @Log(title = "门店", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, CtStore ctStore)
    {
        List<CtStore> list = ctStoreService.selectCtStoreList(ctStore);
        ExcelUtil<CtStore> util = new ExcelUtil<CtStore>(CtStore.class);
        util.exportExcel(response, list, "门店数据");
    }

    /**
     * 获取门店详细信息
     */
    @PreAuthorize("@ss.hasPermi('store:store:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(ctStoreService.selectCtStoreById(id));
    }

    /**
     * 新增门店
     */
    @PreAuthorize("@ss.hasPermi('store:store:add')")
    @Log(title = "门店", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody CtStore ctStore)
    {
        return toAjax(ctStoreService.insertCtStore(ctStore));
    }

    /**
     * 修改门店
     */
    @PreAuthorize("@ss.hasPermi('store:store:edit')")
    @Log(title = "门店", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody CtStore ctStore)
    {
        return toAjax(ctStoreService.updateCtStore(ctStore));
    }

    /**
     * 删除门店
     */
    @PreAuthorize("@ss.hasPermi('store:store:remove')")
    @Log(title = "门店", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(ctStoreService.deleteCtStoreByIds(ids));
    }
}
