<%@page
	import="com.magicmoble.luzhouapp.model.server.Shuoshuo_xiangqing"%>
<%@page
	import="com.magicmoble.luzhouapp.server.server_function.Server_Function"%>
<%@page
	import="com.magicmoble.luzhouapp.server.server_function.Server_Func"%>
<%@page import="com.magicmoble.luzhouapp.business.ShuoshuoBusiness"%>
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
<link rel="stylesheet" type="text/css" href="common/css/luzou.css" />
<link rel="stylesheet" type="text/css" href="common/css/calendar1.css" />
<script src="../common/lib/jquery-1.9.0.min.js" type="text/javascript"
	charset="utf-8"></script>
<script src="../common/js/jquery.min.js" type="text/javascript"
	charset="utf-8"></script>
<script src="../common/js/jquery.date_input.pack.js"
	type="text/javascript" charset="utf-8"></script>
<%
	List<Shuoshuo_xiangqing> list = Server_Func.limitShuoshuo_ser(1, 15);
	request.setAttribute("list", list);
%>
</head>

<body>

	<div class="content">
		<div class="position-header">
			<ul class="clearfix">
				<li class="position-header-click"><img
					src="../common/image/icon-1.png" />
					<p>全部</p></li>


			</ul>
		</div>
		<div class="position-content">
			<div class="position-status">
				<select name="" class="input-text" id="change_recommend">
					<option value="">是否推荐</option>
					<option value="已推荐">已推荐</option>
					<option value="未推荐">未推荐</option>
				</select>  <input type="text" name=""
					id="guanjianzi_search" value="" placeholder="关键字"
					class="input-rate key-bord" /> <select name=""
					class="third-select" id="sort">
					<option value="">排序</option>
					<option value="阅读量从高到低">阅读量从高到低</option>
					<option value="阅读量从低到高">阅读量从低到高</option>
					<option value="点赞量从高到低">点赞量从高到低</option>
					<option value="点赞量从低到高">点赞量从低到高</option>
					<option value="日期从早到晚">日期从早到晚</option>
					<option value="日期从晚到早">日期从晚到早</option>
				</select> <a href="Add_Shuoshuo.jsp" class="rebuild">添加说说</a>
			</div>
			<div class="position-show">
				<ul class="clearfix" id="clear-fix">
					<c:forEach var="list" items="${list}">

						<li>
							<p class="position-show-title">
								<i class="position-circle"></i> <img class="position-square"
									src="${list.pictures.get(0).picture_url}" /> <span
									class="position-title" id="position-title-1"> <span>${list.content}</span>
									<span class="position-share">阅读量:${list.yuedu_count}
										分享:${list.share_count} 时间:${list.time}</span>
								</span>

							</p>
							<p class="position-author">
								<img class="icon-author" src="${list.releaser_touxiang}">
								<span class="position-title" id="position-title-2"> <span>${list.name}</span>
									<span class="position-share">${list.qianming}</span>
								</span>
							</p>
							<p class="position-public">
								<span class="position-title position-p" id="position-title-3">
									<span>${list.tuijian_message}</span>
								</span>
							</p>
							<p class="edit-exit">
								<a class="icon-edit icon-webpage"
									href="<%=request.getContextPath()%>/Update_Shuoshuo?updete_id=${list.id}"></a>
								<a class="icon-edit icon-del" 
									href="<%=request.getContextPath()%>/Handle_Shuoshuo?del_id=${list.id}"></a>
							</p>
						</li>

					</c:forEach>
				</ul>


			</div>
		</div>
		<div class="position-footer">
			<input type="radio" class="select-all" />全选 <a href="###"
				class="position-delete">删除</a> <span> <a href="##">首页</a> <a
				href="##">上一页</a> <a href="/mServer/PaginationServlet?pageno=1">1</a>
				<a href="/mServer/PaginationServlet?pageno=2">2</a> <a
				href="/mServer/PaginationServlet?pageno=3">3</a> <a href="##">下一页</a>
				<a href="##">末页</a>
			</span>
		</div>
	</div>

	<script type="text/javascript">
		function updateShuoshuo(id) {
			location.href = "<%=request.getContextPath()%>/Update_Shuoshuo?updete_id=" + id;
		}
		function deleteShuoshuo(id) {
			location.href = "<%=request.getContextPath()%>/Handle_Shuoshuo?del_id=" + id;
		}
		function ChangeDateFormat(d) {
			//将时间戳转为int类型，构造Date类型
			var date = new Date(parseInt(d.time, 10));
	
			//月份得+1，且只有个位数时在前面+0
			var month = date.getMonth() + 1 < 10 ? "0" + (date.getMonth() + 1) :
				date.getMonth() + 1;
	
			//日期为个位数时在前面+0
			var currentDate = date.getDate() < 10 ? "0" + date.getDate() :
				date.getDate(); //getFullYear得到4位数的年份 ，返回一串字符串 return
			date.getFullYear() + "-" + month + "-" + currentDate + " " +
			date.getHours() + ":" + date.getMinutes() + ":" +
			date.getSeconds() + "." + date.getMilliseconds();
		}
	
		$(function() { //改变推荐
			$("#change_recommend").change(function() {
				$.ajax({
					url : "/mServer/Handle_Shuoshuo",
					type : "POST",
					data : {
						"change_rec" : $("#change_recommend").val()
					},
					dataType : "json",
					success : function(message) {
						var str = "";
						for (var i in message) {
							str += "<li>"
							str += "<p class=\"position-show-title\">";
							str += "	<i class=\"position-circle\"></i> <img class=\"position-square\" "
							str += "	src=\"" + message[i]["pictures"][0]["picture_url"] + "\" /> <span"
							str += "	class=\"position-title\" id=\"position-title-1\"> <span>" + message[i]["content"] + "</span>"
							str += "		<span class=\"position-share\">阅读量:" + message[i]["yuedu_count"] + ""
							str += "			分享:" + message[i]["share_count"] + " 时间:" + message[i]["time"] + "</span>"
							str += "	</span>"
							str += "	</p>"
							str += "	<p class=\"position-author\">"
							str += "		<span class=\"icon-author\"></span> <span class=\"position-title\""
							str += "		id=\"position-title-2\"> <span>" + message[i]["name"] + "</span> <span"
							str += "		class=\"position-share\">" + message[i]["qianming"] + "</span>"
							str += "		</span>"
							str += "	</p>"
							str += "	<p class=\"position-public\">"
							str += "		<span class=\"position-title position-p\" id=\"position-title-3\">"
							str += "			<span>" + message[i]["tuijian_message"] + "</span>"
							str += "		</span>"
							str += "	</p>"
							str += "	<p class=\"edit-exit\">"
							str += "		<a class=\"icon-edit icon-webpage\" "
							str += "			href='tiaozhuan.jsp?updete_id=" + message[i]["id"] + " '></a>"
							str += "	<a class=\"icon-edit icon-del\" "
							str += "		href=tiaozhuan.jsp?del_id=" + message[i]["id"] + " \"></a>"
							str += "	</p>"
							str += "</li>"
						}
						$("#clear-fix").html(str);
					},
					error : function() {
						alert("error");
					}
				});
			})
		})
		$(function() { 
			$("#guanjianzi_search").blur(function() {
				$.ajax({
					url : "/mServer/Handle_Shuoshuo",
					type : "POST",
					data : {
						"blur_rec" : $("#guanjianzi_search").val()
					},
					dataType : "json",
					success : function(message) {
	
	
						var str = "";
						for (var i in message) {
							str += "<li>"
							str += "<p class=\"position-show-title\">";
							str += "	<i class=\"position-circle\"></i> <img class=\"position-square\" "
							str += "	src=\"" + message[i]["pictures"][0]["picture_url"] + "\" /> <span"
							str += "	class=\"position-title\" id=\"position-title-1\"> <span>" + message[i]["content"] + "</span>"
							str += "		<span class=\"position-share\">阅读量:" + message[i]["yuedu_count"] + ""
							str += "			分享:" + message[i]["share_count"] + " 时间:" + message[i]["time"] + "</span>"
							str += "	</span>"
							str += "	</p>"
							str += "	<p class=\"position-author\">"
							str += "		<span class=\"icon-author\"></span> <span class=\"position-title\""
							str += "		id=\"position-title-2\"> <span>" + message[i]["name"] + "</span> <span"
							str += "		class=\"position-share\">" + message[i]["qianming"] + "</span>"
							str += "		</span>"
							str += "	</p>"
							str += "	<p class=\"position-public\">"
							str += "		<span class=\"position-title position-p\" id=\"position-title-3\">"
							str += "			<span>" + message[i]["tuijian_message"] + "</span>"
							str += "		</span>"
							str += "	</p>"
							str += "	<p class=\"edit-exit\">"
							str += "		<a class=\"icon-edit icon-webpage\" "
							str += "			href='tiaozhuan.jsp?updete_id=" + message[i]["id"] + " '></a>"
							str += "	<a class=\"icon-edit icon-del\" "
							str += "		href=tiaozhuan.jsp?del_id=" + message[i]["id"] + " \"></a>"
							str += "	</p>"
							str += "</li>"
						}
						$("#clear-fix").html(str);
					},
					error : function() {
						alert("error");
					}
				});
			})
	
	
			$("#sort").change(function() {
				$.ajax({
					url : "/mServer/Handle_Shuoshuo",
					type : "POST",
					data : {
						"sort_rec" : $("#sort").val()
					},
					dataType : "json",
					success : function(message) {
	
	
						var str = "";
						for (var i in message) {
							str += "<li>"
							str += "<p class=\"position-show-title\">";
							str += "	<i class=\"position-circle\"></i> <img class=\"position-square\" "
							str += "	src=\"" + message[i]["pictures"][0]["picture_url"] + "\" /> <span"
							str += "	class=\"position-title\" id=\"position-title-1\"> <span>" + message[i]["content"] + "</span>"
							str += "		<span class=\"position-share\">阅读量:" + message[i]["yuedu_count"] + ""
							str += "			分享:" + message[i]["share_count"] + " 时间:" + message[i]["time"] + "</span>"
							str += "	</span>"
							str += "	</p>"
							str += "	<p class=\"position-author\">"
							str += "		<span class=\"icon-author\"></span> <span class=\"position-title\""
							str += "		id=\"position-title-2\"> <span>" + message[i]["name"] + "</span> <span"
							str += "		class=\"position-share\">" + message[i]["qianming"] + "</span>"
							str += "		</span>"
							str += "	</p>"
							str += "	<p class=\"position-public\">"
							str += "		<span class=\"position-title position-p\" id=\"position-title-3\">"
							str += "			<span>" + message[i]["tuijian_message"] + "</span>"
							str += "		</span>"
							str += "	</p>"
							str += "	<p class=\"edit-exit\">"
							str += "		<a class=\"icon-edit icon-webpage\" "
							str += "			href='tiaozhuan.jsp?updete_id=" + message[i]["id"] + " '></a>"
							str += "	<a class=\"icon-edit icon-del\" "
							str += "		href=tiaozhuan.jsp?del_id=" + message[i]["id"] + " \"></a>"
							str += "	</p>"
							str += "</li>"
						}
						$("#clear-fix").html(str);
					},
					error : function() {
						alert("error");
					}
				});
			})
			
	
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
			/* 
				修改和删除
			$(".icon-webpage").click(function() {
				var id = $(this).attr("updateValue");
				window.location.href = "update_Shuoshuo.jsp?id="+id;
			})
			$(".icon-del").click(function() {
				$(this).parent().parent().remove();
			}) */
	
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
