package com.dermo.app.ammj.common.constant

object Constant {

    object Param {
        const val TIMEOUT = 5000L
        const val INIT_DATE = "init-date"
        const val END_DATE = "end-date"
        const val ORIGIN_REQUEST_ID = "origin-request-id"
    }

    object CACHE {
        const val NATURE_DURATION = 48L
    }

    object DATABASE {
        const val MAXIMUM_POOL_SIZE = 15
    }

    object TIMEZONE {
        const val ZONE = "America/Bogota"
        const val LOCALE = "es"
    }

    object Trace {
        const val DERMO_TRACEABILITY_ID = "dermo-traceability-id"
    }
}
