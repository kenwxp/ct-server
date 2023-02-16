package com.cloudtimes.app.controller.wechat;

import com.alibaba.druid.util.StringUtils;
import com.cloudtimes.app.controller.wechat.model.ScanCodeReq;
import com.cloudtimes.app.controller.wechat.model.ScanCodeResp;
import com.cloudtimes.common.core.domain.AjaxResult;
import com.cloudtimes.common.core.domain.entity.AuthUser;
import com.cloudtimes.common.enums.ChannelType;
import com.cloudtimes.common.utils.AuthUtils;
import com.cloudtimes.serving.wechat.service.ICtCustomerBusinessService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = "小程序业务相关接口")
@RestController
@RequestMapping("/mapp/business")
public class CustomerBusinessController {
    @Autowired
    private ICtCustomerBusinessService businessService;


    @ApiOperation("扫码流程")
    @PostMapping("/check")
    public AjaxResult scanCode(@RequestBody ScanCodeReq param) {
        AuthUser authUser = AuthUtils.getObject();
        if (StringUtils.equals(authUser.getChannelType(), ChannelType.WECHAT.getCode())) {
            return AjaxResult.error("渠道类型不匹配");
        }
        //todo 校验参数

        businessService.scanCode(authUser.getId(), param.getShopId(), param.getDynamicCode(), param.getDid());
        ScanCodeResp loginCheckResp = new ScanCodeResp();
        return AjaxResult.success();
    }

}
