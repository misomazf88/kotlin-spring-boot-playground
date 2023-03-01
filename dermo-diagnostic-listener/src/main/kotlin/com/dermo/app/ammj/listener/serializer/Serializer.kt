package com.dermo.app.ammj.listener.serializer

import com.fasterxml.jackson.databind.ObjectMapper
import org.apache.kafka.common.serialization.Serializer
import org.slf4j.LoggerFactory

class Serializer<T> : Serializer<T> {

    private val logger = LoggerFactory.getLogger(Serializer::class.java)

    companion object {
        private val OBJECT_MAPPER = ObjectMapper()
    }

    override fun configure(props: Map<String, *>?, isKey: Boolean) = Unit

    override fun serialize(topic: String, data: T?): ByteArray? {
        if (data == null)
            return null
        return try {
            OBJECT_MAPPER.writeValueAsBytes(data)
        } catch (e: Exception) {
            logger.error("kafka-streams: Error serializing JSON message " + e.message)
            null
        }
    }

    override fun close() = Unit
}
