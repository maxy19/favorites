package com.mxy.module.cache.redis.redission;

import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Slf4j
@Component
public class RedissionLockDemo {

    @Autowired
    private RedissonClient redissonClient;

    private int count = 0;

    public String Lock(String key) {
        RLock rLock = redissonClient.getLock(key);
        try {
            if (rLock.tryLock(20, 2, TimeUnit.SECONDS)) {
                log.info("获得锁线程->{}", Thread.currentThread().getName());
                log.info("获得锁" + rLock.getName());
                count++;
                Thread.sleep(10 * 1000);
                log.info("加一次后 " + count);
            }
        } catch (Exception e) {
            log.error("", e);
        } finally {
            if (rLock.isLocked()) {
                log.info("获得锁线程释放->{}", Thread.currentThread().getName());
                rLock.unlock();
            }
        }
        return "ok";
    }

}
