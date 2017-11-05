package com.magicmoble.luzhouapp.action.alipay.Recharge;

import org.springframework.web.context.support.WebApplicationContextUtils;
import org.jdom.JDOMException;
import org.springframework.context.ApplicationContext;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.SortedMap;
import java.util.function.IntPredicate;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alipay.api.domain.PubChannelDTO;
import com.fasterxml.jackson.annotation.JsonTypeInfo.Id;
import com.magicmoble.luzhouapp.Util.Monery.weixin.ResponseHandler;
import com.magicmoble.luzhouapp.Util.Monery.weixin.util.XMLUtil;
import com.magicmoble.luzhouapp.action.weixin_pay.RechargeInq.Weixin_Pay_RechargeInq;
import com.magicmoble.luzhouapp.business.Dingdan_Business;
import com.magicmoble.luzhouapp.business.QianbaoBusiness;

public class Ali_Notify_RechargeInq extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static Map<String, String> params = null;

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		params = new HashMap<String, String>();
		Map requestParams = request.getParameterMap();
		for (Iterator iter = requestParams.keySet().iterator(); iter.hasNext();) {
			String name = (String) iter.next();
			String[] values = (String[]) requestParams.get(name);
			String valueStr = "";
			for (int i = 0; i < values.length; i++) {
				valueStr = (i == values.length - 1) ? valueStr + values[i] : valueStr + values[i] + ",";
			}
			// 乱码解决，这段代码在出现乱码时使用。
			valueStr = new String(valueStr.getBytes("ISO-8859-1"), "utf-8");
			params.put(name, valueStr);
		}

		if (params.get("trade_status").equals("TRADE_SUCCESS")) {
			Dingdan_Business.setNotify("SUCCESS", Ali_Pay_RechargeInq.orderId);
			QianbaoBusiness.Recharge(Ali_Pay_RechargeInq.my_id, Double.parseDouble(params.get("buyer_pay_amount")));
System.out.println("success");
		}

	}

}
