package com.cloudtimes.app.controller.system;

import com.cloudtimes.common.core.controller.BaseController;
import com.cloudtimes.common.core.domain.AjaxResult;
import com.cloudtimes.system.domain.SysConfig;
import com.cloudtimes.system.service.ISysConfigService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 参数配置 信息操作处理
 *
 * @author tank
 */
@RestController
@RequestMapping("/system")
public class AppConfigController extends BaseController {
    @Autowired
    private ISysConfigService configService;

    /**
     * 获取参数配置列表
     */
    @ApiOperation("获取参数配置列表")
    @GetMapping("/config")
    public AjaxResult list() {
        SysConfig config = new SysConfig();
        config.setConfigType("N");
        List<SysConfig> list = configService.selectConfigList(config);
        return AjaxResult.success(list);
    }


}
