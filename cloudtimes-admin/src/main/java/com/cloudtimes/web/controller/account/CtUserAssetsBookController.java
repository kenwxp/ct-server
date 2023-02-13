package com.cloudtimes.web.controller.account;

import java.util.List;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.cloudtimes.common.annotation.Log;
import com.cloudtimes.common.core.controller.BaseController;
import com.cloudtimes.common.core.domain.AjaxResult;
import com.cloudtimes.common.enums.BusinessType;
import com.cloudtimes.account.domain.CtUserAssetsBook;
import com.cloudtimes.account.service.ICtUserAssetsBookService;
import com.cloudtimes.common.utils.poi.ExcelUtil;
import com.cloudtimes.common.core.page.TableDataInfo;

/**
 * 用户资产簿记Controller
 * 
 * @author 沈兵
 * @date 2023-02-07
 */
@RestController
@RequestMapping("/account/assets_book")
public class CtUserAssetsBookController extends BaseController
{
    @Autowired
    private ICtUserAssetsBookService ctUserAssetsBookService;

    /**
     * 查询用户资产簿记列表
     */
    @PreAuthorize("@ss.hasPermi('account:assets_book:list')")
    @GetMapping("/list")
    public TableDataInfo list(CtUserAssetsBook ctUserAssetsBook)
    {
        startPage();
        List<CtUserAssetsBook> list = ctUserAssetsBookService.selectCtUserAssetsBookList(ctUserAssetsBook);
        return getDataTable(list);
    }

    /**
     * 导出用户资产簿记列表
     */
    @PreAuthorize("@ss.hasPermi('account:assets_book:export')")
    @Log(title = "用户资产簿记", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, CtUserAssetsBook ctUserAssetsBook)
    {
        List<CtUserAssetsBook> list = ctUserAssetsBookService.selectCtUserAssetsBookList(ctUserAssetsBook);
        ExcelUtil<CtUserAssetsBook> util = new ExcelUtil<CtUserAssetsBook>(CtUserAssetsBook.class);
        util.exportExcel(response, list, "用户资产簿记数据");
    }

    /**
     * 获取用户资产簿记详细信息
     */
    @PreAuthorize("@ss.hasPermi('account:assets_book:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") String id)
    {
        return AjaxResult.success(ctUserAssetsBookService.selectCtUserAssetsBookById(id));
    }
}
