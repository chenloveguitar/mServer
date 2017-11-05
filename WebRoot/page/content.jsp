<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title></title>
<link rel="stylesheet" type="text/css"
	href="../common/iconfont/content/iconfont.css" />
<link rel="stylesheet" type="text/css" href="../common/css/luzou.css" />
<link rel="stylesheet" type="text/css" href="../common/css/calendar.css" />
<link rel="stylesheet" type="text/css"
	href="../common/css/calendar1.css" />
<script src="../common/lib/jquery-1.9.0.min.js" type="text/javascript"
	charset="utf-8"></script>
<!--<script type="text/javascript" src="http://www.js-css.cn/jscode/jquery.min.js"></script>-->
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

</head>
<body>
	<div class="content">
		<!--基本内容-->
		<div class="basic-content">
			<div class="basic-content-title">
				<p>基本内容</p>
			</div>
			<div class="basic-content-cont">
				<select class="basic-first">
					<option value="">分类</option>
				</select> <select>
					<option value="">状态</option>
				</select> <select>
					<option value="">发布日期</option>
				</select> <select name="" id="select_yn">
					<option id="delete_select" value="">是否评论</option>
					<option value="yes">是</option>
					<option value="no">否</option>
				</select>
				<p class="content-title">
					标题<input type="text" placeholder="这里是标题名称，是否有字数限制"
						class="content-input" maxlength="20" />
				</p>
				<p class="content-title">
					作者<input type="search" placeholder="请选择或者输入作者昵称"
						class="content-input input-second" list="itemlist" />
				</p>
				<datalist id="itemlist">
					<option>item1</option>
					<option>item2</option>
				</datalist>
			</div>
		</div>
		<!--数据-->
		<div class="basic-data clearfix">
			<div class="basic-data-title">
				<p>数据</p>
			</div>
			<div class="basic-data-cont">
				<div>
					<span>阅读量</span><input type="text" placeholder="256,256,125" /> <span>点赞量</span><input
						type="text" placeholder="26,256,125" />
				</div>
				<ul class="clearfix">
					<li class="basic-data-one"><span>收藏量</span> <span>56,256,125</span>

					</li>
					<li class="basic-data-two"><span>分享量</span> <span>43,256,125</span>

					</li>
				</ul>
			</div>
		</div>
		<!--推荐-->
		<div class="recommend">
			<div class="recommend-title">
				<p>推荐</p>
			</div>
			<div class="recommend-cont clearfix">

				<div id="demo">
					<div class="exit" id='exit'>X</div>
					<div id="one"></div>
				</div>
				<p class="p1 remain-time">
					剩余推荐时间<span class="recommend-cont-day ll">15</span>天<span
						class="recommend-cont-hour ll">20</span>小时<span
						class="iconfont icon-rili ll" id="icon-rili"></span>
				</p>
				<p class="p2 remain-time">
					总推荐时间<span class="ll">30</span>天<span
						class="iconfont icon-icon-test ll"></span>
				</p>
				<p class="recommend-data" style="margin-top: 40px;">
					推荐日期 <input type="data" value="2017-05-17" class="icon date_picker" /><span
						class="to">至</span><input type="text" placeholder="请选择日期"
						class="choose icon date_picker" /><span class="recommend-people">
						推荐人</span><select name="" class="contact">
						<option value="" label="请选择推荐人"></option>
					</select>
				<div class="redPacket">
					<p class="overplus">
						剩余红包<span class="money">1,452,00</span><span class="price">元</span><span
							class="money">146</span><span class="price">个</span>
					</p>
					<p class="recommend-data sum">
						红包金额 <input type="text" placeholder="2,523.00" class="price-input" /><span
							class="price">元</span>个数 <input type="text" placeholder="158"
							class="price-input" /><span class="price">个</span><span
							class="recommend-people send">发放人</span><select name="" id=""
							class="contact">
							<option value="" label="请选择发放人"></option>
						</select>
				</div>
				<p class="reward">
					打赏量<input type="text" placeholder="43,256.00" class="reward-input" /><span
						class="price">元</span>
				</p>
			</div>
		</div>
		<!--样式-->
		<div class="pattem">
			<div class="pattem-title">
				<p>样式</p>
			</div>
			<div class="pattem-cont">
				<div class="form-select" id="picture">
					<lable> <input type="radio" name="se" checked="checked"
						id="1" />多缩略图</lable>
					<lable> <input type="radio" name="se" id="123" />单缩略图</lable>
					<lable> <input type="radio" name="se" id="3" />大图</lable>
				</div>
				<div class="display" id="display">
					<ul style="overflow: hidden;height: 95px;">

						<li class="" id="spe"><a href="##" class="jiahao"></a>
							<p>添加图片</p></li>


					</ul>
					<p class="suggest">
						图片建议尺寸比例 &nbsp;&nbsp;<span id="number">3:2</span>
					</p>
				</div>
			</div>
		</div>
		<!--正文编辑-->
		<div class="text-edit">
			<div class="text-edit-title">
				<p>正文编辑</p>
			</div>
			<div class="text-edit-cont">
				<ul style="height: 120px;" class="clearfix" id="clone">
					<li class="add add-two image-show"><a href="###"></a>
						<p>添加图片</p></li>
					<li class="text"><textarea name="text0" rows="" cols=""
							class="textarea">这里是文字编辑框，可以输入你想要的文本</textarea></li>
				</ul>
				<div class="add-another">

					<span style="position: relative;" class="span-two"><i
						style="position: absolute;left: -29px;" href="##" class="span-one"></i>添加一组</span>

				</div>

				<!--文本编辑器-->
				<div class="nav">
					<textarea id="txtDefaultHtmlArea" cols="50" rows="15"
						style="width:100%;height: 200px;"></textarea>
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

	<!--特殊处理-->
	<div id="picture-all1" class="picture-all"
		style="display: none;z-index: 20;position: fixed;top: 8%;left: 12%;background: #FFFFFF;">
		<div class="picture-header">
			<a href="##" class="pic-choose picture-send">上传图片</a> <a href="##"
				class="pic-choose">从图库选择</a> <a href="##" class="pic-exit">x</a>
		</div>
		<div class="picture-show" id="preview" style="position: relative;">
			<div id="img_li1"
				style="width: 100%;height: 100px;position: absolute;left: 0;top:0">
				<label id="upp-load" style="display: none;">
					<div>+</div> <input id="photo-input" type="file"
					style="opacity: 0;cursor:pointer;" onchange="upload(this)" />
				</label>
			</div>
			<!--<img id="imghead" width=100 height=100 src='<%=request.getContextPath()%>/images/defaul.jpg'>-->
			<img src="../common/image/pic.png" class="img-show" id="img-show1" />
			<a href="####" id="choose1">点击选择图片<input id="photo-input"
				type="file" style="opacity: 0;cursor:pointer;"
				onchange="upload(this)" /></a>
		</div>
		<hr />
		<span class="submit-all"> <a href="##"
			class="submit submit-yes" id="submit1">确定</a> <a href="###"
			class="submit" id="cancel">取消</a>
		</span>

	</div>
	<input type="file" id="img-img" data-url='' data-flag='b'
		style="display: none;" />
	<script type="text/javascript">
		//文本编辑器
		var aa = '';
		$(function() {
			$("#txtDefaultHtmlArea").htmlarea();
	
			$(".save-clicked").click(function() {
				var a = $("#txtDefaultHtmlArea").val();
				console.log("txt:" + a);
			})
		});
	
		$(function() {
	
			//单多略图切换
			$('.date_picker').date_input();
	
			$('#select_yn').click(function() {
				$('#delete_select').remove();
	
			})
			var pic = $('#picture input');
	
	
			var width = $('#spe').outerWidth();
			$('#1').click(function() {
				var picc = $('.display-li');
				picc.siblings().show();
				picc.eq(0).css("width", width + "px");
				$('#number').html("3:2");
			})
			$('#123').click(function() {
				var picc = $('.display-li');
				picc.eq(0).show().siblings().hide();
				picc.eq(0).css("width", width + "px");
				$('#number').html("3:2");
			})
			$('#3').click(function() {
				var picc = $('.display-li');
				picc.eq(0).show().siblings().hide();
				picc.eq(0).css("width", width * 1.5 + "px");
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
	
			var num = 0;
			$('.add-another').click(function() {
				num++;
				var a = x.clone(true).insertBefore($(this));
				a.attr("id", 'clone' + num);
				a.find("textarea").attr("name", "text" + num);
			})
	
			//保存取消
			$(".save a").click(function() {
				$(this).addClass("save-clicked").siblings().removeClass("save-clicked");
			})
			$(".content-cancel").click(function() {
				window.location.reload();
			})
	
		})
	
	
		//最下方文本编辑上传图片
		$("#img-img").change(function() {
			$('#img-img').attr('data-flag', 'a');
			var file = this.files[0];
			if (window.FileReader) {
				var reader = new FileReader();
				reader.readAsDataURL(file);
				//监听文件读取结束后事件    
				reader.onloadend = function(e) {
					$("#img-img").attr("data-url", e.target.result);
					console.log(e.target.result);
				};
			}
		});
		//上传图片
		var str2 = 0;
		var n = 0;
		var m = 0;
		function upload(obj) {
			var sum = obj.files.length;
			console.log(obj);
			var file = obj.files[0];
			str = "";
			if (window.FileReader) {
				var reade = new FileReader();
				reade.readAsDataURL(file);
				//监听文件读取结束后事件    
				reade.onloadend = function(e) {
					n++;
					m++;
					str = "<img width='100%' height='100%' id='image" + m + "' src=" + e.target.result + ">";
					str1 = "";
					str1 += "<li class='display-li' data-id='image" + m + "'><img  width='100%' height='100%' id='image" + m + "' src=\"" + e.target.result + "\"></li>"
					//		               console.log(str);
					$("#img_li").append(str);
					$("#upp-load").before(str1);
					str2 += str1;
	
				};
			}
	
		}
	
	
		$('#spe').click(function() {
			str2 = '';
	
			$("#img_li1 li").remove();
			$('#img-show1').show();
			$('#choose1').show();
			$('#all').show();
			$('#picture-all1').show();
			$("#cancel").live("click", function() {
				$("#img_li1 .display-li").remove();
				$('#img-show1').show();
				$('#choose1').show();
				$("#img_li1").show();
	
			});
		})
		$('#choose1').click(function() {
			$('#choose1').hide();
			$('#img-show1').hide();
			$('#upp-load').show();
		})
	
	
		$('#submit1').click(function() {
			$('#picture-all1').hide();
			$('#all').hide();
			$('#upp-load').hide();
			$('#spe').after(str2);
		})
	
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
				$("#img_li img").remove();
				$('#img-show').show();
				$('#choose').show();
	
			});
	
	
			//重新上传图片
			var flag1 = true;
			$(".display-li").live("click", function() {
	
				a = $(this);
	
				str = '';
	
				$("#img_li img").remove();
				$('#img-show').show();
				$('#choose').show();
				$('#all').show();
				$('#picture-all').show();
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
					$('#picture-all').show();
					flag1 = false;
				})
			}, 100)
			var numb = 0;
			$('#submit').click(function() {
				if (flag1) {
					console.log(str);
					$('#picture-all').hide();
					$('#all').hide();
					a.children().remove();
					a.append(str);
					a.children().attr('id', a.attr('data-id'))
	
				} else if (flag1 == false) {
					$('#picture-all').hide();
					$('#all').hide();
					b.children().remove();
					b.append(str);
					b.find("img").attr("id", "img" + numb);
					numb++;
				}
			});
	
			$("#choose input").change(function() {
				upload(this);
				console.log(1231321);
			})
	
		})
		$(".save-clicked").on("click", function() {
			$.ajax({
				url : "/mServer/Upload_file2",
				type : "POST",
				traditional : true,
				data : {
					"title" : $("#title").val(),
					"user_name" : $("#user_name").val(),
					"sort_rec" : $("#sort").val(),
					"picture" : $("#img0").attr("src"),
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
