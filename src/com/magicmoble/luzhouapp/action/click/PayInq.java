package com.magicmoble.luzhouapp.action.click;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.magicmoble.luzhouapp.business.DashangBusiness;
import com.magicmoble.luzhouapp.business.FaxianBusiness;
import com.magicmoble.luzhouapp.business.FunctionBusiness;
import com.magicmoble.luzhouapp.business.QianbaoBusiness;
import com.magicmoble.luzhouapp.business.ToutiaoBusiness;
import com.magicmoble.luzhouapp.json.core.DataObject;
import com.magicmoble.luzhouapp.json.core.ListObject;
import com.magicmoble.luzhouapp.json.responseUtils.ResponseUtils;
import com.magicmoble.luzhouapp.json.status.StatusHouse;
import com.magicmoble.luzhouapp.json.utils.JackJsonUtils;
import com.magicmoble.luzhouapp.model.Dashang_click;
import com.magicmoble.luzhouapp.model.Faxian;
import com.magicmoble.luzhouapp.model.Qianbao;
import com.magicmoble.luzhouapp.model.Toutiao;

public class PayInq extends HttpServlet {

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
		String friend_id = request.getParameter("duixiang_id");
		String _price = request.getParameter("price");
		String buy_name = request.getParameter("buy_name");
		String type = request.getParameter("type");
		// List<Dashang_click> clicks = new ArrayList<Dashang_click>();
		double price = 0.0;

		if (_price != null) {
			price = Double.parseDouble(_price);
		}

		try {
			String str = QianbaoBusiness.pay(price, my_id, friend_id, type, buy_name);

			// ListObject listObject = new ListObject();
			// listObject.setResult(clicks);
			DataObject dataObject = new DataObject();
			dataObject.setdata(str);
			dataObject.setStatusObject(StatusHouse.COMMON_STATUS_OK);
			String responseText = JackJsonUtils.toJson(dataObject);
			ResponseUtils.renderJson(response, responseText);
		} 
//		catch (NullPointerException e) {
//			DataObject dataObject = new DataObject();
//			dataObject.setdata("请传入正确参数");
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
