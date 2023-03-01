package com.dermo.app.ammj.api

import com.dermo.app.ammj.common.request.CreateAccountRequest
import com.dermo.app.ammj.common.request.CreateInjuryRequest
import com.dermo.app.ammj.common.request.UserProfileRequest
import com.dermo.app.ammj.common.response.CreateAccountResponse
import com.dermo.app.ammj.common.response.GetInjuriesResponse
import org.springframework.http.ResponseEntity
import java.util.UUID

interface DiagnosticControllerApi {

    fun createAccount(
        dermoTraceabilityId: UUID,
        createAccountRequest: CreateAccountRequest
    ): ResponseEntity<CreateAccountResponse>

    fun login(
        dermoTraceabilityId: UUID,
        correoElectronico: String?,
        contrasena: String?
    ): ResponseEntity<CreateAccountResponse>

    fun createUserProfile(
        dermoTraceabilityId: UUID,
        createAccountRequest: UserProfileRequest
    ): ResponseEntity<CreateAccountResponse>

    fun createInjury(
        dermoTraceabilityId: UUID,
        createInjuryRequest: CreateInjuryRequest
    ): ResponseEntity<CreateAccountResponse>

    fun getAllInjuries(
        dermoTraceabilityId: UUID,
        correoElectronico: String?
    ): ResponseEntity<GetInjuriesResponse>
}
