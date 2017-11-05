package com.magicmoble.luzhouapp.action.shuoshuo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.magicmoble.luzhouapp.business.ShuoshuoBusiness;
import com.magicmoble.luzhouapp.json.core.DataObject;
import com.magicmoble.luzhouapp.json.core.ListObject;
import com.magicmoble.luzhouapp.json.responseUtils.ResponseUtils;
import com.magicmoble.luzhouapp.json.status.StatusHouse;
import com.magicmoble.luzhouapp.json.utils.JackJsonUtils;
import com.magicmoble.luzhouapp.model.Shuoshuo;

public class ShuoshuoInq extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String str = null;
		List<Shuoshuo> list = null;
		int page = 0;
		String my_id = request.getParameter("my_id");
		if (request.getParameter("page") != null) {
			page = Integer.valueOf(Integer.parseInt(request.getParameter("page")));
		}

		if (page == 0) {
			DataObject dataObject = new DataObject();
			dataObject.setdata("参数不足");
			dataObject.setStatusObject(StatusHouse.COMMON_STATUS_ERROR);
			String responseText = JackJsonUtils.toJson(dataObject);

			ResponseUtils.renderJson(response, responseText);
		} else {
			if (my_id == null) {
				list = ShuoshuoBusiness.getAllShuoshuo(page);
			} else if (my_id != null) {
				try {
					list = ShuoshuoBusiness.getAllShuoshuo(page, my_id);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
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
}
