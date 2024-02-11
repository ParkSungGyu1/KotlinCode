package com.sparta.kotlincode.chapter.testClasses

import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface MemberRepository: JpaRepository<Member, Long> {
    fun findByName(name: String): Optional<Member>
}