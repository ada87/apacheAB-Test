package com.xdnote.ab.activity.biz;


import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
/**
 * @author xdnote.com
 * <pre>
 * 注解，用于SET方法，利用反射提取后注入
 * </pre>
 * */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface SetField {
    /**
     * 映射日志的前部分字符串
     * */
    String name();

}
