package com.dermo.app.ammj.core.enums

enum class IgnoredListCodeErrors(val code: String) {

    COBIS_MANDATORY_ACCOUNT("250030"),
    COBIS_INSUFICCIENT_SAVINGS("251033"),
    COBIS_PRODUCT_COBIS_NOT_EXIST("351518"),
    COBIS_COTIZATION_DOES_NOT_EXIST("149051"),
    COBIS_EXPIRATION_DATE_OVERDUE("250027"),
    COBIS_PRODUCT_NOT_EXIST("101032"),
    COBIS_PARAMETER_NOT_FOUND("201196"),
    COBIS_ACCOUNT_NOT_EXIST("251001");

    companion object {
        fun ignoredListCodeErrors() =
            values().map { it.code }
    }
}
