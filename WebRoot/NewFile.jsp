<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<link rel="stylesheet" href="common/bootstrap/bootstrap.css">
<link rel="stylesheet" href="common/bootstrap/bootstrap-select.css">
<script src="../common/lib/jquery-1.9.0.min.js" type="text/javascript" charset="utf-8"></script>
<script type="text/javascript" src="common/bootstrap/bootstrap.js"></script>
<script src="common/bootstrap/bootstrap-select.js"></script>
<script src="common/bootstrap/i18n/defaults-zh_CN.js"></script>
<body>

	<select id="lunch" class="selectpicker" data-live-search="true" title="输入或选择作者">
	</select>
	<script type="text/javascript">
		function getschoolList() {
			$.ajax({
				url : "/eschool/viewEschoolList",
				type : "get",

				dataType : "json",

				data : 'data',

				success : function(data) {

					$.each(data.data, function(i) {

						$('#schoolno.selectpicker')
								.append(
										"<option value=" + data.data[i].schoolno + ">"
												+ data.data[i].schoolname
												+ "</option>");

					});

					$('#schoolno').selectpicker('refresh');

				},

				error : function(data) {

					alert("查询作者失败" + data);

				}
			})

		}
	</script>
</body>
</html>