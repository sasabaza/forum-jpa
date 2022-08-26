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
	<title>Login - Forum</title>
	<link rel="stylesheet" href="<c:url value="/ressources/css/style.css" />">
</head>
<body>

<header>
	<h1><strong>Login</strong></h1>
</header>
<main>	
	<jsp:include page="./inc/_menu.jsp"></jsp:include>
	
	<div class="espace">
		<div>Formulaire de connexion</div>		
		<form action="<c:url value="/login"/>" method="POST">
			<div><input type="text" name="username"></div>
			<div><input type="password" name="password"></div>
			<div><input type="submit" value="Se connecter"></div>		
		</form>
	</div>

</main>	

<footer>
	<jsp:include page="./inc/_footer.jsp"></jsp:include>
</footer>

</body>
</html>