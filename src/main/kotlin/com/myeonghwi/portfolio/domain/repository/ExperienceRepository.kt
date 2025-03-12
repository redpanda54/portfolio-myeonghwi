package com.myeonghwi.portfolio.domain.repository

import com.myeonghwi.portfolio.domain.entity.Experience
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface ExperienceRepository : JpaRepository<Experience, Long> {

    fun findAllByIsActive(isActive: Boolean): List<Experience>
    override fun findById(id: Long): Optional<Experience>

}