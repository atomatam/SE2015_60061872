<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="java.util.*"%>
<%@page import="kr.ac.mju.model.*"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<html lang="ko">
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
			<c:if test="${not empty madeGangjwa }">
				<br />
				<div>
					<fieldset style="width:500px;">
						<legend style="color:red;">강좌 개설 완료</legend>
						<table>
							<tr><td>강좌 아이디</td><td>강좌 이름</td><td>학점</td><td>최대 학생수</td></tr>
							<tr><td>${madeGangjwa.gangjwaID}</td><td>${madeGangjwa.gwamokName }</td>
								<td>${madeGangjwa.gwamokHakjeom }</td><td>${madeGangjwa.maxStudent }</td></tr>
						 </table>
						</fieldset>
					 <br />
				 </div>
			</c:if>
			<br />
			<div style="color: red;">${errorMessage}</div>
			<div id="alreadyTable">
				<c:choose>
					<c:when test="${not empty allGangjwa}">
						<table>
							<tr>
								<td colspan="4">현재 개설하신 강좌 리스트</td>
							</tr>
							<tr>				
								<td>강좌아이디</td>
								<td>과목 아이디</td>
								<td>과목 이름</td>
								<td>학점</td>
								<td>학년</td>
								<td>개설 년도</td>
								<td>개설 학기</td>					

							</tr>
							<c:forEach items="${allGangjwa}" var="gangjwa">
							<tr>
								<td>${gangjwa.gangjwaID}</td>
								<td>${gangjwa.gwamokID}</td>
								<td>${gangjwa.gwamokName}</td>
								<td>${gangjwa.gwamokHakjeom}</td>
								<td>${gangjwa.haknyun}</td>
								<td>${gangjwa.year}</td>
								<td>${gangjwa.hakki}</td>
							</tr>
							</c:forEach>
						</table>
					</c:when>
					<c:otherwise>
						<br/><br/>현재 개설하신 강좌가 없습니다.
					</c:otherwise>
				</c:choose>
			</div>
			<br/>
			<div id="notYetTable">
			<div id="serviceTitle">강좌 개설 하기</div>
				<table>
					<tr>
						<td colspan="4">개설 가능 과목 리스트</td>
					</tr>
					<tr>
						<th>과목아이디</th>
						<th>과목이름</th>
						<th>학점</th>
						<td>학년</td>
						<td>개설 년도</td>
						<td>개설 학기</td>		
						<th>개설 선택</th>
					</tr>
					<c:forEach items="${allGwamok}" var="gwamok">
						<form action="${pageContext.request.contextPath}/Course/makeGangjwa"
							method="post">
							<input type="hidden" name="gwamokID" value="${gwamok.gwamokID}"/>
							<input type="hidden" name="gwamokName" value="${gwamok.gwamokName}"/> 
							<input type="hidden" name="gwamokHakjeom" value="${gwamok.gwamokHakjeom}"/>
							<input type="hidden" name="haknyun" value="${gwamok.haknyun}"/>
							<input type="hidden" name="year" value="${gwamok.year}"/> 
							<input type="hidden" name="hakki" value="${gwamok.gwamokHakjeom}"/>
							<tr>
								<td>${gwamok.gwamokID}</td>
								<td>${gwamok.gwamokName}</td>
								<td>${gwamok.gwamokHakjeom}</td>
								<td>${gwamok.haknyun}</td>
								<td>${gwamok.year}</td>
								<td>${gwamok.hakki}</td>
								<td><input type="submit" value="강좌 개설" /></td>
							</tr>
						</form>
					</c:forEach>
				</table>
				<br/><br/>
			</div>
		</div>
	</div>
</body>
</html>