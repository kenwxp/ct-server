package com.cloudtimes.web.controller.station;

import com.cloudtimes.common.core.domain.ApiResult;
import com.cloudtimes.common.utils.SecurityUtils;
import com.cloudtimes.station.domain.*;
import com.cloudtimes.station.service.ICtSuperviseStationService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@Api(tags = "在线值守相关接口")
@RestController
@RequestMapping("/station/supervise")
public class CtSuperviseStationController {
    @Autowired
    private ICtSuperviseStationService superviseStationService;

    // 门店区域视频树查询
    @ApiOperation(value = "门店区域视频树查询", notes = "station:supervise:videoTree")
    @PreAuthorize("@ss.hasPermi('station:supervise:videoTree')")
    @PostMapping(value = "/videoTree")
    public ApiResult<List<VideoTreeNode>> getVideoTree() {
        List<VideoTreeNode> videoTree = superviseStationService.getVideoTree(SecurityUtils.getUserId());
        return new ApiResult().success(videoTree);
    }

    // 获取语音模版列表
    @ApiOperation(value = "获取语音模版列表", notes = "station:supervise:audioTemplate")
    @PreAuthorize("@ss.hasPermi('station:supervise:audioTemplate')")
    @PostMapping(value = "/audioTemplate")
    public ApiResult<List<GetAudioTemplateResp>> getAudioTemplate() {
        List<GetAudioTemplateResp> audioTemplate = superviseStationService.getAudioTemplate();
        return new ApiResult().success(audioTemplate);
    }

    //语音接入
    @ApiOperation(value = "语音接入", notes = "station:supervise:joinAudio")
    @PreAuthorize("@ss.hasPermi('station:supervise:joinAudio')")
    @PostMapping(value = "/joinAudio")
    public ApiResult<JoinAudioResp> joinAudio(@RequestBody @Valid JoinAudioReq param) {
        return new ApiResult().success(superviseStationService.joinAudio(SecurityUtils.getUserId(), param));
    }

    // 应急开门
    @ApiOperation(value = "应急开门", notes = "station:supervise:openDoor")
    @PreAuthorize("@ss.hasPermi('station:supervise:openDoor')")
    @PostMapping(value = "/openDoor")
    public ApiResult openDoor(@RequestBody @Valid OpenDoorReq param) {
        superviseStationService.openDoor(SecurityUtils.getUserId(), param);
        return new ApiResult().success();
    }

    // 锁门，解锁
    @ApiOperation(value = "值守页锁门", notes = "station:supervise:lockDoor")
    @PreAuthorize("@ss.hasPermi('station:supervise:lockDoor')")
    @PostMapping(value = "/lockDoor")
    public ApiResult lockDoor(@RequestBody @Valid LockDoorReq param) {
        superviseStationService.lockDoor(SecurityUtils.getUserId(), param);
        return new ApiResult().success();
    }

    // 订单审核
    @ApiOperation(value = "订单审核", notes = "station:supervise:approveOrder")
    @PreAuthorize("@ss.hasPermi('station:supervise:approveOrder')")
    @PostMapping(value = "/approveOrder")
    public ApiResult approveOrder(@RequestBody ApproveOrderReq param) {
        superviseStationService.approveOrder(SecurityUtils.getUserId(), SecurityUtils.getUsername(), param);
        return new ApiResult().success();
    }

    // 新建事件（异常）
    @ApiOperation(value = "新建事件（异常）", notes = "station:supervise:createEvent")
    @PreAuthorize("@ss.hasPermi('station:supervise:createEvent')")
    @PostMapping(value = "/createEvent")
    public ApiResult createEvent(@RequestBody CreateEventReq param) {
        superviseStationService.createEvent(SecurityUtils.getUserId(), SecurityUtils.getUsername(), param);
        return new ApiResult().success();
    }

    // 接单开关维护
    @ApiOperation(value = "接单开关维护", notes = "station:supervise:acceptTask")
    @PreAuthorize("@ss.hasPermi('station:supervise:acceptTask')")
    @PostMapping(value = "/acceptTask")
    public ApiResult acceptTask(@RequestBody AcceptTaskReq param) {
        superviseStationService.acceptTask(SecurityUtils.getUserId(), param);
        return new ApiResult().success();
    }
    // 申请调度

    // 换岗
//    @ApiOperation(value = "移交任务接口", tags = {"station:supervise:handoverTask"})
//    @PreAuthorize("@ss.hasPermi('station:supervise:handoverTask')")
//    @PostMapping(value = "/handoverTask")
//    public ApiResult handoverTask(@RequestBody HandoverTaskReq param) {
//        LoginUser loginUser = SecurityUtils.getLoginUser();
//        return new ApiResult().success();
//    }
    // 结束任务
    @ApiOperation(value = "结束任务接口", notes = "station:supervise:finishTask")
    @PreAuthorize("@ss.hasPermi('station:supervise:finishTask')")
    @PostMapping(value = "/finishTask")
    public ApiResult finishTask(@RequestBody FinishTaskReq param) {
        superviseStationService.finishTask(SecurityUtils.getUserId(), param);
        return new ApiResult().success();
    }
}
