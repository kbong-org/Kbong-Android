package com.project.data.repositoryimpl.log

import android.content.Context
import android.graphics.Bitmap
import android.graphics.ImageDecoder
import android.net.Uri
import android.os.Build
import android.provider.MediaStore
import androidx.annotation.RequiresApi
import com.project.data.mapper.toDomain
import com.project.data.mapper.toDto
import com.project.data.service.LogService
import com.project.data.utils.getFileExtension
import com.project.domain.model.log.ChoiceLogRequest
import com.project.domain.model.question.ChoiceQuestion
import com.project.domain.model.log.FreeLogRequest
import com.project.domain.model.log.ShortLogRequest
import com.project.domain.model.question.ShortQuestion
import com.project.domain.repository.log.LogRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody.Companion.toRequestBody
import java.io.ByteArrayOutputStream
import javax.inject.Inject
import javax.inject.Named

class LogRepositoryImpl @Inject constructor(
    private val logService: LogService,
    @Named("upload") private val uploadClient: OkHttpClient
) : LogRepository {

    override suspend fun postFreeLog(request: FreeLogRequest): Result<Long> {
        return try {
            val response = logService.postFreeLog(request.toDto())
            val body = response.data
            if (response.isSuccess && body != null) {
                Result.success(body.id)
            } else {
                Result.failure(Exception(response.errorResponse.message))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    @RequiresApi(Build.VERSION_CODES.R)
    override suspend fun uploadImageAndGetUrl(uri: Uri, context: Context): String = withContext(Dispatchers.IO) {
        // WebP 확장자 사용
        val extension = "webp"

        // presigned URL 요청
        val presignedUrl = logService.getPresignedUrl(extension).data?.uploadUrl
            ?: throw Exception("Presigned URL 요청 실패")

        // Uri → Bitmap → WebP ByteArray로 변환
        val bitmap = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
            val source = ImageDecoder.createSource(context.contentResolver, uri)
            ImageDecoder.decodeBitmap(source)
        } else {
            MediaStore.Images.Media.getBitmap(context.contentResolver, uri)
        }

        val byteArrayOutputStream = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.WEBP_LOSSY, 80, byteArrayOutputStream) // 80은 품질, 필요 시 조정
        val webpBytes = byteArrayOutputStream.toByteArray()

        // 3. 업로드
        val requestBody = webpBytes.toRequestBody("image/webp".toMediaTypeOrNull())
        val request = Request.Builder()
            .url(presignedUrl)
            .put(requestBody)
            .build()

        val response = uploadClient.newCall(request).execute()
        if (!response.isSuccessful) throw Exception("이미지 업로드 실패: ${response.code}")

        return@withContext presignedUrl.split("?")[0] // 업로드 성공 시 최종 URL 리턴
    }

    override suspend fun postShortLog(request: ShortLogRequest): Result<Long> {
        return try {
            val response = logService.postShortLog(request.toDto())
            val body = response.data
            if (response.isSuccess && body != null) {
                Result.success(body.id)
            } else {
                Result.failure(Exception(response.errorResponse.message))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun postChoiceLog(request: ChoiceLogRequest): Result<Long> {
        return try {
            val response = logService.postChoiceLog(request.toDto())
            val body = response.data
            if (response.isSuccess && body != null) {
                Result.success(body.id)
            } else {
                Result.failure(Exception(response.errorResponse.message))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}