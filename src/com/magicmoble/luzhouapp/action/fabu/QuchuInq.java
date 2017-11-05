package com.magicmoble.luzhouapp.action.fabu;

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

import com.magicmoble.luzhouapp.business.QuchuBusiness;
import com.magicmoble.luzhouapp.business.ToutiaoBusiness;
import com.magicmoble.luzhouapp.json.core.DataObject;
import com.magicmoble.luzhouapp.json.core.ListObject;
import com.magicmoble.luzhouapp.json.responseUtils.ResponseUtils;
import com.magicmoble.luzhouapp.json.status.StatusHouse;
import com.magicmoble.luzhouapp.json.utils.JackJsonUtils;
import com.magicmoble.luzhouapp.model.Quchu;
import com.magicmoble.luzhouapp.model.Quchu_Shouye;
import com.magicmoble.luzhouapp.model.Toutiao;

public class QuchuInq extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		List<Quchu_Shouye> list = new ArrayList<Quchu_Shouye>();
		int page = 0;
		String my_id = request.getParameter("my_id");
		int num = 3;
		if (request.getParameter("page") == null) {
			DataObject dataObject = new DataObject();
			dataObject.setdata("参数不足");
			dataObject.setStatusObject(StatusHouse.COMMON_STATUS_ERROR);
			String responseText = JackJsonUtils.toJson(dataObject);

			ResponseUtils.renderJson(response, responseText);
		} else {
			if (request.getParameter("page") != null) {
				page = Integer.valueOf(Integer.parseInt(request.getParameter("page")));
			}

			list = QuchuBusiness.getAllQuchu(my_id, page, num);

			ListObject listObject = new ListObject();
			listObject.setResult(list);
			listObject.setCount(list.size());

			DataObject dataObject = new DataObject();
			dataObject.setdata(listObject);
			dataObject.setStatusObject(StatusHouse.COMMON_STATUS_OK);
			String responseText = JackJsonUtils.toJson(dataObject);
			ResponseUtils.renderJson(response, responseText);
		}

	}

}
