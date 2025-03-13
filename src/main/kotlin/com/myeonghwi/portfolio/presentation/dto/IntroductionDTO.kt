package com.myeonghwi.portfolio.presentation.dto

import com.myeonghwi.portfolio.domain.entity.Introduction

data class IntroductionDTO(
    val content: String
) {
    constructor(introduction: Introduction) : this(
        content = introduction.content
    )
}