<%@page import="com.magicmoble.luzhouapp.model.Pinglun"%>
<%@page import="com.magicmoble.luzhouapp.action.click.Pinglun_listInq"%>
<%@page import="com.magicmoble.luzhouapp.model.Pinglun_list"%>
<%@page import="com.magicmoble.luzhouapp.business.FunctionBusiness"%>
<%@page import="javax.servlet.jsp.tagext.FunctionInfo"%>
<%@page import="com.magicmoble.luzhouapp.model.server.Toutiao"%>
<%@page
	import="com.magicmoble.luzhouapp.server.server_function.Server_Function"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>内容详情</title>
<link rel="stylesheet" type="text/css" href="../common/css/luzou.css" />
<script src="../common/lib/jquery-1.9.0.min.js" type="text/javascript"
	charset="utf-8"></script>
</head>


<%
	String chaxun_id = request.getParameter("chaxun_id");
	String _Tag = request.getParameter("Tag");
	int Tag = Integer.parseInt(_Tag);
	Toutiao list = Server_Function.chaxun(chaxun_id, Tag);
	List<Pinglun> list2 = FunctionBusiness.getPinglun("1", list.getId(), 0);
	request.setAttribute("list", list);
	request.setAttribute("list2", list2);
%>
<body>
	<div class="content">
		<div class="content-container">
			<div class="content-detail-title">
				<p>内容详情</p>
			</div>
			<div class="content-detail-pic">${list.content }</div>
			<div class="content-detail-comment">
				<div class="content-detail-title">
					<p>评论</p>
				</div>
				<div class="content-detail-user">
					<ul>
						<c:forEach var="list" items="${list2 }">
							<li>
								<div class="content-detail-header clearfix">
									<p class="icon-head">
										<img src="../common/image/icon-6.png" />
									</p>
									<p>
										<span class="content-detail-userName">${list.pingluner_name }</span>
										<span class="content-detail-rate"> ${list.time } <span
											class="content-detail-sign">${list.pingluner_qianming }</span>
										</span>
									</p>

								</div>

								<p class="write-comment">${list.content }</p>

							</li>
						</c:forEach>
					</ul>
				</div>
			</div>
			<div class="content-detail-comment comments">
				<div class="content-detail-title">
					<p>是否审核通过</p>
				</div>
				<div class="content-detail-user users">
					<input type="radio" name="no" id="" value="是" />是 <input
						type="radio" name="no" id="" value="否" />否
				</div>
			</div>
			<button class="content-detail-save" id="tijiao">提交</button>
		</div>

	</div>
</body>
<script type="text/javascript">
	$(function() {
		$("#tijiao").click(function() {
			$.ajax({
				url : "/mServer/Handle_shenhe_neirong",
				type : "POST",
				data : {
					"shenhe" : $("input[name='no']:checked").val(),
					"id" : "<%=chaxun_id%>",
					"Tag" : "<%=Tag%>"
				},
				dataType : "json",
				success : function(message) {
					alert(message);
					window.location.href = "shenhe_Success.jsp?Tag=<%=Tag%>"


				},
				error : function() {
					alert("error");
					window.location.href = "shenhe_Success.jsp?Tag=<%=Tag%>"
				}
			});
		})

	})
</script>



</html>
