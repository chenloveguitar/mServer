<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>上传图片</title>
<link rel="stylesheet" type="text/css"
	href="../common/iconfont/content/iconfont.css" />
<link rel="stylesheet" type="text/css" href="../common/css/luzou.css" />
<script src="../common/lib/jquery-1.9.0.min.js" type="text/javascript"
	charset="utf-8"></script>
<!--<script src="../common/js/luzhou.js" type="text/javascript" charset="utf-8"></script>-->
<script src="../common/js/rem.js" type="text/javascript" charset="utf-8"></script>
</head>
<body>
	<div class="picture-all">
		<div class="picture-header">
			<a href="##" class="picture-send">上传图片</a> <a href="##">从图库选择</a> <a
				href="##">x</a>
		</div>
		<div class="picture-show" id="preview">
			<!--<img id="imghead" width=100 height=100 src='<%=request.getContextPath()%>/images/defaul.jpg'>-->
			<a href="####">点击选择图片<input type="file"
				style="opacity: 0;cursor:pointer;" onchange="previewImage(this)" /></a>
		</div>
		<hr />
		<span class="submit-all"> <a href="##"
			class="submit submit-yes">确定</a> <a href="###" class="submit">取消</a>
		</span>

	</div>

	<script type="text/javascript">
		function previewImage(file) {
			var MAXWIDTH = 260;
			var MAXHEIGHT = 180;
			var div = document.getElementById('preview');
			if (file.files && file.files[0]) {
				div.innerHTML = '<img id=imghead>';
				var img = document.getElementById('imghead');
				img.onload = function() {
					var rect = clacImgZoomParam(MAXWIDTH, MAXHEIGHT, img.offsetWidth, img.offsetHeight);
					img.width = rect.width;
					img.height = rect.height;
					//                 img.style.marginLeft = rect.left+'px'; 
					img.style.marginTop = rect.top + 'px';
				}
				var reader = new FileReader();
				reader.onload = function(evt) {
					img.src = evt.target.result;
				}
				reader.readAsDataURL(file.files[0]);
			} else //兼容IE 
			{
				var sFilter = 'filter:progid:DXImageTransform.Microsoft.AlphaImageLoader(sizingMethod=scale,src="';
				file.select();
				var src = document.selection.createRange().text;
				div.innerHTML = '<img id=imghead>';
				var img = document.getElementById('imghead');
				img.filters.item('DXImageTransform.Microsoft.AlphaImageLoader').src = src;
				var rect = clacImgZoomParam(MAXWIDTH, MAXHEIGHT, img.offsetWidth, img.offsetHeight);
				status = ('rect:' + rect.top + ',' + rect.left + ',' + rect.width + ',' + rect.height);
				div.innerHTML = "<div id=divhead style='width:" + rect.width + "px;height:" + rect.height + "px;margin-top:" + rect.top + "px;" + sFilter + src + "\"'></div>";
			}
		}
		function clacImgZoomParam(maxWidth, maxHeight, width, height) {
			var param = {
				top : 0,
				left : 0,
				width : width,
				height : height
			};
			if (width > maxWidth || height > maxHeight) {
				rateWidth = width / maxWidth;
				rateHeight = height / maxHeight;
	
				if (rateWidth > rateHeight) {
					param.width = maxWidth;
					param.height = Math.round(height / rateWidth);
				} else {
					param.width = Math.round(width / rateHeight);
					param.height = maxHeight;
				}
			}
	
			param.left = Math.round((maxWidth - param.width) / 2);
			param.top = Math.round((maxHeight - param.height) / 2);
			return param;
			//          document.getElementById("preview").backgroundImage="none";
	
		}
	</script>
</body>
</html>
