package com.cloudtimes.app.controller;

import com.cloudtimes.common.core.controller.BaseController;
import com.cloudtimes.common.core.domain.AjaxResult;
import com.cloudtimes.kuaidi100.Kuaidi100Utils;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

/**
 * 验证码操作处理
 *
 * @author tank
 */
@RestController
public class KuaidiController extends BaseController {

    @Autowired
    private Kuaidi100Utils kuaidi100Utils;


    /**
     * 查询快递信息
     */
    @ApiOperation("查询快递信息")
    @GetMapping("/kuaidi")
    public AjaxResult getCode(String company,String mobile, String num) throws IOException {
        String result = kuaidi100Utils.query(company,num, mobile);
        return AjaxResult.success(result);
    }
}
