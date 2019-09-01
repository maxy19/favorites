package com.mxy.module.template.trace;


import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.Marker;

import java.util.concurrent.ThreadLocalRandom;

public class LoggerTrace {

    public static final ThreadLocal<String> threadLocal = new ThreadLocal<>();

    private Logger loggerFactory;

    private String bizName;

    public LoggerTrace(Class clazz, String bizName) {
        loggerFactory = LoggerFactory.getLogger(clazz);
        if (threadLocal.get() == null) {
            StringBuffer buffer = new StringBuffer();
            buffer.append(":bizName:").append(bizName)
                  .append(":trance:").append(System.nanoTime())
                  .append(ThreadLocalRandom.current().nextInt(1000));
            threadLocal.set(buffer.toString());
        }
        this.bizName = bizName;
    }

    public static ThreadLocal<String> getThreadLocal() {
        return threadLocal;
    }

    public void info(String format, Object... arguments) {
        loggerFactory.info(append(format), arguments);
    }

    public void info(Marker marker, String format, Object arg1, Object arg2) {
        loggerFactory.info(marker, append(format), arg1, arg2);
    }

    public void info(Marker marker, String format, Object... arguments) {
        loggerFactory.info(marker, append(format), arguments);
    }

    public void info(Marker marker, String msg, Throwable t) {
        loggerFactory.info(marker, append(msg), t);
    }

    public void error(String format, Object... arguments) {
        loggerFactory.error(append(format), arguments);
    }

    public void error(Marker marker, String format, Object arg1, Object arg2) {
        loggerFactory.error(marker, append(format), arg1, arg2);
    }

    public void error(Marker marker, String format, Object... arguments) {
        loggerFactory.error(marker, append(format), arguments);
    }

    public void error(Marker marker, String msg, Throwable t) {
        loggerFactory.info(marker, append(msg), t);
    }

    public void warn(String format, Object... arguments) {
        loggerFactory.warn(append(format), arguments);
    }

    public void warn(Marker marker, String format, Object arg1, Object arg2) {
        loggerFactory.warn(marker, append(format), arg1, arg2);
    }


    public void warn(Marker marker, String format, Object... arguments) {
        loggerFactory.warn(marker, append(format), arguments);
    }

    public void warn(Marker marker, String msg, Throwable t) {
        loggerFactory.warn(marker, append(msg), t);
    }

    private String append(String msg) {
        return new StringBuffer().append((StringUtils.isEmpty(msg) ? "" : msg)).append(threadLocal.get()).toString();
    }


}
