package com.cloudtimes.account.domain;

import com.cloudtimes.common.annotation.Excel;
import com.cloudtimes.common.core.domain.BaseEntity;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.util.Date;

/**
 * 用户对象 ct_user
 *
 * @author 沈兵
 * @date 2023-01-17
 */
@Data
@Slf4j
public class CtUser extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 序列号
     */
    private String id;

    /**
     * 登录账号
     */
    @Excel(name = "登录账号")
    private String account;
    /**
     * 微信openId
     */
    @Excel(name = "微信openId")
    private String wxOpenId;
    /**
     * 微信unionId
     */
    @Excel(name = "微信unionId")
    private String wxUnionId;
    /**
     * 登录密码
     */
    @Excel(name = "登录密码")
    private String password;

    /**
     * 电话号码
     */
    @Excel(name = "电话号码")
    private String mobile;


    /**
     * 昵称
     */
    private String nickName;

    /**
     * 真实姓名
     */
    @Excel(name = "真实姓名")
    private String realName;

    /**
     * 性别
     */
    @Excel(name = "性别")
    private String sex;

    /**
     * 生日
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "生日", width = 30, dateFormat = "yyyy-MM-dd")
    private Date birthday;

    /**
     * 是否实名
     * 否	0
     * 是	1
     */
    @Excel(name = "是否实名")
    private String isReal;

    /**
     * 违规次数
     */
    @Excel(name = "违规次数")
    private Long violationCount;

    /**
     * 身份证号
     */
    @Excel(name = "身份证号")
    private String idNo;

    /**
     * 是否代理
     * 否	0
     * 是	1
     */
    @Excel(name = "是否代理")
    private String isAgent;

    /**
     * 注册门店编号
     */
    @Excel(name = "注册门店编号")
    private String registerStoreId;

    /**
     * 是否店老板
     * 否	0
     * 是	1
     */
    @Excel(name = "是否店老板")
    private String isShopBoss;

    /**
     * 最近登录IP
     */
    @Excel(name = "最近登录IP")
    private String lastLoginIp;

    /**
     * 最近登录时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "最近登录时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date lastLoginTime;

    /**
     * 成为店主时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "成为店主时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date createBossTime;

    /**
     * 成为代理时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "成为代理时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date createAgentTime;

    /**
     * 代理状态
     * 未开通	0
     * 签约中	1
     * 已开通	2
     * 已停用	3
     */
    @Excel(name = "代理状态")
    private String agentState;

    /**
     * 代理类型
     * 城市合伙人	0
     * 特约合伙人	1
     * 普通代理	2
     */
    @Excel(name = "代理类型")
    private String agentType;

    /**
     * 店主状态
     * 未开通	0
     * 签约中	1
     * 已开通	2
     * 已停用	3
     */
    @Excel(name = "店主状态")
    private String bossState;

    /**
     * 顾客状态
     * 正常	0
     * 违规	1
     * 禁用	2
     */
    @Excel(name = "顾客状态")
    private String customerState;

    /**
     * 操作管理员
     */
    @Excel(name = "操作管理员")
    private String operator;

    /**
     * 注册日期
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "注册日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date createDate;

    /**
     * 是否删除
     * 否	0
     * 是	1
     */
    private String delFlag;

}
