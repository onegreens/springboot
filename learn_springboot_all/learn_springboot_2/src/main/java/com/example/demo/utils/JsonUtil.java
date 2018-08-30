package com.example.demo.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;

public class JsonUtil {
	/**
	 * List<T> 转 json 保存到数据库
	 */
	public static <T> String listToJson(List<T> ts) {
		String jsons = JSON.toJSONString(ts);
		return jsons;
	}

	/**
	 * json 转 List<T>
	 */
	public static <T> List<T> jsonToList(String jsonString, Class<T> clazz) {
		@SuppressWarnings("unchecked")
		List<T> ts = (List<T>) JSONArray.parseArray(jsonString, clazz);
		return ts;
	}

	public static List<Map<String, Object>> strJsontoList(String json) {

		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		// 去除两端方括号
		json = json.replaceAll("[\\[\\]]", "");
		// 第一次分割
		String[] splitArray = json.split("},");
		for (int i = 0; i < splitArray.length; i++) {
			// 去除大括号，好方法去除大括号就用了子串的方式
			if (i == splitArray.length - 1) {
				splitArray[i] = splitArray[i].substring(1, splitArray[i].length() - 1);
			} else {
				splitArray[i] = splitArray[i].substring(1, splitArray[i].length());
			}
			Map<String, Object> map = new HashMap<String, Object>();
			// 第二次分割
			String[] mapArray = splitArray[i].split(",");
			for (int j = 0; j < mapArray.length; j++) {
				String str = mapArray[j].replaceAll("\"", "");
				// 第三次分割,为了防止value为空，下面加了一个长度判断
				String[] keyValue = str.split(":");
				if (keyValue.length == 2)
					map.put(keyValue[0], keyValue[1]);
				else
					map.put(keyValue[0], "");
			}
			list.add(map);
		}
		return list;

	}

}
