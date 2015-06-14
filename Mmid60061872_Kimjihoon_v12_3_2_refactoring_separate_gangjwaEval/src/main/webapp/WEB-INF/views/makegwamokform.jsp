<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec"	uri="http://www.springframework.org/security/tags"%>
<html>
<head>
<style>
	.errorMessage {
		color:red;
	}
</style>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/home.css" type="text/css" media="screen" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/toggle_format.css" type="text/css" media="screen" />
<script src="${pageContext.request.contextPath}/resources/js/jquery-2.1.3.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/resources/js/toggleTable_formtag.js" type="text/javascript"></script>
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
			</ul>
		</div>
		<div id="contentBoby">
			<c:if test="${not empty madeGwamok }">
				<div style="color:red;">
					 ${madeGwamok.gwamokID } / ${madeGwamok.gwamokName } / ${madeGwamok.gwamokHakjeom } 생성 완료
					 <br />
					 <br/>
				 </div>
			</c:if>
			
			<div id="title_toggle">현재 개설 과목 현황</div>
			<div id="alreadyTable">
				<table>
					<tr>
						<th>과목 아이디</th>
						<th>과목 이름</th>
						<th>과목 학점</th>
						<th>학년</th>
						<th>연도</th>
						<th>학기</th>
					</tr>
					<c:forEach items="${allGwamokList}" var="gwamok">
						<tr>
							<td>${gwamok.gwamokID}</td>
							<td>${gwamok.gwamokName}</td>
							<td>${gwamok.gwamokHakjeom}</td>
							<td>${gwamok.haknyun}</td>
							<td>${gwamok.year}</td>
							<td>${gwamok.hakki}</td>
						</tr>
					</c:forEach>
				</table>
			</div>
			<br/>
			<form:form commandName="gwamok">
				<form:label path="gwamokID" cssClass="" cssErrorClass="errorMessage">과목 아이디</form:label>
				<form:input path="gwamokID" size="20" />
				<form:errors path="gwamokID" cssClass="errorMessage" />
				<br />
				<form:label path="gwamokName" cssClass="" cssErrorClass="errorMessage">과목 이름</form:label>
				<form:input path="gwamokName" size="20" />
				<form:errors path="gwamokName" cssClass="errorMessage" />
				<br />
				<form:label path="gwamokHakjeom" cssClass="" cssErrorClass="errorMessage">과목 학점</form:label>
				<form:input path="gwamokHakjeom" size="20" />
				<form:errors path="gwamokHakjeom" cssClass="errorMessage" />
				<br/>
				<form:label path="haknyun" cssClass="" cssErrorClass="errorMessage">해당 학년</form:label>
				<form:input path="haknyun" size="20" />
				<form:errors path="haknyun" cssClass="errorMessage" />
				<br/>
				<form:label path="year" cssClass="" cssErrorClass="errorMessage">해당 년도</form:label>
				<form:input path="year" size="20" />
				<form:errors path="year" cssClass="errorMessage" />
				<br/>
				<form:label path="hakki" cssClass="" cssErrorClass="errorMessage">해당 학기</form:label>
				<form:input path="hakki" size="20" />
				<form:errors path="hakki" cssClass="errorMessage" />
				<br/>
				<input type="submit" value="과목 생성" />
			</form:form>
		</div>
	</div>
</body>
</html>