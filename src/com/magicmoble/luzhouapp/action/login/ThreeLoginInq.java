package com.magicmoble.luzhouapp.action.login;

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

import com.magicmoble.luzhouapp.business.Admin_xinxi_Business;
import com.magicmoble.luzhouapp.business.LoginBusiness;
import com.magicmoble.luzhouapp.json.core.DataObject;
import com.magicmoble.luzhouapp.json.responseUtils.ResponseUtils;
import com.magicmoble.luzhouapp.json.status.StatusHouse;
import com.magicmoble.luzhouapp.json.utils.JackJsonUtils;
import com.magicmoble.luzhouapp.model.Admin_xinxi;
import com.magicmoble.luzhouapp.model.Login;

public class ThreeLoginInq extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String id = request.getParameter("uid");
		String name = request.getParameter("name");
		String touxiang = request.getParameter("touxiang");
		String sex = request.getParameter("sex");

		try {

			LoginBusiness.Regist(id, name, touxiang, sex);
			Admin_xinxi_Business.getAdmin_xinxiInfoById(id);

			DataObject dataObject = new DataObject();
			dataObject.setdata(new String("登录成功"));
			dataObject.setStatusObject(StatusHouse.COMMON_STATUS_OK);
			String responseText = JackJsonUtils.toJson(dataObject);
			ResponseUtils.renderJson(response, responseText);
		} 
//		catch (NullPointerException e) {
//			DataObject dataObject = new DataObject();
//			dataObject.setdata(new String("请输入帐号密码"));
//			dataObject.setStatusObject(StatusHouse.COMMON_STATUS_ERROR);
//			String responseText = JackJsonUtils.toJson(dataObject);
//			ResponseUtils.renderJson(response, responseText);
//		}
		catch (ArrayIndexOutOfBoundsException e) {
			DataObject dataObject = new DataObject();
			dataObject.setdata("json参数错误");
			dataObject.setStatusObject(StatusHouse.COMMON_STATUS_ERROR);
			String responseText = JackJsonUtils.toJson(dataObject);

			ResponseUtils.renderJson(response, responseText);
		}
//		catch (Exception e) {
//			DataObject dataObject = new DataObject();
//			dataObject.setdata("未知错误");
//			dataObject.setStatusObject(StatusHouse.COMMON_STATUS_ERROR);
//			String responseText = JackJsonUtils.toJson(dataObject);
//
//			ResponseUtils.renderJson(response, responseText);
//		}
	}
}
