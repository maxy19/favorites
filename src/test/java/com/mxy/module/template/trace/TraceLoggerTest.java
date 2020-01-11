package com.mxy.module.template.trace;


import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

@Slf4j
public class TraceLoggerTest {

    Process process = new ProcessImpl();

    @Test
    public void test() throws InterruptedException {
        ExecutorService service = Executors.newFixedThreadPool(10);
        for (int i = 0; i < 5; i++) {
            int inner_i = i;
            service.execute(() -> new AbstractMiniTrace<String>() {
                @Override
                protected void checkParams() {
                    tLogger.info("start check param");
                }
                @Override
                protected String process() {
                    //new Thread(() -> process.print()).start();
                    process.print();
                    return "hello world!!";
                }
            }.execute("order"));
        }
        service.awaitTermination(1, TimeUnit.SECONDS);
    }

    @Test
    public void test2() {
        new AbstractMiniTrace<String>() {
            @Override
            protected void checkParams() {
                tLogger.info("start check param");
            }

            @Override
            protected String process() {
                process.print();
                return "hello world!!";
            }
        }.execute("order");
    }

    @Test
    public void test3() throws IOException {
        //开启内嵌线程
        new Thread(() ->
                new AbstractMiniTrace<String>() {
                    @Override
                    protected String process() {
                        new Thread(()->{
                            process.print();
                        }).start();
                        return "ok";
                    }
                }.execute("order1",true)
        ).start();
        //没有开启
        new Thread(() ->
                new AbstractMiniTrace<String>() {
                    @Override
                    protected String process() {
                        process.print();
                        return "ok";
                    }
                }.execute("order2")
        ).start();

        System.in.read();
    }

}
