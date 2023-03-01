package com.dermo.app.ammj.core.util

import org.apache.kafka.clients.producer.ProducerRecord

object ProducerUtil {

    fun addHeaders(
        headers: Map<String, List<String>>,
        record: ProducerRecord<String, Any>
    ): ProducerRecord<String, Any> {
        headers.keys.forEach {
            record.headers().add(it, headers[it]?.toString()?.toByteArray())
        }
        return record
    }
}
