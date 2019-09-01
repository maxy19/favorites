package com.mxy.module.compare.object;

import com.google.common.base.Preconditions;
import com.google.common.collect.Maps;
import net.sf.cglib.beans.BeanMap;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.function.BiConsumer;

/**
 * ###比较两个对象不同的属性###
 * @param <S> 源对象
 * @param <T> 比较对象
 * @param <C> Source与Target不同的值放入集合
 */
public abstract class EqualsProperty<S, T, C extends Set> {

    private List<Map<String, String>> result = new ArrayList<>();

    protected C collection = null;

    protected S source;

    protected T target;

    protected EqualsProperty(S source, T target) {
        this.source = source;
        this.target = target;
    }

    protected EqualsProperty(S source, T target, C collection) {
        this.source = source;
        this.target = target;
        this.collection = collection;
    }

    protected void baseCheck() {
        Preconditions.checkNotNull(source);
        Preconditions.checkNotNull(target);
    }

    protected Map<String, Object> toMap(Object obj) {
        Map<String, Object> map = Maps.newHashMap();
        if (obj != null) {
            BeanMap beanMap = BeanMap.create(obj);
            for (Object key : beanMap.keySet()) {
                map.put(String.valueOf(key), beanMap.get(key));
            }
        }
        return map;
    }

    protected boolean isEquals(Map<String, Object> sourceMap, Map<String, Object> targetMap) {
        return sourceMap.equals(targetMap);
    }

    protected synchronized List<Map<String, String>> invokeByBean() {
        //check param
        baseCheck();
        //analysis
        Map<String, Object> sourceMap = toMap(source);
        Map<String, Object> targetMap = toMap(target);
        //is equals
        if (isEquals(sourceMap, targetMap)) return Collections.emptyList();
        //loop
        findMatchProperty.accept(sourceMap, targetMap);
        return result;
    }


    protected BiConsumer<Map<String, Object>, Map<String, Object>> findMatchProperty = (s, t) -> {
        s.keySet().stream().filter(c -> (collection != null && !collection.isEmpty()) ? collection.contains(c) : true).forEach(c -> {
            Boolean isSameProperty = Objects.deepEquals(s.get(c), t.get(c));
            if (!isSameProperty) {
                //key:Bean属性名 , value:Source与Target不同的值放入集合.
                Map<String, String> collectMap = new HashMap<>();
                collectMap.put(c, String.valueOf(s.get(c)));
                result.add(collectMap);
            }
        });
    };

}
