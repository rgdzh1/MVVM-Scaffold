package com.student.drop.bean

// 坑死了, 这个类没有keep
data class BResponse<out T>(
    val mException: Exception? = null,
    val code: String,
    val message: String,
    val data: T? = null
)

