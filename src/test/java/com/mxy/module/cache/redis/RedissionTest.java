package com.mxy.module.cache.redis;

import com.mxy.module.TestApplyApplication;
import com.mxy.module.cache.redis.redission.RedissionLockDemo;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Slf4j
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = TestApplyApplication.class)
public class RedissionTest {
    @Resource
    private RedissionLockDemo redissionLock;


    @Test
    public void testRedissionLock() {
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        for (int i = 0; i < 10; i++) {
            executorService.submit(() -> {
                redissionLock.Lock("lock1");
            });
        }
        while (!executorService.isTerminated()) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                log.error("", e);
            }
        }
    }
}
