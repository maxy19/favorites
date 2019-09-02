package com.mxy.module.compare.object;

import com.google.common.base.Preconditions;
import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;
import net.sf.cglib.beans.BeanMap;
import org.apache.commons.collections4.CollectionUtils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.BiFunction;

/**
 * ###比较两个对象不同的属性###
 *
 * @param <S> 源对象
 * @param <T> 目标对象
 */
@Slf4j
public abstract class EqualsProperties<S, T> {

    private Collection collection = null;

    private S source;

    private T target;

    public Collection getCollection() {
        return collection;
    }

    public void setCollection(Collection collection) {
        this.collection = collection;
    }

    public S getSource() {
        return source;
    }

    public void setSource(S source) {
        this.source = source;
    }

    public T getTarget() {
        return target;
    }

    public void setTarget(T target) {
        this.target = target;
    }

    protected EqualsProperties(S source, T target) {
        this.source = source;
        this.target = target;
    }

    protected EqualsProperties(S source, T target, Collection collection) {
        this.source = source;
        this.target = target;
        this.collection = collection;
    }

    protected void baseCheck() {
        Preconditions.checkArgument(source != null);
        Preconditions.checkArgument(target != null);
    }

    protected Map<String, Object> toMap(Object obj) {
        if (obj == null) {
            return Collections.emptyMap();
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

    public Collection<String> execute() {
        //check param
        try {
            baseCheck();
        } catch (IllegalArgumentException e) {
            log.error("execute#baseCheck throw IllegalArgumentException.", e);
            return Collections.emptyList();
        }
        try {
            //analysis
            Map<String, Object> sourceMap = toMap(source);
            Map<String, Object> targetMap = toMap(target);
            log.info("execute#toMap sourceMap = {} , targetMap = {}.", sourceMap, targetMap);
            //is equals
            if (isEquals(sourceMap, targetMap)) {
                return Collections.emptyList();
            }
            //find
            return findMatchProperty.apply(sourceMap, targetMap);
        } catch (Exception e) {
            log.error("execute#baseCheck throw Exception.", e);
        }
        return Collections.emptyList();
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
