package com.cloudtimes.app.controller;

import com.cloudtimes.common.annotation.RepeatSubmit;
import com.cloudtimes.common.core.controller.BaseController;
import com.cloudtimes.common.core.domain.AjaxResult;
import com.cloudtimes.common.core.redis.RedisCache;
import com.cloudtimes.system.service.ISysConfigService;
import com.cloudtimes.ybf.domain.YbfFinanceOrder;
import com.cloudtimes.ybf.service.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 理财产品（绿色能量）用户订单Controller
 *
 * @author polo
 * @date 2022-09-02
 */
@Api("绿色能量购买订单处理API接口")
@RestController
@RequestMapping("/ybf/financeorder")
public class AppFinanceOrderController extends BaseController {
    @Autowired
    private IYbfFinanceOrderService ybfFinanceOrderService;

    @Autowired
    private RedisCache redisCache;

    @Autowired
    private ISysConfigService configService;

    @Autowired
    private IYbfFinanceService ybfFinanceService;

    @Autowired
    private IYbfPaymentChannelService ybfPaymentChannelService;

    @Autowired
    private IYbfMemberFinanceService memberFinanceService;

    @Autowired
    private IYbfMemberNoticeService memberNoticeService;

    @Autowired
    private IYbfMemberService memberService;


    @ApiOperation("获取绿色能量订单详情")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id) {
        return AjaxResult.success(ybfFinanceOrderService.selectYbfFinanceOrderById(id));
    }

    @RepeatSubmit(interval = 5000, message = "重复提交过于频繁，请稍候再试")
    @ApiOperation("购买绿色能量下单")
    @PostMapping("/order")
    public AjaxResult add(@RequestBody YbfFinanceOrder ybfFinanceOrder) {

        ybfFinanceOrder.setUsername(getAppUsername());

//        String paymentURL = configService.selectConfigByKey("app.payment.domain");
//        paymentURL = paymentURL + "/test/payment/finance/" + ybfFinanceOrder.getOrderNo();


        return ybfFinanceOrderService.createOrder(ybfFinanceOrder);
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
