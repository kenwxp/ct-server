package com.cloudtimes.app.controller;

import com.cloudtimes.common.annotation.RateLimiter;
import com.cloudtimes.common.core.controller.BaseController;
import com.cloudtimes.common.core.domain.AjaxResult;
import com.cloudtimes.common.core.domain.entity.YbfMember;
import com.cloudtimes.common.enums.LimitType;
import com.cloudtimes.common.utils.DateUtils;
import com.cloudtimes.system.service.ISysConfigService;
import com.cloudtimes.ybf.domain.YbfMemberFlowDetails;
import com.cloudtimes.ybf.domain.YbfMemberGame;
import com.cloudtimes.ybf.service.IYbfMemberFlowDetailsService;
import com.cloudtimes.ybf.service.IYbfMemberGameService;
import com.cloudtimes.ybf.service.IYbfMemberNoticeService;
import com.cloudtimes.ybf.service.IYbfMemberService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.aspectj.weaver.loadtime.Aj;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 会员种树游戏数据Controller
 *
 * @author polo
 * @date 2022-09-29
 */
@Api("会员游戏处理接口")
@RestController
@RequestMapping("/ybf/game")
public class AppMemberGameController extends BaseController {
    @Autowired
    private IYbfMemberGameService ybfMemberGameService;
    @Autowired
    private ISysConfigService configService;

    @Autowired
    private IYbfMemberService ybfMemberService;

    @Autowired
    private IYbfMemberNoticeService ybfMemberNoticeService;


    @Autowired
    private IYbfMemberFlowDetailsService ybfMemberFlowDetailsService;


    /**
     * 获取会员种树游戏数据详细信息
     */
    @ApiOperation("会员游戏详情")
    @GetMapping(value = "/info")
    public AjaxResult getInfo() {
        YbfMemberGame ybfMemberGame = ybfMemberGameService.selectYbfMemberGameByUsername(getAppUsername());
        if (ybfMemberGame == null) {
            ybfMemberGame = new YbfMemberGame();
            ybfMemberGame.setUsername(getAppUsername());
            ybfMemberGame.setProgress(BigDecimal.valueOf(new Long(-1)));
            ybfMemberGame.setScoreAmount(BigDecimal.ZERO);
            resetDaily(ybfMemberGame);
            ybfMemberGameService.insertYbfMemberGame(ybfMemberGame);
        } else {
            resetDaily(ybfMemberGame);
            ybfMemberGameService.updateYbfMemberGame(ybfMemberGame);
        }
        return AjaxResult.success(ybfMemberGame);
    }


    /**
     * 获取会员种树游戏数据详细信息
     */
    @RateLimiter(limitType = LimitType.IP, count = 5, time = 10)
    @ApiOperation("游戏浇水施肥")
    @GetMapping(value = "/operation")
    public AjaxResult operation(int opType) {
        YbfMemberGame ybfMemberGame = ybfMemberGameService.selectYbfMemberGameByUsername(getAppUsername());
        resetDaily(ybfMemberGame);


        YbfMember ybfMember = ybfMemberService.selectYbfMemberByUsername(getAppUsername());
        BigDecimal totalScore = BigDecimal.valueOf(Long.parseLong(configService.selectConfigByKey("game.total.score")));
        if (opType == 1) {
            if (ybfMemberGame.getProgress().compareTo(BigDecimal.valueOf(100)) >= 0) {
                return AjaxResult.success(ybfMemberGame);
            }
            long shui = Long.parseLong(configService.selectConfigByKey("game.every.shui"));
            BigDecimal reward = BigDecimal.valueOf(Double.parseDouble(configService.selectConfigByKey("game.everyshui.score")));
            if (ybfMemberGame.getShuiCount() < shui) {
                return AjaxResult.error("水量不足！");
            }
            ybfMemberGame.setShuiCount(ybfMemberGame.getShuiCount() - shui);
            ybfMemberGame.setShuiLastTime(DateUtils.getNowDate());

            BigDecimal beforScore = BigDecimal.valueOf(ybfMemberGame.getScoreAmount().doubleValue());

            ybfMemberGame.setScoreAmount(ybfMemberGame.getScoreAmount().add(reward));
            ybfMemberGame.setProgress(ybfMemberGame.getScoreAmount().divide(totalScore).multiply(BigDecimal.valueOf(100)));
            ybfMemberGameService.updateYbfMemberGame(ybfMemberGame);

            ybfMemberNoticeService.addMemberNotice(getAppUsername(),  new Long(5), "低碳植树浇水积分奖励", "恭喜您！获得低碳植树" + reward + "D积分奖励。");

            createMemberFlowDetail(ybfMember, beforScore, ybfMemberGame.getScoreAmount(), reward, "浇水获得" + reward + "积分奖励！");
        } else if (opType == 2) {
            if (ybfMemberGame.getProgress().compareTo(BigDecimal.valueOf(100)) >= 0) {
                return AjaxResult.success(ybfMemberGame);
            }
            long fei = Long.parseLong(configService.selectConfigByKey("game.every.fei"));
            BigDecimal feireward = BigDecimal.valueOf(Double.parseDouble(configService.selectConfigByKey("game.everyfei.score")));
            if (ybfMemberGame.getFeiCount() < fei) {
                return AjaxResult.error("化肥不足！");
            }
            ybfMemberGame.setFeiCount(ybfMemberGame.getFeiCount() - fei);
            ybfMemberGame.setFeiLastTime(DateUtils.getNowDate());

            BigDecimal beforScore = BigDecimal.valueOf(ybfMemberGame.getScoreAmount().doubleValue());

            ybfMemberGame.setScoreAmount(ybfMemberGame.getScoreAmount().add(feireward));

            ybfMemberGame.setProgress(ybfMemberGame.getScoreAmount().divide(totalScore).multiply(BigDecimal.valueOf(100)));
            ybfMemberGameService.updateYbfMemberGame(ybfMemberGame);

            ybfMemberNoticeService.addMemberNotice(getAppUsername(),  new Long(5), "低碳植树施肥积分奖励", "恭喜您！获得低碳植树" + feireward + "D积分奖励。");

            createMemberFlowDetail(ybfMember, beforScore, ybfMemberGame.getScoreAmount(), feireward, "施肥获得" + feireward + "积分奖励！");

        } else if (opType == 3) {
            if (ybfMemberGame.getScoreAmount().compareTo(totalScore) >= 0) {

                BigDecimal beforeAmount = BigDecimal.valueOf(ybfMember.getScoreAmount().doubleValue());

                YbfMember updateMember = new YbfMember();
                updateMember.setId(ybfMember.getId());
                updateMember.setScoreAmount(ybfMember.getScoreAmount().add(totalScore));
                ybfMemberService.updateYbfMember(updateMember);

                ybfMemberGame.setScoreAmount(BigDecimal.ZERO);
                ybfMemberGame.setProgress(BigDecimal.valueOf(Double.valueOf(-1)));
                ybfMemberGameService.updateYbfMemberGame(ybfMemberGame);

                ybfMemberNoticeService.addMemberNotice(getAppUsername(), new Long(5), "低碳植树积分奖励", "恭喜您！获得低碳植树" + totalScore + "D积分奖励。");

                YbfMemberFlowDetails ybfMemberFlowDetails = new YbfMemberFlowDetails();
                ybfMemberFlowDetails.setFlowDetailsType(new Long(5));
                ybfMemberFlowDetails.setUsername(getAppUsername());
                ybfMemberFlowDetails.setMoneyType(new Long(0));
                ybfMemberFlowDetails.setDescText("摘取植树游戏成熟结果获得积分奖励");
                ybfMemberFlowDetails.setBeforeAmount(beforeAmount);
                ybfMemberFlowDetails.setNickName(ybfMember.getNickName());
                ybfMemberFlowDetails.setAmount(totalScore);
                ybfMemberFlowDetails.setAlterAmount(ybfMember.getScoreAmount());
                ybfMemberFlowDetails.setOperateType(new Long(0));
                ybfMemberFlowDetailsService.insertYbfMemberFlowDetails(ybfMemberFlowDetails);
            }
        } else {
            ybfMemberGame.setProgress(BigDecimal.ZERO);
            ybfMemberGameService.updateYbfMemberGame(ybfMemberGame);
        }

        return AjaxResult.success(ybfMemberGame);
    }

    public void createMemberFlowDetail(YbfMember member, BigDecimal beforSocre, BigDecimal afterSocre, BigDecimal socre, String desc) {
        YbfMemberFlowDetails ybfMemberFlowDetails = new YbfMemberFlowDetails();
        ybfMemberFlowDetails.setFlowDetailsType(new Long(8));
        ybfMemberFlowDetails.setUsername(getAppUsername());
        ybfMemberFlowDetails.setMoneyType(new Long(0));
        ybfMemberFlowDetails.setDescText(desc);
        ybfMemberFlowDetails.setBeforeAmount(beforSocre);
        ybfMemberFlowDetails.setNickName(member.getNickName());
        ybfMemberFlowDetails.setAmount(socre);
        ybfMemberFlowDetails.setAlterAmount(afterSocre);
        ybfMemberFlowDetails.setOperateType(new Long(0));
        ybfMemberFlowDetailsService.insertYbfMemberFlowDetails(ybfMemberFlowDetails);
    }


    private void resetDaily(YbfMemberGame ybfMemberGame) {
        Date now = DateUtils.getNowDate();
        if (ybfMemberGame.getShuiLastTime() == null || !DateUtils.isSameDay(ybfMemberGame.getShuiLastTime(), now)) {
            ybfMemberGame.setShuiCount(Long.parseLong(configService.selectConfigByKey("game.daily.rewardshui")));
        }

        if (ybfMemberGame.getFeiLastTime() == null || !DateUtils.isSameDay(ybfMemberGame.getFeiLastTime(), now)) {
            ybfMemberGame.setFeiCount(Long.parseLong(configService.selectConfigByKey("game.daily.rewardfei")));
        }
    }

}
