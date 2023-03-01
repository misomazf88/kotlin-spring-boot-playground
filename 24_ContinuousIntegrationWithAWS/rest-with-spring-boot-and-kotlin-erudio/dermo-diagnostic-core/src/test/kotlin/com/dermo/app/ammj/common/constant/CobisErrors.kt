package com.dermo.app.ammj.common.constant

object CobisErrors {

    object GeneralSystem {
        val cobisErrorsMap = hashMapOf(
            // final events
            "250311" to ("00C_1333_CLOSING_DATE_IS_BELOW_CURRENT_PROCESS_DATE" to "La fecha de cierre es menor a la fecha de proceso actual."),
            // retriable events (no retriable events were identified after meeting with Cobis)
        )
    }

    object Reverse {
        val errors = hashMapOf(
            "250355" to ("00C_1333_NOT_ALLOW_REVERSE_ADJUSTMENT" to "Ajuste por reverso no permitido: Transacción original generó un valor en suspenso."),
            "250356" to ("00C_1333_ORIGINAL_TRANSACTION_ALREADY_ADJUSTED" to "Transacción original ya fue ajustada en su totalidad."),
            "250357" to ("00C_1333_ADJUSTED_AMOUNT_GREATER_THAN_TX_AMOUNT" to "Monto a ajustar supera el monto de la transacción."),
            "250359" to ("00C_1333_TABLE_UPDATING_FAILURE" to "Error al actualizar tabla de ajuste."),
            "250408" to ("00C_1333_ALTERNATIVE_CODE_REPEATED" to "Código alterno de la transacción repetido."),
            "250364" to ("00C_1333_UUID_MUST_BE_UNIQUE" to "Código UUID debe ser un valor único."),
            "250365" to ("00C_1333_ADJUSTED_REASON_NOT_RELATED" to "Causa de ajuste no relacionada."),
            "250407" to ("00C_1333_DEBIT_REASON_INCORRECT_OR_DOES_NOT_EXIST" to "Débitos relacionados contienen datos inconsistentes: Causa incorrecta o no existe."),
            "250411" to ("00C_1333_DEBIT_INVALID_AMOUNT" to "Débitos relacionados contienen datos inconsistentes: Monto inválido."),
            "250412" to ("00C_1333_DEBIT_ALTERNATE_CODE_INVALID" to "Débitos relacionados contienen datos inconsistentes: Código alterno inválido."),
            "250413" to ("00C_1333_REASON_DOES_NOT_EXIST" to "Acción del causal no existe."),
            "250415" to ("00C_1333_DEBIT_ADJUSTMENT_AMOUNT_INVALID" to "Débitos relacionados contienen datos inconsistentes: Monto de ajuste inválido."),
            "250416" to ("00C_1333_TABLE_INSERTING_FAILURE" to "Error al insertar en tabla temporal de ajuste."),
            "251001" to ("00C_1333_ACCOUNT_DOES_NOT_EXIST" to "No existe cuenta."),
            "250419" to ("00C_1333_RELATED_DEBIT_TX_DO_NOT_MATCH" to "Débitos relacionados no coinciden con la transacción original al servicio de ajuste de costo adicionales."),
            "251127" to ("00C_1333_ACCOUNT_DOES_NOT_EXIST" to "La cuenta no existe."),
            "252110" to ("00C_1333_INACTIVE_ACCOUNT" to "La cuenta no se encuentra activa."),
            "253090" to ("00C_1333_INVALID_ADJUSTED_AMOUNT" to "Monto del Ajuste inválido."),
            "253092" to ("00C_1333_ORIGINAL_DATA_NOT_FOUND" to "Datos originales del requerimiento como la fecha y el ID no encontrados."),
            "351501" to ("00C_1333_SERVICE_DOES_NOT_EXIST" to "No existe servicio disponible."),
            "351511" to ("00C_1333_UNDEFINED_MARKET" to "No existe mercado definido."),
            "351531" to ("00C_1333_SEARCHING_ERROR_FOR_CATEGORIES" to "Error en búsqueda de rubros."),
            "351532" to ("00C_1333_SEARCHING_ERROR_FOR_RANGE_TYPE" to "Error en búsqueda de tipo de rango."),
            "351540" to ("00C_1333_CUSTOM_SERVICE_DOES_NOT_EXIST" to "No existe servicio personalizable."),
            "351550" to ("00C_1333_UNAVAILABLE_VALUE" to "No se ha ingresado valor sobre el cual se actúa."),
            "351551" to ("00C_1333_VALUE_RANGE_DOES_NOT_EXIST" to "No existe rango en el cual se encuentra el valor a actuar."),
            "351552" to ("00C_1333_COST_DOES_NOT_EXIST" to "No existe costo."),
        )
    }

    /** Possible transaction error events
     final events ->
     • 250120: La transacción no puede ser reversada.
     • 201030: Causa no existe.
     • 251057: La cuenta está cerrada.
     • 251058: La cuenta está inactiva.
     • 251101: Error en validación de depósito inicial.
     • 251125: No existe causal.
     • 351527: No existe producto final.
     • 253000: Error en inserción de transacción monetaria.
     • 253004: Error en inserción de transacción de servicio.
     • 255001: Error al actualizar cuenta.
     • 253002: Error en inserción de línea pendiente.
     • 255005: Error al actualizar Nro. Control.
     • 251012: Error en tabla de alícuotas.
     • 201048: Error en código de transacción.
     • 201196: Parámetro no encontrado.
     • 101190: No existe Default para País.
     • 252093: Error en reintegro de GMF.
     • 101042: No existe prospecto o cédula incorrecta.
     • 251068: Valor de la transacción no puede ser cero.
     • 253082: Monto inválido.
     • 251067: Transacción origen del reverso no coincide o transacción ya reversada.
     • 251029: Moneda no corresponde a cuenta.
     • 250119: El monto total de transacciones de la cuenta excede al monto maximo permitido.
     • 252077: El crédito a la cuenta excede el saldo máximo permitido.
     • 251033: Fondos insuficientes.
     retriable events ->
     no events were identified
     **/
    object General {
        val cobisErrorsMap = hashMapOf(
            // final events
            // Credit/Debit
            "250120" to ("00C_1333_CANT_REVERSE_TRANSACTION" to "La transacción no puede ser reversada."),
            "201030" to ("00C_1333_CAUSE_NOT_EXISTS" to "Causa no existe."),
            "251057" to ("00C_1333_ACCOUNT_CLOSED" to "La cuenta está cerrada."),
            "251058" to ("00C_1333_ACCOUNT_INACTIVE" to "La cuenta está inactiva."),
            "251101" to ("00C_1333_INITIAL_DEPOSIT_VALIDATION_ERROR" to "Error en validación de depósito inicial."),
            "251125" to ("00C_1333_CAUSAL_NOT_EXISTS" to "No existe causal."),
            "351527" to ("00C_1333_FINAL_PRODUCT_NOT_EXISTS" to "No existe producto final."),
            "253000" to ("00C_1333_MONETARY_TRANSACTION_INSERT_ERROR" to "Error en inserción de transacción monetaria."),
            "253004" to ("00C_1333_SERVICE_TRANSACTION_INSERT_ERROR" to "Error en inserción de transacción de servicio."),
            "255001" to ("00C_1333_ACCOUNT_UPDATE_ERROR" to "Error al actualizar cuenta."),
            "253002" to ("00C_1333_PENDING_LINE_INSERT_ERROR" to "Error en inserción de línea pendiente."),
            "255005" to ("00C_1333_CONTROL_NUMBER_UPDATE_ERROR" to "Error al actualizar Nro. Control."),
            "251012" to ("00C_1333_ALIQUOT_TABLE_ERROR" to "Error en tabla de alícuotas."),
            "201048" to ("00C_1333_TRANSACTION_CODE_ERROR" to "Error en código de transacción."),
            "201196" to ("00C_1333_MISSING_PARAMETER" to "Parámetro no encontrado."),
            "101190" to ("00C_1333_DEFAULT_COUNTRY_NOT_EXISTS" to "No existe Default para País."),
            "252093" to ("00C_1333_GMF_REFUND_ERROR" to "Error en reintegro de GMF."),
            "251033" to ("00C_1333_NOT_ENOUGH_FOUNDS" to "Fondos insuficientes."),
            "252077" to ("00C_1333_MAXIMUM_CREDIT_ALLOWED_OVERCOME" to "El crédito a la cuenta excede el saldo máximo permitido."),
            "250119" to ("00C_1333_TRANSACTIONS_TOTAL_ACCOUNT_AMOUNT_OVERCOME" to "El monto total de transacciones de la cuenta excede al monto maximo permitido."),
            "251029" to ("00C_1333_ACCOUNT_CURRENCY_DOESNT_MATCH" to "Moneda no corresponde a cuenta."),
            "251067" to ("00C_1333_REVERSE_TRANSACTION_DOESNT_MATCH_OR_ALREADY_REVERSED" to "Transacción origen del reverso no coincide o transacción ya reversada."),
            "253082" to ("00C_1333_INVALID_AMOUNT" to "Monto inválido."),
            "251068" to ("00C_1333_TRANSACTION_AMOUNT_CANT_BE_ZERO" to "Valor de la transacción no puede ser cero."),
            "101042" to ("00C_1333_PROSPECTUS_NOT_EXISTS_OR_INCORRECT_ID" to "No existe prospecto o cédula incorrecta."),
            // retriable events (no retriable events were identified after meeting with Cobis)
            "000000" to ("00C_1333_FAKE_RETRIABLE_EVENT" to "Fake retriable event."),
            "000001" to ("00C_1333_FAKE_RETRIABLE_EVENT_1" to "Fake retriable event 1.")
        ) + GeneralSystem.cobisErrorsMap + Reverse.errors

        val cobisRetryErrorCodes = listOf("000000", "000001")
    }
}
