package com.cloudtimes.web.controller.supervise;

import java.util.List;
import javax.servlet.http.HttpServletResponse;

import com.cloudtimes.common.utils.poi.ExcelUtil;
import com.cloudtimes.supervise.domain.CtOrderDetail;
import com.cloudtimes.supervise.service.ICtOrderDetailService;
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

import com.cloudtimes.common.core.page.TableDataInfo;

/**
 * 订单物品清单Controller
 * 
 * @author tank
 * @date 2023-02-17
 */
@RestController
@RequestMapping("/system/detail")
public class CtOrderDetailController extends BaseController
{
    @Autowired
    private ICtOrderDetailService ctOrderDetailService;

    /**
     * 查询订单物品清单列表
     */
    @PreAuthorize("@ss.hasPermi('system:detail:list')")
    @GetMapping("/list")
    public TableDataInfo list(CtOrderDetail ctOrderDetail)
    {
        startPage();
        List<CtOrderDetail> list = ctOrderDetailService.selectCtOrderDetailList(ctOrderDetail);
        return getDataTable(list);
    }

    /**
     * 导出订单物品清单列表
     */
    @PreAuthorize("@ss.hasPermi('system:detail:export')")
    @Log(title = "订单物品清单", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, CtOrderDetail ctOrderDetail)
    {
        List<CtOrderDetail> list = ctOrderDetailService.selectCtOrderDetailList(ctOrderDetail);
        ExcelUtil<CtOrderDetail> util = new ExcelUtil<CtOrderDetail>(CtOrderDetail.class);
        util.exportExcel(response, list, "订单物品清单数据");
    }

    /**
     * 获取订单物品清单详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:detail:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") String id)
    {
        return AjaxResult.success(ctOrderDetailService.selectCtOrderDetailById(id));
    }

    /**
     * 新增订单物品清单
     */
    @PreAuthorize("@ss.hasPermi('system:detail:add')")
    @Log(title = "订单物品清单", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody CtOrderDetail ctOrderDetail)
    {
        return toAjax(ctOrderDetailService.insertCtOrderDetail(ctOrderDetail));
    }

    /**
     * 修改订单物品清单
     */
    @PreAuthorize("@ss.hasPermi('system:detail:edit')")
    @Log(title = "订单物品清单", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody CtOrderDetail ctOrderDetail)
    {
        return toAjax(ctOrderDetailService.updateCtOrderDetail(ctOrderDetail));
    }

    /**
     * 删除订单物品清单
     */
    @PreAuthorize("@ss.hasPermi('system:detail:remove')")
    @Log(title = "订单物品清单", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable String[] ids)
    {
        return toAjax(ctOrderDetailService.deleteCtOrderDetailByIds(ids));
    }
}
