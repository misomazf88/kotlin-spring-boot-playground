package com.dermo.app.ammj.app.handler

import com.dermo.app.ammj.common.exception.DiagnosticException
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Test
import org.mockito.Mock
import org.springframework.mock.http.client.MockClientHttpRequest

class ExceptionHandlerTest {

    @Mock
    private lateinit var mockClientHttpRequest: MockClientHttpRequest

    @Test
    fun `should handle DiagnosticException`() {
        val response = ExceptionHandler().handleDiagnosticException(
            DiagnosticException(
                500,
                500,
                "Error"
            )
        )
        assertNotNull(response)
    }
}
