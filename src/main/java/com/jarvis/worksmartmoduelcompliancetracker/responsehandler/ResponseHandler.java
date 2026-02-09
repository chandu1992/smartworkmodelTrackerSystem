package com.jarvis.worksmartmoduelcompliancetracker.responsehandler;


public class ResponseHandler <T>{
    private T data;
    private int statusCode;
    private String message;

    public ResponseHandler(T data, int statusCode, String message) {
        this.data = data;
        this.statusCode = statusCode;
        this.message = message;
    }

    public void setData(T data) {
        this.data = data;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
