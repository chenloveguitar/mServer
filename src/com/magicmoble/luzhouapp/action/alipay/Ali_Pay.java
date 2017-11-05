package com.magicmoble.luzhouapp.action.alipay;

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

public class Ali_Pay extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public static String orderId;
	public static String my_id;

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);

	}

	public void doPost(HttpServletRequest request1, HttpServletResponse response1)
			throws ServletException, IOException {

		String orderContent = request1.getParameter("orderContent");// 商品描述
		String _price = request1.getParameter("price");// 价格
		String orderName = request1.getParameter("orderName");// 商品名称
		orderId = request1.getParameter("transaction_id");// 商品名称
		AlipayTradeAppPayResponse response = null;

		my_id = request1.getParameter("my_id");
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
		request.setNotifyUrl("http://120.92.169.86/mServer/Ali_Notify");
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

		// AlipayClient alipayClient = new
		// DefaultAlipayClient("https://openapi.alipay.com/gateway.do","2015120200901473","MIICeAIBADANBgkqhkiG9w0BAQEFAASCAmIwggJeAgEAAoGBANX63iTzhUFhh/6cbuj9pjDNYt1EdJcRolr3lFrBC9poVOhG4phGQPCFN0ZjtjBsfj3Tb8oWBe8KORMNeeI/dwraqy1WvHs7VRWtXGlGjIZT5W8Plw4f/M70iNC3l8fMuIX76ckmpqWQO7uH0pzEWmQQ3VJb7WGJJVigz7FUlDVjAgMBAAECgYAAj8P9fNYgsczVkjuISQIipqdXxVI5A0bVzkiJZHBPhm6SnX0CwUxyVScr/B8CSYabSu7ELQKe+fvkXOiw+1GoqGbyijK1uFSzqahsM2nKjSiwV6pIslad9oj5ykya6vwdZPlBOSsJAMz96EylAunWQ/kfTUp5jGPnTz2BB8kFIQJBAPt0tiVS5SacteSTWQJA7qrWRY1lzAqywGYNN1Okw7yIa7S9koKJVVGq0hR4H298FEIzUzuiKJ1Scpx7c0PVUqcCQQDZ2MjWQAif/9NXisMdnARiv4g8y4KlMctaex1yPpmCvmwBvcffQ8Ga2kbI24PxX4il5pxHUudC9xD3NhJ420rlAkEAqlJguiFPE/tb/UqNJMikoNToYaFNNwXi6n0TN7XH22EwS0mI/qBVSS8Cg1jmOhZ6hmjLfP4WspFRDotUBONuywJBAL4IBoInD58K2gBGkpf0rjq524009f1Gp56BQRmUk/LiS3NlasJO58c+W3sBSmK3NIuL1I2/Ou9GeK09pPV6TiUCQQCkmDYc+NUoGt9dMleYbjo5fbbgfHblPdjENS5FUnm3fhgTwboebgfC5ZhzahxFDQl95utIFK3IbU/RO9mbvGQa","json","UTF-8","MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDV+t4k84VBYYf+nG7o/aYwzWLdRHSXEaJa95RawQvaaFToRuKYRkDwhTdGY7YwbH4902/KFgXvCjkTDXniP3cK2qstVrx7O1UVrVxpRoyGU+VvD5cOH/zO9IjQt5fHzLiF++nJJqalkDu7h9KcxFpkEN1SW+1hiSVYoM+xVJQ1YwIDAQAB","RSA");
		// AlipayTradePayRequest request = new AlipayTradePayRequest();
		// request.setBizContent("{" +
		// "\"out_trade_no\":\""+orderId+"\"," +
		// "\"scene\":\"bar_code\"," +
		// "\"auth_code\":\"28763443825664394\"," +
		// "\"product_code\":\"FACE_TO_FACE_PAYMENT\"," +
		// "\"subject\":\"Iphone6 16G\"," +
		// "\"buyer_id\":\"2088202954065786\"," +
		// "\"seller_id\":\"2088102146225135\"," +
		// "\"total_amount\":88.88," +
		// "\"discountable_amount\":8.88," +
		// "\"undiscountable_amount\":80.00," +
		// "\"body\":\"Iphone6 16G\"," +
		// " \"goods_detail\":[{" +
		// " \"goods_id\":\"apple-01\"," +
		// "\"alipay_goods_id\":\"20010001\"," +
		// "\"goods_name\":\"ipad\"," +
		// "\"quantity\":1," +
		// "\"price\":2000," +
		// "\"goods_category\":\"34543238\"," +
		// "\"body\":\"特价手机\"," +
		// "\"show_url\":\"http://www.alipay.com/xxx.jpg\"" +
		// " }]," +
		// "\"operator_id\":\"yx_001\"," +
		// "\"store_id\":\"NJ_001\"," +
		// "\"terminal_id\":\"NJ_T_001\"," +
		// "\"alipay_store_id\":\"2016041400077000000003314986\"," +
		// "\"extend_params\":{" +
		// "\"sys_service_provider_id\":\"2088511833207846\"," +
		// "\"hb_fq_num\":\"3\"," +
		// "\"hb_fq_seller_percent\":\"100\"" +
		// " }," +
		// "\"timeout_express\":\"90m\"," +
		// "\"royalty_info\":{" +
		// "\"royalty_type\":\"ROYALTY\"," +
		// " \"royalty_detail_infos\":[{" +
		// " \"serial_no\":1," +
		// "\"trans_in_type\":\"userId\"," +
		// "\"batch_no\":\"123\"," +
		// "\"out_relation_id\":\"20131124001\"," +
		// "\"trans_out_type\":\"userId\"," +
		// "\"trans_out\":\"2088101126765726\"," +
		// "\"trans_in\":\"2088101126708402\"," +
		// "\"amount\":0.1," +
		// "\"desc\":\"分账测试1\"," +
		// "\"amount_percentage\":\"100\"" +
		// " }]" +
		// " }," +
		// "\"sub_merchant\":{" +
		// "\"merchant_id\":\"19023454\"" +
		// " }," +
		// "\"disable_pay_channels\":\"credit_group\"," +
		// "\"ext_user_info\":{" +
		// "\"name\":\"李明\"," +
		// "\"mobile\":\"16587658765\"," +
		// "\"cert_type\":\"IDENTITY_CARD\"," +
		// "\"cert_no\":\"362334768769238881\"," +
		// "\"fix_buyer\":\"F\"" +
		// " }" +
		// " }");
		// AlipayTradePayResponse response = alipayClient.execute(request);
		// if(response.isSuccess()){
		// System.out.println("调用成功");
		// } else {
		// System.out.println("调用失败");
		// }
		//
		//

		// } catch (Exception e) {
		// System.out.println("111111111111111111111111111111111");
		// DataObject dataObject = new DataObject();
		// dataObject.setdata(e.getMessage());
		// dataObject.setStatusObject(StatusHouse.COMMON_STATUS_OK);
		// String responseText = JackJsonUtils.toJson(dataObject);
		// ResponseUtils.renderJson(response, responseText);
		//
		// }

	}

}
