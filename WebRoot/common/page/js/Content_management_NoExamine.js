
$(function() {
	//全部1,头条2,日记3,图片4,秘密5,去处6,逛街7,
	$("#page-1").pagination($("#clear-fix-1"),{url:"/mServer/Handle_NoExamine_neirong?Tag=1",args:{Tag:1}});
	$("#page-2").pagination($("#clear-fix-2"),{url:"/mServer/Handle_NoExamine_neirong?Tag=2",args:{Tag:2}});
	$("#page-3").pagination($("#clear-fix-3"),{url:"/mServer/Handle_NoExamine_neirong?Tag=3",args:{Tag:3}});
	$("#page-4").pagination($("#clear-fix-4"),{url:"/mServer/Handle_NoExamine_neirong?Tag=4",args:{Tag:4}});
	$("#page-5").pagination($("#clear-fix-5"),{url:"/mServer/Handle_NoExamine_neirong?Tag=5",args:{Tag:5}});
	$("#page-6").pagination($("#clear-fix-6"),{url:"/mServer/Handle_NoExamine_neirong?Tag=6",args:{Tag:6}});
	$("#page-7").pagination($("#clear-fix-7"),{url:"/mServer/Handle_NoExamine_neirong?Tag=7",args:{Tag:7}});
});

(function(root,factory,plugin){
	factory(root.jQuery,plugin);
})(window,function($,plugin){
	$.fn[plugin] = function(container,params){
		var _this = this;
		console.log(params);
		_this.zPager({
			url:params.url,
			htmlBox: container,
			btnShow: false,
			dataRender: function(data){
				var lis = "";
				for ( var index in data) {
					lis += getLi(data[index],params.args.Tag);
				}
				console.log(data)
				container.empty();
				container.append(lis);
			}
		});
	}
},"pagination");

function getLi(data,Tag) {
	return '<li>' + '<p class="position-show-title">'
			+ '<i class="position-circle"></i> '
			+ '<img class="position-square" src="/mServer/FileDownLoadServlet?itemId='+data.id+'"/>'
			+ '<span class="position-title">'
			+ '<span>'
			+ data.title
			+ '</span>'
			+ '<span class="position-share">阅读量:'
			+ data.yuedu_count
			+ ' 分享:'
			+ data.share_count
			+ ' 收藏:'
			+ data.shoucang_count
			+ '</span>'
			+ '</span>'
			+ '</p>'
			+ '<p class="position-author">'
			+ '<img class="icon-author" src="'
			+ data.releaser_touxiang
			+ '" />'
			+ '<span class="position-title">'
			+ '<span>'
			+ data.releaser_name
			+ '</span>'
			+ '<span class="position-share">'
			+ data.time
			+ '</span>'
			+ '</span>'
			+ '</p>'
			+ '<p class="position-public">'
			+ '<span class="position-title position-p"> '
			+ '<span>'
			+ data.shenhe
			+ '</span>'
			+ '<span class="position-share">'
			+ data.tuijian_Tag
			+ '</span>'
			+ '</span> '
			+ '<span class="position-title position-p"> '
			+ '<span>点赞红包</span>'
			+ '<span class="position-share">剩余'
			+ data.dianzan_hongbao
			+ '元</span>'
			+ '</span> '
			+ '<span class="position-title position-p"> '
			+ '<span>分享红包</span>'
			+ '<span class="position-share">剩余'
			+ data.share_hongbao
			+ '元</span>'
			+ '</span> '
			+ '<span class="position-title position-p"> '
			+ '<span>'
			+ data.tuijian_message
			+ '</span>'
			+ '<span class="position-share">剩余'
			+ data.days
			+ '天</span>'
			+ '</span>'
			+ '</p>'
			+ '<p class="edit-exit">'
			+ '<a class="icon-edit icon-webpage" href="/mServer/page/content-detail.jsp?chaxun_id='
			+ data.id
			+ '&Tag='+Tag+'"></a>'
			+ '<a class="icon-edit icon-del" href="/mServer/Handle_NoExamine_neirong?del_id='
			+ data.id + '&Tag='+Tag+'"></a>' + '</p>' + '</li>';
}