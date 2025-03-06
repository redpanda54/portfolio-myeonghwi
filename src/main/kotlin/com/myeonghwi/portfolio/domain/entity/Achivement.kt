package com.myeonghwi.portfolio.domain.entity

import jakarta.persistence.*

@Entity
class Achivement : BaseEntity() {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "achievement_id")
    var id: Long? = null    // ? 이란 "null 값을 허용한다."


}