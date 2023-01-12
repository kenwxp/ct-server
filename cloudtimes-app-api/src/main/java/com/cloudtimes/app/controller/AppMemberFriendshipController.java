package com.cloudtimes.app.controller;

import com.cloudtimes.common.annotation.Log;
import com.cloudtimes.common.core.controller.BaseController;
import com.cloudtimes.common.core.domain.AjaxResult;
import com.cloudtimes.common.core.domain.entity.YbfMember;
import com.cloudtimes.common.core.page.TableDataInfo;
import com.cloudtimes.common.enums.BusinessType;
import com.cloudtimes.common.utils.poi.ExcelUtil;
import com.cloudtimes.ybf.domain.YbfMemberFriendship;
import com.cloudtimes.ybf.service.IYbfMemberFriendshipService;
import com.cloudtimes.ybf.service.IYbfMemberService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 好友关系Controller
 *
 * @author polo
 * @date 2022-10-06
 */
@Api("好友关系API接口")
@RestController
@RequestMapping("/ybf/friendship")
public class AppMemberFriendshipController extends BaseController {
    @Autowired
    private IYbfMemberFriendshipService ybfMemberFriendshipService;

    @Autowired
    private IYbfMemberService memberService;

    /**
     * 查询直属好友列表
     */
    @GetMapping("/list")
    public AjaxResult list(YbfMemberFriendship ybfMemberFriendship) {
        ybfMemberFriendship.setFriendUsername(getAppUsername());
        List<YbfMemberFriendship> list = ybfMemberFriendshipService.selectYbfMemberFriendshipList(ybfMemberFriendship);
        YbfMember member;
        for (YbfMemberFriendship mf : list) {
            member = memberService.selectYbfMemberByUsername(mf.getUsername());
            mf.setUsername(member.getNickName());
        }

        int indirectCount = ybfMemberFriendshipService.indirectCount(getAppUsername());
        AjaxResult ajaxResult = AjaxResult.success(list);
        ajaxResult.put("directlyCount", list.size());
        ajaxResult.put("indirectCount", indirectCount);
        return ajaxResult;
    }


}
