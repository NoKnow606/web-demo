package com.example.demo.domain;

//import net.bytebuddy.dynamic.scaffold.subclass.ConstructorStrategy;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;
import java.util.List;

public class ResponseFormate<E> implements Serializable{

    // 返回码
    private int code;

    private E data;

    private String msg = "";

    public ResponseFormate (){}

    public void setCode(int code) {
        this.code = code;
    }

    public void setData(E data) {
        this.data = data;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public E getData() {
        return data;
    }

    public String getMsg() {
        return msg;
    }

    public ResponseFormate<E> setSuccessResponse(E e){
        this.setCode(0);
        this.setData(e);
        this.setMsg("success");
        return this;
    }

    public void setFailResponse(int code,String msg){
        this.setCode(code);
        this.setMsg(msg);
    }

    public void setFailResponse(String msg){
        this.setMsg(msg);
        this.setCode(1000);
    }
}
