package com.wolfe.utils.json;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;

import java.util.List;
import java.util.Map;

/**
 * 基于fastjson封装的json转换工具类
 *
 * @author Wolfe
 * @since 1.0.0
 */
public class FastJsonUtil {

    /**
     * 将对象转换成json格式
     *
     * @param object
     * @return json
     */
    public static String objectToJson(Object object) {
        return JSON.toJSONString(object);
    }

    /**
     * 将json转换成bean对象
     *
     * @param jsonStr
     * @return
     */
    public static Object jsonToBean(String jsonStr, Class<?> cl) {
        return JSON.parseObject(jsonStr, cl);
    }

    /**
     * 将json格式转换成list对象
     *
     * @param jsonStr
     * @return
     */
    public static List<?> jsonToList(String jsonStr) {
        return JSON.parseArray(jsonStr);
    }

    /**
     * json转成list中有map的
     *
     * @param jsonStr
     * @return
     */
    public static List<Map<?, ?>> jsonToListMap(String jsonStr) {
        return JSON.parseObject(jsonStr, new TypeReference<List<Map<?, ?>>>() {
        });
    }


    /**
     * 将json格式转换成map对象
     *
     * @param jsonStr
     * @return
     */
    public static Map<?, ?> jsonToMap(String jsonStr) {
        return JSON.parseObject(jsonStr, new TypeReference<Map<?, ?>>() {
        });
    }

    /**
     * 根据map中的key获取json的value（只能是map格式的json）
     *
     * @param jsonStr
     * @param key
     * @return
     */
    public static Object getJsonValue(String jsonStr, String key) {
        Object rulObj = null;
        Map<?, ?> rulMap = jsonToMap(jsonStr);
        if (rulMap != null && rulMap.size() > 0) {
            rulObj = rulMap.get(key);
        }
        return rulObj;
    }

    public static void main(String[] args) {
        String json = "{\"name\":\"BeJson\",\"url\":\"http://www.bejson.com\",\"page\":88.8,\"isNonProfit\":true}";
        String jsonList = "[{\"street\":\"科技园路.\",\"city\":\"江苏苏州\",\"country\":\"中国\"},{\"ee\":22,\"df\":324,\"ew\":435}]";
        System.out.println(jsonToMap(json));
        System.out.println(jsonToListMap(jsonList));
        System.out.println(jsonToList(jsonList));
        System.out.println(jsonToListMap(jsonList).get(1).get("ee") instanceof Integer);
    }
}
