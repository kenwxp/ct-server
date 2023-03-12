package com.cloudtimes.app.controller.system;

import com.cloudtimes.common.core.controller.BaseController;
import com.cloudtimes.common.core.domain.AjaxResult;
import com.cloudtimes.system.domain.SysConfig;
import com.cloudtimes.system.service.ISysConfigService;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 参数配置 信息操作处理
 *
 * @author tank
 */
@Tag(name = "系统参数")
@RestController
@RequestMapping("/system")
public class AppConfigController extends BaseController {
    @Autowired
    private ISysConfigService configService;

    /**
     * 获取参数配置列表
     */
    @Operation(summary = "获取参数配置列表")
    @GetMapping("/config")
    public AjaxResult list() {
        SysConfig config = new SysConfig();
        config.setConfigType("N");
        List<SysConfig> list = configService.selectConfigList(config);
        return AjaxResult.success(list);
    }

    @Operation(summary = "根据配置Key获取参数配置")
    @GetMapping("/config/{key}")
    public AjaxResult getConfigByKey(@PathVariable("key") String key) {
        var config = configService.selectConfigByKey(key);
        return AjaxResult.success(config);
    }
}
