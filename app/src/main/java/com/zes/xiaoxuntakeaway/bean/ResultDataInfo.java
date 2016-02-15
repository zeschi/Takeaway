package com.zes.xiaoxuntakeaway.bean;

public class ResultDataInfo<T> {
    /**
     * 返回信息
     */
    private String retmsg;
    /**
     * 返回码
     */
    private int code;
    /**
     * 返回数据
     */
    private T data;

    public String getRetmsg() {
        return retmsg;
    }

    public void setRetmsg(String retmsg) {
        this.retmsg = retmsg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "ResultDataInfo{" +
                "retmsg='" + retmsg + '\'' +
                ", code=" + code +
                ", data=" + data +
                '}';
    }
}
