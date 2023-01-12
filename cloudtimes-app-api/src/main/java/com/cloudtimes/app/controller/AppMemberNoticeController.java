package com.cloudtimes.app.controller;

import com.cloudtimes.common.core.controller.BaseController;
import com.cloudtimes.common.core.domain.AjaxResult;
import com.cloudtimes.common.core.domain.entity.YbfMember;
import com.cloudtimes.common.core.page.TableDataInfo;
import com.cloudtimes.common.core.redis.RedisCache;
import com.cloudtimes.system.domain.SysNotice;
import com.cloudtimes.system.service.ISysNoticeService;
import com.cloudtimes.ybf.domain.YbfMemberNotice;
import com.cloudtimes.ybf.domain.vo.NoticeVO;
import com.cloudtimes.ybf.service.IYbfMemberFlowDetailsService;
import com.cloudtimes.ybf.service.IYbfMemberFriendshipService;
import com.cloudtimes.ybf.service.IYbfMemberNoticeService;
import com.cloudtimes.ybf.service.IYbfMemberService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 会员消息Controller
 *
 * @author polo
 * @date 2022-09-23
 */
@ApiOperation("会员消息API接口")
@RestController
@RequestMapping("/ybf/membernotice")
public class AppMemberNoticeController extends BaseController {
    @Autowired
    private IYbfMemberNoticeService ybfMemberNoticeService;

    @Autowired
    private IYbfMemberFlowDetailsService memberFlowDetailsService;

    @Autowired
    private IYbfMemberFriendshipService memberFriendshipService;

    @Autowired
    private IYbfMemberService memberService;

    @Autowired
    private ISysNoticeService noticeService;

    @Autowired
    private RedisCache redisCache;


    /**
     * 查询会员消息列表
     */
    @ApiOperation("查询会员消息列表")
    @GetMapping("/list")
    public TableDataInfo list(YbfMemberNotice ybfMemberNotice) {
        this.loadSysNotice(getAppUsername());
        startPage();
        ybfMemberNotice.setUsername(getAppUsername());
        List<YbfMemberNotice> list = ybfMemberNoticeService.selectYbfMemberNoticeList(ybfMemberNotice);
        return getDataTable(list);
    }


    @ApiOperation("会员类型消息统计")
    @GetMapping("/noticeOneType")
    public AjaxResult noticeOneType() {
        this.loadSysNotice(getAppUsername());
        Map<Integer, NoticeVO> list = new HashMap<>();
        NoticeVO noticeVO;
        for (int i = 3; i < 9; i++) {
            noticeVO = new NoticeVO();
            noticeVO.setNoticeType(i);
            noticeVO.setNotReadCount(ybfMemberNoticeService.countNoReadNotice(i, getAppUsername()));
            noticeVO.setLastMemberNotice(ybfMemberNoticeService.getLastMemberNotice(i, getAppUsername()));
            list.put(i, noticeVO);
        }
        return AjaxResult.success(list);
    }

    public void loadSysNotice(String username) {
        SysNotice notice = new SysNotice();
        notice.setStatus("0");
        List<SysNotice> noticeList = noticeService.selectNoticeList(notice);
        if (noticeList != null && noticeList.size() > 0) {
            YbfMemberNotice ybfMemberNotice = new YbfMemberNotice();
            ybfMemberNotice.setUsername(getAppUsername());
            ybfMemberNotice.setType(3L);
            List<YbfMemberNotice> list = ybfMemberNoticeService.selectYbfMemberNoticeList(ybfMemberNotice);
            for (SysNotice sysNotice : noticeList) {
                boolean isExists = false;
                for (YbfMemberNotice notice1 : list) {
                    if (sysNotice.getNoticeTitle().equals(notice1.getBrieflyText())) {
                        isExists = true;
                        break;
                    }
                }

                if (!isExists) {
                    YbfMemberNotice addNotice = new YbfMemberNotice();
                    addNotice.setType(3L);
                    addNotice.setUsername(username);
                    addNotice.setReadStatus(1L);
                    addNotice.setBrieflyText(sysNotice.getNoticeTitle());
                    addNotice.setDescText(sysNotice.getNoticeContent());
                    ybfMemberNoticeService.insertYbfMemberNotice(addNotice);
                }
            }
        }

    }

    @ApiOperation("获取会员消息详细信息")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id) {
        YbfMemberNotice ybfMemberNotice = ybfMemberNoticeService.selectYbfMemberNoticeById(id);
        if (ybfMemberNotice.getReadStatus() == 1) {
            ybfMemberNotice.setReadStatus(new Long(0));
            ybfMemberNoticeService.updateYbfMemberNotice(ybfMemberNotice);
        }
        return AjaxResult.success(ybfMemberNotice);
    }


    @ApiOperation("获取会员浇水施肥最近10条信息")
    @GetMapping(value = "/jssf")
    public AjaxResult jssf() {
        return AjaxResult.success(memberFlowDetailsService.selectYbfMemberFlowDetailsLastTo10());
    }

    @ApiOperation("获取会员直属下级有几个会员即将成为VIP，而自己不是VIP")
    @GetMapping(value = "/vipfriend")
    public AjaxResult vipfriend() {
        YbfMember member = memberService.selectYbfMemberByUsername(getAppUsername());
        if (member.getIsVip() == null || member.getIsVip() == 0) { //自身还不是VIP，查它直属下级有几个是VIP
            int count = memberFriendshipService.countDircetyMemeber(member.getUsername());
            return AjaxResult.success(count);
        }
        return AjaxResult.success(0);
    }

    @ApiOperation("获取会员直属下级即将成为会员VIP会员列表")
    @GetMapping(value = "/dircetyFriendSoonVip")
    public AjaxResult dircetyFriendSoonVip() {
        YbfMember member = memberService.selectYbfMemberByUsername(getAppUsername());
        if (member.getIsVip() == null || member.getIsVip() == 0) { //自身还不是VIP，查它直属下级有几个是VIP
            return AjaxResult.success(memberFriendshipService.findDircetyMemeberSoonVip(member.getUsername()));
        }
        return AjaxResult.success();
    }

}
