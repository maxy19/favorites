package com.mxy.module.hystrix;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

@Slf4j
public class HystrixTest {

    ExecutorService executorService = Executors.newFixedThreadPool(10);

    @Test
    public void testThreadPool() throws InterruptedException {
        for (int i = 0; i < 5; i++) {
            executorService.execute(() -> {
                long start = System.currentTimeMillis();
                log.info("result===>"+new HystrixCommandDemo("mxy").useThreadPool()+"===>time:"+(System.currentTimeMillis() - start));
            });
        }
        executorService.awaitTermination(10, TimeUnit.SECONDS);
    }

    @Test
    public void testTimeout() throws InterruptedException {
        for (int i = 0; i < 5; i++) {
            executorService.execute(() -> {
                long start = System.currentTimeMillis();
                log.info("result===>"+new HystrixCommandDemo("mxy").timeout()+"===>time:"+(System.currentTimeMillis() - start));
            });
        }
        executorService.awaitTermination(10, TimeUnit.SECONDS);
    }

    @Test
    public void testUseSemaphore() throws InterruptedException {
        for (int i = 0; i < 5; i++) {
            executorService.execute(() -> {
                long start = System.currentTimeMillis();
                log.info("result===>"+new HystrixCommandDemo("mxy").useSemaphore()+"===>time:"+(System.currentTimeMillis() - start));
            });
        }
        executorService.awaitTermination(10, TimeUnit.SECONDS);
    }

}
