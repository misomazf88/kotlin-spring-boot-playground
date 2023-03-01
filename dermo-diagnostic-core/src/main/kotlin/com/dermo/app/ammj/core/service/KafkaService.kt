package com.dermo.app.ammj.core.service

import com.dermo.app.ammj.common.constant.Exceptions
import com.dermo.app.ammj.common.environment.VisionEnvironment.Companion.APP_NAME
import com.dermo.app.ammj.common.exception.DiagnosticException
import com.dermo.app.ammj.core.util.ProducerUtil
import org.apache.kafka.clients.producer.ProducerRecord
import org.slf4j.LoggerFactory
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.stereotype.Service

@Service
class KafkaService(
    private val kafkaTemplate: KafkaTemplate<String, Any>
) {
    private val logger = LoggerFactory.getLogger(KafkaService::class.java)

    companion object {
        val CLASS = KafkaService::class.simpleName
    }

    fun sendMessage(
        topic: String,
        message: Any,
        headersRequest: Map<String, List<String>> = emptyMap()
    ) {
        try {
            logger.info("--{} --$CLASS:sendMessage --Topic:[{}] ", APP_NAME, topic)
            var record = ProducerRecord<String, Any>(topic, message)
            record = ProducerUtil.addHeaders(headersRequest, record)
            kafkaTemplate.send(record)
        } catch (ex: Exception) {
            logger.error(
                "--{} --$CLASS:sendMessage --Topic:[{}] --Message:[{}] --Headers:[{}] --Exception:[{}] ",
                APP_NAME, topic, message, headersRequest, ex.message
            )
            throw DiagnosticException(
                500,
                Exceptions.CODE_KAFKA_PUBLISHER_ERROR,
                ex.message ?: ex.cause?.message
            )
        }
    }
}
