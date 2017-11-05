package com.magicmoble.luzhouapp.Util.Monery.weixin.util;

import java.io.UnsupportedEncodingException;
import java.util.*;
import java.util.Map.Entry;

import javax.print.DocFlavor.STRING;

public class WXUtil {

	public static String getNonceStr() {
		Random random = new Random();
		return MD5Util.MD5Encode(String.valueOf(random.nextInt(10000)), "UTF-8");
	}

	public static String getTimeStamp() {
		return String.valueOf(System.currentTimeMillis() / 1000);
	}

	/**
	 * 微信支付签名算法sign
	 * 
	 * @param parameters
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static String createSign(SortedMap<Object, Object> parameters) {

		StringBuffer sb = new StringBuffer();
		Set es = parameters.entrySet();// 所有参与传参的参数按照accsii排序（升序）
		Iterator it = es.iterator();
		while (it.hasNext()) {
			Map.Entry entry = (Map.Entry) it.next();
			String k = (String) entry.getKey();
			Object v = entry.getValue();
			if (null != v && !"".equals(v) && !"sign".equals(k) && !"key".equals(k)) {
				sb.append(k + "=" + v + "&");
			}
		}
		sb.append("key=" + ConstantUtil.PARTNER_KEY);
		String sign = MD5Util.MD5Encode(sb.toString(), "UTF-8").toUpperCase();
		System.out.println("签名：" + sign);
		return sign;

	}

	public static String getXmlBody(SortedMap<Object, Object> parameters) {
		StringBuffer sb = new StringBuffer();
		Set es = parameters.entrySet();
		Iterator it = es.iterator();
		String sign = null;
		sb.append("<xml>");
		while (it.hasNext()) {
			Map.Entry entry = (Map.Entry) it.next();
			String k = (String) entry.getKey();
			String v = (String) entry.getValue();
			if (!"appkey".equals(k) && !"sign".equals(k)) {
				sb.append("<" + k + ">" + v + "</" + k + ">" + "\r\n");
			}
			if ("sign".equals(k)) {
				sign = v;
			}
		}
		sb.append("<sign>" + sign + "</sign>" + "\r\n");
		sb.append("</xml>");
		return sb.toString();

	}

}
