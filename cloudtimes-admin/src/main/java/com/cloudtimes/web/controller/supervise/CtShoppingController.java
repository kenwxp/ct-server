package com.cloudtimes.web.controller.supervise;

import java.util.List;
import javax.servlet.http.HttpServletResponse;

import com.cloudtimes.supervise.domain.CtShopping;
import com.cloudtimes.supervise.service.ICtShoppingService;
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

import com.cloudtimes.common.utils.poi.ExcelUtil;
import com.cloudtimes.common.core.page.TableDataInfo;

/**
 * 购物Controller
 *
 * @author wangxp
 * @date 2023-02-07
 */
@RestController
@RequestMapping("/supervise/shopping")
public class CtShoppingController extends BaseController {
    @Autowired
    private ICtShoppingService ctShoppingService;

    /**
     * 查询购物列表
     */
    @PreAuthorize("@ss.hasPermi('supervise:shopping:list')")
    @GetMapping("/list")
    public TableDataInfo list(CtShopping ctShopping) {
        startPage();
        List<CtShopping> list = ctShoppingService.selectCtShoppingList(ctShopping);
        return getDataTable(list);
    }

    /**
     * 导出购物列表
     */
    @PreAuthorize("@ss.hasPermi('supervise:shopping:export')")
    @Log(title = "购物", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, CtShopping ctShopping) {
        List<CtShopping> list = ctShoppingService.selectCtShoppingList(ctShopping);
        ExcelUtil<CtShopping> util = new ExcelUtil<CtShopping>(CtShopping.class);
        util.exportExcel(response, list, "购物数据");
    }

    /**
     * 获取购物详细信息
     */
    @PreAuthorize("@ss.hasPermi('supervise:shopping:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") String id) {
        return AjaxResult.success(ctShoppingService.selectCtShoppingById(id));
    }

    /**
     * 新增购物
     */
    @PreAuthorize("@ss.hasPermi('supervise:shopping:add')")
    @Log(title = "购物", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody CtShopping ctShopping) {
        return toAjax(ctShoppingService.insertCtShopping(ctShopping));
    }

    /**
     * 修改购物
     */
    @PreAuthorize("@ss.hasPermi('supervise:shopping:edit')")
    @Log(title = "购物", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody CtShopping ctShopping) {
        return toAjax(ctShoppingService.updateCtShopping(ctShopping));
    }

    /**
     * 删除购物
     */
    @PreAuthorize("@ss.hasPermi('supervise:shopping:remove')")
    @Log(title = "购物", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable String[] ids) {
        return toAjax(ctShoppingService.deleteCtShoppingByIds(ids));
    }
}
