package com.mxy.module.cache.redis;

import com.mxy.module.cache.redission.RedissionConfig;
import com.mxy.module.cache.redission.RedissionLockService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;
import org.redisson.api.RedissonClient;

@Slf4j
public class RedissionTest {

    private RedissionLockService redissionLock = new RedissionLockService();
    RedissonClient redissonClient = null;

    @Before
    public void before() {
        redissonClient = RedissionConfig.builder.redisson();
    }

    @Test
    public void testRedissionLock() {
        String lock = redissionLock.Lock("a", redissonClient);
        System.out.println(lock);
    }
}
