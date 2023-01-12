package com.cloudtimes.system.domain.vo;

import java.math.BigDecimal;

public class MySaleCashOrderVO {

    private Long memberBankId;
    private BigDecimal cashAmount;
    private String code;
    private String uuid;


    public Long getMemberBankId() {
        return memberBankId;
    }

    public void setMemberBankId(Long memberBankId) {
        this.memberBankId = memberBankId;
    }

    public BigDecimal getCashAmount() {
        return cashAmount;
    }

    public void setCashAmount(BigDecimal cashAmount) {
        this.cashAmount = cashAmount;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }
}
