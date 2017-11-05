package com.magicmoble.luzhouapp.action.fabu;

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

import com.magicmoble.luzhouapp.business.CommodityBusiness;
import com.magicmoble.luzhouapp.json.core.DataObject;
import com.magicmoble.luzhouapp.json.core.ListObject;
import com.magicmoble.luzhouapp.json.responseUtils.ResponseUtils;
import com.magicmoble.luzhouapp.json.status.StatusHouse;
import com.magicmoble.luzhouapp.json.utils.JackJsonUtils;
import com.magicmoble.luzhouapp.model.Commodity;

public class CommodityInq_clickInq extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		List<Commodity> list = null;
		int page = Integer.valueOf(Integer.parseInt(request.getParameter("page")));

		if (page == 0) {
			list = CommodityBusiness.getNoBuyCommodity();
		} else {
			list = CommodityBusiness.getNoBuyCommodity(page);
		}

		ListObject listObject = new ListObject();
		listObject.setResult(list);
		DataObject dataObject = new DataObject();
		dataObject.setdata(listObject);
		dataObject.setStatusObject(StatusHouse.COMMON_STATUS_OK);
		String responseText = JackJsonUtils.toJson(dataObject);

		ResponseUtils.renderJson(response, responseText);

	}

}
