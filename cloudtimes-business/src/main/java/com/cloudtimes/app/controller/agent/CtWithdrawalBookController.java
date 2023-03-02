package com.cloudtimes.app.controller.agent;

import com.cloudtimes.account.domain.CtWithdrawalBook;
import com.cloudtimes.account.service.ICtWithdrawalBookService;
import com.cloudtimes.app.constant.PrefixPathConstants;
import com.cloudtimes.common.annotation.Log;
import com.cloudtimes.common.core.controller.BaseController;
import com.cloudtimes.common.core.domain.AjaxResult;
import com.cloudtimes.common.core.page.TableDataInfo;
import com.cloudtimes.common.enums.BusinessType;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 提现登记簿Controller
 * 
 * @author 沈兵
 * @date 2023-02-03
 */
@RestController
@RequestMapping(PrefixPathConstants.WX_OFFICIAL_PATH_PREFIX + "/withdrawal_book")
@Api(tags = "代理-提现登记簿")
public class CtWithdrawalBookController extends BaseController
{
    @Autowired
    private ICtWithdrawalBookService ctWithdrawalBookService;

    /**
     * 查询提现登记簿列表
     */
    @GetMapping("/list")
    @ApiOperation("查询提现登记簿列表")
    public TableDataInfo list(CtWithdrawalBook ctWithdrawalBook)
    {
        startPage();
        List<CtWithdrawalBook> list = ctWithdrawalBookService.selectCtWithdrawalBookList(ctWithdrawalBook);
        return getDataTable(list);
    }

    /**
     * 获取提现登记簿详细信息
     */
    @ApiOperation("获取提现登记簿详细信息")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") String id)
    {
        return AjaxResult.success(ctWithdrawalBookService.selectCtWithdrawalBookById(id));
    }

    /**
     * 新增提现登记簿
     */
    @Log(title = "提现登记簿", businessType = BusinessType.INSERT)
    @PostMapping
    @ApiOperation("新增提现")
    public AjaxResult add(@RequestBody CtWithdrawalBook ctWithdrawalBook)
    {
        return toAjax(ctWithdrawalBookService.insertCtWithdrawalBook(ctWithdrawalBook));
    }

    /**
     * 修改提现登记簿
     */
    @Log(title = "提现登记簿", businessType = BusinessType.UPDATE)
    @PutMapping
    @ApiOperation("修改提现")
    public AjaxResult edit(@RequestBody CtWithdrawalBook ctWithdrawalBook)
    {
        return toAjax(ctWithdrawalBookService.updateCtWithdrawalBook(ctWithdrawalBook));
    }
}
