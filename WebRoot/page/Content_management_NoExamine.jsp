
<%@page
	import="com.magicmoble.luzhouapp.server.server_function.Server_Func"%>
<%@page import="com.magicmoble.luzhouapp.model.server.Toutiao"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title></title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/common/css/luzou.css" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/common/css/calendar1.css" />
<script src="${pageContext.request.contextPath}/common/lib/jquery-1.9.0.min.js" type="text/javascript" charset="utf-8"></script>
<script src="${pageContext.request.contextPath}/common/js/jquery.min.js" type="text/javascript" charset="utf-8"></script>
<script src="${pageContext.request.contextPath}/common/js/jquery.date_input.pack.js" type="text/javascript" charset="utf-8"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/common/pager/jquery.z-pager.js"></script>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/common/pager/pager.css">
<script type="text/javascript" src="${pageContext.request.contextPath}/common/page/js/Content_management_NoExamine.js"></script>
<style type="text/css">
	.hide{
		display: none;	
	}
</style>
</head>

<%
	List<Toutiao> list = Server_Func.toutiao_NoExamine();
	request.setAttribute("list", list);
	List<Toutiao> list2 = Server_Func.riji_NoExamine();
	request.setAttribute("list2", list2);
	List<Toutiao> list3 = Server_Func.tuji_NoExamine();
	request.setAttribute("list3", list3);
	List<Toutiao> list4 = Server_Func.faxian_NoExamine();
	request.setAttribute("list4", list4);
	List<Toutiao> list5 = Server_Func.quchu_NoExamine();
	request.setAttribute("list5", list5);
	List<Toutiao> list6 = Server_Func.guangjie_NoExamine();
	request.setAttribute("list6", list6);
	List<Toutiao> list7 = new ArrayList<Toutiao>();
	list7.addAll(list);
	list7.addAll(list2);
	list7.addAll(list3);
	list7.addAll(list4);
	list7.addAll(list5);
	list7.addAll(list6);
	request.setAttribute("list7", list7);
%>
<body>
	<div class="content">
		<div class="position-header">
			<ul class="clearfix">
				<li class="position-header-click" onclick="Tag(1)"><img
					src="../common/image/icon-1.png" />
					<p>全部</p></li>
				<li onclick="Tag(2)"><img src="${pageContext.request.contextPath}/common/image/icon-3.png" />
					<p>今日头条</p></li>
				<li onclick="Tag(3)"><img src="${pageContext.request.contextPath}/common/image/icon-4.png" />
					<p>酒域日记</p></li>
				<li onclick="Tag(4)"><img src="${pageContext.request.contextPath}/common/image/icon-5.png" />
					<p>图片</p></li>
				<li onclick="Tag(5)"><img src="${pageContext.request.contextPath}/common/image/icon-7.png" />
					<p>发现秘密</p></li>
				<li onclick="Tag(6)"><img src="${pageContext.request.contextPath}/common/image/icon-8.png" />
					<p>有去处</p></li>
				<li onclick="Tag(7)"><img src="${pageContext.request.contextPath}/common/image/icon-9.png" />
					<p>逛街</p></li>
			</ul>
		</div>
		<div class="position-content">
			<div class="position-status">
				<select name="" class="input-text" id="change_recommend">
					<option value="">是否推荐</option>
					<option value="已推荐">已推荐</option>
					<option value="未推荐">未推荐</option>
				</select> <select id="change_hongbao" class="input-text">
					<option value="">红包</option>
					<option value="还有余额">还有余额</option>
					<option value="余额用尽">余额用尽</option>
					<option value="没有红包">没有红包</option>
				</select> <select id="change_dashang" class="input-text">
					<option value="">打赏</option>
					<option value="有打赏">有打赏</option>
					<option value="没有打赏">没有打赏</option>
				</select> <input type="text" name="" value="" id="guangjianzi_search"
					placeholder="关键字" class="input-rate" />
						<a href="javascript:void(0);" id="guangjianzi_search_button" style="position: absolute; z-index: 2; margin-left: -70px; margin-top: 8px;">
							<img src="${pageContext.request.contextPath}/common/image/search.png">
						</a>
					<select id="change_paixu" class="third-select">
					<option value="">排序</option>
					<option value="阅读量从高到低">阅读量从高到低</option>
					<option value="阅读量从低到高">阅读量从低到高</option>
					<option value="阅读量从高到低">日期从早到晚</option>
					<option value="阅读量从低到高">日期从晚到早</option>
				</select><a href="neirong_content.jsp" class="rebuild">新增内容</a>
			</div>
			<div class="position-write">
				<ul class="clearfix">
					<li class="position-show-title1">标题内容</li>
					<li class="position-author1">作者</li>
					<li class="position-fa">是否发布</li>
					<li class="position-red1">红包</li>
					<li class="position-red2">红包</li>
					<li class="position-ye">是否推广</li>
					<li class="position-de">操作</li>
				</ul>
			</div>
			<div class="position-show">
				<ul class="clearfix" id="clear-fix-1">

<%-- 					<c:forEach var="list" items="${list7}"> --%>
<!-- 						<li> -->
<!-- 							<p class="position-show-title"> -->
<!-- 								<i class="position-circle"></i>  -->
<%-- 								<img class="position-square" src="${list.pictures.get(0).picture_url}" />  --%> 
<!-- 								<img class="position-square" src="#" />  -->
<%-- 								<span class="position-title"> <span>${list.title}</span> <span --%>
<%-- 									class="position-share">阅读量:${list.yuedu_count} --%>
<%-- 										分享:${list.share_count} 收藏:${list.shoucang_count}</span> --%>
<!-- 								</span> -->

<!-- 							</p> -->
<!-- 							<p class="position-author"> -->
<%--  								<img class="icon-author" src="${list.releaser_touxiang}" />  --%> 
<!-- 								<img class="icon-author" src="#" />  -->
<!-- 								<span -->
<%-- 									class="position-title"> <span>${list.releaser_name}</span> --%>
<%-- 									<span class="position-share">${list.time}</span> --%>
<!-- 								</span> -->
<!-- 							</p> -->
<!-- 							<p class="position-public"> -->
<%-- 								<span class="position-title position-p"> <span>${list.shenhe}</span> --%>
<%-- 									<span class="position-share">${list.tuijian_Tag}</span> --%>
<!-- 								</span> <span class="position-title position-p"> <span>点赞红包</span> -->
<%-- 									<span class="position-share">剩余${list.dianzan_hongbao}元</span> --%>
<!-- 								</span> <span class="position-title position-p"> <span>分享红包</span> -->
<%-- 									<span class="position-share">剩余${list.share_hongbao}元</span> --%>
<%-- 								</span> <span class="position-title position-p"> <span>${list.tuijian_message}</span> --%>
<%-- 									<span class="position-share">剩余${list.days}天</span> --%>
<!-- 								</span> -->
<!-- 							</p> -->
<!-- 							<p class="edit-exit"> -->
<!-- 								<a class="icon-edit icon-webpage" -->
<%-- 									href="<%=request.getContextPath()%>/page/content-detail.jsp?chaxun_id=${list.id}&Tag=1"></a> --%>
<!-- 								<a class="icon-edit icon-del" -->
<%-- 									href="<%=request.getContextPath()%>/Handle_NoExamine_neirong?del_id=${list.id}&Tag=1"></a> --%>
<!-- 							</p> -->
<!-- 						</li> -->
<%-- 					</c:forEach> --%>


				</ul>
				<ul class="clearfix"  id="clear-fix-2" style="display: none;">
<%-- 					<c:forEach var="list" items="${list}"> --%>
<!-- 						<li> -->
<%-- 							<p class="position-show-title" onclick="tiaozhuan('${list.id}')"> --%>
<!-- 								<i class="position-circle"></i>  -->
<%--  								<img class="position-square" src="${list.pictures.get(0).picture_url}" />  --%>
<!-- 								<img class="position-square" src="#" />  -->
<!-- 									<span -->
<%-- 									class="position-title"> <span>${list.title}</span> <span --%>
<%-- 									class="position-share">阅读量:${list.yuedu_count} --%>
<%-- 										分享:${list.share_count} 收藏:${list.shoucang_count}</span> --%>
<!-- 								</span> -->

<!-- 							</p> -->
<!-- 							<p class="position-author"> -->
<%-- 								<img class="icon-author" src="${list.releaser_touxiang}" />  --%>
<!-- 								<img class="icon-author" src="#" />  -->
<!-- 								<span -->
<%-- 									class="position-title"> <span>${list.releaser_name}</span> --%>
<%-- 									<span class="position-share">${list.time}</span> --%>
<!-- 								</span> -->
<!-- 							</p> -->
<!-- 							<p class="position-public"> -->
<%-- 								<span class="position-title position-p"> <span>${list.shenhe}</span> --%>
<%-- 									<span class="position-share">${list.tuijian_Tag}</span> --%>
<!-- 								</span> <span class="position-title position-p"> <span>点赞红包</span> -->
<%-- 									<span class="position-share">剩余${list.dianzan_hongbao}元</span> --%>
<!-- 								</span> <span class="position-title position-p"> <span>分享红包</span> -->
<%-- 									<span class="position-share">剩余${list.share_hongbao}元</span> --%>
<%-- 								</span> <span class="position-title position-p"> <span>${list.tuijian_message}</span> --%>
<%-- 									<span class="position-share">剩余${list.days}天</span> --%>
<!-- 								</span> -->
<!-- 							</p> -->
<!-- 							<p class="edit-exit"> -->
<!-- 								<a class="icon-edit icon-webpage" -->
<%-- 									href="<%=request.getContextPath()%>/page/content-detail.jsp?chaxun_id=${list.id}&Tag=2"></a> <a --%>
<!-- 									class="icon-edit icon-del" -->
<%-- 									href="<%=request.getContextPath()%>/Handle_NoExamine_neirong?del_id=${list.id}&Tag=2"></a> --%>
<!-- 							</p> -->
<!-- 						</li> -->
<%-- 					</c:forEach> --%>
				</ul>
				<ul class="clearfix"  id="clear-fix-3" style="display: none;">
<%-- 					<c:forEach var="list" items="${list2}"> --%>
<!-- 						<li> -->
<!-- 							<p class="position-show-title"> -->
<!-- 								<i class="position-circle"></i>  -->
<%-- 								<img class="position-square" src="${list.pictures.get(0).picture_url}" />  --%> 
<!-- 								<img class="position-square" src="#" />  -->
<!-- 								<span -->
<%-- 									class="position-title"> <span>${list.title}</span> <span --%>
<%-- 									class="position-share">阅读量:${list.yuedu_count} --%>
<%-- 										分享:${list.share_count} 收藏:${list.shoucang_count}</span> --%>
<!-- 								</span> -->

<!-- 							</p> -->
<!-- 							<p class="position-author"> -->
<%--  								<img class="icon-author" src="${list.releaser_touxiang}" />  --%> 
<!-- 								<img class="icon-author" src="#" />  -->
<!-- 								<span -->
<%-- 									class="position-title"> <span>${list.releaser_name}</span> --%>
<%-- 									<span class="position-share">${list.time}</span> --%>
<!-- 								</span> -->
<!-- 							</p> -->
<!-- 							<p class="position-public"> -->
<%-- 								<span class="position-title position-p"> <span>${list.shenhe}</span> --%>
<%-- 									<span class="position-share">${list.tuijian_Tag}</span> --%>
<!-- 								</span> <span class="position-title position-p"> <span>点赞红包</span> -->
<%-- 									<span class="position-share">剩余${list.dianzan_hongbao}元</span> --%>
<!-- 								</span> <span class="position-title position-p"> <span>分享红包</span> -->
<%-- 									<span class="position-share">剩余${list.share_hongbao}元</span> --%>
<%-- 								</span> <span class="position-title position-p"> <span>${list.tuijian_message}</span> --%>
<%-- 									<span class="position-share">剩余${list.days}天</span> --%>
<!-- 								</span> -->
<!-- 							</p> -->
<!-- 							<p class="edit-exit"> -->
<!-- 								<a class="icon-edit icon-webpage" -->
<%-- 									href="<%=request.getContextPath()%>/page/content-detail.jsp?chaxun_id=${list.id}&Tag=3"></a> <a --%>
<!-- 									class="icon-edit icon-del" -->
<%-- 									href="<%=request.getContextPath()%>/Handle_NoExamine_neirong?del_id=${list.id}&Tag=3"></a> --%>
<!-- 							</p> -->
<!-- 						</li> -->
<%-- 					</c:forEach> --%>
				</ul>
				<ul class="clearfix" id="clear-fix-4" style="display: none;">
<%-- 					<c:forEach var="list" items="${list3}"> --%>
<!-- 						<li> -->
<!-- 							<p class="position-show-title"> -->
<!-- 								<i class="position-circle"></i>  -->
<%--  								<img class="position-square" src="${list.pictures.get(0).picture_url}" />  --%> 
<!-- 								<img class="position-square" src="#" />  -->
<!-- 								<span -->
<%-- 									class="position-title"> <span>${list.title}</span> <span --%>
<%-- 									class="position-share">阅读量:${list.yuedu_count} --%>
<%-- 										分享:${list.share_count} 收藏:${list.shoucang_count}</span> --%>
<!-- 								</span> -->

<!-- 							</p> -->
<!-- 							<p class="position-author"> -->
<%--  								<img class="icon-author" src="${list.releaser_touxiang}" />  --%> 
<!-- 								<img class="icon-author" src="#" />  -->
<!-- 								<span -->
<%-- 									class="position-title"> <span>${list.releaser_name}</span> --%>
<%-- 									<span class="position-share">${list.time}</span> --%>
<!-- 								</span> -->
<!-- 							</p> -->
<!-- 							<p class="position-public"> -->
<%-- 								<span class="position-title position-p"> <span>${list.shenhe}</span> --%>
<%-- 									<span class="position-share">${list.tuijian_Tag}</span> --%>
<!-- 								</span> <span class="position-title position-p"> <span>点赞红包</span> -->
<%-- 									<span class="position-share">剩余${list.dianzan_hongbao}元</span> --%>
<!-- 								</span> <span class="position-title position-p"> <span>分享红包</span> -->
<%-- 									<span class="position-share">剩余${list.share_hongbao}元</span> --%>
<%-- 								</span> <span class="position-title position-p"> <span>${list.tuijian_message}</span> --%>
<%-- 									<span class="position-share">剩余${list.days}天</span> --%>
<!-- 								</span> -->
<!-- 							</p> -->
<!-- 							<p class="edit-exit"> -->
<!-- 								<a class="icon-edit icon-webpage" -->
<%-- 									href="<%=request.getContextPath()%>/page/content-detail.jsp?chaxun_id=${list.id}&Tag=4"></a> <a --%>
<!-- 									class="icon-edit icon-del" -->
<%-- 									href="<%=request.getContextPath()%>/Handle_NoExamine_neirong?del_id=${list.id}&Tag=4"></a> --%>
<!-- 							</p> -->
<!-- 						</li> -->
<%-- 					</c:forEach> --%>
				</ul>
				<ul class="clearfix" id="clear-fix-5" style="display: none;">
<%-- 					<c:forEach var="list" items="${list4}"> --%>
<!-- 						<li> -->
<!-- 							<p class="position-show-title"> -->
<!-- 								<i class="position-circle"></i>  -->
<%-- 								<img class="position-square" src="${list.pictures.get(0).picture_url}" />  --%> 
<!-- 									<img class="position-square" src="#" />  -->
<!-- 								<span -->
<%-- 									class="position-title"> <span>${list.title}</span> <span --%>
<%-- 									class="position-share">阅读量:${list.yuedu_count} --%>
<%-- 										分享:${list.share_count} 收藏:${list.shoucang_count}</span> --%>
<!-- 								</span> -->

<!-- 							</p> -->
<!-- 							<p class="position-author"> -->
<%--  								<img class="icon-author" src="${list.releaser_touxiang}" />  --%> 
<!-- 								<img class="icon-author" src="#" />  -->
<!-- 								<span -->
<%-- 									class="position-title"> <span>${list.releaser_name}</span> --%>
<%-- 									<span class="position-share">${list.time}</span> --%>
<!-- 								</span> -->
<!-- 							</p> -->
<!-- 							<p class="position-public"> -->
<%-- 								<span class="position-title position-p"> <span>${list.shenhe}</span> --%>
<%-- 									<span class="position-share">${list.tuijian_Tag}</span> --%>
<!-- 								</span> <span class="position-title position-p"> <span>点赞红包</span> -->
<%-- 									<span class="position-share">剩余${list.dianzan_hongbao}元</span> --%>
<!-- 								</span> <span class="position-title position-p"> <span>分享红包</span> -->
<%-- 									<span class="position-share">剩余${list.share_hongbao}元</span> --%>
<%-- 								</span> <span class="position-title position-p"> <span>${list.tuijian_message}</span> --%>
<%-- 									<span class="position-share">剩余${list.days}天</span> --%>
<!-- 								</span> -->
<!-- 							</p> -->
<!-- 							<p class="edit-exit"> -->
<!-- 								<a class="icon-edit icon-webpage" -->
<%-- 									href="<%=request.getContextPath()%>/page/content-detail.jsp?chaxun_id=${list.id}&Tag=5"></a> <a --%>
<!-- 									class="icon-edit icon-del" -->
<%-- 									href="<%=request.getContextPath()%>/Handle_NoExamine_neirong?del_id=${list.id}&Tag=5"></a> --%>
<!-- 							</p> -->
<!-- 						</li> -->
<%-- 					</c:forEach> --%>
				</ul>
				<ul class="clearfix" id="clear-fix-6" style="display: none;">
<%-- 					<c:forEach var="list" items="${list5}"> --%>
<!-- 						<li> -->
<!-- 							<p class="position-show-title"> -->
<!-- 								<i class="position-circle"></i>  -->
<%-- 							<img class="position-square" src="${list.pictures.get(0).picture_url}" />  --%>
<!-- 								<img class="position-square" src="#" />  -->
<!-- 								<span -->
<%-- 									class="position-title"> <span>${list.title}</span> <span --%>
<%-- 									class="position-share">阅读量:${list.yuedu_count} --%>
<%-- 										分享:${list.share_count} 收藏:${list.shoucang_count}</span> --%>
<!-- 								</span> -->

<!-- 							</p> -->
<!-- 							<p class="position-author"> -->
<%-- 							<img class="icon-author" src="${list.releaser_touxiang}" />  --%> 
<!-- 								<img class="icon-author" src="#" />  -->
<!-- 								<span -->
<%-- 									class="position-title"> <span>${list.releaser_name}</span> --%>
<%-- 									<span class="position-share">${list.time}</span> --%>
<!-- 								</span> -->
<!-- 							</p> -->
<!-- 							<p class="position-public"> -->
<%-- 								<span class="position-title position-p"> <span>${list.shenhe}</span> --%>
<%-- 									<span class="position-share">${list.tuijian_Tag}</span> --%>
<!-- 								</span> <span class="position-title position-p"> <span>点赞红包</span> -->
<%-- 									<span class="position-share">剩余${list.dianzan_hongbao}元</span> --%>
<!-- 								</span> <span class="position-title position-p"> <span>分享红包</span> -->
<%-- 									<span class="position-share">剩余${list.share_hongbao}元</span> --%>
<%-- 								</span> <span class="position-title position-p"> <span>${list.tuijian_message}</span> --%>
<%-- 									<span class="position-share">剩余${list.days}天</span> --%>
<!-- 								</span> -->
<!-- 							</p> -->
<!-- 							<p class="edit-exit"> -->
<!-- 								<a class="icon-edit icon-webpage" -->
<%-- 									href="<%=request.getContextPath()%>/page/content-detail.jsp?chaxun_id=${list.id}&Tag=6"></a><a --%>
<!-- 									class="icon-edit icon-del" -->
<%-- 									href="<%=request.getContextPath()%>/Handle_NoExamine_neirong?del_id=${list.id}&Tag=6"></a> --%>
<!-- 							</p> -->
<!-- 						</li> -->
<%-- 					</c:forEach> --%>
				</ul>
				<ul  class="clearfix" id="clear-fix-7" style="display: none;">
<%-- 					<c:forEach var="list" items="${list6}"> --%>
<!-- 						<li> -->
<!-- 							<p class="position-show-title"> -->
<!-- 								<i class="position-circle"></i>  -->
<%-- 							<img class="position-square" src="${list.pictures.get(0).picture_url}" />  --%> 
<!-- 								<img class="position-square" src="#" />  -->
<!-- 								<span -->
<%-- 									class="position-title"> <span>${list.title}</span> <span --%>
<%-- 									class="position-share">阅读量:${list.yuedu_count} --%>
<%-- 										分享:${list.share_count} 收藏:${list.shoucang_count}</span> --%>
<!-- 								</span> -->

<!-- 							</p> -->
<!-- 							<p class="position-author"> -->
<%--  								<img class="icon-author" src="${list.releaser_touxiang}" />  --%> 
<!-- 								<img class="icon-author" src="#" />  -->
<!-- 								<span -->
<%-- 									class="position-title"> <span>${list.releaser_name}</span> --%>
<%-- 									<span class="position-share">${list.time}</span> --%>
<!-- 								</span> -->
<!-- 							</p> -->
<!-- 							<p class="position-public"> -->
<%-- 								<span class="position-title position-p"> <span>${list.shenhe}</span> --%>
<%-- 									<span class="position-share">${list.tuijian_Tag}</span> --%>
<!-- 								</span> <span class="position-title position-p"> <span>点赞红包</span> -->
<%-- 									<span class="position-share">剩余${list.dianzan_hongbao}元</span> --%>
<!-- 								</span> <span class="position-title position-p"> <span>分享红包</span> -->
<%-- 									<span class="position-share">剩余${list.share_hongbao}元</span> --%>
<%-- 								</span> <span class="position-title position-p"> <span>${list.tuijian_message}</span> --%>
<%-- 									<span class="position-share">剩余${list.days}天</span> --%>
<!-- 								</span> -->
<!-- 							</p> -->
<!-- 							<p class="edit-exit"> -->
<!-- 								<a class="icon-edit icon-webpage" -->
<%-- 									href="<%=request.getContextPath()%>/page/content-detail.jsp?chaxun_id=${list.id}&Tag=7"></a><a --%>
<!-- 									class="icon-edit icon-del" -->
<%-- 									href="<%=request.getContextPath()%>/Handle_NoExamine_neirong?del_id=${list.id}&Tag=7"></a> --%>
<!-- 							</p> -->
<!-- 						</li> -->
<%-- 					</c:forEach> --%>
				</ul>
			</div>
		</div>
		<div class="position-footer">
				<input type="radio" class="select-all" />全选
				<a href="javascript:void(0);" class="position-delete">删除</a> 
				<div id="page-1" class="pager clearfix"></div>
				<div id="page-2" class="pager clearfix hide"></div>
				<div id="page-3" class="pager clearfix hide"></div>
				<div id="page-4" class="pager clearfix hide"></div>
				<div id="page-5" class="pager clearfix hide"></div>
				<div id="page-6" class="pager clearfix hide"></div>
				<div id="page-7" class="pager clearfix hide"></div>
		</div>
	</div>

	<script type="text/javascript">
		var flag = 1;
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
	
	
	
		//改变推荐
		$(function() {
			$("#change_recommend").change(function() {
	
	
				$.ajax({
					url : "/mServer/Handle_NoExamine_neirong?Tag=" + flag,
					type : "POST",
					data : {
						"change_rec" : $("#change_recommend").val()
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
	
	
						$("#clear-fix-1").html(str);
					},
					error : function() {
						alert("error");
					}
				});
			})
	
			$("#change_hongbao").change(function() {
	
	
				$.ajax({
					url : "/mServer/Handle_NoExamine_neirong?Tag=" + flag,
					type : "POST",
					data : {
						"change_hongbao" : $("#change_hongbao").val()
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
	
	
						$("#clear-fix-1").html(str);
					},
					error : function(data) {
						console.log(data);
						alert("error");
					}
				});
			})
			$("#guangjianzi_search_button").click(function() {
				if($("#guangjianzi_search").val().trim() != ""){
					$.ajax({
						url : "/mServer/Handle_NoExamine_neirong?Tag=" + flag,
						type : "POST",
						data : {
							"guangjianzi_search" : $("#guangjianzi_search").val()
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
		
		
							$("#clear-fix-1").html(str);
						},
						error : function() {
							alert("error");
						}
					});
				}
			})
			$("#change_dashang").change(function() {
	
	
				$.ajax({
					url : "/mServer/Handle_NoExamine_neirong?Tag=" + flag,
					type : "POST",
					data : {
						"change_dashang" : $("#change_dashang").val()
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
	
	
						$("#clear-fix-1").html(str);
					},
					error : function() {
						alert("error");
					}
				});
			})
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
						$("#clear-fix-1").html(str);
					},
					error : function() {
						alert("error");
					}
				});
			})
		})
		function Tag(a) {
			$("ul[id^='clear-fix-']").hide();
			$("#clear-fix-"+a).show();
			$("div[id^='page-']").hide();
			$("#page-"+a).show();
			flag = a;
		}
		function tiaozhuan(a) {
			window.location.href = "content-detail.jsp?id=" + a;
		}
	</script>
</body>
</html>
