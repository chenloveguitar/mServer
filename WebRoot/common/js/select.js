$(function(){
	//全选
	var fla=true;
	$(".select-all").click(function(){
		if(fla){
			$(".position-circle").addClass("list-click");
			fla=false;
		}else{
			$(".position-circle").removeClass("list-click");
			fla=true;
		}
		
		$(".position-delete").click(function(){
			$(".position-show ul li").remove();
			$(this).css("color","#00C8E8");
		})
	})
	
	$(".position-circle").click(function(){
		if($(this).hasClass("list-click")){
			$(this).removeClass('list-click');
		}else{
			$(this).addClass('list-click');
			$(this).parent().parent().find(".edit-exit").find(".icon-del").click(function(){
				$(this).parent().parent().remove();
			})
		}	
	})
	//删除
	$(".position-delete").click(function(){
			$(".list-click").parent().parent().remove();
			$(this).css("color","#00C8E8");
	})
})