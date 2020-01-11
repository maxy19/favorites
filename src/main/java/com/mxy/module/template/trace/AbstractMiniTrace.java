package com.mxy.module.template.trace;

import org.apache.commons.lang3.StringUtils;

import java.lang.reflect.Array;
import java.util.Collection;
import java.util.Map;

/**
 * miniTrace工具使用模板模式结合Trace输出
 *
 * @param <T>
 */
public abstract class AbstractMiniTrace<T> {

    protected TraceLogger tLogger;

    protected void checkParams() {}

    public AbstractMiniTrace() {}

    public T execute(String bizName, boolean nestedThread) {
        initTraceLogger(bizName, nestedThread);
        return doExecute();
    }

    public T execute(String bizName) {
        initTraceLogger(bizName,false);
        return doExecute();
    }

    protected T doExecute() {
        try {
            checkParams();
        } catch (IllegalArgumentException e) {
            tLogger.error("catch IllegalArgumentException.", e);
            return null;
        }
        try {
            return process();
        } catch (Exception e) {
            tLogger.error("catch exception.", e);
            return null;
        } finally {
            afterProcess();
            TraceLogger.remove();
        }
    }

    protected void initTraceLogger(String bizName, boolean nestedThread) {
        tLogger = new TraceLogger(AbstractMiniTrace.class, bizName, nestedThread);
    }

    protected abstract T process();


    protected void afterProcess() {

    }

    protected void validateParam(Object obj, String errMsg) {

        if (obj == null) {
            throw new IllegalArgumentException(errMsg);
        } else if (obj instanceof String && StringUtils.isBlank((String) obj)) {
            throw new IllegalArgumentException(errMsg);
        } else if (obj.getClass().isArray() && Array.getLength(obj) == 0) {
            throw new IllegalArgumentException(errMsg);
        } else if (obj instanceof Collection && ((Collection<?>) obj).isEmpty()) {
            throw new IllegalArgumentException(errMsg);
        } else if (obj instanceof Map && ((Map<?, ?>) obj).isEmpty()) {
            throw new IllegalArgumentException(errMsg);
        }
    }
}
