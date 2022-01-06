<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
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
		<h1>liste des personnages</h1>
		<table class="table">
			<thead>
				<tr>
					<th>Id</th>
					<th>Nom</th>
					<th>Points de vie</th>
					<th>Race</th>
					<th>Vivant</th>
					<th>Monture</th>
					<th>Arme</th>
					<th>Armure</th>
					<th>Team</th>
					<th>Familier</th>
					<th>Version</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${personnages}" var="p">
					<tr>
						<td>${p.id}</td>
						<td>${p.nom}</td>
						<td>${p.pv}</td>
						<td>${p.race}</td>
						<td>${p.vivant}</td>
						<td>${p.monture.nom}</td>
						<td>${p.arme.nom}</td>
						<td>${p.armure.nom}</td>
						<td>${p.team}</td>
						<td>${p.familier.nom}</td>
						<td>${p.version}</td>

						<td><a href="${ctx}/personnage/edit?id=${p.id}"
							class="btn btn-outline-primary">editer</a></td>
						<td><a href="${ctx}/personnage/delete?id=${p.id}"
							class="btn btn-outline-danger">supprimer</a></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		<a href="${ctx}/personnage/add" class="btn btn-link">ajouter un
			Personnage</a>

	</div>
</body>
</html>