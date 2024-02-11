package com.sparta.kotlincode.chapter.chapter2

import org.springframework.data.jpa.repository.JpaRepository

interface PostRepository : JpaRepository<Post, Long> {
}