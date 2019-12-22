package com.mxy.module.cache.redis;

import org.junit.Test;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.Pipeline;

import java.util.Set;

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
    public void Set() throws InterruptedException {
        JedisPool jedisPool = RedisClient.getJedisPool();
        Jedis jedis = jedisPool.getResource();
        jedis.sadd("a","a1");
        jedis.sadd("a","a2");
        jedis.sadd("a","null");
        Set<String> sets = jedis.smembers("a");
        jedis.expire("a",10);
        Thread.sleep(11000);
        sets = jedis.sunion("a");
        System.out.println("---"+sets.isEmpty());
        System.out.println(sets.remove("null"));
        System.out.println(sets);
    }
    @Test
    public void zSet() {
        Pipeline pipelined = jedis.pipelined();
        pipelined.zadd("a",5,"a5");
        pipelined.zadd("a",4,"a4");
        pipelined.zadd("a",3,"a3");
        pipelined.sync();
        System.out.println(RedisClient.zrangeWithScores("a",0,5));
        System.out.println(RedisClient.zrevrangeByScoreWithScores("a",5,0));
    }
}
