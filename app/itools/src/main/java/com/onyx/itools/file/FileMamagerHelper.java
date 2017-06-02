package com.onyx.itools.file;

import android.annotation.SuppressLint;
import android.text.TextUtils;
import android.webkit.MimeTypeMap;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 * @Copyright: Copyright © 2017 Onyx International Inc. All rights reserved.
 * @Project: iTools
 * @Author: Jack
 * @Date: 2017/6/3 0003,1:29
 * @Version: V1.0
 * @Description: TODO
 */
public class FileMamagerHelper {

    public static List<File> getFilesByFile(File file) {

        if (file != null && file.exists()) {
            File[] files = file.listFiles(new PFileFilter());
            List<File> filterFile = new ArrayList<>();
            if (Collections.addAll(filterFile, files)) {
                return filterFile;
            }
        }
        return null;
    }

    public static List<File> getFilesByPath(String path) {
        if (TextUtils.isEmpty(path)) {
            return null;
        }
        File file = new File(path);
        return getFilesByFile(file);
    }

    public static boolean hasParent(String filePath) {
        if (TextUtils.isEmpty(filePath)) {
            return false;
        }

        File file = new File(filePath);
        return hasParent(file);
    }

    public static String getParent(String filePath) {
        if (TextUtils.isEmpty(filePath)) {
            return "";
        }

        File file = new File(filePath);
        return file.getParent();
    }

    public static boolean hasParent(File file) {
        if (file != null && file.exists()) {
            return file.getParentFile() != null;
        }

        return false;
    }

    public static String getFileName(File file) {
        if (file != null) {
            return file.getName();
        }
        return "";
    }

    public static String getFileLastDate(File file) {
        if (file == null) {
            return "";
        }
        long date = file.lastModified();
        if (date == 0) {
            return "";
        }
        @SuppressLint("SimpleDateFormat")
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy年MM月dd日");
        return simpleDateFormat.format(new Date(date));
    }

    public static String getFileSize(File file) {
        if (file.isFile()) {
            float size = file.length() / 1024f;
            if (size < 1024) {
                if (size < 0.01) {
                    size = 0.01f;
                }
                return String.format("%.2fKB", size);
            }
            size = size / 1024f;
            return String.format("%.2fMB", size);
        }
        return "";
    }

    public static String getMimeType(File file) {
        String suffix = getSuffix(file);
        if (suffix == null) {
            return "file/*";
        }
        String type = MimeTypeMap.getSingleton().getMimeTypeFromExtension(suffix);
        if (type != null || !type.isEmpty()) {
            return type;
        }
        return "file/*";
    }

    private static String getSuffix(File file) {
        if (file == null || !file.exists() || file.isDirectory()) {
            return null;
        }
        String fileName = file.getName();
        if (fileName.equals("") || fileName.endsWith(".")) {
            return null;
        }
        int index = fileName.lastIndexOf(".");
        if (index != -1) {
            return fileName.substring(index + 1).toLowerCase(Locale.US);
        } else {
            return null;
        }
    }
}
