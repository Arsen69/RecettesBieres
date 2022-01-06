<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
		<h1>Edition Personnage</h1>
		<form:form action="${ctx}/personnage" method="post"
			modelAttribute="personnage">
			<div class="form-group">
				<form:label path="id">Id</form:label>
				<form:input path="id" cssClass="form-control" readonly="true"
					placeholder="generation automatique" />
				<form:errors path="id" cssClass="alert alert-danger" element="div"></form:errors>
			</div>
			<div class="form-group">
				<form:label path="nom">Nom</form:label>
				<form:input cssClass="form-control" path="nom" />
				<form:errors path="nom" cssClass="alert alert-danger" element="div"></form:errors>
			</div>
			<div>
				<form:label path="pv">Points de vie</form:label>
				<form:input cssClass="form-control" path="pv" type="number" />
				<form:errors path="pv" cssClass="alert alert-danger" element="div"></form:errors>
			</div>
			<div>
				<form:label path="race">Race</form:label>
				<form:select cssClass="form-control" path="race" items="${races}"></form:select>
			</div>
			<div class="form-group">
				<div class="form-check">
					<form:label path="vivant" cssClass="form-check-label">Vivant</form:label>
					<!--
				<form:select cssClass="form-control" path="vivant">
					<form:option value="true" />
					<form:option value="false" />
				</form:select>
				  -->
					<form:checkbox path="vivant" cssClass="form-check-input" />
					<form:errors path="vivant" cssClass="alert alert-danger"
						element="div"></form:errors>
				</div>
			</div>
			<div class="form-group">
				<form:label path="arme">Arme</form:label>
				<form:select cssClass="form-control" path="arme.id">
					<form:option value="${null}">Pas d'arme</form:option>
					<form:options items="${armes}" itemValue="id" itemLabel="nom"></form:options>
				</form:select>
				<form:errors path="arme" cssClass="alert alert-danger" element="div"></form:errors>
			</div>
			<div class="form-group">
				<form:label path="armure.id">Armure</form:label>
				<form:select cssClass="form-control" path="armure.id">
					<form:option value="${null}">Pas d'armure</form:option>
					<form:options items="${armures}" itemValue="id" itemLabel="nom"></form:options>
				</form:select>
				<form:errors path="armure.id" cssClass="alert alert-danger"
					element="div"></form:errors>
			</div>
			<div class="form-group">
				<form:label path="monture">Monture</form:label>
				<form:select cssClass="form-control" path="armure.id">
					<form:option value="${null}">Pas de Monture</form:option>
					<form:options items="${montures}" itemValue="id" itemLabel="nom"></form:options>
				</form:select>
				<form:errors path="monture" cssClass="alert alert-danger"
					element="div"></form:errors>
			</div>
			<div>
				<button type="submit" class="btn btn-outline-success">enregistrer</button>
				<a href="${ctx}/personnage" class="btn btn-outline-warning">annuler</a>
			</div>
		</form:form>
	</div>
</body>
</html>