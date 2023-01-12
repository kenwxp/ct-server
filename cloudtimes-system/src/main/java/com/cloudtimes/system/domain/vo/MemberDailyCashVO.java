package com.cloudtimes.system.domain.vo;

import java.math.BigDecimal;
import java.util.Date;

public class MemberDailyCashVO {

    private int cashCount;
    private BigDecimal cashAmount;
    private Date lastBuyTime;
    private String username;

    public int getCashCount() {
        return cashCount;
    }

    public void setCashCount(int cashCount) {
        this.cashCount = cashCount;
    }

    public BigDecimal getCashAmount() {
        return cashAmount;
    }

    public void setCashAmount(BigDecimal cashAmount) {
        this.cashAmount = cashAmount;
    }

    public Date getLastBuyTime() {
        return lastBuyTime;
    }

    public void setLastBuyTime(Date lastBuyTime) {
        this.lastBuyTime = lastBuyTime;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
