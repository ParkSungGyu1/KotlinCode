package com.sparta.kotlincode.chapter.chapter4

import org.aspectj.lang.JoinPoint
import org.aspectj.lang.annotation.Aspect
import org.aspectj.lang.annotation.Before
import org.aspectj.lang.reflect.MethodSignature
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component


@Aspect
@Component
class RoleCheckAspect {

    private val log = LoggerFactory.getLogger(this.javaClass)

    @Before("@annotation(com.sparta.kotlincode.chapter.chapter4.RoleCheck)")
    fun roleCheckByExecution(joinPoint: JoinPoint) {
        log.info("RoleCheckAspect.roleCheckByExecution")

        val signature = joinPoint.signature as MethodSignature
        val method = signature.method

        val annotation = method.getAnnotation(RoleCheck::class.java)
        val methodRole = annotation.value
        log.info("method role = {}", methodRole)

        val account = joinPoint.args[0] as Account
        val accountRole = account.role
        log.info("account role = {}", accountRole)

        if (methodRole == accountRole) {
            log.info("request success : method = {} : username = {} : email = {} : role = {}",
                method.name, account.username, account.email, accountRole)
            return
        }

        log.info("request failed : method = {} : username = {} : email = {} : role = {}",
            method.name, account.username, account.email, accountRole)
        throw RuntimeException("권한 부족")
    }
}