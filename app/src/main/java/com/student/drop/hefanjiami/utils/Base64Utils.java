package com.student.drop.hefanjiami.utils;

import java.io.File;
import java.io.IOException;

public class Base64Utils {
    private static char[] alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/=".toCharArray();
    private static byte[] codes = new byte[256];

    public static String encode(String str) {
        return new String(encode(str.getBytes()));
    }

    public static String decode(String str) {
        return new String(decode(str.toCharArray()));
    }

    public static char[] encode(byte[] bArr) {
        boolean z;
        char[] cArr = new char[(((bArr.length + 2) / 3) * 4)];
        int i = 0;
        int i2 = 0;
        while (i < bArr.length) {
            int i3 = (bArr[i] & 255) << 8;
            int i4 = i + 1;
            boolean z2 = true;
            if (i4 < bArr.length) {
                i3 |= bArr[i4] & 255;
                z = true;
            } else {
                z = false;
            }
            int i5 = i3 << 8;
            int i6 = i + 2;
            if (i6 < bArr.length) {
                i5 |= bArr[i6] & 255;
            } else {
                z2 = false;
            }
            int i7 = 64;
            cArr[i2 + 3] = alphabet[z2 ? i5 & 63 : 64];
            int i8 = i5 >> 6;
            int i9 = i2 + 2;
            char[] cArr2 = alphabet;
            if (z) {
                i7 = i8 & 63;
            }
            cArr[i9] = cArr2[i7];
            int i10 = i8 >> 6;
            char[] cArr3 = alphabet;
            cArr[i2 + 1] = cArr3[i10 & 63];
            cArr[i2 + 0] = cArr3[(i10 >> 6) & 63];
            i += 3;
            i2 += 4;
        }
        return cArr;
    }

    public static byte[] decode(char[] cArr) {
        int length = cArr.length;
        for (int i = 0; i < cArr.length; i++) {
            if (cArr[i] > 255 || codes[cArr[i]] < 0) {
                length--;
            }
        }
        int i2 = (length / 4) * 3;
        int i3 = length % 4;
        if (i3 == 3) {
            i2 += 2;
        }
        if (i3 == 2) {
            i2++;
        }
        byte[] bArr = new byte[i2];
        int i4 = 0;
        int i5 = 0;
        int i6 = 0;
        for (int i7 = 0; i7 < cArr.length; i7++) {
            byte b = cArr[i7] > 255 ? -1 : codes[cArr[i7]];
            if (b >= 0) {
                i6 += 6;
                i5 = (i5 << 6) | b;
                if (i6 >= 8) {
                    i6 -= 8;
                    bArr[i4] = (byte) ((i5 >> i6) & 255);
                    i4++;
                }
            }
        }
        if (i4 == bArr.length) {
            return bArr;
        }
        throw new Error("Miscalculated data length (wrote " + i4 + " instead of " + bArr.length + ")");
    }

    public static void encode(File file) throws IOException {
        if (!file.exists()) {
            System.exit(0);
        } else {
            writeChars(file, encode(readBytes(file)));
        }
    }

    public static void decode(File file) throws IOException {
        if (!file.exists()) {
            System.exit(0);
        } else {
            writeBytes(file, decode(readChars(file)));
        }
    }

    static {
        for (int i = 0; i < 256; i++) {
            codes[i] = -1;
        }
        for (int i2 = 65; i2 <= 90; i2++) {
            codes[i2] = (byte) (i2 - 65);
        }
        for (int i3 = 97; i3 <= 122; i3++) {
            codes[i3] = (byte) ((i3 + 26) - 97);
        }
        for (int i4 = 48; i4 <= 57; i4++) {
            codes[i4] = (byte) ((i4 + 52) - 48);
        }
        byte[] bArr = codes;
        bArr[43] = 62;
        bArr[47] = 63;
    }

    /* JADX WARNING: Removed duplicated region for block: B:24:0x0042 A[SYNTHETIC, Splitter:B:24:0x0042] */
    /* JADX WARNING: Removed duplicated region for block: B:29:0x004a A[Catch:{ Exception -> 0x0046 }] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static byte[] readBytes(File r6) throws IOException {
        /*
            java.io.ByteArrayOutputStream r0 = new java.io.ByteArrayOutputStream
            r0.<init>()
            r1 = 0
            java.io.FileInputStream r2 = new java.io.FileInputStream     // Catch:{ all -> 0x003e }
            r2.<init>(r6)     // Catch:{ all -> 0x003e }
            java.io.BufferedInputStream r6 = new java.io.BufferedInputStream     // Catch:{ all -> 0x003c }
            r6.<init>(r2)     // Catch:{ all -> 0x003c }
            r1 = 16384(0x4000, float:2.2959E-41)
            byte[] r1 = new byte[r1]     // Catch:{ all -> 0x0037 }
        L_0x0014:
            int r3 = r6.read(r1)     // Catch:{ all -> 0x0037 }
            r4 = -1
            if (r3 == r4) goto L_0x0022
            if (r3 <= 0) goto L_0x0014
            r4 = 0
            r0.write(r1, r4, r3)     // Catch:{ all -> 0x0037 }
            goto L_0x0014
        L_0x0022:
            byte[] r1 = r0.toByteArray()     // Catch:{ all -> 0x0037 }
            r2.close()     // Catch:{ Exception -> 0x0030 }
            r6.close()     // Catch:{ Exception -> 0x0030 }
            r0.close()     // Catch:{ Exception -> 0x0030 }
            goto L_0x0036
        L_0x0030:
            r6 = move-exception
            java.io.PrintStream r0 = java.lang.System.out
            r0.println(r6)
        L_0x0036:
            return r1
        L_0x0037:
            r1 = move-exception
            r5 = r1
            r1 = r6
            r6 = r5
            goto L_0x0040
        L_0x003c:
            r6 = move-exception
            goto L_0x0040
        L_0x003e:
            r6 = move-exception
            r2 = r1
        L_0x0040:
            if (r2 == 0) goto L_0x0048
            r2.close()     // Catch:{ Exception -> 0x0046 }
            goto L_0x0048
        L_0x0046:
            r0 = move-exception
            goto L_0x0051
        L_0x0048:
            if (r1 == 0) goto L_0x004d
            r1.close()     // Catch:{ Exception -> 0x0046 }
        L_0x004d:
            r0.close()     // Catch:{ Exception -> 0x0046 }
            goto L_0x0056
        L_0x0051:
            java.io.PrintStream r1 = java.lang.System.out
            r1.println(r0)
        L_0x0056:
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.hefan.advertutils.utils.Base64Utils.readBytes(java.io.File):byte[]");
    }

    /* JADX WARNING: Removed duplicated region for block: B:26:0x0045 A[Catch:{ Exception -> 0x004e }] */
    /* JADX WARNING: Removed duplicated region for block: B:28:0x004a A[Catch:{ Exception -> 0x004e }] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static char[] readChars(File r6) throws IOException {
        /*
            java.io.CharArrayWriter r0 = new java.io.CharArrayWriter
            r0.<init>()
            r1 = 0
            java.io.FileReader r2 = new java.io.FileReader     // Catch:{ all -> 0x003e }
            r2.<init>(r6)     // Catch:{ all -> 0x003e }
            java.io.BufferedReader r6 = new java.io.BufferedReader     // Catch:{ all -> 0x003c }
            r6.<init>(r2)     // Catch:{ all -> 0x003c }
            r1 = 16384(0x4000, float:2.2959E-41)
            char[] r1 = new char[r1]     // Catch:{ all -> 0x0037 }
        L_0x0014:
            int r3 = r6.read(r1)     // Catch:{ all -> 0x0037 }
            r4 = -1
            if (r3 == r4) goto L_0x0022
            if (r3 <= 0) goto L_0x0014
            r4 = 0
            r0.write(r1, r4, r3)     // Catch:{ all -> 0x0037 }
            goto L_0x0014
        L_0x0022:
            r0.close()     // Catch:{ Exception -> 0x002c }
            r6.close()     // Catch:{ Exception -> 0x002c }
            r2.close()     // Catch:{ Exception -> 0x002c }
            goto L_0x0032
        L_0x002c:
            r6 = move-exception
            java.io.PrintStream r1 = java.lang.System.out
            r1.println(r6)
        L_0x0032:
            char[] r6 = r0.toCharArray()
            return r6
        L_0x0037:
            r1 = move-exception
            r5 = r1
            r1 = r6
            r6 = r5
            goto L_0x0040
        L_0x003c:
            r6 = move-exception
            goto L_0x0040
        L_0x003e:
            r6 = move-exception
            r2 = r1
        L_0x0040:
            r0.close()     // Catch:{ Exception -> 0x004e }
            if (r1 == 0) goto L_0x0048
            r1.close()     // Catch:{ Exception -> 0x004e }
        L_0x0048:
            if (r2 == 0) goto L_0x0054
            r2.close()     // Catch:{ Exception -> 0x004e }
            goto L_0x0054
        L_0x004e:
            r0 = move-exception
            java.io.PrintStream r1 = java.lang.System.out
            r1.println(r0)
        L_0x0054:
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.hefan.advertutils.utils.Base64Utils.readChars(java.io.File):char[]");
    }

    /* JADX WARNING: Removed duplicated region for block: B:17:0x0025 A[SYNTHETIC, Splitter:B:17:0x0025] */
    /* JADX WARNING: Removed duplicated region for block: B:22:0x002d A[Catch:{ Exception -> 0x0029 }] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static void writeBytes(File r2, byte[] r3) throws IOException {
        /*
            r0 = 0
            java.io.FileOutputStream r1 = new java.io.FileOutputStream     // Catch:{ all -> 0x0021 }
            r1.<init>(r2)     // Catch:{ all -> 0x0021 }
            java.io.BufferedOutputStream r2 = new java.io.BufferedOutputStream     // Catch:{ all -> 0x001f }
            r2.<init>(r1)     // Catch:{ all -> 0x001f }
            r2.write(r3)     // Catch:{ all -> 0x001c }
            r2.close()     // Catch:{ Exception -> 0x0015 }
            r1.close()     // Catch:{ Exception -> 0x0015 }
            goto L_0x001b
        L_0x0015:
            r2 = move-exception
            java.io.PrintStream r3 = java.lang.System.out
            r3.println(r2)
        L_0x001b:
            return
        L_0x001c:
            r3 = move-exception
            r0 = r2
            goto L_0x0023
        L_0x001f:
            r3 = move-exception
            goto L_0x0023
        L_0x0021:
            r3 = move-exception
            r1 = r0
        L_0x0023:
            if (r0 == 0) goto L_0x002b
            r0.close()     // Catch:{ Exception -> 0x0029 }
            goto L_0x002b
        L_0x0029:
            r2 = move-exception
            goto L_0x0031
        L_0x002b:
            if (r1 == 0) goto L_0x0036
            r1.close()     // Catch:{ Exception -> 0x0029 }
            goto L_0x0036
        L_0x0031:
            java.io.PrintStream r0 = java.lang.System.out
            r0.println(r2)
        L_0x0036:
            throw r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.hefan.advertutils.utils.Base64Utils.writeBytes(java.io.File, byte[]):void");
    }

    /* JADX WARNING: Removed duplicated region for block: B:17:0x0023 A[SYNTHETIC, Splitter:B:17:0x0023] */
    /* JADX WARNING: Removed duplicated region for block: B:22:0x002b A[Catch:{ Exception -> 0x0027 }] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static void writeChars(File r2, char[] r3) throws IOException {
        /*
            r0 = 0
            java.io.FileWriter r1 = new java.io.FileWriter     // Catch:{ all -> 0x001f }
            r1.<init>(r2)     // Catch:{ all -> 0x001f }
            java.io.BufferedWriter r2 = new java.io.BufferedWriter     // Catch:{ all -> 0x001d }
            r2.<init>(r1)     // Catch:{ all -> 0x001d }
            r2.write(r3)     // Catch:{ all -> 0x001a }
            r2.close()     // Catch:{ Exception -> 0x0015 }
            r1.close()     // Catch:{ Exception -> 0x0015 }
            goto L_0x0019
        L_0x0015:
            r2 = move-exception
            r2.printStackTrace()
        L_0x0019:
            return
        L_0x001a:
            r3 = move-exception
            r0 = r2
            goto L_0x0021
        L_0x001d:
            r3 = move-exception
            goto L_0x0021
        L_0x001f:
            r3 = move-exception
            r1 = r0
        L_0x0021:
            if (r0 == 0) goto L_0x0029
            r0.close()     // Catch:{ Exception -> 0x0027 }
            goto L_0x0029
        L_0x0027:
            r2 = move-exception
            goto L_0x002f
        L_0x0029:
            if (r1 == 0) goto L_0x0032
            r1.close()     // Catch:{ Exception -> 0x0027 }
            goto L_0x0032
        L_0x002f:
            r2.printStackTrace()
        L_0x0032:
            throw r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.hefan.advertutils.utils.Base64Utils.writeChars(java.io.File, char[]):void");
    }
}
