package com.cloudtimes.app.controller;

import com.cloudtimes.common.annotation.Log;
import com.cloudtimes.common.core.controller.BaseController;
import com.cloudtimes.common.core.domain.AjaxResult;
import com.cloudtimes.common.core.page.TableDataInfo;
import com.cloudtimes.common.enums.BusinessType;
import com.cloudtimes.common.utils.poi.ExcelUtil;
import com.cloudtimes.ybf.domain.YbfMemberFriendshipReward;
import com.cloudtimes.ybf.service.IYbfMemberFriendshipRewardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 我的销售业绩记录Controller
 *
 * @author polo
 * @date 2022-10-11
 */
@RestController
@RequestMapping("/ybf/friendshipreward")
public class AppMemberFriendshipRewardController extends BaseController {
    @Autowired
    private IYbfMemberFriendshipRewardService ybfMemberFriendshipRewardService;

    @GetMapping("/list")
    public TableDataInfo list(YbfMemberFriendshipReward ybfMemberFriendshipReward) {
        startPage();
        ybfMemberFriendshipReward.setUsername(getAppUsername());
        List<YbfMemberFriendshipReward> list = ybfMemberFriendshipRewardService.selectYbfMemberFriendshipRewardList(ybfMemberFriendshipReward);
        return getDataTable(list);
    }


}
