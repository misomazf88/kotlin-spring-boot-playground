package com.dermo.app.ammj.app.controller

import com.dermo.app.ammj.api.HealthCheckApi
import com.dermo.app.ammj.common.route.Route
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.bind.annotation.RestController
import kotlin.collections.HashMap

@RestController
@RequestMapping(value = [(Route.HEALTH)], produces = [(MediaType.APPLICATION_JSON_VALUE)])
class HealthCheckController() : HealthCheckApi {

    @GetMapping
    @ResponseBody
    override fun getHealthCheck(): ResponseEntity<HashMap<String, String>> = ResponseEntity(hashMapOf("status" to "UP"), HttpStatus.OK)
}
