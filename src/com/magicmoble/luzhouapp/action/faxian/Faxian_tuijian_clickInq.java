package com.magicmoble.luzhouapp.action.faxian;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.magicmoble.luzhouapp.business.CommodityBusiness;
import com.magicmoble.luzhouapp.business.FaxianBusiness;
import com.magicmoble.luzhouapp.business.FunctionBusiness;
import com.magicmoble.luzhouapp.business.FuwuBusiness;
import com.magicmoble.luzhouapp.business.QuchuBusiness;
import com.magicmoble.luzhouapp.business.ToutiaoBusiness;
import com.magicmoble.luzhouapp.json.core.DataObject;
import com.magicmoble.luzhouapp.json.core.ListObject;
import com.magicmoble.luzhouapp.json.responseUtils.ResponseUtils;
import com.magicmoble.luzhouapp.json.status.StatusHouse;
import com.magicmoble.luzhouapp.json.utils.JackJsonUtils;
import com.magicmoble.luzhouapp.model.Faxian_Shouye;
import com.magicmoble.luzhouapp.model.Faxian_Xiangqing;
import com.magicmoble.luzhouapp.model.Guangjie_Xiangqing;
import com.magicmoble.luzhouapp.model.Quchu_Xiangqing;
import com.magicmoble.luzhouapp.model.Toutiao;
import com.magicmoble.luzhouapp.model.Toutiao_Xiangqing;

public class Faxian_tuijian_clickInq extends HttpServlet {

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
		String tuijian_Tags = request.getParameter("tuijian_Tag");
		String guangjie_fenlei_Tags = request.getParameter("guangjie_fenlei_Tag");
		int tuijian_Tag = 0;
		int guangjie_fenlei_Tag = 0;
		List<Object> list = new ArrayList<Object>();
		try {
			if (tuijian_Tags != null) {
				tuijian_Tag = Integer.parseInt(tuijian_Tags);
			}
			if (guangjie_fenlei_Tags != null) {
				guangjie_fenlei_Tag = Integer.parseInt(guangjie_fenlei_Tags);
			}

			if (id == null || my_id == null || tuijian_Tags == null) {
				DataObject dataObject = new DataObject();
				dataObject.setdata("参数不足");
				dataObject.setStatusObject(StatusHouse.COMMON_STATUS_ERROR);
				String responseText = JackJsonUtils.toJson(dataObject);

				ResponseUtils.renderJson(response, responseText);
			} else {

				if (tuijian_Tag == 1) {
					Faxian_Xiangqing Faxian_Xiangqing = FaxianBusiness.getFaxianById(my_id, id);
					list.add(Faxian_Xiangqing);
					ListObject listObject = new ListObject();
					listObject.setResult(list);
					DataObject dataObject = new DataObject();
					dataObject.setdata(listObject);
					dataObject.setStatusObject(StatusHouse.COMMON_STATUS_OK);
					String responseText = JackJsonUtils.toJson(dataObject);
					ResponseUtils.renderJson(response, responseText);
				} else if (tuijian_Tag == 2) {
					Quchu_Xiangqing quchu = QuchuBusiness.getQuchuById(id, my_id);
					list.add(quchu);

					ListObject listObject = new ListObject();
					listObject.setResult(list);
					DataObject dataObject = new DataObject();
					dataObject.setdata(listObject);
					dataObject.setStatusObject(StatusHouse.COMMON_STATUS_OK);
					String responseText = JackJsonUtils.toJson(dataObject);
					ResponseUtils.renderJson(response, responseText);
				} else if (tuijian_Tag == 3) {
					if (guangjie_fenlei_Tag == 1) {

						Guangjie_Xiangqing guangjie_Xiangqing = CommodityBusiness.getGuangjie_CommodityById(id, my_id,
								guangjie_fenlei_Tag);
						list.add(guangjie_Xiangqing);
						ListObject listObject = new ListObject();
						listObject.setResult(list);
						DataObject dataObject = new DataObject();
						dataObject.setdata(listObject);
						dataObject.setStatusObject(StatusHouse.COMMON_STATUS_OK);
						String responseText = JackJsonUtils.toJson(dataObject);
						ResponseUtils.renderJson(response, responseText);
					} else if (guangjie_fenlei_Tag == 2) {
						Guangjie_Xiangqing guangjie_Xiangqing = FuwuBusiness.getGuangjie_FuwuById(id, my_id,
								guangjie_fenlei_Tag);

						list.add(guangjie_Xiangqing);
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
