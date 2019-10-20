package com.mxy.module.template.facade;
/**
 * @author maxy26
 */
public enum ExceptionEnum {
    // BIZ ERROR
    BIZ_ERROR("1000", "business handle exception"),
    // SYS ERROR
    SYS_ERROR("2000", "system error "),
    // Parameter ERROR
    PARAM_ERROR("3000", "parameter error."),
    // Db ERROR
    DB_ERROR("4000", "Db error."),
    // Dependency Service Error
    CLIENT_AGENT_ERROR("5000", "Call the dependency service error."),
    ;

    private String code;
    private String message;

    ExceptionEnum(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
