<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title></title>
<link rel="stylesheet" href="../common/bootstrap/bootstrap.css">
<link rel="stylesheet" href="../common/bootstrap/bootstrap-select.css">
<link rel="stylesheet" type="text/css"
	href="../common/iconfont/content/iconfont.css" />
<link rel="stylesheet" type="text/css" href="../common/css/luzou.css" />
<link rel="stylesheet" type="text/css" href="../common/css/calendar.css" />
<link rel="stylesheet" type="text/css"
	href="../common/css/calendar1.css" />
<script src="../common/lib/jquery-1.9.0.min.js" type="text/javascript"
	charset="utf-8"></script>
	<script type="text/javascript" src="../common/js/ajaxfileupload.js"></script>
<script type="text/javascript" src="http://www.js-css.cn/jscode/jquery.min.js"></script>
<script src="../common/js/jquery.min.js" type="text/javascript"
	charset="utf-8"></script>
<script src="../common/js/jquery.date_input.pack.js"
	type="text/javascript" charset="utf-8"></script>
<script src="../common/js/calendar.js" type="text/javascript"
	charset="utf-8"></script>
<script type="text/javascript"
	src="../common/lib/scripts/jquery-ui-1.7.2.custom.min.js"></script>
<link rel="Stylesheet" type="text/css"
	href="../common/lib/style/jqueryui/ui-lightness/jquery-ui-1.7.2.custom.css" />
<script type="text/javascript"
	src="../common/lib/scripts/jHtmlArea-0.8.min.js"></script>
<link rel="Stylesheet" type="text/css"
	href="../common/lib/style/jHtmlArea.css" />
<!-- bootstrap 下拉搜索框 -->
<script src="../common/lib/jquery-1.9.1.js" type="text/javascript" charset="utf-8"></script>
<script type="text/javascript" src="../common/bootstrap/bootstrap.js"></script>
<script src="../common/bootstrap/bootstrap-select.js"></script>
<script src="../common/bootstrap/i18n/defaults-zh_CN.js"></script>
<style type="text/css">
	.manage-list {
	    overflow: auto;
  	 	margin: 30px;
    	border: 1px dashed #ECECEC;
    	height: 430px;
	}
	.manage-list ul li{
		margin: 15px 5px;
	}
	.manage-list .images{
		border: 1px solid #B3B3B3;
	}
	
	.picture-show{
		border:0;
		margin:0;
		height:420px;
	}
	.select{
		background: url("../common/image/images/button_marquee_selected.png") !important;
	}
	
	.manage-delete{
		color: #C7C7C7;
	    font-size: large;
	    font-weight: bold;
	    cursor: pointer;
	    position: absolute;
	    top:0px;
	    right: 5px;
	}
	.jHtmlArea .ToolBar .h1,.h2,.h3,.h3,.h4,.h5,.h6{
		margin:0px;
	}
</style>
</head>
<body>
	<div class="content">
		<!--基本内容-->
		<div class="basic-content">
			<div class="basic-content-title">
				<p>基本内容</p>
			</div>
			<div class="basic-content-cont" style="padding-bottom: 50px;">
				<select class="basic-first" id="select">
					<option value="今日头条">今日头条</option>
					<option value="酒城日记">酒城日记</option>
					<option value="发现秘密">发现秘密</option>
					<option value="有去处">有去处</option>
					<option value="商品">商品</option>
					<option value="服务">服务</option>
				</select>
				<select class="basic-second" id="status">
					<option value="状态">状态</option>
					<option value="待审核">待审核</option>
					<option value="已发布">已发布</option>
					<option value="已下架">已下架</option>
				</select>
			
				<input type="text" placeholder="发布日期" class="basic-input date-input" id="publish_date">
				<p class="content-title">
					标题 <input type="text" placeholder="请输入标题" class="content-input"
						maxlength="20" id="title" />
				</p>
				<p class="content-title">
						作者<span style="margin-left:25px;"><select class="selectpicker" data-live-search="true" title="请选择或输入作者昵称"></select></span>
				</p>
				<div>
					<div class="service" style="display: none;">
						<p class="content-title">
							价格 <input type="text" placeholder="请输入价格" class="content-input"
								id="price_fuwu" />
						</p>
						<p class="content-title">
							电话 <input type="text" placeholder="请输入电话" class="content-input"
								id="phone_fuwu" />
						</p>
					</div>
					<div class="gowhere" style="display: none;">
						<p class="content-title">
							店铺名 <input type="text" placeholder="请输入店铺名称"
								class="content-input" id="dianpu_id" />
						</p>
						<p class="content-title">
							地址 <input type="text" placeholder="请输入地址" class="content-input"
								id="address" />
						</p>
						<p class="content-title">
							电话 <input type="text" placeholder="请输入电话" class="content-input"
								id="phone" />
						</p>
					</div>
					<div class="goods" style="display: none;">
						<p class="content-title">
							价格 <input type="text" placeholder="请输入价格" class="content-input"
								id="price_commodity" />
						</p>
						<p class="content-title">
							数量 <input type="text" placeholder="请输入数量" class="content-input"
								id="shuliang" />
						</p>
						<p class="content-title">
							运费 <input type="text" placeholder="请输入运费" class="content-input"
								id="freight" />
						</p>
						<p class="content-title">
							电话 <input type="text" placeholder="请输入电话" class="content-input"
								id="phone_commodity" />
						</p>
					</div>
				</div>
			</div>
		</div>
		<!--数据-->
		<div class="basic-data clearfix">
			<div class="basic-data-title">
				<p>数据</p>
			</div>
			<div class="basic-data-cont">
				<div>
					<span>阅读量</span><input type="text" placeholder="0" id="yuedu_count" />
					<span>点赞量</span><input type="text" placeholder="0"
						id="dianzan_count" />
				</div>
				<ul class="clearfix">
					<li class="basic-data-one"><span>收藏量</span> <span>0</span></li>
					<li class="basic-data-two"><span>分享量</span> <span>0</span></li>
				</ul>
			</div>
		</div>
		<!--推荐-->
		<div class="recommend">
			<div class="recommend-title">
				<p>推荐</p>
			</div>
			<div class="recommend-cont clearfix">
				<p class="p2 remain-time">
					<span>总推荐时间</span>
					<span class="ll">1</span>天
					<span class="iconfont icon-icon-test ll"></span>
				</p>
				<p class="recommend-data " style="margin-top: 40px;">
					<span>推荐日期 </span>
					<input type="text" value="" class="icon date_picker" />
					<span class="to">至</span>
					<input type="text" placeholder="请选择日期" class="choose icon date_picker" />
					<span class="recommend-people">推荐人</span>
					<span><select class="selectpicker" data-live-search="true" title="请选择推荐人"></select></span>
				<div class="redPacket">
					<p class="overplus">
						<span>剩余红包</span>
						<span class="money">0,00</span>
						<span class="price">元</span>
						<span class="money">0</span>
						<span class="price">个</span>
					</p>
					<p class="recommend-data sum">
						<span>点赞红包金额</span>
						<input type="text" placeholder="0.00" class="price-input" />
						<span class="price">元</span>个数
						<input type="text" placeholder="0" class="price-input" />
						<span class="price">个</span>
					</p>
					<p class="recommend-data sum">
						<span>红包金额</span> 
						<input type="text" placeholder="0.00" class="price-input" />
						<span class="price">元</span>个数 
						<input type="text" placeholder="0" class="price-input" />
						<span class="price">个</span>
					</p>
					<p class="reward">
						<span>打赏量</span>
						<input type="text" placeholder="0.00" class="reward-input" />
						<span class="price">元</span>
					</p>
				</div>
			</div>
		</div>
		<!--样式-->
		<div class="pattem">
			<div class="pattem-title">
				<p>样式</p>
			</div>
			<div class="pattem-cont">
				<div class="form-select" id="picture">
					<label><input type="radio" name="se" checked="checked" value="2" />多缩略图</label>
					<label><input type="radio" name="se" value="1" />单缩略图</label>
					<label><input type="radio" name="se" value="3" />大图</label>
				</div>
			</div>
		</div>
		<!--正文编辑-->
		<div class="text-edit">
			<div class="text-edit-title">
				<p>正文编辑</p>
			</div>
			<div class="text-edit-cont">
				<ul style="height: 120px;" class="clearfix clone-ul" id="clone0">
					<li class="add add-two image-show"><a href="###"></a>
						<p>添加图片</p>
						<p id="imageCount"></p>
					</li>
					<li class="text"><textarea id="textarea" name="text0" rows="" cols=""
							class="textarea"></textarea></li>
				</ul>
<!-- 				<div class="add-another"> -->

<!-- 					<span style="position: relative;" class="span-two"><i -->
<!-- 						style="position: absolute;left: -29px;" href="##" class="span-one"></i>添加一组</span> -->

<!-- 				</div> -->

				<!--文本编辑器-->
				<div class="nav">
					<textarea id="txtDefaultHtmlArea" cols="50" rows="15"
						style="width:100%;height: 500px;"></textarea>
				</div>
				<!--<textarea name="" rows="" cols="" class="textarea-two">这里是图文编辑器，听说有插件可以自动生成</textarea>-->
			</div>
		</div>
		<!--保存-->
		<div class="save">
			<button type="button" class="save-s save-clicked">保存</button>
			<!--<a href="##" class="save-s save-clicked">保存</a>-->
			<a href="##" class="content-cancel">取消</a>

		</div>


	</div>
	<div id="all"></div>
	<!--上传图片-->
	<div id="picture-all" class="picture-all"
		style="display: none;z-index: 20;position: fixed;top: 8%;left: 12%;background: #FFFFFF;">
		<div class="picture-header">
			<a href="javascript:uploadFile();" class="pic-choose picture-send">上传图片</a> 
			<a href="javascript:selectToDepot();" class="pic-choose">从图库选择</a> <a href="javascript:cancelSelected();" class="pic-exit">x</a>
		</div>
		<div id="image_content">
			<div class="manage-list" style="height:500px;overflow: auto;">
				<ul class="clearfix manage-image" style="display: none;" id="clear-fix">
	
				</ul>
				<ul class="clearfix manage-image" id="manage-list-ul">
	
				</ul>
				<div class="picture-show" id="preview" style="position: relative;">
				</div>
			</div>
		</div>
		<hr/>
		<span class="submit-all"> 
			<a href="javascript:confirmSelected();" class="submit submit-yes" id="submit">确定</a> 
			<a href="javascript:cancelSelected();" class="submit" id="cancel">取消</a>
		</span>
	</div>
	<input type="file" id="img-img" data-url='' data-flag='b'
		style="display: none;" />
	<script type="text/javascript">
		
		var $jq = jQuery.noConflict(true);
		var deleteds = [];
		//文本编辑器
		var aa = '';
		var x = $('#clone0').clone();
		$(function() {
			//加载用户列表
			getUserList();	
			initUploadPanel();
			eventBinding();
			initWidget();
			$(".save-clicked").on("click", function() {
				var selectedNum = $("#clear-fix .select").parents("li").length;
				var uploadNum = document.getElementById("file").files.length;
				if(selectedNum && uploadNum){
					$.ajax({
						url : "/mServer/Upload_file2",
						type : "POST",
						data : {
							"select-val" : $("#select").val(),
							"price_fuwu" : $("#price_fuwu").val(),
							"phone_fuwu" : $("#phone_fuwu").val(),
							"phone_commodity" : $("#phone_commodity").val(),
							"dianpu_id" : $("#dianpu_id").val(),
							"address" : $("#address").val(),
							"phone" : $("#phone").val(),
							"price_commodity" : $("#price_commodity").val(),
							"shuliang" : $("#shuliang").val(),
							"freight" : $("#freight").val(),
							"title" : $("#title").val(),
							"releaser_name" : $("#releaser_name").val(),
							"yuedu_count" : $("#yuedu_count").val(),
							"dianzan_count" : $("#dianzan_count").val(),
							"releaser_id" : $("#releaser_id").val(),
							"muban_Tag" : $("input[type='radio']:checked").val(),
	//	 					"picture" : arr,
							"content1" : $("#textarea").val(),
							"content2" : $("#txtDefaultHtmlArea").val()
							},
							dataType : "json",
							success : function(data) {
								if(data.code === "0000"){
									var id = data.data;
									var success = imageRelAndUpload(id);
									if(success){
										alert("发布成功");
									}
	//	 							window.location.href = "page/Content_management_Release.jsp";
								}else{
									alert("发布失败");
								}
								
							},
							error : function() {
								alert("发布失败");
							}
						});
				}else{
					alert("请上传或从图库中选择图片！");
				}
			});
		});
	//图片关联和上传
	function imageRelAndUpload(itemId){
		var success = false;
		//从图库中选择的
		var data_ids = "";
		var pid = null;
		//获取关联的图片集合
		$.each($("#clear-fix .select").parents("li"),function(){
			data_ids += $(this).attr("data-id") + ",";
		});
		//获取需要上传的图片集合
		if(data_ids){
			//文件关联
			$.ajax({
				url:'${pageContext.request.contextPath}/CommonServlet',
				type:'post',
				dataType:'json',
				async:false,
				data:{
					type:'imageDataRel',
					itemId:itemId,
					id:data_ids
				},
				success:function(data){
					if(data.code == "0000" ){//&& success
// 						alert("文件关联成功！");
					success = true;
					}
				},
				error:function(data){
						alert("文件关联失败！");
				}
			});
		}
		var sum = document.getElementById("file").files.length;
		//文件上传
		if(sum > 0){
			$.ajaxFileUpload({
	            url: '${pageContext.request.contextPath}/FileUploadServlet?parentId='+pid+"&deleteds="+deleteds+"&itemId="+itemId, //用于文件上传的服务器端请求地址
	            secureuri: false, //是否需要安全协议，一般设置为false
	            fileElementId: 'file', //文件上传域的ID
	            async:false,
	            dataType: 'json', //返回值类型 一般设置为json
	            success: function (data, status){
	            	var results = data.data;
	            	if(data.code === "0000"){
	            		success = success && true;
// 						alert("文件上传成功！");
	            	}else{
	            		alert("文件上传失败!");
	            	}
	            },error: function (data, status, e){
	           		alert("文件上传失败!");
	            }
	        });
		}
		
		if(data_ids && sum == 0){
			alert("请上传或从图库中选择图片！");
			return false;
		}
		return success;
	}
	//获取用户列表
	function getUserList() {
		$jq.ajax({
			url : "${pageContext.request.contextPath}/CommonServlet",
			type : "post",
			dataType : "json",
			data : {
				type:'getUsers'
			},
			success : function(data) {
				var result = data.data.result;
				$jq.each(result, function(i) {
					$jq('.selectpicker').append("<option value=" + result[i].admin_xinxi_id + ">"
											+ result[i].name
											+ "</option>");
				});
				$jq('.selectpicker').selectpicker({
					  size: 4,
					  
				});
				$jq('.selectpicker').selectpicker('refresh');
			},
			error : function(data) {
				alert("查询作者失败" + data);
			}
		});
	}
	
	function getUploadImagesHtml(url,name){
		return '<li id="pic">'+
			'<div class="images" style="cursor: pointer;">'+
				'<a href="javascript:void(0);">'+
				'<img src="'+url+'"/>'+
				'<span class="manage-delete" onclick="javascript:deleteImage(this)" name='+name+'>X</span>'+
				'</a>'+
			'</div>'+
		'</li>';
	}
	
	function deleteImage(obj){
		var deleted = $(obj).attr("name");
		deleteds.push(deleted);
		$(obj).parent().parent().parent().remove();
		var count = $("#manage-list-ul").find("li").length;
		if(count == 0){
			$("#img-show").show();	
			$("#choose").show();
		}
	}
	//添加图片
	function addImages(obj){
	    var sum = obj.files.length;
	    if(sum > 0){
	    	for(var i=0;i<sum;i++){
	   	        var url = window.URL.createObjectURL(obj.files[i]);
	   	        var name = obj.files[i].name;
	   	        var li = $(getUploadImagesHtml(url,name));
	   		    $(li).appendTo($("#manage-list-ul"));
   	    	}
	 	}
    }
	
	function confirmSelected(){
		var depotImageCount = $("#clear-fix .select").length;
		var uploadImageCount = $("#manage-list-ul li").length;
		if(depotImageCount == 0 && uploadImageCount == 0){
			alert("请上传或选择图片后点击确认!");
			return;
		}
		//这里只做将选择的图片显示在编辑框中,不做后台请求.在提交的时候 在做后台请求
		closePanel();
		//设置数字显示
		$(".add.add-two.image-show #imageCount").html("已添加"+((depotImageCount || 0) + (uploadImageCount || 0))+"张图片");
	}
	
	function cancelSelected(){
		closePanel();
	}
	
	function closePanel(){
		//图片上传/选择弹出框
		$(".picture-all").hide();
		//页面遮罩
		$("#all").hide();
	}
	//上传图片
	function uploadFile(){
		$("#clear-fix").hide();
		$("#preview").show();
		$("#manage-list-ul").show();	
	}
	
	//从仓库选择
	function selectToDepot(){
		$("#clear-fix").show();
		$("#preview").hide();
		$("#manage-list-ul").hide();	
	}
	
	function initUploadPanel(){
		$("#preview").append(getUploadHtml());
// 		$("#clear-fix").append(getImagesHtml());
		getImages();
	}
	
	function getImages(){
		$.ajax({
			url:'${pageContext.request.contextPath}/CommonServlet',
			type:'post',
			dataType:'json',
			data:{
				type:'getImages'
			},
			success:function(data){
				var imagesHtml = getImagesHtml();
				if(data.code === "0000"){
					var results = data.data;
					if(results.length > 0){
						console.log(results);
						for(var i in results){
							$("#clear-fix").append(getImagesHtml(results[i].id,results[i].absolutePath));
						}
					}else{
						alert("图片库中无图片显示!");
					}
				}else{
					alert("获取图片库图片失败!");
				}
			},
			error:function(data){
				alert("获取图片库图片失败!");
			}
		});
	}
	
	function getUploadHtml(){
		return 	'<div id="img_li" style="position: absolute;left: 0;top:0;display:none;"></div>'+
				'<img src="../common/image/pic.png" class="img-show" id="img-show" />'+
				'<a href="#" id="choose">点击选择图片'+
					'<input type="file" name="file" id="file" multiple="multiple" style="opacity: 0;cursor:pointer;"/>'+
				'</a>';
	}
	
	function getImagesHtml(id,absolutePath){
		return '<li id="pic" data-id="'+id+'">'+
			'<div class="images" style="cursor: pointer;" onclick=selectImage("'+id+'",this)>'+
				'<a href="javascript:void(0);">'+
				'<img src="${pageContext.request.contextPath}/FileDownLoadServlet?absolutePath=' + absolutePath + '"/>'+
				'<span class="manage-choose" style="top:0px;right:0px;"></span>'+
				'</a>'+
			'</div>'+
		'</li>';
	}
	
	function selectImage(id,obj){
		$(obj).find("span").toggleClass("select");
	}
	
	//元素事件绑定
	function eventBinding(){
		//添加图片元素事件绑定
		$('.add.add-two.image-show').click(function() {
			var count = $("#manage-list-ul li").length;
			if(count == 0){
				$("#choose").show();
				$('#img-show').show();
			}else{
				$("#choose").hide();
				$('#img-show').hide();
			}
			$("#img_li img").remove();
			$('#all').show();
			$('#picture-all').show();
			$(".save-clicked").click(function() {
				var a = $("#txtDefaultHtmlArea").val();
				console.log("txt:" + a);
			})
			//保存取消
			$(".save a").click(function() {
				$(this).addClass("save-clicked").siblings().removeClass("save-clicked");
			});
			
			
			$(".content-cancel").click(function() {
				window.location.reload();
			});
		
			$('#choose1').click(function() {
				$('#choose1').hide();
				$('#img-show1').hide();
				$('#upp-load').show();
			})
		
			$('#submit1').click(function() {
				$('#picture-all1').hide();
				$('#all').hide();
				$('#upp-load').hide();
			})
		});
		
		//点击上传图片时显示弹出框和添加
		$("#choose input").change(function() {
			addImages(this);
			$("#manage-list-ul").show();
		});
		//下拉框切换
		$("#select").change(function() {
			var value = $(this).val();
			if (value == "今日头条" || value == "酒城日记" || value == "发现秘密") {
				$(".gowhere").hide();
				$(".goods").hide();
				$(".service").hide();
			}
			if (value == "有去处") {
				$(".gowhere").show().siblings().hide();
			}
			if (value == "商品") {
				$(".goods").show().siblings().hide();
			}
			if (value == "服务") {
				$(".service").show().siblings().hide();
			}
		});
		//图库选择样式变化
		$(".pic-choose").click(function() {
			$(this).addClass('picture-send').siblings().removeClass('picture-send');
		});
		
		$('#choose').click(function() {
			$(this).hide();
			$('#img-show').hide();
		})
		$("#cancel").click(function() {
			$("#img_li img").remove();
			$('#img-show').show();
			$('#choose').show();
		});
		
		$(".iconfont.icon-icon-test.ll").click(function(){
			var oldDate = new Date($(".choose.icon.date_picker").val());
			var newDate = oldDate.addDay(1);
			$(".choose.icon.date_picker").val(newDate.format("yyyy-MM-dd"));
			var beginDate = new Date($(".icon.date_picker").val());
			var endDate = new Date($(".choose.icon.date_picker").val());
			//日期相减
			var dateCount = beginDate.subtractDay(endDate);
			$(".p2.remain-time span.ll:first").html(dateCount + 1);
		});
	}
	
	
	
	//初始化控件
	function initWidget(){
		$("#txtDefaultHtmlArea").htmlarea();
		//选择日期
		$("#publish_date").val(new Date().format("yyyy-MM-dd"));
		$('#publish_date').date_input();
		//日期选择
		$(".date_picker").val(new Date().format("yyyy-MM-dd"));
		$('.date_picker').date_input();
	}
	
	/**  
	 * 日期格式化（原型扩展或重载）  
	 * 格式 YYYY/yyyy/ 表示年份  
	 * MM/M 月份  
	 * dd/DD/d/D 日期  
	 * @param {formatStr} 格式模版  
	 * @type string  
	 * @returns 日期字符串  
	 */  
	Date.prototype.format = function(formatStr){   
	        var str = formatStr;   
	        var Week = ['日','一','二','三','四','五','六'];   
	        str=str.replace(/yyyy|YYYY/,this.getFullYear());  
	        str=str.replace(/MM/,(this.getMonth()+1)>9?(this.getMonth()+1).toString():'0' + (this.getMonth()+1));   
	        str=str.replace(/dd|DD/,this.getDate()>9?this.getDate().toString():'0' + this.getDate());   
	       return str;   
	} 
	 
	Date.prototype.addDay = function(addDay){
		var time = this.getTime();
		var date = new Date(time + addDay * 24 * 60 * 60 * 1000)
		return date;
	}
	
	Date.prototype.subtractDay = function(date){
		var beginDateTime = this.getTime();
		var endDateTime = date.getTime();
		var diff = endDateTime - beginDateTime;
		console.log(this.getTime())
		console.log(date.getTime())
		var result = diff / (24 * 60 * 60 * 1000);
		return result;
	}
	</script>
</body>
</html>
