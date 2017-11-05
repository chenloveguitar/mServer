package com.magicmoble.luzhouapp.action.weixin_pay.RechargeInq;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.SortedMap;
import java.util.TreeMap;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.magicmoble.luzhouapp.Util.Monery.weixin.WeiXinPayUtil;
import com.magicmoble.luzhouapp.Util.Monery.weixin.util.ConstantUtil;
import com.magicmoble.luzhouapp.business.Dingdan_Business;
import com.magicmoble.luzhouapp.json.core.DataObject;
import com.magicmoble.luzhouapp.json.responseUtils.ResponseUtils;
import com.magicmoble.luzhouapp.json.status.StatusHouse;
import com.magicmoble.luzhouapp.json.utils.JackJsonUtils;

public class Weixin_Pay_RechargeInq extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public static String orderId;
	public static String my_id;

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);

	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String orderContent = request.getParameter("orderContent");// 商品描述
		String _price = request.getParameter("price");// 价格
		my_id = request.getParameter("my_id");
		orderId = request.getParameter("transaction_id");

		if (orderId == null) {
			int r1 = (int) (Math.random() * (10));// 产生2个0-9的随机数
			int r2 = (int) (Math.random() * (10));
			long now = System.currentTimeMillis();// 一个13位的时间戳
			String paymentID = String.valueOf(r1) + String.valueOf(r2) + String.valueOf(now);// 订单ID
			orderId = paymentID;
		}

		SortedMap<Object, Object> map = new TreeMap<Object, Object>();
		String sign = null;
		double price = 0;
		if (_price != null) {
			price = Double.parseDouble(_price);
		}
		try {
			map = WeiXinPayUtil.weiXinPay("192.168.0.1", orderContent, price, orderId,
					"http://120.92.169.86/mServer/Notify_RechargeInq");

			Dingdan_Business.addPaylist(my_id, orderId);

			DataObject dataObject = new DataObject();
			dataObject.setdata(map);
			dataObject.setStatusObject(StatusHouse.COMMON_STATUS_OK);
			String responseText = JackJsonUtils.toJson(dataObject);
			ResponseUtils.renderJson(response, responseText);

		} catch (Exception e) {
		}

	}

}
