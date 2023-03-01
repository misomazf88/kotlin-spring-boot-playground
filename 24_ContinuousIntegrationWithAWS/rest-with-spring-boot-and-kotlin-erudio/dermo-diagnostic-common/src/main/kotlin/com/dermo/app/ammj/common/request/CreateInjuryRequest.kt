package com.dermo.app.ammj.common.request

import com.fasterxml.jackson.databind.PropertyNamingStrategies
import com.fasterxml.jackson.databind.annotation.JsonNaming
import javax.validation.constraints.NotNull

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy::class)
data class CreateInjuryRequest(

    @field:NotNull(message = "El campo correoElectronico es obligatorio")
    var correoElectronico: String? = "",

    @field:NotNull(message = "El campo tipoDeLesion es obligatorio")
    var tipoDeLesion: String? = "",

    @field:NotNull(message = "El campo formaDeLesion es obligatorio")
    var formaDeLesion: String? = "",

    @field:NotNull(message = "El campo numeroDeLesiones es obligatorio")
    var numeroDeLesiones: String? = "",

    @field:NotNull(message = "El campo distribucion es obligatorio")
    var distribucion: String? = "",

    @field:NotNull(message = "El campo fotoDeLesion es obligatorio")
    var fotoDeLesion: String? = ""
)
