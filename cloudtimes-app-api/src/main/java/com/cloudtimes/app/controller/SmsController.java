package com.cloudtimes.app.controller;

import com.cloudtimes.common.annotation.RateLimiter;
import com.cloudtimes.common.constant.CacheConstants;
import com.cloudtimes.common.constant.Constants;
import com.cloudtimes.common.core.controller.BaseController;
import com.cloudtimes.common.core.domain.AjaxResult;
import com.cloudtimes.common.core.redis.RedisCache;
import com.cloudtimes.common.enums.LimitType;
import com.cloudtimes.common.utils.uuid.IdUtils;
import com.cloudtimes.common.utils.uuid.Seq;
import com.cloudtimes.sms.cdcxcloud.api.CdcxcloundSMSUtil;
import com.cloudtimes.system.service.ISysConfigService;
import com.cloudtimes.ybf.service.IYbfMemberService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * 验证码操作处理
 *
 * @author tank
 */
@RestController
public class SmsController extends BaseController {

    @Autowired
    private RedisCache redisCache;

    @Autowired
    private ISysConfigService configService;

    @Autowired
    private IYbfMemberService ybfMemberService;

    /**
     * 生成短信验证码
     */
    @RateLimiter(limitType = LimitType.IP, count = 10, time = 60)
    @ApiOperation("短信验证码")
    @GetMapping("/sms/{ok}/{mobile}")
    public AjaxResult getCode(@PathVariable(value = "ok", required = true) String ok, @PathVariable(value = "mobile", required = true) String mobile) throws IOException {

        if (ok.equals("y")) {
            int count = ybfMemberService.checkUserNameUnique(mobile);
            if (count <= 0) {
                return AjaxResult.error("该手机号未注册!");
            }
        }

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
        int count = ybfMemberService.checkUserNameUnique(mobile);

        ajax.put("uuid", uuid);
        ajax.put("isExists", count > 0);
        ajax.put("data", code);
        return ajax;
    }
}
