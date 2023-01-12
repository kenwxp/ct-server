package com.cloudtimes.common.constant;

/**
 * 缓存的key 常量
 *
 * @author tank
 */
public class CacheConstants {
    /**
     * 登录用户 redis key
     */
    public static final String LOGIN_TOKEN_KEY = "login_tokens:";

    /**
     * APP登录用户 redis key
     */
    public static final String APP_LOGIN_TOKEN_KEY = "app_login_tokens:";


    /**
     * 验证码 redis key
     */
    public static final String CAPTCHA_CODE_KEY = "captcha_codes:";

    /**
     * 短信验证码 redis key
     */
    public static final String SMS_CODE_KEY = "sms_codes:";

    /**
     * 会员每日购买消费记录 redis key
     */
    public static final String MEMBER_DAILY_BUY = "member_daily_buy:";

    /**
     * 会员每日提现记录 redis key
     */
    public static final String MEMBER_DAILY_CASH = "member_daily_cash:";

    /**
     * 会员每日是否可提现 redis key
     */
    public static final String MEMBER_WEEKDAY_CASH = "member_weekday_cash:";

    /**
     * 修改密码 redis key
     */
    public static final String UPDATE_PWD_KEY = "update_pwd_key";

    /**
     * 参数管理 cache key
     */
    public static final String SYS_CONFIG_KEY = "sys_config:";

    /**
     * 字典管理 cache key
     */
    public static final String SYS_DICT_KEY = "sys_dict:";

    /**
     * 防重提交 redis key
     */
    public static final String REPEAT_SUBMIT_KEY = "repeat_submit:";

    /**
     * 限流 redis key
     */
    public static final String RATE_LIMIT_KEY = "rate_limit:";

    /**
     * 登录账户密码错误次数 redis key
     */
    public static final String PWD_ERR_CNT_KEY = "pwd_err_cnt:";
}
