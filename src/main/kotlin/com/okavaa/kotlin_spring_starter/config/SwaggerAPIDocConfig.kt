package com.okavaa.kotlin_spring_starter.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.servlet.config.annotation.EnableWebMvc
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer
import springfox.documentation.builders.ApiInfoBuilder
import springfox.documentation.builders.PathSelectors
import springfox.documentation.builders.RequestHandlerSelectors
import springfox.documentation.service.ApiInfo
import springfox.documentation.service.ApiKey
import springfox.documentation.service.AuthorizationScope
import springfox.documentation.service.SecurityReference
import springfox.documentation.spi.DocumentationType
import springfox.documentation.spi.service.contexts.SecurityContext
import springfox.documentation.spring.web.paths.RelativePathProvider
import springfox.documentation.spring.web.plugins.Docket
import springfox.documentation.swagger2.annotations.EnableSwagger2
import java.time.LocalDate
import java.util.*

@EnableWebMvc
@Configuration
@EnableSwagger2
class SwaggerAPIDocConfig : WebMvcConfigurationSupport() {


    override fun addResourceHandlers(registry: ResourceHandlerRegistry) {
        registry.addResourceHandler("swagger-ui.html").addResourceLocations("classpath:/META-INF/resources/")
        registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/")
    }

    @Bean
    fun webMvcConfigurer(): WebMvcConfigurer? {
        return object : WebMvcConfigurer {
            override fun addResourceHandlers(registry: ResourceHandlerRegistry) {
                registry.addResourceHandler("swagger-ui.html").addResourceLocations("classpath:/META-INF/resources/")
                registry.addResourceHandler("/webjars/**")
                    .addResourceLocations("classpath:/META-INF/resources/webjars/")
            }
        }
    }

    @Bean
    fun api(): Docket {
        return Docket(DocumentationType.SWAGGER_2).directModelSubstitute(LocalDate::class.java, Date::class.java)
            .pathProvider(object : RelativePathProvider(servletContext) {
                override fun getApplicationBasePath(): String {
                    return "/"
                }
            }).select().apis(RequestHandlerSelectors.basePackage("com.okavaa.kotlin_spring_starter.controllers"))
            .paths(PathSelectors.any()).build().apiInfo(apiInfo()).securitySchemes(listOf(apiKey()))
            .securityContexts(listOf(securityContext()))
    }

    private fun apiKey(): ApiKey {
        return ApiKey("Bearer", "Authorization", "header")
    }

    private fun securityContext(): SecurityContext {
        return SecurityContext.builder().securityReferences(defaultAuth()).forPaths(PathSelectors.regex("/.*")).build()
    }

    private fun defaultAuth(): List<SecurityReference?> {
        val authorizationScope = AuthorizationScope("global", "accessEverything")
        val authorizationScopes = arrayOf(authorizationScope)
        return listOf(SecurityReference("Bearer", authorizationScopes))
    }


    private fun apiInfo(): ApiInfo? {
        return ApiInfoBuilder().title("OkavaPay").description("APIs Documentation")
            .termsOfServiceUrl("https://github.com/irumvanselme")
            .version("1.0").build()
    }
}