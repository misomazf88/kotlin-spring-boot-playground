package com.dermo.app.ammj.common.constant

object Exceptions {
    private const val OPERATION_CODE = "AMMJ"
    private const val MS_CODE = "0001"
    private const val CODE_PREFIX = "${OPERATION_CODE}_${MS_CODE}_"
    const val CODE_REQUEST_MANDATORY_ATTRIBUTE_MISSING = "${CODE_PREFIX}dermo.diagnostic.request_mandatory_attribute_missing"
    const val CODE_INTERNAL_SERVER_ERROR = "${CODE_PREFIX}dermo.diagnostic.internal_server_error"
    const val CODE_KAFKA_PUBLISHER_ERROR = "${CODE_PREFIX}dermo.diagnostic.kafka_published_error"
}
