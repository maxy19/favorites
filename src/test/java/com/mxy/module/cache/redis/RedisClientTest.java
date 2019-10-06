package com.mxy.module.cache.redis;

import org.junit.Test;

public class RedisClientTest {

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
    public void pipeline() {
        RedisClient.pipeline();
        RedisClient.getJedisPool().close();
    }
}
