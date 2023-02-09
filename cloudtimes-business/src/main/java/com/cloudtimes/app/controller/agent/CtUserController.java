package com.cloudtimes.app.controller.agent;

import com.cloudtimes.account.domain.CtUser;
import com.cloudtimes.account.service.ICtUserService;
import com.cloudtimes.common.annotation.Log;
import com.cloudtimes.common.core.controller.BaseController;
import com.cloudtimes.common.core.domain.AjaxResult;
import com.cloudtimes.common.core.page.TableDataInfo;
import com.cloudtimes.common.enums.BusinessType;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 用户Controller
 * 
 * @author 沈兵
 * @date 2023-01-17
 */
@RestController
@RequestMapping("/v1/agent/ctuser")
@Api(tags = "用户管理")
public class CtUserController extends BaseController
{
    @Autowired
    private ICtUserService ctUserService;

    /**
     * 查询用户列表
     */
    @ApiOperation("查询用户列表")
    @GetMapping("/list")
    public TableDataInfo list(CtUser ctUser)
    {
        startPage();
        List<CtUser> list = ctUserService.selectCtUserList(ctUser);
        return getDataTable(list);
    }

    /**
     * 获取用户详细信息
     */
    @GetMapping(value = "/{id}")
    @ApiOperation("获取用户详细信息")
    public AjaxResult getInfo(@PathVariable("id") String id)
    {
        return AjaxResult.success(ctUserService.selectCtUserById(id));
    }

    /**
     * 新增用户
     */
    @Log(title = "用户", businessType = BusinessType.INSERT)
    @PostMapping
    @ApiOperation("新增用户")
    public AjaxResult add(@RequestBody CtUser ctUser)
    {
        return toAjax(ctUserService.insertCtUser(ctUser));
    }

    /**
     * 修改用户
     */
    @Log(title = "用户", businessType = BusinessType.UPDATE)
    @PutMapping
    @ApiOperation("修改用户")
    public AjaxResult edit(@RequestBody CtUser ctUser)
    {
        return toAjax(ctUserService.updateCtUser(ctUser));
    }
}
