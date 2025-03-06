package com.myeonghwi.portfolio.domain.repository

import com.myeonghwi.portfolio.domain.entity.Project
import org.springframework.data.jpa.repository.JpaRepository

interface ProjectRepository : JpaRepository<Project, Long>