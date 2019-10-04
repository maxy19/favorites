package com.mxy.module.template.trace;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.Marker;

import java.util.concurrent.ThreadLocalRandom;
public class TraceLogger {

    public static final ThreadLocal<String> threadLocal = new ThreadLocal<>();

    private Logger tLogger;

    public TraceLogger(Class clazz, String bizName) {
        tLogger = LoggerFactory.getLogger(clazz);
        if (threadLocal.get() == null) {
            threadLocal.set(generateFormatTrace(bizName));
        }
    }

    protected String generateFormatTrace(String bizName) {
        return new StringBuffer()
                  .append(":bizName:").append(bizName)
                  .append(":trance:").append(System.nanoTime())
                  .append(ThreadLocalRandom.current().nextInt(1000))
                  .toString();
    }

    public static void remove() {
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
        return new StringBuffer().append((StringUtils.isEmpty(msg) ? "" : msg))
                                 .append(threadLocal.get())
                                 .toString();
    }
}
