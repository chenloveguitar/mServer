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
import com.magicmoble.luzhouapp.business.JiguangPush;
import com.magicmoble.luzhouapp.business.ToutiaoBusiness;
import com.magicmoble.luzhouapp.json.core.DataObject;
import com.magicmoble.luzhouapp.json.core.ListObject;
import com.magicmoble.luzhouapp.json.responseUtils.ResponseUtils;
import com.magicmoble.luzhouapp.json.status.StatusHouse;
import com.magicmoble.luzhouapp.json.utils.JackJsonUtils;
import com.magicmoble.luzhouapp.model.Faxian;
import com.magicmoble.luzhouapp.model.Huifu_list;
import com.magicmoble.luzhouapp.model.Pinglun;
import com.magicmoble.luzhouapp.model.Pinglun_list;
import com.magicmoble.luzhouapp.model.Toutiao;

public class AddHuifu extends HttpServlet {

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
		String pingluner_id = request.getParameter("my_id");
		String content = request.getParameter("content");
		String releaser_id = request.getParameter("releaser_id");
		List<Object> list = new ArrayList<Object>();
		if (tiaomu_id == null || pingluner_id == null || content == null) {
			DataObject dataObject = new DataObject();
			dataObject.setdata("参数不足");
			dataObject.setStatusObject(StatusHouse.COMMON_STATUS_ERROR);
			String responseText = JackJsonUtils.toJson(dataObject);
			ResponseUtils.renderJson(response, responseText);
		} else {

			Huifu_list huifu_list = FunctionBusiness.addHuifu(tiaomu_id, pingluner_id, content);
			FunctionBusiness.addmessage(pingluner_id,releaser_id, tiaomu_id, "回复了你的内容", content);
			JiguangPush.push(releaser_id, "有人回复了你");
			list.add(huifu_list);
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
