package com.myeonghwi.portfolio.domain.entity

import jakarta.persistence.*
import java.time.LocalDate

@Entity
class Achivement(
    title: String,
    description: String,
    achivedDate: LocalDate?,
    host: String,
    isActive: Boolean,
) : BaseEntity() {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "achievement_id")
    var id: Long? = null    // ? 이란 "null 값을 허용한다."

    var title: String = title

    var description: String = description

    var achivedDate: LocalDate? = achivedDate

    var host: String = host

    var isActive: Boolean = isActive
}