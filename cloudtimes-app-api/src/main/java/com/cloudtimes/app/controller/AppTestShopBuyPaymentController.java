package com.cloudtimes.app.controller;

import com.cloudtimes.common.utils.DateUtils;
import com.cloudtimes.common.utils.file.FileReadUtils;
import com.cloudtimes.ybf.domain.YbfGoodsOrder;
import com.cloudtimes.ybf.domain.YbfPaymentChannel;
import com.cloudtimes.ybf.service.IYbfGoodsOrderService;
import com.cloudtimes.ybf.service.IYbfPaymentChannelService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Api("购物支付通道收银API接口")
@RestController
@RequestMapping("/test/payment")
public class AppTestShopBuyPaymentController {

    private static String payment = null;

    @Autowired
    private IYbfGoodsOrderService ybfGoodsOrderService;

    @Autowired
    private IYbfPaymentChannelService ybfPaymentChannelService;

    @ApiOperation("模拟测试-购买商品收银处理接口")
    @GetMapping(value = "/buyshop/{orderId}")
    public void buyshop(@PathVariable("orderId") String orderId, HttpServletResponse response) {
        if (payment == null) {
            try {
                payment = FileReadUtils.readFileToString("payment.html");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        response.setContentType("text/html;charset=utf8");
        response.setCharacterEncoding("utf-8");
        try {
            YbfGoodsOrder goodsOrder = ybfGoodsOrderService.selectYbfGoodsOrderByOrderNo(orderId);
            if (goodsOrder == null) {
                response.getWriter().print("<h1>该订单为异常订单！</h1>");
                return;
            }

            if (goodsOrder.getPaymentTime() != null) {
                response.getWriter().print("<h1>该订单交易已关闭！</h1>");
                return;
            }

            goodsOrder.setStatus(new Long(1));
            ybfGoodsOrderService.updateYbfGoodsOrder(goodsOrder);
            YbfPaymentChannel ybfPaymentChannel = ybfPaymentChannelService.selectYbfPaymentChannelById(goodsOrder.getPaymentChannelId());

            String result = String.format(payment,
                    ybfPaymentChannel.getPaymentName() + "平台",
                    ybfPaymentChannel.getPaymentName(),
                    ybfPaymentChannel.getChannelName(),
                    goodsOrder.getOrderNo(),
                    goodsOrder.getMoneyAmount(),
                    goodsOrder.getCreateTime(),
                    "/api/test/payment/buyshop/ok/" + goodsOrder.getOrderNo(),
                    "/api/test/payment/buyshop/fail/" + goodsOrder.getOrderNo()
            );
            response.getWriter().print(result);
        } catch (
                IOException e) {
            e.printStackTrace();
        }

    }

    @ApiOperation("模拟测试-购买商品收银处理成功接口")
    @GetMapping(value = "/buyshop/ok/{orderId}")
    public void financeOrderSuccess(@PathVariable("orderId") String orderId, HttpServletResponse response) {

        try {
            response.setContentType("text/html;charset=utf8");
            response.setCharacterEncoding("utf-8");

            YbfGoodsOrder ybfGoodsOrder = ybfGoodsOrderService.selectYbfGoodsOrderByOrderNo(orderId);
            if (ybfGoodsOrder.getPaymentTime() != null) {
                response.getWriter().print("<h1>该订单交易已关闭！</h1>");
                return;
            }

            ybfGoodsOrderService.processOrderSuccess(ybfGoodsOrder);

            response.getWriter().print("<h1>支付成功！</h1>");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @ApiOperation("模拟测试-购买商品收银处理失败接口")
    @GetMapping(value = "/buyshop/fail/{orderId}")
    public void financeOrderFail(@PathVariable("orderId") String orderId, HttpServletResponse response) {
        try {
            YbfGoodsOrder ybfGoodsOrder = ybfGoodsOrderService.selectYbfGoodsOrderByOrderNo(orderId);
            if (ybfGoodsOrder.getPaymentTime() != null) {
                response.getWriter().print("<h1>该订单交易已关闭！</h1>");
                return;
            }

            ybfGoodsOrder.setStatus(new Long(-1));
            ybfGoodsOrder.setPaymentTime(DateUtils.getNowDate());
            ybfGoodsOrderService.updateYbfGoodsOrder(ybfGoodsOrder);

            ybfGoodsOrderService.processOrderFailed(ybfGoodsOrder);

            response.setContentType("text/html;charset=utf8");
            response.setCharacterEncoding("utf-8");
            response.getWriter().print("<h1>支付失败！</h1>");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
