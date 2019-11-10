package com.mxy.module.cache.redis;

import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.util.ResourceBundle;

public class RedisConfingration {

    protected static JedisPool jedisPool;

    static {
        // 读取相关的配置:redis.properties
        ResourceBundle resourceBundle = ResourceBundle.getBundle("redis");

        int maxActive = Integer.parseInt(resourceBundle.getString("redis.pool.maxActive"));
        int maxIdle = Integer.parseInt(resourceBundle.getString("redis.pool.maxIdle"));
        int maxWait = Integer.parseInt(resourceBundle.getString("redis.pool.maxWait"));

        String ip = resourceBundle.getString("redis.ip");
        int port = Integer.parseInt(resourceBundle.getString("redis.port"));

        JedisPoolConfig config = new JedisPoolConfig();
        // 设置最大连接数
        config.setMaxTotal(maxActive);
        // 设置最大空闲数
        config.setMaxIdle(maxIdle);
        // 设置超时时间
        config.setMaxWaitMillis(maxWait);

        // 初始化连接池
        jedisPool = new JedisPool(config, ip, port);

    }

}
