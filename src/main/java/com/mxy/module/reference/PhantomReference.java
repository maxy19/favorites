package com.mxy.module.reference;

import com.google.common.base.Preconditions;
import lombok.extern.slf4j.Slf4j;

import java.lang.ref.ReferenceQueue;

/**
 * 虚引用
 */
@Slf4j
public class PhantomReference {

    public static void invoke() {
        Object referent = new Object();
        java.lang.ref.PhantomReference<Object> phantomReference = new java.lang.ref.PhantomReference<>(referent, new ReferenceQueue<>());
        /**
         * phantom reference 的 get 方法永远返回 null
         */
        log.info("phantom reference 的 get 方法永远返回 null . phantomReference = {} ",(  phantomReference.get()));
        Preconditions.checkNotNull(phantomReference.get());
    }

}
