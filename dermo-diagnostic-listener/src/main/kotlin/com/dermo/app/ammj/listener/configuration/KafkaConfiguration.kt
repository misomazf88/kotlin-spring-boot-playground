package com.dermo.app.ammj.listener.configuration

import org.apache.kafka.clients.admin.AdminClientConfig
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Profile
import org.springframework.kafka.core.KafkaAdmin

@Configuration
@Profile("default", "dev", "prod")
class KafkaConfiguration {

    private val logger = LoggerFactory.getLogger(KafkaConfiguration::class.java)

    companion object {
        val CLASS = KafkaConfiguration::class.simpleName
    }

    @Value("\${spring.kafka.bootstrap-servers}")
    private lateinit var bootstrapServers: String

    @Bean
    fun kafkaAdmin(): KafkaAdmin {
        return KafkaAdmin(
            mutableMapOf<String, Any>(
                AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG to bootstrapServers
            )
        )
    }
}
