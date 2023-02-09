package com.cloudtimes.web.controller.supervise;

import com.cloudtimes.common.annotation.Log;
import com.cloudtimes.common.core.controller.BaseController;
import com.cloudtimes.common.core.domain.AjaxResult;
import com.cloudtimes.common.core.page.TableDataInfo;
import com.cloudtimes.common.enums.BusinessType;
import com.cloudtimes.common.utils.poi.ExcelUtil;
import com.cloudtimes.supervise.domain.CtOrder;
import com.cloudtimes.supervise.service.ICtOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 购物订单Controller
 *
 * @author tank
 * @date 2023-02-07
 */
@RestController
@RequestMapping("/supervise/order")
public class CtOrderController extends BaseController {
    @Autowired
    private ICtOrderService ctOrderService;

    /**
     * 查询购物订单列表
     */
    @PreAuthorize("@ss.hasPermi('supervise:order:list')")
    @GetMapping("/list")
    public TableDataInfo list(CtOrder ctOrder) {
        startPage();
        List<CtOrder> list = ctOrderService.selectCtOrderList(ctOrder);
        return getDataTable(list);
    }

    /**
     * 导出购物订单列表
     */
    @PreAuthorize("@ss.hasPermi('supervise:order:export')")
    @Log(title = "购物订单", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, CtOrder ctOrder) {
        List<CtOrder> list = ctOrderService.selectCtOrderList(ctOrder);
        ExcelUtil<CtOrder> util = new ExcelUtil<CtOrder>(CtOrder.class);
        util.exportExcel(response, list, "购物订单数据");
    }

    /**
     * 获取购物订单详细信息
     */
    @PreAuthorize("@ss.hasPermi('supervise:order:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") String id) {
        return AjaxResult.success(ctOrderService.selectCtOrderById(id));
    }

    /**
     * 新增购物订单
     */
    @PreAuthorize("@ss.hasPermi('supervise:order:add')")
    @Log(title = "购物订单", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody CtOrder ctOrder) {
        return toAjax(ctOrderService.insertCtOrder(ctOrder));
    }

    /**
     * 修改购物订单
     */
    @PreAuthorize("@ss.hasPermi('supervise:order:edit')")
    @Log(title = "购物订单", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody CtOrder ctOrder) {
        return toAjax(ctOrderService.updateCtOrder(ctOrder));
    }

    /**
     * 删除购物订单
     */
    @PreAuthorize("@ss.hasPermi('supervise:order:remove')")
    @Log(title = "购物订单", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable String[] ids) {
        return toAjax(ctOrderService.deleteCtOrderByIds(ids));
    }
}
