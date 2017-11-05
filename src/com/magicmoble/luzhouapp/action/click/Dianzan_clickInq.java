package com.magicmoble.luzhouapp.action.click;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.annotation.JsonTypeInfo.Id;
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
import com.magicmoble.luzhouapp.model.Toutiao;

public class Dianzan_clickInq extends HttpServlet {

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
		String my_id = request.getParameter("my_id");// 用户id
		String tiaomu_id = request.getParameter("tiaomu_id");// 对象id
		String dianzan_Tag2 = request.getParameter("dianzan_Tag");// 点赞对象类型（1：去处，2：说说，3：头条，4：发现，5：服务，6：商品，7:评论）
		String iscancel2 = request.getParameter("cancel");//1:点赞，2：取消点赞
		String releaser_id=request.getParameter("releaser_id");
		int dianzan_Tag = 0;
		int iscancel = 0;
		try {
			if (dianzan_Tag2 != null) {
				dianzan_Tag = Integer.parseInt(dianzan_Tag2);
			}
			if (iscancel2 != null) {
				iscancel = Integer.parseInt(iscancel2);
			}
			if (my_id == null || tiaomu_id == null || dianzan_Tag2 == null || iscancel2 == null) {
				DataObject dataObject = new DataObject();
				dataObject.setdata("参数不足");
				dataObject.setStatusObject(StatusHouse.COMMON_STATUS_ERROR);
				String responseText = JackJsonUtils.toJson(dataObject);

				ResponseUtils.renderJson(response, responseText);
			} else {
				if (iscancel == 1) {
					FunctionBusiness.addDianzan(my_id, tiaomu_id, dianzan_Tag);
					FunctionBusiness.addmessage(my_id,releaser_id, tiaomu_id, "给你点了赞","");
					JiguangPush.push(releaser_id, "有人给你点了赞");
					DataObject dataObject = new DataObject();
					dataObject.setdata("点赞成功");
					dataObject.setStatusObject(StatusHouse.COMMON_STATUS_OK);
					String responseText = JackJsonUtils.toJson(dataObject);
					ResponseUtils.renderJson(response, responseText);
				} else if (iscancel == 2) {
					FunctionBusiness.DeleteDianzan(my_id, tiaomu_id, dianzan_Tag);
					DataObject dataObject = new DataObject();
					dataObject.setdata("取消点赞成功");
					dataObject.setStatusObject(StatusHouse.COMMON_STATUS_OK);
					String responseText = JackJsonUtils.toJson(dataObject);
					ResponseUtils.renderJson(response, responseText);
				}

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
