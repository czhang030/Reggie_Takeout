package com.example.reggie_takeout.common;

/**
 * 基于ThreadLocal的封装工具类，用于保护和获取当前http请求下的当前用户id
 */
public class BaseContext {
    private static ThreadLocal<Long> threadLocal = new ThreadLocal<>();

    public static void setCurrentId(Long id){
        threadLocal.set(id);
    }

    public static Long getCurrentId(){
        return threadLocal.get();
    }
}
