package com.mxy.module.template.trace;


import org.junit.Test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class LoggerTraceTest {

    Process process = new ProcessImpl();

    @Test
    public void test1() throws InterruptedException {
        ExecutorService service = Executors.newFixedThreadPool(10);
        for (int i = 0; i < 5; i++) {
            service.execute(() -> {
                new FacadeTemplate<String>() {
                    @Override
                    protected void checkParams() {
                        getLoggerTrace().info("start check param");
                    }
                    @Override
                    protected String process() {
                        process.print();
                        return "hello world!!";
                    }
                }.execute();
            });
        }
        service.awaitTermination(1, TimeUnit.SECONDS);
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
                process.print();
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
