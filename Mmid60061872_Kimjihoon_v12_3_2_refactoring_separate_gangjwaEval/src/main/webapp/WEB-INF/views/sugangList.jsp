<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="kr.ac.mju.model.*" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/toggleTable.css" type="text/css" media="screen"/>
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/home.css" type="text/css" media="screen" />
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
		<div style="color:red;">
			<c:if test="${not empty errorMessage}">
				${errorMessage}
			</c:if>
		</div>
		<div id="alreadyTable">
		<c:choose>
			<c:when test="${not empty alreadySugangList}">
				<table>
					<tr><td  colspan="4">현재 수강 신청한 강좌</td></tr>
					<tr><td>과목아이디</td><td>과목이름</td>
					<td>학점</td><td>현재 수강 인원</td><td>최대 수강 인원</td><td>학년</td><td>개설 년도</td><td>개설 학기</td><td>강좌번호</td><td>담당 교수</td><td>철회</td></tr>
					<c:forEach items="${alreadySugangList}" var="gangjwa">
						<form action="${pageContext.request.contextPath}/Course/sugangdraw" method="POST">
							<tr>
								<td>${gangjwa.gwamokID}</td>
								<td>${gangjwa.gwamokName}</td>
								<td>${gangjwa.gwamokHakjeom}</td>
								<td>${gangjwa.currentStudent}</td>
								<td>${gangjwa.maxStudent}</td>
								<td>${gangjwa.haknyun}</td>
								<td>${gangjwa.year}</td>
								<td>${gangjwa.hakki}</td>
								<td>${gangjwa.gangjwaID}</td>
								<td>${gangjwa.profname}</td>
								<td><input type="submit" value="철회"/></td>
							</tr>
							<input type="hidden" value="${gangjwa.gangjwaID}" name="gangjwaID"/>
						</form>
					</c:forEach>
				</table>
				<br/><br/>
				<div style="float:right;">현재 ${totalHakjeom}학점 수강 중</div>
			</c:when>
			<c:otherwise>
				<br/>
				<div>현재 수강신청 하신 과목이 없습니다.</div>
			</c:otherwise>
		</c:choose>
		</div>
		<br/>
		<div id="notYetTable">
			<div id="serviceTitle">수강 신청 하기</div>
			<c:choose>
				<c:when test="${not empty sugangList}">
					<table>
						<tr><td>과목아이디</td><td>과목이름</td>
							<td>학점</td><td>현재 수강 인원</td><td>최대 수강 인원</td><td>학년</td><td>개설 년도</td><td>개설 학기</td><td>강좌번호</td><td>담당 교수</td><td>수강신청</td>
						</tr>
						<c:forEach items="${sugangList}" var="gangjwa">
							<form action="${pageContext.request.contextPath}/Course/sugangshinchung" method="post">
								<input type="hidden" name="gwamokID" value="${gangjwa.gwamokID}"/>
								<input type="hidden" name="gwamokName" value="${gangjwa.gwamokName}"/>
								<input type="hidden" name="gwamokHakjeom" value="${gangjwa.gwamokHakjeom}"/>
								<input type="hidden" name="gangjwaID" value="${gangjwa.gangjwaID}"/>
								<input type="hidden" name="maxStudent" value="${gangjwa.maxStudent}"/>
								<input type="hidden" name="currentStudent" value="${gangjwa.currentStudent}"/>
								<input type="hidden" name="totalHakjeom" value="${totalHakjeom}"/>
								<tr>
									<td>${gangjwa.gwamokID}</td>
									<td>${gangjwa.gwamokName}</td>
									<td>${gangjwa.gwamokHakjeom}</td>
									<td>${gangjwa.currentStudent}</td>
									<td>${gangjwa.maxStudent}</td>
									<td>${gangjwa.haknyun}</td>
									<td>${gangjwa.year}</td>
									<td>${gangjwa.hakki}</td>
									<td>${gangjwa.gangjwaID}</td>
									<td>${gangjwa.profname}</td>
									<td><input type="submit" value="수강 신청"/></td>
								</tr>
							</form>
						</c:forEach>
					</table>
				</c:when>
				<c:otherwise>
					더이상 수강신청 할 수 있는 강좌가 없습니다.<br/><br/>
				</c:otherwise>
			</c:choose>
			</div>
		</div>
	</div>
</body>
</html>