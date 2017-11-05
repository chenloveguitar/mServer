package com.magicmoble.luzhouapp.action.weixin_pay.buy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jdom.JDOMException;

import com.magicmoble.luzhouapp.Util.Monery.weixin.util.XMLUtil;
import com.magicmoble.luzhouapp.action.alipay.Recharge.Ali_Pay_RechargeInq;
import com.magicmoble.luzhouapp.business.Dingdan_Business;
import com.magicmoble.luzhouapp.business.QianbaoBusiness;

public class Notify_BuyInq extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		InputStream ip = request.getInputStream();

		BufferedReader reader = new BufferedReader(new InputStreamReader(ip));
		StringBuilder sb = new StringBuilder();
		String line = null;
		while ((line = reader.readLine()) != null) {
			sb.append(line + "\n");
		}
		ip.close();

		String str = sb.toString();

		Map<String, String> map = new HashMap<String, String>();
		try {
			map = XMLUtil.doXMLParse(str);

			if (map.get("return_code").equals("SUCCESS")) {
				Dingdan_Business.setNotify("SUCCESS", Weixin_Pay_BuyInq.orderId);
				Dingdan_Business.payment(Weixin_Pay_BuyInq.orderId, Integer.parseInt(map.get("cash_fee")) / 100,
						Weixin_Pay_BuyInq.my_id);

			}

		} catch (JDOMException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
