package com.cloudtimes.app.controller.mobile;

import com.cloudtimes.app.constant.PrefixPathConstants;
import com.cloudtimes.common.core.controller.BaseController;
import com.cloudtimes.common.core.domain.ApiResult;
import com.cloudtimes.common.core.domain.entity.AuthUser;
import com.cloudtimes.common.utils.AuthUtils;
import com.cloudtimes.common.utils.PageUtils;
import com.cloudtimes.business.mobile.domain.*;
import com.cloudtimes.business.mobile.service.ICtShopBossBusinessService;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Tag(name = "店家app业务相关接口")
@RestController
@RequestMapping(PrefixPathConstants.MOBILE_PATH_PREFIX + "/business")
public class ShopBossBusinessController extends BaseController {
    @Autowired
    private ICtShopBossBusinessService shopBossBusinessService;

    @Operation(summary = "用户修改密码")
    @PostMapping("/password")
    public ApiResult changePassword(@RequestBody ChangePasswordReq param) {
        AuthUser authUser = AuthUtils.getObject();
        shopBossBusinessService.changePassword(authUser.getId(), param);
        return new ApiResult().success();
    }

    @Operation(summary = "申请云值守")
    @PostMapping("/supervise/apply")
    public ApiResult applySupervise(@RequestBody ApplySuperviseReq param) {
        AuthUser authUser = AuthUtils.getObject();
        shopBossBusinessService.applySupervise(authUser.getId(), param);
        return new ApiResult().success();
    }

    @Operation(summary = "查询门店列表")
    @PostMapping("/shop/list")
    public ApiResult<List<GetShopListResp>> getShopList(@RequestBody GetShopListReq param) {
        AuthUser authUser = AuthUtils.getObject();
        List<GetShopListResp> shopList = shopBossBusinessService.getShopList(authUser.getId(), param);
        return new ApiResult().success(shopList);
    }

    @Operation(summary = "查询门店详情")
    @PostMapping("/shop/detail")
    public ApiResult<GetShopDetailResp> getShopDetail(@RequestBody GetShopDetailReq param) {
        AuthUser authUser = AuthUtils.getObject();
        GetShopDetailResp shopDetail = shopBossBusinessService.getShopDetail(authUser.getId(), param);
        return new ApiResult().success(shopDetail);
    }

    @Operation(summary = "查询门店访问人数排行")
    @PostMapping("/shop/rank")
    public ApiResult<List<GetShopRankResp>> getShopRank() {
        AuthUser authUser = AuthUtils.getObject();
        List<GetShopRankResp> shopRank = shopBossBusinessService.getShopRank(authUser.getId());
        return new ApiResult().success(shopRank);
    }

    @Operation(summary = "店家开门")
    @PostMapping("/shop/open/door")
    public ApiResult getShopOpenDoor(@RequestBody GetShopDetailReq param) {
        AuthUser authUser = AuthUtils.getObject();
        shopBossBusinessService.getShopOpenDoor(authUser.getId(), param);
        return new ApiResult().success();
    }

    @Operation(summary = "查询门店设备列表")
    @PostMapping("/device/list")
    public ApiResult<List<GetDeviceListResp>> getDeviceList(@RequestBody GetDeviceListReq param) {
        AuthUser authUser = AuthUtils.getObject();
        List<GetDeviceListResp> deviceList = shopBossBusinessService.getDeviceList(authUser.getId(), param);
        return new ApiResult().success(deviceList);
    }

    @Operation(summary = "查询门店值守日志列表")
    @PostMapping("/supervise/list")
    public ApiResult<List<GetSuperviseListResp>> getSuperviseList(@RequestBody GetSuperviseListReq param) {
        ApiResult apiResult = new ApiResult();
        AuthUser authUser = AuthUtils.getObject();
        PageUtils.startPage(param.getPageNum(), param.getPageSize());
        List<GetSuperviseListResp> list = shopBossBusinessService.getSuperviseList(authUser.getId(), param);
        apiResult.setTotal(getDataTable(list).getTotal());
        return apiResult.success(list);
    }

    @Operation(summary = "门店访问量汇总统计数据")
    @PostMapping("/stat/sum/visit")
    public ApiResult<ShopVisitSumResp> getStatVisitSum(@RequestBody ShopStatSumReq param) {
        AuthUser authUser = AuthUtils.getObject();
        ShopVisitSumResp resp = shopBossBusinessService.getStatVisitSum(authUser.getId(), param);
        return new ApiResult().success(resp);
    }

    @Operation(summary = "门店访问量图表统计数据")
    @PostMapping("/stat/chart/visit")
    public ApiResult<List<ShopStatChartResp>> getStatVisitChart(@RequestBody ShopStatChartReq param) {
        AuthUser authUser = AuthUtils.getObject();
        List<ShopStatChartResp> list = shopBossBusinessService.getStatVisitChart(authUser.getId(), param);
        return new ApiResult().success(list);
    }

    @Operation(summary = "门店营收汇总统计数据")
    @PostMapping("/stat/sum/income")
    public ApiResult<ShopIncomeSumResp> getStatIncomeSum(@RequestBody ShopStatSumReq param) {
        AuthUser authUser = AuthUtils.getObject();
        ShopIncomeSumResp resp = shopBossBusinessService.getStatIncomeSum(authUser.getId(), param);
        return new ApiResult().success(resp);
    }

    @Operation(summary = "门店营收图表统计数据")
    @PostMapping("/stat/chart/income")
    public ApiResult<List<ShopStatChartResp>> getStatIncomeChart(@RequestBody ShopStatChartReq param) {
        AuthUser authUser = AuthUtils.getObject();
        List<ShopStatChartResp> list = shopBossBusinessService.getStatIncomeChart(authUser.getId(), param);
        return new ApiResult().success(list);
    }

    @Operation(summary = "门店24小时访问量及销售额统计")
    @PostMapping("/stat/sum/24h")
    public ApiResult<ShopStat24hSumResp> getStatIncomeSum(@RequestBody GetShopDetailReq param) {
        AuthUser authUser = AuthUtils.getObject();
        ShopStat24hSumResp resp = shopBossBusinessService.getStatIncomeSum(authUser.getId(), param);
        return new ApiResult().success(resp);
    }

    @Operation(summary = "查询订单列表")
    @PostMapping("/order/list")
    public ApiResult<List<GetOrderListResp>> getOrderList(@RequestBody GetOrderListReq param) {
        ApiResult apiResult = new ApiResult();
        AuthUser authUser = AuthUtils.getObject();
        PageUtils.startPage(param.getPageNum(), param.getPageSize());
        List<GetOrderListResp> list = shopBossBusinessService.getOrderList(authUser.getId(), param);
        apiResult.setTotal(getDataTable(list).getTotal());
        return new ApiResult().success(list);
    }

    @Operation(summary = "查询订单详情")
    @PostMapping("/order/detail")
    public ApiResult<GetOrderDetailResp> getOrderDetail(@RequestBody GetOrderDetailReq param) {
        AuthUser authUser = AuthUtils.getObject();
        GetOrderDetailResp resp = shopBossBusinessService.getOrderDetail(authUser.getId(), param);
        return new ApiResult().success(resp);
    }


    @Operation(summary = "获取订单本地回放")
    @PostMapping("/video/order")
    public ApiResult<GetOrderLocalVideoResp> getOrderLocalVideo(@RequestBody GetOrderLocalVideoReq param) {
        AuthUser authUser = AuthUtils.getObject();
        GetOrderLocalVideoResp resp = shopBossBusinessService.getOrderLocalVideo(authUser.getId(), param);
        return new ApiResult().success(resp);
    }

    @Operation(summary = "更新商品")
    @PostMapping("/product/sync")
    public ApiResult syncProduct(@RequestBody SyncProductReq param) {
        AuthUser authUser = AuthUtils.getObject();
        shopBossBusinessService.syncProduct(authUser.getId(), param);
        return new ApiResult().success();
    }
}
