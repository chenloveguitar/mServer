<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>内容管理-详细信息-分享</title>
<link rel="stylesheet" type="text/css"
	href="../common/iconfont/content/iconfont.css" />
<link rel="stylesheet" type="text/css" href="../common/css/luzou.css" />
<link rel="stylesheet" type="text/css"
	href="../common/css/jquery.page.css" />
<script src="../common/lib/jquery-1.9.0.min.js" type="text/javascript"
	charset="utf-8"></script>
<script src="../common/js/jquery.page.js" type="text/javascript"
	charset="utf-8"></script>
<!--<script src="../common/js/luzhou.js" type="text/javascript" charset="utf-8"></script>-->
<script src="../common/js/rem.js" type="text/javascript" charset="utf-8"></script>
</head>
<body>
	<div class="content">
		<div class="share-nav basic-content">
			<ul class="clearfix share-nav-all">
				<li class="li-clicked"><a href="##">收藏</a></li>
				<li><a href="##">分享</a></li>
				<li><a href="##">推荐</a></li>
				<li class="red-pocket"><a href="##">红包</a></li>
				<li><a href="##">打赏</a></li>
			</ul>

			<div class="content-show">
				<ul class="content-show-li pocket-ul restore clearfix">
					<li class="cotent-li content-show-li">
						<p>
							<i class="icon-circle"></i> <span class="nickname rate">这里是昵称</span>
							<span class="rate-time"> <span class="rate">2017-5-17</span>
								<span class="time">16:10:10</span>
							</span>
						</p>
					</li>
					<li class="cotent-li">
						<p>
							<i class="icon-circle"></i> <span class="nickname rate">这里是昵称</span>
							<span class="rate-time"> <span class="rate">2017-5-17</span>
								<span class="time">16:10:10</span>
							</span>
						</p>
					</li>
					<li class="cotent-li">
						<p>
							<i class="icon-circle"></i> <span class="nickname rate">这里是昵称</span>
							<span class="rate-time"> <span class="rate">2017-5-17</span>
								<span class="time">16:10:10</span>
							</span>
						</p>
					</li>
					<li class="cotent-li">
						<p>
							<i class="icon-circle"></i> <span class="nickname rate">这里是昵称</span>
							<span class="rate-time"> <span class="rate">2017-5-17</span>
								<span class="time">16:10:10</span>
							</span>
						</p>
					</li>
					<li class="cotent-li">
						<p>
							<i class="icon-circle"></i> <span class="nickname rate">这里是昵称</span>
							<span class="rate-time"> <span class="rate">2017-5-17</span>
								<span class="time">16:10:10</span>
							</span>
						</p>
					</li>
					<li class="cotent-li">
						<p>
							<i class="icon-circle"></i> <span class="nickname rate">这里是昵称</span>
							<span class="rate-time"> <span class="rate">2017-5-17</span>
								<span class="time">16:10:10</span>
							</span>
						</p>
					</li>
				</ul>
				<ul class="clearfix content-show-li  par-border pocket-ul "
					style="display: none;">
					<li class="cotent-li">
						<p class="odd-border">
							<i class="icon-circle"></i> <span class="nickname rate">这里是昵称</span>
							<span class="nick-price rate">20.00元</span> <span
								class="rate-time"> <span class="rate">2017-5-17</span> <span
								class="time">16:10:10</span>
							</span>
						</p>
					</li>
					<li class="cotent-li">
						<p>
							<i class="icon-circle"></i> <span class="nickname rate">这里是昵称</span>
							<span class="nick-price rate">20.00元</span> <span
								class="rate-time"> <span class="rate">2017-5-17</span> <span
								class="time">16:10:10</span>
							</span>
						</p>
					</li>
				</ul>
				<ul class="clearfix  recommend-ul pocket-ul" style="display: none;">
					<li><i class="icon-circle"></i> <span class="nickname rate">这里是推荐人</span>
						<span class="nick-price rate">2017-5-17&nbsp;&nbsp;0:00&nbsp;&nbsp;&nbsp;&nbsp;至&nbsp;&nbsp;&nbsp;&nbsp;2017-5-18&nbsp;&nbsp;0:00</span>
						<span class="oneday rate">1天</span> <span
						class="rate-time-recommend"> <span class="rate">2017-5-16</span>
							<span class="time">16:10:10</span>
					</span></li>
				</ul>

				<ul class="red-ul pocket-ul" style="display: none;">
					<li class="cotent-li">
						<p class="odd-border">
							<i class="icon-circle"></i> <span class="nickname rate">这里是昵称</span>
							<span class="nick-price rate">20.00元</span> <span
								class="num rate">40个</span> <span class="rate-time"> <span
								class="rate">2017-5-17</span> <span class="time">16:10:10</span>
							</span>
						</p>
					</li>
					<li class="cotent-li">
						<p class="odd-border">
							<i class="icon-circle"></i> <span class="nickname rate">这里是昵称</span>
							<span class="nick-price rate">20.00元</span> <span
								class="num rate">40个</span> <span class="rate-time"> <span
								class="rate">2017-5-17</span> <span class="time">16:10:10</span>
							</span>
						</p>
					</li>
				</ul>
				<ul class="reward-ul pocket-ul" style="display: none;">

				</ul>
			</div>
			<div id="page"></div>
			<!--<div class="pagination">
				   <ul class="clearfix page">
				   	<span class="pre"><</span>
				   	<li>1</li>
				   	<li>2</li>
				   	<li>3</li>
				   	<li class="page-clicked">4</li>
				   	<li>5</li>
				   	<li>...</li>
				   	<li>15</li>
				   	<li>30</li>
				   	<li>45</li>
				   	<span class="next">></span>
				   </ul>
				</div>-->
		</div>

	</div>
	<script type="text/javascript">
		$(function() {
	
			var lis = $('.share-nav-all li');
			var listxt = $('.pocket-ul');
			lis.click(function() {
				$(this).addClass('li-clicked').siblings().removeClass('li-clicked');
				var index = lis.index(this);
				listxt.eq(index).show().siblings().hide();
	
			});
	
			$("#page").Page({
				totalPages : 9, //分页总数
				liNums : 8, //分页的数字按钮数(建议取奇数)
				activeClass : 'activP', //active 类样式定义
				callBack : function(page) {
					console.log(page)
				}
			});
	
	
	
			//				$('.share-nav-all li').click(function(){
			//          		$(this).addClass('li-clicked').siblings().removeClass('li-clicked');
			//          	})
			//				
	
			//				
			//				$('.red-pocket').click(function(){
			//					$('.content-show-li').hide();
			//					$('.pocket').show();
			//				})
			//				
			var index = 0;
			var page = $('.page li');
			$('.page li').click(function() {
				$(this).addClass("page-clicked").siblings().removeClass("page-clicked");
				//					console.log($(this).index());
				index = $(this).index();
				console.log(index);
			})
		//				if(page.className('page-clicked')){
		//					alert($(this).index());
		//				}
		//				$('.pre').click(function(){
		//					$(this).addClass("page-clicked");
		//					index--;
		//					console.log(index);
		//					page.eq(index).addclass('page-clicked').siblings().removeClass('page-clicked');
		//				})
		})
	</script>
</body>
</html>
