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
<script src="${pageContext.request.contextPath}/resources/js/gangjwaEvalForm.js" type="text/javascript" ></script>
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
			<div id="serviceTitle">강의 평가할 대상을 선택하십시오.</div>
				<c:choose>
					<c:when test="${not empty gangjwaList}">
						<table>
							<thead>
								<tr>
									<td>강좌 번호</td><td>과목 이름</td><td>학점</td><td>학년</td><td>개설 연도</td><td>학기</td><td>담당 교수</td><td>선택</td>
								</tr>
							</thead>
							<tbody>
								<c:forEach items="${gangjwaList}" var="gangjwa">
									<form action="${pageContext.request.contextPath}/Course/gangjwaEval" method="post">
										<input type="hidden" name="gangjwaID" value="${gangjwa.gangjwaID}"/>
										<tr>
											<td style="width:80px;">${gangjwa.gangjwaID}</td><td>${gangjwa.gwamokName}</td>
											<td style="width:80px;">${gangjwa.gwamokHakjeom}</td>
											<td style="width:80px;">${gangjwa.haknyun}</td><td style="width:80px;">${gangjwa.year}</td>
											<td style="width:80px;">${gangjwa.hakki}</td>
											<td style="width:80px;">${gangjwa.profname}</td>
											<td style="width:80px;"><input name="${gangjwa.gangjwaID}" type="button" value="선택 하기"/></td>
										</tr>
										<tr class="${gangjwa.gangjwaID}">
											<c:forEach items="${gangjwaEvalList}" var="gangjwaEvalList">
												<c:if test="${gangjwa.gangjwaID eq gangjwaEvalList.gangjwaID}">
													<c:set value="${gangjwaEvalList.gangjwaEval}" var="eval"/>
												</c:if>
											</c:forEach>
											<td colspan="7" style="width:580px;"><textarea style="width:580px;height:200px;"  name="gangjwaEval"  >${eval}</textarea></td>
											<td><input type="submit" value="제출"/>
											<c:remove var="eval"/>
										</tr>
									</form>
								</c:forEach>
							</tbody>
						</table>
					</c:when>
					<c:otherwise>
					아직 수강신청을 하지 않으셨습니다.
					</c:otherwise>
				</c:choose>
			</div>
		</div>
	</div>
</body>
</html>