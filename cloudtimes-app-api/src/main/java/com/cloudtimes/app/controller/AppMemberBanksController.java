package com.cloudtimes.app.controller;

import com.cloudtimes.common.core.controller.BaseController;
import com.cloudtimes.common.core.domain.AjaxResult;
import com.cloudtimes.common.core.domain.entity.YbfMember;
import com.cloudtimes.common.payment.adapay.AdapayPaymentManager;
import com.cloudtimes.ybf.domain.YbfMemberBanks;
import com.cloudtimes.ybf.service.IYbfMemberBanksService;
import com.cloudtimes.ybf.service.IYbfMemberService;
import org.aspectj.weaver.loadtime.Aj;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * 会员银行卡列Controller
 *
 * @author polo
 * @date 2022-12-22
 */
@RestController
@RequestMapping("/ybf/memberbanks")
public class AppMemberBanksController extends BaseController {
    @Autowired
    private IYbfMemberBanksService ybfMemberBanksService;

    @Autowired
    private IYbfMemberService memberService;

    @Autowired
    private AdapayPaymentManager adapayPaymentManager;

    /**
     * 查询会员银行卡列表
     */
    @GetMapping("/list")
    public AjaxResult list(YbfMemberBanks ybfMemberBanks) {
        ybfMemberBanks.setUsername(getAppUsername());
        ybfMemberBanks.setEnabled(0L);
        List<YbfMemberBanks> list = ybfMemberBanksService.selectYbfMemberBanksList(ybfMemberBanks);
        return AjaxResult.success(list);
    }


    /**
     * 获取会员银行卡列详细信息
     */
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id) {
        return AjaxResult.success(ybfMemberBanksService.selectYbfMemberBanksById(id));
    }


    /**
     * 会员新增银行卡
     */
    @PostMapping
    public AjaxResult add(@RequestBody YbfMemberBanks ybfMemberBanks) {
        YbfMember member = memberService.selectYbfMemberByUsername(getAppUsername());
        if (member.getPaymentBinding() == null || member.getPaymentBinding() != 2) {
            return AjaxResult.error("未实名认证");
        }

        ybfMemberBanks.setUsername(getAppUsername());
        ybfMemberBanks.setMemberId(member.getMemberCode());

        Map<String, Object> resultMap = adapayPaymentManager.executerCardApply(ybfMemberBanks.getBankType(),
                member.getMemberCode(),
                ybfMemberBanks.getTelNo(),
                ybfMemberBanks.getCardNo(),
                ybfMemberBanks.getCreditCardCode(),
                ybfMemberBanks.getExpiration());

        if (resultMap.get("status").equals("succeeded")) {
            ybfMemberBanks.setPaymentBinding(1L);
            ybfMemberBanks.setEnabled(0L);
            ybfMemberBanksService.insertYbfMemberBanks(ybfMemberBanks);
            return AjaxResult.success();
        } else {
            return AjaxResult.error(resultMap.get("error_msg").toString());
        }

    }

    /**
     * 会员解绑银行卡
     */
    @GetMapping(value = "/unbinding/{id}")
    public AjaxResult unbinding(@PathVariable("id") Long id) {
        return AjaxResult.success(ybfMemberBanksService.unbindingBank(id));
    }


}
