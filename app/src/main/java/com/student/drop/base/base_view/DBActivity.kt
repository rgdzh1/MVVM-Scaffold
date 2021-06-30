package com.student.drop.base.base_view

import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.student.drop.R
import com.student.drop.util.DialogUtil
import com.tbruyelle.rxpermissions3.RxPermissions


abstract class DBActivity<T : ViewDataBinding> : AppCompatActivity() {
    // 当前界面的DataBinding
    lateinit var mBinding: T

    val mPermissionsLy = lazy {
        RxPermissions(this)
    }

    // 动画加载弹窗
    val mLoadingDULy = lazy {
        DialogUtil
            .Builder(this, R.layout.dialog_loading)
            .setTransparentStyle()
            .build()
    }

    // 是否执行set类方法, 不执行将由BVMActivity类执行
    var isProcess = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // 加载布局
        mBinding = DataBindingUtil.setContentView(this, layoutId())
        mBinding.lifecycleOwner = this@DBActivity
        if (isProcess) {
            startObserve()
            initView()
            initListener()
            initData()
        }
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