package com.cloudtimes.common.core.domain.model;

/**
 * 用户注册对象
 * 
 * @author tank
 */
public class RegisterBody extends LoginBody
{

    private String area;
    private String inviteCode;

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getInviteCode() {
        return inviteCode;
    }

    public void setInviteCode(String inviteCode) {
        this.inviteCode = inviteCode;
    }










}
