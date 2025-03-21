package com.myeonghwi.portfolio.admin.interceptor

import org.springframework.web.servlet.config.annotation.InterceptorRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer

class AdminInterceptorConfiguration(
    val adminInterceptor: AdminInterceptor
) : WebMvcConfigurer {
    override fun addInterceptors(registry: InterceptorRegistry) {
        registry.addInterceptor(adminInterceptor)
            .addPathPatterns("/admin/**")
            .excludePathPatterns("/assets/**", "/css/**", "/js/**", "h2**")
    }
}