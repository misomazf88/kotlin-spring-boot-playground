package com.dermo.app.ammj.common.util

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import java.util.Date

class DateUtilTest {

    @Test
    fun `should return default pattern`() {
        val result = DateUtil.convertDateToString(Date())
        Assertions.assertEquals("MM/dd/yyyy HH:mm:ss".length, result.length)
    }

    @Test
    fun `should return custom pattern`() {
        val result = DateUtil.convertDateToString(Date(), "MM/dd/yyyy")
        Assertions.assertEquals("MM/dd/yyyy".length, result.length)
    }

    @Test
    fun `should return instant string without nanoseconds`() {
        val result = DateUtil.getCurrentInstantWithoutNanoseconds()
        Assertions.assertEquals("yyyy-MM-ddTHH:mm:ssZ".length, result.length)
    }
}
