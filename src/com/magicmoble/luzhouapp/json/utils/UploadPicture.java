package com.magicmoble.luzhouapp.json.utils;

import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Base64;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import Decoder.BASE64Decoder;

public class UploadPicture {

	public static boolean GenerateImage(String imgStr, String imgFilePath) {// 对字节数组字符串进行Base64解码并生成图片
		if (imgStr == null) // 图像数据为空
			return false;
		BASE64Decoder decoder = new BASE64Decoder();
		
		
		try {
			// Base64解码
			byte[] bitmap = Base64.getDecoder().decode(imgStr);
//			byte[] bytes = decoder.decodeBuffer(imgStr);
			for (int i = 0; i < bitmap.length; ++i) {
				if (bitmap[i] < 0) {// 调整异常数据
					bitmap[i] += 256;
				}
			}
			// 生成jpeg图片
			OutputStream out = new FileOutputStream(imgFilePath);
			out.write(bitmap);
			out.flush();
			out.close();
			return true;
		} catch (Exception e) {
			
			return false;
		}
	}

	public static List<Map<String, String>> upload(String picture) {
		int a = picture.indexOf("[");
		int b = picture.indexOf("]");

		picture = picture.substring(a + 1, b);
		picture = picture.replace("{", "");
		picture = picture.replace("}", "");
		List<String> list = new ArrayList<String>();
		for (int i = 0; i < picture.split(",").length; i++) {
			list.add(picture.split(",")[i]);
		}
		List<Map<String, String>> list2 = new ArrayList<Map<String, String>>();
		Map<String, String> map = new HashMap<String, String>();
		for (int j = 0; j < list.size(); j++) {
			map.put(list.get(j).split(",")[0].split(":")[0].replace("\"", ""),
					list.get(j).split(",")[0].split(":")[1].replace("\"", ""));
			if (j % 2 == 1) {
				list2.add(map);
				map = new HashMap<String, String>();
			}
		}
		return list2;
	}

	public static List<Map<String, String>> uploadContent(String content) {
		int a = content.indexOf("[");
		int b = content.indexOf("]");

		content = content.substring(a + 1, b);
		content = content.replace("{", "");
		content = content.replace("}", "");
		List<String> list = new ArrayList<String>();
		for (int i = 0; i < content.split(",").length; i++) {
			list.add(content.split(",")[i]);
		}
		List<Map<String, String>> list2 = new ArrayList<Map<String, String>>();
		Map<String, String> map = new HashMap<String, String>();
		for (int j = 0; j < list.size(); j++) {
			map.put(list.get(j).split(",")[0].split(":")[0].replace("\"", ""),
					list.get(j).split(",")[0].split(":")[1].replace("\"", ""));
			if (j % 3 == 2) {
				list2.add(map);
				map = new HashMap<String, String>();
			}
		}
		return list2;
	}
}
