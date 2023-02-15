package com.cloudtimes.app.controller.agent

import javax.servlet.http.HttpServletResponse;
import com.cloudtimes.account.domain.CtUser
import com.cloudtimes.account.dto.request.WxLoginRequest
import com.cloudtimes.partner.weixin.ICtWeixinOfficialApiService
import com.alibaba.fastjson.JSONObject;
import com.cloudtimes.account.service.ICtUserService
import com.cloudtimes.common.core.domain.RestResult
import com.cloudtimes.common.exception.ServiceException
import com.cloudtimes.common.utils.JWTManager
import io.swagger.annotations.Api
import com.cloudtimes.common.core.domain.entity.AuthUser;
import com.cloudtimes.common.enums.ChannelType
import io.swagger.annotations.ApiOperation
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import javax.validation.Valid

import me.zhyd.oauth.model.AuthCallback
import me.zhyd.oauth.model.AuthResponse
import org.springframework.beans.factory.annotation.Autowired

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.web.bind.annotation.GetMapping


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
        if ( ds.code !== 2000 ) {
            logger.info("${ds.code}")
            logger.info(ds.msg)
            throw  ServiceException("微信授权失败: ${ds.msg}")
        }

        val userInfo = ds.data
        val unionId = userInfo.rawUserInfo.getObject("unionid", String::class.java)
        logger.info(JSONObject.toJSONString(ds));
        logger.info("unionid: $unionId")


        // 登陆或创建用户
        val loginUser = CtUser().apply {
            wxUnionId = unionId
            nickName = userInfo.nickname
            wxAvatar = userInfo.avatar
        }
        val loggedUser = userService.wxLoginOrCreateNewUser(loginUser)
        val token = jwtManager.createToken(AuthUser(loggedUser.id, ChannelType.WECHAT.code));
        loggedUser.token = token

        return WxLoginResponse(loggedUser)
    }
}
