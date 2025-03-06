package com.myeonghwi.portfolio.domain.repository

import com.myeonghwi.portfolio.domain.entity.Link
import org.springframework.data.jpa.repository.JpaRepository

interface LinkRepository : JpaRepository<Link, Long>