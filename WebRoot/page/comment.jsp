<%@page import="com.magicmoble.luzhouapp.server.ctrl.add_user"%>
<%@page
	import="com.magicmoble.luzhouapp.server.server_function.Server_Func"%>
<%@page
	import="com.magicmoble.luzhouapp.server.server_function.Server_Function"%>
<%@page import="com.magicmoble.luzhouapp.model.server.Pinglun_model"%>
<%@page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>评论管理</title>
<link rel="stylesheet" type="text/css" href="../common/css/luzou.css" />
<link rel="stylesheet" type="text/css" href="common/css/luzou.css" />
<script src="../common/lib/jquery-1.9.0.min.js" type="text/javascript"
	charset="utf-8"></script>
<script src="common/lib/jquery-1.9.0.min.js" type="text/javascript"
	charset="utf-8"></script>
</head>


<%
	List<Pinglun_model> list1 = Server_Function.getpinglun_toutiao();
	request.setAttribute("list1", list1);
	List<Pinglun_model> list2 = Server_Function.getpinglun_faxian();
	request.setAttribute("list2", list2);
	List<Pinglun_model> list3 = Server_Function.getpinglun_quchu();
	request.setAttribute("list3", list3);
	List<Pinglun_model> list4 = Server_Function.getpinglun_commodity();
	request.setAttribute("list4", list4);
	List<Pinglun_model> list5 = Server_Function.getpinglun_fuwu();
	request.setAttribute("list5", list5);
	List<Pinglun_model> list6 = new ArrayList<Pinglun_model>();
	list6.addAll(list1);
	list6.addAll(list2);
	list6.addAll(list3);
	list6.addAll(list4);
	list6.addAll(list5);
	request.setAttribute("list6", list6);
%>
<body>
	<div class="content">
		<div class="position-header">
			<ul class="clearfix">
				<li class="position-header-click"><img
					src="../common/image/icon-1.png">
					<p>全部</p></li>
				<li><img src="../common/image/icon-3.png">
					<p>头条</p></li>
				<li><img src="../common/image/icon-7.png">
					<p>发现</p></li>
				<li><img src="../common/image/icon-8.png">
					<p>去处</p></li>
				<li><img src="../common/image/icon-9.png">
					<p>商品</p></li>
				<li><img src="../common/image/icon-9.png">
					<p>服务</p></li>

			</ul>
		</div>
		<div class="position-content">
			<div class="position-status">
				<input type="text" name="" id="" value="" placeholder="关键字"
					class="input-rate key-bord"> <select id="change_paixu"
					class="third-select" style="background-image: none;">
					<option value="">排序</option>
					<option value="时间从早到晚">时间从早到晚</option>
					<option value="时间从晚到早">时间从晚到早</option>

				</select> <a href="comment-add.jsp" class="rebuild">添加评论</a>
			</div>
			<div class="position-show">
				<ul class="clearfix">
					<c:forEach var="list" items="${list6}">
						<li>
							<p class="position-show-title comment-title">
								<i class="position-circle "></i> <span
									class="position-title comment-content"> ${list.content }
								</span>
							</p>

							<p class="position-author comment-author">
								<span class="position-title advert-time comment-time">
									${list.time } </span> <span class="comment-obj"> ${list.title }
								</span>

							</p>
							<p class="edit-exit">
								<span onclick="tiaozhuan('${list.id}')">查看</span> <i
									class="icon-edit icon-webpage"></i> <i
									class="icon-edit icon-del"></i>
							</p>
						</li>
					</c:forEach>
				</ul>
				<ul class="clearfix" style="display: none;">
					<c:forEach var="list" items="${list1}">
						<li>
							<p class="position-show-title comment-title">
								<i class="position-circle "></i> <span
									class="position-title comment-content"> ${list.content }
								</span>
							</p>

							<p class="position-author comment-author">
								<span class="position-title advert-time comment-time">
									${list.time } </span> <span class="comment-obj"> ${list.title }
								</span>

							</p>
							<p class="edit-exit">
								<span onclick="tiaozhuan('${list.id}')">查看</span> <i
									class="icon-edit icon-webpage"></i> <i
									class="icon-edit icon-del"></i>
							</p>
						</li>
					</c:forEach>
				</ul>
				<ul class="clearfix" style="display: none;">
					<c:forEach var="list" items="${list2}">
						<li>
							<p class="position-show-title comment-title">
								<i class="position-circle "></i> <span
									class="position-title comment-content"> ${list.content }
								</span>
							</p>

							<p class="position-author comment-author">
								<span class="position-title advert-time comment-time">
									${list.time } </span> <span class="comment-obj"> ${list.title }
								</span>

							</p>
							<p class="edit-exit">
								<span onclick="tiaozhuan('${list.id}')">查看</span> <i
									class="icon-edit icon-webpage"></i> <i
									class="icon-edit icon-del"></i>
							</p>
						</li>
					</c:forEach>
				</ul>
				<ul class="clearfix" style="display: none;">
					<c:forEach var="list" items="${list3}">
						<li>
							<p class="position-show-title comment-title">
								<i class="position-circle "></i> <span
									class="position-title comment-content"> ${list.content }
								</span>
							</p>

							<p class="position-author comment-author">
								<span class="position-title advert-time comment-time">
									${list.time } </span> <span class="comment-obj"> ${list.title }
								</span>

							</p>
							<p class="edit-exit">
								<span onclick="tiaozhuan('${list.id}')">查看</span> <i
									class="icon-edit icon-webpage"></i> <i
									class="icon-edit icon-del"></i>
							</p>
						</li>
					</c:forEach>
				</ul>
				<ul class="clearfix" style="display: none;">
					<c:forEach var="list" items="${list4}">
						<li>
							<p class="position-show-title comment-title">
								<i class="position-circle "></i> <span
									class="position-title comment-content"> ${list.content }
								</span>
							</p>

							<p class="position-author comment-author">
								<span class="position-title advert-time comment-time">
									${list.time } </span> <span class="comment-obj"> ${list.title }
								</span>

							</p>
							<p class="edit-exit">
								<span onclick="tiaozhuan('${list.id}')">查看</span> <i
									class="icon-edit icon-webpage"></i> <i
									class="icon-edit icon-del"></i>
							</p>
						</li>
					</c:forEach>
				</ul>
				<ul class="clearfix" style="display: none;">
					<c:forEach var="list" items="${list5}">
						<li>
							<p class="position-show-title comment-title">
								<i class="position-circle "></i> <span
									class="position-title comment-content"> ${list.content }
								</span>
							</p>

							<p class="position-author comment-author">
								<span class="position-title advert-time comment-time">
									${list.time } </span> <span class="comment-obj"> ${list.title }
								</span>

							</p>
							<p class="edit-exit">
								<span onclick="tiaozhuan('${list.id}')">查看</span> <i
									class="icon-edit icon-webpage"></i> <i
									class="icon-edit icon-del"></i>
							</p>
						</li>
					</c:forEach>
				</ul>

			</div>
		</div>
		<div class="position-footer">
			<input type="checkbox" class="select-all" />全选 <a href="###"
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
				listxt.eq(index).attr("id", "clear-fix").siblings().removeAttr("id");
	
	
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
				window.location.href = "comment-update.jsp"
			})
			$(".icon-del").click(function() {
				$(this).parent().parent().remove();
			})
	
			//全选
			var fla = true;
			$(".select-all").click(function() {
				if (fla) {
					$(".position-circle").addClass("list-click");
					fla = false;
				} else {
					$(".position-circle").removeClass("list-click");
					fla = true;
				}
	
				$(".position-delete").click(function() {
					$(".position-show ul li").remove();
					$(this).css("color", "#00C8E8");
				})
			})
	
			var flag = true;
			$(".position-circle").click(function() {
				if ($(this).hasClass("list-click")) {
					$(this).removeClass('list-click');
				} else {
					$(this).addClass('list-click');
					$(this).parent().parent().find(".edit-exit").find(".icon-del").click(function() {
						$(this).parent().parent().remove();
					})
				}
			})
			//删除
			$(".position-delete").click(function() {
				$(".list-click").parent().parent().remove();
				$(this).css("color", "#00C8E8");
			})
	
		})
		function tiaozhuan(a) {
			window.location.href = "comment-detail.jsp?id=" + a;
	
	
		}
	</script>
</body>
</html>
