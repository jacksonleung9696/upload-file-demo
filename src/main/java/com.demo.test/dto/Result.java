package com.demo.test.dto;

/**
 * @author lianghuayue
 *
 * @Date 2021/3/30 23:41
 * @Version 1.0
 */

public class Result<T> {
    private Integer code;
    private String message;
    private T data;
    public Result(Integer code, String message, T data){
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public Result success(T data){
        return new Result(1, "success", data);
    }

    public static Result fail(){
        return new Result(2, "fail", null);
    }
    public Result(String message, T data){
        this.code = 1;
        this.message = message;
        this.data = data;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "Result{" +
                "code=" + code +
                ", message='" + message + '\'' +
                ", data=" + data +
                '}';
    }
}
