package com.mxy.module.template.service;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

@Slf4j
public class BizTemplateTest {

    @Test
    public void executeNotParam() {

        new BizTemplate<Void,Object>("order") {
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

    @Test
    public void executeUseParam() {

        new BizTemplate<Void,Object>("order",1,2,"") {
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