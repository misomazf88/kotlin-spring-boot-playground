package com.dermo.app.ammj.listener.deserializer

import com.fasterxml.jackson.databind.ObjectMapper
import org.apache.kafka.common.serialization.Deserializer
import org.slf4j.LoggerFactory
import org.springframework.util.ClassUtils

class Deserializer<T> : Deserializer<T> {

    private val logger = LoggerFactory.getLogger(Deserializer::class.java)

    private var targets: MutableMap<String, Class<T>> = mutableMapOf()

    companion object {
        private val OBJECT_MAPPER = ObjectMapper()
    }

    override fun close() {}

    override fun configure(props: Map<String, *>, isKey: Boolean) {
        props.forEach { (key, value) ->
            if (value is String && key.contains("dermo.app.diagnostic")) {
                targets[key] = ClassUtils.forName(value, null) as Class<T>
            }
        }
    }

    override fun deserialize(topic: String, bytes: ByteArray?): T? =
        bytes?.let {
            try {
                OBJECT_MAPPER.readValue(bytes, targets[topic])
            } catch (e: Exception) {
                logger.error("kafka-streams: Error deserializing JSON message " + e.message)
                null
            }
        }
}
