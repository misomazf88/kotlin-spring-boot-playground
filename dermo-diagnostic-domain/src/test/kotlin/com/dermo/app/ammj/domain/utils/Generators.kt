package com.dermo.app.ammj.domain.utils

import com.dermo.app.ammj.domain.entity.AccountEntity
import java.sql.Timestamp
import java.time.LocalDateTime
import java.util.UUID

object Generators {

    fun getDiagnosticEntity(): AccountEntity {
        var diagnosticEntity = AccountEntity(
            id = UUID.randomUUID(),
            correoElectronico = "mazf@gmail.com",
            contrasena = "1234",
            createdAt = Timestamp.valueOf(LocalDateTime.now())
        )
        return diagnosticEntity
    }
}
