package com.dermo.app.ammj.app.utils

import org.springframework.http.HttpHeaders

object HeadersUtils {

    fun resolverHeaders(originHeaders: HashMap<String, String>): HttpHeaders {
        val headers = HttpHeaders()
        originHeaders.forEach { (key, value) ->
            headers[key] = value
        }
        return headers
    }
}
