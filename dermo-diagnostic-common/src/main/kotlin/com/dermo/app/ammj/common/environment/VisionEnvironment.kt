package com.dermo.app.ammj.common.environment

import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Configuration

@Configuration("VisionEnvironment")
class VisionEnvironment {

    companion object {
        // App envs
        var APP_NAME: String = "DERMO-DIAGNOSTIC-MS"
        lateinit var ENVIROMENT: String
    }

    @Value("\${spring.application.name}")
    fun setAppName(environmentKey: String) {
        APP_NAME = environmentKey
    }

    @Value("\${microservices.environment}")
    fun setEnvironment(environmentKey: String) {
        ENVIROMENT = environmentKey
    }
}
