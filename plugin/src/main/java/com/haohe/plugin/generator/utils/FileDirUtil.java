package com.haohe.plugin.generator.utils;

import java.io.File;

/**
 * 文件目录工具类
 */
public class FileDirUtil {

    /**
     * 创建文件夹
     * @param paths
     */
    public static void createFileDir(String paths) {
        new File(paths).mkdirs();
    }
}
