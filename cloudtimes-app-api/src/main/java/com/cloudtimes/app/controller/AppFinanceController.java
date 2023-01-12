package com.cloudtimes.app.controller;

import com.cloudtimes.common.core.controller.BaseController;
import com.cloudtimes.common.core.domain.AjaxResult;
import com.cloudtimes.ybf.domain.YbfFinance;
import com.cloudtimes.ybf.service.IYbfFinanceService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 理财产品（绿色能量）Controller
 *
 * @author polo
 * @date 2022-09-02
 */
@Api("绿色能量API接口")
@RestController
@RequestMapping("/ybf/finance")
public class AppFinanceController extends BaseController {
    @Autowired
    private IYbfFinanceService ybfFinanceService;

    /**
     * 查询理财产品（绿色能量）列表
     */
    @ApiOperation("查询理财产品（绿色能量）列表")
    @GetMapping("/list")
    public AjaxResult list(YbfFinance ybfFinance) {
        ybfFinance.setEnabled("1");
        List<YbfFinance> list = ybfFinanceService.selectYbfFinanceList(ybfFinance);
        return AjaxResult.success(list);
    }


    /**
     * 获取理财产品（绿色能量）详细信息
     */
    @ApiOperation("获取理财产品（绿色能量）详细信息")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id) {
        return AjaxResult.success(ybfFinanceService.selectYbfFinanceById(id));
    }

}
