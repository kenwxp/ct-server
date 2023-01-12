package com.cloudtimes.web.controller.system;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.cloudtimes.common.config.CloudTimesConfig;
import com.cloudtimes.common.utils.StringUtils;

/**
 * 首页
 *
 * @author tank
 */
@RestController
public class SysIndexController
{
    /** 系统基础配置 */
    @Autowired
    private CloudTimesConfig cloudTimesConfig;

    /**
     * 访问首页，提示语
     */
    @RequestMapping("/")
    public String index()
    {
        return StringUtils.format("欢迎使用{}后台管理框架，当前版本：v{}，请通过前端地址访问。", cloudTimesConfig.getName(), cloudTimesConfig.getVersion());
    }
}
