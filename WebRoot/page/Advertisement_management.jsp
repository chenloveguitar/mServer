<%@page
	import="com.magicmoble.luzhouapp.server.server_function.Server_Func"%>
<%@page import="com.magicmoble.luzhouapp.model.server.Guanggao_model"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title></title>
<link rel="stylesheet" type="text/css" href="../common/css/luzou.css" />
<link rel="stylesheet" type="text/css" href="common/css/luzou.css" />
<link rel="stylesheet" type="text/css" href="../common/css/calendar1.css" />
<link rel="stylesheet" type="text/css" href="common/css/calendar1.css" />
<script src="../common/lib/jquery-1.9.0.min.js" type="text/javascript"
	charset="utf-8"></script>
<script src="common/lib/jquery-1.9.0.min.js" type="text/javascript"
	charset="utf-8"></script>
<script src="../common/js/jquery.min.js" type="text/javascript"
	charset="utf-8"></script>
<script src="common/js/jquery.min.js" type="text/javascript"
	charset="utf-8"></script>
<script src="../common/js/jquery.date_input.pack.js"
	type="text/javascript" charset="utf-8"></script>
<script src="common/js/jquery.date_input.pack.js"
	type="text/javascript" charset="utf-8"></script>
</head>


<%
	List<Guanggao_model> list = Server_Func.getguanggao();
	request.setAttribute("list", list);
%>
<body>
	<div class="content">
		<div class="position-header">
			<ul class="clearfix">
				<li class="position-header-click"><img
					src="../common/image/icon-1.png" />
					<p>全部</p></li>
				<li><img src="../common/image/icon-3.png" />
					<p>详情页广告</p></li>
				<li><img src="../common/image/icon-4.png" />
					<p>轮播广告</p></li>


			</ul>
		</div>
		<div class="position-content">
			<div class="position-status">
				<select name="" class="input-text">
					<option value="">状态</option>
					<option value="">正常</option>
					<option value="">已过期</option>
				</select> <input type="text" placeholder="日期查找" class="input-rate "
					id="rate-search" /> <input type="text" name="" id="" value=""
					placeholder="关键字" class="input-rate key-bord" /> <select name=""
					class="third-select">
					<option value="">排序</option>
					<option value="">点击量从高到低</option>
					<option value="">点击量从低到高</option>
				</select> <a href="advert-add.jsp" class="rebuild">添加广告</a>
			</div>
			<div class="position-show">

				<ul class="clearfix">
					<c:forEach var="list" items="${list}">
						<li>
							<p class="position-show-title">
								<i class="position-circle "></i> <span class="position-title">
									<span class="advert-manage"> <img src="${list.picture}"
										alt="" />
								</span> <span class="advert-position">${list.url}</span>
								</span>
							</p>
							<p class="position-author">
								<span class="position-title advert-time"> ${list.time} </span>
							</p>
							<p class="position-author">
								<span class="position-title advert-time">
									${list.shangjia_message}</span>
							</p>
							<p class="edit-exit">
								<a class="icon-edit icon-webpage" href="<%=request.getContextPath()%>/page/advert-add.jsp"></a> <i
									class="icon-edit icon-del"></i>
							</p>
						</li>

					</c:forEach>

				</ul>

				<ul class="clearfix" style="display: none;">
					<c:forEach var="list" items="${list}">
						<li>
							<p class="position-show-title">
								<i class="position-circle "></i> <span class="position-title">
									<span class="advert-manage"> <img src="${list.picture}"
										alt="" />
								</span> <span class="advert-position">${list.url}</span>
								</span>
							</p>
							<p class="position-author">
								<span class="position-title advert-time"> ${list.time} </span>
							</p>
							<p class="position-author">
								<span class="position-title advert-time">
									${list.shangjia_message}</span>
							</p>
							<p class="edit-exit">
								<a class="icon-edit icon-webpage" href="<%=request.getContextPath()%>/page/advert-update.jsp"></a> <i
									class="icon-edit icon-del"></i>
							</p>
						</li>

					</c:forEach>
				</ul>
				<ul class="clearfix" style="display: none;">
					<c:forEach var="list" items="${list}">
						<li>
							<p class="position-show-title">
								<i class="position-circle "></i> <span class="position-title">
									<span class="advert-manage"> <img  src="${list.picture}"
										alt="" />
								</span> <span class="advert-position">${list.url}</span>
								</span>
							</p>
							<p class="position-author">
								<span class="position-title advert-time"> ${list.time} </span>
							</p>
							<p class="position-author">
								<span class="position-title advert-time">
									${list.shangjia_message}</span>
							</p>
							<p class="edit-exit">
								<a class="icon-edit icon-webpage" href="<%=request.getContextPath()%>/page/advert-update.jsp"></a> <i
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
				window.location.href = "advert-update.jsp"
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
