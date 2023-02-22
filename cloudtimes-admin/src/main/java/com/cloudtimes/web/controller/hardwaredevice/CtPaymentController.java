package com.cloudtimes.web.controller.hardwaredevice;

import com.cloudtimes.common.annotation.Log;
import com.cloudtimes.common.core.controller.BaseController;
import com.cloudtimes.common.core.domain.AjaxResult;
import com.cloudtimes.common.core.page.TableDataInfo;
import com.cloudtimes.common.enums.BusinessType;

import com.cloudtimes.common.utils.poi.ExcelUtil;
import com.cloudtimes.hardwaredevice.domain.CtPayment;
import com.cloudtimes.hardwaredevice.service.ICtPaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 支付渠道Controller
 * 
 * @author tank
 * @date 2023-02-22
 */
@RestController
@RequestMapping("/hardwaredevice/payment")
public class CtPaymentController extends BaseController
{
    @Autowired
    private ICtPaymentService ctPaymentService;

    /**
     * 查询支付渠道列表
     */
    @PreAuthorize("@ss.hasPermi('hardwaredevice:payment:list')")
    @GetMapping("/list")
    public TableDataInfo list(CtPayment ctPayment)
    {
        startPage();
        List<CtPayment> list = ctPaymentService.selectCtPaymentList(ctPayment);
        return getDataTable(list);
    }

    /**
     * 导出支付渠道列表
     */
    @PreAuthorize("@ss.hasPermi('hardwaredevice:payment:export')")
    @Log(title = "支付渠道", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, CtPayment ctPayment)
    {
        List<CtPayment> list = ctPaymentService.selectCtPaymentList(ctPayment);
        ExcelUtil<CtPayment> util = new ExcelUtil<CtPayment>(CtPayment.class);
        util.exportExcel(response, list, "支付渠道数据");
    }

    /**
     * 获取支付渠道详细信息
     */
    @PreAuthorize("@ss.hasPermi('hardwaredevice:payment:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") String id)
    {
        return AjaxResult.success(ctPaymentService.selectCtPaymentById(id));
    }

    /**
     * 新增支付渠道
     */
    @PreAuthorize("@ss.hasPermi('hardwaredevice:payment:add')")
    @Log(title = "支付渠道", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody CtPayment ctPayment)
    {
        return toAjax(ctPaymentService.insertCtPayment(ctPayment));
    }

    /**
     * 修改支付渠道
     */
    @PreAuthorize("@ss.hasPermi('hardwaredevice:payment:edit')")
    @Log(title = "支付渠道", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody CtPayment ctPayment)
    {
        return toAjax(ctPaymentService.updateCtPayment(ctPayment));
    }

    /**
     * 删除支付渠道
     */
    @PreAuthorize("@ss.hasPermi('hardwaredevice:payment:remove')")
    @Log(title = "支付渠道", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable String[] ids)
    {
        return toAjax(ctPaymentService.deleteCtPaymentByIds(ids));
    }
}
