package com.dermo.app.ammj.core.service

import com.dermo.app.ammj.common.constant.Exceptions
import com.dermo.app.ammj.common.environment.VisionEnvironment.Companion.APP_NAME
import com.dermo.app.ammj.common.exception.DiagnosticException
import com.fasterxml.jackson.databind.PropertyNamingStrategies
import com.fasterxml.jackson.databind.annotation.JsonNaming
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service

@Service
class ReportErrorService(
    private val kafkaService: KafkaService
) {

    private val logger = LoggerFactory.getLogger(ReportErrorService::class.java)

    companion object {
        val CLASS = ReportErrorService::class.simpleName
    }

    fun <S> reportError(url: String, responseEntity: Class<S>, message: KafkaReportError) {
        try {
            logger.info("--{} --$CLASS:reportError --Message:[{}] ", APP_NAME, message)
            // val topic = ReportErrorMapper.buildKafkaTopic(url, responseEntity.name, message)
            val topic = ""
            if (topic.isBlank().not()) {
                kafkaService.sendMessage(
                    topic = topic,
                    message = message
                )
            }
        } catch (ex: Exception) {
            logger.error(
                "--{} --$CLASS:reportError --Message:[{}] --Exception:[{}] ",
                APP_NAME, message, ex.message
            )
            throw DiagnosticException(
                500,
                Exceptions.CODE_KAFKA_PUBLISHER_ERROR,
                ex.message ?: ex.cause?.message
            )
        }
    }
}

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy::class)
data class KafkaReportError(
    var url: String? = null,
    var type: String? = null,
    var request: Any? = null,
    var errorCode: String? = null,
    var errorDescription: String? = null,
    var traceability_id: String? = null
)
