package com.sparta.kotlincode.chapter.chapter8

import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.multipart.MultipartFile

@RequestMapping("/api/v1")
@RestController
class S3Controller(
    private val s3Service: AwsS3Service
) {

    @PostMapping("/upload")
    fun fileUpload(@RequestParam("image") multipartFile: List<MultipartFile>): List<String> {
        return s3Service.uploadFiles(multipartFile)
    }

}