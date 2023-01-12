package com.cloudtimes.app.controller;

import com.cloudtimes.common.core.controller.BaseController;
import com.cloudtimes.common.core.domain.AjaxResult;
import com.cloudtimes.ybf.domain.YbfAdvertisement;
import com.cloudtimes.ybf.service.IYbfAdvertisementService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 轮播广告Controller
 *
 * @author polo
 * @date 2022-10-06
 */
@Api("轮播图广告API接口")
@RestController
@RequestMapping("/ybf/advertisement")
public class AppAdvertisementController extends BaseController {
    @Autowired
    private IYbfAdvertisementService ybfAdvertisementService;

    /**
     * 查询轮播广告列表
     */
    @ApiOperation("查询轮播广告列表")
    @GetMapping("/list")
    public AjaxResult list(YbfAdvertisement ybfAdvertisement) {
        List<YbfAdvertisement> list = ybfAdvertisementService.selectYbfAdvertisementList(ybfAdvertisement);
        return AjaxResult.success(list);
    }

}
