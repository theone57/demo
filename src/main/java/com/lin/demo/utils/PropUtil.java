package com.lin.demo.utils;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Created by parkYD on 2016/7/14.
 */
public class PropUtil {

	public static void setPropNull(Object obj) {
		Class clazz = obj.getClass();
		if (clazz.getSuperclass() != null && clazz.getSuperclass() != Object.class) {
			for (; clazz != Object.class; clazz = clazz.getSuperclass()) {
				Field[] fields = clazz.getDeclaredFields();
				setFields(fields, clazz, obj);
			}
		} else {
			Field[] fields = clazz.getDeclaredFields();
			setFields(fields, clazz, obj);
		}
	}

	private static void setFields(Field[] fields, Class clazz, Object obj) {
		for (Field field : fields) {
			String type = field.getGenericType().toString();
			if (type.endsWith("String")) {
				Method methodGet = null;
				try {
					methodGet = clazz.getMethod("get" + getMethodName(field.getName()));
					String val = (String) methodGet.invoke(obj);
					if (val != null && "".equals(val)) {
						Method methodSet = clazz.getMethod("set" + getMethodName(field.getName()), new Class[]{String.class});
						methodSet.invoke(obj, new Object[]{null});
					}
				} catch (NoSuchMethodException e) {
					throw new RuntimeException(e);
				} catch (InvocationTargetException e) {
					throw new RuntimeException(e);
				} catch (IllegalAccessException e) {
					throw new RuntimeException(e);
				}

			}
		}
	}

	// 把一个字符串的第一个字母大写
	private static String getMethodName(String fildeName) {
		byte[] items = fildeName.getBytes();
		items[0] = (byte) ((char) items[0] - 'a' + 'A');
		return new String(items);
	}
}