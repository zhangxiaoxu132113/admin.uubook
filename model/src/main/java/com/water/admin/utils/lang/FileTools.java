package com.water.admin.utils.lang;

/**
 * Created by mrwater on 16/7/7.
 */
public class FileTools {

    /**
     * @param originalFilename
     * @return
     * @description 返回文件的后缀名
     */
    public static String getFileExtendsName(String originalFilename) {
        return originalFilename.substring(originalFilename.lastIndexOf("."), originalFilename.length());
    }
}
