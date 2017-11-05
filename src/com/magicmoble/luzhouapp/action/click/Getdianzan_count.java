package com.magicmoble.luzhouapp.action.click;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.magicmoble.luzhouapp.business.FaxianBusiness;
import com.magicmoble.luzhouapp.business.FunctionBusiness;
import com.magicmoble.luzhouapp.business.ToutiaoBusiness;
import com.magicmoble.luzhouapp.json.core.DataObject;
import com.magicmoble.luzhouapp.json.core.ListObject;
import com.magicmoble.luzhouapp.json.responseUtils.ResponseUtils;
import com.magicmoble.luzhouapp.json.status.StatusHouse;
import com.magicmoble.luzhouapp.json.utils.JackJsonUtils;
import com.magicmoble.luzhouapp.model.Dianzan_Number;
import com.magicmoble.luzhouapp.model.Dianzan_touxiang;
import com.magicmoble.luzhouapp.model.Faxian;
import com.magicmoble.luzhouapp.model.Pinglun;
import com.magicmoble.luzhouapp.model.Pinglun_list;
import com.magicmoble.luzhouapp.model.Toutiao;

public class Getdianzan_count extends HttpServlet {

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
		String tiaomu_id = request.getParameter("tiaomu_id");
		String _dianzan_Tag = request.getParameter("dianzan_Tag");

		int dianzan_Tag = 0;
		if (_dianzan_Tag != null) {
			dianzan_Tag = Integer.parseInt(_dianzan_Tag);
		}

		List<Dianzan_touxiang> list = FunctionBusiness.getDianzan_touxiang_List(tiaomu_id, dianzan_Tag);
		ListObject listObject = new ListObject();
		listObject.setResult(list);
		DataObject dataObject = new DataObject();
		dataObject.setdata(listObject);
		dataObject.setStatusObject(StatusHouse.COMMON_STATUS_OK);
		String responseText = JackJsonUtils.toJson(dataObject);

		ResponseUtils.renderJson(response, responseText);
	}
}
