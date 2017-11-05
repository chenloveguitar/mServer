package com.magicmoble.luzhouapp.action.click;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.magicmoble.luzhouapp.business.Dingdan_Business;
import com.magicmoble.luzhouapp.business.FaxianBusiness;
import com.magicmoble.luzhouapp.business.FunctionBusiness;
import com.magicmoble.luzhouapp.business.ToutiaoBusiness;
import com.magicmoble.luzhouapp.json.core.DataObject;
import com.magicmoble.luzhouapp.json.core.ListObject;
import com.magicmoble.luzhouapp.json.responseUtils.ResponseUtils;
import com.magicmoble.luzhouapp.json.status.StatusHouse;
import com.magicmoble.luzhouapp.json.utils.JackJsonUtils;
import com.magicmoble.luzhouapp.model.Create_order;
import com.magicmoble.luzhouapp.model.Faxian;
import com.magicmoble.luzhouapp.model.Toutiao;

public class Add_DingdanInq extends HttpServlet {

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
		String tiaomu_id = request.getParameter("tiaomu_id");
		String _shuliang = request.getParameter("shuliang");
		String buyer_id = request.getParameter("buyer_id");
		String buyer_name = request.getParameter("buyer_name");
		String buyer_phone = request.getParameter("buyer_phone");
		String address = request.getParameter("address");
		String beizhu = request.getParameter("beizhu");
		String _guangjie_fenlei_Tag = request.getParameter("guangjie_fenlei_Tag");
		int shuliang = 0;
		int guangjie_fenlei_Tag = 0;
		if (_shuliang != null) {
			shuliang = Integer.parseInt(_shuliang);

		}
		if (_guangjie_fenlei_Tag != null) {
			guangjie_fenlei_Tag = Integer.parseInt(_guangjie_fenlei_Tag);
		}

		try {
			String transaction_id = Dingdan_Business.addDingdan(my_id, tiaomu_id, shuliang, buyer_id, buyer_name,
					buyer_phone, address, beizhu, guangjie_fenlei_Tag);

			DataObject dataObject = new DataObject();
			dataObject.setdata(transaction_id);
			dataObject.setStatusObject(StatusHouse.COMMON_STATUS_OK);
			String responseText = JackJsonUtils.toJson(dataObject);

			ResponseUtils.renderJson(response, responseText);

		}
		// catch (NullPointerException e) {
		// DataObject dataObject = new DataObject();
		// dataObject.setdata("请传入正确参数");
		// dataObject.setStatusObject(StatusHouse.COMMON_STATUS_ERROR);
		// String responseText = JackJsonUtils.toJson(dataObject);
		//
		// ResponseUtils.renderJson(response, responseText);
		// }
		catch (ArrayIndexOutOfBoundsException e) {
			DataObject dataObject = new DataObject();
			dataObject.setdata("json参数错误");
			dataObject.setStatusObject(StatusHouse.COMMON_STATUS_ERROR);
			String responseText = JackJsonUtils.toJson(dataObject);

			ResponseUtils.renderJson(response, responseText);
		}
		// catch (Exception e) {
		// DataObject dataObject = new DataObject();
		// dataObject.setdata("未知错误");
		// dataObject.setStatusObject(StatusHouse.COMMON_STATUS_ERROR);
		// String responseText = JackJsonUtils.toJson(dataObject);
		//
		// ResponseUtils.renderJson(response, responseText);
		// }
	}

}
