package com.myeonghwi.portfolio.domain.repository

import com.myeonghwi.portfolio.domain.entity.Skill
import org.springframework.data.jpa.repository.JpaRepository

interface SkillRepository : JpaRepository<Skill, Long>