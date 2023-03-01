package com.dermo.app.ammj.domain.repository

import com.dermo.app.ammj.domain.entity.AccountEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.Optional

@Repository
interface AccountRepository : JpaRepository<AccountEntity, Long> {

    fun findByCorreoElectronico(mail: String): Optional<AccountEntity>
}
