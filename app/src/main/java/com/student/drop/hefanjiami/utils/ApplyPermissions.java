package com.student.drop.hefanjiami.utils;

import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.util.Log;
import androidx.core.app.ActivityCompat;
import java.util.LinkedList;

public class ApplyPermissions {
    public static boolean checkPermission(Context context, String... strArr) {
        for (String str : strArr) {
            if (ActivityCompat.checkSelfPermission(context, str) != 0) {
                return false;
            }
        }
        return true;
    }

    public static void applyPermissions(Context context, int i, String... strArr) {
        Log.i("applyPermissions", String.format("requestCode:%s,permissions:%s", Integer.valueOf(i), strArr));
        LinkedList linkedList = new LinkedList();
        if (Build.VERSION.SDK_INT >= 23) {
            for (int i2 = 0; i2 < strArr.length; i2++) {
                if (!checkPermission(context, strArr[i2])) {
                    linkedList.add(strArr[i2]);
                }
            }
            Log.i("applyPermissions", "mPermissionList:" + linkedList.size());
            if (!linkedList.isEmpty()) {
                Log.i("applyPermissions", "请求权限方法:" + linkedList.size());
                ActivityCompat.requestPermissions((Activity) context, (String[]) linkedList.toArray(new String[linkedList.size()]), i);
            }
        }
    }
}
