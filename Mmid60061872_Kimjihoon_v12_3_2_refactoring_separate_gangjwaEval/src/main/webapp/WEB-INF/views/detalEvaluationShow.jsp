<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<html>
<head>
<title>Home</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/home.css" type="text/css" media="screen" />
<script src="${pageContext.request.contextPath}/resources/js/jquery-2.1.3.js" type="text/javascript" ></script>
<script src="${pageContext.request.contextPath}/resources/js/home.js" type="text/javascript" ></script>
</head>
<body>
	<div id="wrapMainAll">
		<jsp:include page="common/header.jsp"></jsp:include>
		<div id="navy">
			<ul>
				<li>
					<c:url value="/logout" var="logout"/><a href="${logout}">로그 아웃</a><br/>
				</li>
				<sec:authorize access="hasRole('ROLE_GYOSU')">
					<li>
						<div class="navyItem"><a href="${pageContext.request.contextPath}/Course/getGangjwaList">강좌 등록</a></div>
					</li>
				</sec:authorize>
				<sec:authorize access="hasRole('ROLE_GYOSU')">
					<li>
						<div class="navyItem"><a href="${pageContext.request.contextPath }/Course/gangjwaInfoForm">강좌 관리</a></div>
					</li>
				</sec:authorize>
				<sec:authorize access="hasRole('ROLE_GYOSU')">
					<li>
						<div class="navyItem"><a href="${pageContext.request.contextPath }/Course/showGangjwaEvalForm">강의 평가 확인</a></div>
					</li>
				</sec:authorize>
			</ul>
		</div>
		<div id="contentBoby">
			<table style="padding-left:50px;padding-top: 50px;">
				<tr>
					<td>학번</td>
					<td>${gangjwaEval.userName}</td>
				</tr>
				<tr>
					<td>날짜</td>
					<td>${gangjwaEval.date}</td>
				</tr>
			</table>
			<form action="${pageContext.request.contextPath}/Course/showGangjwaEval" method="post">
				<input type="hidden" name="gangjwaID" value="${gangjwaEval.gangjwaID}"/>
				<input type="submit" value="확인 하기" style="margin-left:500px;padding:2px;"/>
			</form>
			<fieldset style="margin-left:20px; width:700px;height:100px;">
				<legend>강의 평가</legend>
				<div style="margin-left:30px;">${gangjwaEval.gangjwaEval}</div>
			</fieldset>
		</div>
	</div>
</body>
</html>