package com.dermo.app.ammj.core.mapper

import com.dermo.app.ammj.common.request.CreateAccountRequest
import com.dermo.app.ammj.common.request.CreateInjuryRequest
import com.dermo.app.ammj.common.request.UserProfileRequest
import com.dermo.app.ammj.common.response.CreateAccountResponse
import com.dermo.app.ammj.common.response.GetInjuriesResponse
import com.dermo.app.ammj.common.response.InjuryResponse
import com.dermo.app.ammj.domain.entity.AccountEntity
import com.dermo.app.ammj.domain.entity.InjuryEntity
import com.dermo.app.ammj.domain.entity.UserProfileEntity
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import java.util.Optional
import java.util.UUID
import kotlin.collections.ArrayList

object DiagnosticMapper {

    fun getAccountEntity(request: CreateAccountRequest) =
        AccountEntity(
            id = UUID.randomUUID(),
            correoElectronico = request.correoElectronico,
            contrasena = request.contrasena
        )

    fun createAccountResponse(entity: AccountEntity) =
        ResponseEntity(
            CreateAccountResponse(
                description = "Cuenta creada exitosamente",
                createdAt = entity.createdAt
            ),
            HttpStatus.CREATED
        )

    fun createUserProfileResponse(entity: UserProfileEntity) =
        ResponseEntity(
            CreateAccountResponse(
                description = "Perfil creado exitosamente",
                createdAt = entity.createdAt
            ),
            HttpStatus.CREATED
        )

    fun createInjuryResponse(entity: InjuryEntity) =
        ResponseEntity(
            CreateAccountResponse(
                description = "Lesion creada exitosamente",
                createdAt = entity.createdAt,
                updatedAt = entity.updatedAt
            ),
            HttpStatus.CREATED
        )

    fun updateUserProfileResponse(entity: UserProfileEntity) =
        ResponseEntity(
            CreateAccountResponse(
                description = "Perfil actualizado exitosamente",
                createdAt = entity.createdAt,
                updatedAt = entity.updatedAt
            ),
            HttpStatus.CREATED
        )

    fun loginResponse(entity: AccountEntity) =
        ResponseEntity(
            CreateAccountResponse(
                description = "Inicio de sesion exitoso",
                createdAt = entity.createdAt
            ),
            HttpStatus.OK
        )

    fun loginPasswordErrorResponse() =
        ResponseEntity(
            CreateAccountResponse(
                description = "Credenciales invalidas"
            ),
            HttpStatus.UNAUTHORIZED
        )

    fun accountErrorResponse() =
        ResponseEntity(
            CreateAccountResponse(
                description = "La cuenta no existe"
            ),
            HttpStatus.NOT_FOUND
        )

    fun profileErrorResponse() =
        ResponseEntity(
            CreateAccountResponse(
                description = "El perfil dermatologico no existe"
            ),
            HttpStatus.CONFLICT
        )

    fun createAccountResponseCorreoExiste() =
        ResponseEntity(
            CreateAccountResponse(
                description = "El correo ingresado ya esta registrado"
            ),
            HttpStatus.BAD_REQUEST
        )

    fun getUserProfileEntity(request: UserProfileRequest, accountEntity: AccountEntity) =
        UserProfileEntity(
            id = accountEntity.id,
            correoElectronico = request.correoElectronico,
            nombre = request.nombre,
            edad = request.edad,
            ciudad = request.ciudad,
            tipoDePiel = request.tipoDePiel,
            fotoDePiel = request.fotoDePiel,
        )

    fun getInjuryEntity(request: CreateInjuryRequest) =
        InjuryEntity(
            id = UUID.randomUUID(),
            correoElectronico = request.correoElectronico,
            tipoDeLesion = request.tipoDeLesion,
            formaDeLesion = request.formaDeLesion,
            numeroDeLesiones = request.numeroDeLesiones,
            distribucion = request.distribucion,
            fotoDeLesion = request.fotoDeLesion,
        )

    fun getAllInjuriesEntity(request: UserProfileEntity, injuries: List<Optional<InjuryEntity>>): ResponseEntity<GetInjuriesResponse> {

        var injuriesList = ArrayList<InjuryResponse>()

        injuries.forEach {
            var injury = InjuryResponse(
                tipoDeLesion = it.get().tipoDeLesion,
                formaDeLesion = it.get().formaDeLesion,
                numeroDeLesiones = it.get().numeroDeLesiones,
                distribucion = it.get().distribucion,
                fotoDeLesion = it.get().fotoDeLesion,
                createdAt = it.get().createdAt,
                updatedAt = it.get().updatedAt,
            )
            injuriesList.add(injury)
        }

        var getInjuriesResponse = GetInjuriesResponse(
            correoElectronico = request.correoElectronico,
            nombre = request.nombre,
            edad = request.edad,
            ciudad = request.ciudad,
            tipoDePiel = request.tipoDePiel,
            fotoDePiel = request.fotoDePiel,
            lesiones = injuriesList,
            description = "Lesiones registradas a la fecha"
        )
        return ResponseEntity(getInjuriesResponse, HttpStatus.OK)
    }
}
