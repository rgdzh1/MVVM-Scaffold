package com.student.drop.hefanjiami.utils;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.Point;
import android.os.Build;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.WindowManager;

import java.lang.reflect.Method;
import java.math.BigDecimal;

public class DeviceUtil {
    public static final String TAG = "DeviceUtil";

    public static void getDisplay(Context context) {
        StringBuilder sb = new StringBuilder();
        sb.append("Version code is  \n");
        sb.append("设备的Android版本号:");
        sb.append(getSDKInt() + " " + getSDKVersion() + "\t");
        sb.append("设备型号:");
        sb.append(getDeviceModel() + "\t");
        sb.append("设备厂商:");
        sb.append(getDeviceBrand() + "\t");
        sb.append("程序版本号:" + getAppVersionCode(context) + " " + getAppVersionName(context) + "\t");
        sb.append("设备唯一标识符:" + getSerialNumber());
        Log.i(TAG, ((((sb.toString() + " \n") + getDisplayInfomation(context) + " \n") + getDensity(context) + " \n") + "屏幕大小：" + getScreenInch(context) + "英寸 \n") + getAndroiodScreenProperty(context));
    }

    private static double formatDouble(double d, int i) {
        return new BigDecimal(d).setScale(i, 4).doubleValue();
    }

    public static String getDeviceModel() {
        return Build.MODEL;
    }

    public static String getDeviceBrand() {
        return Build.BRAND;
    }

    public static int getSDKInt() {
        return Build.VERSION.SDK_INT;
    }

    public static String getSDKVersion() {
        return Build.VERSION.RELEASE;
    }

    public static int getAppVersionCode(Context context) {
        try {
            return context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            return 1;
        }
    }

    public static String getAppVersionName(Context context) {
        try {
            return context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            return "";
        }
    }

    public static String getDisplayInfomation(Context context) {
        Point point = new Point();
        Activity activity = (Activity) context;
        activity.getWindowManager().getDefaultDisplay().getSize(point);
        Log.d(TAG, "the screen size is " + point.toString());
        activity.getWindowManager().getDefaultDisplay().getRealSize(point);
        Log.d(TAG, "the screen real size is " + point.toString());
        return point.toString();
    }

    public static String getAndroiodScreenProperty(Context context) {
        int i = getScreenPixel(context).widthPixels;
        int i2 = getScreenPixel(context).heightPixels;
        float density = getDensity(getScreenPixel(context));
        int dpi = getDpi(context);
        int i3 = getDp(context)[0];
        int i4 = getDp(context)[1];
        Log.d("h_bl", "屏幕宽度（像素）：" + i);
        Log.d("h_bl", "屏幕高度（像素）：" + i2);
        Log.d("h_bl", "屏幕密度（0.75 / 1.0 / 1.5）：" + density);
        Log.d("h_bl", "屏幕密度dpi（120 / 160 / 240）：" + dpi);
        Log.d("h_bl", "屏幕宽度（dp）：" + i3);
        Log.d("h_bl", "屏幕高度（dp）：" + i4);
        StringBuilder sb = new StringBuilder();
        return sb.toString();
    }

    public static int getStatusBarHeight(Context context) {
        try {
            Class<?> cls = Class.forName("com.android.internal.R$dimen");
            return context.getResources().getDimensionPixelSize(Integer.parseInt(cls.getField("status_bar_height").get(cls.newInstance()).toString()));
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    @SuppressLint("WrongConstant")
    public static int getDpi(Context context) {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        ((WindowManager) context.getSystemService("window")).getDefaultDisplay().getMetrics(displayMetrics);
        return displayMetrics.densityDpi;
    }

    public static Point getDisplayPixel(Context context) {
        Point point = new Point();
        Activity activity = (Activity) context;
        activity.getWindowManager().getDefaultDisplay().getSize(point);
        Log.d(TAG, "the screen size is " + point.toString());
        activity.getWindowManager().getDefaultDisplay().getRealSize(point);
        Log.d(TAG, "the screen real size is " + point.toString());
        return point;
    }

    @SuppressLint("WrongConstant")
    public static DisplayMetrics getScreenPixel(Context context) {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        ((WindowManager) context.getSystemService("window")).getDefaultDisplay().getMetrics(displayMetrics);
        return displayMetrics;
    }

    public static int[] getDp(Context context) {
        int i = getScreenPixel(context).widthPixels;
        int i2 = getScreenPixel(context).heightPixels;
        float density = getDensity(getScreenPixel(context));
        return new int[]{(int) (((float) i) / density), (int) (((float) i2) / density)};
    }

    public static float getDensity(DisplayMetrics displayMetrics) {
        return displayMetrics.density;
    }

    public static String getDensity(Context context) {
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        Log.d(TAG, "Density is " + displayMetrics.density + " densityDpi is " + displayMetrics.densityDpi + " height: " + displayMetrics.heightPixels + " width: " + displayMetrics.widthPixels);
        return "Density is " + displayMetrics.density + " densityDpi is " + displayMetrics.densityDpi + " height: " + displayMetrics.heightPixels + " width: " + displayMetrics.widthPixels;
    }

    public static double getScreenInch(Context context) {
        int i;
        int i2;
        try {
            Display defaultDisplay = ((Activity) context).getWindowManager().getDefaultDisplay();
            DisplayMetrics displayMetrics = new DisplayMetrics();
            defaultDisplay.getMetrics(displayMetrics);
            if (Build.VERSION.SDK_INT >= 17) {
                Point point = new Point();
                defaultDisplay.getRealSize(point);
                i = point.x;
                i2 = point.y;
            } else if (Build.VERSION.SDK_INT >= 17 || Build.VERSION.SDK_INT < 14) {
                i = displayMetrics.widthPixels;
                i2 = displayMetrics.heightPixels;
            } else {
                Method method = Display.class.getMethod("getRawHeight", new Class[0]);
                int intValue = ((Integer) Display.class.getMethod("getRawWidth", new Class[0]).invoke(defaultDisplay, new Object[0])).intValue();
                i2 = ((Integer) method.invoke(defaultDisplay, new Object[0])).intValue();
                i = intValue;
            }
            float f = (float) i;
            float f2 = (f / displayMetrics.xdpi) * (f / displayMetrics.xdpi);
            float f3 = (float) i2;
            return formatDouble(Math.sqrt((double) (f2 + ((f3 / displayMetrics.ydpi) * (f3 / displayMetrics.ydpi)))), 1);
        } catch (Exception e) {
            e.printStackTrace();
            return 0.0d;
        }
    }

    public static int convertDpToPixel(int i, Context context) {
        return (int) (((float) i) * context.getResources().getDisplayMetrics().density);
    }

    public static int convertPixelToDp(int i, Context context) {
        return (int) (((float) i) / context.getResources().getDisplayMetrics().density);
    }

    public static String getSerialNumber() {
        try {
            if (Build.VERSION.SDK_INT >= 28) {
                return Build.getSerial();
            }
            if (Build.VERSION.SDK_INT > 24) {
                return Build.SERIAL;
            }
            Class<?> cls = Class.forName("android.os.SystemProperties");
            return (String) cls.getMethod("get", String.class).invoke(cls, "ro.serialno");
        } catch (Exception e) {
            e.printStackTrace();
            Log.e("e", "读取设备序列号异常：" + e.toString());
            return "";
        }
    }
}
