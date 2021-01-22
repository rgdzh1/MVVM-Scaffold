package com.student.drop.base.base_vm

import androidx.lifecycle.MutableLiveData
import com.blankj.utilcode.util.LogUtils
import com.blankj.utilcode.util.ToastUtils
import com.google.gson.JsonParseException
import com.student.drop.BuildConfig
import com.student.drop.R
import com.student.drop.VCashApp
import com.student.drop.bean.BResponse

import com.student.drop.bean.VCashPageState
import java.net.ConnectException
import java.net.SocketTimeoutException


open class BRepo {
    // 状态页控制对象
    val mPageStateMLD = MutableLiveData(VCashPageState(isPageLoding = true))

    /**
     * 安全调用,可以捕获协程中的异常
     * call(): Retrofit 网络接口代理对象请求网络
     */
    suspend fun <E : Any> call(callBlock: suspend () -> BResponse<E>): BResponse<E> {
        return try {
            callBlock.invoke()
        } catch (e: Exception) {
            BResponse(e, "", "")
        }
    }

    /**
     * 安全调用,可以捕获协程中的异常
     * call(): Retrofit 网络接口代理对象请求网络
     */
    suspend fun <E : Any> call(
        isPageLoding: Boolean,
        call: suspend () -> BResponse<E>
    ): BResponse<E> {
        if (isPageLoding) {
            // 页面加载等待
            mPageStateMLD.postValue(VCashPageState(isPageLoding = true))
        } else {
            // 弹窗加载等待
            mPageStateMLD.postValue(VCashPageState(isDialogLoding = true))
        }
        return try {
            call()
        } catch (err: Exception) {
            if (BuildConfig.DEBUG) {
                LogUtils.e("日志 ", err.message)
            }
            BResponse(err, "", "")
        }
    }

    /**
     * 处理网络返回结果,比如网络异常,数据异常
     * successBlock: 从数据库获取到了完整的数据处理方法
     * errorBlock: 与网络有关的错误都交给该方法处理方法
     * code000Block : 验证码000000时候调用的方法
     * isPageStatus: 处理数据的时候是否自动处理页面状态
     */
    suspend fun <F : Any> handleResponse(
        response: BResponse<F>,
        data200Block: suspend (f: F) -> Unit = {},
        dataSuccessBlock: suspend (f: F) -> Unit = {},
        dataErrorBlock: suspend () -> Unit = {},
        handleDataSuccess: Boolean = true,
        handleDataErr: Boolean = true
    ) {
        if (response.mException == null) {
            // 处理数据获取成功后的界面
            if (handleDataSuccess) {
                mPageStateMLD.postValue(VCashPageState(isSuccess = true))
            }
            // 数据获取成功的回调
            response.data?.apply {
                dataSuccessBlock(this)
            }
            if (response.code == "200") {
                // code为200的回调
                response.data?.apply {
                    data200Block(this)
                }
            } else {
                // code非200,就show出Toast
                ToastUtils.showLong(response.msg)
            }
        } else {
            // 有异常
            when (response.mException) {
                is ConnectException -> {
                    // 连接失败
                    if (handleDataErr) mPageStateMLD.postValue(VCashPageState(isNetErr = true))
                    ToastUtils.showLong(VCashApp.CONTEXT.getString(R.string.network_connection_failed))
                }
                is SocketTimeoutException -> {
                    // 请求超时
                    if (handleDataErr) mPageStateMLD.postValue(VCashPageState(isNetErr = true))
                    ToastUtils.showLong(VCashApp.CONTEXT.getString(R.string.network_request_timeout))
                }
                is JsonParseException -> {
                    // 数据解析错误
                    if (handleDataErr) mPageStateMLD.postValue(VCashPageState(isDataErr = true))
                    ToastUtils.showLong(VCashApp.CONTEXT.getString(R.string.api_data_parse_error))
                }
                else -> {
                    // 未知错误
                    if (handleDataErr) mPageStateMLD.postValue(VCashPageState(isDataErr = true))
                    ToastUtils.showLong(response.mException.message)
                }
            }
            // 数据获取错误的回调
            dataErrorBlock()
            mPageStateMLD.postValue(VCashPageState(isDialogLoding = false))
        }
    }
}