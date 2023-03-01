package com.dermo.app.ammj.core.util

import com.dermo.app.ammj.common.request.CreateAccountRequest
import com.dermo.app.ammj.domain.entity.AccountEntity

object Generators {

    fun getDiagnosticRequest() =
        CreateAccountRequest(
            correoElectronico = "mazf@gmail.com",
            contrasena = "123"
        )

    fun getDiagnosticEntity() =
        AccountEntity(
            correoElectronico = "mazf@gmail.com",
            contrasena = "123"
        )

    fun getAccountEntities(): List<AccountEntity> {
        var listOfAccountEntity = listOf<AccountEntity>()

        var diagnosticEntity = AccountEntity(
            correoElectronico = "mazf@gmail.com",
            contrasena = "123"
        )
        listOfAccountEntity += diagnosticEntity

        return listOfAccountEntity
    }
}
