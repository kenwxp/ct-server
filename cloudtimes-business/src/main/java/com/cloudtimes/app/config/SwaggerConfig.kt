package com.cloudtimes.app.config

import com.cloudtimes.common.config.CloudTimesConfig
import io.swagger.annotations.ApiOperation
import io.swagger.v3.oas.models.security.SecurityScheme
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import springfox.documentation.builders.ApiInfoBuilder
import springfox.documentation.builders.PathSelectors
import springfox.documentation.builders.RequestHandlerSelectors
import springfox.documentation.service.*
import springfox.documentation.spi.DocumentationType
import springfox.documentation.spi.service.contexts.OperationContext
import springfox.documentation.spi.service.contexts.SecurityContext
import springfox.documentation.spring.web.plugins.Docket

/**
 * Swagger2的接口配置
 *
 * @author tank
 */
@Configuration
class SwaggerConfig {
    /**
     * 系统基础配置
     */
    @Autowired
    private val cloudTimesConfig: CloudTimesConfig? = null

    /**
     * 是否开启swagger
     */
    @Value("\${swagger.enabled}")
    private val enabled = false

    /**
     * 设置请求的统一前缀
     */
    @Value("\${swagger.pathMapping}")
    private val pathMapping: String? = null

    /**
     * 创建API
     */
    @Bean
    fun createRestApi(): Docket {
        return Docket(DocumentationType.OAS_30) // 是否启用Swagger
            .enable(enabled) // 用来创建该API的基本信息，展示在文档的页面中（自定义展示的信息）
            .apiInfo(apiInfo()) // 设置哪些接口暴露给Swagger展示
            .select() // 扫描所有有注解的api，用这种方式更灵活
            .apis(RequestHandlerSelectors.withMethodAnnotation(ApiOperation::class.java)) // 扫描指定包中的swagger注解
            .apis(RequestHandlerSelectors.basePackage("com.cloudtimes.app.controller")) // 扫描所有 .apis(RequestHandlerSelectors.any())
            .paths(PathSelectors.any())
            .build() /* 设置安全模式，swagger可以设置访问token */
            .securitySchemes(securitySchemes())
            .securityContexts(securityContexts())
            .pathMapping(pathMapping)
    }

    @Bean
    fun agentGroup(): Docket {
        return Docket(DocumentationType.OAS_30)
            .groupName("代理API")
            .select()
            .paths(PathSelectors.regex(".*/agent/.*"))
            .build()
    }

    @Bean
    fun rcygGroup(): Docket {
        return Docket(DocumentationType.OAS_30)
            .groupName("蓉城易购API")
            .select()
            .paths(PathSelectors.regex(".*/rcyg/.*"))
            .build()
    }

    @Bean
    fun cashGroup(): Docket {
        return Docket(DocumentationType.OAS_30)
            .groupName("收银API")
            .select()
            .paths(PathSelectors.regex(".*/cash/.*"))
            .build()
    }

    @Bean
    fun mobileGroup(): Docket {
        return Docket(DocumentationType.OAS_30)
            .groupName("店主APP接口")
            .select()
            .paths(PathSelectors.regex(".*/mobile/.*"))
            .build()
    }

    @Bean
    fun mappGroup(): Docket {
        return Docket(DocumentationType.OAS_30)
            .groupName("小程序API")
            .select()
            .paths(PathSelectors.regex(".*/mapp/.*"))
            .build()
    }

    /**
     * 安全模式，这里指定token通过Authorization头请求头传递
     */
    private fun securitySchemes(): List<springfox.documentation.service.SecurityScheme> {
        val apiKeyList: MutableList<springfox.documentation.service.SecurityScheme> = ArrayList()
        apiKeyList.add(ApiKey("Authorization", "Authorization", SecurityScheme.In.HEADER.name))
        return apiKeyList
    }

    /**
     * 安全上下文
     */
    private fun securityContexts(): List<SecurityContext> {
        val securityContexts: MutableList<SecurityContext> = ArrayList()
        securityContexts.add(
            SecurityContext.builder()
                .securityReferences(defaultAuth())
                .operationSelector { o: OperationContext -> o.requestMappingPattern().matches("/.*".toRegex()) }
                .build())
        return securityContexts
    }

    /**
     * 默认的安全上引用
     */
    private fun defaultAuth(): List<SecurityReference> {
        val authorizationScope = AuthorizationScope("global", "accessEverything")
        val authorizationScopes = arrayOfNulls<AuthorizationScope>(1)
        authorizationScopes[0] = authorizationScope
        val securityReferences: MutableList<SecurityReference> = ArrayList()
        securityReferences.add(SecurityReference("Authorization", authorizationScopes))
        return securityReferences
    }

    /**
     * 添加摘要信息
     */
    private fun apiInfo(): ApiInfo {
        // 用ApiInfoBuilder进行定制
        return ApiInfoBuilder() // 设置标题
            .title("标题：云时代系统API_接口文档") // 描述
            .description("描述：云时代系统APP-API") // 作者信息
            .contact(Contact(cloudTimesConfig!!.name, null, null)) // 版本
            .version("版本号:" + cloudTimesConfig.version)
            .build()
    }
}