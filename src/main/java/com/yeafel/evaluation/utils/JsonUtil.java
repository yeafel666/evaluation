package com.yeafel.evaluation.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * 目的: 将对象转换成JSON数据
 * Created by kangyifan on 2018/9/19 1:05
 */
public class JsonUtil {

    public static String toJson(Object object){
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.setPrettyPrinting();
        Gson gson = gsonBuilder.create();
        return gson.toJson(object);
    }
}
