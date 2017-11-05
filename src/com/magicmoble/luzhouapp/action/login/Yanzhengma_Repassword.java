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

public class Yanzhengma_Repassword extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public static String code;
	public static String rusult;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String phone = request.getParameter("phone");
		Map<String, String> map = null;
		int num = 0;
		try {

			List<Login> list = LoginBusiness.getAllLogin();
			for (int i = 0; i < list.size(); i++) {
				if (((Login) list.get(i)).getPhone().equals(phone)) {

					map = LoginBusiness.Repassword(phone);
					code = map.get("code");
					rusult = map.get("rusult");
					DataObject dataObject = new DataObject();
					dataObject.setdata("验证码获取成功");
					dataObject.setStatusObject(StatusHouse.COMMON_STATUS_OK);
					String responseText = JackJsonUtils.toJson(dataObject);
					ResponseUtils.renderJson(response, responseText);
					num++;
					break;

				}

			}
			if (rusult != null) {
				DataObject dataObject = new DataObject();
				dataObject.setdata("验证码获取失败," + rusult);
				dataObject.setStatusObject(StatusHouse.COMMON_STATUS_ERROR);
				String responseText = JackJsonUtils.toJson(dataObject);
				ResponseUtils.renderJson(response, responseText);
				num++;
			}
			if (num == 0) {
				DataObject dataObject = new DataObject();
				dataObject.setStatusObject(StatusHouse.COMMON_STATUS_NO);
				String responseText = JackJsonUtils.toJson(dataObject);
				ResponseUtils.renderJson(response, responseText);
			}

		} catch (Exception e) {
			DataObject dataObject = new DataObject();
			dataObject.setdata("验证码获取失败");
			dataObject.setStatusObject(StatusHouse.COMMON_STATUS_ERROR);
			String responseText = JackJsonUtils.toJson(dataObject);

			ResponseUtils.renderJson(response, responseText);
		}
	}
}
