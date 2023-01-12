package com.cloudtimes.app.controller;

import com.cloudtimes.common.core.controller.BaseController;
import com.cloudtimes.common.core.domain.AjaxResult;
import com.cloudtimes.ybf.domain.YbfPaymentChannel;
import com.cloudtimes.ybf.service.IYbfPaymentChannelService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 支付通道Controller
 *
 * @author polo
 * @date 2022-09-03
 */
@Api("支付通道AP接口")
@RestController
@RequestMapping("/ybf/paymentchannel")
public class AppPaymentChannelController extends BaseController {
    @Autowired
    private IYbfPaymentChannelService ybfPaymentChannelService;

    /**
     * 查询支付通道列表
     */
    @ApiOperation("查询支付通道列表")
    @GetMapping("/list")
    public AjaxResult list(YbfPaymentChannel ybfPaymentChannel) {
        List<YbfPaymentChannel> list = ybfPaymentChannelService.selectYbfPaymentChannelList(ybfPaymentChannel);
        return AjaxResult.success(list);
    }


}
