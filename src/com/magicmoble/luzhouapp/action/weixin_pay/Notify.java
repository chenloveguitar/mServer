package com.magicmoble.luzhouapp.action.weixin_pay;

import org.springframework.web.context.support.WebApplicationContextUtils;
import org.jdom.JDOMException;
import org.springframework.context.ApplicationContext;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.SortedMap;
import java.util.function.IntPredicate;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.magicmoble.luzhouapp.Util.Monery.weixin.ResponseHandler;
import com.magicmoble.luzhouapp.Util.Monery.weixin.util.XMLUtil;
import com.magicmoble.luzhouapp.action.weixin_pay.RechargeInq.Weixin_Pay_RechargeInq;
import com.magicmoble.luzhouapp.business.Dingdan_Business;
import com.magicmoble.luzhouapp.business.QianbaoBusiness;

public class Notify extends HttpServlet {

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
				Dingdan_Business.setNotify("SUCCESS", Weixin_Pay_RechargeInq.orderId);
				QianbaoBusiness.Recharge(Weixin_Pay_RechargeInq.my_id, Integer.parseInt(map.get("total_fee")));

			}

		} catch (JDOMException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
