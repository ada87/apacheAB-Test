package com.xdnote.ab.util;

import java.awt.Font;

/**
 * @author xdnote.com
 * <p>
 * 字符串的公用处理类
 * </p>
 * */
public class StringUtil {

    /**
     * <p>常量，12号的微软雅黑字体</p>
     * */
    public final static Font  font   =  new  Font("微软雅黑", Font.PLAIN, 12);
    /**
     * @param strs 需要拼接的字符串数组
     * @param separator 拼接时的分隔符
     * @return String 拼接结果
     * */
    public static String formatStringList(
            final String[] strs, final String separator) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0, j = strs.length; i < j; i++) {
            sb.append(strs[i]);
                if (i != (j - 1)) {
                    sb.append(separator);
                }
        }
        return sb.toString();
   }
    /**
     * @deprecated
     * <p>工具类无法构造</p>
     * */
    private StringUtil() { }
}
