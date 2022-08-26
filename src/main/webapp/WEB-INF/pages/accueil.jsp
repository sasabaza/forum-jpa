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
	<title>Forum</title>
	<link rel="stylesheet" href="<c:url value="/ressources/css/style.css" />">
</head>
<body>

<header>
	<h1><strong>Forum</strong></h1>
</header>
<main>
	<jsp:include page="./inc/_menu.jsp"></jsp:include>
	
	<c:choose>
		<c:when test="${sessionScope['access']}">
			<fieldset>
				<form method="post" action="<c:url value='/accueil?f=ajouter' />" >
					<input type="text" name="title" placeholder="Titre">
					<input type="submit" value="Nouveau topic" >
				</form>
			</fieldset>
		</c:when>
	</c:choose>
	
	<fieldset>
		<legend>Topics du forum</legend>
		<ul>
		<c:forEach items="${listTopics}" var="topic" varStatus="status">
			<li>
				<a href="<c:url value="/topic?topicId=${topic.id}"/>">${topic.title}</a>
				 - le <fmt:setLocale value="fr_FR" /><fmt:formatDate pattern="EEEE F MMMM yyyy à HH:mm" value="${topic.topic_date}"/>
				<c:choose>
					<c:when test="${sessionScope['access'] and sessionScope['userId'] == topic.user.id}">
						<form method="post" action="<c:url value='/accueil?f=supprimer&id=${topic.id}' /> ">
							<input type="submit" value="X">
						</form>
						
						<form method="post" action="<c:url value='/accueil?f=maj&id=${topic.id}' /> ">
							<input type="text" name="title" placeholder="Titre">
							<input type="submit"  value="Modifier">
						</form>
					</c:when>
				</c:choose>				
			</li>
		</c:forEach>
		</ul>
	</fieldset>

</main>
<footer>
	<jsp:include page="./inc/_footer.jsp"></jsp:include>
</footer>
</body>
</html>