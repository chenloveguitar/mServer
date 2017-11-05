package com.magicmoble.luzhouapp.action.message;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;
import com.magicmoble.luzhouapp.business.FunctionBusiness;
import com.magicmoble.luzhouapp.business.LoginBusiness;
import com.magicmoble.luzhouapp.business.RenzhengBusiness;
import com.magicmoble.luzhouapp.json.core.DataObject;
import com.magicmoble.luzhouapp.json.core.ListObject;
import com.magicmoble.luzhouapp.json.responseUtils.ResponseUtils;
import com.magicmoble.luzhouapp.json.status.StatusHouse;
import com.magicmoble.luzhouapp.json.utils.JackJsonUtils;
import com.magicmoble.luzhouapp.json.utils.UploadPicture;
import com.magicmoble.luzhouapp.model.Admin_xinxi;
import com.magicmoble.luzhouapp.model.Login;
import com.magicmoble.luzhouapp.model.Message;
import com.magicmoble.luzhouapp.model.Tou_Picture;
import com.magicmoble.luzhouapp.singleLogin.SingleUtil;

public class MessageInq extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String my_id = request.getParameter("my_id");

		try {

			List<Message> list = FunctionBusiness.getmessage(my_id);
			ListObject listObject = new ListObject();
			listObject.setResult(list);

			DataObject dataObject = new DataObject();
			dataObject.setdata(listObject);
			dataObject.setStatusObject(StatusHouse.COMMON_STATUS_OK);
			String responseText = JackJsonUtils.toJson(dataObject);
			ResponseUtils.renderJson(response, responseText);

		}
		// catch (NullPointerException e) {
		//
		// DataObject dataObject = new DataObject();
		// dataObject.setdata(new String("请输入帐号或密码"));
		// dataObject.setStatusObject(StatusHouse.COMMON_STATUS_ERROR_PASSWORD_OR_PHONE);
		// String responseText = JackJsonUtils.toJson(dataObject);
		// ResponseUtils.renderJson(response, responseText);
		// }
		catch (ArrayIndexOutOfBoundsException e) {
			DataObject dataObject = new DataObject();
			dataObject.setdata("json参数错误");
			dataObject.setStatusObject(StatusHouse.COMMON_STATUS_ERROR);
			String responseText = JackJsonUtils.toJson(dataObject);

			ResponseUtils.renderJson(response, responseText);
		} catch (Exception e) {

			DataObject dataObject = new DataObject();
			dataObject.setdata(e);
			dataObject.setStatusObject(StatusHouse.COMMON_STATUS_ERROR);
			String responseText = JackJsonUtils.toJson(dataObject);

			ResponseUtils.renderJson(response, responseText);
		}
	}
}
