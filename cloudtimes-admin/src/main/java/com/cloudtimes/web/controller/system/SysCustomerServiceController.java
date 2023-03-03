package com.cloudtimes.web.controller.system;

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
import com.cloudtimes.system.domain.SysCustomerService;
import com.cloudtimes.system.service.ISysCustomerServiceService;
import com.cloudtimes.common.utils.poi.ExcelUtil;
import com.cloudtimes.common.core.page.TableDataInfo;

/**
 * 客服特性参数Controller
 * 
 * @author wangxp
 * @date 2023-03-03
 */
@RestController
@RequestMapping("/system/service")
public class SysCustomerServiceController extends BaseController
{
    @Autowired
    private ISysCustomerServiceService sysCustomerServiceService;

    /**
     * 查询客服特性参数列表
     */
    @PreAuthorize("@ss.hasPermi('system:service:list')")
    @GetMapping("/list")
    public TableDataInfo list(SysCustomerService sysCustomerService)
    {
        startPage();
        List<SysCustomerService> list = sysCustomerServiceService.selectSysCustomerServiceList(sysCustomerService);
        return getDataTable(list);
    }

    /**
     * 导出客服特性参数列表
     */
    @PreAuthorize("@ss.hasPermi('system:service:export')")
    @Log(title = "客服特性参数", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, SysCustomerService sysCustomerService)
    {
        List<SysCustomerService> list = sysCustomerServiceService.selectSysCustomerServiceList(sysCustomerService);
        ExcelUtil<SysCustomerService> util = new ExcelUtil<SysCustomerService>(SysCustomerService.class);
        util.exportExcel(response, list, "客服特性参数数据");
    }

    /**
     * 获取客服特性参数详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:service:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") String id)
    {
        return AjaxResult.success(sysCustomerServiceService.selectSysCustomerServiceById(id));
    }

    /**
     * 新增客服特性参数
     */
    @PreAuthorize("@ss.hasPermi('system:service:add')")
    @Log(title = "客服特性参数", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody SysCustomerService sysCustomerService)
    {
        return toAjax(sysCustomerServiceService.insertSysCustomerService(sysCustomerService));
    }

    /**
     * 修改客服特性参数
     */
    @PreAuthorize("@ss.hasPermi('system:service:edit')")
    @Log(title = "客服特性参数", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody SysCustomerService sysCustomerService)
    {
        return toAjax(sysCustomerServiceService.updateSysCustomerService(sysCustomerService));
    }

    /**
     * 删除客服特性参数
     */
    @PreAuthorize("@ss.hasPermi('system:service:remove')")
    @Log(title = "客服特性参数", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable String[] ids)
    {
        return toAjax(sysCustomerServiceService.deleteSysCustomerServiceByIds(ids));
    }
}
