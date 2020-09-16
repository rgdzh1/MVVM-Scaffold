package com.yey.plus.base.base_view

import android.os.Bundle
import android.view.View
import androidx.databinding.ViewDataBinding
import com.kingja.loadsir.core.LoadSir
import com.yey.plus.base.base_vm.BViewModel


abstract class BVMActivity<T : ViewDataBinding, E : BViewModel> : DBActivity<T>() {
    // 获取LoadSir对象
    private var mLoadSir = lazy {
        LoadSir.getDefault().register(this){
            // 错误点击回调
            onReloadListener(it)
        }
    }

    // 当前界面的ViewModule
    val mVM = getVM()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // 加载界面监听
        lifecycle.addObserver(PageStateLifecycleObserver(mVM.value, mLoadSir.value, this))
    }

    /**
     * 返回ViewModel
     */
    abstract fun getVM(): Lazy<E>

    /**
     * 状态页错误点击回调
     */
    open fun onReloadListener(v: View) {

    }
}