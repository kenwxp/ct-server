package com.cloudtimes.app.controller.mobile;

import com.alibaba.druid.util.StringUtils;
import com.cloudtimes.app.controller.cash.model.GetProductListResp;
import com.cloudtimes.app.controller.mobile.model.*;
import com.cloudtimes.app.models.ApiResult;
import com.cloudtimes.common.core.controller.BaseController;
import com.cloudtimes.common.core.domain.AjaxResult;
import com.cloudtimes.common.core.domain.entity.AuthUser;
import com.cloudtimes.common.enums.ChannelType;
import com.cloudtimes.common.utils.AuthUtils;
import com.cloudtimes.common.utils.PageUtils;
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
@RequestMapping("/mobile/business")
public class ShopBossBusinessController extends BaseController {
    @Autowired
    private ICtShopBossBusinessService shopBossBusinessService;

    @ApiOperation("用户修改密码")
    @PostMapping("/password")
    public ApiResult changePassword(@RequestBody ChangePasswordReq param) {
        AuthUser authUser = AuthUtils.getObject();
        if (StringUtils.equals(authUser.getChannelType(), ChannelType.MOBILE.getCode())) {
            return new ApiResult().error("渠道类型不匹配");
        }
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
        if (StringUtils.equals(authUser.getChannelType(), ChannelType.MOBILE.getCode())) {
            return new ApiResult().error("渠道类型不匹配");
        }
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
        if (StringUtils.equals(authUser.getChannelType(), ChannelType.MOBILE.getCode())) {
            return new ApiResult().error("渠道类型不匹配");
        }
        ArrayList<GetShopListResp> shopList = new ArrayList<>();
        return new ApiResult().success(shopList);
    }

    @ApiOperation("查询门店详情")
    @PostMapping("/shop/detail")
    public ApiResult<GetShopDetailResp> getShopDetail(@RequestBody GetShopDetailReq param) {
        AuthUser authUser = AuthUtils.getObject();
        if (StringUtils.equals(authUser.getChannelType(), ChannelType.MOBILE.getCode())) {
            return new ApiResult().error("渠道类型不匹配");
        }
        GetShopDetailResp getShopDetailResp = new GetShopDetailResp();
        return new ApiResult().success(getShopDetailResp);
    }

    @ApiOperation("查询门店设备列表")
    @PostMapping("/device/list")
    public ApiResult<List<GetDeviceListResp>> getDeviceList(@RequestBody GetDeviceListReq param) {
        AuthUser authUser = AuthUtils.getObject();
        if (StringUtils.equals(authUser.getChannelType(), ChannelType.MOBILE.getCode())) {
            return new ApiResult().error("渠道类型不匹配");
        }
        GetDeviceListResp resp = new GetDeviceListResp();
        return new ApiResult().success(resp);
    }

    @ApiOperation("查询门店值守日志列表")
    @PostMapping("/supervise/list")
    public ApiResult<List<GetSuperviseListResp>> getSuperviseList() {
        AuthUser authUser = AuthUtils.getObject();
        if (StringUtils.equals(authUser.getChannelType(), ChannelType.MOBILE.getCode())) {
            return new ApiResult().error("渠道类型不匹配");
        }
        PageUtils.startPage();
        GetSuperviseListResp resp = new GetSuperviseListResp();
        getDataTable(new ArrayList<>()).getTotal();
        return new ApiResult().success(resp);
    }
}
