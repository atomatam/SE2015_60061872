<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>	
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/home.css" type="text/css" media="screen" />
<script src="${pageContext.request.contextPath}/resources/js/jquery-2.1.3.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/resources/js/home.js" type="text/javascript" ></script>
</head>
<body>
	<div id="wrapMainAll">
		<jsp:include page="common/header.jsp"></jsp:include>
		<div id="navy">
			<ul>
			</ul>
		</div>
		<div id="contentBoby">
			<div style="color: red;">${errorMessage}</div>
			<form action="${pageContext.request.contextPath}/loginController/memberEnroll" method="post">
				<table>
					<tr>
						<td>아이디</td>
						<td><input type="text" name="username" /></td>
					</tr>
					<tr>
						<td>비밀번호</td>
						<td><input type="password" name="password" /></td>
					</tr>
					<tr>
						<td>이름</td>
						<td><input type="text" name="userRealName" /></td>
					</tr>
					<tr>
						<td>직업</td>
						<td><select name="userCode">
								<option value="3">student</option>
								<option value="2">professor</option>
								<option value="1">manager</option>
						</select></td>
					</tr>
				</table>
				<input type="submit" value="가입 신청" />
			</form>
		</div>
	</div>
</body>
</html>