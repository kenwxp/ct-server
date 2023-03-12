package com.cloudtimes.app.config

import io.swagger.v3.oas.models.ExternalDocumentation
import io.swagger.v3.oas.models.OpenAPI
import io.swagger.v3.oas.models.info.Info
import io.swagger.v3.oas.models.info.License
import org.springdoc.core.GroupedOpenApi
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

/**
 * Swagger2的接口配置
 *
 * @author tank
 */
@Configuration
class SwaggerConfig {
    @Bean
    fun agentGroup(): GroupedOpenApi {
        return GroupedOpenApi.builder()
            .group("公众号API")
            .pathsToMatch("/mp/**")
            .build()
    }

    @Bean
    fun rcygGroup(): GroupedOpenApi {
        return GroupedOpenApi.builder()
            .group("蓉城易购API")
            .pathsToMatch("/rcyg/**")
            .build()
    }

    @Bean
    fun cashGroup(): GroupedOpenApi {
        return GroupedOpenApi.builder()
            .group("收银API")
            .pathsToMatch("/cash/**")
            .build()
    }

    @Bean
    fun mobileGroup(): GroupedOpenApi {
        return GroupedOpenApi.builder()
            .group("店主APP接口")
            .pathsToMatch("/mobile/**")
            .build()
    }

    @Bean
    fun mappGroup(): GroupedOpenApi {
        return GroupedOpenApi.builder()
            .group("小程序API")
            .pathsToMatch("/mapp/**")
            .build()
    }

    @Bean
    fun springShopOpenAPI(): OpenAPI {
        return OpenAPI()
            .info(
                Info().title("标题：云时代系统API_接口文档")
                    .description("描述：云时代系统APP-API")
                    .version("v0.0.1")
                    .license(License().name("Apache 2.0").url("http://springdoc.org"))
            )
            .externalDocs(
                ExternalDocumentation()
                    .description("SpringShop Wiki Documentation")
                    .url("https://springshop.wiki.github.org/docs")
            )
    }
}
