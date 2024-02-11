package com.sparta.kotlincode.chapter.chapter2

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.Id

@Entity
class User {
    @Id @GeneratedValue
    val id : Long ?= 0L
}
