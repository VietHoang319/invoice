package com.example.invoice.model;

public class DTOInvoice<T> {
    String message;
    T t;
    String status;

    public String getMessage() {
        return message;
    }

    public DTOInvoice() {
    }

    public DTOInvoice(String message, T t, String status) {
        this.message = message;
        this.t = t;
        this.status = status;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getT() {
        return t;
    }

    public void setT(T t) {
        this.t = t;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
