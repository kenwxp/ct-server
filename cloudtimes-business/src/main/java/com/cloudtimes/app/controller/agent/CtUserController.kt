package com.cloudtimes.app.controller.agent

import com.cloudtimes.account.domain.CtUser
import com.cloudtimes.account.dto.request.H5LoginRequest
import com.cloudtimes.account.dto.request.QueryByUserIdRequest
import com.cloudtimes.account.dto.request.VerifyRealNameRequest
import com.cloudtimes.account.service.ICtUserService
import com.cloudtimes.agent.dto.request.AgentRegisterRequest
import com.cloudtimes.common.annotation.Log
import com.cloudtimes.common.core.controller.BaseController
import com.cloudtimes.common.core.domain.AjaxResult
import com.cloudtimes.common.core.domain.RestResult
import com.cloudtimes.common.core.domain.entity.AuthUser
import com.cloudtimes.common.core.page.TableDataInfo
import com.cloudtimes.common.enums.BusinessType
import com.cloudtimes.common.enums.ChannelType
import com.cloudtimes.common.utils.JWTManager
import io.swagger.annotations.Api
import io.swagger.annotations.ApiOperation
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

class UserDetailResponse : RestResult<CtUser>()

/**
 * 用户Controller
 *
 * @author 沈兵
 * @date 2023-01-17
 */
@RestController
@RequestMapping("/agent/user")
@Api(tags = ["代理-用户"])
class CtUserController : BaseController() {
    @Autowired
    private lateinit var ctUserService: ICtUserService

    @Autowired
    private lateinit var jwtManager: JWTManager;

    /**
     * 获取用户详细信息
     */
    @PostMapping(value = ["/detail"])
    @ApiOperation("获取用户详细信息")
    fun getInfo(@Valid @RequestBody request: QueryByUserIdRequest): UserDetailResponse {
        val user = ctUserService.selectCtUserById(request.userId!!)
        return UserDetailResponse().apply {
            data = user
        }
    }

    @ApiOperation("实名认证")
    @PostMapping(value = ["/verify_real_name"])
    fun verifyRealName(@Valid @RequestBody request: VerifyRealNameRequest): AjaxResult {
        val existUser = ctUserService.selectCtUserBySsn(request.ssn!!)

        if (existUser !== null && existUser.id != request.userId) {
            return AjaxResult.error("存在相同的身份证用户信息")
        }

        ctUserService.verifyRealName(request)
        return AjaxResult.success("登记认证信息成功")
    }

    /**
     * 获取用户详细信息
     */
    @PostMapping(value = ["/h5_login"])
    @ApiOperation("H5手机用户登陆，测试用")
    fun h5Login(@Valid @RequestBody request: H5LoginRequest): UserDetailResponse {
        val user = ctUserService.selectCtUserByAccount(request.mobile!!)
            ?: return UserDetailResponse().apply {
                code = HttpStatus.NOT_FOUND.value()
                msg = "用户不存在"
            }

        user.let {
            val token = jwtManager.createToken(AuthUser(it.id, ChannelType.MOBILE.code));
            it.token = token
        }

        return UserDetailResponse().apply {
            data = user
        }
    }


    @PostMapping(value = ["/register"])
    @ApiOperation("代理用户注册")
    fun register(@Valid @RequestBody request: AgentRegisterRequest): UserDetailResponse {
        val user = ctUserService.selectCtUserByAccount(request.mobile!!)
        return UserDetailResponse().apply {
            data = user
        }
    }
}
