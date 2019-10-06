package com.mxy.module.cache.local.cache;

import lombok.AllArgsConstructor;
import lombok.Data;
import net.jodah.expiringmap.ExpirationPolicy;
import net.jodah.expiringmap.ExpiringMap;

import java.util.Map;
import java.util.concurrent.TimeUnit;

public class ExpiringMapSample {

    private static ExpiringMapSample sample;

    private ExpiringMapSample() {
    }

    public static ExpiringMapSample getInstance() {
        if (sample != null) return sample;

        sample = new ExpiringMapSample();
        return sample;
    }

    public void option(String index) throws InterruptedException {
        switch (index) {
            case "maxSave":
                //设置最大存储
                maxSave();
                break;
            case "createProtocol":
                //创建时间
                createProtocol();
                break;
            case "accessProtocol":
                //访问协议
                accessProtocol();
                break;
            case "simpleMapSetTime":
                //为单独KV设置时间
                simpleMapSetTime();
                break;
            case "exprieListener":
                //监听过期数据
                exprieListener();
                break;
            case "lazyLoading":
                //懒加载
                lazyLoading();
                break;
            default:
                break;
        }

        return;
    }

    /**
     * 监听过期
     *
     * @throws InterruptedException
     */
    private void exprieListener() throws InterruptedException {
        ExpiringMap<String, String> map = ExpiringMap.builder().variableExpiration()
                                                     .expirationListener((thekey, thevalue) ->
                                                             System.out.println("key:" + thekey + "过期")
                                                     ).build();
        map.put("key", "value", 2000, TimeUnit.MILLISECONDS);
        Thread.sleep(2001);
        System.out.println("key:" + map.get("key"));
    }

    /**
     * 为每个map设置时间
     */
    private void simpleMapSetTime() throws InterruptedException {

        ExpiringMap<String, String> map = ExpiringMap.builder().variableExpiration()
                                                     .expirationPolicy(ExpirationPolicy.CREATED)
                                                     .build();
        map.put("key1", "value1", ExpirationPolicy.ACCESSED, 2000, TimeUnit.MILLISECONDS);
        map.put("key2", "value2", 3000, TimeUnit.MILLISECONDS);
        Thread.sleep(2001);
        System.out.println("key1:" + map.get("key1"));
        System.out.println("key2:" + map.get("key2"));
        Thread.sleep(3001);
        System.out.println("key2:" + map.get("key2"));
    }

    /**
     * ExpirationPolicy.CREATED：在每次更新元素时，过期时间同时清零。
     * 在过期时间内调用map.put(),map.replace()更新操作后：
     */
    private void createProtocol() throws InterruptedException {

        ExpiringMap<String, String> map = ExpiringMap.builder().expiration(5000, TimeUnit.MILLISECONDS)
                                                     .expirationPolicy(ExpirationPolicy.CREATED)
                                                     .build();
        map.put("key", "value");
        System.out.println(map.get("key"));
        Thread.sleep(4000);
        map.put("key", "value2");
        System.out.println("更新map：" + map.get("key"));
        Thread.sleep(1001);
        System.out.println(map.get("key"));
    }

    /**
     * ExpirationPolicy.ACCESSED：在每次访问元素时，过期时间同时清零。
     */
    private void accessProtocol() throws InterruptedException {
        ExpiringMap<String, String> map = ExpiringMap.builder().expiration(5000, TimeUnit.MILLISECONDS)
                                                     .expirationPolicy(ExpirationPolicy.ACCESSED)
                                                     .build();
        map.put("key", "value");
        System.out.println(map.get("key"));
        Thread.sleep(4000);
        System.out.println("访问map：" + map.get("key"));
        Thread.sleep(1001);
        System.out.println(map.get("key"));
    }

    /**
     * 设置最大数量
     */
    private void maxSave() {
        ExpiringMap<String, String> map = ExpiringMap.builder()
                                                     .maxSize(2)
                                                     .build();
        map.put("key1", "value1");
        map.put("key2", "value2");
        map.put("key3", "value3");
        System.out.println(map.get("key1"));
        System.out.println(map.get("key2"));
        System.out.println(map.get("key3"));
    }

    /**
     * 懒加载map
     */
    private void lazyLoading() throws InterruptedException {
        Map<String, User> map = ExpiringMap.builder()
                                           .expiration(5000, TimeUnit.MILLISECONDS)
                                           .expirationListener((theKey, theValue) ->
                                                   System.out.println("过期key:" + theKey)
                                           )
                                           .entryLoader(u -> new User("mxy", "123"))
                                           .build();
        System.out.println(map.get("12"));
        Thread.sleep(5001);
    }

    @Data
    @AllArgsConstructor
    private class User {
        private String username;
        private String password;
    }


}
