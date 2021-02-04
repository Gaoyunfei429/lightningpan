package com.lightning.portal.bean;

import java.io.File;

/**
 * @Author gyf
 * @Date 2021-02-03 16:07
 * @ClassName PathUtils
 * @Description
 */
public class PathUtils {
    public static final String BASEPATH;
    static {
        String path = System.getProperty("user.dir");
        File bFile = new File(path);
        File file = new File(bFile.getParent() + "\\user");
        if (!file.exists()) {
            file.mkdir();
        }
        BASEPATH =  file.getPath();
        System.out.println("BASEPATH = " + BASEPATH);
    }
}