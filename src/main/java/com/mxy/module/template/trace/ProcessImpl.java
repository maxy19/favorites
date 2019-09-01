package com.mxy.module.template.trace;

public class ProcessImpl implements Process {

    private LoggerTrace loggerTrace = new LoggerTrace(FacadeTemplate.class,"process");
    @Override
    public void print() {
        loggerTrace.info("process.....");
    }
}
