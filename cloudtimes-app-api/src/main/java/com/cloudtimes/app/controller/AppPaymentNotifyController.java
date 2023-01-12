package com.cloudtimes.app.controller;

import com.alibaba.fastjson.JSONObject;
import com.huifu.adapay.core.AdapayCore;
import com.huifu.adapay.core.util.AdapaySign;
import com.cloudtimes.common.constant.HttpStatus;
import com.cloudtimes.common.core.redis.RedisCache;
import com.cloudtimes.common.utils.DateUtils;
import com.cloudtimes.common.utils.file.FileReadUtils;
import com.cloudtimes.ybf.domain.*;
import com.cloudtimes.ybf.service.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.spring.web.json.Json;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

@Api("三方支付回调API接口")
@RestController
@RequestMapping("/payment/notify")
public class AppPaymentNotifyController {

    private static String payment = null;

    @Autowired
    private IYbfGoodsOrderService ybfGoodsOrderService;

    @Autowired
    private IYbfFinanceOrderService ybfFinanceOrderService;

    @Autowired
    private IYbfCashOutOrderService ybfCashOutOrderService;

    @Autowired
    private IYbfMysaleCashoutOrderService ybfMysaleCashoutOrderService;

    @Autowired
    private IYbfAdminRechargeOrderService adminRechargeOrderService;

    @Autowired
    private RedisCache redisCache;

    static Logger log = LoggerFactory.getLogger(AppPaymentNotifyController.class);


    @ApiOperation("购买商品三方支付回调处理接口")
    @PostMapping(value = "/buyshop")
    public void buyShopPaymentNotify(HttpServletResponse response, HttpServletRequest request) {
        response.setContentType("text/html;charset=utf8");
        response.setCharacterEncoding("utf-8");
        try {
            //验签请参data
            String data = request.getParameter("data");
            //验签请参sign
            String sign = request.getParameter("sign");
            //验签标记
            boolean checkSign = false;
            //验签请参publicKey
            String publicKey = AdapayCore.PUBLIC_KEY;
            log.info("验签请参：data={}sign={}", data, sign);
            //验签
            checkSign = AdapaySign.verifySign(data, sign, publicKey);
            //  checkSign = true;
            log.info("验签结果：" + checkSign);
            if (checkSign) {
                //验签成功逻辑
                System.out.println("成功返回数据data:" + data);
                JSONObject resultJson = JSONObject.parseObject(data);
                String orderNo = resultJson.getString("order_no");
                log.info("付款成功订单号：" + orderNo);
                YbfGoodsOrder ybfGoodsOrder = ybfGoodsOrderService.selectYbfGoodsOrderByOrderNo(orderNo);
                if (resultJson.getString("status").equals("succeeded")) {
                    if (ybfGoodsOrder.getStatus().intValue() == 0 || ybfGoodsOrder.getStatus().intValue() == 1) {
                        ybfGoodsOrderService.processOrderSuccess(ybfGoodsOrder);
                    }
                    redisCache.deleteObject(ybfGoodsOrder.getOrderNo());
                    redisCache.setCacheObject(ybfGoodsOrder.getOrderNo(), 2, 5, TimeUnit.MINUTES);
                    response.getWriter().print("支付成功订单处理完成！");
                } else {
                    if (ybfGoodsOrder.getStatus().intValue() == 0 || ybfGoodsOrder.getStatus().intValue() == 1) {
                        ybfGoodsOrder.setStatus(new Long(-1));
                        ybfGoodsOrder.setPaymentTime(DateUtils.getNowDate());
                        ybfGoodsOrderService.updateYbfGoodsOrder(ybfGoodsOrder);
                        ybfGoodsOrderService.processOrderFailed(ybfGoodsOrder);
                        redisCache.deleteObject(ybfGoodsOrder.getOrderNo());
                        redisCache.setCacheObject(ybfGoodsOrder.getOrderNo(), -1, 5, TimeUnit.MINUTES);
                    }
                    response.getWriter().print("支付失败订单处理完成！");
                }
                response.setStatus(HttpStatus.SUCCESS);
            } else {
                log.info("验签失败:" + checkSign);
                //验签失败逻辑
                response.getWriter().print("验签失败逻辑");
                response.setStatus(HttpStatus.ERROR);
            }
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage(), e);
            response.setStatus(HttpStatus.ERROR);
        }
    }


    @ApiOperation("购买内部商品三方支付回调处理接口")
    @PostMapping(value = "/buyAdminShop")
    public void buyAdminShopNotify(HttpServletResponse response, HttpServletRequest request) {
        response.setContentType("text/html;charset=utf8");
        response.setCharacterEncoding("utf-8");
        try {
            //验签请参data
            String data = request.getParameter("data");
            //验签请参sign
            String sign = request.getParameter("sign");
            //验签标记
            boolean checkSign = false;
            //验签请参publicKey
            String publicKey = AdapayCore.PUBLIC_KEY;
            log.info("验签请参：data={}sign={}", data, sign);
            //验签
            checkSign = AdapaySign.verifySign(data, sign, publicKey);
            //  checkSign = true;
            log.info("验签结果：" + checkSign);
            if (checkSign) {
                //验签成功逻辑
                System.out.println("成功返回数据data:" + data);
                JSONObject resultJson = JSONObject.parseObject(data);
                String orderNo = resultJson.getString("order_no");
                log.info("付款成功订单号：" + orderNo);
                YbfAdminRechargeOrder adminRechargeOrder = adminRechargeOrderService.selectYbfAdminRechargeOrderByOrderNo(orderNo);
                if (resultJson.getString("status").equals("succeeded")) {
                    if (adminRechargeOrder.getStatus().intValue() == 0) {
                        adminRechargeOrder.setStatus(new Long(1));
                        adminRechargeOrder.setPaymentTime(DateUtils.getNowDate());
                        adminRechargeOrder.setRspResult(resultJson.toJSONString());
                        adminRechargeOrder.setPaymentId(resultJson.getString("id"));
                        adminRechargeOrderService.updateYbfAdminRechargeOrder(adminRechargeOrder);
                    }
                    response.getWriter().print("支付成功订单处理完成！");
                } else {
                    if (adminRechargeOrder.getStatus().intValue() == 0) {
                        adminRechargeOrder.setStatus(new Long(-1));
                        adminRechargeOrder.setRspResult(resultJson.toJSONString());
                        adminRechargeOrder.setPaymentTime(DateUtils.getNowDate());
                        adminRechargeOrderService.updateYbfAdminRechargeOrder(adminRechargeOrder);
                    }
                    response.getWriter().print("支付失败订单处理完成！");
                }
                response.setStatus(HttpStatus.SUCCESS);
            } else {
                log.info("验签失败:" + checkSign);
                //验签失败逻辑
                response.getWriter().print("验签失败逻辑");
                response.setStatus(HttpStatus.ERROR);
            }
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage(), e);
            response.setStatus(HttpStatus.ERROR);
        }
    }


    @ApiOperation("购买树苗三方支付回调处理接口")
    @PostMapping(value = "/buyTree")
    public void financePaymentNotify(HttpServletResponse response, HttpServletRequest request) {
        response.setContentType("text/html;charset=utf8");
        response.setCharacterEncoding("utf-8");
        try {
            //验签请参data
            String data = request.getParameter("data");
            //验签请参sign
            String sign = request.getParameter("sign");
            //验签标记
            boolean checkSign = false;
            //验签请参publicKey
            String publicKey = AdapayCore.PUBLIC_KEY;
            log.info("购买树苗三方回调验签请参：data={}sign={}", data, sign);
            //验签
            checkSign = AdapaySign.verifySign(data, sign, publicKey);
            //  checkSign = true;
            log.info("购买树苗三方回调验签结果：" + checkSign);
            if (checkSign) {
                //验签成功逻辑
                System.out.println("成功返回数据data:" + data);
                JSONObject resultJson = JSONObject.parseObject(data);
                String orderNo = resultJson.getString("order_no");
                log.info("购买树苗三方回调付款成功订单号：" + orderNo);
                YbfFinanceOrder ybfFinanceOrder = ybfFinanceOrderService.selectYbfFinanceOrderByOrderId(orderNo);
                if (resultJson.getString("status").equals("succeeded")) {
                    if (ybfFinanceOrder.getStatus().intValue() == 0 || ybfFinanceOrder.getStatus().intValue() == 1) {
                        ybfFinanceOrderService.processOrder(ybfFinanceOrder);
                    }
                    redisCache.deleteObject(ybfFinanceOrder.getOrderNo());
                    redisCache.setCacheObject(ybfFinanceOrder.getOrderNo(), 2, 5, TimeUnit.MINUTES);
                    response.getWriter().print("购买树苗支付成功订单处理完成！");
                } else {
                    if (ybfFinanceOrder.getStatus().intValue() == 0 || ybfFinanceOrder.getStatus().intValue() == 1) {
                        ybfFinanceOrder.setStatus(new Long(-1));
                        ybfFinanceOrder.setPaymentTime(DateUtils.getNowDate());
                        ybfFinanceOrderService.updateYbfFinanceOrder(ybfFinanceOrder);
                        redisCache.deleteObject(ybfFinanceOrder.getOrderNo());
                        redisCache.setCacheObject(ybfFinanceOrder.getOrderNo(), -1, 5, TimeUnit.MINUTES);
                    }
                    response.getWriter().print("支付失败订单处理完成！");
                }
                response.setStatus(HttpStatus.SUCCESS);
            } else {
                log.info("购买树苗三方回调验签失败:" + checkSign);
                //验签失败逻辑
                response.getWriter().print("验签失败逻辑");
                response.setStatus(HttpStatus.ERROR);
            }
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage(), e);
            response.setStatus(HttpStatus.ERROR);
        }
    }


    @ApiOperation("碳积分取现三方支付回调处理接口")
    @PostMapping(value = "/scoreCash")
    public void scoreCashNotify(HttpServletResponse response, HttpServletRequest request) {
        response.setContentType("text/html;charset=utf8");
        response.setCharacterEncoding("utf-8");
        try {
            //验签请参data
            String data = request.getParameter("data");
            //验签请参sign
            String sign = request.getParameter("sign");
            //验签标记
            boolean checkSign = false;
            //验签请参publicKey
            String publicKey = AdapayCore.PUBLIC_KEY;
            log.info("碳积分取现三方回调验签请参：data={}sign={}", data, sign);
            //验签
            checkSign = AdapaySign.verifySign(data, sign, publicKey);
            //  checkSign = true;
            log.info("碳积分取现三方回调验签结果：" + checkSign);
            if (checkSign) {
                //验签成功逻辑
                System.out.println("成功返回数据data:" + data);
                JSONObject resultJson = JSONObject.parseObject(data);
                String orderNo = resultJson.getString("order_no");
                log.info("碳积分取现三方回调付款成功订单号：" + orderNo);
                YbfCashOutOrder ybfCashOutOrder = ybfCashOutOrderService.selectYbfCashOutOrderByOrderId(orderNo);
                if (resultJson.getString("status").equals("succeeded")) {
                    if (ybfCashOutOrder.getStatus().intValue() == 2) {
                        ybfCashOutOrder.setAdapayTradeId(resultJson.getString("id"));
                        ybfCashOutOrder.setDescText(ybfCashOutOrder.getDescText() + ";碳积分取现三方回调付款成功。");
                        ybfCashOutOrderService.notifyCashSuccess(ybfCashOutOrder);
                    }
                    response.getWriter().print("碳积分取现支付成功订单处理完成！");
                } else {
                    if (ybfCashOutOrder.getStatus().intValue() == 2) {
                        ybfCashOutOrder.setAdapayTradeId(resultJson.getString("id"));
                        ybfCashOutOrderService.notifyCashFailed(ybfCashOutOrder, "");
                    }
                    response.getWriter().print("碳积分取现支付失败订单处理完成！");
                }
                response.setStatus(HttpStatus.SUCCESS);
            } else {
                log.info("碳积分取现三方回调验签失败:" + checkSign);
                //验签失败逻辑
                response.getWriter().print("验签失败逻辑");
                response.setStatus(HttpStatus.ERROR);
            }
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage(), e);
            response.setStatus(HttpStatus.ERROR);
        }
    }


    @ApiOperation("销售取现三方支付回调处理接口")
    @PostMapping(value = "/rewardCash")
    public void rewardCashNotify(HttpServletResponse response, HttpServletRequest request) {
        response.setContentType("text/html;charset=utf8");
        response.setCharacterEncoding("utf-8");
        try {
            //验签请参data
            String data = request.getParameter("data");
            //验签请参sign
            String sign = request.getParameter("sign");
            //验签标记
            boolean checkSign = false;
            //验签请参publicKey
            String publicKey = AdapayCore.PUBLIC_KEY;
            log.info("销售奖励取现三方回调验签请参：data={}sign={}", data, sign);
            //验签
            checkSign = AdapaySign.verifySign(data, sign, publicKey);
            //  checkSign = true;
            log.info("销售奖励取现三方回调验签结果：" + checkSign);
            if (checkSign) {
                //验签成功逻辑
                System.out.println("成功返回数据data:" + data);
                JSONObject resultJson = JSONObject.parseObject(data);
                String orderNo = resultJson.getString("order_no");
                log.info("销售奖励取现三方回调付款成功订单号：" + orderNo);
                YbfMysaleCashoutOrder mysaleCashoutOrder = ybfMysaleCashoutOrderService.selectYbfMysaleCashoutOrderByOrderNo(orderNo);
                if (resultJson.getString("status").equals("succeeded")) {
                    if (mysaleCashoutOrder.getStatus().intValue() == 2) {
                        mysaleCashoutOrder.setAdapayTradeId(resultJson.getString("id"));
                        mysaleCashoutOrder.setDescText(mysaleCashoutOrder.getDescText() + ";销售奖励取现三方回调付款成功。");
                        ybfMysaleCashoutOrderService.notifyCashSuccess(mysaleCashoutOrder);
                    }
                    response.getWriter().print("销售奖励分取现支付成功订单处理完成！");
                } else {
                    if (mysaleCashoutOrder.getStatus().intValue() == 2) {
                        mysaleCashoutOrder.setAdapayTradeId(resultJson.getString("id"));
                        ybfMysaleCashoutOrderService.notifyCashFailed(mysaleCashoutOrder, "");
                    }
                    response.getWriter().print("销售奖励取现支付失败订单处理完成！");
                }
                response.setStatus(HttpStatus.SUCCESS);
            } else {
                log.info("销售奖励取现三方回调验签失败:" + checkSign);
                //验签失败逻辑
                response.getWriter().print("验签失败逻辑");
                response.setStatus(HttpStatus.ERROR);
            }
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage(), e);
            response.setStatus(HttpStatus.ERROR);
        }
    }

}
