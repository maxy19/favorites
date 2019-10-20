package com.mxy.module.template.service;

import com.mxy.module.template.facade.BusinessException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.lang.reflect.Array;
import java.util.Collection;
import java.util.Map;


/**
 * @author maxy26
 */
@Slf4j
public abstract class BizTemplate<T> {

    private String bizName = "";

    public BizTemplate(String bizName) {
        this.bizName = bizName;
    }

    protected abstract void checkParams();

    protected abstract T process();

    protected void afterProcess() {
    }

    public T execute() {
        log.info("execute {} business start.",bizName);
        final long start = System.currentTimeMillis();
        final T t = doExecute();
        final long spendTime = System.currentTimeMillis() - start;
        log.info("execute {} business end and spend time {} ms.", bizName, spendTime);
        return t;
    }

    private T doExecute() {
        try {
            checkParams();
        } catch (IllegalArgumentException e) {
            if (log.isDebugEnabled()) {
                log.debug("validate fail.", e);
            } else {
                log.warn("validate fail ," + e.getMessage());
            }
            throw new IllegalArgumentException(e.getMessage(), e);
        }
        try {
            T result = process();
            return result;
        } catch (BusinessException e) {
            log.error("find the business exception in the {} process .", bizName, e);
            throw e;
        } catch (Exception e) {
            log.error("find the exception in the {} process .", bizName, e);
            throw e;
        } finally {
            afterProcess();
        }
    }

    protected void validate(Object obj, String errMsg) {

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
