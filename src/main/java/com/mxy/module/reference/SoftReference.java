package com.mxy.module.reference;

import com.google.common.base.Preconditions;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

/**
 * 引用网上的结论:SoftReference 于 WeakReference 的特性基本一致， 最大的区别在于 SoftReference 会尽可能长的保留引用直到 JVM 内存不足时才会被回收(虚拟机保证),
 * 这一特性使得 SoftReference 非常适合缓存应用
 * 现实却是：如果你的进程所占的内存不是满到要抛 OutOfMemoryError 的程度，JVM 根本不清理 SoftReference 占用的内存。
 * 以下code已经论证这个结论
 * JVM参数 -Xmx10m -Xms10m
 */
@Slf4j
public class SoftReference {

    public static void invoke() throws IOException, InterruptedException {
        Object referent = new Object();
        java.lang.ref.SoftReference<Object> softRerference = new java.lang.ref.SoftReference<Object>(referent);
        log.info("softRerference.get() = {} ", (softRerference.get()));
        Preconditions.checkNotNull(softRerference.get());
        referent = null;
        long maxMemory = Runtime.getRuntime().totalMemory();
        long currentUse = maxMemory - Runtime.getRuntime().freeMemory();
        log.info("当前内存已经使用{}k", currentUse / 1024);
        try {
            Byte[] bytes = new Byte[((1024 * 1024) + (540 * 1024))];
        } catch (Throwable e) {
            log.error(e.getMessage());
        }
        long lastUse = maxMemory - Runtime.getRuntime().freeMemory();
        log.info("加大内存空间后 总共{}k，已经使用{}k. softReference = {} ", maxMemory / 1024, (lastUse) / 1024, softRerference.get());
        //启动GC
        System.gc();
        //停留5秒
        Thread.sleep(5000);
        log.info("soft references 只有在 jvm OutOfMemory 之后才会被回收 . softReference = {} ", softRerference.get());
        Preconditions.checkNotNull(softRerference.get());

    }

}
