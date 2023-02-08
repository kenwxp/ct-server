package com.cloudtimes.app.controller.mobile;

import com.alibaba.druid.util.StringUtils;
import com.cloudtimes.app.controller.mobile.model.ApplySuperviseReq;
import com.cloudtimes.common.core.domain.AjaxResult;
import com.cloudtimes.common.core.domain.entity.AuthUser;
import com.cloudtimes.common.enums.ChannelType;
import com.cloudtimes.common.utils.AuthUtils;
import com.cloudtimes.serving.mobile.service.ICtShopBossBusinessService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = "店家app登录相关接口")
@RestController
@RequestMapping("/mobile")
public class ShopBossBusinessController {
    @Autowired
    private ICtShopBossBusinessService shopBossBusinessService;

    @ApiOperation("申请云值守")
    @PostMapping("/supervise/apply")
    public AjaxResult applySupervise(@RequestBody ApplySuperviseReq param) {
        AuthUser authUser = AuthUtils.getObject();
        if (StringUtils.equals(authUser.getChannelType(), ChannelType.MOBILE.getCode())) {
            return AjaxResult.error("渠道类型不匹配");
        }
        if (StringUtils.isEmpty(param.getStoreId())) {
            return AjaxResult.error("门店id不能为空");
        }
        String opFlag = param.getOptFlag();
        if (!StringUtils.equals(opFlag, "0") && !StringUtils.equals(opFlag, "1")) {
            return AjaxResult.error("操作标志非法");
        }
        return shopBossBusinessService.applySupervise(authUser.getId(), param.getStoreId(), param.getOptFlag()) ? AjaxResult.success() : AjaxResult.error();
    }
}