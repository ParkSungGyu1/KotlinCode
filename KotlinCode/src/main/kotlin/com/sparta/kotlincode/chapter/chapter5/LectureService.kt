package com.sparta.kotlincode.chapter.chapter5

import org.springframework.stereotype.Service

@Service
class LectureService (
    private val lectureRepositoryQuery: LectureRepositoryQuery
){
    fun search(param: LectureSearchParam) : List<Lecture>{
        return lectureRepositoryQuery.search(param)
    }
}