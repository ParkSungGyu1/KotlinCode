package com.sparta.kotlincode.chapter.chapter6

import com.querydsl.core.BooleanBuilder
import com.sparta.kotlincode.chapter.chapter5.QueryDslSupport
import org.springframework.data.domain.Page

import org.springframework.data.domain.PageImpl
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Repository


@Repository
class CustomCourseRepository : QueryDslSupport() {
    private val course = QCourse.course



    fun findByPageableAndStatus(pageable: Pageable, status: String?): Page<Course> {

        val whereClause = BooleanBuilder()

        status?.let { whereClause.and(course.status.eq(status)) }


        val totalCount = queryFactory.select(course.count()).from(course).where(whereClause).fetchOne() ?: 0L


        val query = queryFactory.selectFrom(course)
            .where(whereClause)
            .offset(pageable.offset)
            .limit(pageable.pageSize.toLong())


        if (pageable.sort.isSorted) {

            when(pageable.sort.first()?.property) {
                "id" -> query.orderBy(course.id.asc())
                "title" -> query.orderBy(course.title.asc())
                else -> query.orderBy(course.id.asc())
            }
        } else {
            query.orderBy(course.id.asc())
        }


        val contents = query.fetch()


        return PageImpl(contents, pageable, totalCount)

    }
}