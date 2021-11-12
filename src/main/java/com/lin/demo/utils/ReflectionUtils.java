package com.lin.demo.utils;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * 反射工具类,日志切面专用
 *
 * @Auth ParkYD
 */
public class ReflectionUtils {

    private static String[] types = {"java.lang.Integer", "java.lang.Double",
            "java.lang.Float", "java.lang.Long", "java.lang.Short",
            "java.lang.Byte", "java.lang.Boolean", "java.lang.Char",
            "java.lang.String", "int", "double", "long", "short", "byte",
            "boolean", "char", "float"};


    public static List<ParamData> sensorDataList(Object param, String paramName) {
        List<ParamData> paramDataList = new ArrayList<>();
        Class<?> clazz = param.getClass();
        try {
            exceClass(param, paramDataList, clazz, paramName);
        } catch (Exception e) {

        }
        return paramDataList;
    }

    public static Object getValue(Object obj, String name) {
        Method[] m = obj.getClass().getMethods();
        try {
            // 构建方法的后缀
            String methodEnd = name.substring(0, 1).toUpperCase() + name.substring(1);
            for (int i = 0; i < m.length; i++) {
                if (("get" + methodEnd).toLowerCase().equals(m[i].getName().toLowerCase())) {
                    return m[i].invoke(obj);
                }
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static void exceClass(Object sensorDataDto, List<ParamData> paramDataList, Class<?> clazz, String paramName) throws Exception {
        for (String baseType : types) {
            if (clazz.getName().equals(baseType)) {
                returnClassField(sensorDataDto, paramDataList, clazz, paramName);
                return;
            }
        }
        if (clazz != Object.class) {
            returnClassField(sensorDataDto, paramDataList, clazz);
            Class<?> clazzs = clazz.getSuperclass();
            exceClass(sensorDataDto, paramDataList, clazzs, paramName);
        }
    }

    private static void returnClassField(Object sensorDataDto, List<ParamData> paramDataList, Class<?> clazz, String paramName) throws Exception {
        ParamData paramData = new ParamData();
        paramData.setSensorId(paramName);
        paramData.setSensorValue(sensorDataDto.toString());
        paramDataList.add(paramData);
    }

    private static void returnClassField(Object sensorDataDto, List<ParamData> paramDataList, Class<?> clazz) throws Exception {
        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
            ParamData paramData = new ParamData();
            paramData.setSensorId(field.getName());
            paramData.setSensorValue(field.get(sensorDataDto));
            paramDataList.add(paramData);
        }
    }
}