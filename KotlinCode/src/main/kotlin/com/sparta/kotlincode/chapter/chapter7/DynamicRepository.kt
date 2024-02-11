package com.sparta.kotlincode.chapter.chapter7

import com.querydsl.core.BooleanBuilder
import com.querydsl.core.types.dsl.BooleanExpression
import com.sparta.kotlincode.chapter.chapter2.Post
import com.sparta.kotlincode.chapter.chapter2.QPost
import com.sparta.kotlincode.chapter.chapter5.QueryDslSupport
import org.springframework.stereotype.Repository
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime

@Repository
class DynamicRepository : QueryDslSupport(){

    val post: QPost = QPost.post

    private fun search(searchCondition: Map<String, String>): List<Post> {
        return queryFactory
            .selectFrom(post)
            .where(allCond(searchCondition))
            .fetch()
    }

    // BooleanBuilder
    private fun allCond(searchCondition: Map<String, String>): BooleanBuilder {
        val builder = BooleanBuilder()

        return builder
            .and(titleLike(searchCondition["title"]))
            .and(categoryEq(searchCondition["category"]))
            .and(tagLike(searchCondition["tag"]))
            .and(stateEq(searchCondition["status"]))
            .and(withInDays(searchCondition["daysAgo"]))
    }

    // 조건1
    private fun titleLike(title: String?): BooleanExpression? {
        return if (title.isNullOrBlank()) null else post.title.contains(title)
    }

    // 조건2
    private fun categoryEq(category: String?): BooleanExpression? {
        return if (category.isNullOrBlank()) null else post.category.eq(category)
    }

    // 조건3
    private fun tagLike(tag: String?): BooleanExpression? {
        return if (tag.isNullOrBlank()) null else post.tag.contains(tag)
    }

    // 조건4
    private fun stateEq(stateCode: String?): BooleanExpression? {
        return if (stateCode.isNullOrBlank()) null else {post.status.eq(stateCode) }
    }

    // 조건5
    private fun withInDays(daysAgo: String?): BooleanExpression? {
        return if (daysAgo.isNullOrBlank()) null else {
            val days = daysAgo.toLong()
            val now = LocalDate.now()
            val startDate = now.minusDays(days)
            val startDateTime = LocalDateTime.of(startDate, LocalTime.MIN)

            post.createdDate.goe(startDateTime)
        }
    }
}