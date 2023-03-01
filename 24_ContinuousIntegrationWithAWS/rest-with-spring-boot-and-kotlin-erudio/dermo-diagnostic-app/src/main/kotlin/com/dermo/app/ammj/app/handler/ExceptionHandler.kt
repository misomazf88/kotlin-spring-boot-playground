package com.dermo.app.ammj.app.handler

import com.dermo.app.ammj.common.environment.VisionEnvironment.Companion.APP_NAME
import com.dermo.app.ammj.common.exception.DiagnosticException
import com.dermo.app.ammj.common.response.CreateAccountResponse
import com.dermo.app.ammj.common.response.ErrorResponse
import com.fasterxml.jackson.module.kotlin.MissingKotlinParameterException
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.http.converter.HttpMessageNotReadableException
import org.springframework.web.bind.MissingRequestHeaderException
import org.springframework.web.bind.MissingServletRequestParameterException
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler

@ControllerAdvice
class ExceptionHandler {

    private val logger = LoggerFactory.getLogger(this.javaClass.name)
    private val teamCode = "00C"
    private val microserviceId = "1333"

    /**
     * Handles DiagnosticException
     * @see com.dermo.app.ammj.common.exception.DiagnosticException.kt
     */
    @ExceptionHandler(DiagnosticException::class)
    fun handleDiagnosticException(ex: DiagnosticException): ResponseEntity<ErrorResponse> {
        val errorResponse = ErrorResponse(
            status = ex.status,
            message = if (!ex.message.isNullOrEmpty()) {
                ex.message!!
            } else {
                ex.errorCode
            }
        )
        logger.error(
            "--{} --ExceptionHandler:handleDiagnosticException --status: [{}] --message: [{}]",
            APP_NAME, errorResponse.status, errorResponse.message
        )
        return ResponseEntity.status(ex.status).body(errorResponse)
    }

    /**
     * Handles bad request exceptions
     */
    @ExceptionHandler(
        MissingRequestHeaderException::class,
        MissingServletRequestParameterException::class,
        HttpMessageNotReadableException::class,
        MissingKotlinParameterException::class
    )
    fun handleBadRequestException(ex: Exception): ResponseEntity<CreateAccountResponse> {
        val errorResponse = CreateAccountResponse(
            description = ex.message
        )
        logger.error(
            "--{} --ExceptionHandler:handleBadRequestException --message: [{}] --error: [{}]",
            APP_NAME, errorResponse.description, ex.message
        )
        return ResponseEntity(errorResponse, HttpStatus.BAD_REQUEST)
    }
}
