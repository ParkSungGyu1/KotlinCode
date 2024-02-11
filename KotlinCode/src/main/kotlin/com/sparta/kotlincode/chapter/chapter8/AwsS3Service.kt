package com.sparta.kotlincode.chapter.chapter8

import com.amazonaws.services.s3.AmazonS3Client
import com.amazonaws.services.s3.model.ObjectMetadata
import jakarta.transaction.Transactional
import org.joda.time.LocalDateTime
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import org.springframework.web.multipart.MultipartFile
import java.io.IOException
import java.io.InputStream


/**
 * 공식문서
 * https://docs.aws.amazon.com/ko_kr/AmazonS3/latest/userguide/example_s3_PutBucketCors_section.html
 */
@Service
class AwsS3Service(
    private val amazonS3Client: AmazonS3Client,
    private val uploadUtils: UploadUtils) {

    @Value("\${cloud.aws.s3.bucket}")
    private lateinit var bucket:String

    //파일을 aws 보내고 url 받기
    fun uploadFiles(files:List<MultipartFile>): List<String> {
        var imageUrls = ArrayList<String>()
        for (file in files) {
            val originalFileName: String? = file.originalFilename

            if(uploadUtils.isNotImageFile(originalFileName as String))
                throw IllegalArgumentException("png, jpeg, jpg에 해당하는 파일만 업로드할 수 있습니다.");

            val objectMetadata = ObjectMetadata()
            objectMetadata.contentLength = file.size
            objectMetadata.contentType = file.contentType

            try {
                val inputStream: InputStream = file.inputStream
                amazonS3Client.putObject(bucket,originalFileName, inputStream,objectMetadata);
                val uploadFileUrl = amazonS3Client.getUrl(bucket, originalFileName).toString();
                imageUrls.add(uploadFileUrl)
            }catch (e: IOException) {
                e.printStackTrace();
            }
        }
        return imageUrls
    }

    //URL을 DB에 저장 후 DTO로 반환하기
    @Transactional
    fun storeUrls(userId:Int, imageUrls: List<String>, files: List<MultipartFile>): ArrayList<UploadLogDTO> {
        val dtoList = ArrayList<UploadLogDTO>()

        for ((index, file) in files.withIndex()) {

            if(uploadUtils.isNotImageFile(file.originalFilename as String))
                throw IllegalArgumentException("png, jpeg, jpg에 해당하는 파일만 업로드할 수 있습니다.");

            val storeUrlDto = UploadLogDTO(
                user_id = userId,
                file_size = file.size.toInt(),
                upload_date = LocalDateTime.now(),
                url = imageUrls[index]
            )
            dtoList.add(storeUrlDto)
            /* TODO Mapper 이용해 db 저장 하기 */
        }

        return dtoList
    }

}