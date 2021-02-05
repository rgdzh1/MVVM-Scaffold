package com.student.drop.hefanjiami.utils;

import android.graphics.Point;

public class AlgorithmUtils {
    public static int pwdLevel(String str) {
        boolean z;
        try {
            Integer.parseInt(str);
            z = false;
        } catch (Exception unused) {
            z = true;
        }
        boolean z2 = false;
        boolean z3 = false;
        boolean z4 = false;
        boolean z5 = false;
        for (int i = 0; i < str.length(); i++) {
            char c = str.toCharArray()[i];
            if (c > '@' && c < '[') {
                z4 = true;
            } else if (c > '`' && c < '{') {
                z3 = true;
            } else if (c <= '/' || c >= ':') {
                z2 = true;
            } else {
                z5 = true;
            }
        }
        if (str.length() > 6 && z2 && z3 && z4 && z5) {
            return 4;
        }
        if ((str.length() > 6 && z2 && !z3 && z4 && z5) || ((str.length() > 6 && z2 && z3 && !z4 && z5) || (str.length() > 6 && !z2 && z3 && z4 && z5))) {
            return 3;
        }
        if ((str.length() > 6 && z2 && !z3 && !z4 && z5) || ((str.length() > 6 && !z2 && !z3 && z4 && z5) || (str.length() > 6 && !z2 && z3 && !z4 && z5))) {
            return 2;
        }
        if (!((str.length() <= 6 || !z2 || z3 || z4 || z5) && ((str.length() <= 6 || z2 || z3 || !z4 || z5) && (str.length() <= 6 || z2 || !z3 || z4 || z5)))) {
            return 1;
        }
        if ((str.length() >= 7 || str.length() <= 0) && z && str.length() == 0) {
            return -1;
        }
        return 0;
    }

    private static int getIn_angle(Point point, Point point2, Point point3) {
        return (int) ((Math.acos(((double) (((point.x - point2.x) * (point3.x - point2.x)) + ((point.y - point2.y) * (point3.y - point2.y)))) / Math.sqrt((double) ((Math.abs((point.x - point2.x) * (point.x - point2.x)) + Math.abs((point.y - point2.y) * (point.y - point2.y))) * (Math.abs((point3.x - point2.x) * (point3.x - point2.x)) + Math.abs((point3.y - point2.y) * (point3.y - point2.y)))))) * 180.0d) / 3.141592653589793d);
    }

    public float[] getLocation(float f, float f2, float f3, double d) {
        double d2 = (double) f3;
        double d3 = 6.283185307179586d / d;
        return new float[]{f + ((float) ((int) (Math.cos(d3) * d2))), f2 + ((float) ((int) (d2 * Math.sin(d3))))};
    }
}
