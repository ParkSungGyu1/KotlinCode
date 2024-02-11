package com.sparta.kotlincode.chapter.chapter4

@Retention(AnnotationRetention.RUNTIME)
@Target(AnnotationTarget.FUNCTION)
annotation class RoleCheck(val value: String = "ADMIN")

