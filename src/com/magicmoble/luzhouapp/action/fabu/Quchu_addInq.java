package com.magicmoble.luzhouapp.action.fabu;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.magicmoble.luzhouapp.business.CommodityBusiness;
import com.magicmoble.luzhouapp.business.QuchuBusiness;
import com.magicmoble.luzhouapp.business.ToutiaoBusiness;
import com.magicmoble.luzhouapp.json.core.DataObject;
import com.magicmoble.luzhouapp.json.core.ListObject;
import com.magicmoble.luzhouapp.json.responseUtils.ResponseUtils;
import com.magicmoble.luzhouapp.json.status.StatusHouse;
import com.magicmoble.luzhouapp.json.utils.JackJsonUtils;
import com.magicmoble.luzhouapp.json.utils.UploadPicture;
import com.magicmoble.luzhouapp.model.Quchu;
import com.magicmoble.luzhouapp.model.Tou_Picture;
import com.magicmoble.luzhouapp.model.Tou_content;

public class Quchu_addInq extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String title = request.getParameter("title");
		String address = request.getParameter("address");
		String phone = request.getParameter("phone");
		String picture = request.getParameter("picture");
		String touxiang = request.getParameter("touxiang");
		String content = request.getParameter("content");
		String my_id = request.getParameter("my_id");
		int muban_Tag = 0;
		Type contentType = new TypeToken<List<Tou_content>>() {
		}.getType();
		Gson gson = new Gson();
		List<Tou_content> toutiao_content = gson.fromJson(content, contentType);
		// 图片
		Type pictureType = new TypeToken<List<Tou_Picture>>() {
		}.getType();
		List<Tou_Picture> toutiao_picture = gson.fromJson(picture, pictureType);
		Tou_Picture picture2 = gson.fromJson(touxiang, Tou_Picture.class);

		try {

			if (title == null || address == null || phone == null || picture == null || touxiang == null
					|| content == null || my_id == null) {
				DataObject dataObject = new DataObject();
				dataObject.setdata("参数不足");
				dataObject.setStatusObject(StatusHouse.COMMON_STATUS_ERROR);
				String responseText = JackJsonUtils.toJson(dataObject);

				ResponseUtils.renderJson(response, responseText);
			} else {

				if (picture != null) {
					picture = "";
					for (int i = 0; i < toutiao_picture.size(); i++) {
						String img_base64 = toutiao_picture.get(i).getBase64Picture();
						String dataString = new Date().getTime() + "" + (Math.round(Math.random() * 10000)) + ".jpeg";
						String path = request.getRealPath("/upload/picture");
						String ServicePath = path + "/" + dataString;
						UploadPicture.GenerateImage(img_base64, ServicePath);
						picture += "http://120.92.169.86/mServer/upload/picture/" + dataString + ",";
					}
				}
				if (touxiang != null) {
					touxiang = "";
					String img_base64 = picture2.getBase64Picture();
					String dataString = new Date().getTime() + "" + (Math.round(Math.random() * 10000)) + ".jpeg";
					String path = request.getRealPath("/upload/touxiang");
					String ServicePath = path + "/" + dataString;
					UploadPicture.GenerateImage(img_base64, ServicePath);

					touxiang += "http://120.92.169.86/mServer/upload/touxiang/" + dataString;
				}

				if (content != null) {
					content = "";
					for (int i = 0; i < toutiao_content.size(); i++) {

						String text = toutiao_content.get(i).getInputStr();
						String img_base64 = toutiao_content.get(i).getBitmap();
						String dataString = new Date().getTime() + "" + (Math.round(Math.random() * 10000)) + ""
								+ ".jpeg";
						String path = request.getRealPath("/upload/textpicture");
						String ServicePath = path + "/" + dataString;
						UploadPicture.GenerateImage(img_base64, ServicePath);
						if (text != null && !text.trim().equals("")) {
							content += text + "<--分隔符-->";
						}
						if (img_base64 != null && !img_base64.equals("")) {
							content += "http://120.92.169.86/mServer/upload/textpicture/" + dataString + "<--分隔符-->";
						}
					}
				}
				if (toutiao_picture.size() >= 3) {
					muban_Tag = 1;
				} else {
					muban_Tag = 2;
				}

				QuchuBusiness.addQuchu(my_id, title, address, touxiang, phone, picture, content);

				DataObject dataObject = new DataObject();
				dataObject.setdata("发布成功");
				dataObject.setStatusObject(StatusHouse.COMMON_STATUS_OK);
				String responseText = JackJsonUtils.toJson(dataObject);

				ResponseUtils.renderJson(response, responseText);
			}
		} catch (NullPointerException e) {
			DataObject dataObject = new DataObject();
			dataObject.setdata("请传入正确参数");
			dataObject.setStatusObject(StatusHouse.COMMON_STATUS_ERROR);
			String responseText = JackJsonUtils.toJson(dataObject);

			ResponseUtils.renderJson(response, responseText);
		} catch (ArrayIndexOutOfBoundsException e) {
			DataObject dataObject = new DataObject();
			dataObject.setdata("json参数错误");
			dataObject.setStatusObject(StatusHouse.COMMON_STATUS_ERROR);
			String responseText = JackJsonUtils.toJson(dataObject);

			ResponseUtils.renderJson(response, responseText);
		} catch (Exception e) {
			DataObject dataObject = new DataObject();
			dataObject.setdata("未知错误");
			dataObject.setStatusObject(StatusHouse.COMMON_STATUS_ERROR);
			String responseText = JackJsonUtils.toJson(dataObject);

			ResponseUtils.renderJson(response, responseText);
		}

	}

}
