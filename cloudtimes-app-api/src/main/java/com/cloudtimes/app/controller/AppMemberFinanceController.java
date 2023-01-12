package com.cloudtimes.app.controller;

import com.cloudtimes.common.annotation.RateLimiter;
import com.cloudtimes.common.annotation.RepeatSubmit;
import com.cloudtimes.common.core.controller.BaseController;
import com.cloudtimes.common.core.domain.AjaxResult;
import com.cloudtimes.common.core.page.TableDataInfo;
import com.cloudtimes.common.utils.DateUtils;
import com.cloudtimes.ybf.domain.YbfMemberFinance;
import com.cloudtimes.ybf.service.IYbfMemberFinanceLogsService;
import com.cloudtimes.ybf.service.IYbfMemberFinanceService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * 会员能量进度Controller
 *
 * @author polo
 * @date 2022-09-26
 */
@Api("会员能量进度API接口")
@RestController
@RequestMapping("/ybf/memberfinance")
public class AppMemberFinanceController extends BaseController {
    @Autowired
    private IYbfMemberFinanceService ybfMemberFinanceService;

    @Autowired
    private IYbfMemberFinanceLogsService financeLogsService;

    @ApiOperation("查询会员能量进度列表")
    @GetMapping("/list")
    public TableDataInfo list(YbfMemberFinance ybfMemberFinance) {
        startPage();
        ybfMemberFinance.setUsername(getAppUsername());
        List<YbfMemberFinance> list = ybfMemberFinanceService.selectYbfMemberFinanceListSX(getAppUsername());
        return getDataTable(list);
    }

    @RepeatSubmit(interval = 5000, message = "重复提交过于频繁，请稍候再试")
    @ApiOperation("提取能量")
    @GetMapping("/jieshuo/{id}")
    public AjaxResult jieshuo(@PathVariable("id") Long id) {
        ybfMemberFinanceService.jieshuo(id, getAppUsername());
        return AjaxResult.success("操作成功！");
    }


    @ApiOperation("查询会员未生息的能量进度列表")
    @GetMapping("/slist")
    public TableDataInfo slist() {
        startPage();
        YbfMemberFinance ybfMemberFinance = new YbfMemberFinance();
        ybfMemberFinance.setUsername(getAppUsername());
        List<YbfMemberFinance> list = ybfMemberFinanceService.selectYbfMemberFinanceLists(ybfMemberFinance);
        return getDataTable(list);
    }


    @ApiOperation("统计会员总体能量进度")
    @GetMapping("/statistics")
    public AjaxResult statisticsInfo() {
        AjaxResult ajaxResult = AjaxResult.success();
        Date yestoday = DateUtils.getNowDate();

        Calendar startTime = Calendar.getInstance();
        startTime.setTime(yestoday);
        startTime.set(Calendar.HOUR_OF_DAY, 0);
        startTime.set(Calendar.MINUTE, 0);
        startTime.set(Calendar.SECOND, 0);
        startTime.set(Calendar.MILLISECOND, 0);

        Calendar endTime = Calendar.getInstance();
        endTime.setTime(yestoday);
        endTime.set(Calendar.HOUR_OF_DAY, 23);
        endTime.set(Calendar.MINUTE, 59);
        endTime.set(Calendar.SECOND, 59);
        endTime.set(Calendar.MILLISECOND, 999);

        BigDecimal yestodayTotal = financeLogsService.totalYestodaySettlement(getAppUsername(), startTime.getTime(), endTime.getTime());
        BigDecimal settlementTotal = ybfMemberFinanceService.totalSettementScore(getAppUsername());
        BigDecimal scoreAmountTotal = ybfMemberFinanceService.totalScore(getAppUsername());

        yestodayTotal = yestodayTotal == null ? BigDecimal.ZERO : yestodayTotal;
        scoreAmountTotal = scoreAmountTotal == null ? BigDecimal.ZERO : scoreAmountTotal;

        ajaxResult.put("yestodayTotal", yestodayTotal);
        ajaxResult.put("settlementTotal", settlementTotal);
        ajaxResult.put("scoreAmountTotal", scoreAmountTotal);
        ajaxResult.put("sumScoreAmountTotal", settlementTotal == null ? BigDecimal.ZERO.add(scoreAmountTotal) : settlementTotal.add(scoreAmountTotal));
        ajaxResult.put("scoreAmountProcessTotal", settlementTotal == null ? BigDecimal.ZERO : settlementTotal.divide(scoreAmountTotal, BigDecimal.ROUND_CEILING));

        return ajaxResult;
    }

}
