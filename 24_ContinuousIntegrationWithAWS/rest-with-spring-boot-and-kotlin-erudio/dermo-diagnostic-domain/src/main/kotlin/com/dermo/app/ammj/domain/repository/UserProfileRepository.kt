package com.dermo.app.ammj.domain.repository

import com.dermo.app.ammj.domain.entity.UserProfileEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.Optional

@Repository
interface UserProfileRepository : JpaRepository<UserProfileEntity, Long> {

    fun findByCorreoElectronico(mail: String): Optional<UserProfileEntity>
}
