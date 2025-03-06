package com.myeonghwi.portfolio.domain.repository

import com.myeonghwi.portfolio.domain.entity.Experience
import org.springframework.data.jpa.repository.JpaRepository

interface ExperienceRepository : JpaRepository<Experience, Long>