package com.mxy.module.compare.object;

import com.google.common.base.Preconditions;
import com.google.common.collect.Maps;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import net.sf.cglib.beans.BeanMap;
import org.apache.commons.collections4.CollectionUtils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.BiFunction;

/**
 * ###比较两个对象不同的属性###
 * 注意：入参必须是 Object 或者 Map ,如果是对象请重写equals 与 hashcode
 * @param <S> 源对象
 * @param <T> 目标对象
 * @param <R> 返回对象
 * @author maxy
 */
@Slf4j
@Getter
@Setter
public abstract class AbstractEqualsProperties<S, T, R> {

    private Collection collection = null;

    private S source;

    private T target;

    private R returnValue;

    protected AbstractEqualsProperties(S source, T target) {
        this.source = source;
        this.target = target;
    }

    protected AbstractEqualsProperties(S source, T target, Collection collection) {
        this.source = source;
        this.target = target;
        this.collection = collection;
    }

    protected void baseCheck() {
        Preconditions.checkArgument(source != null);
        Preconditions.checkArgument(target != null);
    }

    protected void beforeProcess() {

    }

    protected void finallyProcess() {

    }

    protected Map<String, Object> toMap(Object obj) {
        if (obj instanceof Map) {
            return (Map) obj;
        }
        Map<String, Object> map = Maps.newHashMap();
        BeanMap beanMap = BeanMap.create(obj);
        for (Object key : beanMap.keySet()) {
            if (CollectionUtils.isEmpty(collection)) {
                map.put(String.valueOf(key), beanMap.get(key));
            } else if (collection.contains(key)) {
                map.put(String.valueOf(key), beanMap.get(key));
            }
        }
        return map;
    }

    protected boolean isEquals(Map<String, Object> sourceMap, Map<String, Object> targetMap) {
        return sourceMap.equals(targetMap);
    }

    public R execute() {
        //check param
        try {
            baseCheck();
        } catch (IllegalArgumentException e) {
            log.error("execute#baseCheck throw IllegalArgumentException.", e);
            return returnValue;
        }
        try {
            beforeProcess();
            //analysis
            Map<String, Object> sourceMap = toMap(source);
            Map<String, Object> targetMap = toMap(target);
            log.info("execute#toMap \n sourceMap = {} \n targetMap = {}.", sourceMap, targetMap);
            //is equals
            if (isEquals(sourceMap, targetMap)) {
                log.warn("isEquals#not found diff property \n source = {} \n target = {}.", sourceMap, targetMap);
                return returnValue;
            }
            //main logic
            final Collection<String> diffProperties = findMatchProperty.apply(sourceMap, targetMap);
            //after process
            return afterProcess(diffProperties);
        } catch (Exception e) {
            log.error("execute#baseCheck throw Exception.", e);
        } finally {
            finallyProcess();
        }
        return returnValue;
    }

    protected R afterProcess(Collection<String> diffProperties) {
        return (R) diffProperties;
    }

    protected BiFunction<Map<String, Object>, Map<String, Object>, Collection<String>> findMatchProperty = (s, t) -> {
        List<String> result = new ArrayList();
        s.keySet().forEach(c -> {
            if (!Objects.deepEquals(s.get(c), t.get(c))) {
                result.add(c);
            }
        });
        return result;
    };

}
