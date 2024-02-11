package com.sparta.kotlincode.chapter.chapter2

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.Id
import jakarta.persistence.ManyToOne
import java.time.LocalDateTime


@Entity
class Post(requestDto: PostRequestDto) {
    @Id
    @GeneratedValue
    val id : Long = 0L

    @ManyToOne
    var user: User?=null

    val title : String = ""

    val category : String = ""

    val tag : String = ""

    val status : String = ""

    val createdDate : LocalDateTime?= null

}
