package org.xi.quick.usermanager.model;

public class JsonResult<T> {

    private Short code;

    private String Message;

    private T data;

    private Long systemTimeMillis;

    public JsonResult() {
        super();
    }

    public JsonResult(Short code, String message, T data, Long systemTimeMillis) {
        super();
        this.code = code;
        Message = message;
        this.data = data;
        this.systemTimeMillis = systemTimeMillis;
    }

    public Short getCode() {
        return code;
    }

    public void setCode(Short code) {
        this.code = code;
    }

    public String getMessage() {
        return Message;
    }

    public void setMessage(String message) {
        Message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public Long getSystemTimeMillis() {
        return systemTimeMillis;
    }

    public void setSystemTimeMillis(Long systemTimeMillis) {
        this.systemTimeMillis = systemTimeMillis;
    }

}
