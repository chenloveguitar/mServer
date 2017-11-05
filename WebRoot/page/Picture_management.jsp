<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
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
<body>
	<div class="content">
		<div class="position-header">
			<ul class="clearfix">
				<li class="position-header-click"><img
					src="../common/image/icon-1.png" />
					<p>全部</p></li>
				<li><img src="../common/image/icon-2.png" />
					<p>首页推荐</p></li>
				<li><img src="../common/image/icon-3.png" />
					<p>今日头条</p></li>
				<li><img src="../common/image/icon-4.png" />
					<p>酒域日记</p></li>
				<li><img src="../common/image/icon-5.png" />
					<p>图片</p></li>
				<li><img src="../common/image/icon-6.png" />
					<p>发现推荐</p></li>
				<li><img src="../common/image/icon-7.png" />
					<p>发现秘密</p></li>
				<li><img src="../common/image/icon-8.png" />
					<p>有去处</p></li>
				<li><img src="../common/image/icon-9.png" />
					<p>逛街</p></li>
			</ul>
		</div>
		<div class="position-content">
			<div class="position-status">
				<select name="" class="input-text">
					<option value="">状态</option>
					<option value=""></option>
					<option value=""></option>
				</select> <select name="" class="input-text">
					<option value="">是否推荐</option>
					<option value="">是</option>
					<option value="">否</option>
				</select> <input type="text" placeholder="日期查找" class="input-rate "
					id="rate-search" /> <input type="text" name="" id="" value=""
					placeholder="关键字" class="input-rate key-bord" /> <select name=""
					class="third-select">
					<option value="">排序</option>
					<option value="">正序</option>
					<option value=""></option>
				</select> <a href="content.jsp" class="rebuild">新增内容</a>
			</div>
			<div class="position-show">
				<ul class="clearfix">
					<li>
						<p class="position-show-title">
							<i class="position-circle"></i> <i class="position-square"></i> <span
								class="position-title"> <span>这里是标题</span> <span
								class="position-share">阅读量:2356 分享:27 收藏:23</span>
							</span>

						</p>
						<p class="position-author">
							<span class="icon-author"></span> <span class="position-title">
								<span>作者王大崔子</span> <span class="position-share">2017年5月18日
									14:35:48</span>
							</span>
						</p>
						<p class="position-public">
							<span class="position-title position-p"> <span>已发布</span>
								<span class="position-share">首页推荐</span>
							</span> <span class="position-title position-p"> <span>红包</span>
								<span class="position-share">剩余0元</span>
							</span> <span class="position-title position-p"> <span>未推广</span>
								<span class="position-share">剩余0天</span>
							</span>
						</p>
						<p class="edit-exit">
							<i class="icon-edit icon-webpage"></i> <i
								class="icon-edit icon-del"></i>
						</p>
					</li>

					<li>
						<p class="position-show-title">
							<i class="position-circle"></i> <i class="position-square"></i> <span
								class="position-title"> <span>这里是标题</span> <span
								class="position-share">阅读量:2356 分享:27 收藏:23</span>
							</span>

						</p>
						<p class="position-author">
							<span class="icon-author"></span> <span class="position-title">
								<span>作者王大崔子</span> <span class="position-share">2017年5月18日
									14:35:48</span>
							</span>
						</p>
						<p class="position-public">
							<span class="position-title position-p"> <span>已发布</span>
								<span class="position-share">首页推荐</span>
							</span> <span class="position-title position-p"> <span>红包</span>
								<span class="position-share">剩余0元</span>
							</span> <span class="position-title position-p"> <span>未推广</span>
								<span class="position-share">剩余0天</span>
							</span>
						</p>
						<p class="edit-exit">
							<i class="icon-edit icon-webpage"></i> <i
								class="icon-edit icon-del"></i>
						</p>
					</li>
				</ul>

				<ul class="clearfix" style="display: none;">
					<li>
						<p class="position-show-title">
							<i class="position-circle"></i> <i class="position-square"></i> <span
								class="position-title"> <span>这里是标题</span> <span
								class="position-share">阅读量:2356 分享:27 收藏:23</span>
							</span>

						</p>
						<p class="position-author">
							<span class="icon-author"></span> <span class="position-title">
								<span>作者王大崔子</span> <span class="position-share">2017年5月18日
									14:35:48</span>
							</span>
						</p>
						<p class="position-public">
							<span class="position-title position-p"> <span>已发布</span>
								<span class="position-share">首页推荐</span>
							</span> <span class="position-title position-p"> <span>红包</span>
								<span class="position-share">剩余0元</span>
							</span> <span class="position-title position-p"> <span>未推广</span>
								<span class="position-share">剩余0天</span>
							</span>
						</p>
						<p class="edit-exit">
							<i class="icon-edit icon-webpage"></i> <i
								class="icon-edit icon-del"></i>
						</p>
					</li>
				</ul>
				<ul class="clearfix" style="display: none;">
					<li>
						<p class="position-show-title">
							<i class="position-circle"></i> <i class="position-square"></i> <span
								class="position-title"> <span>这里是标题</span> <span
								class="position-share">阅读量:2356 分享:27 收藏:23</span>
							</span>

						</p>
						<p class="position-author">
							<span class="icon-author"></span> <span class="position-title">
								<span>作者王大崔子</span> <span class="position-share">2017年5月18日
									14:35:48</span>
							</span>
						</p>
						<p class="position-public">
							<span class="position-title position-p"> <span>已发布</span>
								<span class="position-share">首页推荐</span>
							</span> <span class="position-title position-p"> <span>红包</span>
								<span class="position-share">剩余0元</span>
							</span> <span class="position-title position-p"> <span>未推广</span>
								<span class="position-share">剩余0天</span>
							</span>
						</p>
						<p class="edit-exit">
							<i class="icon-edit icon-webpage"></i> <i
								class="icon-edit icon-del"></i>
						</p>
					</li>
					<li>
						<p class="position-show-title">
							<i class="position-circle"></i> <i class="position-square"></i> <span
								class="position-title"> <span>这里是标题</span> <span
								class="position-share">阅读量:2356 分享:27 收藏:23</span>
							</span>

						</p>
						<p class="position-author">
							<span class="icon-author"></span> <span class="position-title">
								<span>作者王大崔子</span> <span class="position-share">2017年5月18日
									14:35:48</span>
							</span>
						</p>
						<p class="position-public">
							<span class="position-title position-p"> <span>已发布</span>
								<span class="position-share">首页推荐</span>
							</span> <span class="position-title position-p"> <span>红包</span>
								<span class="position-share">剩余0元</span>
							</span> <span class="position-title position-p"> <span>未推广</span>
								<span class="position-share">剩余0天</span>
							</span>
						</p>
						<p class="edit-exit">
							<i class="icon-edit icon-webpage"></i> <i
								class="icon-edit icon-del"></i>
						</p>
					</li>
				</ul>
				<ul class="clearfix" style="display: none;">
					<li>
						<p class="position-show-title">
							<i class="position-circle"></i> <i class="position-square"></i> <span
								class="position-title"> <span>这里是标题</span> <span
								class="position-share">阅读量:2356 分享:27 收藏:23</span>
							</span>

						</p>
						<p class="position-author">
							<span class="icon-author"></span> <span class="position-title">
								<span>作者王大崔子</span> <span class="position-share">2017年5月18日
									14:35:48</span>
							</span>
						</p>
						<p class="position-public">
							<span class="position-title position-p"> <span>已发布</span>
								<span class="position-share">首页推荐</span>
							</span> <span class="position-title position-p"> <span>红包</span>
								<span class="position-share">剩余0元</span>
							</span> <span class="position-title position-p"> <span>未推广</span>
								<span class="position-share">剩余0天</span>
							</span>
						</p>
						<p class="edit-exit">
							<i class="icon-edit icon-webpage"></i> <i
								class="icon-edit icon-del"></i>
						</p>
					</li>
				</ul>
				<ul class="clearfix" style="display: none;">
					<li>
						<p class="position-show-title">
							<i class="position-circle"></i> <i class="position-square"></i> <span
								class="position-title"> <span>这里是标题</span> <span
								class="position-share">阅读量:2356 分享:27 收藏:23</span>
							</span>

						</p>
						<p class="position-author">
							<span class="icon-author"></span> <span class="position-title">
								<span>作者王大崔子</span> <span class="position-share">2017年5月18日
									14:35:48</span>
							</span>
						</p>
						<p class="position-public">
							<span class="position-title position-p"> <span>已发布</span>
								<span class="position-share">首页推荐</span>
							</span> <span class="position-title position-p"> <span>红包</span>
								<span class="position-share">剩余0元</span>
							</span> <span class="position-title position-p"> <span>未推广</span>
								<span class="position-share">剩余0天</span>
							</span>
						</p>
						<p class="edit-exit">
							<i class="icon-edit icon-webpage"></i> <i
								class="icon-edit icon-del"></i>
						</p>
					</li>
				</ul>
				<ul class="clearfix" style="display: none;">
					<li>
						<p class="position-show-title">
							<i class="position-circle"></i> <i class="position-square"></i> <span
								class="position-title"> <span>这里是标题</span> <span
								class="position-share">阅读量:2356 分享:27 收藏:23</span>
							</span>

						</p>
						<p class="position-author">
							<span class="icon-author"></span> <span class="position-title">
								<span>作者王大崔子</span> <span class="position-share">2017年5月18日
									14:35:48</span>
							</span>
						</p>
						<p class="position-public">
							<span class="position-title position-p"> <span>已发布</span>
								<span class="position-share">首页推荐</span>
							</span> <span class="position-title position-p"> <span>红包</span>
								<span class="position-share">剩余0元</span>
							</span> <span class="position-title position-p"> <span>未推广</span>
								<span class="position-share">剩余0天</span>
							</span>
						</p>
						<p class="edit-exit">
							<i class="icon-edit icon-webpage"></i> <i
								class="icon-edit icon-del"></i>
						</p>
					</li>
				</ul>
				<ul class="clearfix" style="display: none;">
					<li>
						<p class="position-show-title">
							<i class="position-circle"></i> <i class="position-square"></i> <span
								class="position-title"> <span>这里是标题</span> <span
								class="position-share">阅读量:2356 分享:27 收藏:23</span>
							</span>

						</p>
						<p class="position-author">
							<span class="icon-author"></span> <span class="position-title">
								<span>作者王大崔子</span> <span class="position-share">2017年5月18日
									14:35:48</span>
							</span>
						</p>
						<p class="position-public">
							<span class="position-title position-p"> <span>已发布</span>
								<span class="position-share">首页推荐</span>
							</span> <span class="position-title position-p"> <span>红包</span>
								<span class="position-share">剩余0元</span>
							</span> <span class="position-title position-p"> <span>未推广</span>
								<span class="position-share">剩余0天</span>
							</span>
						</p>
						<p class="edit-exit">
							<i class="icon-edit icon-webpage"></i> <i
								class="icon-edit icon-del"></i>
						</p>
					</li>
				</ul>
				<ul class="clearfix" style="display: none;">
					<li>
						<p class="position-show-title">
							<i class="position-circle"></i> <i class="position-square"></i> <span
								class="position-title"> <span>这里是标题</span> <span
								class="position-share">阅读量:2356 分享:27 收藏:23</span>
							</span>

						</p>
						<p class="position-author">
							<span class="icon-author"></span> <span class="position-title">
								<span>作者王大崔子</span> <span class="position-share">2017年5月18日
									14:35:48</span>
							</span>
						</p>
						<p class="position-public">
							<span class="position-title position-p"> <span>已发布</span>
								<span class="position-share">首页推荐</span>
							</span> <span class="position-title position-p"> <span>红包</span>
								<span class="position-share">剩余0元</span>
							</span> <span class="position-title position-p"> <span>未推广</span>
								<span class="position-share">剩余0天</span>
							</span>
						</p>
						<p class="edit-exit">
							<i class="icon-edit icon-webpage"></i> <i
								class="icon-edit icon-del"></i>
						</p>
					</li>
				</ul>
				<ul class="clearfix" style="display: none;">
					<li>
						<p class="position-show-title">
							<i class="position-circle"></i> <i class="position-square"></i> <span
								class="position-title"> <span>这里是标题</span> <span
								class="position-share">阅读量:2356 分享:27 收藏:23</span>
							</span>

						</p>
						<p class="position-author">
							<span class="icon-author"></span> <span class="position-title">
								<span>作者王大崔子</span> <span class="position-share">2017年5月18日
									14:35:48</span>
							</span>
						</p>
						<p class="position-public">
							<span class="position-title position-p"> <span>已发布</span>
								<span class="position-share">首页推荐</span>
							</span> <span class="position-title position-p"> <span>红包</span>
								<span class="position-share">剩余0元</span>
							</span> <span class="position-title position-p"> <span>未推广</span>
								<span class="position-share">剩余0天</span>
							</span>
						</p>
						<p class="edit-exit">
							<i class="icon-edit icon-webpage"></i> <i
								class="icon-edit icon-del"></i>
						</p>
					</li>
					<li>
						<p class="position-show-title">
							<i class="position-circle"></i> <i class="position-square"></i> <span
								class="position-title"> <span>这里是标题</span> <span
								class="position-share">阅读量:2356 分享:27 收藏:23</span>
							</span>

						</p>
						<p class="position-author">
							<span class="icon-author"></span> <span class="position-title">
								<span>作者王大崔子</span> <span class="position-share">2017年5月18日
									14:35:48</span>
							</span>
						</p>
						<p class="position-public">
							<span class="position-title position-p"> <span>已发布</span>
								<span class="position-share">首页推荐</span>
							</span> <span class="position-title position-p"> <span>红包</span>
								<span class="position-share">剩余0元</span>
							</span> <span class="position-title position-p"> <span>未推广</span>
								<span class="position-share">剩余0天</span>
							</span>
						</p>
						<p class="edit-exit">
							<i class="icon-edit icon-webpage"></i> <i
								class="icon-edit icon-del"></i>
						</p>
					</li>
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
