package com.dermo.app.ammj.common.request

import com.fasterxml.jackson.databind.PropertyNamingStrategies
import com.fasterxml.jackson.databind.annotation.JsonNaming
import javax.validation.constraints.NotNull

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy::class)
data class UserProfileRequest(

    @field:NotNull(message = "El campo correoElectronico es obligatorio")
    var correoElectronico: String? = "",

    @field:NotNull(message = "El campo nombre es obligatorio")
    var nombre: String? = "",

    @field:NotNull(message = "El campo edad es obligatorio")
    var edad: String? = "",

    @field:NotNull(message = "El campo ciudad es obligatorio")
    var ciudad: String? = "",

    @field:NotNull(message = "El campo tipDePiel es obligatorio")
    var tipoDePiel: String? = "",

    @field:NotNull(message = "El campo foto es obligatorio")
    var fotoDePiel: String? = ""
)
