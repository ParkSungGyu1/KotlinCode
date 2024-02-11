package com.sparta.kotlincode.chapter.chapter5

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.Id

@Entity
class Lecture (
    @Id @GeneratedValue
    val id : Long,

    @Column
    val title : String
)