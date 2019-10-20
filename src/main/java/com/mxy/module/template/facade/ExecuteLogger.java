package com.mxy.module.template.facade;


import lombok.Getter;
import lombok.Setter;
import org.slf4j.Logger;
/**
 * @author maxy26
 */
@Getter
@Setter
public class ExecuteLogger<T> {

    private Logger logger;

    private String methodName;

    private T request;

    public ExecuteLogger(Logger logger, String methodName, T request) {
        this.logger = logger;
        this.methodName = methodName;
        this.request = request;
    }


    public void startLog() {
        this.logger.info("{}_start#param[{}]", methodName, request);
    }

    @SuppressWarnings("rawtypes")
    public void endLog(Response response, long time) {
        this.logger.info("{}_end#cost:{}#response:[{}]", methodName, time, response);
    }

    public void printLog(Exception e) {
        this.logger.error(String.format("invoke %s error! param[%s]", methodName, request), e);
    }

    public void printMsgLog(Exception e) {
        this.logger.error(String.format("invoke %s error! param:[%s]", methodName, request), e.getMessage());
    }

    public static <T> ExecuteLogger<T> newInstance(Logger logger, String methodName, T request) {
        return new ExecuteLogger<T>(logger, methodName, request);
    }

}
