package com.cloudtimes.app.controller.cash;

import com.cloudtimes.app.controller.cash.model.*;
import com.cloudtimes.common.constant.HttpStatus;
import com.cloudtimes.common.core.domain.ApiResult;
import com.cloudtimes.common.core.domain.entity.AuthUser;
import com.cloudtimes.common.enums.ChannelType;
import com.cloudtimes.common.utils.AuthUtils;
import com.cloudtimes.common.utils.JWTManager;
import com.cloudtimes.common.utils.StringUtils;
import com.cloudtimes.partner.pay.shouqianba.domain.AuthInfoData;
import com.cloudtimes.product.domain.CtShopProduct;
import com.cloudtimes.serving.cash.service.ICtCashBusinessService;
import com.cloudtimes.serving.cash.service.ICtCashLoginService;
import com.cloudtimes.serving.cash.service.domain.VoiceTokenData;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Api(tags = "收银机业务相关接口")
@RestController
@RequestMapping("/cash/business")
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
        if (!StringUtils.equals(authUser.getChannelType(), ChannelType.CASH.getCode())) {
            return new ApiResult().error("渠道类型错误");
        }
        if (StringUtils.isEmpty(info.getRawdata())) {
            return new ApiResult().error("rawdata不能为空");
        }
        if (StringUtils.isEmpty(info.getAuthType())) {
            return new ApiResult().error("凭证类型未设置");
        }
        AuthInfoData faceAuthInfo = cashBusinessService.getFaceAuthInfo(authUser.getId(), info.getRawdata(), info.getAuthType());
        if (faceAuthInfo == null) {
            return new ApiResult().error();
        } else {
            return new ApiResult().success(faceAuthInfo);
        }
    }

    @ApiOperation("根据刷脸token获取订单号")
    @PostMapping(value = "/face/order")
    public ApiResult<GetOrderIdResp> getOrderId(@RequestBody GetOrderIdReq info) {
        AuthUser authUser = AuthUtils.getObject();
        if (!StringUtils.equals(authUser.getChannelType(), ChannelType.CASH.getCode())) {
            return new ApiResult().error("渠道类型错误");
        }
        Map<String, String> orderInfo = cashBusinessService.getOrderId(authUser.getId(), info.getToken());
        GetOrderIdResp resp = new GetOrderIdResp();
        resp.setOrderId(orderInfo.get("orderId"));
        resp.setCustomerPhone(orderInfo.get("phone"));
        return new ApiResult().success(resp);
    }

    @ApiOperation("拉取商品列表")
    @PostMapping(value = "/product/fetch")
    public ApiResult<List<GetProductListResp>> getProductList() {
        AuthUser authUser = AuthUtils.getObject();
        if (!StringUtils.equals(authUser.getChannelType(), ChannelType.CASH.getCode())) {
            return new ApiResult().error("渠道类型错误");
        }
        List<CtShopProduct> productList = cashBusinessService.getProductList(authUser.getId());
        List<GetProductListResp> resp = new ArrayList<>();
        for (CtShopProduct dbProduct :
                productList) {
            GetProductListResp getProductListResp = new GetProductListResp();
            getProductListResp.setProductUid(dbProduct.getId());
            getProductListResp.setProductName(dbProduct.getProductName());
            getProductListResp.setCategoryUid(dbProduct.getCategoryId());
            getProductListResp.setCategoryName(dbProduct.getCategoryName());
            getProductListResp.setBarcode(dbProduct.getBarcode());
            getProductListResp.setImageUrl(dbProduct.getPictureUrl());
            getProductListResp.setBuyPrice(dbProduct.getPurchasePrice().intValue());
            getProductListResp.setSellPrice(dbProduct.getRetailPrice().intValue());
            getProductListResp.setCustomerPrice(dbProduct.getVipPrice().intValue());
            int isCustomerDiscount = 0;
            if (StringUtils.equals(dbProduct.getIsEnableVipPrice().toUpperCase(), "Y")) {
                isCustomerDiscount = 1;
            }
            getProductListResp.setIsCustomerDiscount(isCustomerDiscount);
            resp.add(getProductListResp);
        }
        return new ApiResult().success(resp);
    }

    @ApiOperation("获取语音token")
    @PostMapping(value = "/voice/token")
    public ApiResult<GetVoiceTokenResp> getVoiceToken() {
        AuthUser authUser = AuthUtils.getObject();
        if (!StringUtils.equals(authUser.getChannelType(), ChannelType.CASH.getCode())) {
            return new ApiResult().error("渠道类型错误");
        }
        VoiceTokenData voiceToken = cashBusinessService.getVoiceToken(authUser.getId());
        GetVoiceTokenResp resp = new GetVoiceTokenResp();
        resp.setAppId(voiceToken.getAppId());
        resp.setVoiceToken(voiceToken.getVoiceToken());
        resp.setChannelName(voiceToken.getChannelName());
        resp.setUid(voiceToken.getUid());
        return new ApiResult().success(resp);
    }

    @ApiOperation("订单加商品")
    @PostMapping(value = "/order/item/add")
    public ApiResult<OrderItemResp> addOrderItem(@RequestBody OrderItemReq info) {
        AuthUser authUser = AuthUtils.getObject();
        if (!StringUtils.equals(authUser.getChannelType(), ChannelType.CASH.getCode())) {
            return new ApiResult().error("渠道类型错误");
        }
        String newOrderId = cashBusinessService.addOrderItem(authUser.getId(),
                info.getOrderId(),
                info.getIsSupervise(),
                info.getGoodId(),
                info.getGoodName(),
                info.getCategoryId(),
                info.getCategoryName(),
                info.getNum(),
                info.getBuyPrice(),
                info.getSellPrice());
        return new ApiResult().success(new OrderItemResp(newOrderId));
    }

    @ApiOperation("订单减商品")
    @PostMapping(value = "/order/item/delete")
    public ApiResult<OrderItemResp> deleteOrderItem(@RequestBody OrderItemReq info) {
        AuthUser authUser = AuthUtils.getObject();
        if (!StringUtils.equals(authUser.getChannelType(), ChannelType.CASH.getCode())) {
            return new ApiResult().error("渠道类型错误");
        }
        cashBusinessService.deleteOrderItem(authUser.getId(), info.getOrderId(), info.getGoodId(), info.getNum());
        return new ApiResult().success(new OrderItemResp(info.getOrderId()));
    }

    @ApiOperation("取消订单")
    @PostMapping(value = "/order/cancel")
    public ApiResult<OrderItemResp> cancelOrder(@RequestBody OrderItemReq info) {
        AuthUser authUser = AuthUtils.getObject();
        if (!StringUtils.equals(authUser.getChannelType(), ChannelType.CASH.getCode())) {
            return new ApiResult().error("渠道类型错误");
        }
        cashBusinessService.cancelOrder(authUser.getId(), info.getOrderId());
        return new ApiResult().success(new OrderItemResp(info.getOrderId()));
    }

    @ApiOperation("订单支付")
    @PostMapping(value = "/order/pay")
    public ApiResult payOrder(@RequestBody OrderPayReq info) {
        AuthUser authUser = AuthUtils.getObject();
        if (!StringUtils.equals(authUser.getChannelType(), ChannelType.CASH.getCode())) {
            return new ApiResult().error("渠道类型错误");
        }
        String errorMsg = cashBusinessService.payOrder(authUser.getId(), info.getOrderId(), info.getPayType(), info.getPayCode(), info.getTotalAmount(), info.getTotalNum());
        if (StringUtils.isEmpty(errorMsg)) {
            return new ApiResult().success();
        } else {
            return new ApiResult().error(HttpStatus.BAD_REQUEST, errorMsg);
        }
    }

    @ApiOperation("查询订单状态")
    @PostMapping(value = "/order/status")
    public ApiResult<OrderPayStatusResp> payOrderStatus(@RequestBody OrderPayReq info) {
        AuthUser authUser = AuthUtils.getObject();
        if (!StringUtils.equals(authUser.getChannelType(), ChannelType.CASH.getCode())) {
            return new ApiResult().error("渠道类型错误");
        }
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
