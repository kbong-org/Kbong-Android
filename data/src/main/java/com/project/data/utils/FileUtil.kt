package com.project.data.utils

import android.content.Context
import android.net.Uri

fun Uri.getFileExtension(context: Context): String {
    val type = context.contentResolver.getType(this)
    return when {
        type != null -> type.substringAfterLast("/")
        else -> lastPathSegment?.substringAfterLast(".", "jpg") ?: "jpg"
    }
}