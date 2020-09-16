package com.yey.plus.base.base_vm

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.yey.plus.VCashApp
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

open class BViewModel(var mBRepo: BRepo) : AndroidViewModel(VCashApp.CONTEXT as Application) {

    /**
     * viewModelScope默认运行在UI线程中
     */
    fun <T> launchOnUI(block: suspend () -> T) {
        viewModelScope.launch {
            block()
        }
    }

    /**
     * 运行在子线程
     */
    fun <T> launchOnIO(block: suspend () -> T) {
        viewModelScope.launch(Dispatchers.IO) {
            block()
        }
    }

    /**
     * 带返回值的协程
     */
    suspend fun <T> asyncOnUI(block: suspend () -> T): T {
        return viewModelScope.async {
            block()
        }.await()
    }
}