package com.yey.plus.base.base_view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import com.kingja.loadsir.core.LoadSir
import com.yey.plus.base.base_vm.BViewModel


abstract class BVMFragment<T : ViewDataBinding, E : BViewModel> : DBFragment<T>() {
    // 获取LoadSir对象
    private var mLoadSir = lazy {
        LoadSir.getDefault().register(mBinding.root) {
            // 错误点击回调
            onReloadListener(it)
        }
    }

    // 当前界面的ViewModule
    val mVM: Lazy<E> = getVM()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        return mLoadSir.value.loadLayout
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // 加载界面监听
        lifecycle.addObserver(
            PageStateLifecycleObserver(
                mVM.value,
                mLoadSir.value,
                this.activity!!
            )
        )
    }

    var isFirstLoad = true
    override fun onResume() {
        super.onResume()
        if (isFirstLoad) {
            isFirstLoad = !isFirstLoad
            lazyLoad()
        }
    }

    /**
     * 状态页错误点击回调
     */
    open fun onReloadListener(v: View) {

    }

    /**
     * 第一次加载
     */
    open fun lazyLoad() {

    }

    /**
     * 返回ViewModel
     */
    abstract fun getVM(): Lazy<E>
}