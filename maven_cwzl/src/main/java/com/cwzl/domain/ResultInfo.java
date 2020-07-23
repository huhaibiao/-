package com.cwzl.domain;

import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.List;
import java.util.Objects;

/**
 * 用于封装后端返回前端数据对象
 */
public class ResultInfo implements Serializable {
    private boolean flag;//后端返回结果正常为true，发生异常返回false
    private String msg;//发生异常的错误消息
    private Object data;//后端返回结果数据对象
    //无参方法
    public ResultInfo() {
    }

    /**
     * 有参方法
     * @param flag
     */
    public ResultInfo(boolean flag) {
        this.flag = flag;
    }

    /**
     * 有参方法
     * @param flag
     * @param msg
     */
    public ResultInfo(boolean flag, String msg) {
        this.flag = flag;
        this.msg = msg;
    }

    /**
     *
     * @param flag
     * @param msg
     * @param data
     */
    public ResultInfo(boolean flag, String msg, Object data) {
        this.flag = flag;
        this.msg = msg;
        this.data = data;
    }

    /**
     *
     * @return
     */
    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
