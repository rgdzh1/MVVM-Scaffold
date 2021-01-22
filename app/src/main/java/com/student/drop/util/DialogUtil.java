package com.student.drop.util;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Handler;
import android.os.Looper;
import android.util.DisplayMetrics;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import androidx.annotation.NonNull;
import androidx.lifecycle.LifecycleObserver;
import com.student.drop.R;


public class DialogUtil implements LifecycleObserver {
    private Dialog dialog;

    private DialogUtil(Builder builder) {
        dialog = createDialog(builder);
        dialog.setCanceledOnTouchOutside(builder.isCloseOnTouchOutside);
        if (builder.isBackBtn) {
            // 设置弹窗对返回按钮有反应
            dialog.setOnKeyListener(null);
        }
    }




    /**
     * 展示弹窗
     */
    public void show() {
        if (dialog != null && !dialog.isShowing()) {
            new Handler(Looper.getMainLooper())
                    .post(new Runnable() {
                        @Override
                        public void run() {
                            dialog.show();
                        }
                    });
        }
    }

    /**
     * 隐藏弹窗
     */
    public void dissMiss() {
        if (dialog != null && dialog.isShowing()) {
            new Handler(Looper.getMainLooper())
                    .post(new Runnable() {
                        @Override
                        public void run() {
                            dialog.dismiss();
                        }
                    });
        }
    }

    /**
     * 获取Dialog
     */
    public Dialog getDailog() throws Throwable {
        if (dialog != null) {
            return dialog;
        } else {
            throw new Throwable("Activity 已经结束或者Dialog为null,不允许调用这个方法");
        }
    }




    /**
     * @return
     */
    @NonNull
    private Dialog createDialog(Builder builder) {
        Dialog popDialog = new Dialog(builder.mContext, builder.mDialogStyle);
        if (builder.isScrollView) {
            View view = LayoutInflater.from(builder.mContext).inflate(builder.resourceId, null);
            popDialog.setContentView(view);
        } else {
            // 这种方法可能导致ScrollView显示不全
            popDialog.setContentView(builder.resourceId);
        }

        // Metrics中包含的尺寸
        DisplayMetrics metrics = new DisplayMetrics();
        WindowManager windowManager = (WindowManager) builder.mContext.getSystemService(Context.WINDOW_SERVICE);
        windowManager.getDefaultDisplay().getMetrics(metrics);

        Window window = popDialog.getWindow();
        WindowManager.LayoutParams layoutParams = window.getAttributes();
        if (builder.mHeightRate != 0f) {
            layoutParams.height = (int) (metrics.heightPixels * (builder.mHeightRate));
        } else {
            layoutParams.height = WindowManager.LayoutParams.WRAP_CONTENT;
        }
        if (builder.mWidthRate != 0f) {
            layoutParams.width = (int) (metrics.widthPixels * (builder.mWidthRate));
        } else {
            layoutParams.width = (int) (metrics.widthPixels * 0.85);
        }
        window.setAttributes(layoutParams);

        popDialog.setOnKeyListener(new DialogInterface.OnKeyListener() {
            @Override
            public boolean onKey(DialogInterface dialog, int keyCode, KeyEvent event) {
                return keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0;
            }
        });
        return popDialog;
    }

    public static class Builder {
        private Context mContext;
        private int resourceId;
        private boolean isCloseOnTouchOutside;
        private boolean isBackBtn;
        private boolean isScrollView;
        private float mWidthRate;
        private float mHeightRate;
        private int mDialogStyle = R.style.style_transparent_dialog;// 默认样式

        public Builder(Context context, int resourceId) {
            mContext = context;
            this.resourceId = resourceId;
        }

        /**
         * 设置弹窗相对于屏幕的宽高
         *
         * @param wRate 宽
         * @param hRate 高
         * @return
         */
        public Builder setSizeRate(float wRate, float hRate) {
            this.mHeightRate = hRate;
            this.mWidthRate = wRate;
            return this;
        }

        /**
         * 布局是否是ScrollView
         */
        public Builder isScrollView() {
            this.isScrollView = true;
            return this;
        }

        /**
         * 触摸空白处是否关闭弹窗
         *
         * @param isClose true 关闭, false 不关闭
         */
        public Builder setCloseOnTouchOutside(boolean isClose) {
            isCloseOnTouchOutside = isClose;
            return this;
        }

        /**
         * 弹窗是否对返回按钮无反应
         *
         * @param cancle true 有反应 , false 无法应
         */
        public Builder setBackBtn(boolean cancle) {
            isBackBtn = cancle;
            return this;
        }

        /**
         * 设置阴影样式
         *
         * @return
         */
        public Builder setShadowStyle() {
            mDialogStyle = R.style.style_shadow_dialog;
            return this;
        }

        /**
         * 设置半透明样式
         *
         * @return
         */
        public Builder setTranslucentStyle() {
            mDialogStyle = R.style.style_translucent_dialog;
            return this;
        }

        /**
         * 设置透明样式
         *
         * @return
         */
        public Builder setTransparentStyle() {
            mDialogStyle = R.style.style_transparent_dialog;
            return this;
        }

        /**
         * 屏蔽返回按钮
         *
         * @return
         */

        public DialogUtil build() {
            return new DialogUtil(this);
        }
    }
}
