package com.cloudtimes.app.controller;

import java.util.List;
import javax.servlet.http.HttpServletResponse;

import com.cloudtimes.common.core.domain.entity.YbfMember;
import com.cloudtimes.ybf.service.IYbfMemberService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.cloudtimes.common.annotation.Log;
import com.cloudtimes.common.core.controller.BaseController;
import com.cloudtimes.common.core.domain.AjaxResult;
import com.cloudtimes.common.enums.BusinessType;
import com.cloudtimes.ybf.domain.YbfMemberAgent;
import com.cloudtimes.ybf.service.IYbfMemberAgentService;
import com.cloudtimes.common.utils.poi.ExcelUtil;
import com.cloudtimes.common.core.page.TableDataInfo;

/**
 * 会员代理区域Controller
 *
 * @author polo
 * @date 2022-10-10
 */
@RestController
@RequestMapping("/ybf/memgeragent")
public class AppMemberAgentController extends BaseController {
    @Autowired
    private IYbfMemberAgentService ybfMemberAgentService;

    @Autowired
    private IYbfMemberService memberService;

    /**
     * 查询会员代理区域列表
     */
    @PreAuthorize("@ss.hasPermi('ybf:memgeragent:list')")
    @GetMapping("/list")
    public TableDataInfo list(YbfMemberAgent ybfMemberAgent) {
        startPage();
        List<YbfMemberAgent> list = ybfMemberAgentService.selectYbfMemberAgentList(ybfMemberAgent);
        return getDataTable(list);
    }

    /**
     * 导出会员代理区域列表
     */
    @PreAuthorize("@ss.hasPermi('ybf:memgeragent:export')")
    @Log(title = "会员代理区域", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, YbfMemberAgent ybfMemberAgent) {
        List<YbfMemberAgent> list = ybfMemberAgentService.selectYbfMemberAgentList(ybfMemberAgent);
        ExcelUtil<YbfMemberAgent> util = new ExcelUtil<YbfMemberAgent>(YbfMemberAgent.class);
        util.exportExcel(response, list, "会员代理区域数据");
    }

    /**
     * 获取会员代理区域详细信息
     */
    @PreAuthorize("@ss.hasPermi('ybf:memgeragent:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id) {
        return AjaxResult.success(ybfMemberAgentService.selectYbfMemberAgentById(id));
    }

    /**
     * 新增会员代理区域
     */
    @PreAuthorize("@ss.hasPermi('ybf:memgeragent:add')")
    @Log(title = "会员代理区域", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody YbfMemberAgent ybfMemberAgent) {

        YbfMember member = memberService.selectYbfMemberByUsername(ybfMemberAgent.getUsername());
        if(member == null){
            return AjaxResult.error("[" + ybfMemberAgent.getUsername() + "]未找到该会员！");
        }
        if (member.getIsAgent() != 1) {
            return AjaxResult.error("[" + ybfMemberAgent.getUsername() + "]还不是代理会员，无法添加代理区域！");
        }

        return toAjax(ybfMemberAgentService.insertYbfMemberAgent(ybfMemberAgent));
    }

    /**
     * 修改会员代理区域
     */
    @PreAuthorize("@ss.hasPermi('ybf:memgeragent:edit')")
    @Log(title = "会员代理区域", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody YbfMemberAgent ybfMemberAgent) {
        return toAjax(ybfMemberAgentService.updateYbfMemberAgent(ybfMemberAgent));
    }

    /**
     * 删除会员代理区域
     */
    @PreAuthorize("@ss.hasPermi('ybf:memgeragent:remove')")
    @Log(title = "会员代理区域", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids) {
        return toAjax(ybfMemberAgentService.deleteYbfMemberAgentByIds(ids));
    }
}
