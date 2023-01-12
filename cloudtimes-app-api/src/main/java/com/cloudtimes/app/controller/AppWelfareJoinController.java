package com.cloudtimes.app.controller;

import com.cloudtimes.common.core.controller.BaseController;
import com.cloudtimes.common.core.domain.AjaxResult;
import com.cloudtimes.common.core.domain.entity.YbfMember;
import com.cloudtimes.ybf.domain.YbfWelfareJoin;
import com.cloudtimes.ybf.service.IYbfMemberNoticeService;
import com.cloudtimes.ybf.service.IYbfMemberService;
import com.cloudtimes.ybf.service.IYbfWelfareJoinService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 公益活动参于者Controller
 *
 * @author polo
 * @date 2022-09-01
 */
@Api("会员处理接口")
@RestController
@RequestMapping("/ybf/welfarejoin")
public class AppWelfareJoinController extends BaseController {
    @Autowired
    private IYbfWelfareJoinService ybfWelfareJoinService;

    @Autowired
    private IYbfMemberService ybfMemberService;

    @Autowired
    private IYbfMemberNoticeService memberNoticeService;

    /**
     * 新增公益活动参于者
     */
    @ApiOperation("公益活动参于者报名")
    @PostMapping
    public AjaxResult add(@RequestBody YbfWelfareJoin ybfWelfareJoin) {

        YbfWelfareJoin query = new YbfWelfareJoin();
        query.setUsername(getAppUsername());
        query.setWelfareId(ybfWelfareJoin.getWelfareId());
        List<YbfWelfareJoin> list = ybfWelfareJoinService.selectYbfWelfareJoinList(query);

        if (!list.isEmpty() && list.size() > 0) {
            return AjaxResult.error("您已参于报名！");
        }

        YbfMember member = ybfMemberService.selectYbfMemberByUsername(getAppUsername());
        ybfWelfareJoin.setNickName(member.getNickName());
        ybfWelfareJoin.setUsername(member.getUsername());
        ybfWelfareJoinService.insertYbfWelfareJoin(ybfWelfareJoin);

        memberNoticeService.addMemberNotice(member.getUsername(), new Long(7), "恭喜您3.12植树节报名成功", "3.12植树节活动报名成功，敬请等待平台客服与你电话联系！");

        return AjaxResult.success("活动报名成功！");
    }


    @ApiOperation("检测参与者是否已经报名")
    @GetMapping("/checkedJoin/{welfareId}")
    public AjaxResult checkJoin(@PathVariable("welfareId") Long welfareId) {
        YbfWelfareJoin query = new YbfWelfareJoin();
        query.setUsername(getAppUsername());
        query.setWelfareId(welfareId);
        List<YbfWelfareJoin> list = ybfWelfareJoinService.selectYbfWelfareJoinList(query);

        if (!list.isEmpty() && list.size() > 0) {
            return AjaxResult.error("您已经参与了报名");
        }
        return AjaxResult.success();
    }
}
