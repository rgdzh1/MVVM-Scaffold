package com.yey.plus.base.base_view

import android.content.res.Resources
import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.blankj.utilcode.util.AdaptScreenUtils
import com.tbruyelle.rxpermissions3.RxPermissions
import com.yey.plus.R
import com.yey.plus.util.AdaptScreen
import com.yey.plus.util.DialogUtil


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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // 加载布局
        mBinding = DataBindingUtil.setContentView(this, layoutId())
        mBinding.lifecycleOwner = this@DBActivity
        startObserve()
        initView()
        initListener()
        initData()
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

    /**
     * 默认是不开启屏幕适配,如果想适配的话在子类中调用该方法并传入相应的适配枚举.
     *
     * @param mAdaptScreenType 适配基准类型
     */
    var mAdaptScreenType: AdaptScreen = AdaptScreen.CLOSE
        set(value) {
            field = value
        }
        get() {
            return field
        }

    /**
     * 屏幕适配
     */
    override fun getResources(): Resources {
        return when (mAdaptScreenType) {
            AdaptScreen.CLOSE -> {
                return AdaptScreenUtils.closeAdapt(super.getResources())
            }
            AdaptScreen.WIDTH -> {
                return AdaptScreenUtils.adaptWidth(super.getResources(), mAdaptScreenType.getSize())
            }
            AdaptScreen.HEIGHT -> {
                return AdaptScreenUtils.adaptHeight(
                    super.getResources(),
                    mAdaptScreenType.getSize()
                )
            }
        }
    }
}