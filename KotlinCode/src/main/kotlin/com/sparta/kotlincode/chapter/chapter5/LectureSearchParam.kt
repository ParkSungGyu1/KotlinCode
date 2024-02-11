package com.sparta.kotlincode.chapter.chapter5

data class LectureSearchParam (
    val keywords : String,
    val category : String
) {
    companion object {
        fun toParam(title: String): LectureSearchParam {
            return LectureSearchParam(title,"")
        }
    }
}
