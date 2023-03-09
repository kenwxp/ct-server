package com.cloudtimes.app.controller.wechat;

import com.cloudtimes.app.constant.PrefixPathConstants;
import com.cloudtimes.serving.wechat.domain.ScanCodeReq;
import com.cloudtimes.serving.wechat.domain.ScanCodeResp;
import com.cloudtimes.common.core.domain.ApiResult;
import com.cloudtimes.common.core.domain.entity.AuthUser;
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
@RequestMapping(PrefixPathConstants.WX_MP_PATH_PREFIX + "/business")
public class CustomerBusinessController {
    @Autowired
    private ICtCustomerBusinessService businessService;


    @ApiOperation("扫码流程")
    @PostMapping("/check")
    public ApiResult<ScanCodeResp> scanCode(@RequestBody ScanCodeReq param) {
        AuthUser authUser = AuthUtils.getObject();
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
        ScanCodeResp respData = businessService.scanCode(authUser.getId(), param);
        return new ApiResult().success(respData);
    }

}
