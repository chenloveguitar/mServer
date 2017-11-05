<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>添加用户</title>
<link rel="stylesheet" type="text/css" href="../common/css/luzou.css" />
<script src="../common/lib/jquery-1.9.0.min.js" type="text/javascript"
	charset="utf-8"></script>
</head>
<body>
	<div class="user-add">
		<div class="user-add-content">
			<h2>添加管理员:</h2>
			<form action="../add_admin" method="post" id="uploadForm"
				>
				<p>
					用户分类： <select name="select">
						<option value="1">超级管理员</option>
						<option value="2">编辑</option>
						<option value="3">财务</option>
					</select>
				</p>
				<p>
					账号： <input type="text" placeholder="请输入账号" name="zhanghao" />
				</p>
				<p>
					名字： <input type="text" placeholder="请输入名字" name="user_name" />
				</p>
				<p>
					密码： <input type="password" placeholder="请输入密码" name="password" />
				</p>
				<p>
					电话： <input type="number" placeholder="请输入电话" name="phone" />
				</p>
	
				<input type="submit" value="保存" class="advert-add-save user-addh" />
			</form>
		</div>
	</div>

</body>
</html>
