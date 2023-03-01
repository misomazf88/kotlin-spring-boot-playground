package com.dermo.app.ammj.app.controller

import com.dermo.app.ammj.app.utils.Generators
import com.dermo.app.ammj.common.request.CreateAccountRequest
import com.dermo.app.ammj.common.request.CreateInjuryRequest
import com.dermo.app.ammj.common.request.UserProfileRequest
import com.dermo.app.ammj.common.response.CreateAccountResponse
import com.dermo.app.ammj.common.route.Route
import com.dermo.app.ammj.core.service.DiagnosticService
import com.google.gson.Gson
import org.junit.jupiter.api.Test
import org.mockito.Mockito
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.OverrideAutoConfiguration
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultHandlers
import org.springframework.test.web.servlet.result.MockMvcResultMatchers
import java.sql.Timestamp
import java.time.LocalDateTime

@WebMvcTest(controllers = [DiagnosticController::class])
@OverrideAutoConfiguration(enabled = true)
class DiagnosticControllerTest {

    @Autowired
    private lateinit var mvc: MockMvc

    @MockBean
    private lateinit var diagnosticService: DiagnosticService

    @Test
    fun `Crear cuenta Exitosamente`() {
        val accountRequest = CreateAccountRequest(
            correoElectronico = "mazf@gmail.com",
            contrasena = "123"
        )

        val accountResponse = ResponseEntity(
            CreateAccountResponse(
                description = "Cuenta creada exitosamente"
            ),
            HttpStatus.OK
        )

        Mockito.doReturn(accountResponse).`when`(diagnosticService).createAccount(accountRequest)

        val gson = Gson()

        val jsonRequest = gson.toJson(accountRequest)

        mvc.perform(
            MockMvcRequestBuilders.post(Route.Diagnostic.ACCOUNT)
                .headers(Generators.getAccountHeaders())
                .content(jsonRequest)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
        )
            .andExpect(MockMvcResultMatchers.status().is2xxSuccessful)
            .andDo(MockMvcResultHandlers.print())
            .andReturn()
    }

    @Test
    fun `Crear cuenta Bad Request correo`() {
        val accountRequest = CreateAccountRequest(
            correoElectronico = "",
            contrasena = "123"
        )

        val gson = Gson()

        val jsonRequest = gson.toJson(accountRequest)

        mvc.perform(
            MockMvcRequestBuilders.post(Route.Diagnostic.ACCOUNT)
                .content(jsonRequest)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
        )
            .andExpect(MockMvcResultMatchers.status().isBadRequest)
            .andDo(MockMvcResultHandlers.print())
            .andReturn()
    }

    @Test
    fun `Crear cuenta Bad Request contrasena`() {
        val accountRequest = CreateAccountRequest(
            correoElectronico = "mazf@gmail.com",
            contrasena = ""
        )

        val gson = Gson()

        val jsonRequest = gson.toJson(accountRequest)

        mvc.perform(
            MockMvcRequestBuilders.post(Route.Diagnostic.ACCOUNT)
                .content(jsonRequest)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
        )
            .andExpect(MockMvcResultMatchers.status().isBadRequest)
            .andDo(MockMvcResultHandlers.print())
            .andReturn()
    }

    // Login ///

    @Test
    fun `Iniciar sesion Exitosamente`() {
        var accountRequest = CreateAccountRequest(
            correoElectronico = "mazf@gmail.com",
            contrasena = "123"
        )

        val accountResponse = ResponseEntity(
            CreateAccountResponse(
                description = "Inicio de sesion exitoso"
            ),
            HttpStatus.OK
        )

        Mockito.doReturn(accountResponse).`when`(diagnosticService).login(accountRequest)

        var gson = Gson()

        var jsonRequest = gson.toJson(accountRequest)

        mvc.perform(
            MockMvcRequestBuilders.get(Route.Diagnostic.ACCOUNT_LOGIN)
                .headers(Generators.getAccountHeaders())
                .content(jsonRequest)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
        )
            .andExpect(MockMvcResultMatchers.status().is2xxSuccessful)
            .andDo(MockMvcResultHandlers.print())
            .andReturn()
    }

    @Test
    fun `Iniciar sesion bad request`() {
        var accountRequest = CreateAccountRequest(
            correoElectronico = "mazf@gmail.com",
            contrasena = ""
        )

        val accountResponse = ResponseEntity(
            CreateAccountResponse(
                description = "Inicio de sesion exitoso"
            ),
            HttpStatus.OK
        )

        Mockito.doReturn(accountResponse).`when`(diagnosticService).login(accountRequest)

        var gson = Gson()

        var jsonRequest = gson.toJson(accountRequest)

        mvc.perform(
            MockMvcRequestBuilders.get(Route.Diagnostic.ACCOUNT_LOGIN)
                .content(jsonRequest)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
        )
            .andExpect(MockMvcResultMatchers.status().is4xxClientError)
            .andDo(MockMvcResultHandlers.print())
            .andReturn()
    }

    // Crear perfil dermatologico///

    @Test
    fun `Crear perfilUsuario Exitosamente`() {
        val userProfileRequest = UserProfileRequest(
            correoElectronico = "mazf123@gmail.com",
            nombre = "Mario Zambrano",
            edad = "25",
            ciudad = "Bogota",
            tipoDePiel = "Grasa",
            fotoDePiel = "/foto/piel"
        )

        val accountResponse = ResponseEntity(
            CreateAccountResponse(
                description = "Diagnostico creado exitosamente",
                createdAt = Timestamp.valueOf(LocalDateTime.now())
            ),
            HttpStatus.OK
        )

        Mockito.doReturn(accountResponse).`when`(diagnosticService).createUserProfile(userProfileRequest)

        val gson = Gson()

        val jsonRequest = gson.toJson(userProfileRequest)

        mvc.perform(
            MockMvcRequestBuilders.post(Route.Diagnostic.USER_PROFILE_CREATE)
                .headers(Generators.getAccountHeaders())
                .content(jsonRequest)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
        )
            .andExpect(MockMvcResultMatchers.status().is2xxSuccessful)
            .andDo(MockMvcResultHandlers.print())
            .andReturn()
    }

    @Test
    fun `Crear perfilUsuario bad request`() {
        val userProfileRequest = UserProfileRequest(
            nombre = "Mario Zambrano",
            edad = "25",
            ciudad = "",
            tipoDePiel = "",
            fotoDePiel = "/foto/piel"
        )

        val accountResponse = ResponseEntity(
            CreateAccountResponse(
                description = "Diagnostico creado exitosamente",
                createdAt = Timestamp.valueOf(LocalDateTime.now())
            ),
            HttpStatus.OK
        )

        Mockito.doReturn(accountResponse).`when`(diagnosticService).createUserProfile(userProfileRequest)

        val gson = Gson()

        val jsonRequest = gson.toJson(userProfileRequest)

        mvc.perform(
            MockMvcRequestBuilders.post(Route.Diagnostic.USER_PROFILE_CREATE)
                .content(jsonRequest)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
        )
            .andExpect(MockMvcResultMatchers.status().is4xxClientError)
            .andDo(MockMvcResultHandlers.print())
            .andReturn()
    }

    // Informar lesion///

    @Test
    fun `Informar lesion Exitosamente`() {
        val createInjuryRequest = CreateInjuryRequest(
            correoElectronico = "mazf123@gmail.com",
            tipoDeLesion = "Placa",
            formaDeLesion = "Ovalada",
            numeroDeLesiones = "Multiple",
            distribucion = "Esparcida",
            fotoDeLesion = "base64/foto"
        )

        val accountResponse = ResponseEntity(
            CreateAccountResponse(
                description = "Lesion creada exitosamente",
                createdAt = Timestamp.valueOf(LocalDateTime.now()),
                updatedAt = Timestamp.valueOf(LocalDateTime.now())
            ),
            HttpStatus.OK
        )

        Mockito.doReturn(accountResponse).`when`(diagnosticService).createInjury(createInjuryRequest)

        val gson = Gson()

        val jsonRequest = gson.toJson(createInjuryRequest)

        mvc.perform(
            MockMvcRequestBuilders.post(Route.Diagnostic.INJURY_CREATE)
                .headers(Generators.getAccountHeaders())
                .content(jsonRequest)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
        )
            .andExpect(MockMvcResultMatchers.status().is2xxSuccessful)
            .andDo(MockMvcResultHandlers.print())
            .andReturn()
    }

    @Test
    fun `Informar lesion bad request`() {
        val createInjuryRequest = CreateInjuryRequest(
            tipoDeLesion = "Placa",
            formaDeLesion = "Ovalada",
            numeroDeLesiones = "Multiple",
            distribucion = "Esparcida",
            fotoDeLesion = "base64/foto"
        )

        val accountResponse = ResponseEntity(
            CreateAccountResponse(
                description = "Diagnostico creado exitosamente",
                createdAt = Timestamp.valueOf(LocalDateTime.now())
            ),
            HttpStatus.OK
        )

        Mockito.doReturn(accountResponse).`when`(diagnosticService).createInjury(createInjuryRequest)

        val gson = Gson()

        val jsonRequest = gson.toJson(createInjuryRequest)

        mvc.perform(
            MockMvcRequestBuilders.post(Route.Diagnostic.INJURY_CREATE)
                .content(jsonRequest)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
        )
            .andExpect(MockMvcResultMatchers.status().is4xxClientError)
            .andDo(MockMvcResultHandlers.print())
            .andReturn()
    }
}
