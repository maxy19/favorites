package com.mxy.module.cache.redission;

import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;

import java.util.concurrent.TimeUnit;

@Slf4j
public class RedissionLockService  {

    private int count = 0;

    public String Lock(String key, RedissonClient client) {
        RLock rLock = client.getLock(key);
        try {
            //抢锁时间 得到锁后多久过期
            if (rLock.tryLock(20, 100, TimeUnit.SECONDS)) {
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
