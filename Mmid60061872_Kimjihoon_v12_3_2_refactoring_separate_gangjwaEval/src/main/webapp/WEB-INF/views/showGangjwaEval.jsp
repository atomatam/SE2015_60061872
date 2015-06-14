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
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/showEvalOnly.css" type="text/css" media="screen" />
<script src="${pageContext.request.contextPath}/resources/js/jquery-2.1.3.js" type="text/javascript" ></script>
<script src="${pageContext.request.contextPath}/resources/js/showEvalOnly.js" type="text/javascript" ></script>
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
				<c:when test="${not empty gangjwaEval}">
					<div class="uncheckClass" style="float:right; width:150px; margin-right: 20px; text-align: center;">안 읽은 평가의 색 </div>
					<table>
						<thead>
							<tr>
								<td>학번</td><td>강좌 번호</td><td>강의 평가</td><td>날짜</td><td>보기</td>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${gangjwaEval}" var="eval">
								<form method="get" action="${pageContext.request.contextPath}/Course/detalEvaluationShow">
									<input type="hidden" name="userName" value="${eval.userName}"/>
									<input type="hidden" name="gangjwaID" value="${eval.gangjwaID}"/>
									<c:choose>
										<c:when test="${eval.isRead eq 0}">
											<tr class="uncheckClass">
										</c:when>
										<c:otherwise>
											<tr>
										</c:otherwise>
									</c:choose>
										<td>${eval.userName}</td><td>${eval.gangjwaID}</td><td><div id="realEval">${eval.gangjwaEval}</div></td>
										<td>${eval.date}</td><td><input type="submit" value="자세히 보기"/></td>
									</tr>
								</form>
							</c:forEach>
						</tbody>
					</table>						
				</c:when>
				<c:otherwise>
					<div style="padding:30px;">아직 강의 평가가 없습니다.</div>
				</c:otherwise>
			</c:choose>
		</div>
	</div>
</body>
</html>