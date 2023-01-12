package com.cloudtimes.app.controller;

import com.cloudtimes.common.annotation.Log;
import com.cloudtimes.common.core.controller.BaseController;
import com.cloudtimes.common.core.domain.AjaxResult;
import com.cloudtimes.common.core.page.TableDataInfo;
import com.cloudtimes.common.enums.BusinessType;
import com.cloudtimes.common.utils.poi.ExcelUtil;
import com.cloudtimes.ybf.domain.YbfMemberLeavingMessage;
import com.cloudtimes.ybf.service.IYbfMemberLeavingMessageService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 会员留言信息Controller
 *
 * @author polo
 * @date 2022-09-26
 */
@Api("会员留言处理接口")
@RestController
@RequestMapping("/ybf/leavingmessage")
public class AppMemberLeavingMessageController extends BaseController {
    @Autowired
    private IYbfMemberLeavingMessageService ybfMemberLeavingMessageService;

    /**
     * 新增会员留言信息
     */
    @Log(title = "会员提交新增留言信息", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    public AjaxResult add(@RequestBody YbfMemberLeavingMessage ybfMemberLeavingMessage) {

        ybfMemberLeavingMessage.setUsername(getAppLoginUser().getUsername());
        ybfMemberLeavingMessage.setNickName(getAppLoginUser().getUser().getNickName());
        ybfMemberLeavingMessage.setStatus(new Long(0));

        ybfMemberLeavingMessageService.insertYbfMemberLeavingMessage(ybfMemberLeavingMessage);
        return AjaxResult.success("提交成功");
    }


}
