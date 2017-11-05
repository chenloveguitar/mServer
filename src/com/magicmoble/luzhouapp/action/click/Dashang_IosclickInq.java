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
import com.magicmoble.luzhouapp.business.JiguangPush;
import com.magicmoble.luzhouapp.business.ToutiaoBusiness;
import com.magicmoble.luzhouapp.json.core.DataObject;
import com.magicmoble.luzhouapp.json.core.ListObject;
import com.magicmoble.luzhouapp.json.responseUtils.ResponseUtils;
import com.magicmoble.luzhouapp.json.status.StatusHouse;
import com.magicmoble.luzhouapp.json.utils.JackJsonUtils;
import com.magicmoble.luzhouapp.model.Faxian;
import com.magicmoble.luzhouapp.model.Toutiao;

public class Dashang_IosclickInq extends HttpServlet {

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
		String dashang_prices = request.getParameter("dashang_price");
		String my_id = request.getParameter("my_id");
		String duixiang_id = request.getParameter("duixiang_id");
		String tiaomu_id = request.getParameter("tiaomu_id");
		String fenlei_Tags = request.getParameter("fenlei_Tag");// 1:头条 2：发现秘密
			String releaser_id=request.getParameter("releaser_id");													// 3：去处 4 商品服务
		int fenlei_Tag = 0;
		double dashang_price = 0;

		if (dashang_prices != null) {
			dashang_price = Double.parseDouble(dashang_prices);
		}
		if (fenlei_Tags != null) {
			fenlei_Tag = Integer.parseInt(fenlei_Tags);
		}
		try {
			String str = DashangBusiness.Dashang_Iosclick(tiaomu_id, my_id, duixiang_id, dashang_price, fenlei_Tag);
			FunctionBusiness.addmessage(my_id,duixiang_id, tiaomu_id, "有人给你打赏了", "打赏了"+dashang_price+"元");
			JiguangPush.push(duixiang_id, "有人打赏了你");
			DataObject dataObject = new DataObject();
			dataObject.setdata(str);
			dataObject.setStatusObject(StatusHouse.COMMON_STATUS_OK);
			String responseText = JackJsonUtils.toJson(dataObject);
			ResponseUtils.renderJson(response, responseText);
		} catch (NullPointerException e) {
			DataObject dataObject = new DataObject();
			dataObject.setdata("请传入正确参数");
			dataObject.setStatusObject(StatusHouse.COMMON_STATUS_ERROR);
			String responseText = JackJsonUtils.toJson(dataObject);

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
