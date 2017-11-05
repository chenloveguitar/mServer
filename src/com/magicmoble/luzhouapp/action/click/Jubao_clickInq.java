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
import com.magicmoble.luzhouapp.business.ToutiaoBusiness;
import com.magicmoble.luzhouapp.json.core.DataObject;
import com.magicmoble.luzhouapp.json.core.ListObject;
import com.magicmoble.luzhouapp.json.responseUtils.ResponseUtils;
import com.magicmoble.luzhouapp.json.status.StatusHouse;
import com.magicmoble.luzhouapp.json.utils.JackJsonUtils;
import com.magicmoble.luzhouapp.model.Faxian;
import com.magicmoble.luzhouapp.model.Toutiao;

public class Jubao_clickInq extends HttpServlet {

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
		String reporter_id = request.getParameter("my_id");
		String content = request.getParameter("content");
		String tiaomu_id = request.getParameter("tiaomu_id");
		String fenlei_Tags = request.getParameter("fenlei_Tag");// 1:头条2：发现秘密3：去处 4:商品服务
		int fenlei_Tag = 0;

		if (fenlei_Tags != null) {
			fenlei_Tag = Integer.parseInt(fenlei_Tags);
		}

		try {
			if (reporter_id == null || content == null || tiaomu_id == null || fenlei_Tags == null) {
				DataObject dataObject = new DataObject();
				dataObject.setdata("参数不足");
				dataObject.setStatusObject(StatusHouse.COMMON_STATUS_ERROR);
				String responseText = JackJsonUtils.toJson(dataObject);
				ResponseUtils.renderJson(response, responseText);
			} else {
				FunctionBusiness.jubao(reporter_id, tiaomu_id, fenlei_Tag, content);

				DataObject dataObject = new DataObject();
				dataObject.setdata("举报成功");
				dataObject.setStatusObject(StatusHouse.COMMON_STATUS_OK);
				String responseText = JackJsonUtils.toJson(dataObject);
				ResponseUtils.renderJson(response, responseText);
			}
		}
		// catch (NullPointerException e) {
		// DataObject dataObject = new DataObject();
		// dataObject.setdata("请传入正确参数");
		// dataObject.setStatusObject(StatusHouse.COMMON_STATUS_ERROR);
		// String responseText = JackJsonUtils.toJson(dataObject);
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
