package com.dermo.app.ammj.listener.constant

import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Configuration

@Configuration
class Topics {

    companion object {
        lateinit var DIAGNOSTIC_CREATE: String
    }

    @Value("\${spring.kafka.producer.topic.dermo.app.diagnostic.create}")
    fun setDiagnosticCreate(environmentKey: String) {
        DIAGNOSTIC_CREATE = environmentKey
    }
}
