package com.magicmoble.luzhouapp.json.utils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializerProvider;

public class JackJsonUtils {
	static ObjectMapper objectMapper;

	public static <T> T fromJson(String content, Class<T> valueType) {
		if (objectMapper == null) {
			objectMapper = new ObjectMapper();
			objectMapper.getSerializerProvider().setNullValueSerializer(new JsonSerializer<Object>() {

				@Override
				public void serialize(Object value, JsonGenerator jg, SerializerProvider sp)
						throws IOException, JsonProcessingException {
					if (value==null) {
						jg.writeString("");
					}
				
				}
			});
		}
		try {
			return objectMapper.readValue(content, valueType);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public static String toJson(Object object) {
		if (objectMapper == null) {
			objectMapper = new ObjectMapper();
			objectMapper.getSerializerProvider().setNullValueSerializer(new JsonSerializer<Object>() {

				@Override
				public void serialize(Object value, JsonGenerator jg, SerializerProvider sp)
						throws IOException, JsonProcessingException {
					if (value==null) {
						jg.writeString("");
					}
				}
			});
		}
		try {
			return objectMapper.writeValueAsString(object);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
