package com.cloudtimes.app.controller.agent

import com.alibaba.fastjson.JSONObject
import com.cloudtimes.account.domain.CtUser
import com.cloudtimes.account.dto.request.WxLoginRequest
import com.cloudtimes.account.service.ICtUserService
import com.cloudtimes.common.constant.CacheConstants
import com.cloudtimes.common.core.domain.AjaxResult
import com.cloudtimes.common.core.domain.RestResult
import com.cloudtimes.common.core.domain.entity.AuthUser
import com.cloudtimes.common.core.redis.RedisCache
import com.cloudtimes.common.enums.ChannelType
import com.cloudtimes.common.exception.ServiceException
import com.cloudtimes.common.utils.JWTManager
import com.cloudtimes.partner.weixin.ICtWeixinOfficialApiService
import io.swagger.annotations.Api
import io.swagger.annotations.ApiOperation
import me.zhyd.oauth.model.AuthCallback
import me.zhyd.oauth.model.AuthResponse
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*
import java.util.concurrent.TimeUnit
import javax.servlet.http.HttpServletResponse
import javax.validation.Valid


class WxLoginResponse(override var data: CtUser?) : RestResult<CtUser>(data)


@RestController
@RequestMapping("/agent/wx_auth")
@Api(tags = ["代理-微信登陆授权"])
class WxAuthController {
    private val logger: Logger = LoggerFactory.getLogger(javaClass);

    @Autowired
    private lateinit var weixinOfficialApiService: ICtWeixinOfficialApiService

    @Autowired
    private lateinit var userService: ICtUserService

    @Autowired
    private lateinit var jwtManager: JWTManager;

    @Autowired
    private lateinit var redisCache: RedisCache;

    @GetMapping()
    @ApiOperation(value = "微信授权")
    fun mpAuth(response: HttpServletResponse) {
        val url = weixinOfficialApiService.wxAuthURL;
        response.sendRedirect(url);
    }

    @PostMapping()
    @ApiOperation(value = "微信登陆")
    fun wxLogin(@Valid @RequestBody request: WxLoginRequest): WxLoginResponse {
        val callback = AuthCallback()
        callback.code = request.code
        callback.state = request.state

        // 获取微信用户信息
        val ds: AuthResponse<me.zhyd.oauth.model.AuthUser> = weixinOfficialApiService.login(callback);
        if (ds.code !== 2000) {
            logger.info("${ds.code}")
            logger.info(ds.msg)
            throw ServiceException("微信授权失败: ${ds.msg}")
        }

        val userInfo = ds.data
        val unionId: String = userInfo.rawUserInfo.getString("unionid");
        logger.info("unionid: $unionId")


        // 登陆或创建用户
        val loginUser = CtUser().apply {
            wxUnionId = unionId.toString()
            nickName = userInfo.nickname
            wxAvatar = userInfo.avatar
        }
        val loggedUser = userService.wxLoginOrCreateNewUser(loginUser)
        val token = jwtManager.createToken(AuthUser(loggedUser.id, ChannelType.WECHAT.code));
        loggedUser.token = token

        //缓存一下accesstoken
        redisCache.setCacheObject(CacheConstants.WX_LOGIN_ACCESS_TOKEN_KEY + loggedUser.id, ds.data.token.accessToken);

        return WxLoginResponse(loggedUser)
    }

    @GetMapping("/jsSign")
    @ApiOperation(value = "微信JSSDK签发授权")
    fun wxJSSDKSign(url: String): AjaxResult {
        redisCache.deleteObject(CacheConstants.WX_ACCESS_TOKEN_KEY);
        var accessToken = ""
        if (redisCache.hasKey(CacheConstants.WX_ACCESS_TOKEN_KEY)) {
            accessToken = redisCache.getCacheObject(CacheConstants.WX_ACCESS_TOKEN_KEY);
        } else {
            var accessTokenResult = weixinOfficialApiService.getMPAccessToken()
            var wxResultObj = JSONObject.parseObject(accessTokenResult);
            accessToken = wxResultObj.getString("access_token")
            redisCache.setCacheObject(CacheConstants.WX_ACCESS_TOKEN_KEY, accessToken, 7200, TimeUnit.SECONDS);
        }

        var ticket = ""
        if (redisCache.hasKey(CacheConstants.WX_TICKET_KEY)) {
            ticket = redisCache.getCacheObject(CacheConstants.WX_TICKET_KEY);
        } else {
            var wxResult = weixinOfficialApiService.getJSSDKSgin(accessToken)
            var wxResultObj = JSONObject.parseObject(wxResult);
            ticket = wxResultObj.getString("ticket")
            redisCache.setCacheObject(CacheConstants.WX_TICKET_KEY, ticket, 7200, TimeUnit.SECONDS);
        }
        return weixinOfficialApiService.getJSSDKSign(accessToken, ticket, url)
    }


}
