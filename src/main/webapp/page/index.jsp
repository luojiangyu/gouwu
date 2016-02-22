<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
<script>
	function login() {
		loginForm.submit();
	}
</script>
</head>
<body>
	<form id="loginForm" action="login.do">
		<table  style="align:center">
			<tr>
				<td>账号</td>
				<td><input type="text" name="userName" value="" /></td>
			</tr>
			<tr>
				<td>密码</td>
				<td><input type="password" name="password" value="" /></td>

			</tr>
			<tr>
			<td><button onclick="login()">登录</button></td><td><button>取消</button></td>
			</tr>
		</table>
	
	</form>
</body>
</html>