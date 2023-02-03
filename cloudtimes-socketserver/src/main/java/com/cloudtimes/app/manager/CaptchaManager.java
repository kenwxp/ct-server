package com.cloudtimes.app.manager;

import com.cloudtimes.common.constant.CacheConstants;
import com.cloudtimes.common.core.redis.RedisCache;
import com.cloudtimes.common.exception.user.CaptchaException;
import com.cloudtimes.common.exception.user.CaptchaExpireException;
import com.cloudtimes.common.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CaptchaManager {


    @Autowired
    private RedisCache redisCache;

    public boolean checkSMSCaptcha(String uuid, String phoneNumber, String code) {
        String verifyKey = CacheConstants.SMS_CODE_KEY + "_" + phoneNumber + "_" + StringUtils.nvl(uuid, "");
        String captcha = redisCache.getCacheObject(verifyKey);
        redisCache.deleteObject(verifyKey);
        if (captcha == null) {
            throw new CaptchaExpireException();
        }

        if (!code.equalsIgnoreCase(captcha)) {
            throw new CaptchaException();
        }

        return true;
    }

    public boolean checkCodeCaptcha(String uuid, String code) {
        String verifyKey = CacheConstants.CAPTCHA_CODE_KEY + StringUtils.nvl(uuid, "");
        String captcha = redisCache.getCacheObject(verifyKey);
        redisCache.deleteObject(verifyKey);
        if (captcha == null) {
            throw new CaptchaExpireException();
        }
        if (!code.equalsIgnoreCase(captcha)) {
            throw new CaptchaException();
        }
        return true;
    }
}
