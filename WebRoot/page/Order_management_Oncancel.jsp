<%@page import="com.magicmoble.luzhouapp.model.server.Dingdan_model"%>
<%@page
	import="com.magicmoble.luzhouapp.server.server_function.Server_Func"%>
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
	List<Dingdan_model> list = Server_Func.Commodity_Oncancel();
	request.setAttribute("list", list);
	List<Dingdan_model> list2 = Server_Func.Commodity_Oncancel();
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
				</select> <select name="" class="input-text">
					<option value="">付款方式</option>
					<option value="支付宝">支付宝</option>
					<option value="微信">微信</option>
					<option value="余额">余额</option>
				</select> <select name="" class="input-text">
					<option value="">是否推荐</option>
					<option value="">是</option>
					<option value="">否</option>
				</select> <input type="text" placeholder="日期查找" class="input-rate "
					id="rate-search" /> <input type="text" name="" id="" value=""
					placeholder="关键字" class="input-rate key-bord" /> <select name=""
					class="third-select">
					<option value="">排序</option>
					<option value="日期从早到晚">日期从早到晚</option>
					<option value="日期从晚到早">日期从晚到早</option>
					<option value="价格从高到低">价格从高到低</option>
					<option value="价格从低到高">价格从低到高</option>
				</select> <a href="content.jsp" class="rebuild">新增内容</a>
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
				<ul class="clearfix" style="display: none;">
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
				window.location.href = "content.jsp"
			})
			$(".icon-del").click(function() {
				$(this).parent().parent().remove();
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
		})
	</script>
</body>
</html>
