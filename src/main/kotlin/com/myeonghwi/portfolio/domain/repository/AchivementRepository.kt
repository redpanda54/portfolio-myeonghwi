package com.myeonghwi.portfolio.domain.repository

import com.myeonghwi.portfolio.domain.entity.Achivement
import org.springframework.data.jpa.repository.JpaRepository

interface AchivementRepository : JpaRepository<Achivement, Long> {

    // select * from achievement where is_active = :isActive
    fun findAllByIsActive(isActive: Boolean): List<Achivement>

}