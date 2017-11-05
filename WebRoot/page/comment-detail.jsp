<%@page
	import="com.magicmoble.luzhouapp.server.server_function.Server_Func"%>
<%@page
	import="com.magicmoble.luzhouapp.server.server_function.Server_Function"%>
<%@page import="com.magicmoble.luzhouapp.business.FunctionBusiness"%>
<%@page import="com.magicmoble.luzhouapp.model.Huifu_list"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>评论回复</title>
<link rel="stylesheet" type="text/css" href="../common/css/luzou.css" />
<script src="../common/lib/jquery-1.9.0.min.js" type="text/javascript"
	charset="utf-8"></script>
</head>
<%
	String id = request.getParameter("id");
	List<Huifu_list> list = FunctionBusiness.getHuifu_list(id, "1", 1);
	request.setAttribute("list", list);
%>


<body>
	<div class="user-add">
		<div class="restore-content">
			<p class="comment-header">评论详情</p>
			<ul class="comment-list">
				<c:forEach var="list" items="${list }">
					<li><span class="comment-detail-content">${list.huifu_content}</span>
						<span class="comment-detail-per">${list.huifuer_name}</span> <span
						class="comment-detail-per">${list.time}</span> <span
						class="comment-delete" id="${list.huifu_id}">删除</span></li>
				</c:forEach>
			</ul>
		</div>
	</div>
	<script type="text/javascript">
			$(function(){
						$(".comment-delete").click(function(){
							$.ajax({
							url : "/mServer/Handle_huifu",
							type : "POST",
							data : {
								"pinglun_id" : <%=id%>,
								"huifu_id" : $(this).attr("id");
							},
							dataType : "json",
							success : function(message) {
								var str = "";
								for (var i in message) {
									
							str+="		<li><span class=\"comment-detail-content\">"+message[huifu_content]+"</span>"
							str+="		<span class=\"comment-detail-per\">"+message[huifuer_name]+"</span> <span"
							str+="		class=\"comment-detail-per\">"+message[time]+"</span> <span"
							str+="		class=\"comment-delete\" id=\""+message[huifu_id]+"\">删除</span></li>"
			
			
								}
			
			
								$("#comment-list").html(str);
							},
							error : function() {
								alert("error");
							}
						});
					})
				})
		
		</script>
</body>
</html>
