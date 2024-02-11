package com.sparta.kotlincode.chapter.chapter1

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController

@RestController
class CommentController(private val commentService: CommentService) {

    @DeleteMapping("/comments/{id}/like")
    fun deleteLikeComment( @PathVariable id: Long): ResponseEntity<ApiResponseDto> {
        try {
            commentService.deleteLikeComment(id)
        } catch (e: IllegalArgumentException) {
            ResponseEntity.badRequest().body(ApiResponseDto(e.message, HttpStatus.BAD_REQUEST.value()))
        }
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(ApiResponseDto("댓글 좋아요 취소 성공", HttpStatus.ACCEPTED.value()))
    }
}