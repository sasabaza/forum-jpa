<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql"%>
<%@taglib prefix="x" uri="http://java.sun.com/jsp/jstl/xml"%>


<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Topic: ${topic.title} - Forum</title>
	<link rel="stylesheet" href="<c:url value="/ressources/css/style.css" />">
</head>
<body>

	<header>
		<h1>
			<strong>Topic: ${topic.title}</strong>
		</h1>
	</header>
	<main>
		<jsp:include page="./inc/_menu.jsp"></jsp:include>

		<c:choose>
			<c:when test="${sessionScope['access']}">
				<fieldset>
					<form method="post" action="<c:url value='/topic?topicId=${topic.id}&f=ajouter' />">
						<input type="text" name="message" placeholder="Message">
						<input type="submit" value="Nouveau message">
					</form>
				</fieldset>
			</c:when>
		</c:choose>

		<fieldset>
			<legend>Messages du topic: ${topic.title}</legend>
			<ul>
				<c:forEach items="${topic.messages}" var="message" varStatus="status">
					<li>
					Par ${message.user} ${message.message} - le <fmt:setLocale value="fr_FR" /><fmt:formatDate pattern="EEEE F MMMM yyyy à HH:mm" value="${message.message_date}"/>			
						<c:choose>
							<c:when test="${sessionScope['access'] and sessionScope['userId'] == message.user.id}">
								<form method="post" action="<c:url value='/topic?topicId=${topic.id}&f=supprimer&messageId=${message.id}' /> ">
									<input type="submit"  value="X">
								</form>
								<form method="post" action="<c:url value='/topic?topicId=${topic.id}&f=modifier&messageId=${message.id}' /> ">
									<input type="text" name="message" placeholder="Message">
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