package com.cloudtimes.web.controller.account;

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
import com.cloudtimes.account.domain.CtWithdrawalBook;
import com.cloudtimes.account.service.ICtWithdrawalBookService;
import com.cloudtimes.common.utils.poi.ExcelUtil;
import com.cloudtimes.common.core.page.TableDataInfo;

/**
 * 提现登记薄Controller
 * 
 * @author 沈兵
 * @date 2023-02-03
 */
@RestController
@RequestMapping("/account/withdrawal_book")
public class CtWithdrawalBookController extends BaseController
{
    @Autowired
    private ICtWithdrawalBookService ctWithdrawalBookService;

    /**
     * 查询提现登记薄列表
     */
    @PreAuthorize("@ss.hasPermi('account:withdrawal_book:list')")
    @GetMapping("/list")
    public TableDataInfo list(CtWithdrawalBook ctWithdrawalBook)
    {
        startPage();
        List<CtWithdrawalBook> list = ctWithdrawalBookService.selectCtWithdrawalBookList(ctWithdrawalBook);
        return getDataTable(list);
    }

    /**
     * 导出提现登记薄列表
     */
    @PreAuthorize("@ss.hasPermi('account:withdrawal_book:export')")
    @Log(title = "提现登记薄", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, CtWithdrawalBook ctWithdrawalBook)
    {
        List<CtWithdrawalBook> list = ctWithdrawalBookService.selectCtWithdrawalBookList(ctWithdrawalBook);
        ExcelUtil<CtWithdrawalBook> util = new ExcelUtil<CtWithdrawalBook>(CtWithdrawalBook.class);
        util.exportExcel(response, list, "提现登记薄数据");
    }

    /**
     * 获取提现登记薄详细信息
     */
    @PreAuthorize("@ss.hasPermi('account:withdrawal_book:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") String id)
    {
        return AjaxResult.success(ctWithdrawalBookService.selectCtWithdrawalBookById(id));
    }

    /**
     * 新增提现登记薄
     */
    @PreAuthorize("@ss.hasPermi('account:withdrawal_book:add')")
    @Log(title = "提现登记薄", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody CtWithdrawalBook ctWithdrawalBook)
    {
        return toAjax(ctWithdrawalBookService.insertCtWithdrawalBook(ctWithdrawalBook));
    }

    /**
     * 修改提现登记薄
     */
    @PreAuthorize("@ss.hasPermi('account:withdrawal_book:edit')")
    @Log(title = "提现登记薄", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody CtWithdrawalBook ctWithdrawalBook)
    {
        return toAjax(ctWithdrawalBookService.updateCtWithdrawalBook(ctWithdrawalBook));
    }

    /**
     * 删除提现登记薄
     */
    @PreAuthorize("@ss.hasPermi('account:withdrawal_book:remove')")
    @Log(title = "提现登记薄", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable String[] ids)
    {
        return toAjax(ctWithdrawalBookService.deleteCtWithdrawalBookByIds(ids));
    }
}
