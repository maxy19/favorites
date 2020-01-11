package com.mxy.module.template.trace;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.Marker;

import java.util.Optional;
import java.util.concurrent.ThreadLocalRandom;

public class TraceLogger {

    private static final ThreadLocal<String> threadLocal = new ThreadLocal<>();
    private static final InheritableThreadLocal<String> inheritableThreadLocal = new InheritableThreadLocal<>();

    private Logger tLogger;
    //多线程中是否内嵌多线程
    private boolean isNestedThread = false;

    public TraceLogger(Class clazz) {
        tLogger = LoggerFactory.getLogger(clazz);
    }

    public TraceLogger(Class clazz, String bizName) {
        tLogger = LoggerFactory.getLogger(clazz);
        if (threadLocal.get() == null) {
            threadLocal.set(generateFormatTrace(bizName));
        }
    }

    public TraceLogger(Class clazz, String bizName, boolean isNestedThread) {
        this(clazz, bizName);
        if (isNestedThread) {
            inheritableThreadLocal.set(threadLocal.get());
        }
    }

    protected String generateFormatTrace(String bizName) {
        return new StringBuffer()
                .append("bizName:").append(bizName)
                .append(":trance:").append(System.nanoTime())
                //ThreadLocalRandom.current 为每个线程初始化一个seed 不要设置全局变量否则会出现重复的值
                .append(ThreadLocalRandom.current().nextInt(1000))
                .toString();
    }

    public static void remove() {
        inheritableThreadLocal.remove();
        threadLocal.remove();
    }

    public void info(String format, Object... arguments) {
        tLogger.info(append(format), arguments);
    }

    public void info(Marker marker, String format, Object arg1, Object arg2) {
        tLogger.info(marker, append(format), arg1, arg2);
    }

    public void info(Marker marker, String format, Object... arguments) {
        tLogger.info(marker, append(format), arguments);
    }

    public void info(Marker marker, String msg, Throwable t) {
        tLogger.info(marker, append(msg), t);
    }

    public void error(String format, Object... arguments) {
        tLogger.error(append(format), arguments);
    }

    public void error(Marker marker, String format, Object arg1, Object arg2) {
        tLogger.error(marker, append(format), arg1, arg2);
    }

    public void error(Marker marker, String format, Object... arguments) {
        tLogger.error(marker, append(format), arguments);
    }

    public void error(Marker marker, String msg, Throwable t) {
        tLogger.info(marker, append(msg), t);
    }

    public void warn(String format, Object... arguments) {
        tLogger.warn(append(format), arguments);
    }

    public void warn(Marker marker, String format, Object arg1, Object arg2) {
        tLogger.warn(marker, append(format), arg1, arg2);
    }

    public void warn(Marker marker, String format, Object... arguments) {
        tLogger.warn(marker, append(format), arguments);
    }

    public void warn(Marker marker, String msg, Throwable t) {
        tLogger.warn(marker, append(msg), t);
    }

    private String append(String msg) {
        return new StringBuilder().append((StringUtils.isEmpty(msg) ? "" : msg))
                                  .append(" [")
                                  .append(Optional.ofNullable(inheritableThreadLocal.get()).orElse(threadLocal.get()))
                                  .append("]")
                                  .toString();
    }
}
