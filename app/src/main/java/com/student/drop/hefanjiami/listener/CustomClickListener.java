package com.student.drop.hefanjiami.listener;

import android.view.View;

public abstract class CustomClickListener implements View.OnClickListener {
    private long mLastClickTime;
    private long timeInterval = 1000;

    /* access modifiers changed from: protected */
    public void onFastClick(View view) {
    }

    /* access modifiers changed from: protected */
    public abstract void onSingleClick(View view);

    public CustomClickListener() {
    }

    public CustomClickListener(long j) {
        this.timeInterval = j;
    }

    public void onClick(View view) {
        long currentTimeMillis = System.currentTimeMillis();
        if (currentTimeMillis - this.mLastClickTime > this.timeInterval) {
            onSingleClick(view);
            this.mLastClickTime = currentTimeMillis;
            return;
        }
        onFastClick(view);
    }
}
