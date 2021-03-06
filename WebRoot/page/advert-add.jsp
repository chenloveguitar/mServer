<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>广告添加</title>
<link rel="stylesheet" type="text/css" href="../common/css/luzou.css" />
<link rel="stylesheet" type="text/css" href="common/css/luzou.css" />
<script src="../common/lib/jquery-1.9.0.min.js" type="text/javascript"
	charset="utf-8"></script>
<script src="common/lib/jquery-1.9.0.min.js" type="text/javascript"
	charset="utf-8"></script>
</head>
<body>
	<div class="user-add">
		<div class="banner-add">
			<p class="ad-title">添加轮播广告</p>
			<div class="ad-content">
				<ul class="clearfix ad-clone banner_length" id="lunbo0">
					<li class="add add-two "><a href="javaScript:;"></a>
						<p>添加图片</p> <input type="file" name="" id="" value=""
						onchange="upload(this)" /></li>
					<li class="ad-detail">
						<p class="ad-name">
							<span> <select class="select1">
									<option value="头条">头条</option>
									<option value="发现">发现</option>
									<option value="有去处">有去处</option>
									<option value="商品">商品</option>
									<option value="服务">服务</option>
							</select>

							</span> <span> <select class="select2">
									<option value="上架">上架</option>
									<option value="不上架">不上架</option>
							</select>
							</span>

						</p>
						<p class="ad-link">
							<input type="text" name="" id="" value="" /> <span>对象id</span>
						</p>
					</li>
				</ul>
				<div class="add-another" id="lunbo-add">
					<span class="span-two"> <i class="span-one"></i> 添加一组
					</span>
				</div>
			</div>
		</div>
		<div class="ad-add-save ">
			<input type="submit" name="" id="banner" value="保存" class="ad-save" />
			<input type="submit" name="" id="" value="取消" class="ad-cancel" />
		</div>
		<div class="banner-add detail-add">
			<p class="ad-title">添加详情广告</p>
			<div class="ad-content">
				<ul class="clearfix ad-clone detail_length" id="detail0">
					<li class="add add-two "><a href="javaScriipt:;"></a>
						<p>添加图片</p> <input type="file" name="" id="" value=""
						onchange="upload(this)" /></li>
					<li class="ad-detail">
						<p class="ad-name">
							<input type="text" name="" id="" value="" /> <span>图片名称</span>
						</p>
						<p class="ad-link">
							<input type="text" name="" id="" value="" /> <span>广告链接</span>
						</p>
					</li>
				</ul>
				<div class="add-another" id="detail-add">
					<span class="span-two"> <i class="span-one"></i> 添加一组
					</span>
				</div>
			</div>
		</div>
		<div class="ad-add-save">
			<input type="submit" name="" id="xiangqing" value="保存"
				class="ad-save" /> <input type="submit" name="" id="" value="取消"
				class="ad-cancel" />
		</div>
	</div>
	<script type="text/javascript">
		$(function() {
			//轮播图添加一组
			var x = $('.ad-clone').clone();
			var num = 0;
			$('#lunbo-add').click(function() {
				num++;
				var a = x.clone(true).insertBefore($(this));
				a.attr("id", 'lunbo' + num);
	
			})
			//详情添加一组
			var x = $('#detail0').clone();
			var n = 0;
			$('#detail-add').click(function() {
				n++;
				var a = x.clone(true).insertBefore($(this));
				a.attr("id", 'detail' + n);
	
			})
	
			//保存取消操作
			$(".ad-add-save input").click(function() {
				$(this).addClass("ad-save").siblings().removeClass("ad-save");
				$(".ad-cancel").click(function() {
					window.location.reload();
				})
			})
	
		})
		//轮播图片上传
		function upload(obj) {
			var sum = obj.files.length;
			var file = obj.files[0];
			var name = obj.files[0].name;
			var th = $(obj).parent(".add");
			var nam = $(obj).parent(".add").siblings(".ad-detail").find(".ad-name").find("input");
			str = "";
			$(obj).parent(".add").html(" ");
			if (window.FileReader) {
				var reader = new FileReader();
				reader.readAsDataURL(file);
				reader.onloadend = function(e) {
					str = "<img width='100%' height='100%'  src=" + e.target.result + ">";
					th.append(str);
					nam.val(name);
				};
			}
	
	
	
		}
		$("#banner").click(function() {
			var a = $("#banner_length").length;
	
			var arr = []
			for (var i = 0; i < a; i++) {
				arr.push($("#lunbo" + i).find(".add").find("img").src)
				arr.push($("#lunbo" + i).find(".add").find(".ad-detail").find(".ad-name").find(".select1").val())
				arr.push($("#lunbo" + i).find(".add").find(".ad-detail").find(".ad-name").find(".select2").val())
				arr.push($("#lunbo" + i).find(".ad-detail").find(".ad-link").find("input").val())
			}
	
			$.ajax({
				url : "/mServer/Handle_guanggao",
				type : "POST",
				data : {
					"banner" : arr
				},
				dataType : "json",
				success : function(message) {},
				error : function() {
					alert("error");
				}
			});
		})
	</script>
</body>
</html>
