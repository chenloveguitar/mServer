<%@page
	import="com.magicmoble.luzhouapp.server.server_function.Server_Function"%>
<%@page import="com.magicmoble.luzhouapp.model.server.Admin"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title></title>
<link rel="stylesheet" type="text/css" href="../common/css/luzou.css" />
<link rel="stylesheet" type="text/css"
	href="../common/css/calendar1.css" />
<script src="../common/lib/jquery-1.9.0.min.js" type="text/javascript"
	charset="utf-8"></script>
<script src="../common/js/jquery.min.js" type="text/javascript"
	charset="utf-8"></script>
<script src="../common/js/jquery.date_input.pack.js"
	type="text/javascript" charset="utf-8"></script>
</head>

<%
	List<Admin> admins = Server_Function.getadmin();
	request.setAttribute("admin", admins);
%>

<body>

	<div class="content">
		<div class="position-header">
			<ul class="clearfix">
				<li class="position-header-click"><img
					src="../common/image/icon-1.png" />
					<p>全部</p></li>
				<li><img src="../common/image/icon-2.png" />
					<p>超级管理员</p></li>
				<li><img src="../common/image/icon-3.png" />
					<p>编辑</p></li>
				<li><img src="../common/image/icon-4.png" />
					<p>财务</p></li>

			</ul>
		</div>
		<div class="position-content">
			<div class="position-status">

				<input type="text" placeholder="日期查找" class="input-rate "
					id="rate-search" /> <input type="text" name="" id="" value=""
					placeholder="关键字" class="input-rate key-bord" /> <a
					href="admin-add.jsp" class="rebuild">添加管理员</a>
			</div>
			<div class="position-show">
				<ul class="clearfix">
					<c:forEach var="list" items="${admin}">

						<li>
							<p class="position-show-title">
								<i class="position-circle"></i> <span class="position-title">
									<span>${list.name}</span> <span class="position-share">审核内容量:%s
										时间:%s</span>
								</span>

							</p>
							<p class="position-author">
								<span class="position-title"> <span>电话:</span> <span
									class="position-share">${list.phone}</span>
								</span>
							</p>

							<p class="edit-exit">
								<i class="icon-edit icon-webpage"></i> <i
									class="icon-edit icon-del"></i>
							</p>
						</li>
					</c:forEach>

				</ul>

				<ul class="clearfix" style="display: none;">
					<c:forEach var="list" items="${admin}">
						<li>
							<p class="position-show-title">
								<i class="position-circle"></i> <i class="position-square"></i>
								<span class="position-title"> <span>${list.name}</span> <span
									class="position-share">审核内容量:%s 时间:%s</span>
								</span>

							</p>
							<p class="position-author">
								<span class="position-title"> <span>电话:</span> <span
									class="position-share">${list.phone}</span>
								</span>
							</p>

							<p class="edit-exit">
								<i class="icon-edit icon-webpage"></i> <i
									class="icon-edit icon-del"></i>
							</p>
						</li>
					</c:forEach>
				</ul>
				<ul class="clearfix" style="display: none;">
					<c:forEach var="list" items="${admin}">
						<li>
							<p class="position-show-title">
								<i class="position-circle"></i> <i class="position-square"></i>
								<span class="position-title"> <span>${list.name}</span> <span
									class="position-share">审核内容量:%s 时间:%s</span>
								</span>

							</p>
							<p class="position-author">
								<span class="position-title"> <span>电话:</span> <span
									class="position-share">${list.phone}</span>
								</span>
							</p>

							<p class="edit-exit">
								<i class="icon-edit icon-webpage"></i> <i
									class="icon-edit icon-del"></i>
							</p>
						</li>
					</c:forEach>
				</ul>
				<ul class="clearfix" style="display: none;">
					<c:forEach var="list" items="${admin}">
						<li>
							<p class="position-show-title">
								<i class="position-circle"></i> <i class="position-square"></i>
								<span class="position-title"> <span>${list.name}</span> <span
									class="position-share">审核内容量:%s 时间:%s</span>
								</span>

							</p>
							<p class="position-author">
								<span class="position-title"> <span>电话:</span> <span
									class="position-share">${list.phone}</span>
								</span>
							</p>

							<p class="edit-exit">
								<i class="icon-edit icon-webpage"></i> <i
									class="icon-edit icon-del"></i>
							</p>
						</li>
					</c:forEach>
				</ul>

			</div>
		</div>
		<div class="position-footer">
			<input type="radio" class="select-all" />全选 <a href="###"
				class="position-delete">删除</a> <span>分页的位置</span>
		</div>
	</div>

	<script type="text/javascript">
		$(function() {
			//切换样式选项卡
			$(".position-header ul li").click(function() {
				$(this).addClass("position-header-click").siblings().removeClass("position-header-click");
			})
	
			var lis = $('.position-header ul li');
			var listxt = $('.position-show ul');
			lis.click(function() {
				$(this).addClass('li-clicked').siblings().removeClass('li-clicked');
				var index = lis.index(this);
				listxt.eq(index).show().siblings().hide();
	
			});
	
			//input点击效果
			$(".input-status .input-recommend").focus(function() {
				$(this).css("background-image", "none");
			})
			//列表操作
			$(".position-circle").click(function() {
				$(this).addClass("list-click");
			})
			$(".rebuild").click(function() {
				$(this).css("background", "#00C8E8");
			})
			$(".icon-webpage").click(function() {
				window.location.href = "content.jsp"
			})
			$(".icon-del").click(function() {
				$(this).parent().parent().remove();
			})
			//全选
			$(".select-all").click(function() {
				$(".position-circle").addClass("list-click");
				$(".position-delete").click(function() {
					$(".position-show ul li").remove();
					$(this).css("color", "#00C8E8");
				})
			})
			//删除
			$(".position-delete").click(function() {
				$(".list-click").parent().parent().remove();
				$(this).css("color", "#00C8E8");
			})
	
			//选择日期
			$('#rate-search').date_input();
	
	
			//关键字搜索
			var a = $('.key-bord').val();
			$('.position ul li').each(function() {})
		})
	</script>
</body>
</html>
