package com.jason.tools.http.bean;

/**
 * Created by jason on 2018/11/26.
 */

public class JuheBaseResult<T> {

    /**
     * resultcode : 101
     * reason : 错误的请求KEY
     * result : null
     * error_code : 10001
     */

   // private String resultcode;
    private String reason;
    private T result;
    private int error_code;

//    public String getResultcode() {
//        return resultcode;
//    }
//
//    public void setResultcode(String resultcode) {
//        this.resultcode = resultcode;
//    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public T getResult() {
        return result;
    }

    public void setResult(T result) {
        this.result = result;
    }

    public int getError_code() {
        return error_code;
    }

    public void setError_code(int error_code) {
        this.error_code = error_code;
    }
}
