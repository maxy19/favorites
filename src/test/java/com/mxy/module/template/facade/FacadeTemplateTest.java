package com.mxy.module.template.facade;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
@Slf4j
public class FacadeTemplateTest {

    @Test
    public void executeProcess() {
         String id = "10";
         FacadeTemplate.execute(ExecuteLogger.newInstance(log, "executeProcess", id), () -> {
             log.info("参数校验1");
             log.info("参数校验2");
         }, (BizCallback<String>) () -> {
              log.info("执行方法内容第一步");
              log.info("执行方法内容第二步");
             return new Response<>();
         });
    }

}