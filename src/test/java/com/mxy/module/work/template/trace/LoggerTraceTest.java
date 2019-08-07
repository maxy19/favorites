package com.mxy.module.work.template.trace;


import org.junit.Test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class LoggerTraceTest {

    @Test
    public void test1() throws InterruptedException {
        ExecutorService service = Executors.newFixedThreadPool(10);
        for (int i = 0; i < 10; i++) {
            service.execute(() -> {
                LoggerTrace loggerTrace = new LoggerTrace(LoggerTraceTest.class, "order");
                loggerTrace.info(Thread.currentThread().getName() + "--test print-->");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }
        service.awaitTermination(10, TimeUnit.SECONDS);
    }

    @Test
    public void test2() {
         new FacadeTemplate<String>() {
            @Override
            protected void checkParams() {
               getLoggerTrace().info("start check param");
            }
            @Override
            protected String process() {
                return "hello world!!";
            }
        }.execute();
    }
    @Test
    public void test3() {
        ExecutorService service = Executors.newFixedThreadPool(10);
        for (int i = 0; i <100 ; i++) {
            service.execute(new Runnable() {
                @Override
                public void run() {
                    System.out.println(Thread.currentThread().getId()+"-"+System.nanoTime());
                }
            });
        }
    }





}
