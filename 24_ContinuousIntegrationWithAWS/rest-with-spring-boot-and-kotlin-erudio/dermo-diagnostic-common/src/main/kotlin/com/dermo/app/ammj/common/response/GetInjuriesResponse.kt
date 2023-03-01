package com.dermo.app.ammj.common.response

import com.fasterxml.jackson.databind.PropertyNamingStrategies
import com.fasterxml.jackson.databind.annotation.JsonNaming
import java.sql.Timestamp
import java.time.LocalDateTime

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy::class)
data class GetInjuriesResponse(

    var correoElectronico: String? = "",

    var nombre: String? = "",

    var edad: String? = "",

    var ciudad: String? = "",

    var tipoDePiel: String? = "",

    var fotoDePiel: String? = "",

    var lesiones: List<InjuryResponse>? = emptyList(),

    var description: String? = "",

    var createdAt: Timestamp? = Timestamp.valueOf(LocalDateTime.now()),

    var updatedAt: Timestamp? = Timestamp.valueOf(LocalDateTime.now())
)
