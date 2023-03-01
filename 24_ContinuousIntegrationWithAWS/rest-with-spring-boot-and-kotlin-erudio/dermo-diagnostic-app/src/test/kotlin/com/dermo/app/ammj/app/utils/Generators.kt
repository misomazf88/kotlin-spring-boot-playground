package com.dermo.app.ammj.app.utils

import com.dermo.app.ammj.common.constant.Constant
import java.util.UUID

object Generators {

    fun getAccountHeaders() = getDefaultHeaders()

    private fun getDefaultHeaders() = HeadersUtils.resolverHeaders(
        hashMapOf(
            Constant.Trace.DERMO_TRACEABILITY_ID to UUID.randomUUID().toString()
        )
    )
}
