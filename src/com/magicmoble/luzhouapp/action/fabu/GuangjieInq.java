package com.magicmoble.luzhouapp.action.fabu;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.magicmoble.luzhouapp.business.CommodityBusiness;
import com.magicmoble.luzhouapp.json.core.DataObject;
import com.magicmoble.luzhouapp.json.core.ListObject;
import com.magicmoble.luzhouapp.json.responseUtils.ResponseUtils;
import com.magicmoble.luzhouapp.json.status.StatusHouse;
import com.magicmoble.luzhouapp.json.utils.JackJsonUtils;
import com.magicmoble.luzhouapp.model.Commodity;
import com.magicmoble.luzhouapp.model.Guangjie_Shouye;

public class GuangjieInq extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		List<Guangjie_Shouye> list = null;
		int page = 0;
		String my_id = request.getParameter("my_id");
		if (request.getParameter("page") != null) {
			page = Integer.parseInt(request.getParameter("page"));
		}
		int num = 1;
		list = CommodityBusiness.getAllGuangjie(my_id, page, num);

		ListObject listObject = new ListObject();
		listObject.setResult(list);
		DataObject dataObject = new DataObject();
		dataObject.setdata(listObject);
		dataObject.setStatusObject(StatusHouse.COMMON_STATUS_OK);
		String responseText = JackJsonUtils.toJson(dataObject);
		ResponseUtils.renderJson(response, responseText);

	}

}
