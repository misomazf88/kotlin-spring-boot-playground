package com.dermo.app.ammj.common.exception

import com.dermo.app.ammj.common.constant.Exceptions

fun Exception.toUnexpectedException(): DiagnosticException {
    return DiagnosticException(
        500,
        Exceptions.CODE_INTERNAL_SERVER_ERROR,
        this.message ?: this.cause?.message
    )
}

fun Exception.toPSQLException(): DiagnosticException {
    return DiagnosticException(
        500,
        Exceptions.CODE_INTERNAL_SERVER_ERROR,
        "Error en DB"
    )
}
