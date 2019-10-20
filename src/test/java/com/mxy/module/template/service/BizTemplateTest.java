package com.mxy.module.template.service;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

@Slf4j
public class BizTemplateTest {

    @Test
    public void execute() {

        new BizTemplate<Void>("order") {
            @Override
            protected void checkParams() {
                log.info("参数校验1");
                log.info("参数校验2");
            }

            @Override
            protected Void process() {
                log.info("执行方法内容第一步!");
                log.info("执行方法内容第二步!");
                return null;
            }
        }.execute();
    }
}