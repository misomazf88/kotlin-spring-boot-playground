package com.dermo.app.ammj.common.converter

import com.fasterxml.jackson.databind.util.StdConverter
import java.math.BigDecimal

class StringToBigDecimalConverter : StdConverter<String, BigDecimal>() {
    override fun convert(value: String): BigDecimal {
        val integerPart = value.substringBefore(".")
        val fractionPart = value.substringAfter(".", "00")
        return BigDecimal("$integerPart.$fractionPart")
    }
}
