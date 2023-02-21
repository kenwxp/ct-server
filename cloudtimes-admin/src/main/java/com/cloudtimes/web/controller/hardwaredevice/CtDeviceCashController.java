package com.cloudtimes.web.controller.hardwaredevice;

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
import com.cloudtimes.hardwaredevice.domain.CtDeviceCash;
import com.cloudtimes.hardwaredevice.service.ICtDeviceCashService;
import com.cloudtimes.common.utils.poi.ExcelUtil;
import com.cloudtimes.common.core.page.TableDataInfo;

/**
 * 收银机特有信息Controller
 * 
 * @author tank
 * @date 2023-01-17
 */
@RestController
@RequestMapping("/hardwaredevice/ctdevicecash")
public class CtDeviceCashController extends BaseController
{
    @Autowired
    private ICtDeviceCashService ctDeviceCashService;

    /**
     * 查询收银机特有信息列表
     */
    @PreAuthorize("@ss.hasPermi('hardwaredevice:ctdevicecash:list')")
    @GetMapping("/list")
    public TableDataInfo list(CtDeviceCash ctDeviceCash)
    {
        startPage();
        List<CtDeviceCash> list = ctDeviceCashService.selectCtDeviceCashList(ctDeviceCash);
        return getDataTable(list);
    }

    /**
     * 导出收银机特有信息列表
     */
    @PreAuthorize("@ss.hasPermi('hardwaredevice:ctdevicecash:export')")
    @Log(title = "收银机特有信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, CtDeviceCash ctDeviceCash)
    {
        List<CtDeviceCash> list = ctDeviceCashService.selectCtDeviceCashList(ctDeviceCash);
        ExcelUtil<CtDeviceCash> util = new ExcelUtil<CtDeviceCash>(CtDeviceCash.class);
        util.exportExcel(response, list, "收银机特有信息数据");
    }

    /**
     * 获取收银机特有信息详细信息
     */
    @PreAuthorize("@ss.hasPermi('hardwaredevice:ctdevicecash:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") String id)
    {
        return AjaxResult.success(ctDeviceCashService.selectCtDeviceCashById(id));
    }

    /**
     * 新增收银机特有信息
     */
    @PreAuthorize("@ss.hasPermi('hardwaredevice:ctdevicecash:add')")
    @Log(title = "收银机特有信息", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody CtDeviceCash ctDeviceCash)
    {
        return toAjax(ctDeviceCashService.insertCtDeviceCash(ctDeviceCash));
    }

    /**
     * 修改收银机特有信息
     */
    @PreAuthorize("@ss.hasPermi('hardwaredevice:ctdevicecash:edit')")
    @Log(title = "收银机特有信息", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody CtDeviceCash ctDeviceCash)
    {
        return toAjax(ctDeviceCashService.updateCtDeviceCash(ctDeviceCash));
    }

    /**
     * 删除收银机特有信息
     */
    @PreAuthorize("@ss.hasPermi('hardwaredevice:ctdevicecash:remove')")
    @Log(title = "收银机特有信息", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable String[] ids)
    {
        return toAjax(ctDeviceCashService.deleteCtDeviceCashByIds(ids));
    }
}
