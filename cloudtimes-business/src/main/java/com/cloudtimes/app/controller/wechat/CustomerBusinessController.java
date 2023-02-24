package com.cloudtimes.app.controller.wechat;

import com.cloudtimes.app.controller.wechat.model.ScanCodeReq;
import com.cloudtimes.app.controller.wechat.model.ScanCodeResp;
import com.cloudtimes.app.models.ApiResult;
import com.cloudtimes.common.core.domain.AjaxResult;
import com.cloudtimes.common.core.domain.entity.AuthUser;
import com.cloudtimes.common.enums.ChannelType;
import com.cloudtimes.common.utils.AuthUtils;
import com.cloudtimes.common.utils.StringUtils;
import com.cloudtimes.serving.wechat.service.ICtCustomerBusinessService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@Api(tags = "小程序业务相关接口")
@RestController
@RequestMapping("/mapp/business")
public class CustomerBusinessController {
    @Autowired
    private ICtCustomerBusinessService businessService;


    @ApiOperation("扫码流程")
    @PostMapping("/check")
    public ApiResult<ScanCodeResp> scanCode(@RequestBody ScanCodeReq param) {
        AuthUser authUser = AuthUtils.getObject();
        if (StringUtils.equals(authUser.getChannelType(), ChannelType.WECHAT.getCode())) {
            return new ApiResult().error("渠道类型不匹配");
        }
        // 校验参数
        if (StringUtils.isEmpty(param.getShopId())) {
            return new ApiResult().error("门店号不能为空");
        }
        if (StringUtils.isEmpty(param.getDynamicCode())) {
            return new ApiResult().error("动态码不能为空");
        }
        if (StringUtils.isEmpty(param.getDid())) {
            return new ApiResult().error("设备号不能为空");
        }
        Map<String, String> retMap = businessService.scanCode(authUser.getId(), param.getShopId(), param.getDynamicCode(), param.getDid());
        ScanCodeResp respData = new ScanCodeResp();
        respData.setIsSupervise(retMap.get("isSupervise"));
        respData.setShoppingId(retMap.get("shoppingId"));
        return new ApiResult().success(respData);
    }

}
