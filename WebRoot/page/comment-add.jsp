<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%> <%@
taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>添加评论</title>
<link rel="stylesheet" type="text/css" href="../common/css/luzou.css" />
<script src="../common/lib/jquery-1.9.0.min.js" type="text/javascript"
	charset="utf-8"></script>
</head>
<body>
	<div class="content comment-add-header">
		<p class="comment-header">评论添加</p>
		<div class="comment-add-text">
			<p class="comment-add-obj">
				评论对象 <input type="text" placeholder="请输入您要评论的对象" />
			</p>
			<p class="comment-add-pepole">
				评论人 <input type="text" placeholder="请选择或者输入作者昵称" list="itlist" />
			</p>
			<datalist id="itlist">
				<option>item1</option>
				<option>item2</option>
			</datalist>
			</p>
			<p>
				<textarea name="" rows="" cols="" class="comment-text">请输入评论内容：</textarea>
			</p>
		</div>

		<input type="submit" value="添加" class="comment-submit" /> <input
			type="submit" value="取消" class="comment-submit" />
	</div>
</body>
</html>
