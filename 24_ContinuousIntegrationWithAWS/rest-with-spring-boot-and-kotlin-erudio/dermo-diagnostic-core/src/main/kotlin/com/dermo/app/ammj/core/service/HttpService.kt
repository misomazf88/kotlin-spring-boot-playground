package com.dermo.app.ammj.core.service

import org.springframework.stereotype.Service

@Service
class HttpService()
    /*private val restClient: RestClient,
    private val reportErrorService: ReportErrorService
) {

    private val logger = LoggerFactory.getLogger(HttpService::class.java)

    companion object {
        val CLASS = HttpService::class.simpleName
        const val MS_EXCEPTION = "DiagnosticException"
        val SUCCESSFUL_CODES = listOf(HttpStatus.OK.value(), HttpStatus.CREATED.value(), HttpStatus.ACCEPTED.value())
    }

    fun <S> get(
        url: String,
        headers: Map<String, List<String>>,
        responseEntity: Class<S>,
        timeOut: Long = TIMEOUT
    ): HttpResponseBodyAndHeader<S> = try {
        handleHttp(
            url = url,
            response = restClient.get(timeOut, url, headers),
            responseEntity = responseEntity,
            method = HttpMethod.GET
        )
    } catch (ce: DiagnosticException) {
        logger.error("--$APP_NAME --$CLASS:get --DiagnosticException:[{}] ", ce.message)
        reportErrorService.reportError(url, responseEntity, ReportErrorMapper.buildReportError(url, headers, null, ce))
        throw ce
    } catch (ex: RestException) {
        logger.error("--$APP_NAME --$CLASS:get --Exception:[{}] ", ex.message)
        reportErrorService.reportError(url, responseEntity, ReportErrorMapper.buildReportError(url, headers, null, ex.toServiceUnavailableException()))
        throw ex.toServiceUnavailableException()
    }

    fun <S, T> post(
        url: String,
        headers: Map<String, List<String>>,
        body: S,
        responseEntity: Class<T>,
        timeOut: Long = TIMEOUT
    ): HttpResponseBodyAndHeader<T> = try {
        handleHttp(
            url = url,
            response = restClient.post(timeOut, url, headers, body.objectToString()),
            responseEntity = responseEntity,
            method = HttpMethod.POST
        )
    } catch (ce: DiagnosticException) {
        logger.error("--$APP_NAME --$CLASS:post --DiagnosticException:[{}] ", ce.message)
        reportErrorService.reportError(url, responseEntity, ReportErrorMapper.buildReportError(url, headers, body, ce))
        throw ce
    } catch (ex: RestException) {
        logger.error("--$APP_NAME --$CLASS:post --Exception:[{}] ", ex.message)
        reportErrorService.reportError(url, responseEntity, ReportErrorMapper.buildReportError(url, headers, body, ex.toServiceUnavailableException()))
        throw ex.toServiceUnavailableException()
    }

    fun <S> post(
        url: String,
        headers: Map<String, List<String>>,
        body: S,
        timeOut: Long = TIMEOUT
    ): HttpResponseBodyAndHeader<S> = try {
        handleHttp(
            url = url,
            response = restClient.post(timeOut, url, headers, body.objectToString()),
            method = HttpMethod.POST
        )
    } catch (ex: RestException) {
        logger.error("--$APP_NAME --$CLASS:post --Exception:[{}] ", ex.message)
        throw ex.toServiceUnavailableException()
    }

    fun <S, T> patch(
        url: String,
        headers: Map<String, List<String>>,
        body: S,
        responseEntity: Class<T>,
        timeOut: Long = TIMEOUT
    ): HttpResponseBodyAndHeader<T> = try {
        handleHttp(
            url = url,
            response = restClient.patch(timeOut, url, headers, body.objectToString()),
            responseEntity = responseEntity,
            method = HttpMethod.PATCH
        )
    } catch (ce: DiagnosticException) {
        logger.error("--$APP_NAME --$CLASS:patch --DiagnosticException:[{}] ", ce.message)
        reportErrorService.reportError(url, responseEntity, ReportErrorMapper.buildReportError(url, headers, body, ce))
        throw ce
    } catch (ex: RestException) {
        logger.error("--$APP_NAME --$CLASS:patch --Exception:[{}] ", ex.message)
        reportErrorService.reportError(url, responseEntity, ReportErrorMapper.buildReportError(url, headers, null, ex.toServiceUnavailableException()))
        throw ex.toServiceUnavailableException()
    }

    fun <S, T> delete(
        url: String,
        headers: Map<String, List<String>>,
        body: S,
        responseEntity: Class<T>,
        timeOut: Long = TIMEOUT
    ): HttpResponseBodyAndHeader<T> = try {
        handleHttp(
            url = url,
            response = restClient.delete(timeOut, url, headers),
            responseEntity = responseEntity,
            method = HttpMethod.DELETE
        )
    } catch (ce: DiagnosticException) {
        logger.error("--$APP_NAME --$CLASS:delete --DiagnosticException:[{}] ", ce.message)
        reportErrorService.reportError(url, responseEntity, ReportErrorMapper.buildReportError(url, headers, body, ce))
        throw ce
    } catch (ex: RestException) {
        logger.error("--$APP_NAME --$CLASS:delete --Exception:[{}] ", ex.message)
        reportErrorService.reportError(url, responseEntity, ReportErrorMapper.buildReportError(url, headers, body, ex.toServiceUnavailableException()))
        throw ex.toServiceUnavailableException()
    }

    private fun validateStatus(
        url: String,
        response: RestResponse,
        method: HttpMethod,
        responseBody: String
    ) {
        if (response.statusCode !in SUCCESSFUL_CODES) {
            logger.error(
                "--$APP_NAME --$CLASS:validateStatus Error --Method [{}], Url [{}], Error [{}] --Status[{}] ",
                method, url, responseBody.trimIndent().replace("\n", ""), response.statusCode
            )
            VisionUtil.checkResponseMessageError(response)
        }
    }

    private fun <S> buildResponse(
        responseEntity: Class<S>? = null,
        responseBody: String,
        responseHeaders: MutableMap<String, List<String>>
    ): HttpResponseBodyAndHeader<S> {
        return if (responseEntity != null) {
            if (responseBody.isNotBlank()) {
                HttpResponseBodyAndHeader(
                    responseHeaders,
                    responseBody.stringToObject(responseEntity) // TODO when the content of the response has not relation with the expected response, everything will be generated with null values instead of throwing and exception.
                )
            } else {
                throw Exception("Response body is null or blank")
            }
        } else {
            HttpResponseBodyAndHeader(responseHeaders)
        }
    }

    private fun <S> handleHttp(
        url: String,
        response: RestResponse,
        responseEntity: Class<S>? = null,
        method: HttpMethod
    ) =
        try {
            val responseBody: String = response.bodyAsString
            val responseHeaders: MutableMap<String, List<String>> = response.headers
            logger.info("--$APP_NAME --$CLASS:handleHttp --Method [{}], url:[{}]", method, url)
            validateStatus(url = url, response = response, method = method, responseBody = responseBody)
            buildResponse(
                responseEntity = responseEntity,
                responseBody = responseBody,
                responseHeaders = responseHeaders
            )
        } catch (ex: DiagnosticException) {
            logger.error(
                "--$APP_NAME --$CLASS:handleHttp --Method [{}], $MS_EXCEPTION[{}] ", method, ex.message
            )
            throw ex
        } catch (ex: Exception) {
            logger.error(
                "--$APP_NAME --$CLASS:handleHttp --Method [{}], Exception [{}] ", method, ex.message
            )
            throw DiagnosticException(
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                CODE_INTERNAL_SERVER_ERROR,
                MSG_INTERNAL_ERROR
            )
        }
}

data class HttpResponseBodyAndHeader<T>(val headers: Map<String, List<String>>, val response: T? = null)

fun RestException.toServiceUnavailableException(): DiagnosticException {
    return CobisException(
        HttpStatus.SERVICE_UNAVAILABLE.value(),
        Exceptions.CODE_SERVICE_UNAVAILABLE,
        this.message ?: this.cause?.message
    )
}*/
