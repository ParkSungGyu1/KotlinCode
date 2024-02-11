package com.sparta.kotlincode

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.EnableAspectJAutoProxy

@EnableAspectJAutoProxy
@SpringBootApplication
class KotlinCodeApplication

fun main(args: Array<String>) {
    runApplication<KotlinCodeApplication>(*args)
}
