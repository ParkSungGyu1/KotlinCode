package com.sparta.kotlincode.chapter.chapter1

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class GlobalControllerAdvice {
    @ExceptionHandler(IllegalArgumentException::class)
    fun handleIllegalArgumentException(ex: IllegalArgumentException): ResponseEntity<ApiResponseDto> {
        val restApiException = ApiResponseDto(ex.message, HttpStatus.BAD_REQUEST.value())
        return ResponseEntity(
            restApiException,
            HttpStatus.BAD_REQUEST
        )
    }
}
