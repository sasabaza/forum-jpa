<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql" %>
<%@taglib prefix="x" uri="http://java.sun.com/jsp/jstl/xml" %>

<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Compte - Forum</title>
	<link rel="stylesheet" href="<c:url value="/ressources/css/style.css" />">
</head>
<body>

<header>
	<h1><strong>Compte</strong></h1>
</header>
<main>
	<jsp:include page="./inc/_menu.jsp"></jsp:include>
	<div>Bonjour</div>
<c:choose >
	<c:when test="${sessionScope['userRole'] == 'administrateur'}">
	
		<c:forEach items="${listusers}" var="user" varStatus="status">
			<form action="<c:url value="/account"/>" method="POST">
				<div><input type="hidden" name="userid" placeholder="userid" value="${user.id}">${user.username} <input type="submit" value="X"></div>
			</form>
		</c:forEach>
	</c:when>
	<c:otherwise>
		<div></div>
	</c:otherwise>
</c:choose>	
	

</main>

<footer>
	<jsp:include page="./inc/_footer.jsp"></jsp:include>
</footer>

</body>
</html>