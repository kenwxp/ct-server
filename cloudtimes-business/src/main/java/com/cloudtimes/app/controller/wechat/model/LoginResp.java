package com.cloudtimes.app.controller.wechat.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;


@ApiModel(value = "LoginResp", description = "小程序登录校验接口返回体")
public class LoginResp {
    @ApiModelProperty("后台登录token")
    private String accessToken;
    @ApiModelProperty("现金余额")
    private String moneyAmount;
    @ApiModelProperty("积分余额")
    private String scoreAmount;
    @ApiModelProperty("信用分")
    private String creditScore;

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getMoneyAmount() {
        return moneyAmount;
    }

    public void setMoneyAmount(String moneyAmount) {
        this.moneyAmount = moneyAmount;
    }

    public String getScoreAmount() {
        return scoreAmount;
    }

    public void setScoreAmount(String scoreAmount) {
        this.scoreAmount = scoreAmount;
    }

    public String getCreditScore() {
        return creditScore;
    }

    public void setCreditScore(String creditScore) {
        this.creditScore = creditScore;
    }
}
