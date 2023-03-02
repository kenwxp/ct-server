package com.cloudtimes.app.controller.mobile;

import com.alibaba.druid.util.StringUtils;
import com.cloudtimes.app.constant.PrefixPathConstants;
import com.cloudtimes.common.core.controller.BaseController;
import com.cloudtimes.common.core.domain.ApiResult;
import com.cloudtimes.common.core.domain.entity.AuthUser;
import com.cloudtimes.common.utils.AuthUtils;
import com.cloudtimes.common.utils.PageUtils;
import com.cloudtimes.serving.mobile.domain.*;
import com.cloudtimes.serving.mobile.service.ICtShopBossBusinessService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@Api(tags = "店家app业务相关接口")
@RestController
@RequestMapping(PrefixPathConstants.MOBILE_PATH_PREFIX + "/business")
public class ShopBossBusinessController extends BaseController {
    @Autowired
    private ICtShopBossBusinessService shopBossBusinessService;

    @ApiOperation("用户修改密码")
    @PostMapping("/password")
    public ApiResult changePassword(@RequestBody ChangePasswordReq param) {
        AuthUser authUser = AuthUtils.getObject();
        if (shopBossBusinessService.changePassword(authUser.getId(), param.getPasswordNew(), param.getPasswordOld())) {
            return new ApiResult().success();
        } else {
            return new ApiResult().error();
        }

    }

    @ApiOperation("申请云值守")
    @PostMapping("/supervise/apply")
    public ApiResult applySupervise(@RequestBody ApplySuperviseReq param) {
        AuthUser authUser = AuthUtils.getObject();
        
        if (StringUtils.isEmpty(param.getStoreId())) {
            return new ApiResult().error("门店id不能为空");
        }
        String opFlag = param.getOptFlag();
        if (!StringUtils.equals(opFlag, "0") && !StringUtils.equals(opFlag, "1")) {
            return new ApiResult().error("操作标志非法");
        }
        if (shopBossBusinessService.applySupervise(authUser.getId(), param.getStoreId(), param.getOptFlag())) {
            return new ApiResult().success();
        } else {
            return new ApiResult().error();
        }
    }

    @ApiOperation("查询门店列表")
    @PostMapping("/shop/list")
    public ApiResult<List<GetShopListResp>> getShopList(@RequestBody GetShopListReq param) {
        AuthUser authUser = AuthUtils.getObject();
        
        ArrayList<GetShopListResp> shopList = new ArrayList<>();
        return new ApiResult().success(shopList);
    }

    @ApiOperation("查询门店详情")
    @PostMapping("/shop/detail")
    public ApiResult<GetShopDetailResp> getShopDetail(@RequestBody GetShopDetailReq param) {
        AuthUser authUser = AuthUtils.getObject();
        
        GetShopDetailResp getShopDetailResp = new GetShopDetailResp();
        return new ApiResult().success(getShopDetailResp);
    }

    @ApiOperation("查询门店访问人数排行")
    @PostMapping("/shop/rank")
    public ApiResult<List<GetShopRankResp>> getShopRank() {
        AuthUser authUser = AuthUtils.getObject();
        
        GetShopDetailResp getShopDetailResp = new GetShopDetailResp();
        return new ApiResult().success(getShopDetailResp);
    }

    @ApiOperation("店家开门")
    @PostMapping("/shop/open/door")
    public ApiResult getShopOpenDoor(@RequestBody GetShopDetailReq param) {
        AuthUser authUser = AuthUtils.getObject();
        
        return new ApiResult().success();
    }

    @ApiOperation("查询门店设备列表")
    @PostMapping("/device/list")
    public ApiResult<List<GetDeviceListResp>> getDeviceList(@RequestBody GetDeviceListReq param) {
        AuthUser authUser = AuthUtils.getObject();
        
        GetDeviceListResp resp = new GetDeviceListResp();
        return new ApiResult().success(resp);
    }

    @ApiOperation("查询门店值守日志列表")
    @PostMapping("/supervise/list")
    public ApiResult<List<GetSuperviseListResp>> getSuperviseList(@RequestBody GetSuperviseListReq param) {
        ApiResult apiResult = new ApiResult();
        AuthUser authUser = AuthUtils.getObject();
   
        PageUtils.startPage(param.getPageNum(), param.getPageSize());
        List<GetSuperviseListResp> list = new ArrayList<>();
        GetSuperviseListResp resp = new GetSuperviseListResp();
        list.add(resp);
        apiResult.setTotal(getDataTable(list).getTotal());
        return apiResult.success(list);
    }

    @ApiOperation("门店访问量汇总统计数据")
    @PostMapping("/stat/sum/visit")
    public ApiResult<ShopVisitSumResp> getStatVisitSum(@RequestBody ShopStatSumReq param) {
        AuthUser authUser = AuthUtils.getObject();
        
        ShopVisitSumResp resp = new ShopVisitSumResp();
        return new ApiResult().success(resp);
    }

    @ApiOperation("门店访问量图表统计数据")
    @PostMapping("/stat/chart/visit")
    public ApiResult<List<ShopStatChartResp>> getStatVisitChart(@RequestBody ShopStatChartReq param) {
        AuthUser authUser = AuthUtils.getObject();
        
        List<ShopStatChartResp> list = new ArrayList<>();
        ShopStatChartResp resp = new ShopStatChartResp();
        list.add(resp);
        return new ApiResult().success(list);
    }

    @ApiOperation("门店营收汇总统计数据")
    @PostMapping("/stat/sum/income")
    public ApiResult<ShopIncomeSumResp> getStatIncomeSum(@RequestBody ShopStatSumReq param) {
        AuthUser authUser = AuthUtils.getObject();
        
        ShopIncomeSumResp resp = new ShopIncomeSumResp();
        return new ApiResult().success(resp);
    }

    @ApiOperation("门店营收图表统计数据")
    @PostMapping("/stat/chart/income")
    public ApiResult<List<ShopStatChartResp>> getStatIncomeChart(@RequestBody ShopStatChartReq param) {
        AuthUser authUser = AuthUtils.getObject();
        
        List<ShopStatChartResp> list = new ArrayList<>();
        ShopStatChartResp resp = new ShopStatChartResp();
        list.add(resp);
        return new ApiResult().success(list);
    }

    @ApiOperation("门店24小时访问量及销售额统计")
    @PostMapping("/stat/sum/24h")
    public ApiResult<ShopStat24hSumResp> getStatIncomeSum(@RequestBody GetShopDetailReq param) {
        AuthUser authUser = AuthUtils.getObject();
        
        ShopStat24hSumResp resp = new ShopStat24hSumResp();
        return new ApiResult().success(resp);
    }

    @ApiOperation("查询订单列表")
    @PostMapping("/order/list")
    public ApiResult<List<GetOrderListResp>> getOrderList(@RequestBody GetOrderListReq param) {
        ApiResult apiResult = new ApiResult();
        AuthUser authUser = AuthUtils.getObject();
   
        PageUtils.startPage(param.getPageNum(), param.getPageSize());
        List<GetOrderListResp> list = new ArrayList<>();
        GetOrderListResp resp = new GetOrderListResp();
        list.add(resp);
        apiResult.setTotal(getDataTable(list).getTotal());
        return apiResult.success(list);
    }

    @ApiOperation("查询订单详情")
    @PostMapping("/order/detail")
    public ApiResult<GetOrderDetailResp> getOrderDetail(@RequestBody GetOrderDetailReq param) {
        AuthUser authUser = AuthUtils.getObject();
        
        GetOrderDetailResp resp = new GetOrderDetailResp();
        return new ApiResult().success(resp);
    }


    @ApiOperation("获取订单本地回放")
    @PostMapping("/video/order")
    public ApiResult<GetOrderLocalVideoResp> getOrderLocalVideo(@RequestBody GetOrderLocalVideoReq param) {
        AuthUser authUser = AuthUtils.getObject();
        
        GetOrderLocalVideoResp resp = new GetOrderLocalVideoResp();
        return new ApiResult().success(resp);
    }

    @ApiOperation("更新商品")
    @PostMapping("/product/sync")
    public ApiResult syncProduct(@RequestBody SyncProductReq param) {
        AuthUser authUser = AuthUtils.getObject();
        
        return new ApiResult().success();
    }
}
