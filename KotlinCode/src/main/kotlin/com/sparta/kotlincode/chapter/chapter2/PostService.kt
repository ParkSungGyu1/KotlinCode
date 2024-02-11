package com.sparta.kotlincode.chapter.chapter2


interface PostService {
    fun createPost(requestDto: PostRequestDto, user: User): PostResponseDto
}