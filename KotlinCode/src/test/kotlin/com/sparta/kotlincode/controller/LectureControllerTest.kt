package com.sparta.kotlincode.controller

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.sparta.kotlincode.chapter.chapter5.Lecture
import com.sparta.kotlincode.chapter.chapter5.LectureService
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.extensions.spring.SpringExtension
import io.kotest.matchers.shouldBe
import io.mockk.clearAllMocks
import io.mockk.every
import io.mockk.junit5.MockKExtension
import io.mockk.mockk
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get

@SpringBootTest
@AutoConfigureMockMvc // mockMvc 주입용도
@ExtendWith(MockKExtension::class) // mockk쓸 때 표기 해줘야 함
class LectureControllerTest @Autowired constructor(
    private val mockMvc: MockMvc
) : DescribeSpec({
    extension(SpringExtension)

    // describe 끝날시 마다 설정한 mocking 비워줌
    afterContainer {
        clearAllMocks()
    }

    // CourseService Mocking
    val courseService = mockk<LectureService>()

    describe("GET /lecture/{keywords}는") {
        context("존재하는 ID를 요청을 보낼 때") {
            it("200 status code를 응답해야한다.") {
                val keywords = "title"

                // Mock의 동작 정의
                every { courseService.search(any()) } returns mutableListOf<Lecture>(Lecture(1,"title1"))

                // 요청 수행
                val result = mockMvc.perform(
                    get("/lecture/$keywords")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                ).andReturn()

                // status code 확인
                result.response.status shouldBe 200
            }
        }
    }
})