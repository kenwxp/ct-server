package com.cloudtimes.web.core.config

import com.cloudtimes.common.config.CloudTimesConfig
import io.swagger.v3.oas.models.ExternalDocumentation
import io.swagger.v3.oas.models.OpenAPI
import io.swagger.v3.oas.models.info.Info
import io.swagger.v3.oas.models.info.License
import org.springdoc.core.GroupedOpenApi
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration


/**
 * Swagger2的接口配置
 *
 * @author tank
 */
@Configuration
class SwaggerConfig {
    @Autowired
    private lateinit var cloudTimesConfig: CloudTimesConfig


    @Bean
    fun agentGroup(): GroupedOpenApi {
        return GroupedOpenApi.builder()
            .group("代理API")
            .pathsToMatch("/agent/**")
            .build()
    }

    @Bean
    fun productGroup(): GroupedOpenApi {
        return GroupedOpenApi.builder()
            .group("库存API")
            .pathsToMatch("/product/**")
            .build()
    }

    @Bean
    fun promotionGroup(): GroupedOpenApi {
        return GroupedOpenApi.builder()
            .group("营销API")
            .pathsToMatch("/promotion/**")
            .build()
    }

    @Bean
    fun springShopOpenAPI(): OpenAPI {
        return OpenAPI()
            .info(
                Info().title("标题：云时代管理系统_接口文档")
                    .description("描述：云时代管理平台后端API接口文档")
                    .version("版本号: ${cloudTimesConfig.version}")
                    .license(License().name("Apache 2.0").url("http://springdoc.org"))
            )
            .externalDocs(
                ExternalDocumentation()
                    .description("SpringShop Wiki Documentation")
                    .url("https://springshop.wiki.github.org/docs")
            )
    }
}