package com.water.admin.utils.lang;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

/**
 * Created by mrwater on 2017/6/24.
 */
public class StringUtil {

    private StringUtil(){
    }

    public static String deconde(String content) throws UnsupportedEncodingException {
        content = URLDecoder.decode(content,"utf-8");
        return URLDecoder.decode(content,"utf-8");
    }

    public static String transform2utf8(String content) throws UnsupportedEncodingException {
        content = new String(content.getBytes("iso-8859-1"), "utf-8");
        return content;
    }
}
