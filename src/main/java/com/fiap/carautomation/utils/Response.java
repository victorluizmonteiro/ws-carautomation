package com.fiap.carautomation.utils;

import java.util.ArrayList;
import java.util.List;

public class Response<T> {

    private T data;
    private List<T>dataList;
    private List<String>errors;

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }



    public void setDataList(List<T> dataList) {
        this.dataList = dataList;
    }

    public List<String> getErrors() {

        if(this.errors == null){
            this.errors = new ArrayList<String>();
        }
        return errors;
    }

    public void setErrors(List<String> errors) {
        this.errors = errors;
    }
    public List<T> getDataList() {
        if(this.dataList == null){
            this.dataList = new ArrayList<T>();
        }
        return dataList;
    }
}
