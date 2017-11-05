<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>

	<style type="text/css">
		*{margin: 0;padding: 0;}
		.form-upload{padding: 10px;width:500px;margin: auto;}
		.form-upload p{font-size: 14px;margin-bottom: 10px;line-height: 30px;}
		.form-upload p:nth-child(1){margin-left: 41px;}
		.form-upload p:nth-child(3){margin-left: 55px;}
		.form-upload p input{width: 200px;height: 30px;outline: none;padding-left: 5px;border: 1px solid #ddd;}
		.form-upload p textarea{resize: none;width: 200px;height: 120px;outline: none;padding: 5px;margin-left:95px;border: 1px solid #ddd;}
		button{width: 60px;height: 30px;margin:10px 0 0 150px;border:none;border:1px solid #ccc;border-radius: 2px;outline: none;}
		.choose{display: inline-block;color: #000;text-decoration: none;width: 70px;border: 1px solid #DDDDDD;position: relative;text-align: center;cursor: pointer;border-radius: 3px;}
		.choose:hover{background: #DDD;}
		.choose input{width: 100%;height: 100%;opacity: 0;position: absolute;top: 0;left: 0;}
	</style>

</head>

<body>

	<form action="../APK_Upload_file" method="post"
		enctype="multipart/form-data" class="form-upload">
		<p>版本号：<input type="text" name="version"></p>
		<p>需上传的APK：<a href="javaScript:;" class="choose">选择文件<input type="file" name="apk" /></a></p> 
		<p>描述：</p>
		<p><textarea  name="miaosu"></textarea></p>
		<button type="submit">上传</button>
	</form>
</body>
</html>
