package com.dermo.app.ammj.app.controller

import com.dermo.app.ammj.api.DiagnosticControllerApi
import com.dermo.app.ammj.common.constant.Constant.Trace.DERMO_TRACEABILITY_ID
import com.dermo.app.ammj.common.request.CreateAccountRequest
import com.dermo.app.ammj.common.request.CreateInjuryRequest
import com.dermo.app.ammj.common.request.UserProfileRequest
import com.dermo.app.ammj.common.route.Route
import com.dermo.app.ammj.core.service.DiagnosticService
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestHeader
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import java.util.UUID
import javax.validation.Valid

@RestController
@RequestMapping(produces = [(MediaType.APPLICATION_JSON_VALUE)])
class DiagnosticController(
    private val accountService: DiagnosticService
) : DiagnosticControllerApi {

    @PostMapping(Route.Diagnostic.ACCOUNT)
    override fun createAccount(
        @RequestHeader(DERMO_TRACEABILITY_ID) dermoTraceabilityId: UUID,
        @RequestBody @Valid createAccountRequest: CreateAccountRequest
    ) = accountService.createAccount(createAccountRequest)

    @GetMapping(Route.Diagnostic.ACCOUNT_LOGIN)
    override fun login(
        @RequestHeader(DERMO_TRACEABILITY_ID) dermoTraceabilityId: UUID,
        @RequestParam correoElectronico: String?,
        @RequestParam contrasena: String?
    ) = accountService.login(
        CreateAccountRequest(
            correoElectronico = correoElectronico,
            contrasena = contrasena
        )
    )

    @PostMapping(Route.Diagnostic.USER_PROFILE_CREATE)
    override fun createUserProfile(
        @RequestHeader(DERMO_TRACEABILITY_ID) dermoTraceabilityId: UUID,
        @RequestBody @Valid userProfileRequest: UserProfileRequest
    ) = accountService.createUserProfile(userProfileRequest)

    @PostMapping(Route.Diagnostic.INJURY_CREATE)
    override fun createInjury(
        @RequestHeader(DERMO_TRACEABILITY_ID) dermoTraceabilityId: UUID,
        @RequestBody @Valid createInjuryRequest: CreateInjuryRequest
    ) = accountService.createInjury(createInjuryRequest)

    @GetMapping(Route.Diagnostic.INJURIES)
    override fun getAllInjuries(
        @RequestHeader(DERMO_TRACEABILITY_ID) dermoTraceabilityId: UUID,
        @RequestParam correoElectronico: String?
    ) = accountService.getAllInjuries(correoElectronico)
}
