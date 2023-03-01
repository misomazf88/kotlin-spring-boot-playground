package com.dermo.app.ammj.common.request

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.databind.PropertyNamingStrategies
import com.fasterxml.jackson.databind.annotation.JsonNaming
import javax.validation.constraints.NotNull

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy::class)
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
data class CreateAccountRequest(
    @field:NotNull(message = "El campo correoElectronico es obligatorio")
    var correoElectronico: String? = "",

    @field:NotNull(message = "El campo contrasena es obligatorio")
    var contrasena: String? = ""
)
