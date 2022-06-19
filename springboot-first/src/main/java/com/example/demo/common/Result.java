package com.example.demo.common;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.entity.Course;

public class Result<T> {
    private String code;
    private String msg;
    private T data;

    public <T> Result(T data) {

    }


    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setData(T data) {
        this.data = data;
    }

    public T getData() {
        return data;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public  Result(){

    }

    public void Result(T data){
        this.data = data;
    }

    public static Result success(){
        Result result = new Result<>();
        result.setCode("0");
        result.setMsg("成功");
        return result;
    }

    public static Result<?> success(Page<?> T){
        Result result = new Result<>();
        result.setData(T);
        return result;
    }

    public static <T> Result<T> success(T data){
        Result<T> result = new Result<>(data);
        result.setCode("0");
        result.setMsg("成功");
        return result;
    }

    public static Result error(String code,String msg){
        Result result = new Result<>();
        result.setCode(code);
        result.setMsg(msg);
        return result;
    }
}
