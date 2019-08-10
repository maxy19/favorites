package com.mxy.module.reference;

import com.google.common.base.Preconditions;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;
import java.util.WeakHashMap;

/**
 * WeakReference， 顾名思义, 是一个弱引用, 当所引用的对象在 JVM 内不再有强引用时, GC 后 weak reference 将会被自动回收
 */
@Slf4j
public class WeakReference {


    public static void invoke() {
        Object referent = new Object();
        java.lang.ref.WeakReference<Object> weakRerference = new java.lang.ref.WeakReference(referent);

        log.info("referent == weakRerference.get() ? {} ",referent == weakRerference.get());
        Preconditions.checkState(referent == weakRerference.get());
        referent = null;
        System.gc();
        /**
         * 一旦没有指向 referent 的强引用, weak reference 在 GC 后会被自动回收
         */
        log.info("一旦没有指向 referent 的强引用, weak reference 在 GC 后会被自动回收 weakRerference.get = {} ",weakRerference.get());
        Preconditions.checkNotNull(weakRerference.get());
    }

    public static void invoke2() throws InterruptedException {
        Map<Object, Object> weakHashMap = new WeakHashMap<Object, Object>();
        Object key = new Object();
        Object value = new Object();
        weakHashMap.put(key, value);
        Preconditions.checkState(weakHashMap.containsValue(value));
        key = null;
        System.gc();
        /**
         * 等待无效 entries 进入 ReferenceQueue 以便下一次调用 getTable 时被清理
         */
        Thread.sleep(1000);
        /**
         * 一旦没有指向 key 的强引用, WeakHashMap 在 GC 后将自动删除相关的 entry
         */
        log.info("一旦没有指向 key 的强引用, WeakHashMap 在 GC 后将自动删除相关的 entry . weakHashMap.containsValue = {} ",(  weakHashMap.containsKey(value)));
        Preconditions.checkState(weakHashMap.containsValue(value));
    }
}

