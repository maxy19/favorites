package com.mxy.module.template.facade;

import lombok.Getter;
/**
 * @author maxy26
 */
@Getter
public class BusinessException extends RuntimeException {

    private final String errorCode;


    private final transient Object errorData;

    public BusinessException(String errorCode, String message, Throwable cause) {
        super(message, cause);
        this.errorCode = errorCode;
        this.errorData = null;
    }

    public BusinessException(ExceptionEnum exceptionEnum, Throwable cause) {
        super(exceptionEnum.getMessage(), cause);
        this.errorCode = exceptionEnum.getCode();
        this.errorData = null;
    }

    public BusinessException(String errorCode, Object errorData, Throwable cause) {
        super("", cause);
        this.errorCode = errorCode;
        this.errorData = errorData;
    }

    public BusinessException(String errorCode, String message) {
        super(message);
        this.errorCode = errorCode;
        this.errorData = null;
    }
    
    public BusinessException(String errorCode, Object errorData) {
        super("");
        this.errorCode = errorCode;
        this.errorData = errorData;
    }

}
