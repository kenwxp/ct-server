package com.cloudtimes.web.controller.supervise;

import com.cloudtimes.common.annotation.Log;
import com.cloudtimes.common.core.controller.BaseController;
import com.cloudtimes.common.core.domain.AjaxResult;
import com.cloudtimes.common.core.page.TableDataInfo;
import com.cloudtimes.common.enums.BusinessType;
import com.cloudtimes.common.utils.poi.ExcelUtil;
import com.cloudtimes.supervise.domain.CtCashOrder;
import com.cloudtimes.supervise.service.ICtCashOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 取现订单Controller
 *
 * @author wangxp
 * @date 2023-02-07
 */
@RestController
@RequestMapping("/supervise/cashOrder")
public class CtCashOrderController extends BaseController {
    @Autowired
    private ICtCashOrderService ctCashOrderService;

    /**
     * 查询取现订单列表
     */
    @PreAuthorize("@ss.hasPermi('system:order:list')")
    @GetMapping("/list")
    public TableDataInfo list(CtCashOrder ctCashOrder) {
        startPage();
        List<CtCashOrder> list = ctCashOrderService.selectCtCashOrderList(ctCashOrder);
        return getDataTable(list);
    }

    /**
     * 导出取现订单列表
     */
    @PreAuthorize("@ss.hasPermi('system:order:export')")
    @Log(title = "取现订单", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, CtCashOrder ctCashOrder) {
        List<CtCashOrder> list = ctCashOrderService.selectCtCashOrderList(ctCashOrder);
        ExcelUtil<CtCashOrder> util = new ExcelUtil<CtCashOrder>(CtCashOrder.class);
        util.exportExcel(response, list, "取现订单数据");
    }

    /**
     * 获取取现订单详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:order:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") String id) {
        return AjaxResult.success(ctCashOrderService.selectCtCashOrderById(id));
    }

    /**
     * 新增取现订单
     */
    @PreAuthorize("@ss.hasPermi('system:order:add')")
    @Log(title = "取现订单", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody CtCashOrder ctCashOrder) {
        return toAjax(ctCashOrderService.insertCtCashOrder(ctCashOrder));
    }

    /**
     * 修改取现订单
     */
    @PreAuthorize("@ss.hasPermi('system:order:edit')")
    @Log(title = "取现订单", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody CtCashOrder ctCashOrder) {
        return toAjax(ctCashOrderService.updateCtCashOrder(ctCashOrder));
    }

    /**
     * 删除取现订单
     */
    @PreAuthorize("@ss.hasPermi('system:order:remove')")
    @Log(title = "取现订单", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable String[] ids) {
        return toAjax(ctCashOrderService.deleteCtCashOrderByIds(ids));
    }
}
