package com.cloudtimes.app.controller.cash;

import com.cloudtimes.app.controller.cash.model.*;
import com.cloudtimes.common.core.domain.AjaxResult;
import com.cloudtimes.common.core.domain.entity.AuthUser;
import com.cloudtimes.common.enums.ChannelType;
import com.cloudtimes.common.utils.AuthUtils;
import com.cloudtimes.common.utils.JWTManager;
import com.cloudtimes.common.utils.StringUtils;
import com.cloudtimes.partner.pay.shouqianba.domain.AuthInfoData;
import com.cloudtimes.product.domain.CtShopProduct;
import com.cloudtimes.serving.cash.service.ICtCashBusinessService;
import com.cloudtimes.serving.cash.service.ICtCashLoginService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

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
    public AjaxResult getFaceAuthInfo(@RequestBody GetFaceAuthInfoReq info) {
        AuthUser authUser = AuthUtils.getObject();
        if (!StringUtils.equals(authUser.getChannelType(), ChannelType.CASH.getCode())) {
            return AjaxResult.error("渠道类型错误");
        }
        AuthInfoData faceAuthInfo = cashBusinessService.getFaceAuthInfo(authUser.getId(), info.getRawdata(), info.getAuthType());
        if (faceAuthInfo == null) {
            return AjaxResult.error();
        } else {
            return AjaxResult.success(faceAuthInfo);
        }
    }

    @ApiOperation("根据刷脸token获取订单号")
    @PostMapping(value = "/face/order")
    public AjaxResult getOrderId(@RequestBody GetOrderIdReq info) {
        GetOrderIdResp resp = new GetOrderIdResp();
        return AjaxResult.success(resp);
    }

    @ApiOperation("拉取商品列表")
    @PostMapping(value = "/product/fetch")
    public AjaxResult GetProductList() {
        AuthUser authUser = AuthUtils.getObject();
        List<CtShopProduct> productList = cashBusinessService.getProductList(authUser.getId());
        List<GetProductListResp> resp = new ArrayList<>();
        for (CtShopProduct dbProduct :
                productList) {
            GetProductListResp getProductListResp = new GetProductListResp();
            getProductListResp.setProductUid(dbProduct.getId());
            getProductListResp.setProductName(dbProduct.getProductName());
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
        }
        return AjaxResult.success(resp);
    }

    @ApiOperation("获取语音token")
    @PostMapping(value = "/voice/token")
    public AjaxResult GetVoiceToken() {
        GetVoiceTokenResp resp = new GetVoiceTokenResp();
        return AjaxResult.success(resp);
    }

    @ApiOperation("订单加商品")
    @PostMapping(value = "/order/item/add")
    public AjaxResult AddOrderItem(@RequestBody OrderItemReq info) {
        OrderItemResp orderItemResp = new OrderItemResp();
        return AjaxResult.success(orderItemResp);
    }

    @ApiOperation("订单减商品")
    @PostMapping(value = "/order/item/delete")
    public AjaxResult DeleteOrderItem(@RequestBody OrderItemReq info) {
        OrderItemResp orderItemResp = new OrderItemResp();
        return AjaxResult.success(orderItemResp);
    }

    @ApiOperation("取消订单")
    @PostMapping(value = "/order/cancel")
    public AjaxResult CancelOrder(@RequestBody OrderItemReq info) {
        return AjaxResult.success();
    }

    @ApiOperation("订单支付")
    @PostMapping(value = "/order/pay")
    public AjaxResult PayOrder(@RequestBody OrderPayReq info) {
        return AjaxResult.success();
    }

    @ApiOperation("查询订单状态")
    @PostMapping(value = "/order/status")
    public AjaxResult PayOrderStatus(@RequestBody OrderPayReq info) {
        return AjaxResult.success();
    }
}
