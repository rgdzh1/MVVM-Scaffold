package com.student.drop.hefanjiami.utils;

import android.content.Context;
import android.content.SharedPreferences;
import androidx.collection.ArraySet;
import java.util.Set;

public class PreferencesUtils {
    public static final String PREFERENCE_NAME = "zipper";
    public static final String PROJECT = "project";
    public static final String USER = "user";
    public static final String VISITOR = "visitor";

    private PreferencesUtils() {
        throw new AssertionError();
    }

    public static boolean clearData(Context context, String str) {
        return context.getSharedPreferences(str, 0).edit().clear().commit();
    }

    public static boolean clearData(Context context, String str, String str2) {
        putString(context, str, null, str2);
        return true;
    }

    public static void removeKey(Context context, String str, String str2) {
        context.getSharedPreferences(str2, 0).edit().remove(str);
    }

    public static void removeValue(Context context, String str, String str2) {
        context.getSharedPreferences(str2, 0).edit().remove(str);
    }

    public static boolean putString(Context context, String str, String str2, String str3) {
        SharedPreferences.Editor edit = context.getSharedPreferences(str3, 0).edit();
        edit.putString(str, str2);
        return edit.commit();
    }

    public static String getString(Context context, String str, String str2) {
        return getString(context, str, null, str2);
    }

    public static String getString(Context context, String str, String str2, String str3) {
        return context.getSharedPreferences(str3, 0).getString(str, str2);
    }

    public static boolean putInt(Context context, String str, int i, String str2) {
        SharedPreferences.Editor edit = context.getSharedPreferences(str2, 0).edit();
        edit.putInt(str, i);
        return edit.commit();
    }

    public static int getInt(Context context, String str, String str2) {
        return getInt(context, str, -1, str2);
    }

    /* JADX DEBUG: Failed to insert an additional move for type inference into block B:1:0x0005 */
    /* JADX DEBUG: Multi-variable search result rejected for r1v2, resolved type: android.content.SharedPreferences */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r1v1, types: [android.content.SharedPreferences] */
    /* JADX WARN: Type inference failed for: r1v5, types: [int] */
    /* JADX WARNING: Can't wrap try/catch for region: R(3:4|5|6) */
    /* JADX WARNING: Code restructure failed: missing block: B:6:0x0023, code lost:
        return java.lang.Integer.parseInt(r1.getString(r2, r3 + ""));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:7:0x0024, code lost:
        return r3;
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing exception handler attribute for start block: B:4:0x000a */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static int getInt(Context r1, String r2, int r3, String r4) {
        /*
            r0 = 0
            android.content.SharedPreferences r1 = r1.getSharedPreferences(r4, r0)
            int r1 = r1.getInt(r2, r3)     // Catch:{ ClassCastException -> 0x000a }
            return r1
        L_0x000a:
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch:{ NumberFormatException -> 0x0024 }
            r4.<init>()     // Catch:{ NumberFormatException -> 0x0024 }
            r4.append(r3)     // Catch:{ NumberFormatException -> 0x0024 }
            java.lang.String r0 = ""
            r4.append(r0)     // Catch:{ NumberFormatException -> 0x0024 }
            java.lang.String r4 = r4.toString()     // Catch:{ NumberFormatException -> 0x0024 }
            java.lang.String r1 = r1.getString(r2, r4)     // Catch:{ NumberFormatException -> 0x0024 }
            int r1 = java.lang.Integer.parseInt(r1)     // Catch:{ NumberFormatException -> 0x0024 }
            return r1
        L_0x0024:
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.hefan.advertutils.utils.PreferencesUtils.getInt(android.content.Context, java.lang.String, int, java.lang.String):int");
    }

    public static boolean putLong(Context context, String str, long j, String str2) {
        SharedPreferences.Editor edit = context.getSharedPreferences(str2, 0).edit();
        edit.putLong(str, j);
        return edit.commit();
    }

    public static long getLong(Context context, String str, String str2) {
        return getLong(context, str, -1, str2);
    }

    /* JADX DEBUG: Failed to insert an additional move for type inference into block B:1:0x0005 */
    /* JADX DEBUG: Multi-variable search result rejected for r1v2, resolved type: android.content.SharedPreferences */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r1v1, types: [android.content.SharedPreferences] */
    /* JADX WARN: Type inference failed for: r1v5, types: [long] */
    /* JADX WARNING: Can't wrap try/catch for region: R(3:4|5|6) */
    /* JADX WARNING: Code restructure failed: missing block: B:6:0x0023, code lost:
        return java.lang.Long.parseLong(r1.getString(r2, r3 + ""));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:7:0x0024, code lost:
        return r3;
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing exception handler attribute for start block: B:4:0x000a */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static long getLong(Context r1, String r2, long r3, String r5) {
        /*
            r0 = 0
            android.content.SharedPreferences r1 = r1.getSharedPreferences(r5, r0)
            long r1 = r1.getLong(r2, r3)     // Catch:{ ClassCastException -> 0x000a }
            return r1
        L_0x000a:
            java.lang.StringBuilder r5 = new java.lang.StringBuilder     // Catch:{ NumberFormatException -> 0x0024 }
            r5.<init>()     // Catch:{ NumberFormatException -> 0x0024 }
            r5.append(r3)     // Catch:{ NumberFormatException -> 0x0024 }
            java.lang.String r0 = ""
            r5.append(r0)     // Catch:{ NumberFormatException -> 0x0024 }
            java.lang.String r5 = r5.toString()     // Catch:{ NumberFormatException -> 0x0024 }
            java.lang.String r1 = r1.getString(r2, r5)     // Catch:{ NumberFormatException -> 0x0024 }
            long r1 = java.lang.Long.parseLong(r1)     // Catch:{ NumberFormatException -> 0x0024 }
            return r1
        L_0x0024:
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.hefan.advertutils.utils.PreferencesUtils.getLong(android.content.Context, java.lang.String, long, java.lang.String):long");
    }

    public static boolean putFloat(Context context, String str, float f, String str2) {
        SharedPreferences.Editor edit = context.getSharedPreferences(str2, 0).edit();
        edit.putFloat(str, f);
        return edit.commit();
    }

    public static float getFloat(Context context, String str, String str2) {
        return getFloat(context, str, -1.0f, str2);
    }

    /* JADX DEBUG: Failed to insert an additional move for type inference into block B:1:0x0005 */
    /* JADX DEBUG: Multi-variable search result rejected for r1v2, resolved type: android.content.SharedPreferences */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r1v1, types: [android.content.SharedPreferences] */
    /* JADX WARN: Type inference failed for: r1v5, types: [float] */
    /* JADX WARNING: Can't wrap try/catch for region: R(3:4|5|6) */
    /* JADX WARNING: Code restructure failed: missing block: B:6:0x0023, code lost:
        return java.lang.Float.parseFloat(r1.getString(r2, r3 + ""));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:7:0x0024, code lost:
        return r3;
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing exception handler attribute for start block: B:4:0x000a */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static float getFloat(Context r1, String r2, float r3, String r4) {
        /*
            r0 = 0
            android.content.SharedPreferences r1 = r1.getSharedPreferences(r4, r0)
            float r1 = r1.getFloat(r2, r3)     // Catch:{ ClassCastException -> 0x000a }
            return r1
        L_0x000a:
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch:{ NumberFormatException -> 0x0024 }
            r4.<init>()     // Catch:{ NumberFormatException -> 0x0024 }
            r4.append(r3)     // Catch:{ NumberFormatException -> 0x0024 }
            java.lang.String r0 = ""
            r4.append(r0)     // Catch:{ NumberFormatException -> 0x0024 }
            java.lang.String r4 = r4.toString()     // Catch:{ NumberFormatException -> 0x0024 }
            java.lang.String r1 = r1.getString(r2, r4)     // Catch:{ NumberFormatException -> 0x0024 }
            float r1 = java.lang.Float.parseFloat(r1)     // Catch:{ NumberFormatException -> 0x0024 }
            return r1
        L_0x0024:
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.hefan.advertutils.utils.PreferencesUtils.getFloat(android.content.Context, java.lang.String, float, java.lang.String):float");
    }

    public static boolean putBoolean(Context context, String str, boolean z, String str2) {
        SharedPreferences.Editor edit = context.getSharedPreferences(str2, 0).edit();
        edit.putBoolean(str, z);
        return edit.commit();
    }

    public static boolean getBoolean(Context context, String str, String str2) {
        return getBoolean(context, str, false, str2);
    }

    /* JADX DEBUG: Failed to insert an additional move for type inference into block B:1:0x0005 */
    /* JADX DEBUG: Multi-variable search result rejected for r1v2, resolved type: android.content.SharedPreferences */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r1v1, types: [android.content.SharedPreferences] */
    /* JADX WARN: Type inference failed for: r1v5, types: [boolean] */
    /* JADX WARNING: Can't wrap try/catch for region: R(3:4|5|6) */
    /* JADX WARNING: Code restructure failed: missing block: B:6:0x0023, code lost:
        return java.lang.Boolean.parseBoolean(r1.getString(r2, r3 + ""));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:7:0x0024, code lost:
        return r3;
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing exception handler attribute for start block: B:4:0x000a */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static boolean getBoolean(Context r1, String r2, boolean r3, String r4) {
        /*
            r0 = 0
            android.content.SharedPreferences r1 = r1.getSharedPreferences(r4, r0)
            boolean r1 = r1.getBoolean(r2, r3)     // Catch:{ ClassCastException -> 0x000a }
            return r1
        L_0x000a:
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch:{ NumberFormatException -> 0x0024 }
            r4.<init>()     // Catch:{ NumberFormatException -> 0x0024 }
            r4.append(r3)     // Catch:{ NumberFormatException -> 0x0024 }
            java.lang.String r0 = ""
            r4.append(r0)     // Catch:{ NumberFormatException -> 0x0024 }
            java.lang.String r4 = r4.toString()     // Catch:{ NumberFormatException -> 0x0024 }
            java.lang.String r1 = r1.getString(r2, r4)     // Catch:{ NumberFormatException -> 0x0024 }
            boolean r1 = java.lang.Boolean.parseBoolean(r1)     // Catch:{ NumberFormatException -> 0x0024 }
            return r1
        L_0x0024:
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.hefan.advertutils.utils.PreferencesUtils.getBoolean(android.content.Context, java.lang.String, boolean, java.lang.String):boolean");
    }

    public static boolean putStringSet(Context context, String str, Set set, String str2) {
        SharedPreferences.Editor edit = context.getSharedPreferences(str2, 0).edit();
        edit.putStringSet(str, set);
        return edit.commit();
    }

    public static Set getStringSet(Context context, String str, String str2) {
        return getStringSet(context, str, new ArraySet(), str2);
    }

    public static Set getStringSet(Context context, String str, Set set, String str2) {
        return context.getSharedPreferences(str2, 0).getStringSet(str, set);
    }
}
