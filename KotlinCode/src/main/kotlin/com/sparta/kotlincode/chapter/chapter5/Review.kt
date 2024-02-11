package com.sparta.kotlincode.chapter.chapter5

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.Id
import jakarta.persistence.ManyToOne

@Entity
class Review {
    @Id
    @GeneratedValue
    val id : Long?=0L

    @ManyToOne
    val lecture : Lecture?=null
}