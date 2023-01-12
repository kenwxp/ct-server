package com.cloudtimes.app.controller;

import com.cloudtimes.common.core.controller.BaseController;
import com.cloudtimes.common.core.domain.AjaxResult;
import com.cloudtimes.common.core.page.TableDataInfo;
import com.cloudtimes.ybf.domain.YbfGoods;
import com.cloudtimes.ybf.service.IYbfGoodsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 商品Controller
 *
 * @author polo
 * @date 2022-09-01
 */
@Api("商品控制API接口")
@RestController
@RequestMapping("/ybf/goods")
public class AppGoodsController extends BaseController {
    @Autowired
    private IYbfGoodsService ybfGoodsService;

    @ApiOperation("查询商品列表")
    @GetMapping("/list")
    public TableDataInfo list(YbfGoods ybfGoods) {
        startPage();
        ybfGoods.setEnabled("1");
        List<YbfGoods> list = ybfGoodsService.selectYbfGoodsList(ybfGoods);
        return getDataTable(list);
    }

    @ApiOperation("获取商品详细信息")
    @GetMapping(value = "/{goodsCode}")
    public AjaxResult getInfo(@PathVariable("goodsCode") String goodsCode) {

        YbfGoods goods = ybfGoodsService.selectYbfGoodsByGoodsCode(goodsCode);
        YbfGoods update = new YbfGoods();
        update.setId(goods.getId());
        update.setLookNum(goods.getLookNum() + 1);
        ybfGoodsService.updateYbfGoods(update);

        goods.setLookNum(update.getLookNum());
        return AjaxResult.success(goods);
    }

}
