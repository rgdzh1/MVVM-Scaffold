package com.yey.plus.util;

public enum AdaptScreen {

    WIDTH(360),
    HEIGHT(640),
    CLOSE(0);
    // 尺寸
    private int mSize;

    AdaptScreen(int mSize) {
        this.mSize = mSize;
    }

    public int getSize() {
        return mSize;
    }
}
