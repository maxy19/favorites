package com.mxy.module.hystrix;

import com.netflix.hystrix.*;
import lombok.extern.slf4j.Slf4j;

/**
 * 使用HystrixCommand封装执行方法。继承Hystrix命令基类，泛型参数即调用方法的返回参数
 */
@Slf4j
public class HystrixCommandDemo {

   private HystrixCommand.Setter setter;
   private String name;

    public HystrixCommandDemo(String name) {
        this.name = name;
        //我们可以通过andThreadPoolKey配置使用命名为ThreadPoolTest的线程池，实现与其他命名的线程池天然隔离，如果不配置andThreadPoolKey则使用withGroupKey配置来命名线程池
        setter = HystrixCommand.Setter.withGroupKey(HystrixCommandGroupKey.Factory.asKey(name + "_ThreadPoolGroup"))
                .andCommandKey(HystrixCommandKey.Factory.asKey(name + "_CommandKey"))
                .andCommandPropertiesDefaults(HystrixCommandProperties.Setter().withExecutionTimeoutInMilliseconds(3000));
    }

    public Object useThreadPool() {
        setter.andThreadPoolKey(HystrixThreadPoolKey.Factory.asKey(name+"_threadPoolKey"))
                .andThreadPoolPropertiesDefaults(HystrixThreadPoolProperties.Setter()
                        .withMaxQueueSize(10)
                        .withCoreSize(5)
                        .withMaximumSize(10));
        return getResult();
    }


    public Object useSemaphore() {
        setter = HystrixCommand.Setter.withGroupKey(HystrixCommandGroupKey.Factory.asKey(name+"_Semaphore"))
                .andCommandKey(HystrixCommandKey.Factory.asKey("Semaphore"))
                .andCommandPropertiesDefaults(HystrixCommandProperties.Setter()
                        // 设置隔离策略
                        .withExecutionIsolationStrategy(HystrixCommandProperties.ExecutionIsolationStrategy.SEMAPHORE));
       return getResult();
    }

    public Object timeout() {
        return getResult();
    }


    private Object getResult() {
        return new HystrixCommand(setter) {
            @Override
            protected Object run() throws Exception {
                log.info("run---->" + Thread.currentThread().getName());
                Thread.sleep(5000);
                return "返回正常业务结果";
            }

            @Override
            protected Object getFallback() {
                //Hystrix默认的超时时间是1秒，如果超过这个时间尚未响应，将会进入fallback代码
                log.info("getFallback---->" + Thread.currentThread().getName());
                return "返回兜底结果";
            }
        }.execute();
    }
}