package com.cloudtimes.web.controller.supervise;

import com.cloudtimes.common.annotation.Log;
import com.cloudtimes.common.core.controller.BaseController;
import com.cloudtimes.common.core.domain.AjaxResult;
import com.cloudtimes.common.core.page.TableDataInfo;
import com.cloudtimes.common.enums.BusinessType;
import com.cloudtimes.common.utils.poi.ExcelUtil;
import com.cloudtimes.supervise.domain.CtOrderDetail;
import com.cloudtimes.supervise.service.ICtOrderDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 订单物品清单Controller
 *
 * @author tank
 * @date 2023-02-07
 */
@RestController
@RequestMapping("/supervise/detail")
public class CtOrderDetailController extends BaseController {
    @Autowired
    private ICtOrderDetailService ctOrderDetailService;

    /**
     * 查询订单物品清单列表
     */
    @PreAuthorize("@ss.hasPermi('supervise:detail:list')")
    @GetMapping("/list")
    public TableDataInfo list(CtOrderDetail ctOrderDetail) {
        startPage();
        List<CtOrderDetail> list = ctOrderDetailService.selectCtOrderDetailList(ctOrderDetail);
        return getDataTable(list);
    }

    /**
     * 导出订单物品清单列表
     */
    @PreAuthorize("@ss.hasPermi('supervise:detail:export')")
    @Log(title = "订单物品清单", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, CtOrderDetail ctOrderDetail) {
        List<CtOrderDetail> list = ctOrderDetailService.selectCtOrderDetailList(ctOrderDetail);
        ExcelUtil<CtOrderDetail> util = new ExcelUtil<CtOrderDetail>(CtOrderDetail.class);
        util.exportExcel(response, list, "订单物品清单数据");
    }

    /**
     * 获取订单物品清单详细信息
     */
    @PreAuthorize("@ss.hasPermi('supervise:detail:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") String id) {
        return AjaxResult.success(ctOrderDetailService.selectCtOrderDetailById(id));
    }

    /**
     * 新增订单物品清单
     */
    @PreAuthorize("@ss.hasPermi('supervise:detail:add')")
    @Log(title = "订单物品清单", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody CtOrderDetail ctOrderDetail) {
        return toAjax(ctOrderDetailService.insertCtOrderDetail(ctOrderDetail));
    }

    /**
     * 修改订单物品清单
     */
    @PreAuthorize("@ss.hasPermi('supervise:detail:edit')")
    @Log(title = "订单物品清单", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody CtOrderDetail ctOrderDetail) {
        return toAjax(ctOrderDetailService.updateCtOrderDetail(ctOrderDetail));
    }

    /**
     * 删除订单物品清单
     */
    @PreAuthorize("@ss.hasPermi('supervise:detail:remove')")
    @Log(title = "订单物品清单", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable String[] ids) {
        return toAjax(ctOrderDetailService.deleteCtOrderDetailByIds(ids));
    }
}
