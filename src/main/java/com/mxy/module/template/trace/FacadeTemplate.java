package com.mxy.module.template.trace;

import com.google.common.base.Stopwatch;
import org.apache.commons.lang3.StringUtils;

import java.util.Optional;
import java.util.concurrent.TimeUnit;

/**
 * miniTrace工具使用模板模式结合Trace输出
 * @param <T>
 */
public abstract class FacadeTemplate<T> {

    private LoggerTrace loggerTrace = new LoggerTrace(FacadeTemplate.class,"order");

    public LoggerTrace getLoggerTrace() {
        return loggerTrace;
    }

    public void setLoggerTrace(LoggerTrace loggerTrace) {
        this.loggerTrace = loggerTrace;
    }

    private String monitorKey;

    private String className;

    private String methodName;

    protected abstract void checkParams();

    public T execute() {
        initmonitorKey();
        return doExecute();
    }


    protected T doExecute() {
        try {
            checkParams();
        } catch (IllegalArgumentException e) {
            loggerTrace.error("catch exception", e);
            return null;
        }
        try {
            return process();
        } catch (Exception e) {
            loggerTrace.error("catch exception", e);
            return null;
        } finally {
            afterProcess();
            LoggerTrace.getThreadLocal().remove();
        }
    }

    protected abstract T process();


    protected void afterProcess() {

    }

    private void initmonitorKey() {
        boolean anonymous = this.getClass().isAnonymousClass();
        Stopwatch stopwatch = Stopwatch.createStarted();
        try {
            if (anonymous) {
                StackTraceElement[] array = Thread.currentThread().getStackTrace();
                if (array.length > 2) {
                    StackTraceElement stack = array[3];
                    String className = stack.getClassName();
                    className = className.substring(className.lastIndexOf(".") + 1);
                    this.methodName = stack.getMethodName();
                    this.className = className;
                }
            } else {
                this.className = Optional.ofNullable(this.className).orElse(this.getClass().getSimpleName());
                this.methodName = Optional.ofNullable(this.methodName).orElse("execute");
            }

        } catch (Throwable e) {
            loggerTrace.error("initKey error:{}", e.getMessage(), e);
        }

        if (StringUtils.isBlank(className)) {
            this.className = "UnknowClassName";
        }
        if (StringUtils.isBlank(methodName)) {
            this.methodName = "UnknowMethodName";
        }
        this.monitorKey = className + "." + methodName;
        long elapsed = stopwatch.elapsed(TimeUnit.MILLISECONDS);
        if (elapsed > 1) {
            loggerTrace.info("monitoryKey:{}, elapsed:{}", monitorKey, elapsed);
        }
    }

}
