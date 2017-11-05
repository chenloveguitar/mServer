package com.magicmoble.luzhouapp.action.alipay.buy;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.SortedMap;
import java.util.TreeMap;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.domain.AlipayTradeAppPayModel;
import com.alipay.api.request.AlipayTradeAppPayRequest;
import com.alipay.api.request.AlipayTradePayRequest;
import com.alipay.api.response.AlipayTradeAppPayResponse;
import com.alipay.api.response.AlipayTradePayResponse;
import com.magicmoble.luzhouapp.alipay.service.zhifubao.OrderConfig;
import com.magicmoble.luzhouapp.alipay.service.zhifubao.PayUtil;
import com.magicmoble.luzhouapp.business.Dingdan_Business;
import com.magicmoble.luzhouapp.json.core.DataObject;
import com.magicmoble.luzhouapp.json.responseUtils.ResponseUtils;
import com.magicmoble.luzhouapp.json.status.StatusHouse;
import com.magicmoble.luzhouapp.json.utils.JackJsonUtils;

public class Ali_Pay_BuyInq extends HttpServlet {

	/**
	 * 
	 */
	public static String orderId;
	public static String my_id;

	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);

	}

	public void doPost(HttpServletRequest request1, HttpServletResponse response1)
			throws ServletException, IOException {

		String orderContent = request1.getParameter("orderContent");// 商品描述
		String _price = request1.getParameter("price");// 价格
		String orderName = request1.getParameter("orderName");// 商品名称
		orderId = request1.getParameter("transaction_id");// 商品名称
		my_id = request1.getParameter("my_id");
		AlipayTradeAppPayResponse response = null;

		if (orderId == null) {
			int r1 = (int) (Math.random() * (10));// 产生2个0-9的随机数
			int r2 = (int) (Math.random() * (10));
			long now = System.currentTimeMillis();// 一个13位的时间戳
			String paymentID = String.valueOf(r1) + String.valueOf(r2) + String.valueOf(now);// 订单ID
			orderId = paymentID;
		}

		AlipayClient alipayClient = new DefaultAlipayClient("https://openapi.alipay.com/gateway.do", "2015120200901473",
				"MIICdQIBADANBgkqhkiG9w0BAQEFAASCAl8wggJbAgEAAoGBAMUUjQ+Xctz1ZhgS+0e+1DPqjRgaHRW5DxXnNcsv2tNFV9s/5FX7xM4CH9w3BQleftKsmNdIqGuSib183uOsP51ur1JtU8HAdwZ9+M5jPuOXUAXHfON0zt+7gKMtPPNwJ2pUFW2f3QxWFk5+Jx8DMvXCuptJtJDSJVDoHKCntRhZAgMBAAECgYBkXrgMpcQQ9kXdouUov+5Q1navPLVtV8dwD0XdSmz2xk0dU2fVxpiPlpNlUvwUrnSGlPJuSgnEZ2gRAiR97T6j58OhE6zuUJ4x1Fuf0TreuNqa/Seb7Dkz3Q+APqG7q5tEFHenbyrymmN3PJv5nBxMvwyXbrhgP4L5warLeDWSJQJBAP1jVnmjlkywE+PNyGCULBa0F0vxVAPwJyIrEqmWiYnnC1z3N1Ac0iSZiRVVHfy8wUtT8x0/PT077/aIRksLqWsCQQDHHJ+adLRlIZxZcBcN3g1asatfUqQY7OT9mclRINTas3QNUxbtbUVXxIDexGlCXWDfbQzLGuXjCzGF6YLrruJLAkBeNAlZTb06I5gxcDcX2N6PTkL/A+iVUjpqVJMqo4Hc2B38YRiaWyyxWLeDS9LLPK2sQLi43eL5mgf7ouxnSB2jAkAr7cG8twDRyx+Oj+9WLOZvWwF/L2Fq6mD/Hn3O+1pawfrZsbo+Obxxx7XS8Cx06tsjkBChgtazR9XuQH2U9+mPAkA5V4saPkWEUMByXVsqAJTSwnD0JDfgxhz1mDoT5IAXRUi8xmZdfwRZDvCb4CC0J4SHtBqSvIn5317dJyCpBaff",
				"json", "UTF-8",
				"MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDFFI0Pl3Lc9WYYEvtHvtQz6o0YGh0VuQ8V5zXLL9rTRVfbP+RV+8TOAh/cNwUJXn7SrJjXSKhrkom9fN7jrD+dbq9SbVPBwHcGffjOYz7jl1AFx3zjdM7fu4CjLTzzcCdqVBVtn90MVhZOficfAzL1wrqbSbSQ0iVQ6Bygp7UYWQIDAQAB",
				"RSA");
		// 实例化具体API对应的request类,类名称和接口名称对应,当前调用接口名称：alipay.trade.app.pay
		AlipayTradeAppPayRequest request = new AlipayTradeAppPayRequest();
		//
		// SDK已经封装掉了公共参数，这里只需要传入业务参数。以下方法为sdk的model入参方式(model和biz_content同时存在的情况下取biz_content)。
		AlipayTradeAppPayModel model = new AlipayTradeAppPayModel();
		model.setBody(orderContent);
		model.setSubject(orderName);
		model.setOutTradeNo(orderId);
		model.setTimeoutExpress("60m");
		model.setTotalAmount(_price);
		model.setProductCode("QUICK_MSECURITY_PAY");
		request.setBizModel(model);
		request.setNotifyUrl("http://120.92.169.86/mServer/Ali_Notify_BuyInq");
		try {
			// 这里和普通的接口调用不同，使用的是sdkExecute
			response = alipayClient.sdkExecute(request);
			Dingdan_Business.addPaylist(my_id, orderId);

			// 可以直接给客户端请求，无需再做处理。
		} catch (AlipayApiException e) {
			e.printStackTrace();
		}

		DataObject dataObject = new DataObject();
		dataObject.setdata(response.getBody());
		dataObject.setStatusObject(StatusHouse.COMMON_STATUS_OK);
		String responseText = JackJsonUtils.toJson(dataObject);
		ResponseUtils.renderJson(response1, responseText);

	}

}
