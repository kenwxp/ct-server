package com.cloudtimes.app.controller;

import com.cloudtimes.common.annotation.RepeatSubmit;
import com.cloudtimes.common.core.controller.BaseController;
import com.cloudtimes.common.core.domain.AjaxResult;
import com.cloudtimes.common.core.domain.entity.YbfMember;
import com.cloudtimes.common.payment.adapay.AdapayPaymentManager;
import com.cloudtimes.common.utils.SecurityUtils;
import com.cloudtimes.common.utils.StringUtils;
import com.cloudtimes.system.service.ISysConfigService;
import com.cloudtimes.ybf.domain.YbfMemberReplaceManager;
import com.cloudtimes.ybf.service.IYbfMemberFriendshipService;
import com.cloudtimes.ybf.service.IYbfMemberReplaceManagerService;
import com.cloudtimes.ybf.service.IYbfMemberService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.poi.util.StringUtil;
import org.aspectj.weaver.loadtime.Aj;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * 会员Controller
 *
 * @author polo
 * @date 2022-09-01
 */
@Api("会员处理接口")
@RequestMapping("/ybf/member")
@RestController
public class AppMemberController extends BaseController {
    @Autowired
    private IYbfMemberService ybfMemberService;

    @Autowired
    private IYbfMemberReplaceManagerService ybfMemberReplaceManagerService;

    @Autowired
    private IYbfMemberFriendshipService memberFriendshipService;

    @Autowired
    private AdapayPaymentManager adapayPaymentManager;

    @Autowired
    private ISysConfigService configService;

    /**
     * 会员详情
     *
     * @return 结果
     */
    @ApiOperation("会员详情")
    @GetMapping("/profile")
    public AjaxResult profile() {
        logger.info(getAppLoginUser().getUsername());
        YbfMember member = ybfMemberService.selectYbfMemberById(getAppUserId());
        AjaxResult ajax = AjaxResult.success(member);
        return ajax;
    }

    /**
     * 会员详情
     *
     * @return 结果
     */
    @RepeatSubmit(interval = 5000, message = "重复提交过于频繁，请稍候再试")
    @ApiOperation("会员详情")
    @PostMapping("/updateProfile")
    public AjaxResult updateProfile(YbfMember ybfMember) {
        YbfMember member = ybfMemberService.selectYbfMemberById(getAppUserId());
        ybfMember.setId(member.getId());

        if (ybfMember.getMarketFirstEnabled() != null) {
            return AjaxResult.error("涉及非法会员属性更新");
        }

        if (ybfMember.getMemberCode() != null) {
            return AjaxResult.error("涉及非法会员属性更新");
        }

        if (ybfMember.getIsVip() != null) {
            return AjaxResult.error("涉及非法会员属性更新");
        }

        if (ybfMember.getParentInviteCode() != null) {
            return AjaxResult.error("涉及非法会员属性更新");
        }

        if (ybfMember.getScoreAmount() != null) {
            return AjaxResult.error("涉及非法会员属性更新");
        }

        if (ybfMember.getMoneyAmount() != null) {
            return AjaxResult.error("涉及非法会员属性更新");
        }

        if (ybfMember.getMarketEnabled() != null && member.getIsVip() != 1) {
            return AjaxResult.error("普通用户无法开启！");
        }

        //检查用户是否为非首次开启峰值大盘，如果不是，无法再次开起，需联系管理员后台进行开启
        if (ybfMember.getMarketEnabled() != null && ybfMember.getMarketEnabled().intValue() == 1 && member.getMarketFirstEnabled().intValue() == 1) {
            return AjaxResult.error("无法开启，请联系管理员");
        }

        if (ybfMember.getMarketEnabled() != null && ybfMember.getMarketEnabled().intValue() == 1) {
            ybfMember.setMarketFirstEnabled(1);
        }

        ybfMemberService.updateYbfMember(ybfMember);
        AjaxResult ajax = AjaxResult.success("保存成功！");
        return ajax;
    }

    /**
     * 会员详情
     *
     * @return 结果
     */
    @ApiOperation("会员修改密码")
    @PostMapping("/updatePassword")
    public AjaxResult updatePassword(String oldPassword, String newPassword) {
        YbfMember member = ybfMemberService.selectYbfMemberById(getAppUserId());
        if (!SecurityUtils.matchesPassword(oldPassword, member.getPassword())) {
            AjaxResult ajax = AjaxResult.error("输入旧密码不正确！");
            return ajax;
        }

        YbfMember ybfMember = new YbfMember();
        ybfMember.setPassword(SecurityUtils.encryptPassword(newPassword));
        ybfMember.setId(member.getId());

        ybfMemberService.updateYbfMember(ybfMember);
        AjaxResult ajax = AjaxResult.success("修改成功！");
        return ajax;
    }


    /**
     * 获取会员的客户经理ID
     *
     * @return 结果
     */
    @ApiOperation("获取会员的客户经理ID")
    @GetMapping("/getCenterkfId")
    public AjaxResult getCenterkfId() {
        YbfMember member = ybfMemberService.selectYbfMemberById(getAppUserId());

        if (StringUtils.isEmpty(member.getParentInviteCode())) {
            AjaxResult ajax = AjaxResult.success();
            ajax.put("parentMemberCode", 0);
            return ajax;
        }

        YbfMember parent = ybfMemberService.selectYbfMemberByInviteCode(member.getParentInviteCode());
        AjaxResult ajax = AjaxResult.success();
        ajax.put("parentMemberCode", parent.getMemberCode());
        return ajax;
    }


    /**
     * 会员申请更换客户经理
     *
     * @return 结果
     */
    @RepeatSubmit(interval = 5000, message = "重复提交过于频繁，请稍候再试")
    @ApiOperation("会员申请更换客户经理")
    @PostMapping("/replaceParent")
    public AjaxResult replaceParent(String oldParentMemberCode, String newParentMemberCode) {
        YbfMember member = ybfMemberService.selectYbfMemberByUsername(getAppUsername());

        YbfMemberReplaceManager replaceManager = new YbfMemberReplaceManager();
        replaceManager.setUsername(member.getUsername());
        replaceManager.setStatus(new Long(0));
        List<YbfMemberReplaceManager> list = ybfMemberReplaceManagerService.selectYbfMemberReplaceManagerList(replaceManager);

        if (!list.isEmpty() && list.size() > 0) {
            AjaxResult ajax = AjaxResult.error("已申请过了，等待处理！");
            return ajax;
        }


        YbfMember parentMember = ybfMemberService.selectYbfMemberByInviteCode(member.getParentInviteCode());
        if (!parentMember.getMemberCode().equals(oldParentMemberCode)) {
            AjaxResult ajax = AjaxResult.error("旧经理ID输入不正确！");
            return ajax;
        }

        if (parentMember.getMemberCode().equals(newParentMemberCode)) {
            AjaxResult ajax = AjaxResult.error("新旧经理ID相同！");
            return ajax;
        }

        YbfMember newMember = ybfMemberService.selectYbfMemberByMemberCode(newParentMemberCode);
        if (newMember == null) {
            AjaxResult ajax = AjaxResult.error("新旧经理ID不正确！");
            return ajax;
        }

        YbfMemberReplaceManager ybfMemberReplaceManager = new YbfMemberReplaceManager();
        ybfMemberReplaceManager.setStatus(new Long(0));
        ybfMemberReplaceManager.setNickName(member.getNickName());
        ybfMemberReplaceManager.setOldManagerUsername(parentMember.getUsername());
        ybfMemberReplaceManager.setUsername(member.getUsername());
        ybfMemberReplaceManager.setManagerUsername(newMember.getUsername());
        ybfMemberReplaceManager.setOldMemberCode(parentMember.getMemberCode());
        ybfMemberReplaceManager.setNewMemberCode(newMember.getMemberCode());

        ybfMemberReplaceManagerService.insertYbfMemberReplaceManager(ybfMemberReplaceManager);

        AjaxResult ajax = AjaxResult.success("提交成功！");
        return ajax;
    }

    /**
     * 会员实名认证
     *
     * @return 结果
     */
    @RepeatSubmit(interval = 5000, message = "重复提交过于频繁，请稍候再试")
    @ApiOperation("会员实名认证")
    @PostMapping("/realNameAuth")
    public AjaxResult realNameAuth(YbfMember ybfMember) {
        YbfMember member = ybfMemberService.selectYbfMemberByUsername(getAppUsername());

        if (member.getPaymentBinding() == 1) {
            return AjaxResult.error("实名认证中");
        }

        if (member.getPaymentBinding() == 2) {
            return AjaxResult.error("已完成实名认证");
        }

        YbfMember updateMember = new YbfMember();
        updateMember.setUsername(member.getUsername());
        updateMember.setId(member.getId());
        updateMember.setIdNumber(ybfMember.getIdNumber());
        updateMember.setRealName(ybfMember.getRealName());
        updateMember.setSex(ybfMember.getSex());

        int enabled = Integer.parseInt(configService.selectConfigByKey("adapay.realRegister.enabled"));
        if (enabled != 1) {
            updateMember.setPaymentBinding(-1);
            ybfMemberService.updateYbfMember(updateMember);
            return AjaxResult.error("操作完成");
        }

//        if (member.getAdapayRegister() == 0) {
//            Map<String, Object> resultMap = adapayPaymentManager.executerCreateMember(member.getMemberCode(), member.getNickName());
//            if (resultMap.get("status").equals("succeeded")) {
//                ybfMember.setAdapayRegister(1);
//                ybfMemberService.updateYbfMember(ybfMember);
//            }
//        }

        //  ybfMember.setPaymentBinding(1);
        //    ybfMemberService.updateYbfMember(ybfMember);

        Map<String, Object> resultMap = adapayPaymentManager.executerCreateRealname(member.getMemberCode(), member.getUsername(), ybfMember.getRealName(), ybfMember.getIdNumber(), ybfMember.getSex());
        if (resultMap.get("status").equals("succeeded")) {
            updateMember.setPaymentBinding(2);
            ybfMemberService.updateYbfMember(updateMember);
            return AjaxResult.success("认证成功");
        } else {
            updateMember.setPaymentBinding(-1);
            ybfMemberService.updateYbfMember(updateMember);
            return AjaxResult.success("认证失败");
        }

    }

}


