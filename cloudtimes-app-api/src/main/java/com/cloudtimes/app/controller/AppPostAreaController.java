package com.cloudtimes.app.controller;

import com.cloudtimes.common.core.controller.BaseController;
import com.cloudtimes.common.core.domain.AjaxResult;
import com.cloudtimes.ybf.domain.YbfPostArea;
import com.cloudtimes.ybf.service.IYbfPostAreaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 快递特殊区域加价Controller
 *
 * @author POLO
 * @date 2022-10-09
 */
@RestController
@RequestMapping("/ybf/postarea")
public class AppPostAreaController extends BaseController {
    @Autowired
    private IYbfPostAreaService ybfPostAreaService;

    /**
     * 查询快递特殊区域加价列表
     */
    @GetMapping("/list")
    public AjaxResult list(YbfPostArea ybfPostArea) {
        List<YbfPostArea> list = ybfPostAreaService.selectYbfPostAreaList(ybfPostArea);
        return AjaxResult.success(list);
    }


}
