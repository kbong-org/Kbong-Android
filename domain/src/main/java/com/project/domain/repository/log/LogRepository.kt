package com.project.domain.repository.log

import android.content.Context
import android.net.Uri
import com.project.domain.model.log.ChoiceLogRequest
import com.project.domain.model.log.FreeLogRequest
import com.project.domain.model.log.ShortLogRequest

interface LogRepository {
    suspend fun postFreeLog(request: FreeLogRequest): Result<Long>
    suspend fun uploadImageAndGetUrl(uri: Uri, context: Context): String
    suspend fun postShortLog(request: ShortLogRequest): Result<Long>
    suspend fun postChoiceLog(request: ChoiceLogRequest): Result<Long>
}