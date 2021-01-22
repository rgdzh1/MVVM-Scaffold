package com.student.drop.base.base_view

import android.content.Context
import android.graphics.drawable.AnimationDrawable
import android.widget.ImageView
import androidx.lifecycle.*
import com.dinuscxj.progressbar.CircleProgressBar
import com.jeremyliao.liveeventbus.LiveEventBus
import com.kingja.loadsir.callback.SuccessCallback
import com.kingja.loadsir.core.LoadService
import com.student.drop.util.DialogUtil
import com.student.drop.R
import com.student.drop.base.base_vm.BViewModel
import com.student.drop.bean.MyProgressInfo
import com.student.drop.widget.LoadSirPageDateErr
import com.student.drop.widget.LoadSirPageLoading
import com.student.drop.widget.LoadSirPageNetErr
/**
 * 页面状态监听
 */
class PageStateLifecycleObserver(
    private val mViewModel: BViewModel,
    private val mLoadSir: LoadService<Any>,
    private val mContext: Context
) : LifecycleObserver {
    // Dialog 旋转动画加载弹窗
    private val mLoadingDULy = lazy {
        DialogUtil
            .Builder(mContext, R.layout.dialog_loading)
            .setTransparentStyle()
            .build()
    }

    // Dialog 圆形进度条上传下载弹窗
    private val mLoadingProgressDULy = lazy {
        val dialogUtil = DialogUtil
            .Builder(mContext, R.layout.dialog_upload_progress)
            .setShadowStyle()
            .build()
        val mCPB = dialogUtil.dailog.findViewById<CircleProgressBar>(R.id.vcash_cpb)
        // 表示要显示下载进度还是上传进度,type = 1 表示执行上传进度, type = 2 表示执行下载进度
        val type = mViewModel.mBRepo.mPageStateMLD.value!!.mShowProgressInfo.type
        LiveEventBus
            .get("VCashApp-CircleProgressBar", MyProgressInfo::class.java)
            .observe(mContext as LifecycleOwner, Observer {
                if (type == it.type) {
                    mCPB.progress = it.progress
                }
            })
        dialogUtil
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    fun onCreate(owner: LifecycleOwner?) {
        setPageListener(mViewModel)
    }

    /**
     * 监听当前界面状态
     */
    fun setPageListener(mVM: BViewModel) {
        mVM.mBRepo.mPageStateMLD.observe(mContext as LifecycleOwner) { vCashPageState -> // 圆形进度条
            if (vCashPageState.mShowProgressInfo.isShow) {
                mLoadingProgressDULy.value.dailog.show()
            } else {
                mLoadingProgressDULy.value.dailog.dismiss()
            }
            // Dialog加载状态
            if (vCashPageState.isDialogLoding) {
                val mDialogAnimationDrawable = mLoadingDULy.value.dailog.findViewById<ImageView>(R.id.img_animation).background as AnimationDrawable
                mLoadingDULy.value.show()
                mDialogAnimationDrawable.start()
            } else {
                val mDialogAnimationDrawable = mLoadingDULy.value.dailog.findViewById<ImageView>(R.id.img_animation).background as AnimationDrawable
                mDialogAnimationDrawable.stop()
                mLoadingDULy.value.dissMiss()
            }
            // 整个界面加载状态
            if (vCashPageState.isPageLoding) {
                mLoadSir.showCallback(LoadSirPageLoading::class.java)
            }
            // 网络错误状态
            if (vCashPageState.isNetErr) {
                mLoadSir.showCallback(LoadSirPageNetErr::class.java)
            }
            // 数据错误状态
            if (vCashPageState.isDataErr) {
                mLoadSir.showCallback(LoadSirPageDateErr::class.java)
            }
            // 加载成功
            if (vCashPageState.isSuccess) {
                mLoadSir.showCallback(SuccessCallback::class.java)
            }
        }
    }
}