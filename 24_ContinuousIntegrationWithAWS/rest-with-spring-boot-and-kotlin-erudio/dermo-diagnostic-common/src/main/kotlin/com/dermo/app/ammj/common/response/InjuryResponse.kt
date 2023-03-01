package com.dermo.app.ammj.common.response

import com.fasterxml.jackson.databind.PropertyNamingStrategies
import com.fasterxml.jackson.databind.annotation.JsonNaming
import java.sql.Timestamp
import java.time.LocalDateTime

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy::class)
data class InjuryResponse(

    var tipoDeLesion: String? = "",

    var formaDeLesion: String? = "",

    var numeroDeLesiones: String? = "",

    var distribucion: String? = "",

    var fotoDeLesion: String? = "",

    var createdAt: Timestamp? = Timestamp.valueOf(LocalDateTime.now()),

    var updatedAt: Timestamp? = Timestamp.valueOf(LocalDateTime.now())
)
