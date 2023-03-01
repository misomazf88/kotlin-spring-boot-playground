package com.dermo.app.ammj.common.response

import com.fasterxml.jackson.databind.PropertyNamingStrategies
import com.fasterxml.jackson.databind.annotation.JsonNaming
import java.sql.Timestamp
import java.time.LocalDateTime
import javax.validation.constraints.NotNull

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy::class)
data class CreateAccountResponse(

    var description: String?,

    @field:NotNull
    var createdAt: Timestamp? = Timestamp.valueOf(LocalDateTime.now()),

    @field:NotNull
    var updatedAt: Timestamp? = Timestamp.valueOf(LocalDateTime.now())
)
