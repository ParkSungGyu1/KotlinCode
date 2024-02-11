package com.sparta.kotlincode.chapter.chapter3

import com.sparta.kotlincode.chapter.chapter1.ApiResponseDto
import com.sparta.kotlincode.chapter.chapter1.UserPrincipal
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.PathVariable

class TestController(
    private val testService: TestService
) {
    @DeleteMapping("/comments/{id}/like")
    fun deleteLikeComment( @PathVariable id: Long): ResponseEntity<ApiResponseDto> {
        try {
            testService.deleteLikeComment(id)
        } catch (e: NotFoundException) {
            ResponseEntity.badRequest().body(ApiResponseDto(e.message, HttpStatus.BAD_REQUEST.value()))
        }
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(ApiResponseDto("테스트 성공", HttpStatus.ACCEPTED.value()))
    }
}