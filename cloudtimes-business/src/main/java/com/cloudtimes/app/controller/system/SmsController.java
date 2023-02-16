package com.cloudtimes.app.controller.system;

import com.cloudtimes.common.annotation.RateLimiter;
import com.cloudtimes.common.constant.CacheConstants;
import com.cloudtimes.common.constant.Constants;
import com.cloudtimes.common.core.controller.BaseController;
import com.cloudtimes.common.core.domain.AjaxResult;
import com.cloudtimes.common.core.redis.RedisCache;
import com.cloudtimes.common.enums.LimitType;
import com.cloudtimes.common.exception.user.CaptchaException;
import com.cloudtimes.common.exception.user.CaptchaExpireException;
import com.cloudtimes.common.sms.cdcxcloud.api.CdcxcloundSMSUtil;
import com.cloudtimes.common.utils.StringUtils;
import com.cloudtimes.common.utils.uuid.IdUtils;
import com.cloudtimes.common.utils.uuid.Seq;
import com.cloudtimes.system.service.ISysConfigService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * 验证码操作处理
 *
 * @author ruoyi
 */
@Api(tags = "短信验证码")
@RestController
public class SmsController extends BaseController {

    @Autowired
    private RedisCache redisCache;

    @Autowired
    private ISysConfigService configService;


    /**
     * 生成短信验证码
     */
    @RateLimiter(limitType = LimitType.IP, count = 10, time = 60)
    @ApiOperation("短信验证码")
    @GetMapping("/sms/{mobile}")
    public AjaxResult getCode(@PathVariable(value = "mobile", required = true) String mobile) throws IOException {
        AjaxResult ajax = AjaxResult.success("短信已发送");
        // 保存验证码信息
        String uuid = IdUtils.simpleUUID();
        String verifyKey = CacheConstants.SMS_CODE_KEY + "_" + mobile + "_" + uuid;

        // 生成验证码
        String code = Seq.genNumberTo5Len();
        redisCache.setCacheObject(verifyKey, code, Constants.SMS_CAPTCHA_EXPIRATION, TimeUnit.MINUTES);

        boolean smsEnabled = Boolean.valueOf(configService.selectConfigByKey("sms.enabled"));
        if (smsEnabled) {
            String smsbody = configService.selectConfigByKey("sms.body");
            CdcxcloundSMSUtil.semdSms(String.format(smsbody, code), mobile);
        }
        logger.info("生成短信认证码：" + code);
        ajax.put("uuid", uuid);
        ajax.put("data", code);
        return ajax;
    }


    /**
     * 校验短信验证码
     *
     * @param mobile 手机号码
     * @param code   验证码
     * @param uuid   唯一标识
     * @return 结果
     */
    public void validateSMS(String mobile, String code, String uuid) {
        String verifyKey = CacheConstants.SMS_CODE_KEY + "_" + mobile + "_" + StringUtils.nvl(uuid, "");
        String captcha = redisCache.getCacheObject(verifyKey);
        redisCache.deleteObject(verifyKey);
        if (captcha == null) {
            throw new CaptchaExpireException();
        }

        if (!code.equalsIgnoreCase(captcha)) {
            throw new CaptchaException();
        }
    }
}
