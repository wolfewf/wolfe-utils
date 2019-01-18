package com.wolfe.utils.json;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;

/**
 * 基于com.google.code.gson封装的json转换工具类
 *
 * @author Wolfe
 * @since 1.0.0
 */
public class GsonUtil {
    private static Gson gson = null;

    static {
        if (gson == null) {
            gson = new GsonBuilder().serializeNulls().create();
        }
    }

    private GsonUtil() {
    }

    /**
     * 将对象转换成json格式
     *
     * @param object
     * @return json
     */
    public static String objectToJson(Object object) {
        String jsonString = null;
        if (gson != null) {
            jsonString = gson.toJson(object);
        }
        return jsonString;
    }

    /**
     * 将json转换成bean对象
     *
     * @param jsonStr
     * @param cl   指定的java对象
     * @return
     */
    public static Object jsonToBean(String jsonStr, Class<?> cl) {
        Object obj = null;
        if (gson != null) {
            obj = gson.fromJson(jsonStr, cl);
        }
        return obj;
    }

    /**
     * 将json格式转换成list对象
     *
     * @param jsonStr
     * @return
     */
    public static List<?> jsonToList(String jsonStr) {
        List<?> objList = null;
        if (gson != null) {
            Type type = new TypeToken<List<?>>() {
            }.getType();
            objList = gson.fromJson(jsonStr, type);
        }
        return objList;
    }

    /**
     * json转成list中有map的
     *
     * @param jsonStr
     * @return
     */
    public static List<Map<?, ?>> jsonToListMap(String jsonStr) {
        List<Map<?, ?>> list = null;
        if (gson != null) {
            list = gson.fromJson(jsonStr, new TypeToken<List<Map<?, ?>>>() {
            }.getType());
        }
        return list;
    }


    /**
     * 将json格式转换成map对象
     *
     * @param jsonStr
     * @return
     */
    public static Map<?, ?> jsonToMap(String jsonStr) {
        Map<?, ?> objMap = null;
        if (gson != null) {
            Type type = new TypeToken<Map<?, ?>>() {
            }.getType();
            objMap = gson.fromJson(jsonStr, type);
        }
        return objMap;
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
        System.out.println(jsonToList(jsonList));
        System.out.println(jsonToListMap(jsonList));
        System.out.println(jsonToListMap(jsonList).get(1).get("ee") instanceof Double);
        System.out.println(getJsonValue(json, "url"));
    }
}
