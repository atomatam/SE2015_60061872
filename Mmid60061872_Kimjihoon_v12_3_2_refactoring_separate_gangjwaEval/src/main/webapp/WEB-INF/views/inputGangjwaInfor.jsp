<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>    
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/home.css" type="text/css" media="screen" />
<script src="${pageContext.request.contextPath}/resources/js/jquery-2.1.3.js" type="text/javascript"></script>
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
			<div>과목명:${gwamok.getGwamokName()}</div>
			<form action="${pageContext.request.contextPath}/Course/setgangjwa" method="post">
				강좌 번호 설정 <input type="text" name="gangjwaID"/>
				최대 수강 인원 설정 <input type="text" name="maxStudent"/><br/>
				<input type="hidden" name="gwamokID" value="${gwamok.getGwamokID() }"/>
				<input type="hidden" name="gwamokHakjeom" value="${gwamok.getGwamokHakjeom() }"/>
				<input type="hidden" name="gwamokName" value="${gwamok.getGwamokName() }"/>
				<input type="submit" value="개설"/>
			</form>
			<br/><br/><br/><br/><br/>
			과목 학점 :${gwamok.getGwamokHakjeom() } <br/>
			과목이름 : ${gwamok.getGwamokName() }
			</div>
	</div>
</body>
</html>