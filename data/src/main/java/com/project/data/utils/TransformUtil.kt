package com.project.data.utils

import com.google.gson.Gson

// JSON 직렬화 함수 (T 타입을 JSON으로 변환)
fun <T> T.toJsonString(): String {
    val gson = Gson()
    return gson.toJson(this)
}

// JSON 문자열을 T 객체로 변환하는 함수
fun <T> String.fromJsonString(clazz: Class<T>): T {
    val gson = Gson()
    return gson.fromJson(this, clazz)
}

