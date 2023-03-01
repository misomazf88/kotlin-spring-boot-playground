package com.dermo.app.ammj.app.configuration

import org.springframework.boot.test.context.TestConfiguration
import org.springframework.web.servlet.config.annotation.InterceptorRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer

@TestConfiguration
class SecurityWebConfiguration : WebMvcConfigurer {

    override fun addInterceptors(registry: InterceptorRegistry) {
        super.addInterceptors(registry)
    }
}
