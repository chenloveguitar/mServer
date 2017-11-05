package com.magicmoble.luzhouapp.action.login;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.magicmoble.luzhouapp.business.LoginBusiness;
import com.magicmoble.luzhouapp.json.core.DataObject;
import com.magicmoble.luzhouapp.json.core.ListObject;
import com.magicmoble.luzhouapp.json.responseUtils.ResponseUtils;
import com.magicmoble.luzhouapp.json.status.StatusHouse;
import com.magicmoble.luzhouapp.json.utils.JackJsonUtils;
import com.magicmoble.luzhouapp.model.Admin_xinxi;
import com.magicmoble.luzhouapp.model.Login;
import com.magicmoble.luzhouapp.singleLogin.SingleUtil;

public class LoginInq extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String str = null;
		int num = 0;
		String phone = request.getParameter("phone");
		String password = request.getParameter("password");

		try {

			List<Login> list = LoginBusiness.getAllLogin();
			for (int i = 0; i < list.size(); i++) {
				if (list.get(i).getPhone().toString().equals(phone)) {
					if (list.get(i).getPassword().toString().equals(password)) {
						Admin_xinxi xinxi = LoginBusiness.getAdmin_xinxiInfoByPhone(phone);
						List<Admin_xinxi> admin_list = new ArrayList<Admin_xinxi>();
						admin_list.add(xinxi);
						ListObject listObject = new ListObject();
						listObject.setResult(admin_list);

						DataObject dataObject = new DataObject();
						dataObject.setdata(listObject);
						dataObject.setStatusObject(StatusHouse.COMMON_STATUS_OK);
						String responseText = JackJsonUtils.toJson(dataObject);
						ResponseUtils.renderJson(response, responseText);
						break;
					} else {

						DataObject dataObject = new DataObject();
						dataObject.setdata("帐号或密码错误");
						dataObject.setStatusObject(StatusHouse.COMMON_STATUS_ERROR_PASSWORD_OR_PHONE);
						String responseText = JackJsonUtils.toJson(dataObject);
						ResponseUtils.renderJson(response, responseText);
						break;
					}
				} else {
					DataObject dataObject = new DataObject();
					dataObject.setdata("帐号或密码错误");
					dataObject.setStatusObject(StatusHouse.COMMON_STATUS_ERROR_PASSWORD_OR_PHONE);
					String responseText = JackJsonUtils.toJson(dataObject);
					num++;
					if (num == list.size()) {
						ResponseUtils.renderJson(response, responseText);
					}
				}
			}
		} catch (NullPointerException e) {

			DataObject dataObject = new DataObject();
			dataObject.setdata(new String("请输入帐号或密码"));
			dataObject.setStatusObject(StatusHouse.COMMON_STATUS_ERROR_PASSWORD_OR_PHONE);
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
