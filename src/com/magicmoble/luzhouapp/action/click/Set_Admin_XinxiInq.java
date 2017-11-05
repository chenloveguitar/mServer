package com.magicmoble.luzhouapp.action.click;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.magicmoble.luzhouapp.business.FaxianBusiness;
import com.magicmoble.luzhouapp.business.FunctionBusiness;
import com.magicmoble.luzhouapp.business.ToutiaoBusiness;
import com.magicmoble.luzhouapp.json.core.DataObject;
import com.magicmoble.luzhouapp.json.core.ListObject;
import com.magicmoble.luzhouapp.json.responseUtils.ResponseUtils;
import com.magicmoble.luzhouapp.json.status.StatusHouse;
import com.magicmoble.luzhouapp.json.utils.JackJsonUtils;
import com.magicmoble.luzhouapp.json.utils.UploadPicture;
import com.magicmoble.luzhouapp.model.Admin_xinxi;
import com.magicmoble.luzhouapp.model.Create_order;
import com.magicmoble.luzhouapp.model.Faxian;
import com.magicmoble.luzhouapp.model.Tou_Picture;
import com.magicmoble.luzhouapp.model.Toutiao;

public class Set_Admin_XinxiInq extends HttpServlet {

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
		String my_id = request.getParameter("my_id");
		String touxiang = request.getParameter("touxiang");
		String name = request.getParameter("name");
		String weichat = request.getParameter("weichat");
		String sex = request.getParameter("sex");
		String qianming = request.getParameter("qianming");

		int guangjie_fenlei_Tag = 0;

		Gson gson = new Gson();
		Tou_Picture picture2 = gson.fromJson(touxiang, Tou_Picture.class);

		try {

			if (my_id == null) {
				DataObject dataObject = new DataObject();
				dataObject.setdata("参数不足");
				dataObject.setStatusObject(StatusHouse.COMMON_STATUS_ERROR);
				String responseText = JackJsonUtils.toJson(dataObject);

				ResponseUtils.renderJson(response, responseText);
			} else {

				if (touxiang != null) {
					touxiang = "";
					String img_base64 = picture2.getBase64Picture();
					String dataString = new Date().getTime() + "" + (Math.round(Math.random() * 10000)) + ".jpeg";
					String path = request.getRealPath("/upload/touxiang");
					String ServicePath = path + "/" + dataString;
					UploadPicture.GenerateImage(img_base64, ServicePath);

					touxiang += "http://120.92.169.86/mServer/upload/touxiang/" + dataString;
				}
				
				boolean ax = FunctionBusiness.setadmin_xinxi(my_id, name, touxiang, weichat, sex, qianming);

				DataObject dataObject = new DataObject();
				dataObject.setdata(ax);
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
