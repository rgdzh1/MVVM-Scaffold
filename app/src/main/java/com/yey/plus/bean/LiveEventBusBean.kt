package com.yey.plus.bean

// 圆形进度条使用 type =1 表示上传进度, type =2 表示下载进度
// progress 表示进度
data class ProgressInfo(var progress: Int, var type: Int)
