<%@page import="java.io.File"%>
<%@page
	import="com.magicmoble.luzhouapp.server.server_function.Server_Function"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>图片管理</title>
<link rel="stylesheet" type="text/css" href="../common/css/luzou.css" />
<link rel="stylesheet" type="text/css"
	href="../common/css/calendar1.css" />
<link rel="stylesheet" href="../common/layer/theme/default/layer.css">

<script src="../common/lib/jquery-1.9.0.min.js" type="text/javascript"
	charset="utf-8"></script>
<script src="../common/js/rem.js" type="text/javascript" charset="utf-8"></script>
<script src="../common/js/jquery.min.js" type="text/javascript"
	charset="utf-8"></script>
<script src="../common/js/jquery.date_input.pack.js"
	type="text/javascript" charset="utf-8"></script>
<script src="../common/layer/layer.js" type="text/javascript"></script>
<!-- <script type="text/javascript" src="../common/js/jquery.1.7.2.js"></script> -->
<script type="text/javascript" src="../common/js/ajaxfileupload.js"></script>
<style type="text/css">
	.select{
		background: url("../common/image/images/button_marquee_selected.png") !important;
	}
</style>
</head>
<%
	List<String> list = Server_Function.getFolder(request.getRealPath("/upload"));
	request.setAttribute("list", list);
%>
<body>
	<div class="picture-manage">
		<div class="manage-show">
			<p class="manage-nav">
				<a href="##" class="manage-upload"
					style="display: none;position: relative;">上传 <input type="file"
					name="file" id="file" multiple="multiple"
					style="position:absolute;width: 130px;height:50px;opacity:0;left: 0;top: 0;"
					onchange="upload(this)" /></a> <a href="javascript:createNewFolder();" class="manage-rebuild">新建文件夹</a>
				<a href="javascript:deleteFolders()" class="manage-delete">删除</a> <a href="javascript:renameFolder()"
					class="manage-rename">重命名</a> 
					<span class="nanage-nav-right clearfix"> 
						<input onchange="javascript:search('searchDate',null);" type="text" placeholder="日期查找" class=" " id="searchDate" name="searchDate"
							style="height: 34px;margin-top: 5px;margin-right: 2%;border-radius: 5px;border: none;border:1px solid #eee;padding-left: 5px;" />
<!-- 							background: url(../common/image/search.png) no-repeat right; -->
						<input type="text" name="searchWords" id="searchWords" value="" placeholder="关键字" class=""
							style="height: 34px;vertical-align: middle;border-radius: 5px;border: none;border:1px solid #eee;padding-left: 5px;background-color:#fff ;" />
							<a id="searchIcon" href="javascript:search('searchWords',null);" style="height: 32px; padding: 0; margin: 0; position: relative; top: 14px; left: -45px; border: 0px;"><img src="../common/image/search.png"></a>
						<select onchange="javascript:search('sort',this);" name="sort" id="sort" class=""
							style="height:34px;width: 20%;padding-left: 5px;vertical-align: middle;float: right;margin: 6px;background-color: #fff;">
							<option value="-1">排序</option>
							<option value="desc">正序</option>
							<option value="asc">降序</option>
						</select>
					</span>
					
			</p>
			<p class="manage-all">
				<a href="javascript:showImages(null,'images');">全部文件</a> <a href="###">返回上一级</a> <a href="##">这里是路径
					></a> <a href="##">位置 ></a> 
					<a href="##">某个文件夹</a> 
					<span>
						<input type="checkbox" onclick="selectAll();" name="" id="" value="" />已选中<b id="selectedCount">0</b>个文件夹 已全部加载，共<b id="folderCount">0</b>个
					</span>
			</p>
		</div>
		<div class="manage-list">
			<ul class="clearfix manage-list-ul">
<%-- 				<c:forEach var="list" items="${list}"> --%>
<!-- 					<li class="manage-list-li" id="li"> -->
<!-- 						<div class="file-div"> -->
<!-- 							<img src="../common/image/images/picc.jpg" /><span -->
<!-- 								class="manage-choose"></span> -->
<!-- 						</div> -->
<%-- 						<p class="filename">${list}</p> --%>
<!-- 					</li> -->
<%-- 				</c:forEach> --%>
			</ul>
			<ul class="clearfix manage-image" style="display: none;"
				id="clear-fix">
			</ul>
		</div>
	</div>

	<div class="manage-cover" style="display: none;"></div>
	<div class="manage-cover-delete" style="display: none;">
		<p>确定删除</p>
		<p>
			<a href="###" class="manage-cancel">取消</a> <a href="###"
				class="manage-submit" id="manage-delete">确定</a>
		</p>
	</div>
	<div id="popup">
<!-- 		<div class="manage-cover-rename" style="display: none;"> <p> <input type="text" name="" id="" value="" placeholder="请输入文件夹名称" class="rename-value" /> </p> <p> <a href="###" class="manage-rename-cancel">取消</a> <a href="###" class="manage-rename-submit">确定</a> </p> </div> -->
	</div> 
	<div id="outerdiv">
		<div id="innerdiv"><img id="bigimg" src=""/></div>
	</div>
	<script type="text/javascript">
	var pid = -1;
	//表示当前是文件夹路径还是文件路径
	var flagPath = 0;
	//搜索类型,默认是文件夹
	window.searchPath = "folder";
	$(function() {
		initFolderList();
		//日历
		$('#searchDate').date_input();
		
		$(".images img").live("dblclick", function() {
			var _this = $(this);
			imgShow("#outerdiv", "#innerdiv", "#bigimg", _this);
		})
		
		//图片操作
		var flag = true;

		$(".images").live("click", function() {
			if (flag) {
				//						
				$(this).children(".se").css("background", "url(../common/image/images/button_marquee_selected.png)");
				//重命名
				flag = false;
				$(".manage-image-rename").click(function() {
					var a = $(this).parent().parent().children(".picture-name");
					$(".manage-cover").show();
					$(".manage-cover-rename").show();
					$(".manage-rename-cancel").click(function() {
						$(".manage-cover").hide();
						$(".manage-cover-rename").hide();
					})
					$(".manage-rename-submit").click(function() {
						$(".manage-cover").hide();
						$(".manage-cover-rename").hide();
						var value = $(".rename-value").val();
						a.html(value);
					})
				})
				//删除
				$(".manage-image-delete").click(function() {
					var a = $(this).parent().parent().parent();
					$(".manage-cover").show();
					$(".manage-cover-delete").show();
					$(".manage-cancel").click(function() {
						$(".manage-cover").hide();
						$(".manage-cover-delete").hide();
						$(".manage-delete").removeClass("manage-click");
					})
					$(".manage-submit").click(function() {
						$(".manage-cover").hide();
						$(".manage-cover-delete").hide();
						a.remove();
					})
				})
			} else {
				$(this).children(".se").css("background", "url(../common/image/images/button_marquee.png)");
				flag = true;
			}

		})
	})
	function deleteImage(id){
		if(id){
			$.ajax({
				url:'${pageContext.request.contextPath}/CommonServlet',
				type:'post',
				dataType:'json',
				data:{
					id:id,
					type:'deleteImage'
				},
				success:function(data){
					var results = data.data;
                	if(data.code === "0000" && data.data){
                		//进行页面元素删除操作
                		$(".clearfix.manage-image li[data-id='"+id+"']").remove();
                		alert("删除文件成功!");
                	}else{
                		alert("删除文件失败!");
                	}
				},
				error:function(data){
					alert("删除文件失败!")
				}
			});
		}
	}
	
	function editImageName(id,fileName){
		if(id){
			var html = getPopup();
			var name = fileName.substring(0,fileName.indexOf(".")-1);
			var extName = fileName.substring(fileName.indexOf("."),fileName.length);
			$("body").append(html);
			$(".rename-value").val(name);
			$(".manage-cover").show();
			$(".manage-cover-rename").show();
			$(".manage-rename-cancel").click(function() {
				$(".manage-cover").hide();
				$(".manage-cover-rename").remove();
			})
			$(".manage-rename-submit").click(function() {
				$(".manage-cover").hide();
				var value = $(".rename-value").val();
				//数据库存储操作
				$.ajax({
					url:'${pageContext.request.contextPath}/CommonServlet',
					data:{'type':'renameImage','imageName':value + extName,id:id},
					type:'post',
					dataType:'json',
					success:function(data){
						if(data.code == "0000"){
							$("li[data-id='"+id+"']").find("p").html(value + extName);
							$(".manage-cover-rename").remove();
							alert("文件夹重命名成功！");
						}else{	
							alert("文件夹重命名失败！");
						}
					},
					error:function(data){
						alert("文件夹重命名失败！");
					}
				});
			})
		}else{
			alert("请选择一个文件夹进行重命名操作!");
		}		
	}
	function getImageLi(id,name,url){
		return '<li id="pic" data-id="'+id+'">'+
					'<div class="images">'+
						'<img alt="双击放大查看" src="'+url+'">'+
					'</div>'+
					'<p style="padding-left:0px;">'+
						'<span class="picture-name">bull2.png</span>'+
						'<span class="picc">'+
							'<a href=javascript:editImageName("'+id+'","'+name+'") class="manage-image-rename">'+
								'<img src="../common/image/images/button_edit.png">'+
							'</a>'+
							'<a href=javascript:deleteImage("'+id+'") class="manage-image-delete">'+
								'<img src="../common/image/images/button_delete.png">'+
							'</a>'+
						'</span>'+
					'</p>'+
				'</li>';
		
	}
	//上传图片
	function upload(obj){
		console.log($().jquery);
	    var sum = obj.files.length;
	    $.ajaxFileUpload({
                url: '${pageContext.request.contextPath}/FileUploadServlet?parentId='+pid, //用于文件上传的服务器端请求地址
                secureuri: false, //是否需要安全协议，一般设置为false
                fileElementId: 'file', //文件上传域的ID
                dataType: 'json', //返回值类型 一般设置为json
                success: function (data, status){
                	var results = data.data;
                	if(data.code === "0000"){
                		for(var i=0;i<sum;i++){
    	        	        var url = window.URL.createObjectURL(obj.files[i]);
    	        	        var name = obj.files[i].name;
    	        	        var li = $(getImageLi(results[i],name,url));
    	        		    $(li).appendTo($(".manage-image"));
    	        	    }
                		alert("文件上传成功!");
                	}else{
                		alert("文件上传失败!");
                	}
                },error: function (data, status, e){
               		alert("文件上传失败!");
                }
            });
            return false;
    }
	
	var a = document.getElementsByClassName("manage-list-li");
	var b = document.getElementsByClassName("file-div");
	var m = 0;
	for (var i = 0; i < a.length; i++) {
		m++;
		a[i].setAttribute("id", "file" + m);
		b[i].setAttribute("id", "file-div" + m);
	}
	
	//图片放大操作
	function imgShow(outerdiv, innerdiv, bigimg, _this) {
		var src = _this.attr("src");
		$(bigimg).attr("src", src);

		/*获取当前点击图片的真实大小，并显示弹出层及大图*/
		$("<img/>").attr("src", src).load(function() {
			var windowW = $(window).width();
			var windowH = $(window).height();
			var realWidth = this.width;
			var realHeight = this.height;
			var imgWidth,
				imgHeight;
			var scale = 0.8; //缩放尺寸

			if (realHeight > windowH * scale) { //判断图片高度  
				imgHeight = windowH * scale;
				imgWidth = imgHeight / realHeight * realWidth;
				if (imgWidth > windowW * scale) {
					imgWidth = windowW * scale;
				}
			} else if (realWidth > windowW * scale) { //如图片高度合适，判断图片宽度  
				imgWidth = windowW * scale;
				imgHeight = imgWidth / realWidth * realHeight;
			} else { //如果图片真实高度和宽度都符合要求，高宽不变  
				imgWidth = realWidth;
				imgHeight = realHeight;
			}
			$(bigimg).css("width", imgWidth); //以最终的宽度对图片缩放  

			var w = (windowW - imgWidth) / 2;
			var h = (windowH - imgHeight) / 2;
			$(innerdiv).css({
				"top" : h,
				"left" : w
			});
			$(outerdiv).fadeIn("fast");
		});

		$(outerdiv).click(function() {
			$(this).fadeOut("fast");
		});
	}
		//创建文件夹
		function createNewFolder(){
			var html = getPopup();
			$("body").append(html);
			$(".manage-cover").show();
			$(".manage-cover-rename").show();
			$(".manage-rename-cancel").click(function() {
				$(".manage-cover").hide();
				$(".manage-cover-rename").remove();
			})
			$(".manage-rename-submit").click(function() {
				$(".manage-cover").hide();
				var value = $(".rename-value").val();
				//数据库存储操作
				$.ajax({
					url:'${pageContext.request.contextPath}/CommonServlet',
					data:{'type':'createFolder','folderName':value,'isFolder':'1'},//1表示文件夹，其他值表示文件
					type:'post',
					dataType:'json',
					success:function(data){
						if(data.code == "0000"){
							var id = data.data;
							var ul = $(".clearfix.manage-list-ul").append(getFolder(id,value));
							var newFolder = ul.find("li:last");
							newFolder.find(".manage-choose").click(function(){
								$(this).toggleClass("select");
								$("#selectedCount").html($(".select").length || 0);
							});
							$(".manage-cover-rename").remove();
							$("#folderCount").html(parseInt($("#folderCount").html()) + 1);
						}else{
							alter("文件夹创建失败！");
						}
					},
					error:function(data){
						alter("文件夹创建失败！");
					}
				});
			})
		}
		
		//获取新增或修改文件夹弹出框
		function getPopup(){
			return '<div class="manage-cover-rename" style="display: none;"> <p> <input type="text" name="" id="" value="" placeholder="请输入文件夹名称" class="rename-value" /> </p> <p> <a href="###" class="manage-rename-cancel">取消</a> <a href="###" class="manage-rename-submit">确定</a> </p> </div>';
		}
		
		//获取文件夹列表
		function getFolder(id,value){
			return '<li class="manage-list-li" id="'+id+'"> <div class="file-div"> <a href=javascript:showImages('+id+',"images");><img src="../common/image/images/picc.jpg" /></a><span class="manage-choose"></span> </div> <p class="filename">'+value+'</p> </li>';
		}
		
		//初始化文件夹列表
		function initFolderList(){
			$.ajax({
				url:"${pageContext.request.contextPath}/CommonServlet",
				type:"post",
				data:{type:"getFolders"},
				dataType:"json",
				success:function(data){
					if(data.code == "0000"){
						var results = data.data.result;
						if(results.length > 0){
							for(var index in results){
								var result =  results[index];
								$(".clearfix.manage-list-ul").append(getFolder(result.id,result.fileName));
							}
							$("#folderCount").html(results.length || 0);
						}else{
							alert("无文件夹展示！");
						}
					}
					$.each($(".manage-choose"),function(index,element){
						$(element).on("click",function(){
							$(this).toggleClass("select");
							$("#selectedCount").html($(".select").length || 0);
						});
					});
				},
				error:function(data){
					alert("无文件夹展示！");
				}
			});
		}
		
		//删除文件夹
		function deleteFolders(){
			var selected = $(".select");
			var ids = "";
			$.each(selected,function(index,element){
				var li = $(element).parent().parent();
				var id = li.attr("id");
				ids += id + ",";
			});
			//发送ajax到后台，后台需要有一个删除多个的方法
			if(ids){
				$.ajax({
					url:'${pageContext .request.contextPath}/CommonServlet',
					type:'post',
					dataType:'json',
					data:{
						id:ids,
						type:"deleteFolder"
					},
					success:function(data){
						if(data.code == "0000" && data.data){
							alert("删除文件夹成功!");
							selected.parent().parent().remove();
							$("#folderCount").html(parseInt($("#folderCount").html()) -	 selected.length);
						}else{
							alert("删除文件夹失败!");
						}
					},
					error:function(data){
						alert("删除文件夹失败!");
					}
				});
			} else{
				alert("请选择一个文件夹进行删除操作!");
			}
		}
		
		function renameFolder(){
			var selected = $(".select");
			if(selected.length == 1){
				var folderName = selected.parent().parent().find("p").html();
				var id = selected.parent().parent().attr("id");
				var html = getPopup();
				$("body").append(html);
				$(".rename-value").val(folderName);
				$(".manage-cover").show();
				$(".manage-cover-rename").show();
				$(".manage-rename-cancel").click(function() {
					$(".manage-cover").hide();
					$(".manage-cover-rename").remove();
				})
				$(".manage-rename-submit").click(function() {
					$(".manage-cover").hide();
					var value = $(".rename-value").val();
					//数据库存储操作
					$.ajax({
						url:'${pageContext.request.contextPath}/CommonServlet',
						data:{'type':'renameFolder','folderName':value,'isFolder':'1',id:id},//1表示文件夹，其他值表示文件
						type:'post',
						dataType:'json',
						success:function(data){
							if(data.code == "0000"){
								$("#"+id).find("p").html(value);
								$(".manage-cover-rename").remove();
								alert("文件夹重命名成功！");
							}else{	
								alert("文件夹重命名失败！");
							}
						},
						error:function(data){
							alert("文件夹重命名失败！");
						}
					});
				})
			}else{
				alert("请选择一个文件夹进行重命名操作!");
			}
		}
		
		//显示文件列表
		function showImages(parentId,searchPath){
			if(!parentId){
				parentId = null;
			}
			if(searchPath){
				window.searchPath = searchPath;
			}
			pid = parentId;
			$(".manage-image").show();
			$(".manage-list-ul").hide();
			$(".manage-nav a").hide();
			$(".manage-upload").show();
			$("#searchIcon").show();
			$.ajax({
				url : "${pageContext.request.contextPath}/CommonServlet",
				type : "post",
				data : {
					"parentId" : parentId,
					type:"getImages",
					
				},
				dataType : "json",
				success : function(data) {
					if(data.code === "0000"){
						var results = data.data;
						if(results.length > 0){
							var str = "";
							for (var i in results) {
								str += "	<li id=\"pic\" data-id=\""+results[i].id+"\">"
								str += "		<div class=\"images\">"
								str += "		<img alt=\"双击放大查看\" src=\"${pageContext.request.contextPath}/FileDownLoadServlet?absolutePath=" + results[i].absolutePath + "\" />"
// 								str += "		<a href=\"#\"><img alt=\"双击放大查看\" src=\"${pageContext.request.contextPath}/FileDownLoadServlet?absolutePath=" + results[i].absolutePath + "\" /></a> <i class=\"se\"></i>"
								str += "		</div>"
								str += "	<p style=\"padding-left:0px;\">"
								str += "		<span class=\"picture-name\">" + results[i].fileName + "</span> <span class=\"picc\">"
								str += "			<a href=\"javascript:editImageName('"+ results[i].id +"','"+ results[i].fileName +"')\" class=\"manage-image-rename\"><img"
								str += "			src=\"../common/image/images/button_edit.png\" /></a> <a href=\"javascript:deleteImage('"+ results[i].id +"')\""
								str += "			class=\"manage-image-delete\"><img"
								str += "			src=\"../common/image/images/button_delete.png\" /></a>"
								str += "	</span>"
								str += "	</p>"
								str += "	</li>"
							}
							$("#clear-fix").html(str);
						}else{
							alert("无文件展示!");
						}
					}else{
						alert("获取图片列表失败!");
					}
				},
				error : function() {
					alert("获取图片列表失败!");
				}
			});
		}
		
		function search(searchType,obj){
			var value = "";
			switch(searchType){
				case "sort":
					value = $(obj).val();
					break;
				case "searchWords":
					value = $("#searchWords").val();
					break;
				case "searchDate":
					value = $("#searchDate").val();
					break;
			}
			
			$.ajax({
				url:'${pageContext.request.contextPath}/CommonServlet',
				type:'post',
				dataType:'json',
				data:{
					type:'searchFiles',
					searchPath:searchPath,
					searchType:searchType,
					searchValue:value
				},
				success:function(data){
					if(window.searchPath === "folder"){
						$(".clearfix.manage-list-ul").empty();
						if(data.code == "0000"){
							var results = data.data;
							console.log(results);
							if(results.length > 0){
								for(var index in results){
									var result =  results[index];
									$(".clearfix.manage-list-ul").append(getFolder(result.id,result.fileName));
								}
								$("#folderCount").html(results.length || 0);
							}else{
								alert("无文件夹展示！");
							}
						}
						$.each($(".manage-choose"),function(index,element){
							$(element).on("click",function(){
								$(this).toggleClass("select");
								$("#selectedCount").html($(".select").length || 0);
							});
						});
					}else if(window.searchPath == "images"){
						$(".manage-image").show();
						$(".manage-list-ul").hide();
						$(".manage-nav a").hide();
						$(".manage-upload").show();
						$("#searchIcon").show();
						$(".clearfix.manage-image").empty();
						if(data.code === "0000"){
							var results = data.data;
							if(results.length > 0){
								var str = "";
								for (var i in results) {
									str += "	<li id=\"pic\" data-id=\""+results[i].id+"\">"
									str += "		<div class=\"images\">"
									str += "		<img alt=\"双击放大查看\" src=\"${pageContext.request.contextPath}/FileDownLoadServlet?absolutePath=" + results[i].absolutePath + "\" />"
									str += "		</div>"
									str += "	<p style=\"padding-left:0px;\">"
									str += "		<span class=\"picture-name\">" + results[i].fileName + "</span> <span class=\"picc\">"
									str += "			<a href=\"javascript:editImageName('"+ results[i].id +"','"+ results[i].fileName +"')\" class=\"manage-image-rename\"><img"
									str += "			src=\"../common/image/images/button_edit.png\" /></a> <a href=\"javascript:deleteImage('"+ results[i].id +"')\""
									str += "			class=\"manage-image-delete\"><img"
									str += "			src=\"../common/image/images/button_delete.png\" /></a>"
									str += "	</span>"
									str += "	</p>"
									str += "	</li>"
								}
								$("#clear-fix").html(str);
							}else{
								alert("无文件展示!");
							}
						}else{
							alert("获取图片列表失败!");
						}
					}
					
				},
				error:function(data){
					
				}
			});
		}
		
	</script>
</body>
</html>
