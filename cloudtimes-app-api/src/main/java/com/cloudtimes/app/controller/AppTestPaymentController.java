package com.cloudtimes.app.controller;

import com.cloudtimes.common.utils.DateUtils;
import com.cloudtimes.common.utils.file.FileReadUtils;
import com.cloudtimes.ybf.domain.YbfFinanceOrder;
import com.cloudtimes.ybf.domain.YbfPaymentChannel;
import com.cloudtimes.ybf.service.IYbfFinanceOrderService;
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

@Api("支付通道收银API接口")
@RestController
@RequestMapping("/test/payment")
public class AppTestPaymentController {

    private static String payment = null;

    @Autowired
    private IYbfFinanceOrderService ybfFinanceOrderService;

    @Autowired
    private IYbfPaymentChannelService ybfPaymentChannelService;

    @ApiOperation("模拟测试-购买绿色能量收银处理接口")
    @GetMapping(value = "/finance/{orderId}")
    public void getInfo(@PathVariable("orderId") String orderId, HttpServletResponse response) {
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
            YbfFinanceOrder ybfFinanceOrder = ybfFinanceOrderService.selectYbfFinanceOrderByOrderId(orderId);
            if (ybfFinanceOrder.getPaymentTime() != null) {
                response.getWriter().print("<h1>该订单交易已关闭！</h1>");
                return;
            }

            ybfFinanceOrder.setStatus(new Long(1));
            ybfFinanceOrderService.updateYbfFinanceOrder(ybfFinanceOrder);
            YbfPaymentChannel ybfPaymentChannel = ybfPaymentChannelService.selectYbfPaymentChannelById(ybfFinanceOrder.getPaymentChannelId());

            if (ybfFinanceOrder == null) {
                response.getWriter().print("<h1>该订单为异常订单！</h1>");
            } else {
                String result = String.format(payment,
                        ybfPaymentChannel.getPaymentName() + "平台",
                        ybfPaymentChannel.getPaymentName(),
                        ybfPaymentChannel.getChannelName(),
                        ybfFinanceOrder.getOrderNo(),
                        ybfFinanceOrder.getMoneyAmount(),
                        ybfFinanceOrder.getCreateTime(),
                        "/api/test/payment/finance/ok/" + ybfFinanceOrder.getOrderNo(),
                        "/api/test/payment/finance/fail/" + ybfFinanceOrder.getOrderNo()
                );
                response.getWriter().print(result);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @ApiOperation("模拟测试-购买绿色能量收银处理成功接口")
    @GetMapping(value = "/finance/ok/{orderId}")
    public void financeOrderSuccess(@PathVariable("orderId") String orderId, HttpServletResponse response) {

        try {
            response.setContentType("text/html;charset=utf8");
            response.setCharacterEncoding("utf-8");

            YbfFinanceOrder ybfFinanceOrder = ybfFinanceOrderService.selectYbfFinanceOrderByOrderId(orderId);
            if (ybfFinanceOrder.getPaymentTime() != null) {
                response.getWriter().print("<h1>该订单交易已关闭！</h1>");
                return;
            }

            ybfFinanceOrderService.processOrder(ybfFinanceOrder);


            response.getWriter().print("<h1>支付成功！</h1>");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @ApiOperation("模拟测试-购买绿色能量收银处理失败接口")
    @GetMapping(value = "/finance/fail/{orderId}")
    public void financeOrderFail(@PathVariable("orderId") String orderId, HttpServletResponse response) {
        try {
            YbfFinanceOrder ybfFinanceOrder = ybfFinanceOrderService.selectYbfFinanceOrderByOrderId(orderId);
            if (ybfFinanceOrder.getPaymentTime() != null) {
                response.getWriter().print("<h1>该订单交易已关闭！</h1>");
                return;
            }

            ybfFinanceOrder.setStatus(new Long(-1));
            ybfFinanceOrder.setPaymentTime(DateUtils.getNowDate());
            ybfFinanceOrderService.updateYbfFinanceOrder(ybfFinanceOrder);
            response.setContentType("text/html;charset=utf8");
            response.setCharacterEncoding("utf-8");
            response.getWriter().print("<h1>支付失败！</h1>");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
