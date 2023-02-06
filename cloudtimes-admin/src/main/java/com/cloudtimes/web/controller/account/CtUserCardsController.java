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
import com.cloudtimes.account.domain.CtUserCards;
import com.cloudtimes.account.service.ICtUserCardsService;
import com.cloudtimes.common.utils.poi.ExcelUtil;
import com.cloudtimes.common.core.page.TableDataInfo;

/**
 * 卡劵维护Controller
 * 
 * @author 沈兵
 * @date 2023-02-03
 */
@RestController
@RequestMapping("/account/user_cards")
public class CtUserCardsController extends BaseController
{
    @Autowired
    private ICtUserCardsService ctUserCardsService;

    /**
     * 查询卡劵维护列表
     */
    @PreAuthorize("@ss.hasPermi('account:user_cards:list')")
    @GetMapping("/list")
    public TableDataInfo list(CtUserCards ctUserCards)
    {
        startPage();
        List<CtUserCards> list = ctUserCardsService.selectCtUserCardsList(ctUserCards);
        return getDataTable(list);
    }

    /**
     * 导出卡劵维护列表
     */
    @PreAuthorize("@ss.hasPermi('account:user_cards:export')")
    @Log(title = "卡劵维护", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, CtUserCards ctUserCards)
    {
        List<CtUserCards> list = ctUserCardsService.selectCtUserCardsList(ctUserCards);
        ExcelUtil<CtUserCards> util = new ExcelUtil<CtUserCards>(CtUserCards.class);
        util.exportExcel(response, list, "卡劵维护数据");
    }

    /**
     * 获取卡劵维护详细信息
     */
    @PreAuthorize("@ss.hasPermi('account:user_cards:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") String id)
    {
        return AjaxResult.success(ctUserCardsService.selectCtUserCardsById(id));
    }

    /**
     * 新增卡劵维护
     */
    @PreAuthorize("@ss.hasPermi('account:user_cards:add')")
    @Log(title = "卡劵维护", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody CtUserCards ctUserCards)
    {
        return toAjax(ctUserCardsService.insertCtUserCards(ctUserCards));
    }

    /**
     * 修改卡劵维护
     */
    @PreAuthorize("@ss.hasPermi('account:user_cards:edit')")
    @Log(title = "卡劵维护", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody CtUserCards ctUserCards)
    {
        return toAjax(ctUserCardsService.updateCtUserCards(ctUserCards));
    }

    /**
     * 删除卡劵维护
     */
    @PreAuthorize("@ss.hasPermi('account:user_cards:remove')")
    @Log(title = "卡劵维护", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable String[] ids)
    {
        return toAjax(ctUserCardsService.deleteCtUserCardsByIds(ids));
    }
}
