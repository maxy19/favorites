package com.mxy.module.cache.redis;

import org.apache.commons.lang3.RandomUtils;
import org.junit.Test;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.Pipeline;

import java.util.List;

public class RedisClientTest {

    JedisPool jedisPool = RedisClient.getJedisPool();
    Jedis jedis = jedisPool.getResource();

    @Test
    public void createAndConsume() {
        RedisClient.create("mxy", "1");
        RedisClient.create("mxy", "2");
        RedisClient.create("mxy", "3");
        System.out.println("bpop:" + RedisClient.bConsume("mxy", "0"));
        System.out.println("llen:" + RedisClient.getJedisPool().getResource().llen("mxy"));
        System.out.println("rpop:" + RedisClient.consume("mxy"));
        RedisClient.flushAll();
        RedisClient.getJedisPool().close();
    }

    @Test
    public void incr() throws Exception {
        RedisClient.incre("A");//1
        System.out.println(RedisClient.get("A"));//1
        RedisClient.incre("A");//2
        System.out.println(RedisClient.get("A"));//2
        RedisClient.getJedisPool().close();
    }


    @Test
    public void hash() {
        RedisClient.setKV("A", "m", "mxy");
        System.out.println(RedisClient.getKV("A", "m"));
        RedisClient.getJedisPool().close();
    }

    @Test
    public void Set() {
        Pipeline pipelined = jedis.pipelined();
        //去重
        pipelined.sadd("key", "v1");
        pipelined.sadd("key", "v1");
        pipelined.sync();
        pipelined.close();
    }

    @Test
    public void provide() throws InterruptedException {
        while (true) {
            int value = RandomUtils.nextInt();
            this.jedis.lpush("1", value + "");
            System.out.println("放队列：" + value);
            Thread.sleep(2000);
        }
    }

    @Test
    public void consumer() {
        //后面秒 直到拿到值
        while (true) {
            List<String> value = this.jedis.blpop("1", "200");
            System.out.println("出队列：" + value);
        }
    }

    @Test
    public void zSet() {
        Pipeline pipelined = jedis.pipelined();
        pipelined.zadd("key", 5, "v5");
        pipelined.zadd("key", 4, "v4");
        pipelined.zadd("key", 3, "v3");
        pipelined.sync();
        System.out.println(RedisClient.zrangeWithScores("key", 0, 5));
        System.out.println(RedisClient.zrevrangeByScoreWithScores("key", 5, 0));
        pipelined.close();
    }

    @Test
    public void getset() {
        RedisClient.getset("key", "v1");
        RedisClient.getset("key", "v2");
    }

    @Test
    public void exprieAt() {
        RedisClient.set("key", 123);
        RedisClient.expireAt("key", System.currentTimeMillis());
    }
}
