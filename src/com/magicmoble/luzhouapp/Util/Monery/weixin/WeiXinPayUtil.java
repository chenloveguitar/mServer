package com.magicmoble.luzhouapp.Util.Monery.weixin;

import com.alibaba.fastjson.JSON;
import com.magicmoble.luzhouapp.Util.Monery.weixin.util.*;

import java.util.HashMap;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

/**
 * Created by mangues on 16/6/29.
 */
public class WeiXinPayUtil {
	public static SortedMap<Object, Object> weiXinPay(String ip, String orderContent, Double price, String orderId,
			String url) throws Exception {
		Map<Object, Object> resInfo = new HashMap<Object, Object>();
		// ---------------生成订单号 开始------------------------
		// 当前时间 yyyyMMddHHmmss
		String currTime = TenpayUtil.getCurrTime();
		// 8位日期
		String strTime = currTime.substring(8, currTime.length());
		// 四位随机数
		String strRandom = TenpayUtil.buildRandom(4) + "";
		// 10位序列号,可以自行调整。
		String strReq = strTime + strRandom;
		// 订单号，此处用时间加随机数生成，商户根据自己情况调整，只要保持全局唯一就行
		String out_trade_no = strReq;
		// ---------------生成订单号 结束------------------------

		SortedMap<Object, Object> parameters = new TreeMap<Object, Object>();
		int retcode;
		String retmsg = "";
		String xml_body = "";
		// 设置package订单参数
		orderContent=new String(orderContent.getBytes("utf-8"),"ISO-8859-1");
		// packageReqHandler.setParameter("bank_type", "WX");//银行渠道
		parameters.put("body", orderContent); // 商品描述
		parameters.put("notify_url", url); // 接收财付通通知的URL
		parameters.put("mch_id", ConstantUtil.mch_id); // 商户号
		parameters.put("out_trade_no", orderId + "_" + out_trade_no); // 商家订单号+随机数
		parameters.put("total_fee", (int) (price * 100) + ""); // 商品金额,以分为单位
		// //订单生成的机器IP，指用户浏览器端IP
		parameters.put("spbill_create_ip", ip); // 订单生成的机器IP，指用户浏览器端IP
		parameters.put("fee_type", "1"); // 币种，1人民币 66
		parameters.put("trade_type", "APP"); // 字符编码
		parameters.put("device_info", "WEB"); // 字符编码
		// prepayReqHandler.setParameter("input_charset", "GBK"); //字符编码
		String noncestr = WXUtil.getNonceStr();
		//// 设置获取prepayid支付参数
		parameters.put("appid", ConstantUtil.APP_ID);
		parameters.put("nonce_str", noncestr);
		// 生成获取预支付签名
		String sign = WXUtil.createSign(parameters);
		// 增加非参与签名的额外参数
		parameters.put("sign", sign);
		resInfo.put("retmsg", WXUtil.getXmlBody(parameters));
		String xml = WXUtil.getXmlBody(parameters);

		byte[] bytess = HttpUtils.postXml("https://api.mch.weixin.qq.com/pay/unifiedorder", xml, "ISO-8859-1");
		String s = new String(bytess, "UTF-8");
		System.out.println("s:" + s);
		SortedMap<Object, Object> finalpackage = new TreeMap<Object, Object>();
		String timestamp = WXUtil.getTimeStamp();
		finalpackage.put("appid", ConstantUtil.APP_ID);
		finalpackage.put("timestamp", timestamp);
		finalpackage.put("noncestr", noncestr);
		finalpackage.put("partnerid", ConstantUtil.mch_id);
		finalpackage.put("package", "Sign=WXPay");

		try {
			finalpackage.put("prepayid", XMLUtil.doXMLParse(s).get("prepay_id"));
			if (XMLUtil.doXMLParse(s).get("return_code").equals("FAIL")) {
				throw new Exception();
			}
		} catch (Exception e) {

		}
		String finalsign = WXUtil.createSign(finalpackage);
		finalpackage.put("sign", finalsign);

		return finalpackage;

	}
}
