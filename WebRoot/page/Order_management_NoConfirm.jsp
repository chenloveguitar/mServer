<%@page
	import="com.magicmoble.luzhouapp.server.server_function.Server_Func"%>
<%@page import="com.magicmoble.luzhouapp.model.server.Dingdan_model"%>
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
	List<Dingdan_model> list = Server_Func.Commodity_NoConfirm();
	request.setAttribute("list", list);
	List<Dingdan_model> list2 = Server_Func.Fuwu_NoConfirm();
	request.setAttribute("list2", list2);
	List<Dingdan_model> list3 = new ArrayList<Dingdan_model>();
	list3.addAll(list);
	list3.addAll(list2);
	request.setAttribute("list3", list3);
%>

<body>
	<div class="content">
		<div class="position-header">
			<ul class="clearfix">
				<li class="position-header-click"><img
					src="../common/image/icon-1.png" />
					<p>全部</p></li>
				<li><img src="../common/image/icon-2.png" />
					<p>商品</p></li>
				<li><img src="../common/image/icon-3.png" />
					<p>服务</p></li>


			</ul>
		</div>
		<div class="position-content">
			<div class="position-status">
				<select name="" class="input-text">
					<option value="">支付状态</option>
					<option value="支付成功">支付成功</option>
					<option value="未支付">未支付</option>
					<option value="支付失败">支付失败</option>
				</select> <input type="text" name="" id="" value="" placeholder="关键字"
					class="input-rate key-bord" /> <select id="change_paixu"
					class="third-select">
					<option value="">排序</option>
					<option value="日期从早到晚">日期从早到晚</option>
					<option value="日期从晚到早">日期从晚到早</option>
					<option value="价格从高到低">价格从高到低</option>
					<option value="价格从低到高">价格从低到高</option>
				</select>
			</div>

			<div class="position-show">

				<ul class="clearfix">
					<c:forEach var="list" items="${list3}">
						<li>
							<p class="position-show-title">
								<i class="position-circle"></i> <img class="position-square"
									src="${list.pictures.get(0).picture_url}" /> <span
									class="position-title"> <span>${list.title}</span> <span
									class="position-share">${list.transaction_id}</span>
								</span>

							</p>
							<p class="position-author position-aut">
								<img class="icon-author" src="${list.buyer_touxiang}" /> <span
									class="position-title"> <span>${list.buyer_name}</span>
								</span>
							</p>
							<p class="position-author position-aut">
								<img class="icon-author" src="${list.seller_touxiang}" /> <span
									class="position-title"> <span>${list.seller_name}</span>
								</span>
							</p>
							<p class="position-public position-pub">

								<span class="position-title position-p"> <span>数量</span>
									<span class="position-share">购买数量${list.shuliang}</span>
								</span> <span class="position-title position-p"> <span>总价</span>
									<span class="position-share">总价${list.total_price}</span>
								</span>
							</p>
							<p class="edit-exit">
								<a class="icon-edit icon-webpage"
									href="<%=request.getContextPath()%>/page/order-detail.jsp?order_id=${list.transaction_id}&guangjie_fenlei_Tag=${list.guangjie_fenlei_Tag}"></a>
						</li>
					</c:forEach>
				</ul>
				<ul class="clearfix" style="display: none;">
					<c:forEach var="list" items="${list}">
						<li>
							<p class="position-show-title">
								<i class="position-circle"></i> <img class="position-square"
									src="${list.pictures.get(0).picture_url}" /> <span
									class="position-title"> <span>${list.title}</span> <span
									class="position-share">${list.transaction_id}</span>
								</span>

							</p>
							<p class="position-author position-aut">
								<img class="icon-author" src="${list.buyer_touxiang}" /> <span
									class="position-title"> <span>${list.buyer_name}</span>
								</span>
							</p>
							<p class="position-author position-aut">
								<img class="icon-author" src="${list.seller_touxiang}" /> <span
									class="position-title"> <span>${list.seller_name}</span>
								</span>
							</p>
							<p class="position-public position-pub">

								<span class="position-title position-p"> <span>数量</span>
									<span class="position-share">购买数量${list.shuliang}</span>
								</span> <span class="position-title position-p"> <span>总价</span>
									<span class="position-share">总价${list.total_price}</span>
								</span>
							</p>
							<p class="edit-exit">
								<a class="icon-edit icon-webpage"
									href="<%=request.getContextPath()%>/page/order-detail.jsp?order_id=${list.transaction_id}&guangjie_fenlei_Tag=${list.guangjie_fenlei_Tag}"></a>
						</li>
					</c:forEach>
				</ul>
				<ul class="clearfix" style="
				display: none;">
					<c:forEach var="list" items="${list2}">
						<li>
							<p class="position-show-title">
								<i class="position-circle"></i> <img class="position-square"
									src="${list.pictures.get(0).picture_url}" /> <span
									class="position-title"> <span>${list.title}</span> <span
									class="position-share">${list.transaction_id}</span>
								</span>

							</p>
							<p class="position-author position-aut">
								<img class="icon-author" src="${list.buyer_touxiang}" /> <span
									class="position-title"> <span>${list.buyer_name}</span>
								</span>
							</p>
							<p class="position-author position-aut">
								<img class="icon-author" src="${list.seller_touxiang}" /> <span
									class="position-title"> <span>${list.seller_name}</span>
								</span>
							</p>
							<p class="position-public position-pub">

								<span class="position-title position-p"> <span>数量</span>
									<span class="position-share">购买数量${list.shuliang}</span>
								</span> <span class="position-title position-p"> <span>总价</span>
									<span class="position-share">总价${list.total_price}</span>
								</span>
							</p>
							<p class="edit-exit">
								<a class="icon-edit icon-webpage"
									href="<%=request.getContextPath()%>/page/order-detail.jsp?order_id=${list.transaction_id}&guangjie_fenlei_Tag=${list.guangjie_fenlei_Tag}"></a>
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
				window.location.href = "order-detail.jsp"
			})
	
			//全选
			var flag = true;
			$(".select-all").click(function() {
				if (flag) {
					$(".position-circle").addClass("list-click");
					flag = false;
					$(".position-delete").click(function() {
						$(".position-show ul li").remove();
						$(this).css("color", "#00C8E8");
					})
				} else {
					$(".position-circle").removeClass("list-click");
					flag = true;
				}
	
	
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
	
	
	
	
	
	
			$("#change_paixu").change(function() {
	
	
				$.ajax({
					url : "/mServer/Handle_NoExamine_neirong?Tag=" + flag,
					type : "POST",
					data : {
						"change_paixu" : $("#change_paixu").val()
					},
					dataType : "json",
					success : function(message) {
						var str = "";
						for (var i in message) {
							str += "	<li>"
							str += "		<p class=\"position-show-title\">"
							str += "		<i class=\"position-circle\"></i><img class=\"position-square\""
							str += "		src=\"" + message[i]["pictures"][0]["picture_url"] + "\"/> <span"
							str += "	class=\"position-title\"> <span>" + message[i]["title"] + "</span> <span"
							str += "	class=\"position-share\">阅读量:" + message[i]["yuedu_count"] + ""
							str += "	分享:" + message[i]["share_count"] + " 收藏:" + message[i]["shoucang_count"] + "</span>"
							str += "	</span>"
							str += "	</p>"
							str += "	<p class=\"position-author\">"
							str += "	<img class=\"icon-author\" src=\"" + message[i]["releaser_touxiang"] + "\">"
							str += "	<span class=\"position-title\"> <span>" + message[i]["releaser_name"] + "</span>"
							str += "	<span class=\"position-share\">" + message[i]["time"] + "</span>"
							str += "	</span>"
							str += "	</p>"
							str += "	<p class=\"position-public\">"
							str += "	<span class=\"position-title position-p\"> <span>" + message[i]["shenhe"] + "</span>"
							str += "		<span class=\"position-share\">" + message[i]["tuijian_Tag"] + "</span>"
							str += "		</span> <span class=\"position-title position-p\"> <span>点赞红包</span>"
							str += "			<span class=\"position-share\">剩余" + message[i]["dianzan_hongbao"] + "元</span>"
							str += "		</span> <span class=\"position-title position-p\"> <span>分享红包</span>"
							str += "		<span class=\"position-share\">剩余" + message[i]["share_hongbao"] + "元</span>"
							str += "		</span> <span class=\"position-title position-p\"> <span>" + message[i]["tuijian_message"] + "</span>"
							str += "			<span class=\"position-share\">剩余" + message[i]["days"] + "天</span>"
							str += "		</span>"
							str += "	</p>"
							str += "	<p class=\"edit-exit\">"
							str += "	<i class=\"icon-edit icon-webpage\"></i> <i"
							str += "	class=\"icon-edit icon-del\"></i>"
							str += "	</p>"
							str += "	</li>"
	
	
	
						}
	
	
						$("#clear-fix").html(str);
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
