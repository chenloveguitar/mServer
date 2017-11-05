<%@ page language="java" contentType="text/html; charset=GBK"
	pageEncoding="utf-8"%>
<%@ page
	import="com.magicmoble.luzhouapp.Util.Monery.weixin.ResponseHandler"%>
<%@ page
	import="java.util.*,
com.magicmoble.luzhouapp.action.weixin_pay.AppPaymentManager,
org.springframework.web.context.support.WebApplicationContextUtils,
org.springframework.context.ApplicationContext,java.io.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
	//---------------------------------------------------------
	//财付通支付通知（后台通知）示例，商户按照此文档进行开发即可
	//---------------------------------------------------------

	//创建支付应答对象
	ResponseHandler resHandler = new ResponseHandler(request, response);

	//判断签名
	if (resHandler.isTenpaySign()) {
		SortedMap<String, String> queryRes = resHandler.getAllParameters();
		String result_code = queryRes.get("result_code");
		if ("SUCCESS".equals(result_code)) {
			System.out.println("订单查询成功");
			//取结果参数做业务处理
			System.out.println("out_trade_no:" + queryRes.get("out_trade_no") + " transaction_id:"
					+ queryRes.get("transaction_id"));
			System.out.println(
					"trade_state:" + queryRes.get("trade_state") + " total_fee:" + queryRes.get("total_fee"));
			//如果有使用折扣券，discount有值，total_fee+discount=原请求的total_fee
			System.out
					.println("discount:" + queryRes.get("discount") + " time_end:" + queryRes.get("time_end"));
			//------------------------------
			//处理业务开始
			//------------------------------

			ServletContext sc = getServletContext();
			ApplicationContext ac2 = WebApplicationContextUtils.getWebApplicationContext(sc);
			AppPaymentManager appPaymentManager = (AppPaymentManager) ac2.getBean("appPaymentManager");

			appPaymentManager.payOrderFinish(queryRes.get("out_trade_no"));

			//处理数据库逻辑
			//注意交易单不要重复处理
			//注意判断返回金额

			//------------------------------
			//处理业务完毕
			//------------------------------
			resHandler.sendToCFT("Success");
		} else {
			//错误时，返回结果未签名，记录retcode、retmsg看失败详情。
			System.out.println("查询验证签名失败或业务错误");

		}
	} else {
		System.out.println("通知签名验证失败");
	}
%>

