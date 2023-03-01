package com.dermo.app.ammj.core.service

import com.dermo.app.ammj.common.environment.VisionEnvironment.Companion.APP_NAME
import com.dermo.app.ammj.common.exception.toUnexpectedException
import com.dermo.app.ammj.common.request.CreateAccountRequest
import com.dermo.app.ammj.common.request.CreateInjuryRequest
import com.dermo.app.ammj.common.request.UserProfileRequest
import com.dermo.app.ammj.common.response.CreateAccountResponse
import com.dermo.app.ammj.common.response.GetInjuriesResponse
import com.dermo.app.ammj.core.mapper.DiagnosticMapper
import com.dermo.app.ammj.domain.repository.AccountRepository
import com.dermo.app.ammj.domain.repository.InjuryRepository
import com.dermo.app.ammj.domain.repository.UserProfileRepository
import org.slf4j.LoggerFactory
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service
import java.sql.Timestamp
import java.time.LocalDateTime
import javax.transaction.Transactional

@Service
class DiagnosticService(
    private val accountRepository: AccountRepository,
    private val userProfileRepository: UserProfileRepository,
    private val injuryRepository: InjuryRepository
) {

    private var logger = LoggerFactory.getLogger(DiagnosticService::class.java)
    private val CLASS = DiagnosticService::class.simpleName

    @Transactional
    fun createAccount(createAcccountRequest: CreateAccountRequest): ResponseEntity<CreateAccountResponse> = try {
        logger.info(
            "--$APP_NAME --$CLASS:createAccount --correoElectronico[{}] --contrasena[{}]",
            createAcccountRequest.correoElectronico, createAcccountRequest.contrasena
        )

        if (accountRepository.findByCorreoElectronico(createAcccountRequest.correoElectronico.toString()).isPresent) {
            DiagnosticMapper.createAccountResponseCorreoExiste()
        } else if (accountRepository.findByCorreoElectronico(createAcccountRequest.correoElectronico.toString()).isPresent && accountRepository.findByCorreoElectronico(
                createAcccountRequest.correoElectronico.toString()
            ).get().contrasena.equals(createAcccountRequest.contrasena)
        ) {
            DiagnosticMapper.createAccountResponseCorreoExiste()
        } else {
            val newAccount = accountRepository.save(DiagnosticMapper.getAccountEntity(createAcccountRequest))
            DiagnosticMapper.createAccountResponse(newAccount)
        }
    } catch (ex: Exception) {
        logger.error(
            "--$APP_NAME --$CLASS:create --Request[{}] --Exception:[{}]",
            createAcccountRequest, ex.message
        )
        throw ex.toUnexpectedException()
    }

    fun login(createAcccountRequest: CreateAccountRequest): ResponseEntity<CreateAccountResponse> = try {
        logger.info(
            "--$APP_NAME --$CLASS:login --correoElectronico[{}] --contrasena[{}]",
            createAcccountRequest.correoElectronico, createAcccountRequest.contrasena
        )
        val accountDb = accountRepository.findByCorreoElectronico(createAcccountRequest.correoElectronico!!)
        if (accountDb.isPresent && accountDb.get().contrasena.equals(createAcccountRequest.contrasena)) {
            DiagnosticMapper.loginResponse(accountDb.get())
        } else if (accountDb.isPresent && !accountDb.get().contrasena.equals(createAcccountRequest.contrasena)) {
            DiagnosticMapper.loginPasswordErrorResponse()
        } else {
            DiagnosticMapper.accountErrorResponse()
        }
    } catch (ex: Exception) {
        logger.error(
            "--$APP_NAME --$CLASS:create --Request[{}] --Exception:[{}]",
            createAcccountRequest, ex.message
        )
        throw ex.toUnexpectedException()
    }

    @Transactional
    fun createUserProfile(userProfileRequest: UserProfileRequest): ResponseEntity<CreateAccountResponse> = try {
        logger.info(
            "--$APP_NAME --$CLASS:createUserProfile --correo electronico[{}] --nombre[{}] --edad[{}] --ciudad[{}] --tipo de piel[{}] --foto de piel[{}]",
            userProfileRequest.correoElectronico, userProfileRequest.nombre, userProfileRequest.edad, userProfileRequest.ciudad,
            userProfileRequest.tipoDePiel, userProfileRequest.fotoDePiel
        )
        val accountDb = accountRepository.findByCorreoElectronico(userProfileRequest.correoElectronico!!)
        if (!accountDb.isPresent) {
            DiagnosticMapper.accountErrorResponse()
        }
        val userProfileDb = userProfileRepository.findByCorreoElectronico(userProfileRequest.correoElectronico!!)
        if (userProfileDb.isPresent) {
            userProfileDb.get().nombre = userProfileRequest.nombre
            userProfileDb.get().edad = userProfileRequest.edad
            userProfileDb.get().ciudad = userProfileRequest.ciudad
            userProfileDb.get().tipoDePiel = userProfileRequest.tipoDePiel
            userProfileDb.get().fotoDePiel = userProfileRequest.fotoDePiel
            userProfileDb.get().updatedAt = Timestamp.valueOf(LocalDateTime.now())
            userProfileRepository.save(userProfileDb.get())
            DiagnosticMapper.updateUserProfileResponse(userProfileDb.get())
        } else {
            val newProfile = userProfileRepository.save(DiagnosticMapper.getUserProfileEntity(userProfileRequest, accountDb.get()))
            DiagnosticMapper.createUserProfileResponse(newProfile)
        }
    } catch (ex: Exception) {
        logger.error(
            "--$APP_NAME --$CLASS:createUserProfile --Request[{}] --Exception:[{}]",
            userProfileRequest, ex.message
        )
        throw ex.toUnexpectedException()
    }

    fun createInjury(createInjuryRequest: CreateInjuryRequest): ResponseEntity<CreateAccountResponse> = try {
        logger.info(
            "--$APP_NAME --$CLASS:createInjury --correo electronico[{}] --tipo de lesion[{}] --forma de lesion[{}] --numero de lesiones[{}] --distribucion[{}] --foto de lesion[{}]",
            createInjuryRequest.correoElectronico, createInjuryRequest.tipoDeLesion, createInjuryRequest.formaDeLesion, createInjuryRequest.numeroDeLesiones,
            createInjuryRequest.distribucion, createInjuryRequest.fotoDeLesion
        )
        val accountDb = accountRepository.findByCorreoElectronico(createInjuryRequest.correoElectronico!!)
        val profileDb = userProfileRepository.findByCorreoElectronico(createInjuryRequest.correoElectronico!!)

        if (!accountDb.isPresent) {
            DiagnosticMapper.accountErrorResponse()
        } else if (!profileDb.isPresent) {
            DiagnosticMapper.profileErrorResponse()
        } else {
            val newInjury = injuryRepository.save(DiagnosticMapper.getInjuryEntity(createInjuryRequest))
            DiagnosticMapper.createInjuryResponse(newInjury)
        }
    } catch (ex: Exception) {
        logger.error(
            "--$APP_NAME --$CLASS:createInjury --Request[{}] --Exception:[{}]",
            createInjuryRequest, ex.message
        )
        throw ex.toUnexpectedException()
    }

    fun getAllInjuries(correoElectronico: String?): ResponseEntity<GetInjuriesResponse> = try {
        val profileDb = correoElectronico?.let { userProfileRepository.findByCorreoElectronico(it) }

        if (!profileDb!!.isPresent) {
            DiagnosticMapper.profileErrorResponse()
        }

        val injuries = injuryRepository.findByCorreoElectronico(correoElectronico)

        DiagnosticMapper.getAllInjuriesEntity(profileDb.get(), injuries)
    } catch (ex: Exception) {
        logger.error(
            "--$APP_NAME --$CLASS:getAllInjuries --correo[{}] --Exception:[{}]",
            correoElectronico, ex.message
        )
        throw ex.toUnexpectedException()
    }
}
