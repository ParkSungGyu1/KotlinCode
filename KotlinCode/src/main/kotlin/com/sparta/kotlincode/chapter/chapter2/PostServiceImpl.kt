package com.sparta.kotlincode.chapter.chapter2

import jakarta.transaction.Transactional
import org.springframework.stereotype.Service


@Service
class PostServiceImpl(
    private val postRepository: PostRepository
) : PostService {
    @Transactional
    override fun createPost(requestDto: PostRequestDto, user: User): PostResponseDto {
        val post = Post(requestDto)
        post.user = user

        postRepository.save(post)

        return PostResponseDto(post)
    }
}