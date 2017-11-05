package com.magicmoble.luzhouapp.action.click;

import java.io.IOException;
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
import com.magicmoble.luzhouapp.model.Toutiao;

public class Shoucang_clickInq extends HttpServlet {

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
		String duixiang_id = request.getParameter("duixiang_id");
		String shoucang_fenlei_Tag = request.getParameter("shoucang_fenlei_Tag"); // 1头条2发现3去处4逛街
		String guangjie_fenlei_Tag1 = request.getParameter("guangjie_fenlei_Tag");
		String shoucang_Tag2 = request.getParameter("shoucang_Tag");
		boolean shoucang_Tag = false;
		int guangjie_fenlei_Tag = 0;
		int _shoucang_fenlei_Tag = 0;

		try {
			if (shoucang_fenlei_Tag != null) {
				_shoucang_fenlei_Tag = Integer.parseInt(shoucang_fenlei_Tag);
			}
			if (shoucang_Tag2 != null) {
				shoucang_Tag = Boolean.parseBoolean(shoucang_Tag2);
			}
			if (guangjie_fenlei_Tag1 != null) {
				guangjie_fenlei_Tag = Integer.parseInt(guangjie_fenlei_Tag1);
			}
			if (guangjie_fenlei_Tag1 == null || shoucang_Tag2 == null || guangjie_fenlei_Tag1 == null) {
				DataObject dataObject = new DataObject();
				dataObject.setdata("参数不足");
				dataObject.setStatusObject(StatusHouse.COMMON_STATUS_ERROR);
				String responseText = JackJsonUtils.toJson(dataObject);

				ResponseUtils.renderJson(response, responseText);
			} else {
				if (shoucang_Tag == true) {

					if (_shoucang_fenlei_Tag == 3) {
						FunctionBusiness.addShoucangByQuchu(my_id, duixiang_id);
					} else if (_shoucang_fenlei_Tag == 4) {
						FunctionBusiness.addShoucangByCommodity(my_id, duixiang_id, guangjie_fenlei_Tag);
					} else if (_shoucang_fenlei_Tag == 1) {
						FunctionBusiness.addShoucangByToutiao(my_id, duixiang_id);
					} else if (_shoucang_fenlei_Tag == 2) {
						FunctionBusiness.addShoucangByFaxian(my_id, duixiang_id);

					}

					DataObject dataObject = new DataObject();
					dataObject.setdata("收藏成功");
					dataObject.setStatusObject(StatusHouse.COMMON_STATUS_OK);
					String responseText = JackJsonUtils.toJson(dataObject);
					ResponseUtils.renderJson(response, responseText);
				} else if (shoucang_Tag == false) {
					if (_shoucang_fenlei_Tag == 3) {
						FunctionBusiness.DeleteShoucangByQuchu_Id(my_id, duixiang_id);
					} else if (_shoucang_fenlei_Tag == 4) {
						FunctionBusiness.DeleteShoucangByCommodity_Id(my_id, duixiang_id, guangjie_fenlei_Tag);
					} else if (_shoucang_fenlei_Tag == 1) {
						FunctionBusiness.DeleteShoucangByToutiao_Id(my_id, duixiang_id);
					} else if (_shoucang_fenlei_Tag == 2) {
						FunctionBusiness.DeleteShoucangByFaxian_Id(my_id, duixiang_id);
					}

					DataObject dataObject = new DataObject();
					dataObject.setdata("取消收藏成功");
					dataObject.setStatusObject(StatusHouse.COMMON_STATUS_OK);
					String responseText = JackJsonUtils.toJson(dataObject);
					ResponseUtils.renderJson(response, responseText);

				}

			}
		} catch (NullPointerException e) {
			DataObject dataObject = new DataObject();
			dataObject.setdata("请传入正确参数");
			dataObject.setStatusObject(StatusHouse.COMMON_STATUS_ERROR);
			String responseText = JackJsonUtils.toJson(dataObject);

			ResponseUtils.renderJson(response, responseText);
		} catch (ArrayIndexOutOfBoundsException e) {
			DataObject dataObject = new DataObject();
			dataObject.setdata("json参数错误");
			dataObject.setStatusObject(StatusHouse.COMMON_STATUS_ERROR);
			String responseText = JackJsonUtils.toJson(dataObject);

			ResponseUtils.renderJson(response, responseText);
		} catch (Exception e) {
			DataObject dataObject = new DataObject();
			dataObject.setdata("未知错误");
			dataObject.setStatusObject(StatusHouse.COMMON_STATUS_ERROR);
			String responseText = JackJsonUtils.toJson(dataObject);

			ResponseUtils.renderJson(response, responseText);
		}
	}

}
