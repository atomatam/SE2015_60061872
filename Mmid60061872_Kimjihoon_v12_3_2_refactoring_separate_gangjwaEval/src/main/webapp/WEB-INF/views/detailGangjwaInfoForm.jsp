<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/home.css" type="text/css" media="screen" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/toggleTable.css" type="text/css" media="screen"/>
<script src="${pageContext.request.contextPath}/resources/js/jquery-2.1.3.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/resources/js/toggleTable.js" type="text/javascript"></script>
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
			<div style="color:red;">
				<c:if test="${not empty ErrorMsg}">
				${ErrorMsg }
				</c:if>
			</div>
			<c:choose>
				<c:when test="${not empty UserScore}">
				<table style="padding:50px;">
					<thead>
						<tr><th>학번</th><th>이름</th><td>점수</td><th>입력</th></tr>
					</thead>
					<tbody>
						<c:forEach items="${UserScore}" var="scores">
							<tr>
								<form method="post" action="${pageContext.request.contextPath}/Course/setScore">
									<input type="hidden" name="username" value="${scores.username }"/>
									<input type="hidden" name="gangjwaID" value="${gangjwaID }"/>
									<input type="hidden" name="userRealName" value="${scores.userRealName }"/>
									<td>${scores.username}</td>
									<td>${scores.userRealName}</td>
									<td><input type="text" name="score" value="${scores.score}"/></td>
									<td><input type="submit" value="입력 하기"/>
								</form>
							</tr>
						</c:forEach>
					</tbody>
				</table>
				</c:when>
				<c:otherwise>
				<p>현재 수강하고 있는 수강 학생이 없습니다.</p>
				</c:otherwise>
			</c:choose>
		</div>
	</div>
</body>
</html>