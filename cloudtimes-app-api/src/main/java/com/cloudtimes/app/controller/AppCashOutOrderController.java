package com.cloudtimes.app.controller;

import com.cloudtimes.common.core.controller.BaseController;
import com.cloudtimes.common.core.domain.AjaxResult;
import com.cloudtimes.common.core.domain.entity.YbfMember;
import com.cloudtimes.common.core.page.TableDataInfo;
import com.cloudtimes.common.utils.DateUtils;
import com.cloudtimes.system.domain.vo.MySaleCashOrderVO;
import com.cloudtimes.system.service.ISysConfigService;
import com.cloudtimes.ybf.domain.YbfCashOutOrder;
import com.cloudtimes.ybf.domain.YbfMysaleCashoutOrder;
import com.cloudtimes.ybf.service.IYbfCashOutOrderService;
import com.cloudtimes.ybf.service.IYbfMemberService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 提现订单Controller
 *
 * @author polo
 * @date 2022-09-03
 */
@Api("积分提现订单API接口")
@RestController
@RequestMapping("/ybf/cashoutorder")
public class AppCashOutOrderController extends BaseController {
    @Autowired
    private IYbfCashOutOrderService ybfCashOutOrderService;

    @Autowired
    private IYbfMemberService memberService;

    @Autowired
    private ISysConfigService configService;

    /**
     * 查询我的销售提现订单列表
     */
    @ApiOperation("查询我的销售提现订单列表")
    @GetMapping("/list")
    public TableDataInfo list(YbfMysaleCashoutOrder ybfMysaleCashoutOrder) {
        startPage();
        Integer[] statusArray = new Integer[0];
        if (ybfMysaleCashoutOrder.getParams().containsKey("statusArray")) {
            String statusArrayString = ybfMysaleCashoutOrder.getParams().get("statusArray").toString();
            String[] arr = statusArrayString.split(",");
            statusArray = new Integer[arr.length];
            for (int i = 0; i < arr.length; i++) {
                statusArray[i] = new Integer(arr[i]);
            }
        }

        List<YbfCashOutOrder> list = ybfCashOutOrderService.selectYbfMysaleCashoutOrders(getAppUsername(), statusArray);
        return getDataTable(list);
    }


    /**
     * 获取提现订单详细信息
     */
    @ApiOperation("获取提现订单详细信息")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id) {
        return AjaxResult.success(ybfCashOutOrderService.selectYbfCashOutOrderById(id));
    }


    /**
     * 积分现订单
     */
    @PostMapping("/order")
    public AjaxResult createOrder(@RequestBody MySaleCashOrderVO mySaleCashOrderVO) {

        configService.checkNowTimeisCash(DateUtils.getNowDate());


        YbfMember member = memberService.selectYbfMemberByUsername(getAppUsername());
        if (member.getPaymentBinding() != 2) {
            return AjaxResult.error("未实名认证");
        }
        return ybfCashOutOrderService.processOrder(mySaleCashOrderVO);
    }

}
