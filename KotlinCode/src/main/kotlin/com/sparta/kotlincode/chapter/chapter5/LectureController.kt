package com.sparta.kotlincode.chapter.chapter5

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController

@RestController
class LectureController(private val lectureService: LectureService) {
    @GetMapping("/lecture/{keywords}")
    fun search(@PathVariable("keywords") keywords : String) : List<Lecture>{
        return lectureService.search(LectureSearchParam.toParam(keywords))
    }
}