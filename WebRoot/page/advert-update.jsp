<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>广告添加</title>
		<link rel="stylesheet" type="text/css" href="../common/css/luzou.css"/>
		<link rel="stylesheet" type="text/css" href="common/css/luzou.css"/>
		<script src="../common/lib/jquery-1.9.0.min.js" type="text/javascript" charset="utf-8"></script>
		<script src="common/lib/jquery-1.9.0.min.js" type="text/javascript" charset="utf-8"></script>
	</head>
	<body>
		<div class="content">
			<div class="xiangqing-advert">
				<div class="position-header">
					<ul class="clearfix">
						<li class="position-header-click"><img src="../common/image/icon-3.png">
							<p>详情页广告</p></li>
					</ul>
				</div>
				<div class="advert-add-show" id="advert-add-show">
					<ul class="clearfix">
						<li class="li-first">
							<span class="add-advert">
								添加图片
								<input type="file" onchange="upload(this)"/>
							</span>
						</li>
					</ul>
				</div>
				<input type="submit" value="保存" class="advert-add-save"/>
			</div>
			<div class="lunbo-advert">
				<div class="position-header">
					<ul class="clearfix">
						<li class="position-header-click"><img src="../common/image/icon-4.png">
							<p>轮播广告</p></li>
					</ul>
				</div>
				<div class="advert-add-show" id="lunbo-show">
					<ul class="clearfix">
						<li class="li-first">
							<span class="add-advert">
								添加图片
								<input type="file" onchange="up(this)"/>
							</span>
						</li>
					</ul>
				</div>
				<input type="submit" value="保存" class="advert-add-save"/>
			</div>
			
		</div>
		<script type="text/javascript">
			var n=0;
		    function upload(obj){
	            var sum = obj.files.length;
	            console.log(obj);
	            var file = obj.files[0];
	             str = "";        
	             if (window.FileReader) {    
		            var reader = new FileReader();    
		            reader.readAsDataURL(file);    
		            //监听文件读取结束后事件    
			          reader.onloadend = function (e) {
			          	n++;
			            str="<li><img width='100%' height='100%' id='pic"+n+"' src="+e.target.result+"></li>";
		            	$("#advert-add-show ul").prepend(str);   
			         }; 
				}             	
			}
		    
		    
		    var n1=0;
		    function up(obj){
	            var sum = obj.files.length;
	            console.log(obj);
	            var file = obj.files[0];
	             str = "";        
	             if (window.FileReader) {    
		            var reader = new FileReader();    
		            reader.readAsDataURL(file);    
		            //监听文件读取结束后事件    
			          reader.onloadend = function (e) {
			          	n1++;
			            str1="<li><img width='100%' height='100%' id='picture"+n1+"' src="+e.target.result+"></li>";
		            	$("#lunbo-show ul").prepend(str1);   
			         }; 
				}             	
			}
		</script>
	</body>
</html>
