package com.dermo.app.ammj.common.util

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import java.util.Date

class SensitiveDataUtilTest {

    @Test
    fun `should hide number`() {
        val result = SensitiveDataUtil.hideNumber(123456789L)
        Assertions.assertEquals("123***789", result)
    }

    @Test
    fun `should hide shorter number`() {
        val result = SensitiveDataUtil.hideNumber(1234567L)
        Assertions.assertEquals("123*567", result)
    }

    @Test
    fun `should hide longer number`() {
        val result = SensitiveDataUtil.hideNumber(12345678910111213L)
        Assertions.assertEquals("123***********213", result)
    }

    @Test
    fun `should hide string date`() {
        val result = SensitiveDataUtil.hideDate("22/03/1990")
        Assertions.assertEquals("**/**/****", result)
    }

    @Test
    fun `should hide date`() {
        val result = SensitiveDataUtil.hideDate(Date())
        Assertions.assertEquals("**/**/****", result)
    }

    @Test
    fun `should hide email`() {
        val result = SensitiveDataUtil.hideEmail("example@email.com")
        Assertions.assertEquals("exam***@email.com", result)
    }

    @Test
    fun `should hide longer email`() {
        val result = SensitiveDataUtil.hideEmail("examplelonger@email.com")
        Assertions.assertEquals("exam*********@email.com", result)
    }

    @Test
    fun `should hide text`() {
        val result = SensitiveDataUtil.hideText("textExample")
        Assertions.assertEquals("te*******le", result)
    }
}
