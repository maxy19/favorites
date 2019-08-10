package com.mxy.module.reference;

import com.google.common.base.Preconditions;
import lombok.extern.slf4j.Slf4j;

/**
 * StrongReference 是 Java 的默认引用实现, 它会尽可能长时间的存活于 JVM 内， 当没有任何对象指向它时 GC 执行后将会被回收
 */
@Slf4j
public class StrongReference {

    public static void invoke() {
        Object referent = new Object();
        /**
         * 通过赋值创建 StrongReference
         */
        Object strongReference = referent;
        log.info("referent == strongReference  ? {} ",(referent == strongReference));
        Preconditions.checkState(referent == (strongReference));
        referent = null;
        System.gc();
        /**
         * StrongReference 在 GC 后不会被回收
         */
        log.info("StrongReference 在 GC 后不会被回收 strongReference = {} ",(  strongReference));
        Preconditions.checkNotNull(strongReference);

    }
}
