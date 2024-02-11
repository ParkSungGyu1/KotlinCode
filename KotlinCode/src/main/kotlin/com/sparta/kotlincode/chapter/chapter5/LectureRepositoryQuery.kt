package com.sparta.kotlincode.chapter.chapter5

import org.springframework.stereotype.Repository

@Repository
class LectureRepositoryQuery() :  QueryDslSupport(){

    private val lecture = QLecture.lecture

    fun search(param: LectureSearchParam): List<Lecture> {
        return queryFactory
            .select(lecture)
            .from(lecture)
            .where(
                lecture.title.contains(param.keywords)
            ).fetch()
    }

}