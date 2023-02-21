package com.cloudtimes.partner.weixin

import com.cloudtimes.common.core.domain.AjaxResult
import me.zhyd.oauth.model.AuthCallback
import me.zhyd.oauth.model.AuthResponse
import me.zhyd.oauth.model.AuthUser
import java.security.NoSuchAlgorithmException

/**
 * 微公众号
 */
interface ICtWeixinOfficialApiService {
    /**
     * 获取微信授权地址
     *
     * @return
     */
    fun getWXAuthURL(type: String?, inviteCode: String?): String

    fun login(callback: AuthCallback): AuthResponse<AuthUser>

    fun getJSSDKSign(accessToken: String): String

    fun getMPAccessToken(): String

    @Throws(NoSuchAlgorithmException::class)
    fun getJSSDKSign(accessToken: String, ticket: String, url: String): AjaxResult
}