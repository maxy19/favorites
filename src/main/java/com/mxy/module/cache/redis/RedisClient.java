package com.mxy.module.cache.redis;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.Pipeline;

import java.util.Collections;
import java.util.List;
import java.util.UUID;

@Slf4j
public class RedisClient extends RedisConfingration {

    public static JedisPool getJedisPool() {
        return jedisPool;
    }

    /**
     * 向缓存中设置字符串内容
     *
     * @param key   key
     * @param value value
     * @return
     * @throws Exception
     */
    public static boolean set(String key, String value) throws Exception {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            jedis.setex(key, 1000, value);
            return true;
        } catch (Exception e) {
            log.error("", e);
            return false;
        }
    }

    /**
     * 原子加
     *
     * @param key key
     * @return
     * @throws Exception
     */
    public static void incre(String key) throws Exception {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            jedis.incr(key);
        } catch (Exception e) {
            log.error("", e);
        }
    }

    /**
     * 向缓存中设置对象
     *
     * @param key
     * @param value
     * @return
     */
    public static boolean set(String key, Object value) {
        Jedis jedis = null;
        try {
            String objectJson = JSON.toJSONString(value);
            jedis = jedisPool.getResource();  // 从池中获取一个Jedis对象
            jedis.set(key, objectJson);
            return true;
        } catch (Exception e) {
            log.error("", e);
            return false;
        }
    }

    /**
     * 使用hash
     *
     * @param key
     * @param value
     * @return
     */
    public static boolean setKV(String key, String field, String value) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();  // 从池中获取一个Jedis对象
            jedis.hset(key, field, value);
            return true;
        } catch (Exception e) {
            log.error("", e);
            return false;
        }
    }

    public static String getKV(String key, String field) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            return jedis.hget(key, field);
        } catch (Exception e) {
            log.error("", e);
        }
        return "";
    }

    /**
     * 删除缓存中得对象，根据key
     *
     * @param key
     * @return
     */
    public static boolean del(String key) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            jedis.del(key);
            return true;
        } catch (Exception e) {
            log.error("", e);
            return false;
        }
    }

    /**
     * 根据key 获取内容
     *
     * @param key
     * @return
     */
    public static Object get(String key) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            return jedis.get(key);
        } catch (Exception e) {
            log.error("", e);
            return false;
        }
    }

    /**
     * 根据key 获取对象
     *
     * @param key
     * @return
     */
    public static <T> T get(String key, Class<T> clazz) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            String value = jedis.get(key);
            return JSON.parseObject(value, clazz);
        } catch (Exception e) {
            log.error("", e);
            return null;
        }
    }

    /**
     * pipelined 提高吞吐量
     *
     * @return
     */
    public static void pipeline() {
        Jedis jedis = null;

        try {
            jedis = jedisPool.getResource();
            Pipeline pipelined = jedis.pipelined();
            for (int j = 0; j < 10; j++) {
                pipelined.set(j + "--0", UUID.randomUUID().toString());
            }
            pipelined.sync();
        } catch (Exception e) {
            log.error("", e);
        }
    }

    /**
     * 根据key 获取对象
     *
     * @param key
     * @return
     */
    public static <T> List<T> getList(String key, Class<T> clazz) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            String value = jedis.get(key);
            return JSON.parseArray(value, clazz);
        } catch (Exception e) {
            log.error("", e);
            return null;
        }
    }

    /**
     * 生产消息 （使用Redis做异步队列么）
     *
     * @param key
     * @return
     */
    public static void create(String key, String... value) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            jedis.lpush(key, value);
        } catch (Exception e) {
            log.error("", e);
        }
    }

    /**
     * 消费消息 带有阻塞的取值（使用过Redis做异步队列么）
     *
     * @param key
     * @return
     */
    public static List<String> bConsume(String... key) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            return jedis.blpop(key);
        } catch (Exception e) {
            log.error("", e);
        }
        return Collections.emptyList();
    }

    /**
     * 消费消息 带有阻塞的取值（使用过Redis做异步队列么）
     *
     * @param key
     * @return
     */
    public static String consume(String key) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            return jedis.rpop(key);
        } catch (Exception e) {
            log.error("", e);
        }
        return "";
    }

    public static void flushAll() {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            jedis.flushAll();
        } catch (Exception e) {
            log.error("", e);
        }

    }
}
