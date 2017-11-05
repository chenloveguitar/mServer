package com.magicmoble.luzhouapp.action.fabu;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.magicmoble.luzhouapp.business.CommodityBusiness;
import com.magicmoble.luzhouapp.business.FuwuBusiness;
import com.magicmoble.luzhouapp.json.core.DataObject;
import com.magicmoble.luzhouapp.json.core.ListObject;
import com.magicmoble.luzhouapp.json.responseUtils.ResponseUtils;
import com.magicmoble.luzhouapp.json.status.StatusHouse;
import com.magicmoble.luzhouapp.json.utils.JackJsonUtils;
import com.magicmoble.luzhouapp.model.Guangjie_Xiangqing;

public class Guangjie_clickInq extends HttpServlet {

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
		String guangjie_fenlei_Tag = request.getParameter("guangjie_fenlei_Tag");
		int page = 0;
		int guangjie_fenlei_Tag1 = 0;
		Guangjie_Xiangqing guangjie_Xiangqing = null;
		try {

			if (guangjie_fenlei_Tag != null) {
				guangjie_fenlei_Tag1 = Integer.parseInt(guangjie_fenlei_Tag);

			}
			if (id == null || my_id == null || guangjie_fenlei_Tag == null) {
				DataObject dataObject = new DataObject();
				dataObject.setdata("参数不足");
				dataObject.setStatusObject(StatusHouse.COMMON_STATUS_ERROR);
				String responseText = JackJsonUtils.toJson(dataObject);

				ResponseUtils.renderJson(response, responseText);
			} else {

				if (guangjie_fenlei_Tag1 == 1) {
					guangjie_Xiangqing = CommodityBusiness.getGuangjie_CommodityById(id, my_id, guangjie_fenlei_Tag1);

					CommodityBusiness.commodity_yuedu(id);
				} else if (guangjie_fenlei_Tag1 == 2) {
					guangjie_Xiangqing = FuwuBusiness.getGuangjie_FuwuById(id, my_id, guangjie_fenlei_Tag1);

					FuwuBusiness.Fuwu_yuedu(id);
				}

				List<Guangjie_Xiangqing> guangjie_Xiangqings = new ArrayList<Guangjie_Xiangqing>();
				guangjie_Xiangqings.add(guangjie_Xiangqing);
				ListObject listObject = new ListObject();
				listObject.setResult(guangjie_Xiangqings);
				DataObject dataObject = new DataObject();
				dataObject.setdata(listObject);
				dataObject.setStatusObject(StatusHouse.COMMON_STATUS_OK);
				String responseText = JackJsonUtils.toJson(dataObject);
				ResponseUtils.renderJson(response, responseText);
			}
		} 
//		catch (NullPointerException e) {
//			DataObject dataObject = new DataObject();
//			dataObject.setdata("请传入正确参数");
//			dataObject.setStatusObject(StatusHouse.COMMON_STATUS_ERROR);
//			String responseText = JackJsonUtils.toJson(dataObject);
//
//			ResponseUtils.renderJson(response, responseText);
//		} 
		catch (ArrayIndexOutOfBoundsException e) {
			DataObject dataObject = new DataObject();
			dataObject.setdata("json参数错误");
			dataObject.setStatusObject(StatusHouse.COMMON_STATUS_ERROR);
			String responseText = JackJsonUtils.toJson(dataObject);

			ResponseUtils.renderJson(response, responseText);
		} 
//		catch (Exception e) {
//			DataObject dataObject = new DataObject();
//			dataObject.setdata("未知错误");
//			dataObject.setStatusObject(StatusHouse.COMMON_STATUS_ERROR);
//			String responseText = JackJsonUtils.toJson(dataObject);
//
//			ResponseUtils.renderJson(response, responseText);
//		}
	}

}
