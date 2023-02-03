package com.cloudtimes.system.service.impl;

import com.cloudtimes.common.annotation.DataSource;
import com.cloudtimes.common.constant.CacheConstants;
import com.cloudtimes.common.constant.UserConstants;
import com.cloudtimes.common.core.redis.RedisCache;
import com.cloudtimes.common.core.text.Convert;
import com.cloudtimes.common.enums.DataSourceType;
import com.cloudtimes.common.exception.ServiceException;
import com.cloudtimes.common.utils.DateUtils;
import com.cloudtimes.common.utils.HolidayUtil;
import com.cloudtimes.common.utils.StringUtils;
import com.cloudtimes.system.domain.SysConfig;
import com.cloudtimes.system.domain.vo.MemberDailyBuyVO;
import com.cloudtimes.system.domain.vo.MemberDailyCashVO;
import com.cloudtimes.system.mapper.SysConfigMapper;
import com.cloudtimes.system.service.ISysConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * 参数配置 服务层实现
 *
 * @author tank
 */
@DataSource(value = DataSourceType.SYS)
@Service
public class SysConfigServiceImpl implements ISysConfigService {
    @Autowired
    private SysConfigMapper configMapper;

    @Autowired
    private RedisCache redisCache;

    /**
     * 项目启动时，初始化参数到缓存
     */
    @PostConstruct
    public void init() {
        loadingConfigCache();
    }

    /**
     * 查询参数配置信息
     *
     * @param configId 参数配置ID
     * @return 参数配置信息
     */
    @Override
    @DataSource(DataSourceType.SYS)
    public SysConfig selectConfigById(Long configId) {
        SysConfig config = new SysConfig();
        config.setConfigId(configId);
        return configMapper.selectConfig(config);
    }

    /**
     * 根据键名查询参数配置信息
     *
     * @param configKey 参数key
     * @return 参数键值
     */
    @Override
    public String selectConfigByKey(String configKey) {
        String configValue = Convert.toStr(redisCache.getCacheObject(getCacheKey(configKey)));
        if (StringUtils.isNotEmpty(configValue)) {
            return configValue;
        }
        SysConfig config = new SysConfig();
        config.setConfigKey(configKey);
        SysConfig retConfig = configMapper.selectConfig(config);
        if (StringUtils.isNotNull(retConfig)) {
            redisCache.setCacheObject(getCacheKey(configKey), retConfig.getConfigValue());
            return retConfig.getConfigValue();
        }
        return StringUtils.EMPTY;
    }

    /**
     * 获取验证码开关
     *
     * @return true开启，false关闭
     */
    @Override
    public boolean selectCaptchaEnabled() {
        String captchaEnabled = selectConfigByKey("sys.account.captchaEnabled");
        if (StringUtils.isEmpty(captchaEnabled)) {
            return true;
        }
        return Convert.toBool(captchaEnabled);
    }

    /**
     * 查询参数配置列表
     *
     * @param config 参数配置信息
     * @return 参数配置集合
     */
    @Override
    public List<SysConfig> selectConfigList(SysConfig config) {
        return configMapper.selectConfigList(config);
    }

    /**
     * 新增参数配置
     *
     * @param config 参数配置信息
     * @return 结果
     */
    @Override
    public int insertConfig(SysConfig config) {
        int row = configMapper.insertConfig(config);
        if (row > 0) {
            redisCache.setCacheObject(getCacheKey(config.getConfigKey()), config.getConfigValue());
        }
        return row;
    }

    /**
     * 修改参数配置
     *
     * @param config 参数配置信息
     * @return 结果
     */
    @Override
    public int updateConfig(SysConfig config) {
        int row = configMapper.updateConfig(config);
        if (row > 0) {
            redisCache.setCacheObject(getCacheKey(config.getConfigKey()), config.getConfigValue());
        }
        return row;
    }

    /**
     * 批量删除参数信息
     *
     * @param configIds 需要删除的参数ID
     */
    @Override
    public void deleteConfigByIds(Long[] configIds) {
        for (Long configId : configIds) {
            SysConfig config = selectConfigById(configId);
            if (StringUtils.equals(UserConstants.YES, config.getConfigType())) {
                throw new ServiceException(String.format("内置参数【%1$s】不能删除 ", config.getConfigKey()));
            }
            configMapper.deleteConfigById(configId);
            redisCache.deleteObject(getCacheKey(config.getConfigKey()));
        }
    }

    /**
     * 加载参数缓存数据
     */
  //  @Transactional(propagation = Propagation.REQUIRES_NEW)
    @DataSource(value = DataSourceType.SYS)
    @Override
    public void loadingConfigCache() {
        List<SysConfig> configsList = configMapper.selectConfigList(new SysConfig());
        for (SysConfig config : configsList) {
            redisCache.setCacheObject(getCacheKey(config.getConfigKey()), config.getConfigValue());
        }
    }

    /**
     * 清空参数缓存数据
     */
    @Override
    public void clearConfigCache() {
        Collection<String> keys = redisCache.keys(CacheConstants.SYS_CONFIG_KEY + "*");
        redisCache.deleteObject(keys);
    }

    /**
     * 重置参数缓存数据
     */
    @Override
    public void resetConfigCache() {
        clearConfigCache();
        loadingConfigCache();
    }

    /**
     * 校验参数键名是否唯一
     *
     * @param config 参数配置信息
     * @return 结果
     */
    @Override
    public String checkConfigKeyUnique(SysConfig config) {
        Long configId = StringUtils.isNull(config.getConfigId()) ? -1L : config.getConfigId();
        SysConfig info = configMapper.checkConfigKeyUnique(config.getConfigKey());
        if (StringUtils.isNotNull(info) && info.getConfigId().longValue() != configId.longValue()) {
            return UserConstants.NOT_UNIQUE;
        }
        return UserConstants.UNIQUE;
    }

    @Override
    public boolean checkORRefreshDailyMemberBuyRecord(String username) {
        int buyMaxCount = Integer.valueOf(this.selectConfigByKey("buy.daily.count"));
        BigDecimal buyMaxAmount = BigDecimal.valueOf(Long.parseLong(this.selectConfigByKey("buy.dialy.total.amount")));

        String memberDailyBuyKey = CacheConstants.MEMBER_DAILY_BUY + "_" + username;

        MemberDailyBuyVO memberDailyBuyVO = redisCache.getCacheObject(memberDailyBuyKey);

        if (memberDailyBuyVO == null) {
            if (memberDailyBuyVO == null) {
                memberDailyBuyVO = new MemberDailyBuyVO();
                memberDailyBuyVO.setUsername(username);
            }
            initMemberDailyBuy(memberDailyBuyVO);
            return true;
        }

        if (memberDailyBuyVO.getLastBuyTime() == null || !DateUtils.isSameDay(memberDailyBuyVO.getLastBuyTime(), DateUtils.getNowDate())) {
            initMemberDailyBuy(memberDailyBuyVO);
            return true;
        }

        if (memberDailyBuyVO.getBuyCount() > buyMaxCount) {
            return false;
        }

        if (memberDailyBuyVO.getBuyAmount().compareTo(buyMaxAmount) > 0) {
            return false;
        }

        return true;
    }

    private void initMemberDailyBuy(MemberDailyBuyVO memberDailyBuyVO) {
        memberDailyBuyVO.setBuyCount(0);
        memberDailyBuyVO.setBuyAmount(BigDecimal.ZERO);
        String memberDailyBuyKey = CacheConstants.MEMBER_DAILY_BUY + "_" + memberDailyBuyVO.getUsername();
        redisCache.setCacheObject(memberDailyBuyKey, memberDailyBuyVO, 1, TimeUnit.DAYS);
    }

    private void initMemberDailyCash(MemberDailyCashVO memberDailyCashVO) {
        memberDailyCashVO.setCashCount(0);
        memberDailyCashVO.setCashAmount(BigDecimal.ZERO);
        String memberDailyCashKey = CacheConstants.MEMBER_DAILY_CASH + "_" + memberDailyCashVO.getUsername();
        redisCache.setCacheObject(memberDailyCashKey, memberDailyCashVO, 1, TimeUnit.DAYS);
    }

    @Override
    public void updateMemberDailyBuy(String username, BigDecimal buyAmount) {
        this.checkORRefreshDailyMemberBuyRecord(username);
        String memberDailyBuyKey = CacheConstants.MEMBER_DAILY_BUY + "_" + username;
        MemberDailyBuyVO memberDailyBuyVO = redisCache.getCacheObject(memberDailyBuyKey);
        memberDailyBuyVO.setBuyCount(memberDailyBuyVO.getBuyCount() + 1);
        memberDailyBuyVO.setBuyAmount(memberDailyBuyVO.getBuyAmount().add(buyAmount));
        memberDailyBuyVO.setLastBuyTime(DateUtils.getNowDate());

        this.redisCache.deleteObject(memberDailyBuyKey);
        redisCache.setCacheObject(memberDailyBuyKey, memberDailyBuyVO, 1, TimeUnit.DAYS);
    }

    @Override
    public boolean checkORRefreshDailyMemberCashRecord(String username) {
        int maxCount = Integer.valueOf(this.selectConfigByKey("cash.max.count"));
        BigDecimal maxAmount = BigDecimal.valueOf(Long.parseLong(this.selectConfigByKey("cash.max.amount")));

        String memberDailyCashKey = CacheConstants.MEMBER_DAILY_CASH + "_" + username;

        MemberDailyCashVO memberDailyCashVO = redisCache.getCacheObject(memberDailyCashKey);

        if (memberDailyCashVO == null) {
            if (memberDailyCashVO == null) {
                memberDailyCashVO = new MemberDailyCashVO();
                memberDailyCashVO.setUsername(username);
            }
            initMemberDailyCash(memberDailyCashVO);
            return true;
        }

        if (memberDailyCashVO.getLastBuyTime() == null || !DateUtils.isSameDay(memberDailyCashVO.getLastBuyTime(), DateUtils.getNowDate())) {
            initMemberDailyCash(memberDailyCashVO);
            return true;
        }

        if (memberDailyCashVO.getCashCount() > maxCount) {
            return false;
        }

        if (memberDailyCashVO.getCashAmount().compareTo(maxAmount) > 0) {
            return false;
        }

        return true;
    }

    @Override
    public void updateMemberDailyCash(String username, BigDecimal buyAmount) {
        this.checkORRefreshDailyMemberCashRecord(username);
        String memberDailyCashKey = CacheConstants.MEMBER_DAILY_CASH + "_" + username;
        MemberDailyCashVO memberDailyCashVO = redisCache.getCacheObject(memberDailyCashKey);
        memberDailyCashVO.setCashCount(memberDailyCashVO.getCashCount() + 1);
        memberDailyCashVO.setCashAmount(memberDailyCashVO.getCashAmount().add(buyAmount));
        memberDailyCashVO.setLastBuyTime(DateUtils.getNowDate());

        this.redisCache.deleteObject(memberDailyCashKey);
        redisCache.setCacheObject(memberDailyCashKey, memberDailyCashVO, 1, TimeUnit.DAYS);
    }

    @Override
    public boolean checkNowTimeisCash(Date now) {

        int cashEnabled = Integer.valueOf(selectConfigByKey("cash.enabled"));
        //已手动开启
        if (cashEnabled == 1) {
            return true;
        }


        Date nowTime = DateUtils.getNowDate();
        String day = DateUtils.parseDateToStr("yyyyMMdd", nowTime);

        Boolean isFlag = redisCache.getCacheObject(CacheConstants.MEMBER_WEEKDAY_CASH + day);
        if (isFlag == null) {
            isFlag = HolidayUtil.isWeekday(day);
            redisCache.setCacheObject(CacheConstants.MEMBER_WEEKDAY_CASH + day, isFlag, 24, TimeUnit.HOURS);
        }

        if (!isFlag) {
            throw new ServiceException("非工作日内无法取现");
        }

        String startTime = selectConfigByKey("weekday.starttime");
        String todayString = DateUtils.parseDateToStr("yyyy-MM-dd", nowTime);
        todayString = todayString + " " + startTime;


        Date startDate = DateUtils.parseDate(todayString);
        if (startDate.compareTo(nowTime) > 0) {
            throw new ServiceException("非营业时间内无法取现");
        }

        String endTime = selectConfigByKey("weekday.endtime");
        todayString = DateUtils.parseDateToStr("yyyy-MM-dd", nowTime);
        todayString = todayString + " " + endTime;
        Date endDate = DateUtils.parseDate(todayString);
        if (endDate.compareTo(nowTime) < 0) {
            throw new ServiceException("非营业时间内无法取现");
        }

        return true;
    }

    /**
     * 设置cache key
     *
     * @param configKey 参数键
     * @return 缓存键key
     */
    private String getCacheKey(String configKey) {
        return CacheConstants.SYS_CONFIG_KEY + configKey;
    }
}
