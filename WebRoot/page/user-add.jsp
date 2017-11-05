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
			<h2>添加用户:</h2>
			<form action="../add_user" method="post" id="uploadForm"
				enctype="multipart/form-data">
				<p>
					用户分类： <select name="select">
						<option value="3">普通用户</option>
						<option value="1">名人</option>
						<option value="4">团体企业</option>
						<option value="2">编辑组</option>
					</select>
				</p>
				<p>
					账号： <input type="text" placeholder="请输入账号" name="zhanghao" />
				</p>
				<p>
					用户名： <input type="text" placeholder="请输入用户名" name="user_name" />
				</p>
				<p>
					密码： <input type="password" placeholder="请输入密码" name="password" />
				</p>

				<p>
					性别： <input type="radio" name="sex" id="" value="男" />男 <input
						type="radio" name="sex" value="女" />女
				</p>
				<p>
					签名： <input type="text" name="qianming" id="" value=""
						class="autograph" />
				</p>

				<div class="header-up">
					头像上传： <input type="file" name="touxiang" id="add-file" value="上传" />

				</div>
				<input type="submit" value="保存" class="advert-add-save user-addh" />
			</form>
		</div>
	</div>

</body>
</html>
