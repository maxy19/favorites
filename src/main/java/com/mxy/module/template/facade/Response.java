package com.mxy.module.template.facade;


import lombok.Data;

import java.io.Serializable;
import java.util.StringJoiner;
/**
 * @author maxy26
 */
@Data
public class Response<T> implements Serializable {
    private static final long serialVersionUID = -4887606088067040646L;

    private String returnCode;


    private String returnMessage;


    private T data;

    public Response() {
    }


    public static <T> Response<T> getSuccessResponse() {
        return null;
    }

    public static <T> Response<T> getSuccessResponse (T data) {
        Response<T> response = getSuccessResponse();
        response.setData(data);
        return response;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Response.class.getSimpleName() + "[", "]")
                .add("returnCode='" + returnCode + "'")
                .add("returnMessage='" + returnMessage + "'")
                .add("data=" + data)
                .toString();
    }
}
