package com.sparta.kotlincode.chapter.chapter8

import org.springframework.stereotype.Component

@Component
class UploadUtils {

    /* TODO png, jpeg, jpg에 해당하는 파일만 업로드할 수 있도록 개발 해보기 */
    fun isNotImageFile(s: String): Boolean {
        return false
    }

}
