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
			<c:choose>
				<c:when test="${not empty gangjwaList}">
					<table style="padding:50px;" class="normalTable">
					<thead>
						<tr>
							<td>과목이름</td><td>강좌번호</td><td>과목 학점</td><td>학년</td><td>개설 년도</td><td>개설 학기</td><td>수강 인원</td><td>강좌 평가 보기</td>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${gangjwaList}" var="gangjwa">
							<form action="${pageContext.request.contextPath}/Course/showGangjwaEval"	method="post">
								<input type="hidden" name="gangjwaID" value="${gangjwa.gangjwaID}"/>
								<tr>
									<td>${gangjwa.gwamokName}</td>
									<td>${gangjwa.gangjwaID}</td>
									<td>${gangjwa.gwamokHakjeom}</td>
									<td>${gangjwa.haknyun}</td>
									<td>${gangjwa.year}</td>
									<td>${gangjwa.hakki}</td>
									<td>${gangjwa.currentStudent}</td>
									<td><input type="submit" value="강의 평가 보기"/></td>
								</tr>
							</form>
						</c:forEach>
					</tbody>
				</table>
				</c:when>
				<c:otherwise>
				<div style="padding:30px;">아직 개설 강좌가 존재하지 않습니다.</div>
				</c:otherwise>
			</c:choose>
		</div>
	</div>
</body>
</html>