package com.xdnote.ab.activity.biz;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FilenameFilter;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.xdnote.ab.util.ConfigManager;
import com.xdnote.ab.util.M;
/**
 * @author xdnote.com
 * @since 1.0
 * <pre>
 *   日志处理类
 * </pre>
 * */
public class HistoryUtil {
    /**
     * 日志实例化列表
     * */
    private AbUtilBean[] beans;

    /**
     * 日志存放目录
     * */
    private File dir = new File(ConfigManager.getValue(M.T_LOG_PATH));
    /**
     * 日志文件列表
     * */
    private File[] files;
    /**
     * 日志Bean的类方法，可以使用SET方法注入
     * */
    private Method[] runtime = null;

    /**
     * <pre>
     * public HistoryUtil();
     * 无参构造。自动取软件配置里面的信息进行读取
     * </pre>
     * */
    public HistoryUtil() {
        if (dir.exists() && dir.isDirectory()) {
                /**
                 * @author xdnote.com
                 * <pre>
                 * 内部类，过滤规则
                 * </pre>
                 * */
                class LogFiter implements FilenameFilter {

                @Override
                public boolean accept(final File file,  final String str) {
                    if (str.endsWith(".log")) {
                        return true;
                    }
                    return false;
                }
             }
            this.files = dir.listFiles(new LogFiter());
            this.beans = new AbUtilBean[this.files.length];
        }
    }
    /**
     * @param index 文件位置
     * @return File 文件体
     * 获取指定位置的文件
     * */
    public final File getFile(final int index) {
        return this.files[index];
    }
    /**
     * 获得日志存放目录下日志的数量
     * @return int 日志数量
     * */
    public final int getHistoryCount() {
        return this.files.length;
    }
    /**
     * 获取指定位置的日志
     * @param index 日志的位置
     * @return AbUtilBean 日志解析后的数据Bean
     * */
    public final AbUtilBean getResult(final int index) {
        if (this.beans[index] == null) {
            this.beans[index] = this.parse(index);
        }
        return this.beans[index];
    }

    /**
     * 解析单个日志
     * @param index 日志的位置
     * @return AbUtilBean 日志解析后的数据Bean
     * */
    private  AbUtilBean parse(final int index) {
        AbUtilBean bean = new AbUtilBean();
        this.runtime = bean.getClass().getDeclaredMethods();
        File log = this.files[index];
        try {
            BufferedReader bf = new BufferedReader(new FileReader(log));
            String str = bf.readLine();
            while (str != null) {
                this.parseLine(str, bean);
                str = bf.readLine();
            }
            bf.close();
        } catch (FileNotFoundException e) {
            System.out.println("没有日志文件");
        } catch (IOException e) {
            System.out.println("读取错误");
        }
        return bean;
    }

    /**
     * 解析一行日志
     * @param str 一行的文本内容
     * @param bean 日志对象
     * @return boolean 是否解析成功
     * */
    private boolean parseLine(final String str, AbUtilBean bean) {
        if (str.trim().equals("")) {
            return false;
        }
        String[] pair = str.split(":");
        if (pair.length == 2) {
            for (Method method:this.runtime) {
                if (method.isAnnotationPresent(SetField.class)) {
                    SetField sf = method.getAnnotation(SetField.class);
                    if (sf.name().equals(pair[0])) {
                        try {
                            method.invoke(bean, pair[1].trim());
                        } catch (IllegalArgumentException e) {
                            e.printStackTrace();
                        } catch (IllegalAccessException e) {
                            e.printStackTrace();
                        } catch (InvocationTargetException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        } else {
            Pattern p = Pattern.compile("\\s*(\\d+%)\\s+(.+)");
            Matcher m = p.matcher(str);
            if (m.find()) {
                bean.setResponseTime(m.group(1), m.group(2));
            }
        }
        return true;
    }


}
