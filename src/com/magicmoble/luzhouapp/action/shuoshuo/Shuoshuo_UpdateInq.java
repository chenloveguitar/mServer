package com.magicmoble.luzhouapp.action.shuoshuo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.magicmoble.luzhouapp.business.FaxianBusiness;
import com.magicmoble.luzhouapp.business.ShuoshuoBusiness;
import com.magicmoble.luzhouapp.json.core.DataObject;
import com.magicmoble.luzhouapp.json.responseUtils.ResponseUtils;
import com.magicmoble.luzhouapp.json.status.StatusHouse;
import com.magicmoble.luzhouapp.json.utils.JackJsonUtils;
import com.magicmoble.luzhouapp.json.utils.UploadPicture;

public class Shuoshuo_UpdateInq extends HttpServlet {

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
		String picture = request.getParameter("picture");
		try {
			InputStream in = request.getInputStream();
			BufferedReader br = new BufferedReader(new InputStreamReader(in));
			String str = br.readLine();

			Map<String, String> map = new HashMap<String, String>();
			for (int i = 0; i < str.split("&").length; i++) {
				map.put(str.split("&")[i].split("=")[0], str.split("&")[i].split("=")[1]);
			}

			if (picture != null) {
				List<Map<String, String>> list2 = UploadPicture.upload(picture);
				picture = "";
				for (int i = 0; i < list2.size(); i++) {
					System.out.println(list2.size());
					String img_base64 = list2.get(i).get("base64");
					String dataString = list2.get(i).get("name");
					String path = request.getRealPath("/upload");
					String ServicePath = path + "/" + dataString;

					UploadPicture.GenerateImage(img_base64, ServicePath);

					picture += "upload/" + dataString + ",";
				}
			}

			for (int i = 0; i < map.size(); i++) {
				if (str.split("&")[i].split("=")[0].equals("picture")) {
					ShuoshuoBusiness.UpdateShuoshuo(str.split("&")[i].split("=")[0], picture, map.get("id"));
				} else {
					ShuoshuoBusiness.UpdateShuoshuo(str.split("&")[i].split("=")[0], str.split("&")[i].split("=")[1],
							map.get("id"));
				}
			}

			DataObject dataObject = new DataObject();
			dataObject.setdata("修改成功");
			dataObject.setStatusObject(StatusHouse.COMMON_STATUS_OK);
			String responseText = JackJsonUtils.toJson(dataObject);

			ResponseUtils.renderJson(response, responseText);

		} catch (NullPointerException e) {
			DataObject dataObject = new DataObject();
			dataObject.setdata("请传入正确参数");
			dataObject.setStatusObject(StatusHouse.COMMON_STATUS_ERROR);
			String responseText = JackJsonUtils.toJson(dataObject);

			ResponseUtils.renderJson(response, responseText);
		} catch (ArrayIndexOutOfBoundsException e) {
			DataObject dataObject = new DataObject();
			dataObject.setdata("传入参数错误");
			dataObject.setStatusObject(StatusHouse.COMMON_STATUS_ERROR);
			String responseText = JackJsonUtils.toJson(dataObject);

			ResponseUtils.renderJson(response, responseText);
		}

	}

}
