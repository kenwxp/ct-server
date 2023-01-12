package com.cloudtimes.app.controller;

import com.cloudtimes.common.annotation.Log;
import com.cloudtimes.common.core.controller.BaseController;
import com.cloudtimes.common.core.domain.AjaxResult;
import com.cloudtimes.common.core.page.TableDataInfo;
import com.cloudtimes.common.enums.BusinessType;
import com.cloudtimes.common.utils.DateUtils;
import com.cloudtimes.common.utils.poi.ExcelUtil;
import com.cloudtimes.kuaidi100.Kuaidi100Utils;
import com.cloudtimes.ybf.domain.YbfGoodsOrder;
import com.cloudtimes.ybf.domain.YbfKuaidiDetails;
import com.cloudtimes.ybf.service.IYbfGoodsOrderService;
import com.cloudtimes.ybf.service.IYbfGoodsService;
import com.cloudtimes.ybf.service.IYbfKuaidiDetailsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 快递物流信息Controller
 *
 * @author POLO
 * @date 2022-10-23
 */
@Api("快递物流信息API接口")
@RestController
@RequestMapping("/ybf/postdetails")
public class AppKuaidiDetailsController extends BaseController {
    @Autowired
    private IYbfKuaidiDetailsService ybfKuaidiDetailsService;

    @Autowired
    private Kuaidi100Utils kuaidi100Utils;

    @Autowired
    private IYbfGoodsOrderService goodsOrderService;

    private static final long Hour3 = 3  * 3600 * 1000L;

    /**
     * 获取快递物流信息详细信息
     */
    @ApiOperation("获取快递物流信息详细信息")
    @GetMapping(value = "/details")
    public AjaxResult getInfo(String orderNo, String postNo) {

        YbfKuaidiDetails ybfKuaidiDetails = ybfKuaidiDetailsService.selectYbfKuaidiDetailsByPostNo(postNo);
        if (ybfKuaidiDetails == null) {
            return AjaxResult.success(new YbfKuaidiDetails());
        }
        YbfGoodsOrder order = goodsOrderService.selectYbfGoodsOrderByOrderNo(orderNo);
        if (order == null) {
            return AjaxResult.error("无效订单");
        }

        if(order.getStatus().intValue() == 3 ){
            long nowTime = System.currentTimeMillis();
            if(ybfKuaidiDetails.getUpdateTime() != null && (nowTime - ybfKuaidiDetails.getUpdateTime().getTime()) < Hour3 ){
                return AjaxResult.success(ybfKuaidiDetails);
            }

           String detail = kuaidi100Utils.query(ybfKuaidiDetails.getCompany(),ybfKuaidiDetails.getPostNo(),ybfKuaidiDetails.getMobile());
            ybfKuaidiDetails.setDetailsText(detail);
            ybfKuaidiDetailsService.updateYbfKuaidiDetails(ybfKuaidiDetails);
        }

        return AjaxResult.success(ybfKuaidiDetails);
    }


}
