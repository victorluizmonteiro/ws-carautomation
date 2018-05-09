package com.fiap.carautomation.utils;

import java.util.List;

public class Response<T> {

    private List<T> data;
    private List<String>errors;

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }

    public List<String> getErrors() {
        return errors;
    }

    public void setErrors(List<String> errors) {
        this.errors = errors;
    }
}
