package com.itheima.pojo;

import lombok.Data;

@Data
public class Result {
    private Integer code; // 编码 1成功 0失败
    private String msg; // 错误信息
    private Object data; // 数据

    public static Result success() {
        Result result = new Result();
        result.setCode(1);
        result.setMsg("成功");
        result.setData(null);
        return result;
    }

    public static Result success(Object data) {
        Result result = new Result();
        result.setCode(1);
        result.setMsg("成功");
        result.setData(data);
        return result;
    }

    public static Result error(String msg) {
        Result result = new Result();
        result.setCode(0);
        result.setMsg(msg);
        result.setData(null);
        return result;
    }
}
