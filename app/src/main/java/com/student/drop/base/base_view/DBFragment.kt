package com.student.drop.base.base_view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import com.student.drop.R
import com.tbruyelle.rxpermissions3.RxPermissions


abstract class DBFragment<T : ViewDataBinding> : Fragment() {
    // 当前界面的DataBinding
    lateinit var mBinding: T

    // 权限
    val mPermissionsLy = lazy {
        RxPermissions(this)
    }

    // 动画加载弹窗
    val mLoadingDULy = lazy {
        com.student.drop.util.DialogUtil
            .Builder(context, R.layout.dialog_loading)
            .setTransparentStyle()
            .build()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mBinding = DataBindingUtil.inflate<T>(inflater, layoutId(), container, false)
        mBinding.lifecycleOwner = this@DBFragment
        return mBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        startObserve()
        initView()
        initListener()
        initData()
        super.onViewCreated(view, savedInstanceState)
    }


    /**
     * 监听点击事件
     */
    open fun initListener() {

    }

    /**
     * 返回布局ID
     */
    @LayoutRes
    abstract fun layoutId(): Int

    /**
     * 初始化View
     */
    open fun initView() {}

    /**
     * 初始化数据
     */
    open fun initData() {}

    /**
     * 创建观察者然后添加到LiveData中,监听LiveData
     * 注意: 这里只允许在onCreate生命周期添加
     */
    open fun startObserve() {}
}