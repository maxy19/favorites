package com.mxy.module.template.trace;

import org.apache.commons.lang3.RandomUtils;

public class ProcessImpl implements Process {

    private TraceLogger tLogger = new TraceLogger(ProcessImpl.class);

    @Override
    public void print() {
        tLogger.info("print-->{}", RandomUtils.nextInt());
    }
}
