package com.cloudtimes.common.core.domain.entity;

import com.cloudtimes.common.annotation.Excel;
import com.cloudtimes.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 会员对象 ybf_member
 *
 * @author polo
 * @date 2022-09-01
 */
public class YbfMember extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * Id
     */
    @Excel(name = "序号")
    private Long id;

    /**
     * 会员代码
     */
    @Excel(name = "用户ID")
    private String memberCode;

    /**
     * 用户名
     */
    @Excel(name = "用户名")
    private String username;

    /**
     * 登录密码
     */
    private String password;

    /**
     * 区域
     */
    @Excel(name = "区域")
    private String area;

    /**
     * 性别（0女 1男）
     */
    @Excel(name = "性别", readConverterExp = "0=女,1=男")
    private Integer sex;

    /**
     * 积分余额
     */
    @Excel(name = "碳积分余额")
    private BigDecimal scoreAmount;

    /**
     * 佣金余额
     */
    @Excel(name = "奖励余额")
    private BigDecimal moneyAmount;

    /**
     * 邀请码
     */
    @Excel(name = "邀请码")
    private String inviteCode;

    /**
     * 昵称
     */
    private String nickName;

    /**
     * 头像
     */
    private String face;

    /**
     * 收货地址
     */
    @Excel(name = "收货地址")
    private String receivingAddr;

    /**
     * 直接好友
     */
    private Long friendNum;

    /**
     * 间接好友
     */
    private Long friendIndirectNum;

    /**
     * 会员类型（0普通 1VIP会员）
     */
    @Excel(name = "会员类型", readConverterExp = "0=普通,1=VIP会员")
    private Integer isVip;

    /**
     * 是否代理（0否 1是）
     */
    @Excel(name = "是否代理", readConverterExp = "0=否,1=是")
    private Integer isAgent;

    /**
     * 代理区域
     */
    @Excel(name = "代理区域")
    private String areaAgent;

    /**
     * 状态（0正常 1禁用）
     */
    @Excel(name = "状态", readConverterExp = "0=正常,1=禁用")
    private Integer enabled;

    /**
     * 是否首次开启峰值大盘（0否 1是）
     */
    private Integer marketFirstEnabled;

    /**
     * 最近登录IP
     */
    private String loginIp;

    /**
     * 最后登录时间
     */
    private Date loginDate;

    /**
     * 上级推荐邀请码
     */
    private String parentInviteCode;

    /**
     * 收货人
     */
    private String receivingName;

    /**
     * 收货人地区
     */
    private String receivingArea;

    /**
     * 收货人手机号
     */
    private String receivingPhone;

    /**
     * 峰值大盘（0未开启 1已开启）
     */
    private Integer marketEnabled;

    /**
     * 身份证号
     */
    @Excel(name = "身份证号")
    private String idNumber;
    /**
     * 真实姓名
     */
    @Excel(name = "真实姓名")
    private String realName;
    /**
     * 支付渠道是否已实名认证（0未认证 1已认证）
     */
    private Integer paymentBinding;

    /**
     * adapay支付渠道是注册（0未注册 1已注册）
     */
    private int adapayRegister;

    public Integer getMarketEnabled() {
        return marketEnabled;
    }

    public void setMarketEnabled(Integer marketEnabled) {
        this.marketEnabled = marketEnabled;
    }

    public String getReceivingArea() {
        return receivingArea;
    }

    public void setReceivingArea(String receivingArea) {
        this.receivingArea = receivingArea;
    }

    public String getReceivingPhone() {
        return receivingPhone;
    }

    public void setReceivingPhone(String receivingPhone) {
        this.receivingPhone = receivingPhone;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getArea() {
        return area;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public Integer getSex() {
        return sex;
    }

    public void setScoreAmount(BigDecimal scoreAmount) {
        this.scoreAmount = scoreAmount;
    }

    public BigDecimal getScoreAmount() {
        return scoreAmount;
    }

    public void setInviteCode(String inviteCode) {
        this.inviteCode = inviteCode;
    }

    public String getInviteCode() {
        return inviteCode;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getNickName() {
        return nickName;
    }

    public void setFace(String face) {
        this.face = face;
    }

    public String getFace() {
        return face;
    }

    public void setReceivingAddr(String receivingAddr) {
        this.receivingAddr = receivingAddr;
    }

    public String getReceivingAddr() {
        return receivingAddr;
    }

    public void setFriendNum(Long friendNum) {
        this.friendNum = friendNum;
    }

    public Long getFriendNum() {
        return friendNum;
    }

    public void setFriendIndirectNum(Long friendIndirectNum) {
        this.friendIndirectNum = friendIndirectNum;
    }

    public Long getFriendIndirectNum() {
        return friendIndirectNum;
    }

    public void setIsVip(Integer isVip) {
        this.isVip = isVip;
    }

    public Integer getIsVip() {
        return isVip;
    }

    public void setIsAgent(Integer isAgent) {
        this.isAgent = isAgent;
    }

    public Integer getIsAgent() {
        return isAgent;
    }

    public void setAreaAgent(String areaAgent) {
        this.areaAgent = areaAgent;
    }

    public String getAreaAgent() {
        return areaAgent;
    }

    public void setEnabled(Integer enabled) {
        this.enabled = enabled;
    }

    public Integer getEnabled() {
        return enabled;
    }

    public String getLoginIp() {
        return loginIp;
    }

    public void setLoginIp(String loginIp) {
        this.loginIp = loginIp;
    }

    public String getMemberCode() {
        return memberCode;
    }

    public void setMemberCode(String memberCode) {
        this.memberCode = memberCode;
    }

    public BigDecimal getMoneyAmount() {
        return moneyAmount;
    }

    public void setMoneyAmount(BigDecimal moneyAmount) {
        this.moneyAmount = moneyAmount;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("id", getId())
                .append("memberCode", getMemberCode())
                .append("username", getUsername())
                .append("password", getPassword())
                .append("area", getArea())
                .append("sex", getSex())
                .append("scoreAmount", getScoreAmount())
                .append("moneyAmount", getMoneyAmount())
                .append("inviteCode", getInviteCode())
                .append("nickName", getNickName())
                .append("face", getFace())
                .append("receivingAddr", getReceivingAddr())
                .append("friendNum", getFriendNum())
                .append("friendIndirectNum", getFriendIndirectNum())
                .append("isVip", getIsVip())
                .append("isAgent", getIsAgent())
                .append("receivingArea", getReceivingArea())
                .append("receivingPhone", getReceivingPhone())
                .append("areaAgent", getAreaAgent())
                .append("enabled", getEnabled())
                .append("createTime", getCreateTime())
                .append("marketEnabled", getMarketEnabled())
                .append("updateBy", getUpdateBy())
                .append("updateTime", getUpdateTime())
                .append("loginIp", getLoginIp())
                .append("loginDate", getLoginDate())
                .toString();
    }

    public Integer getMarketFirstEnabled() {
        return marketFirstEnabled;
    }

    public void setMarketFirstEnabled(Integer marketFirstEnabled) {
        this.marketFirstEnabled = marketFirstEnabled;
    }

    public Date getLoginDate() {
        return loginDate;
    }

    public void setLoginDate(Date loginDate) {
        this.loginDate = loginDate;
    }

    public String getParentInviteCode() {
        return parentInviteCode;
    }

    public void setParentInviteCode(String parentInviteCode) {
        this.parentInviteCode = parentInviteCode;
    }

    public String getReceivingName() {
        return receivingName;
    }

    public void setReceivingName(String receivingName) {
        this.receivingName = receivingName;
    }


    public String getIdNumber() {
        return idNumber;
    }

    public void setIdNumber(String idNumber) {
        this.idNumber = idNumber;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public Integer getPaymentBinding() {
        return paymentBinding;
    }

    public void setPaymentBinding(Integer paymentBinding) {
        this.paymentBinding = paymentBinding;
    }

    public int getAdapayRegister() {
        return adapayRegister;
    }

    public void setAdapayRegister(int adapayRegister) {
        this.adapayRegister = adapayRegister;
    }
}
