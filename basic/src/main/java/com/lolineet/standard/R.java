package com.lolineet.standard;

//统一返回结果
public class R {

    private int code;
    private String message;
    private Object data;

    public R() {
    }

    public R(int code, String message, Object data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public static R ok() {
        return new R(200, "success", null);
    }

    public static R ok(Object data) {
        return new R(200, "success", data);
    }

    public static R error() {
        return new R(500, "error", null);
    }

    public static R error(String message) {
        return new R(500, message, null);
    }

    public static R error(int code, String message) {
        return new R(code, message, null);
    }

    public static R error(int code, String message, Object data) {
        return new R(code, message, data);
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
