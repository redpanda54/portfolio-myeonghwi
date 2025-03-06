package com.myeonghwi.portfolio.domain.repository

import com.myeonghwi.portfolio.domain.entity.Introduction
import org.springframework.data.jpa.repository.JpaRepository

interface IntroductionRepository : JpaRepository<Introduction, Long>