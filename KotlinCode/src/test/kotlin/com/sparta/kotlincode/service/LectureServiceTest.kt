package com.sparta.kotlincode.service

import com.sparta.kotlincode.chapter.chapter5.LectureRepositoryQuery
import com.sparta.kotlincode.chapter.chapter5.LectureSearchParam
import com.sparta.kotlincode.chapter.chapter5.LectureService
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.extensions.spring.SpringExtension
import io.mockk.clearAllMocks
import io.mockk.every
import io.mockk.junit5.MockKExtension
import io.mockk.mockk
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.boot.test.context.SpringBootTest


@SpringBootTest
@ExtendWith(MockKExtension::class)
class LectureServiceTest  : BehaviorSpec({
    extension(SpringExtension)

    afterContainer {
        clearAllMocks()
    }

    // 종속 repository mocking
    val lectureRepository = mockk<LectureRepositoryQuery>()

    // courseService 생성
    val lectureService = LectureService(lectureRepository)

    Given("Lecture 목록이 존재하지 않을때") {

        When("특정 Lecture 요청하면") {

            Then("IllegalArgumentException 발생해야 한다.") {
                val keywords = "없는제목"
                every { lectureRepository.search(any()) } returns emptyList()

                shouldThrow<IllegalArgumentException> {
                    lectureService.search(LectureSearchParam.toParam(keywords))
                }
            }

        }
    }
})