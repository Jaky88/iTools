package com.onyx.itools.file;

import java.io.File;
import java.io.FileFilter;

/**
 * @Copyright: Copyright © 2017 Onyx International Inc. All rights reserved.
 * @Project: iTools
 * @Author: Jack
 * @Date: 2017/6/3 0003,1:27
 * @Version: V1.0
 * @Description: TODO
 */
public class PFileFilter implements FileFilter {
    @Override
    public boolean accept(File pathname) {
        if (pathname.exists()) {

            if (pathname.isDirectory() && pathname.canRead() && pathname.canWrite()) {
                return pathname.listFiles().length > 0;
            }

            if (pathname.isFile() && pathname.canRead() && pathname.canWrite()) {
                if (pathname.getName().toLowerCase().endsWith(".apk")) {
                    return true;
                }
            }
        }
        return false;
    }
}
