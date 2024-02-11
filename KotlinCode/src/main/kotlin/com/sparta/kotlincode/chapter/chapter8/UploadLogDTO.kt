package com.sparta.kotlincode.chapter.chapter8

import org.joda.time.LocalDateTime

data class UploadLogDTO(val user_id: Int, val file_size: Int, val upload_date: LocalDateTime?, val url: String) {

}
