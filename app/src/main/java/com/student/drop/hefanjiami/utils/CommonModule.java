package com.student.drop.hefanjiami.utils;

import android.content.Context;

public class CommonModule {
    private static Context sAppContext;

    public static void init(Context context) {
        if (sAppContext == null) {
            sAppContext = context.getApplicationContext();
        }
    }

    public static Context getAppContext() {
        return sAppContext;
    }
}
