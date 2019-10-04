package com.mxy.module.template.trace;


import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

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
            service.execute(() -> new MiniTrace<String>() {
                @Override
                protected void initTraceLogger() {
                    //使用模板时可以更改原有trace格式
                    super.initTraceLogger();
                }
                @Override
                public String setBizName() {
                    return "Order";
                }
                @Override
                protected void checkParams() {
                    tLogger.info("start check param");
                }
                @Override
                protected String process() {
                    process.print();
                    return "hello world!!";
                }
            }.execute());
        }
        service.awaitTermination(1, TimeUnit.SECONDS);
    }

    @Test
    public void test2() {
        new MiniTrace<String>() {
            @Override
            public String setBizName() {
                return "Order";
            }
            @Override
            protected void checkParams() {
                tLogger.info("start check param");
            }
            @Override
            protected String process() {
                process.print();
                return "hello world!!";
            }
        }.execute();
    }

}
