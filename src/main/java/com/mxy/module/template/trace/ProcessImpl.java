package com.mxy.module.template.trace;

public class ProcessImpl implements Process {

    private TraceLogger tLogger = new TraceLogger(ProcessImpl.class,"Process");

    @Override
    public void print() {
        tLogger.info("process.....");
    }
}
