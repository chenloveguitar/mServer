package com.magicmoble.luzhouapp.action.click;

import java.io.IOException;
import java.util.IllegalFormatCodePointException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.omg.PortableServer.ID_ASSIGNMENT_POLICY_ID;

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
import com.magicmoble.luzhouapp.model.Hongbao;
import com.magicmoble.luzhouapp.model.Toutiao;

public class Tuijian_clickInq extends HttpServlet {

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
		String tiaomu_id = request.getParameter("tiaomu_id");

		String dianzan_hongbao_counts = request.getParameter("dianzan_hongbao_count");
		String fenxiang_hongbao_counts = request.getParameter("fenxiang_hongbao_count");
		String dianzan_hongbao_prices = request.getParameter("dianzan_hongbao_price");
		String fenxiang_hongbao_prices = request.getParameter("fenxiang_hongbao_price");
		String tianshus = request.getParameter("tuijian_tianshu");

		int dianzan_hongbao_count = 0;
		int fenxiang_hongbao_count = 0;
		double dianzan_hongbao_price = 0.0;
		double fenxiang_hongbao_price = 0.0;
		int tianshu = 0;
		String str = null;

		try {
			if (dianzan_hongbao_counts != null) {
				dianzan_hongbao_count = Integer.parseInt(dianzan_hongbao_counts);
			}
			if (fenxiang_hongbao_counts != null) {
				fenxiang_hongbao_count = Integer.parseInt(fenxiang_hongbao_counts);
			}
			if (dianzan_hongbao_prices != null) {
				dianzan_hongbao_price = Double.parseDouble(dianzan_hongbao_prices);
			}
			if (fenxiang_hongbao_prices != null) {
				fenxiang_hongbao_price =  Double.parseDouble(fenxiang_hongbao_prices);
			}
			if (tianshus != null) {
				tianshu = Integer.parseInt(tianshus);
			}
			if (my_id == null || tiaomu_id == null) {

				DataObject dataObject = new DataObject();
				dataObject.setdata("参数不足");
				dataObject.setStatusObject(StatusHouse.COMMON_STATUS_ERROR);
				String responseText = JackJsonUtils.toJson(dataObject);

				ResponseUtils.renderJson(response, responseText);
			} else {
				if (dianzan_hongbao_count != 0.0 && dianzan_hongbao_price != 0) {

					str = FunctionBusiness.setHongbao(my_id, tiaomu_id, dianzan_hongbao_count, dianzan_hongbao_price,
							1);
				}
				if (fenxiang_hongbao_count != 0.0 && fenxiang_hongbao_price != 0) {
					str = FunctionBusiness.setHongbao(my_id, tiaomu_id, fenxiang_hongbao_count, fenxiang_hongbao_price,
							2);
				}
				if (tianshus != null) {
					str = FunctionBusiness.setTuijian(my_id, tiaomu_id, tianshu);
				}

				DataObject dataObject = new DataObject();
				dataObject.setdata(str);
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
