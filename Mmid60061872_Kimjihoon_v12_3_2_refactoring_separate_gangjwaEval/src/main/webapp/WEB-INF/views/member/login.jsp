<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/login.css" type="text/css" media="screen"/>
<script src="${pageContext.request.contextPath}/resources/js/jquery-2.1.3.js" type="text/javascript" ></script>
<script src="${pageContext.request.contextPath}/resources/js/login.js" type="text/javascript" ></script>
</head>
<body>
	<div id="loginbox">
		<c:if test="${not empty param.fail }">
			아이디 혹은 비밀번호를 다시 입력해 주세요.
		</c:if>
		<form action="${pageContext.request.contextPath}/login" method="post">
		<table>
			<tr>
				<td><input type="text" name="userid" placeholder="ID" id="inputbox"/></td>
				<td rowspan="2" height="30"><input type="submit" value="login" id="submitbox"/></td>
			</tr>
			<tr>
				<td><input type="password" name="userpassword" placeholder="Password" id="inputbox"/></td>
			</tr>
			</table>
		</form>
		

	</div>
</body>
</html>