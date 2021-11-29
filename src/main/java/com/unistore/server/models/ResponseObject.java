package com.unistore.server.models;

public class ResponseObject {
    private String status;
    private String message;
    private Object data;
    private Object pagination;

    public ResponseObject() {}

    public ResponseObject(String status, String message, Object data) {
        this.status = status;
        this.message = message;
        this.data = data;
    }
    public ResponseObject(String status, String message, Object data, Object pagination) {
        this.status = status;
        this.message = message;
        this.data = data;
        this.pagination = pagination;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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

    public Object getPagination() {
        return pagination;
    }

    public void setPagination(Object pagination) {
        this.pagination = pagination;
    }
}
