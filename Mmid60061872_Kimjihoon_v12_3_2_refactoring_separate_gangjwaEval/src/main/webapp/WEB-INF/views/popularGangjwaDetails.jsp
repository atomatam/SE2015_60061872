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
					<sec:authorize access="hasRole('ROLE_STU')">
						<li>
						<c:url value="/logout" var="logout"/><a href="${logout}">로그 아웃</a><br/>
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
				</ul>
			</div>
			<div id="contentBoby">
				<sec:authorize access="isAnonymous()">
					<div id="loginbox">
						<jsp:include page="member/login.jsp"></jsp:include>
					</div>
				</sec:authorize>		
				<div id="notYetTable">
				<div id="serviceTitle">성적 확인</div>
				<div id="popularBody">
					<c:choose>
						<c:when test="${not empty gangjwaList}">
							<table>
								<thead>
									<tr>
										<td>순위</td><td>강좌 아이디</td><td>과목 이름</td><td>담당 교수</td><td>현재 수강 인원</td><td>빠른 수강 신청</td>
									</tr>
								</thead>
								<tbody>
									<c:forEach varStatus="status" items="${gangjwaList}" var="gangjwa">
										<form action="${pageContext.request.contextPath}/Course/directSugangshinchung" method="post">
											<input type="hidden" name="gwamokID" value="${gangjwa.gwamokID}"/>
											<input type="hidden" name="gwamokName" value="${gangjwa.gwamokName}"/>
											<input type="hidden" name="gwamokHakjeom" value="${gangjwa.gwamokHakjeom}"/>
											<input type="hidden" name="gangjwaID" value="${gangjwa.gangjwaID}"/>
											<input type="hidden" name="maxStudent" value="${gangjwa.maxStudent}"/>
											<input type="hidden" name="currentStudent" value="${gangjwa.currentStudent}"/>
											<tr>
												<td>${status.count}</td><td>${gangjwa.gangjwaID}</td><td>${gangjwa.gwamokName}</td>
												<td>${gangjwa.profname}</td><td>${gangjwa.currentStudent}</td><td><input type="submit" value="수강 신청"/></td>
											</tr>
										</form>
									</c:forEach>
								</tbody>
							</table>
						</c:when>
					</c:choose>
					</div>
				</div>
			</div>
		</div>
</body>
</html>