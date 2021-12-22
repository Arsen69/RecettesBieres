<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:set var="ctx" value="${pageContext.servletContext.contextPath}" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3"
	crossorigin="anonymous">
<script
	src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.10.2/dist/umd/popper.min.js"
	integrity="sha384-7+zCNj/IqJ95wo16oMtfsKbZ9ccEh31eOz1HGyDuCQ6wgnyJNSYdrPa03rtR1zdB"
	crossorigin="anonymous"></script>
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.min.js"
	integrity="sha384-QJHtvGhmr9XOIpI6YVutG+2QOK9T+ZnN4kzFN1RtK3zEFEIsxhlmWl5/YESvpZ13"
	crossorigin="anonymous"></script>
<title>lotr</title>
</head>
<body>
	<div class="container">
		<h1>liste des equipements</h1>
		<table class="table">
			<thead>
				<tr>
					<th>id:</th>
					<th>nom:</th>
					<th>Date de création</th>
					<th>Attaque</th>
					<th>Défense</th>
					<th>Mains</th>
					<th>Portée</th>
					<th>Matériaux</th>
					<th>Type de Monture</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${equipements}" var="equipement">
					<tr>
						<td>${equipement.id}</td>
						<td>${equipement.nom}</td>
						<td>
						<fmt:parseDate value="${equipement.creation}" var="dateCreation" pattern="yyy-MM-dd'T'HH:mm:ss"></fmt:parseDate>
						<fmt:formatDate value="${dateCreation}"
								pattern="dd/MM/yyyy hh:mm"></fmt:formatDate> </td>
						<td>${equipement.stats.attaque}</td>
						<td>${equipement.stats.defense}</td>

						<td><c:if test="${equipement.getClass().name=='model.Arme'}">${equipement.hand}</c:if></td>
						<td><c:if test="${equipement.getClass().name=='model.Arme'}">${equipement.portee}</c:if></td>

						<td><c:if
								test="${equipement.getClass().name=='model.Armure'}">
						${equipement.materiaux}
						</c:if></td>
						<td><c:if
								test="${equipement.getClass().name=='model.Monture'}">
						${equipement.type}
						</c:if></td>
						<td><a href="${ctx}/equipement/edit?id=${equipement.id}"
							class="btn btn-outline-primary">editer</a></td>
						<td><a href="${ctx}/equipement/delete?id=${equipement.id}"
							class="btn btn-outline-danger">supprimer</a></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		<a href="${ctx}/equipement/add/Arme" class="btn btn-link">ajouter
			une Arme</a> <a href="${ctx}/equipement/add/Armure" class="btn btn-link">ajouter
			une Armure</a> <a href="${ctx}/equipement/add/Monture"
			class="btn btn-link">ajouter une Monture</a>

	</div>
</body>
</html>