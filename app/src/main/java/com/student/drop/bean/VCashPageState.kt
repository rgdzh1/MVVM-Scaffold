package com.student.drop.bean

/**
 * isPageLoding: 整个界面转圈
 * isDialogLoding: Dialog 弹窗loading
 * isSuccess: 数据加载成功
 * isNetErr: 数据加载失败
 */
class VCashPageState(
    var isPageLoding: Boolean = false,
    var isDialogLoding: Boolean = false,
    var isSuccess: Boolean = false,
    var isNetErr: Boolean = false,
    var isDataErr: Boolean = false,
    var mShowProgressInfo: ShowProgressInfo = ShowProgressInfo()
)

// type = 1 表示执行上传进度, type = 2 表示执行下载进度
// isShow 表示是否展示
data class ShowProgressInfo(var isShow: Boolean = false, var type: Int = 1)