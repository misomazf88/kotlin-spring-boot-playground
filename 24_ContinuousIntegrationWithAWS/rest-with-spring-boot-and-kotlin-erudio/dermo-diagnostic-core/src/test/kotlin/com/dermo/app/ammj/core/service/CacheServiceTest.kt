package com.dermo.app.ammj.core.service

import com.dermo.app.ammj.app.Application
import com.dermo.app.ammj.domain.repository.AccountRepository
import org.mockito.Mock
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.cache.CacheManager

@SpringBootTest(classes = [Application::class])
class CacheServiceTest {

    @Mock
    private lateinit var cacheManager: CacheManager

    @Mock
    private lateinit var diagnosticRepository: AccountRepository

    // @Test
    /*fun findNatureByOriginTransactionAndTypeTest() {
        val nature = Optional.of(Generators.getDiagnosticEntity())
        Mockito.`when`(diagnosticRepository.findByOriginTransactionAndType(nature.get().diagnostic_description!!, nature.get().type!!))
            .thenReturn(nature)

        val result = cacheService.findNatureByOriginTransactionAndType(nature.get().originTransaction!!, nature.get().type!!)
        Assertions.assertEquals(result.nature, nature.get().nature)
    }*/
}
