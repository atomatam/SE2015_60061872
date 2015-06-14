<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<html>
<head>
	<meta charset="UTF-8">
	<title>Home</title>
	<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/home.css" type="text/css" media="screen" />
	<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/onlyhome.css" type="text/css" media="screen" />
	<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/jquery-2.1.3.js" ></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/home.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/onlyhome.js"></script>
</head>
<body>
	<div id="wrapMainAll">
	<sec:authorize access="hasRole('ROLE_GYOSU')">
		<c:if test="${not empty numberOfNewGangjwaEval}">
			<div id="evalAalarm"><a href="${pageContext.request.contextPath}/Course/showGangjwaEvalForm" class="evalAalarmToken">${numberOfNewGangjwaEval}개의 읽지 않은 강의 평가가 있습니다.</a></div>
		</c:if>
	</sec:authorize>
		<jsp:include page="common/header.jsp"></jsp:include>
		<div id="navy">
			<ul>
				<sec:authorize access="isAuthenticated()">
					<li>
						<div class="navyItem"><c:url value="/logout" var="logout"/>
						<a href="${logout}">로그 아웃</a></div>
					</li>
				</sec:authorize>
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
				<sec:authorize access="hasRole('ROLE_ADMIN')">
					<li>
							<div class="navyItem"><a href="${pageContext.request.contextPath }/Course/makegwamokForm">과목 개설</a></div>
					</li>
				</sec:authorize>
				<sec:authorize access="isAnonymous()">
					<li>
						<div class="navyItem"><a href="${pageContext.request.contextPath}/loginController/memberEnrollForm">회원	가입</a></div>
					</li>
				</sec:authorize>		
			</ul>
			<sec:authorize access="isAuthenticated()">
				<div id="welcomeword">
					<sec:authentication property="principal.userRealName"/>님 환영합니다.
				</div>
			</sec:authorize>
		</div>

		<div id="contentBoby">
			<sec:authorize access="isAnonymous()">
				<c:if test="${not empty completeMsg}">
					<script>alert('회원 가입을 축하드립니다. 로그인 해 주세요.');</script>
				</c:if>
				<div id="loginbox">
					<jsp:include page="member/login.jsp"></jsp:include>
				</div>
			</sec:authorize>		
			
			<div id="popularGangjwa">
				<div id="popularIntrodu">
				인기 강좌
				</div>
				<sec:authorize access="hasRole('ROLE_STU')">
					<div id="morePopular"><a href="${pageContext.request.contextPath}/Course/popularGangjwa">자세히</a></div>
				</sec:authorize>
				<c:choose>
				<c:when test="${not empty gangjwaList}">
				<table id="popularTable">
						<tr>
							<td>순위</td><td>&nbsp;강좌 번호</td><td>과목 이름</td>
						</tr>
					<c:forEach varStatus="status" items="${gangjwaList}" var="gangjwa">
						<tr>
							<td>${status.count}.</td><td>&nbsp;${gangjwa.gangjwaID}</td><td>&nbsp;&nbsp;${gangjwa.gwamokName}</td> 
						</tr>
					</c:forEach>
				</table>
				</c:when>
				<c:otherwise>
				<div style="position:relative; padding:30px;">아직 인기 강좌가 없습니다.</div>
				</c:otherwise>
				</c:choose>
			</div>
			<div id="mju5gong">
				<a href="http://www.mju.ac.kr"><img alt="mju5gong" src="${pageContext.request.contextPath}/resources/images/mju5gong.png"></a>
			</div>
		</div>
	</div>
</body>
</html>
