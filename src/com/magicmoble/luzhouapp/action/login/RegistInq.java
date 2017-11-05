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

import com.magicmoble.luzhouapp.business.FunctionBusiness;
import com.magicmoble.luzhouapp.business.LoginBusiness;
import com.magicmoble.luzhouapp.business.ShuoshuoBusiness;
import com.magicmoble.luzhouapp.json.core.DataObject;
import com.magicmoble.luzhouapp.json.responseUtils.ResponseUtils;
import com.magicmoble.luzhouapp.json.status.StatusHouse;
import com.magicmoble.luzhouapp.json.utils.JackJsonUtils;
import com.magicmoble.luzhouapp.model.Login;

public class RegistInq extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String str = null;
		int tag = 0;

		String phone = request.getParameter("phone");
		String password = request.getParameter("password");
		String yanzhengma = request.getParameter("yanzhengma");
		try {

			if (phone == null || password == null || yanzhengma == null) {
				DataObject dataObject = new DataObject();
				dataObject.setdata(new String("输入信息有误，请查看输入信息"));
				dataObject.setStatusObject(StatusHouse.COMMON_STATUS_ERROR);
				String responseText = JackJsonUtils.toJson(dataObject);
				ResponseUtils.renderJson(response, responseText);
			}

			List<Login> list = LoginBusiness.getAllLogin();
			for (int i = 0; i < list.size(); i++) {
				if (((Login) list.get(i)).getPhone().equals(phone)) {
					tag++;
					break;
				}
			}

			if (yanzhengma.equals(Yanzhengma.code)) {
				if (tag == 0) {
					String id = LoginBusiness.Regist(phone, password);
					DataObject dataObject = new DataObject();
					dataObject.setdata(new String("注册成功"));
					dataObject.setStatusObject(StatusHouse.COMMON_STATUS_OK);
					String responseText = JackJsonUtils.toJson(dataObject);
					Yanzhengma.code = null;
					ShuoshuoBusiness.addShuoshuo("http://120.92.169.86/mServer/common/image/bg.png",
							"我第一次来到这里，发现了酒城的秘密，欢迎大家来关注我", id, 3);
					ResponseUtils.renderJson(response, responseText);

				} else {
					DataObject dataObject = new DataObject();
					dataObject.setdata(new String("用户已存在"));
					dataObject.setStatusObject(StatusHouse.COMMON_STATUS_IN);
					String responseText = JackJsonUtils.toJson(dataObject);
					ResponseUtils.renderJson(response, responseText);
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
			dataObject.setdata(new String("输入信息有误，请查看输入信息"));
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
