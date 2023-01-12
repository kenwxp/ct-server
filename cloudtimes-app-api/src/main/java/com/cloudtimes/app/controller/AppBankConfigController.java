package com.cloudtimes.app.controller;

import com.cloudtimes.common.annotation.Log;
import com.cloudtimes.common.core.controller.BaseController;
import com.cloudtimes.common.core.domain.AjaxResult;
import com.cloudtimes.common.core.page.TableDataInfo;
import com.cloudtimes.common.enums.BusinessType;
import com.cloudtimes.common.utils.poi.ExcelUtil;
import com.cloudtimes.ybf.domain.YbfBankConfig;
import com.cloudtimes.ybf.service.IYbfBankConfigService;
import org.aspectj.weaver.loadtime.Aj;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 银行列配置Controller
 *
 * @author POLO
 * @date 2022-12-22
 */
@RestController
@RequestMapping("/ybf/bankconfig")
public class AppBankConfigController extends BaseController {
    @Autowired
    private IYbfBankConfigService ybfBankConfigService;

    /**
     * 查询银行列配置列表
     */
    @GetMapping("/list")
    public AjaxResult list(YbfBankConfig ybfBankConfig) {
        ybfBankConfig.setEnabled(1L);
        List<YbfBankConfig> list = ybfBankConfigService.selectYbfBankConfigList(ybfBankConfig);
        return AjaxResult.success(list);
    }



}
