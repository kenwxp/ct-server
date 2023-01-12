package com.cloudtimes.app.controller;

import com.cloudtimes.common.core.controller.BaseController;
import com.cloudtimes.common.core.domain.AjaxResult;
import com.cloudtimes.common.core.domain.entity.YbfMember;
import com.cloudtimes.common.utils.DateUtils;
import com.cloudtimes.ybf.domain.YbfMemberFlowDetails;
import com.cloudtimes.ybf.domain.YbfMemberFriendshipReward;
import com.cloudtimes.ybf.domain.YbfMemberGame;
import com.cloudtimes.ybf.domain.YbfPeakMarket;
import com.cloudtimes.ybf.service.*;
import io.swagger.annotations.ApiOperation;
import org.apache.poi.util.ArrayUtil;
import org.aspectj.weaver.loadtime.Aj;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.*;

/**
 * 峰值大盘走每日利率Controller
 *
 * @author polo
 * @date 2022-10-05
 */
@RestController
@RequestMapping("/ybf/peakmarket")
public class AppPeakMarketController extends BaseController {
    @Autowired
    private IYbfPeakMarketService ybfPeakMarketService;

    @Autowired
    private IYbfMemberFinanceService ybfMemberFinanceService;

    @Autowired
    private IYbfMemberService memberService;

    @Autowired
    private IYbfMemberGameService memberGameService;

    @Autowired
    private IYbfMemberFlowDetailsService memberFlowDetailsService;

    @Autowired
    private IYbfMemberFriendshipRewardService memberFriendshipRewardService;

    @ApiOperation("查询会员积分信息")
    @GetMapping("/scoreInfo")
    public AjaxResult getMemberScoreInfo() {
        AjaxResult ajaxResult = AjaxResult.success("获取成功！");

        YbfMember ybfMember = memberService.selectYbfMemberByUsername(getAppUsername());
        BigDecimal frozenTotalScore = BigDecimal.ZERO;
        YbfMemberGame memberGame = memberGameService.selectYbfMemberGameByUsername(getAppUsername());
        if (memberGame != null) {
            frozenTotalScore.add(memberGame.getScoreAmount());
        }
        frozenTotalScore = frozenTotalScore.add(ybfMemberFinanceService.totalScore(getAppUsername()));
        ajaxResult.put("frozenTotalScore", frozenTotalScore);
        ajaxResult.put("totalScore", ybfMember.getScoreAmount().add(frozenTotalScore));
        ajaxResult.put("desirableScore", ybfMember.getScoreAmount());

        return ajaxResult;
    }

    @ApiOperation("查询最近7日的峰值大盘利率")
    @GetMapping("/peakData7day")
    public AjaxResult staticsPeakData() {
        AjaxResult ajaxResult = AjaxResult.success("查询成功");

        // YbfPeakMarket ybfPeakMarket = new YbfPeakMarket();
        //   Date yestoday = DateUtils.getNowDate();
        //    Date day7 = DateUtils.addDays(yestoday, -7);
        // ybfPeakMarket.getParams().put("beginCreateTime", DateUtils.toDayStartTime(day7));
        // ybfPeakMarket.getParams().put("endCreateTime", DateUtils.toDayEndTime(DateUtils.getNowDate()));

        List<YbfPeakMarket> list = ybfPeakMarketService.selectYbfPeakMarket7dayList();
        Collections.reverse(list);
        String[] times = new String[list.size()];
        double[] datas = new double[list.size()];
        DecimalFormat df = new DecimalFormat("#####0.00");
        for (int i = 0; i < list.size(); i++) {
            YbfPeakMarket peakMarket = list.get(i);
            times[i] = DateUtils.parseDateToStr("MM.dd", peakMarket.getCreateTime());
            datas[i] = Double.parseDouble(df.format(peakMarket.getDailyRate().doubleValue() * 100d));
        }

        ajaxResult.put("categories", times);
        ajaxResult.put("data", datas);

        return ajaxResult;
    }

    @ApiOperation("查询最近7日的种树积分")
    @GetMapping("/game7day")
    public AjaxResult staticsGame7day() {
        AjaxResult ajaxResult = AjaxResult.success("查询成功");
        Date today = DateUtils.getNowDate();

        String[] times = new String[7];
        List<Double> dataList = new ArrayList<Double>();

        DecimalFormat df = new DecimalFormat("#####0.00");
        for (int i = 0; i < 7; i++) {
            Date time = DateUtils.addDays(today, -1 * i);
            times[i] = DateUtils.parseDateToStr("MM.dd", time);
            dataList.add(queryGameScore(getAppUsername(), time).doubleValue());
        }

        List<String> itemsList = Arrays.asList(times);
        Collections.reverse(itemsList);
        Collections.reverse(dataList);

        ajaxResult.put("categories", itemsList.toArray());
        ajaxResult.put("data", dataList.toArray());

        return ajaxResult;
    }


    public BigDecimal queryGameScore(String username, Date time) {

        YbfMemberFlowDetails query = new YbfMemberFlowDetails();
        query.setUsername(username);
        query.setFlowDetailsType(new Long(8));
        query.getParams().put("beginCreateTime", DateUtils.toDayStartTime(time));
        query.getParams().put("endCreateTime", DateUtils.toDayEndTime(time));

        BigDecimal gameSocre = memberFlowDetailsService.staticsGameSocre(query);

        return gameSocre == null ? BigDecimal.ZERO : gameSocre;
    }


    @ApiOperation("查询最近7日购物释放积分")
    @GetMapping("/buyShopRewards")
    public AjaxResult staticsBuyShopReward7day() {
        AjaxResult ajaxResult = AjaxResult.success("查询成功");
        Date today = DateUtils.getNowDate();

        String[] times = new String[7];
        List<Double> dataList = new ArrayList<Double>();

        DecimalFormat df = new DecimalFormat("#####0.00");
        for (int i = 0; i < 7; i++) {
            Date time = DateUtils.addDays(today, -1 * i);
            times[i] = DateUtils.parseDateToStr("MM.dd", time);
            dataList.add(queryBuyShopScore(getAppUsername(), time).doubleValue());
        }

        List<String> itemsList = Arrays.asList(times);
        Collections.reverse(itemsList);
        Collections.reverse(dataList);

        ajaxResult.put("categories", itemsList.toArray());
        ajaxResult.put("data", dataList.toArray());

        return ajaxResult;
    }


    public BigDecimal queryBuyShopScore(String username, Date time) {

        YbfMemberFlowDetails query = new YbfMemberFlowDetails();
        query.setUsername(username);
        query.setFlowDetailsType(new Long(2));
        query.getParams().put("beginCreateTime", DateUtils.toDayStartTime(time));
        query.getParams().put("endCreateTime", DateUtils.toDayEndTime(time));

        BigDecimal gameSocre = memberFlowDetailsService.staticsGameSocre(query);
        return gameSocre == null ? BigDecimal.ZERO : gameSocre;
    }


    @ApiOperation("查询最近7日复合元素积分")
    @GetMapping("/friendShipRewards")
    public AjaxResult staticsFriendShipReward7day() {
        AjaxResult ajaxResult = AjaxResult.success("查询成功");
        Date today = DateUtils.getNowDate();

        String[] times = new String[7];
        List<Double> dataList = new ArrayList<Double>();

        DecimalFormat df = new DecimalFormat("#####0.00");
        for (int i = 0; i < 7; i++) {
            Date time = DateUtils.addDays(today, -1 * i);
            times[i] = DateUtils.parseDateToStr("MM.dd", time);
            dataList.add(queryFriendShipScore(getAppUsername(), time).doubleValue());
        }

        List<String> itemsList = Arrays.asList(times);
        Collections.reverse(itemsList);
        Collections.reverse(dataList);

        ajaxResult.put("categories", itemsList.toArray());
        ajaxResult.put("data", dataList.toArray());

        return ajaxResult;
    }


    public BigDecimal queryFriendShipScore(String username, Date time) {
        YbfMemberFriendshipReward query = new YbfMemberFriendshipReward();
        query.setUsername(username);
        query.setFriendshipType(new Long(1));
        query.getParams().put("beginCreateTime", DateUtils.toDayStartTime(time));
        query.getParams().put("endCreateTime", DateUtils.toDayEndTime(time));

        BigDecimal gameSocre = memberFriendshipRewardService.staticsFriendShipRewardSocre(query);
        return gameSocre == null ? BigDecimal.ZERO : gameSocre;
    }

}
