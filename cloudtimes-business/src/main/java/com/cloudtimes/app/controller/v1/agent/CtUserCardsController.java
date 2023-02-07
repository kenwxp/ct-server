package com.cloudtimes.app.controller.v1.agent;

import com.cloudtimes.account.domain.CtUserCards;
import com.cloudtimes.account.service.ICtUserCardsService;
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
 * 卡劵维护Controller
 * 
 * @author 沈兵
 * @date 2023-02-03
 */
@RestController
@RequestMapping("/account/user_cards")
@Api("用户卡劵维护")
public class CtUserCardsController extends BaseController
{
    @Autowired
    private ICtUserCardsService ctUserCardsService;

    /**
     * 查询卡劵维护列表
     */
    @ApiOperation("查询卡劵列表")
    @GetMapping("/list")
    public TableDataInfo list(CtUserCards ctUserCards)
    {
        startPage();
        List<CtUserCards> list = ctUserCardsService.selectCtUserCardsList(ctUserCards);
        return getDataTable(list);
    }

    /**
     * 获取卡劵维护详细信息
     */
    @GetMapping(value = "/{id}")
    @ApiOperation("获取卡劵维护详细信息")
    public AjaxResult getInfo(@PathVariable("id") String id)
    {
        return AjaxResult.success(ctUserCardsService.selectCtUserCardsById(id));
    }

    /**
     * 新增卡劵维护
     */
    @Log(title = "卡劵维护", businessType = BusinessType.INSERT)
    @PostMapping
    @ApiOperation("新增卡劵")
    public AjaxResult add(@RequestBody CtUserCards ctUserCards)
    {
        return toAjax(ctUserCardsService.insertCtUserCards(ctUserCards));
    }

    /**
     * 修改卡劵维护
     */
    @Log(title = "卡劵维护", businessType = BusinessType.UPDATE)
    @PutMapping
    @ApiOperation("修改卡劵")
    public AjaxResult edit(@RequestBody CtUserCards ctUserCards)
    {
        return toAjax(ctUserCardsService.updateCtUserCards(ctUserCards));
    }
}
