package com.cloudtimes.app.controller.cash;

import com.cloudtimes.app.constant.PrefixPathConstants;
import com.cloudtimes.common.constant.HttpStatus;
import com.cloudtimes.common.core.domain.ApiResult;
import com.cloudtimes.common.core.domain.entity.AuthUser;
import com.cloudtimes.common.utils.AuthUtils;
import com.cloudtimes.common.utils.JWTManager;
import com.cloudtimes.common.utils.StringUtils;
import com.cloudtimes.partner.pay.shouqianba.domain.AuthInfoData;
import com.cloudtimes.serving.cash.service.ICtCashBusinessService;
import com.cloudtimes.serving.cash.service.ICtCashLoginService;
import com.cloudtimes.serving.cash.service.domain.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Api(tags = "收银机业务相关接口")
@RestController
@RequestMapping(PrefixPathConstants.CASH_PATH_PREFIX + "/business")
public class CashBusinessController {
    @Autowired
    private ICtCashLoginService loginService;
    @Autowired
    private JWTManager jwtManager;
    @Autowired
    private ICtCashBusinessService cashBusinessService;

    @ApiOperation("获取刷脸凭证")
    @PostMapping(value = "/face/authinfo")
    public ApiResult<AuthInfoData> getFaceAuthInfo(@RequestBody GetFaceAuthInfoReq info) {
        AuthUser authUser = AuthUtils.getObject();
        AuthInfoData faceAuthInfo = cashBusinessService.getFaceAuthInfo(authUser.getId(), info.getRawdata(), info.getAuthType());
        return new ApiResult().success(faceAuthInfo);
    }

    @ApiOperation("根据刷脸token获取订单号")
    @PostMapping(value = "/face/order")
    public ApiResult<GetOrderIdResp> getOrderId(@RequestBody GetOrderIdReq info) {
        AuthUser authUser = AuthUtils.getObject();
        GetOrderIdResp resp = cashBusinessService.getOrderId(authUser.getId(), info);
        return new ApiResult().success(resp);
    }

    @ApiOperation("拉取商品列表")
    @PostMapping(value = "/product/fetch")
    public ApiResult<List<GetProductListResp>> getProductList() {
        AuthUser authUser = AuthUtils.getObject();
        List<GetProductListResp> productList = cashBusinessService.getProductList(authUser.getId());
        return new ApiResult().success(productList);
    }

    @ApiOperation("获取语音token")
    @PostMapping(value = "/voice/token")
    public ApiResult<GetVoiceTokenResp> getVoiceToken() {
        AuthUser authUser = AuthUtils.getObject();
        GetVoiceTokenResp resp = cashBusinessService.getVoiceToken(authUser.getId());
        return new ApiResult().success(resp);
    }

    @ApiOperation("订单加商品")
    @PostMapping(value = "/order/item/add")
    public ApiResult<OrderItemResp> addOrderItem(@RequestBody OrderItemAddReq info) {
        AuthUser authUser = AuthUtils.getObject();
        OrderItemResp orderItemResp = cashBusinessService.addOrderItem(authUser.getId(), info);
        return new ApiResult().success(orderItemResp);
    }

    @ApiOperation("订单减商品")
    @PostMapping(value = "/order/item/delete")
    public ApiResult<OrderItemResp> deleteOrderItem(@RequestBody OrderItemDeleteReq info) {
        AuthUser authUser = AuthUtils.getObject();
        cashBusinessService.deleteOrderItem(authUser.getId(), info);
        return new ApiResult().success(new OrderItemResp(info.getOrderId()));
    }

    @ApiOperation("取消订单")
    @PostMapping(value = "/order/cancel")
    public ApiResult<OrderItemResp> cancelOrder(@RequestBody OrderItemCancelReq info) {
        AuthUser authUser = AuthUtils.getObject();
        cashBusinessService.cancelOrder(authUser.getId(), info);
        return new ApiResult().success(new OrderItemResp(info.getOrderId()));
    }

    @ApiOperation("订单支付")
    @PostMapping(value = "/order/pay")
    public ApiResult payOrder(@RequestBody OrderPayReq info) {
        AuthUser authUser = AuthUtils.getObject();
        String errorMsg = cashBusinessService.payOrder(authUser.getId(), info);
        if (StringUtils.isEmpty(errorMsg)) {
            return new ApiResult().success();
        } else {
            return new ApiResult().error(HttpStatus.BAD_REQUEST, errorMsg);
        }
    }

    @ApiOperation("查询订单状态")
    @PostMapping(value = "/order/status")
    public ApiResult<OrderPayStatusResp> payOrderStatus(@RequestBody OrderPayStatusReq info) {
        AuthUser authUser = AuthUtils.getObject();
        int retCode = cashBusinessService.payOrderStatus(authUser.getId(), info.getOrderId());
        if (retCode == 0) {
            //超时
            return new ApiResult().error(HttpStatus.TIMEOUT, "处理超时请重试");
        } else if (retCode == 1) {
            //成功
            return new ApiResult().success(new OrderPayStatusResp("2"));
        } else if (retCode == 2) {
            //失败
            return new ApiResult().success(new OrderPayStatusResp("3"));
        } else {
            //异常
            return new ApiResult().error("订单异常");
        }
    }
}
