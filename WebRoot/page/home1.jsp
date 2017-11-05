<%@page import="com.magicmoble.luzhouapp.model.server.Home_model"%>
<%@page
	import="com.magicmoble.luzhouapp.server.server_function.Server_Function"%>

<%@page import="com.magicmoble.luzhouapp.business.FunctionBusiness"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8" />
<meta name="viewport" content="initial-scale = 1, user-scalable = no">
<title></title>
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/common/iconfont/jiantou/iconfont.css" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/common/css/luzou.css" />
<script src="${pageContext.request.contextPath}/common/lib/jquery-1.9.0.min.js" type="text/javascript"
	charset="utf-8"></script>
<script src="${pageContext.request.contextPath}/common/lib/chart.js" type="text/javascript"
	charset="utf-8"></script>
<script src="${pageContext.request.contextPath}/common/js/luzhou.js" type="text/javascript"
	charset="utf-8"></script>
</head>
<%
	Home_model home_model = Server_Function.get_home1();
%>
<body>
	<!--<header>
			<h1 class='home'>首页</h1>
		</header>-->
	<div class="content ">
		<div class="clearfix">
			<div class="non-checked">
				<div class="non-checked-title clearfix">
					<p>
						<span class="non-content">未审核内容<span
							class="icon-non-content" id="non-content"></span></span> <a href="##">更多>></a>
					</p>
				</div>
				<ul class="non-checked-cont clearfix">
					<li><a href="Content_management_NoExamine.jsp">
							<h1 class="content-num">
								<%=home_model.getToutiao_No_Release()%>
							</h1>
							<p class="content-list">头条文章</p>
					</a></li>
					<li><a href="Content_management_NoExamine.jsp">
							<h1 class="content-num"><%=home_model.getFaxian_No_Release()%></h1>
							<p class="content-list">发现</p>
					</a></li>
					<li><a href="Content_management_NoExamine.jsp">
							<h1 class="content-num"><%=home_model.getCommodity_No_Release()%></h1>
							<p class="content-list">商品</p>
					</a></li>
					<li><a href="Content_management_NoExamine.jsp">
							<h1 class="content-num"><%=home_model.getFuwu_No_Release()%></h1>
							<p class="content-list">服务</p>
					</a></li>
					<li><a href="Content_management_NoExamine.jsp">
							<h1 class="content-num"><%=home_model.getQuchu_No_Release()%></h1>
							<p class="content-list">店铺</p>
					</a></li>
				</ul>
			</div>
			<div class="check-out">
				<div class="check-out-title clearfix">
					<p>
						<span class="non-content">待查看订单<span
							class="icon-non-content" id="non-order"></span></span> <a href="##">更多>></a>
					</p>
				</div>
				<ul class="check-out-cont clearfix">
					<li><a href="Order_management_NoConfirm.jsp">
							<h1 class="content-num"><%=home_model.getCommodity_dingdan()%></h1>
							<p class="content-list">商品订单</p>
					</a></li>
					<li><a href="Order_management_NoConfirm.jsp">
							<h1 class="content-num"><%=home_model.getFuwu_dingdan()%></h1>
							<p class="content-list">服务订单</p>
					</a></li>
					<li><a href="Order_management_NoConfirm.jsp">
							<h1 class="content-num"><%=home_model.getTuijian_dingdan()%></h1>
							<p class="content-list">推广订单</p>
					</a></li>
				</ul>
			</div>
		</div>
		<ul class="something-data clearfix">
			<li>
				<p class="non-comment">待查看评论</p>
				<p class="non-comment-cont">
					<a href="comment.jsp"><%=home_model.getPinglun_NoSee()%></a>
				</p>
			</li>
			<li>
				<p class="non-said">待查看说说</p>
				<p class="non-said-cont">
					<a href="Shuoshuo_management.jsp"><%=home_model.getShuoshuo_NoSee()%></a>
				</p>
			</li>
			<li>
				<p class="non-tip">待查看举报</p>
				<p class="non-tip-cont">
					<a href=""><%=home_model.getJubao_NoSee()%></a>
				</p>
			</li>
			<li>
				<p class="non-addV">加V申请</p>
				<p class="non-addV-cont">
					<a href=""><%=home_model.getPersonal_certification()%></a>
				</p>
			</li>
			<li>
				<p class="non-store">店铺认证申请</p>
				<p class="non-store-cont">
					<a href=""><%=home_model.getStore_certification()%></a>
				</p>
			</li>
			<li>
				<p class="non-postal">待处理提现申请</p>
				<p class="non-postal-cont">
					<a href=""><%=home_model.getTixian_Untreated()%></a>
				</p>
			</li>
		</ul>
		<div class="clearfix">
			<div class="data-analysis">
				<div class="data-analysis-title">
					<p>用户数据分析</p>
				</div>
				<ul class="data-analysis-cont">
					<li class="clearfix">
						<canvas id="Canvas2" style="height: 220px;width:65%;"></canvas>
						<h1 class="total-user">
							<span>●</span>总用户
							<p class="total-num">0</p>
						</h1>
						<h1 class="total-download">
							<span>●</span>总下载量
							<p class="total-num">0</p>
						</h1>
					</li>
					<li class="line-chart clearfix">
						<p class="line-chart-one">
							<span></span>新增用户
						</p>
						<p class="line-chart-two">
							<span></span>下载量
						</p>
						<p class="line-chart-three">
							<span></span>活跃用户
						</p>
					</li>
				</ul>
			</div>
			<div class="today-add">
				<div class="today-add-title">
					<p>
						今日新增 <a href="##">更多</a>
					</p>
				</div>
				<ul class="today-add-cont clearfix">
					<li>
						<canvas id="canvas1" height="130" width='130'></canvas>
						<h1 class="today-said">
							<p class="p1">
								<span class="iconfont icon-tubiao_hengxian"></span><span
									class="comment">说说</span>
							</p>
							<p class="p2">20</p>
						</h1>
						<h1 class="today-content">
							<p class="p1">
								<span class="iconfont icon-tubiao_hengxian"></span><span
									class="comment">内容</span>
							</p>
							<p class="p2">10</p>
						</h1>
					</li>
				</ul>
				<ul class="today-add-list clearfix">
					<li>
						<p class="p1">文章头条</p>
						<p class="p2">2</p>
					</li>
					<li>
						<p class="p1">发现</p>
						<p class="p2">9</p>
					</li>
					<li>
						<p class="p1">商品</p>
						<p class="p2">2</p>
					</li>
					<li>
						<p class="p1">服务</p>
						<p class="p2">2</p>
					</li>
					<li style="border: none;">
						<p class="p1">店铺</p>
						<p class="p2">2</p>
					</li>
				</ul>
			</div>
		</div>
		<div class="clearfix">
			<div class="read-ranking">
				<div class="read-ranking-title">
					<p>
						<span class="title-content">内容阅读排行</span> <a href="##">更多>></a>
					</p>
				</div>
				<ul class="read-ranking-cont clearfix">
					<li class="read-ranking-one clearfix">
						<p class="ranking-num">1</p>
						<p class="ranking-wave iconfont icon-wanjiantou-copy"></p>
						<p class="ranking-note">
							<span class="ranking-note-first">这里是文章标题大概就这么多</span><br /> <span
								class="ranking-rate-second">2017年5月16日&nbsp;14:25:12</span>
						</p>
						<p class="ranking-pageview">256,256,211</p>
					</li>
					<li class="read-ranking-two clearfix">
						<p class="ranking-num">2</p>
						<p class="ranking-wave iconfont icon-zbb_icon-shifuduan-"></p>
						<p class="ranking-note">
							<span class="ranking-note-first">这里是文章标题大概就这么多</span><br /> <span
								class="ranking-rate-second">2017年5月16日&nbsp;14:25:12</span>
						</p>
						<p class="ranking-pageview">256,256,200</p>
					</li>
					<li class="read-ranking-three clearfix">
						<p class="ranking-num">3</p>
						<p class="ranking-wave iconfont icon-tubiao_hengxian"></p>
						<p class="ranking-note">
							<span class="ranking-note-first">这里是文章标题大概就这么多</span><br /> <span
								class="ranking-rate-second">2017年5月16日&nbsp;14:25:12</span>
						</p>
						<p class="ranking-pageview">56,256,200</p>
					</li>
					<li class="read-ranking-four clearfix">
						<p class="ranking-num">4</p>
						<p class="ranking-wave iconfont icon-zbb_icon-shifuduan-"></p>
						<p class="ranking-note">
							<span class="ranking-note-first">这里是文章标题大概就这么多</span><br /> <span
								class="ranking-rate-second">2017年5月16日&nbsp;14:25:12</span>
						</p>
						<p class="ranking-pageview">6,256,200</p>
					</li>
				</ul>
			</div>
			<div class="concern-ranking">
				<div class="concern-ranking-title">
					<p>
						<span class="title-content">用户关注排行</span> <a href="##">更多>></a>
					</p>
				</div>
				<ul class="concern-ranking-cont clearfix">
					<li class="concern-ranking-one clearfix">
						<p class="ranking-num">1</p>
						<p class="ranking-wave iconfont icon-wanjiantou-copy"></p>
						<p class="ranking-note">
							<!--<img src=""/>-->
							<span class="img"></span> <span class="ranking-note-first">这里是用户昵称</span><br />
							<span class="ranking-rate-second">这是他的签名</span>
						</p>
						<p class="ranking-pageview">356,256,211</p>
					</li>
					<li class="concern-ranking-two clearfix">
						<p class="ranking-num">2</p>
						<p class="ranking-wave iconfont icon-zbb_icon-shifuduan-"></p>
						<p class="ranking-note clearfix">
							<span class="img"></span> <span class="ranking-note-first">大概就是昵称</span><br />
							<span class="ranking-rate-second">后面是关注量</span>
						</p>
						<p class="ranking-pageview">256,256,200</p>
					</li>
					<li class="concern-ranking-three clearfix">
						<p class="ranking-num">3</p>
						<p class="ranking-wave iconfont icon-tubiao_hengxian"></p>
						<p class="ranking-note clearfix">
							<span class="img"></span> <span class="ranking-note-first">大概就是昵称</span><br />
							<span class="ranking-rate-second">后面是关注量</span>
						</p>
						<p class="ranking-pageview">56,256,200</p>
					</li>
					<li class="concern-ranking-four clearfix">
						<p class="ranking-num">4</p>
						<p class="ranking-wave iconfont icon-zbb_icon-shifuduan-"></p>
						<p class="ranking-note clearfix">
							<span class="img"></span> <span class="ranking-note-first">大概就是昵称</span><br />
							<span class="ranking-rate-second">后面是关注量</span>
						</p>
						<p class="ranking-pageview">6,256,200</p>
					</li>
				</ul>
			</div>
		</div>
	</div>
	<script type="text/javascript">
		var doughnutData = [
			{
				value : 10,
				color : "#f8cb00"
			},
			{
				value : 20,
				color : "#48c3d3"
			}
		];
		var myDoughnut = new Chart(document.getElementById("canvas1").getContext("2d")).Doughnut(doughnutData);
	
		//未审核内容及待查看订单提示
		$(function() {
			var sum = 0;
			var all = 0;
			for (var i = 0; i < $(".check-out-cont .content-num").length; i++) {
				sum += parseFloat($(".check-out-cont .content-num").get(i).innerHTML);
			}
			for (var i = 0; i < $(".non-checked-cont .content-num").length; i++) {
				all += parseFloat($(".non-checked-cont .content-num").get(i).innerHTML);
			}
			$("#non-order").html(sum);
			$("#non-content").html(all);
		});
	</script>
</body>
</html>
