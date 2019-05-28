package com.algorithm.util;

import com.alibaba.fastjson.JSON;

/**
 * @program: com.algorithm.util
 * @author: mashifei
 * @create: 2019-05-28-20
 */
public class JsonUtil {

    public static String beanToJson(Object o){
        return JSON.toJSONString(o);
    }

    public static <T> T jsonToBean(String json,Class<T> cls){
        return JSON.parseObject(json,cls);
    }
}
