package com.dermo.app.ammj.domain.repository

import com.dermo.app.ammj.domain.entity.InjuryEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.Optional

@Repository
interface InjuryRepository : JpaRepository<InjuryEntity, Long> {

    fun findByCorreoElectronico(mail: String): List<Optional<InjuryEntity>>
}
