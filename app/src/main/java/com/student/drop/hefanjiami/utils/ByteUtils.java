package com.student.drop.hefanjiami.utils;

import android.util.Log;
import androidx.core.internal.view.SupportMenu;

import com.student.drop.util.cl;

import java.util.Locale;

public class ByteUtils {
    static byte[] crc16_tab_h = {0, -63, -127, 64, 1, -64, Byte.MIN_VALUE, 65, 1, -64, Byte.MIN_VALUE, 65, 0, -63, -127, 64, 1, -64, Byte.MIN_VALUE, 65, 0, -63, -127, 64, 0, -63, -127, 64, 1, -64, Byte.MIN_VALUE, 65, 1, -64, Byte.MIN_VALUE, 65, 0, -63, -127, 64, 0, -63, -127, 64, 1, -64, Byte.MIN_VALUE, 65, 0, -63, -127, 64, 1, -64, Byte.MIN_VALUE, 65, 1, -64, Byte.MIN_VALUE, 65, 0, -63, -127, 64, 1, -64, Byte.MIN_VALUE, 65, 0, -63, -127, 64, 0, -63, -127, 64, 1, -64, Byte.MIN_VALUE, 65, 0, -63, -127, 64, 1, -64, Byte.MIN_VALUE, 65, 1, -64, Byte.MIN_VALUE, 65, 0, -63, -127, 64, 0, -63, -127, 64, 1, -64, Byte.MIN_VALUE, 65, 1, -64, Byte.MIN_VALUE, 65, 0, -63, -127, 64, 1, -64, Byte.MIN_VALUE, 65, 0, -63, -127, 64, 0, -63, -127, 64, 1, -64, Byte.MIN_VALUE, 65, 1, -64, Byte.MIN_VALUE, 65, 0, -63, -127, 64, 0, -63, -127, 64, 1, -64, Byte.MIN_VALUE, 65, 0, -63, -127, 64, 1, -64, Byte.MIN_VALUE, 65, 1, -64, Byte.MIN_VALUE, 65, 0, -63, -127, 64, 0, -63, -127, 64, 1, -64, Byte.MIN_VALUE, 65, 1, -64, Byte.MIN_VALUE, 65, 0, -63, -127, 64, 1, -64, Byte.MIN_VALUE, 65, 0, -63, -127, 64, 0, -63, -127, 64, 1, -64, Byte.MIN_VALUE, 65, 0, -63, -127, 64, 1, -64, Byte.MIN_VALUE, 65, 1, -64, Byte.MIN_VALUE, 65, 0, -63, -127, 64, 1, -64, Byte.MIN_VALUE, 65, 0, -63, -127, 64, 0, -63, -127, 64, 1, -64, Byte.MIN_VALUE, 65, 1, -64, Byte.MIN_VALUE, 65, 0, -63, -127, 64, 0, -63, -127, 64, 1, -64, Byte.MIN_VALUE, 65, 0, -63, -127, 64, 1, -64, Byte.MIN_VALUE, 65, 1, -64, Byte.MIN_VALUE, 65, 0, -63, -127, 64};
    static byte[] crc16_tab_l = {0, -64, -63, 1, -61, 3, 2, -62, -58, 6, 7, -57, 5, -59, -60, 4, -52, 12, cl.k, -51, cl.m, -49, -50, cl.l, 10, -54, -53, 11, -55, 9, 8, -56, -40, 24, 25, -39, 27, -37, -38, 26, 30, -34, -33, 31, -35, 29, 28, -36, 20, -44, -43, 21, -41, 23, 22, -42, -46, 18, 19, -45, 17, -47, -48, cl.n, -16, 48, 49, -15, 51, -13, -14, 50, 54, -10, -9, 55, -11, 53, 52, -12, 60, -4, -3, 61, -1, 63, 62, -2, -6, 58, 59, -5, 57, -7, -8, 56, 40, -24, -23, 41, -21, 43, 42, -22, -18, 46, 47, -17, 45, -19, -20, 44, -28, 36, 37, -27, 39, -25, -26, 38, 34, -30, -29, 35, -31, 33, 32, -32, -96, 96, 97, -95, 99, -93, -94, 98, 102, -90, -89, 103, -91, 101, 100, -92, 108, -84, -83, 109, -81, 111, 110, -82, -86, 106, 107, -85, 105, -87, -88, 104, 120, -72, -71, 121, -69, 123, 122, -70, -66, 126, Byte.MAX_VALUE, -65, 125, -67, -68, 124, -76, 116, 117, -75, 119, -73, -74, 118, 114, -78, -77, 115, -79, 113, 112, -80, 80, -112, -111, 81, -109, 83, 82, -110, -106, 86, 87, -105, 85, -107, -108, 84, -100, 92, 93, -99, 95, -97, -98, 94, 90, -102, -101, 91, -103, 89, 88, -104, -120, 72, 73, -119, 75, -117, -118, 74, 78, -114, -113, 79, -115, 77, 76, -116, 68, -124, -123, 69, -121, 71, 70, -122, -126, 66, 67, -125, 65, -127, Byte.MIN_VALUE, 64};

    public static boolean isChinese(String str) {
        char[] charArray = str.toCharArray();
        for (int i = 0; i < charArray.length; i++) {
            byte[] bytes = ("" + charArray[i]).getBytes();
            if (bytes.length == 2) {
                int[] iArr = {bytes[0] & 255, bytes[1] & 255};
                if (iArr[0] >= 129 && iArr[0] <= 254 && iArr[1] >= 64 && iArr[1] <= 254) {
                    return true;
                }
            }
        }
        return false;
    }

    public static String getHumanReadableSize(long j) {
        String[] strArr = {"Byte", "KB", "MB", "GB", "TB", "PB"};
        double d = (double) j;
        int i = 0;
        while (d > 1024.0d) {
            d /= 1024.0d;
            i++;
        }
        return (((double) ((int) (d * 100.0d))) / 100.0d) + strArr[i];
    }

    public static int getActualLength(byte[] bArr) {
        int i = 0;
        while (i < bArr.length && bArr[i] != 0) {
            i++;
        }
        return i;
    }

    public static String strTo16(String str) {
        String str2 = "";
        for (int i = 0; i < str.length(); i++) {
            str2 = str2 + Integer.toHexString(str.charAt(i));
        }
        return str2;
    }

    public static String hexStringToString(String str) {
        String str2;
        Exception e;
        if (str == null || str.equals("")) {
            return null;
        }
        String replace = str.replace(" ", "");
        byte[] bArr = new byte[(replace.length() / 2)];
        for (int i = 0; i < bArr.length; i++) {
            int i2 = i * 2;
            try {
                bArr[i] = (byte) (Integer.parseInt(replace.substring(i2, i2 + 2), 16) & 255);
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        try {
            str2 = new String(bArr, "UTF-8");
            try {
                new String();
            } catch (Exception e3) {
                e = e3;
            }
        } catch (Exception e4) {
            e = e4;
            str2 = replace;
            e.printStackTrace();
            return str2;
        }
        return str2;
    }

    public static byte[] hex2byte(String str) {
        String replace = str.replace(" ", "");
        char[] charArray = replace.toCharArray();
        byte[] bArr = new byte[(replace.length() / 2)];
        for (int i = 0; i < bArr.length; i++) {
            int i2 = i * 2;
            bArr[i] = (byte) (((byte) (((byte) ("0123456789ABCDEF".indexOf(charArray[i2]) * 16)) + "0123456789ABCDEF".indexOf(charArray[i2 + 1]))) & 255);
        }
        return bArr;
    }

    public static byte[] hexStringToByteArray(String str) {
        int length = str.length();
        byte[] bArr = new byte[(length / 2)];
        for (int i = 0; i < length; i += 2) {
            bArr[i / 2] = (byte) ((Character.digit(str.charAt(i), 16) << 4) + Character.digit(str.charAt(i + 1), 16));
        }
        return bArr;
    }

    public static String byteArrayToHexString(byte[] bArr) {
        char[] cArr = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};
        char[] cArr2 = new char[(bArr.length * 2)];
        for (int i = 0; i < bArr.length; i++) {
            int i2 = bArr[i] & 255;
            int i3 = i * 2;
            cArr2[i3] = cArr[i2 >>> 4];
            cArr2[i3 + 1] = cArr[i2 & 15];
        }
        return new String(cArr2).toUpperCase(Locale.US);
    }

    public static String bytes2HexString(byte[] bArr, int i) {
        String str = "";
        for (int i2 = 0; i2 < i; i2++) {
            String hexString = Integer.toHexString(bArr[i2] & 255);
            if (hexString.length() == 1) {
                hexString = '0' + hexString;
            }
            str = str + hexString;
        }
        return str;
    }

    public static String bytesToHexString(byte[] bArr) {
        StringBuilder sb = new StringBuilder("");
        if (bArr == null || bArr.length <= 0) {
            return null;
        }
        for (byte b : bArr) {
            String hexString = Integer.toHexString(b & 255);
            if (hexString.length() < 2) {
                sb.append(0);
            }
            sb.append(hexString);
        }
        return sb.toString();
    }

    public static String byteToStr(byte[] bArr, int i) {
        String str = "";
        for (int i2 = 0; i2 < i; i2++) {
            String hexString = Integer.toHexString(bArr[i2] & 255);
            if (hexString.length() == 1) {
                hexString = '0' + hexString;
            }
            str = str + hexString;
        }
        return str;
    }

    public static byte[] CRC(byte[] bArr) {
        int i = 0;
        byte b = -1;
        byte b2 = -1;
        while (i < bArr.length - 2) {
            b2 = (byte) (b2 ^ bArr[i]);
            byte b3 = b;
            for (int i2 = 0; i2 < 8; i2++) {
                byte b4 = (byte) (b3 / 2);
                byte b5 = (byte) (b2 / 2);
                if ((b3 & 1) == 1) {
                    b5 = (byte) (b5 | 128);
                }
                if ((b2 & 1) == 1) {
                    b3 = (byte) (b4 ^ cl.n);
                    b2 = (byte) (b5 ^ 33);
                } else {
                    b3 = b4;
                    b2 = b5;
                }
            }
            i++;
            b = b3;
        }
        return new byte[]{b, b2};
    }

    public static String getCRC_16(byte[] bArr) {
        int i = 0;
        int i2 = SupportMenu.USER_MASK;
        while (i < bArr.length) {
            int i3 = i2 ^ (bArr[i] & 255);
            for (int i4 = 0; i4 < 8; i4++) {
                i3 = (i3 & 1) != 0 ? (i3 >> 1) ^ 40961 : i3 >> 1;
            }
            i++;
            i2 = i3;
        }
        if (Integer.toHexString(i2).toUpperCase().length() == 2) {
            return byteToStr(bArr, bArr.length) + "00" + Integer.toHexString(i2).toUpperCase();
        } else if (Integer.toHexString(i2).toUpperCase().length() == 3) {
            return byteToStr(bArr, bArr.length) + "0" + Integer.toHexString(i2).toUpperCase();
        } else {
            return byteToStr(bArr, bArr.length) + Integer.toHexString(i2).toUpperCase();
        }
    }

    public static String getSum16(byte[] bArr, int i) {
        byte[] bArr2 = new byte[i];
        long j = 0;
        for (byte b : bArr) {
            long j2 = (long) b;
            if (j2 < 0) {
                j2 += 256;
            }
            j += j2;
        }
        for (int i2 = 0; i2 < i; i2++) {
            bArr2[(i - i2) - 1] = (byte) ((int) ((j >> (i2 * 8)) & 255));
        }
        return byteToStr(bArr, i) + byteToStr(bArr2, bArr2.length).substring(byteToStr(bArr2, bArr2.length).length() - 4, byteToStr(bArr2, bArr2.length).length());
    }

    public static int calcCrc16(byte[] bArr) {
        return calcCrc16(bArr, 0, bArr.length);
    }

    public static int calcCrc16(byte[] bArr, int i, int i2) {
        return calcCrc16(bArr, i, i2, SupportMenu.USER_MASK);
    }

    public static int calcCrc16(byte[] bArr, int i, int i2, int i3) {
        int i4 = (65280 & i3) >> 8;
        int i5 = i3 & 255;
        int i6 = 0;
        while (i6 < i2) {
            int i7 = (i5 ^ bArr[i + i6]) & 255;
            int i8 = i4 ^ crc16_tab_h[i7];
            i6++;
            i4 = crc16_tab_l[i7];
            i5 = i8;
        }
        return ((i4 & 255) << 8) | (i5 & 255 & SupportMenu.USER_MASK);
    }

    public static String getCrc(int i) {
        String format = String.format("%04x", Integer.valueOf(i));
        String substring = format.substring(0, 2);
        String substring2 = format.substring(2, 4);
        Log.i("BLUEDATA", "crc ---- : " + substring + "  " + substring2);
        return substring.concat(" ").concat(substring2).concat(" ");
    }
}
