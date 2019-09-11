package com.mxy.module.cache;

import org.junit.Test;

public class ExpiringMapSampleTest {

    @Test
    public void maxSave() throws InterruptedException {
        ExpiringMapSample.getInstance().option("maxSave");
    }

    @Test
    public void expireTime() throws InterruptedException {
        ExpiringMapSample.getInstance().option("expireTime");
    }

    @Test
    public void simpleMapSetTime() throws InterruptedException {
        ExpiringMapSample.getInstance().option("simpleMapSetTime");
    }
    @Test
    public void exprieListener() throws InterruptedException {
        ExpiringMapSample.getInstance().option("exprieListener");
    }
    @Test
    public void createProtocol() throws InterruptedException {
        ExpiringMapSample.getInstance().option("createProtocol");
    }
    @Test
    public void accessProtocol() throws InterruptedException {
        ExpiringMapSample.getInstance().option("accessProtocol");
    }

    @Test
    public void lazyLoading() throws InterruptedException {
        ExpiringMapSample.getInstance().option("lazyLoading");
    }


}