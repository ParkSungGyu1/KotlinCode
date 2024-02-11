package com.sparta.kotlincode.chapter.chapter6

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.Id

@Entity
class Course {
    @Id @GeneratedValue
    val id : Long = 0L

    @Column
    val title : String ?= ""

    @Column
    val status : String ?= ""

}