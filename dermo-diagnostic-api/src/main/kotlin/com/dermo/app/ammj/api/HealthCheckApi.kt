package com.dermo.app.ammj.api

import org.springframework.http.ResponseEntity
import kotlin.collections.HashMap

interface HealthCheckApi {
    fun getHealthCheck(): ResponseEntity<HashMap<String, String>>
}
