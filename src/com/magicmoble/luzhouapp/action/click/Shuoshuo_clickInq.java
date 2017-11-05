package com.magicmoble.luzhouapp.action.click;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.magicmoble.luzhouapp.business.FunctionBusiness;
import com.magicmoble.luzhouapp.business.ShuoshuoBusiness;
import com.magicmoble.luzhouapp.json.core.DataObject;
import com.magicmoble.luzhouapp.json.core.ListObject;
import com.magicmoble.luzhouapp.json.responseUtils.ResponseUtils;
import com.magicmoble.luzhouapp.json.status.StatusHouse;
import com.magicmoble.luzhouapp.json.utils.JackJsonUtils;
import com.magicmoble.luzhouapp.model.Shuoshuo;
import com.magicmoble.luzhouapp.model.Shuoshuo_Xiangqing;

public class Shuoshuo_clickInq extends HttpServlet {

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
		String _page = request.getParameter("page");
		int page = 0;
		try {
			if (_page != null) {
				page = Integer.parseInt(_page);
			}
			if (id == null || _page == null) {
				DataObject DataObject = new DataObject();
				DataObject.setdata("参数不足");
				DataObject.setStatusObject(StatusHouse.COMMON_STATUS_ERROR);
				String responseText = JackJsonUtils.toJson(DataObject);

				ResponseUtils.renderJson(response, responseText);
			} else {
				List<Object> list = FunctionBusiness.getShuoshuoById(my_id, id, page);
				ShuoshuoBusiness.shuoshuo_yuedu(id);

				ListObject listObject = new ListObject();
				listObject.setResult(list);
				DataObject DataObject = new DataObject();
				DataObject.setdata(listObject);
				DataObject.setStatusObject(StatusHouse.COMMON_STATUS_OK);
				String responseText = JackJsonUtils.toJson(DataObject);
				ResponseUtils.renderJson(response, responseText);

			}
		} catch (NullPointerException e) {
			DataObject DataObject = new DataObject();
			DataObject.setdata("请传入正确参数");
			DataObject.setStatusObject(StatusHouse.COMMON_STATUS_ERROR);
			String responseText = JackJsonUtils.toJson(DataObject);

			ResponseUtils.renderJson(response, responseText);
		} catch (ArrayIndexOutOfBoundsException e) {
			DataObject DataObject = new DataObject();
			DataObject.setdata("json参数错误");
			DataObject.setStatusObject(StatusHouse.COMMON_STATUS_ERROR);
			String responseText = JackJsonUtils.toJson(DataObject);

			ResponseUtils.renderJson(response, responseText);
		} catch (Exception e) {
			DataObject DataObject = new DataObject();
			DataObject.setdata("未知错误");
			DataObject.setStatusObject(StatusHouse.COMMON_STATUS_ERROR);
			String responseText = JackJsonUtils.toJson(DataObject);

			ResponseUtils.renderJson(response, responseText);
		}
	}

}
