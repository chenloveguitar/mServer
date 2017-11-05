<%@page import="org.apache.struts2.components.If"%>
<%@page import="com.magicmoble.luzhouapp.model.Admin_xinxi"%>
<%@page import="com.magicmoble.luzhouapp.business.Admin_xinxi_Business"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>添加用户</title>
<link rel="stylesheet" type="text/css" href="../common/css/luzou.css" />
<link rel="stylesheet" type="text/css" href="common/css/luzou.css" />
<script src="../common/lib/jquery-1.9.0.min.js" type="text/javascript"
	charset="utf-8"></script>
<script src="common/lib/jquery-1.9.0.min.js" type="text/javascript"
	charset="utf-8"></script>
</head>

<%
	String id = request.getParameter("id");

	Admin_xinxi admin_xinxi = Admin_xinxi_Business.getAdmin_xinxiInfoById(id);
%>

<body>
	<div class="user-add">
		<div class="user-add-content">
			<h2>用户信息:</h2>

			<p>
				用户名id：
				<%=admin_xinxi.getAdmin_xinxi_id()%></p>
			<p>
				头像： <img src=" <%=admin_xinxi.getTouxiang_picture()%>"
					style="width:100px;height:50px;" />
			</p>
			<p>
				用户名：
				<%=admin_xinxi.getName()%></p>
			<p>
				微信号：
				<%=admin_xinxi.getWeichat()%></p>
			<p>
				性别：
				<%=admin_xinxi.getSex()%></p>
			<p>
				签名：
				<%=admin_xinxi.getQianming()%></p>
			<p>
				电话：
				<%=admin_xinxi.getPhone()%></p>
			<p>
				钱包：
				<%=admin_xinxi.getQianbao()%></p>
			<p>
				注册时间 ：
				<%=admin_xinxi.getTime()%></p>





		</div>
	</div>
</body>
</html>
