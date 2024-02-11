package com.sparta.kotlincode.chapter.testClasses

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.Id

@Entity
class Member (
    var name : String,
    var age : Int,
){
    @Id @GeneratedValue
    @Column(name = "member_id")
    val id : Long? = null
}