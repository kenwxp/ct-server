package com.cloudtimes.app.controller.v1.agent;

import com.cloudtimes.account.domain.CtUserAgent;
import com.cloudtimes.account.service.ICtUserAgentService;
import com.cloudtimes.common.core.controller.BaseController;
import com.cloudtimes.common.core.domain.AjaxResult;
import com.cloudtimes.common.core.page.TableDataInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 代理Controller
 * 
 * @author 沈兵
 * @date 2023-02-07
 */
@RestController
@RequestMapping("/v1/agent/assets")
@Api(tags = "代理资产")
public class CtAgentAssetsController extends BaseController
{
    @Autowired
    private ICtUserAgentService ctUserAgentService;

    /**
     * 查询代理资产列表
     */
    @GetMapping("/list")
    @ApiOperation("查询代理资产列表")
    public TableDataInfo list(CtUserAgent ctUserAgent)
    {
        startPage();
        List<CtUserAgent> list = ctUserAgentService.selectCtUserAgentList(ctUserAgent);
        return getDataTable(list);
    }

    /**
     * 获取代理资产详细信息
     */
    @GetMapping(value = "/{userId}")
    @ApiOperation(value = "获取代理资产详细信息", response = CtUserAgent.class)
    public AjaxResult getInfo(@PathVariable("userId") String userId)
    {
        return AjaxResult.success(ctUserAgentService.selectCtUserAgentByUserId(userId));
    }
}
