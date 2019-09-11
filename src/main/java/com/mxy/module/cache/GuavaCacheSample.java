package com.mxy.module.cache;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.RemovalListener;
import com.google.common.cache.RemovalNotification;

import java.util.concurrent.TimeUnit;

public class GuavaCacheSample {

    private static GuavaCacheSample sample;

    private GuavaCacheSample() {
    }

    public static GuavaCacheSample getInstance() {
        if(sample!=null) return sample;

        sample = new GuavaCacheSample();
        return sample;
    }

    public void option(String index) throws InterruptedException {
        switch (index) {
            case "maxSave":
                //设置最大存储
                maxSave();
                break;
            case "expireTime":
                //过期时间
                expireTime();
                break;
            case "expireAccess":
                //每次访问增加一秒
                expireAccess();
                break;
            case "weakValues":
                //弱引用
                weakValues();
                break;
            case "addListener":
                //增加监听
                addListener();
                break;
            case "recordStats":
                //获取统计信息
                recordStats();
                break;
            default:
                break;
        }

        return;
    }

    private void recordStats() {
        Cache<String, String> cache = CacheBuilder.newBuilder()
                                                  .maximumSize(3)
                                                  .recordStats() //开启统计信息开关
                                                  .build();
        cache.put("key1", "value1");
        cache.put("key2", "value2");
        cache.put("key3", "value3");
        cache.put("key4", "value4");

        cache.getIfPresent("key1");
        cache.getIfPresent("key2");
        cache.getIfPresent("key3");
        cache.getIfPresent("key4");
        cache.getIfPresent("key5");
        cache.getIfPresent("key6");
        //获取统计信息
        System.out.println(cache.stats());
    }

    private void weakValues() {
        Cache<String, Object> cache = CacheBuilder.newBuilder()
                                                  .maximumSize(2)
                                                  .weakValues()
                                                  .build();
        Object value = new Object();
        cache.put("key1", value);
        //原对象不再有强引用
        value = new Object();
        System.gc();
        System.out.println(cache.getIfPresent("key1"));

    }

    private void expireAccess() throws InterruptedException {
        Cache<String, String> cache = CacheBuilder.newBuilder()
                                                  .maximumSize(2)
                                                  .expireAfterAccess(3, TimeUnit.SECONDS)
                                                  .build();
        cache.put("key1", "value1");
        int time = 1;
        int toggle = 1;
        while (toggle < 5) {
            Thread.sleep(time * 1000);
            toggle++;
            System.out.println("睡眠" + time++ + "秒后取到key1的值为：" + cache.getIfPresent("key1"));
        }
    }

    private void expireTime() throws InterruptedException {
        Cache<String, String> cache = CacheBuilder.newBuilder()
                                                  .maximumSize(2)
                                                  .expireAfterWrite(3, TimeUnit.SECONDS)
                                                  .build();
        cache.put("key1", "value1");
        int time = 1;
        while (true) {
            System.out.println("第" + time++ + "次取到key1的值为：" + cache.getIfPresent("key1"));
            Thread.sleep(1000);
        }
    }

    private void maxSave() {
        Cache<String, String> cache = CacheBuilder.newBuilder()
                                                  .maximumSize(2)
                                                  .build();
        cache.put("key1", "value1");
        cache.put("key2", "value2");
        cache.put("key3", "value3");
        System.out.println("第一个值：" + cache.getIfPresent("key1"));
        System.out.println("第二个值：" + cache.getIfPresent("key2"));
        System.out.println("第三个值：" + cache.getIfPresent("key3"));
    }

    private void addListener() {
        RemovalListener<String, String> listener = new RemovalListener<String, String>() {
            @Override
            public void onRemoval(RemovalNotification<String, String> notification) {
                System.out.println("[" + notification.getKey() + ":" + notification.getValue() + "] is removed!");
            }
        };
        Cache<String, String> cache = CacheBuilder.newBuilder()
                                                  .maximumSize(3)
                                                  .removalListener(listener)
                                                  .build();
        Object value = new Object();
        cache.put("key1", "value1");
        cache.put("key2", "value2");
        cache.put("key3", "value3");
        cache.put("key4", "value3");
        cache.put("key5", "value3");
        cache.put("key6", "value3");
        cache.put("key7", "value3");
        cache.put("key8", "value3");
    }


    public void expiringMapCache(String key, String value) {


    }


}
