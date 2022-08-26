<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql" %>
<%@taglib prefix="x" uri="http://java.sun.com/jsp/jstl/xml" %>

<!DOCTYPE html>
<html>
	<div class="menu">
		<nav>
			<ul>
				<li><a href="<c:url value="/accueil"/>">Accueil</a></li>
				<li><a href="<c:url value="/a-propos"/>">A propos</a></li>
				<c:if test="${!sessionScope['access']}">
					<li><a href="<c:url value="/creer-compte"/>">Créer compte</a></li>
					<li><a href="<c:url value="/login"/>">Login</a></li>
				</c:if>
				<c:if test="${sessionScope['access']}">
					<li><a href="<c:url value="/account"/>">Compte</a></li>
					<li><a href="<c:url value="/login?logoff=true"/>">Deconnexion</a></li>
				</c:if>
			</ul>
		</nav>
	</div>
</html>