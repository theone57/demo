package com.all.lin.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.List;
import java.util.Map;

/**
 * Created by shiyijun 2017-11-24
 */
public final class JsonUtil {
	private JsonUtil() {

	}

	public static String convertString(Object object) {
		ObjectMapper objectMapper = new ObjectMapper();
		try {
			return objectMapper.writeValueAsString(object);
		} catch (JsonProcessingException e) {
			throw new RuntimeException(e);

		}
	}

	public static <T> T convertObject(String str,Class<T> clazz) {
		ObjectMapper objectMapper = new ObjectMapper();
		try {
			return objectMapper.readValue(str, clazz);
		} catch (Exception e) {
			throw new RuntimeException(e);

		}
	}

	/**
	 * 返回list类型
	 * @param str
	 * @param str
	 * @param elementClass
	 * @return
	 */
	public static <T> List<T> convertList(String str,Class<T> elementClass) {
		ObjectMapper objectMapper = new ObjectMapper();
		try {
			JavaType javaType = objectMapper.getTypeFactory().constructParametricType(List.class, elementClass);
			return objectMapper.readValue(str, javaType);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * 返回map类型
	 * @param str
	 * @param keyClass
	 * @param valueClass
	 * @return
	 */
	public static <K,V> Map<K,V> convertMap(String str,Class<K> keyClass,Class<V> valueClass) {
		ObjectMapper objectMapper = new ObjectMapper();
		try {
			JavaType javaType = objectMapper.getTypeFactory().constructParametricType(Map.class, keyClass,valueClass);
			return objectMapper.readValue(str, javaType);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}
