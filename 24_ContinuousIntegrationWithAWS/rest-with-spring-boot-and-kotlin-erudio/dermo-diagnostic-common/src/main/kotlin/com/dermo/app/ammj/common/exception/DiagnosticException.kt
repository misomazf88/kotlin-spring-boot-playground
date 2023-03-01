package com.dermo.app.ammj.common.exception

open class DiagnosticException : RuntimeException {

    var status: Int
    var errorCode: String

    constructor(
        status: Int,
        errorCode: String,
        message: String?,
    ) : super(message) {
        this.status = status
        this.errorCode = errorCode
    }

    constructor(status: Int, errorCode: Int, message: String?) : super(message) {
        this.status = status
        this.errorCode = errorCode.toString()
    }
}
