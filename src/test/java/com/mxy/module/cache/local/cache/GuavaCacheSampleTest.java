package com.mxy.module.cache.local.cache;

import com.mxy.module.cache.local.cache.GuavaCacheSample;
import org.junit.Test;

public class GuavaCacheSampleTest {

    @Test
    public void maxSave() throws InterruptedException {
        GuavaCacheSample.getInstance().option("maxSave");
    }

    @Test
    public void weakValues() throws InterruptedException {
        GuavaCacheSample.getInstance().option("weakValues");
    }

    @Test
    public void expireAccess() throws InterruptedException {
        GuavaCacheSample.getInstance().option("expireAccess");
    }

    @Test
    public void expireTime() throws InterruptedException {
        GuavaCacheSample.getInstance().option("expireTime");
    }

    @Test
    public void addListener() throws InterruptedException {
        GuavaCacheSample.getInstance().option("addListener");
    }

    @Test
    public void recordStats() throws InterruptedException {
        GuavaCacheSample.getInstance().option("recordStats");
    }


}