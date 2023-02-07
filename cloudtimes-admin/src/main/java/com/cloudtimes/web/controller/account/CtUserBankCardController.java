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
import com.cloudtimes.account.domain.CtUserBankCard;
import com.cloudtimes.account.service.ICtUserBankCardService;
import com.cloudtimes.common.utils.poi.ExcelUtil;
import com.cloudtimes.common.core.page.TableDataInfo;

/**
 * 用户银行卡Controller
 * 
 * @author 沈兵
 * @date 2023-02-03
 */
@RestController
@RequestMapping("/account/bank_card")
public class CtUserBankCardController extends BaseController
{
    @Autowired
    private ICtUserBankCardService ctUserBankCardService;

    /**
     * 查询用户银行卡列表
     */
    @PreAuthorize("@ss.hasPermi('account:bank_card:list')")
    @GetMapping("/list")
    public TableDataInfo list(CtUserBankCard ctUserBankCard)
    {
        startPage();
        List<CtUserBankCard> list = ctUserBankCardService.selectCtUserBankCardList(ctUserBankCard);
        return getDataTable(list);
    }

    /**
     * 导出用户银行卡列表
     */
    @PreAuthorize("@ss.hasPermi('account:bank_card:export')")
    @Log(title = "用户银行卡", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, CtUserBankCard ctUserBankCard)
    {
        List<CtUserBankCard> list = ctUserBankCardService.selectCtUserBankCardList(ctUserBankCard);
        ExcelUtil<CtUserBankCard> util = new ExcelUtil<CtUserBankCard>(CtUserBankCard.class);
        util.exportExcel(response, list, "用户银行卡数据");
    }

    /**
     * 获取用户银行卡详细信息
     */
    @PreAuthorize("@ss.hasPermi('account:bank_card:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") String id)
    {
        return AjaxResult.success(ctUserBankCardService.selectCtUserBankCardById(id));
    }

    /**
     * 修改用户银行卡
     */
    @PreAuthorize("@ss.hasPermi('account:bank_card:edit')")
    @Log(title = "用户银行卡", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody CtUserBankCard ctUserBankCard)
    {
        return toAjax(ctUserBankCardService.updateCtUserBankCard(ctUserBankCard));
    }

    /**
     * 删除用户银行卡
     */
    @PreAuthorize("@ss.hasPermi('account:bank_card:remove')")
    @Log(title = "用户银行卡", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable String[] ids)
    {
        return toAjax(ctUserBankCardService.deleteCtUserBankCardByIds(ids));
    }
}
