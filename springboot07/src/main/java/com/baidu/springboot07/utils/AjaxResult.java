package com.baidu.springboot07.utils;

import java.util.HashMap;
import java.util.Map;

//操作添加、删除、修改、查询时，控制器方法的返回值，如果查询的结果只是判断是否存在，也可以用AjaxResult
public class AjaxResult extends HashMap<String, Object> {
    public AjaxResult() {
        put("code", 0);
        put("msg", "操作成功");
    }

    public static AjaxResult error() {
        return error(1, "操作失败");
}

    public static AjaxResult error(String msg) {
        return error(500, msg);
    }

    public static AjaxResult error(int code, String msg) {
        AjaxResult r = new AjaxResult();
        r.put("code", code);
        r.put("msg", msg);
        return r;
    }

    public static AjaxResult right() {
        return new AjaxResult();
    }


    public static AjaxResult right(String msg) {
        AjaxResult r = new AjaxResult();
        r.put("msg", msg);
        return r;
    }

    public static AjaxResult right(Map<String, Object> map) {
        AjaxResult r = new AjaxResult();
        r.putAll(map);
        return r;
    }

    @Override
    public AjaxResult put(String key, Object value) {
        super.put(key, value);
        return this;
    }
}