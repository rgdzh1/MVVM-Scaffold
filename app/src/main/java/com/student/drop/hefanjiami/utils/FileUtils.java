package com.student.drop.hefanjiami.utils;

import java.io.File;

public class FileUtils {
    public static void removeFiles(String str) {
        File file = new File(str);
        File[] listFiles = file.listFiles();
        for (File file2 : listFiles) {
            if (file.isDirectory()) {
                File[] listFiles2 = file.listFiles();
                for (File file3 : listFiles2) {
                    System.out.println(file + "/" + file3.getName());
                    if (file3.getName().indexOf("_") == -1) {
                        file3.delete();
                    }
                }
            } else {
                file2.delete();
            }
        }
    }

    public static void removeAllFile(String str) {
        for (File file : new File(str).listFiles()) {
            file.delete();
        }
    }

    public static void removeAllFile(File file) {
        for (File file2 : file.listFiles()) {
            file2.delete();
        }
    }
}
