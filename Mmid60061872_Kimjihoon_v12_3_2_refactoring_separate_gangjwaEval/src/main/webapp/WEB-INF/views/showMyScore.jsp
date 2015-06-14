<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/home.css" type="text/css" media="screen" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/toggleTable.css" type="text/css" media="screen"/>
<script src="${pageContext.request.contextPath}/resources/js/jquery-2.1.3.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/resources/js/toggleTable.js" type="text/javascript"></script>
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
				<sec:authorize access="hasRole('ROLE_STU')">
					<li>
						<a href="${pageContext.request.contextPath}/Course/sugagnShinchunghome">수강신청 하기</a>
					</li>
				</sec:authorize>
				<sec:authorize access="hasRole('ROLE_STU')">
					<li>
						<div class="navyItem"><a href="${pageContext.request.contextPath }/Course/showMyScore">성적 확인</a></div>
					</li>
				</sec:authorize>
				<sec:authorize access="hasRole('ROLE_STU')">
					<li>
						<div class="navyItem"><a href="${pageContext.request.contextPath }/Course/gangjwaEvalForm">강의 평가</a></div>
					</li>
				</sec:authorize>
			</ul>
		</div>
		<div id="contentBoby">	
			<div id="notYetTable">
			<div id="serviceTitle">성적 확인</div>
			<c:choose>
				<c:when test="${not empty gangjwaScoreTable}">
					<table class="normalTable">
						<thead>
							<tr>
								<td>강좌번호</td>
								<td>과목 명</td>
								<td>담당 교수</td>
								<td>개설 년도</td>
								<td>학기</td>
								<td>학년</td>
								<td>정원</td>
								<td>학점</td>
								<td>점수</td>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${gangjwaScoreTable}" var="gangjwatable">
								<tr>
									<td>${gangjwatable.gangjwaID}</td>
									<td>${gangjwatable.gwamokName}</td>
									<td>${gangjwatable.profname}</td>
									<td>${gangjwatable.year}</td>
									<td>${gangjwatable.hakki}</td>
									<td>${gangjwatable.haknyun}</td>
									<td>${gangjwatable.maxStudent}</td>
									<td>${gangjwatable.gwamokHakjeom}</td>
									<td>${gangjwatable.score}</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</c:when>
				<c:otherwise>
				수강신청을 아직 하지 않으셨습니다.
				</c:otherwise>
			</c:choose>
		</div>
		</div>
	</div>
</body>
</html>