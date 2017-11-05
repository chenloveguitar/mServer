<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page
	import="com.magicmoble.luzhouapp.model.server.Shuoshuo_xiangqing"%>
<%@page
	import="com.magicmoble.luzhouapp.server.server_function.Server_Func"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>


<%
	List<Shuoshuo_xiangqing> list = (List<Shuoshuo_xiangqing>) request.getAttribute("list");
	request.setAttribute("list", list);
%>
<meta charset="UTF-8">
<title></title>
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/common/iconfont/content/iconfont.css" />
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/common/css/luzou.css" />
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/common/css/calendar.css" />
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/common/css/calendar1.css" />
<script
	src="<%=request.getContextPath()%>/common/lib/jquery-1.9.0.min.js"
	type="text/javascript" charset="utf-8"></script>
<!--<script type="text/javascript" src="http://www.js-css.cn/jscode/jquery.min.js"></script>-->
<script src="<%=request.getContextPath()%>/common/js/jquery.min.js"
	type="text/javascript" charset="utf-8"></script>
<script
	src="<%=request.getContextPath()%>/common/js/jquery.date_input.pack.js"
	type="text/javascript" charset="utf-8"></script>
<script src="<%=request.getContextPath()%>/common/js/calendar.js"
	type="text/javascript" charset="utf-8"></script>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/common/lib/scripts/jquery-ui-1.7.2.custom.min.js"></script>
<link rel="Stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/common/lib/style/jqueryui/ui-lightness/jquery-ui-1.7.2.custom.css" />
<script type="text/javascript"
	src="<%=request.getContextPath()%>/common/lib/scripts/jHtmlArea-0.8.min.js"></script>
<link rel="Stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/common/lib/style/jHtmlArea.css" />
</head>
<script>
	$(document).ready(function() {
		var str = "";
		str = '${list.get(0).tuijian_message}';
		$("#change_recommend option").each(function() {
			if ($(this).text() == str) {
				$(this).attr("selected", true);
			}
		});
	});
</script>

<body onload="tuijian_change()">
	<form action="../Upload_file?Tag=2" id="uploadForm"
		enctype="multipart/form-data" method="post">

		<div class="content">
			<!--基本内容-->

			<div class="basic-content">
				<div class="basic-content-title">
					<p>基本内容</p>
				</div>
				<div class="basic-content-cont">
					<br> <br>


					<!--推荐-->


					<p class="content-title">
						是否推荐<select name="is-tuijian" class="input-text"
							style="margin-left:  10px" id="change_recommend">
							<option value="">是否推荐</option>
							<option value="已推荐">已推荐</option>
							<option value="未推荐">未推荐</option>
						</select>
					</p>

					<p class="content-title">
						内容图片 <input type="file" name="filepath" multiple="true"
							width="20px" height="100px" id="filepath" />

					</p>
					<p class="content-title">
						发布者id <input type="text" name="releaser_id" width="20px"
							height="100px" id="releaser_id" />

					</p>
					<p class="content-title">内容:</p>
					<textarea type="text" class="content-input" name="shuoshuo-content"
						style=" width: 700px; height: 200px; margin-left: 13%;resize:none "></textarea>

				</div>
			</div>
			<!--数据-->
			<div class="basic-data clearfix">
				<div class="basic-data-title">
					<p>数据</p>
				</div>
				<div class="basic-data-cont">
					<div>
						<span>阅读量</span><input type="text" name="yuedu-count"
							value="${list.get(0).yuedu_count}" /> <span>点赞量</span><input
							type="text" name="dianzan-count"
							value="${list.get(0).dianzan_count}" />
					</div>
					<ul class="clearfix">
						<li class="basic-data-one"><span>收藏量</span> <span>${list.get(0).shoucang_count}</span>
							<a href="##" class="detail">详情</a></li>
						<li class="basic-data-two"><span>分享量</span> <span>${list.get(0).share_count}</span>
							<a href="##" class="detail">详情</a></li>
					</ul>
				</div>
			</div>
			<!--保存-->
			<div class="save">
				<button type="submit"
					style="height:35px;width: 95px ;background: #00C8E8; color:#ffffff">保存</button>
				<a href="##" class="content-cancel">取消</a>
			</div>

		</div>
	</form>
	<div id="all"></div>
	<!--上传图片-->
	<div class="picture-all"
		style="display: none;z-index: 20;position: fixed;top: 8%;left: 12%;background: #FFFFFF;">
		<div class="picture-header">
			<a href="##" class="pic-choose picture-send">上传图片</a> <a href="##"
				class="pic-choose">从图库选择</a> <a href="##" class="pic-exit">x</a>
		</div>
		<div class="picture-show" id="preview" style="position: relative;">
			<div id="img_li"
				style="width: 19%;height: 100px;position: absolute;left: 0;top:0">

			</div>
			<!--<img id="imghead" width=100 height=100 src='<%=request.getContextPath()%>/images/defaul.jpg'>-->
			<img src="../common/image/pic.png" class="img-show" id="img-show" />
			<a href="####" id="choose">点击选择图片<input type="file"
				style="opacity: 0;cursor:pointer;" /></a>
		</div>
		<hr />
		<span class="submit-all"> <a href="##"
			class="submit submit-yes" id="submit">确定</a> <a href="###"
			class="submit" id="cancel">取消</a>
		</span>

	</div>

	<script type="text/javascript">
		//文本编辑器
		$(function() {
			$("#txtDefaultHtmlArea").htmlarea();
		});
	
		$(function() {
	
			//单多略图切换
			$('.date_picker').date_input();
	
			$('#select_yn').click(function() {
				$('#delete_select').remove();
	
			})
			var pic = $('#picture input');
			var picc = $('.display-li');
	
			var width = picc.eq(0).outerWidth();
			$('#1').click(function() {
				picc.siblings().show();
				picc.eq(0).css("width", width + "px");
				$('#number').html("3:2");
			})
			$('#123').click(function() {
				picc.eq(0).show().siblings().hide();
				picc.eq(0).css("width", width + "px");
				$('#number').html("3:2");
			})
			$('#3').click(function() {
				picc.eq(0).show().siblings().hide();
	
				picc.eq(0).css("width", width * 1.5 + "px");
				picc.eq(0).eq(0).css("width", width * 1.5 + "px");
				$('#number').html("2:1");
			})
	
	
	
			//推荐日历点击显示
			$('#icon-rili').click(function() {
				$('#demo').show();
				$('#all').show();
	
			});
			$('#exit').click(function() {
				$('#demo').hide();
				$('#all').hide();
	
			});
	
			var now = new Date();
			var year = now.getFullYear();
			var month = now.getMonth() + 1;
			var date = now.getDate();
	
	
			var data = [
				{
					date : '2017/7/6',
					value : '荐'
				},
				{
					date : '2017/7/7',
					value : '荐'
				},
				{
					date : '2017/7/8',
					value : '荐'
				},
				{
					date : '2017/7/9',
					value : '荐'
				},
				{
					date : '2017/7/13',
					value : '荐'
				},
				{
					date : '2017/7/14',
					value : '荐'
				},
				{
					date : '2017/7/15',
					value : '荐'
				},
				{
					date : '2017/7/16',
					value : '荐'
				},
			];
	
			// inline
			var $ca = $('#one').calendar({
				// view: 'month',
				width : 260,
				height : 340,
				// startWeek: 0,
				// selectedRang: [new Date(), null],
				data : data,
				monthArray : [ 'January', 'February', 'March', 'April', 'May', 'June', 'July', 'August', 'September', 'October', 'November', 'December' ],
				date : new Date(2017, 6, 1),
				onSelected : function(view, date, data) {
					console.log('view:' + view)
					console.log('date:' + date)
					console.log('data:' + (data || '无'));
				},
				viewChange : function(view, y, m) {
					console.log(view, y, m)
	
				}
			});
	
	
			$(function() {
				$(".dot").html("荐");
				$('.dot').parent().css('color', '#00C8E8');
			})
	
	
	
		})
	
	
		var x = $('#clone').clone();
		//添加一组
		$(function() {
	
	
			$('.add-another').click(function() {
				x.clone(true).insertBefore($(this));
				console.log($('.image-show'))
			})
	
			//保存取消
			$(".save a").click(function() {
				$(this).addClass("save-clicked").siblings().removeClass("save-clicked");
			})
			$(".content-cancel").click(function() {
				window.location.reload();
			})
	
		})
	
	
		//上传图片
		function upload(obj, w, h, name) {
			var sum = obj.files.length;
			console.log(sum);
			str = "";
			if (sum <= 3) {
				for (var i = 0; i < sum; i++) {
					var url = window.URL.createObjectURL(obj.files[i]);
					var name = obj.files[i].name;
					//str+="<li style='width:"+w+"px;height:"+h+"px;float:left;margin-right:15px;background:url(\""+url+"\")no-repeat center center;background-size:cover;'></li>"  
					str += "<img id='jq' width='100%' height='100%' src=\"" + url + "\">"
	
				}
				console.log(11111)
				$("#img_li").append(str);
			} else {
				alert("最多选择3张图片！！！")
				document.getElementById("img-show").style.display = "block";
				document.getElementById("choose").style.display = "block";
				document.getElementById("choose").style.left = "0px";
				document.getElementById("choose").style.top = "0px";
			}
	
		}
	
		$(function() {
			//图库选择样式变化
			var flag = true;
			$(".pic-choose").click(function() {
				$(this).addClass('picture-send').siblings().removeClass('picture-send');
			})
			//确定取消样式变化
			$('.submit-all a').click(function() {
				$(this).addClass('submit-yes').siblings().removeClass('submit-yes');
			})
			$('#choose').click(function() {
				$(this).hide();
				$('#img-show').hide();
			})
			$(".pic-exit").click(function() {
				$('.picture-all').hide();
				$('#all').hide();
			})
			$("#cancel").click(function() {
				$('#preview li').remove();
				$('#img-show').show();
				$('#choose').show();
	
			});
	
	
			//重新上传图片
			var flag1 = true;
			$(".display-li").click(function() {
				a = $(this);
	
				str = '';
	
				$("#img_li img").remove();
				$('#img-show').show();
				$('#choose').show();
				$('#all').show();
				$('.picture-all').show();
				flag1 = true;
			})
	
			setInterval(function() {
				$('.add').click(function() {
					b = $(this);
	
					str = '';
					$("#img_li img").remove();
					$('#img-show').show();
					$('#choose').show();
					$('#all').show();
					$('.picture-all').show();
					flag1 = false;
				})
			}, 100)
	
			$('#submit').click(function() {
				if (flag1) {
					console.log(str);
					$('.picture-all').hide();
					$('#all').hide();
					a.children().remove();
					a.append(str);
				} else if (flag1 == false) {
					$('.picture-all').hide();
					$('#all').hide();
					b.children().remove();
					b.append(str);
				}
			});
	
			$("#choose input").change(function() {
				upload(this);
				console.log(1231321);
			})
	
	
		})
	
	
		/* function xhr2() {
			var xhr = new XMLHttpRequest(); //第一步    
			//定义表单变量    
			var file = document.getElementById('filepath').files;
			//console.log(file.length);    
			//新建一个FormData对象    
			var formData = new FormData(); //++++++++++    
			//追加文件数据    
			for (i = 0; i < file.length; i++) {
				formData.append("file[" + i + "]", file[i]); //++++++++++    
				alert(file[i]);
			}
			//formData.append("file", file[0]); //++++++++++    
		
			//post方式    
			xhr.open('POST', 'Upload_file', true); //第二步骤    
			alert("open")
			//发送请求    
			xhr.send(); //第三步骤    
			//ajax返回    
			xhr.onreadystatechange = function() { //第四步    
				if (xhr.readyState == 4 && xhr.status == 200) {
					alert(xhr.responseText);
				}
			};
			//设置超时时间    
			xhr.timeout = 100000;
			xhr.ontimeout = function(event) {
				alert('请求超时！');
			}
		} */
	</script>
</body>
</html>
