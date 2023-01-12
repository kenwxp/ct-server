package com.cloudtimes.app.controller;

import com.cloudtimes.common.annotation.RepeatSubmit;
import com.cloudtimes.common.core.controller.BaseController;
import com.cloudtimes.common.core.domain.AjaxResult;
import com.cloudtimes.common.core.domain.entity.YbfMember;
import com.cloudtimes.common.core.page.TableDataInfo;
import com.cloudtimes.common.core.redis.RedisCache;
import com.cloudtimes.common.utils.StringUtils;
import com.cloudtimes.common.utils.uuid.Seq;
import com.cloudtimes.system.service.ISysConfigService;
import com.cloudtimes.ybf.domain.*;
import com.cloudtimes.ybf.domain.vo.BuyOrderDetailVO;
import com.cloudtimes.ybf.domain.vo.ShopBuyVO;
import com.cloudtimes.ybf.domain.vo.ShopItemVO;
import com.cloudtimes.ybf.service.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * 会员购物订单Controller
 *
 * @author polo
 * @date 2022-09-02
 */
@Api("会员购物订单处理API接口")
@RestController
@RequestMapping("/ybf/shoporder")
public class AppShopBuyOrderController extends BaseController {

    @Autowired
    private IYbfGoodsOrderService goodsOrderService;

    @Autowired
    private RedisCache redisCache;

    @Autowired
    private ISysConfigService configService;

    @Autowired
    private IYbfPaymentChannelService ybfPaymentChannelService;

    @Autowired
    private IYbfMemberNoticeService memberNoticeService;

    @Autowired
    private IYbfGoodsService ybfGoodsService;

    @Autowired
    private IYbfMemberService memberService;

    @Autowired
    private IYbfPostAreaService postAreaService;

    @Autowired
    private IYbfGoodsOrderItemsService goodsOrderItemsService;

    @Autowired
    private IYbfMemberFlowDetailsService memberFlowDetailsService;

    @Autowired
    private IYbfGoodsOrderRewardService goodsOrderRewardService;

    @Autowired
    private IYbfMemberFriendshipService memberFriendshipService;

    @Autowired
    private IYbfMemberAgentService memberAgentService;


    @ApiOperation("获取购物订单详情")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id) {
        YbfGoodsOrder ybfGoodsOrder = goodsOrderService.selectYbfGoodsOrderById(id);
        AjaxResult ajaxResult = AjaxResult.success(ybfGoodsOrder);

        YbfGoodsOrderItems query = new YbfGoodsOrderItems();
        query.setOrderNo(ybfGoodsOrder.getOrderNo());
        List<YbfGoodsOrderItems> list = goodsOrderItemsService.selectYbfGoodsOrderItemsList(query);
        ajaxResult.put("items", list);
        return ajaxResult;
    }

    @RepeatSubmit(interval = 5000, message = "重复提交过于频繁，请稍候再试")
    @ApiOperation("购买商品下单")
    @PostMapping("/order")
    public AjaxResult order(@RequestBody ShopBuyVO shopBuyVO) {
        return goodsOrderService.createOrder(shopBuyVO, getAppUsername());
    }


    @ApiOperation("查询商品订单列表")
    @GetMapping("/list")
    public TableDataInfo list(YbfGoodsOrder ybfGoodsOrder) {
        startPage();

        ybfGoodsOrder.setUsername(getAppUsername());
        List<YbfGoodsOrder> ybfGoodsOrders = goodsOrderService.selectAppGoodsOrderList(ybfGoodsOrder);

        YbfGoodsOrderItems query = null;
        BuyOrderDetailVO buyOrderDetailVO = null;
        List<BuyOrderDetailVO> buyOrderDetailVOList = new ArrayList<>();
        for (YbfGoodsOrder order : ybfGoodsOrders) {

            buyOrderDetailVO = new BuyOrderDetailVO();
            query = new YbfGoodsOrderItems();
            query.setOrderNo(order.getOrderNo());
            List<YbfGoodsOrderItems> list = goodsOrderItemsService.selectYbfGoodsOrderItemsList(query);
            buyOrderDetailVO.setOrder(order);
            buyOrderDetailVO.setGoodsOrderItemsList(list);
            buyOrderDetailVOList.add(buyOrderDetailVO);
        }

        return getDataTable(buyOrderDetailVOList);
    }


    @RepeatSubmit(interval = 5000, message = "重复提交过于频繁，请稍候再试")
    @ApiOperation("取消订单")
    @GetMapping("/cancelOrder/{orderNo}")
    public AjaxResult order(@PathVariable("orderNo") String orderNo) {

        YbfGoodsOrder goodsOrder = goodsOrderService.selectYbfGoodsOrderByOrderNo(orderNo);
        if (goodsOrder == null) {
            return AjaxResult.error("取消购物订单失败！");
        }

        if (!goodsOrder.getUsername().equals(getAppUsername())) {
            return AjaxResult.error("异常操作！");
        }

        if (goodsOrder.getStatus() == 0 || goodsOrder.getStatus() == 1) {
            goodsOrder.setStatus(new Long(-4));
            goodsOrderService.updateYbfGoodsOrder(goodsOrder);
        }

        return AjaxResult.success("订单取消成功！");
    }

    @RepeatSubmit(interval = 5000, message = "重复提交过于频繁，请稍候再试")
    @ApiOperation("删除订单")
    @GetMapping("/delOrder/{orderNo}")
    public AjaxResult delOrder(@PathVariable("orderNo") String orderNo) {

        YbfGoodsOrder goodsOrder = goodsOrderService.selectYbfGoodsOrderByOrderNo(orderNo);
        if (goodsOrder == null) {
            return AjaxResult.success("删除购物订单成功");
        }

        if (!goodsOrder.getUsername().equals(getAppUsername())) {
            return AjaxResult.error("异常操作！");
        }

        if (goodsOrder.getStatus() == -1 || goodsOrder.getStatus() == -3 || goodsOrder.getStatus() == -4 || goodsOrder.getStatus() == 4) {
            goodsOrder.setStatus(new Long(-5));
            goodsOrderService.updateYbfGoodsOrder(goodsOrder);
        }

        return AjaxResult.success("订单删除成功！");
    }


    @ApiOperation("获取订单详情")
    @GetMapping("/orderInfo/{orderNo}")
    public AjaxResult orderInfo(@PathVariable("orderNo") String orderNo) {

        YbfGoodsOrder goodsOrder = goodsOrderService.selectYbfGoodsOrderByOrderNo(orderNo);
        if (goodsOrder == null) {
            return AjaxResult.success();
        }

        if (!goodsOrder.getUsername().equals(getAppUsername())) {
            return AjaxResult.error("异常操作！");
        }

        YbfGoodsOrderItems query = new YbfGoodsOrderItems();
        query.setOrderNo(goodsOrder.getOrderNo());
        List<YbfGoodsOrderItems> list = goodsOrderItemsService.selectYbfGoodsOrderItemsList(query);
        BuyOrderDetailVO buyOrderDetailVO = new BuyOrderDetailVO();
        buyOrderDetailVO.setOrder(goodsOrder);
        buyOrderDetailVO.setGoodsOrderItemsList(list);

        return AjaxResult.success(buyOrderDetailVO);
    }

    @RepeatSubmit(interval = 5000, message = "重复提交过于频繁，请稍候再试")
    @ApiOperation("确认收货")
    @GetMapping("/okDone/{orderNo}")
    public AjaxResult okDone(@PathVariable("orderNo") String orderNo) {

        YbfGoodsOrder goodsOrder = goodsOrderService.selectYbfGoodsOrderByOrderNo(orderNo);
        if (goodsOrder == null) {
            return AjaxResult.error("无效购物订单！");
        }

        if (!goodsOrder.getUsername().equals(getAppUsername())) {
            return AjaxResult.error("异常操作！");
        }

        if (goodsOrder.getStatus() != 3) {
            return AjaxResult.error("该订单无法操作确认收货！");
        }

        goodsOrder.setStatus(new Long(4));
        goodsOrderService.updateYbfGoodsOrder(goodsOrder);

        return AjaxResult.success("订单已完成！");
    }


    @RepeatSubmit(interval = 5000, message = "重复提交过于频繁，请稍候再试")
    @ApiOperation("申请退货")
    @GetMapping("/returnGoods/{orderNo}")
    public AjaxResult returnGoods(@PathVariable("orderNo") String orderNo) {

        YbfGoodsOrder goodsOrder = goodsOrderService.selectYbfGoodsOrderByOrderNo(orderNo);
        if (goodsOrder == null) {
            return AjaxResult.error("无效购物订单！");
        }

        if (!goodsOrder.getUsername().equals(getAppUsername())) {
            return AjaxResult.error("异常操作！");
        }

        if (goodsOrder.getStatus().intValue() == -2) {
            return AjaxResult.error("您已申请退货，敬请等待回复");
        }

        if (goodsOrder.getStatus() < 2 || goodsOrder.getStatus() > 4) {
            return AjaxResult.error("该订单无法操作申请退货！");
        }

        goodsOrder.setStatus(new Long(-2));
        goodsOrderService.updateYbfGoodsOrder(goodsOrder);

        return AjaxResult.success("申请退货已提交");
    }


    @ApiOperation("获取购物订单详情")
    @GetMapping(value = "/refreshPaymentResult/{id}")
    public AjaxResult refreshPaymentResult(@PathVariable("id") Long id) {
        YbfGoodsOrder ybfGoodsOrder = goodsOrderService.selectYbfGoodsOrderById(id);
        AjaxResult ajaxResult = AjaxResult.success(ybfGoodsOrder);

        YbfGoodsOrderItems query = new YbfGoodsOrderItems();
        query.setOrderNo(ybfGoodsOrder.getOrderNo());
        List<YbfGoodsOrderItems> list = goodsOrderItemsService.selectYbfGoodsOrderItemsList(query);
        ajaxResult.put("items", list);
        return ajaxResult;
    }


    @ApiOperation("刷新交易结果")
    @GetMapping("/refreshPaymentResult")
    public AjaxResult refreshPaymentResult(String orderNo, String paymentNo) {
        if (redisCache.hasKey(orderNo)) {
            Integer datas = redisCache.getCacheObject(orderNo);
            return AjaxResult.success(datas);
        }
        return AjaxResult.success();
    }
}
