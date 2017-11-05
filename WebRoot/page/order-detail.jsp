
<%@page import="com.magicmoble.luzhouapp.business.Dingdan_Business"%>
<%@page import="com.magicmoble.luzhouapp.model.Order"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>订单详情</title>
<link rel="stylesheet" type="text/css" href="../common/css/luzou.css" />
<link rel="stylesheet" type="text/css" href="common/css/luzou.css" />
<script src="../common/lib/jquery-1.9.0.min.js" type="text/javascript"
	charset="utf-8"></script>
<script src="common/lib/jquery-1.9.0.min.js" type="text/javascript"
	charset="utf-8"></script>
</head>

<%
	String order_id = request.getParameter("order_id");
	String _guangjie_fenlei_Tag = request.getParameter("guangjie_fenlei_Tag");
	int guangjie_fenlei_Tag = Integer.parseInt(_guangjie_fenlei_Tag);
	Order order = Dingdan_Business.order_click(order_id, "1", guangjie_fenlei_Tag);
	request.setAttribute("list", order); //
%> 

<body>
	<div class="order-detail">
		<div class="order-detail-title">
			<p class="detail-title">订单详情</p>
			<p>${list.transaction_id}</p>
			<p>${list.time }</p>
		</div>
		<div class="detail-goods">
			<ul>
				<li class="show-goods clearfix"><span class="goods-img">
						<img src="../common/image/bg.png" />
				</span> <span class="goods-name">${list.title }</span></li>
				<li class="detail-price">单价<span>￥${list.price }</span> 数量<span>x${list.shuliang }</span>
					运费<span>${list.freight }元</span> 总价<span>${list.total_price }元</span>
				</li>
			</ul>
		</div>
		<div class="buyer">
			<div class="buyer-name clearfix">
				<p>
					<img src="../common/image/icon-7.png" />
				</p>
				<p>
					<span class="buyer-names">${list.buyer_name }</span><br /> <span
						class="buyer-con">${list.buyer_qianming }</span>
				</p>
			</div>
			<p class="buyer-contact">${list.name }     ${list.phone }</p>
			<p class="buyer-address">收货地址：${list.address }</p>
			<p class="buyer-address">备注：${list.beizhu }</p>
		</div>
		<div class="buyer-status">
			<span>${list.seller_zhuangtai_message }</span> <a href="javaScript:;"
				class="buyer-submit">确定</a>
		</div>
	</div>
</body>
</html>
