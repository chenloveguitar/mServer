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
import com.magicmoble.luzhouapp.model.Faxian;
import com.magicmoble.luzhouapp.model.Pinglun;
import com.magicmoble.luzhouapp.model.Pinglun_list;
import com.magicmoble.luzhouapp.model.Toutiao;

public class Pinglun_listInq extends HttpServlet {

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
		String id = request.getParameter("id");
		String my_id = request.getParameter("my_id");
		String page2 = request.getParameter("page");
		List<Integer> integers = new ArrayList<Integer>();
		int page = 0;
		boolean flag = false;
		if (page2 != null) {
			page = Integer.parseInt(page2);

		}
		if (id == null || page2 == null || my_id == null) {
			DataObject dataObject = new DataObject();
			dataObject.setdata("参数不足");
			dataObject.setStatusObject(StatusHouse.COMMON_STATUS_ERROR);
			String responseText = JackJsonUtils.toJson(dataObject);

			ResponseUtils.renderJson(response, responseText);
		} else {
			List<Pinglun> list2 = new ArrayList<Pinglun>();
			List<Pinglun> list = FunctionBusiness.getPinglun_list(id, my_id, page);
			if (page <= 1) {
				list2 = FunctionBusiness.getRemenPinglun_list(my_id, id);
				for (int i = 0; i < list.size(); i++) {
					for (int j = 0; j < list2.size(); j++) {
						if (list.get(i).getPinglun_id().equals(list2.get(j).getPinglun_id())) {
							flag = true;
						}
					}
					if (!flag) {
						list2.add(list.get(i));
					}

				}

			}
			ListObject listObject = new ListObject();
			listObject.setResult(list2);

			DataObject dataObject = new DataObject();
			dataObject.setdata(listObject);
			dataObject.setStatusObject(StatusHouse.COMMON_STATUS_OK);
			String responseText = JackJsonUtils.toJson(dataObject);

			ResponseUtils.renderJson(response, responseText);
		}
	}

}
