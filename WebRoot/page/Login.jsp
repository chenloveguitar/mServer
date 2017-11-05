<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>登录</title>
<!-- <link rel="stylesheet" type="text/css" href="../mServer/common/css/luzou.css" /> -->
<link rel="stylesheet" type="text/css" href="../common/css/luzou.css" />
</head>

<body>
	<div class="login-content">
		<div class="login-img"></div>
		<div class="login-box">
			<div class="box-img"></div>
			<form action="../Login" method="post">
				<input class="login-input" type="text" placeholder="用户名"
					name="admin_user" /> <input class="login-input" type="password"
					placeholder="密码" name="password" />
				<button class="sub-btn" id="login" type="submit">登录</button>
			</form>
		</div>

	</div>


</body>




</html>
