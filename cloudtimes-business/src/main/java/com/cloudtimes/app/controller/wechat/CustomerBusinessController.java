package com.cloudtimes.app.controller.wechat;

import com.cloudtimes.app.constant.PrefixPathConstants;
import com.cloudtimes.common.core.domain.ApiResult;
import com.cloudtimes.common.core.domain.entity.AuthUser;
import com.cloudtimes.common.utils.AuthUtils;
import com.cloudtimes.serving.wechat.domain.ScanCodeReq;
import com.cloudtimes.serving.wechat.domain.ScanCodeResp;
import com.cloudtimes.serving.wechat.service.ICtCustomerBusinessService;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@Tag(name = "小程序业务相关接口")
@RestController
@RequestMapping(PrefixPathConstants.WX_MP_PATH_PREFIX + "/business")
public class CustomerBusinessController {
    @Autowired
    private ICtCustomerBusinessService businessService;


    @Operation(summary = "扫码流程")
    @PostMapping("/check")
    public ApiResult<ScanCodeResp> scanCode(@RequestBody @Valid ScanCodeReq param) {
        AuthUser authUser = AuthUtils.getObject();
        ScanCodeResp respData = businessService.scanCode(authUser.getId(), param);
        return new ApiResult().success(respData);
    }

}
