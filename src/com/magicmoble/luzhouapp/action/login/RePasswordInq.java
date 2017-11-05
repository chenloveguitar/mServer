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

import com.magicmoble.luzhouapp.business.LoginBusiness;
import com.magicmoble.luzhouapp.json.core.DataObject;
import com.magicmoble.luzhouapp.json.responseUtils.ResponseUtils;
import com.magicmoble.luzhouapp.json.status.StatusHouse;
import com.magicmoble.luzhouapp.json.utils.JackJsonUtils;
import com.magicmoble.luzhouapp.model.Login;

public class RePasswordInq extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String str = null;
		String phone = request.getParameter("phone");
		String password = request.getParameter("password");
		String yanzhengma = request.getParameter("yanzhengma");
		try {

			List<Login> list = LoginBusiness.getAllLogin();
			if (yanzhengma.equals(Yanzhengma_Repassword.code)) {
				for (int i = 0; i < list.size(); i++) {
					if (((Login) list.get(i)).getPhone().equals(phone)) {
						if (((Login) list.get(i)).getPassword().equals(password)) {
							DataObject dataObject = new DataObject();
							dataObject.setdata(new String("请不要使用旧密码"));
							dataObject.setStatusObject(StatusHouse.COMMON_STATUS_ERROR);
							String responseText = JackJsonUtils.toJson(dataObject);
							ResponseUtils.renderJson(response, responseText);
							break;
						} else {
							LoginBusiness.Repass(phone, password);
							DataObject dataObject = new DataObject();
							dataObject.setdata(new String("修改密码成功"));
							dataObject.setStatusObject(StatusHouse.COMMON_STATUS_OK);
							String responseText = JackJsonUtils.toJson(dataObject);
							ResponseUtils.renderJson(response, responseText);
							break;
						}
					}
				}
			} else {
				DataObject dataObject = new DataObject();
				dataObject.setdata(new String("验证码不正确"));
				dataObject.setStatusObject(StatusHouse.COMMON_STATUS_ERROR);
				String responseText = JackJsonUtils.toJson(dataObject);
				ResponseUtils.renderJson(response, responseText);
			}

		} catch (NullPointerException e) {
			DataObject dataObject = new DataObject();
			dataObject.setdata(new String("请输入帐号或密码"));
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
