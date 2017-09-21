<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="es">
	<head>
		<meta charset="utf-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<title>Confirmacion de Registro</title>
		<!-- Bootstrap core CSS -->
		<link href="<c:url value='/static/css/bootstrap.css' />" rel="stylesheet"></link>
	    <link href="<c:url value='/static/css/bootstrap-theme.css' />" rel="stylesheet"></link>
		<link href="<c:url value='/static/css/starter-template.css' />" rel="stylesheet"></link>
	</head>

	<body>
	<div class="container">
		<div class="starter-template">
			<div class="alert alert-success lead">
				${success}
			</div>
			<a class="btn btn-default custom-width" href="<c:url value='/add-document-${usuario.id}' />">Administra tus documentos</a>	
			<a class="btn btn-default custom-width" href="<c:url value='/list' />">Lista de usuarios</a>
		</div>
	</div>
	</body>

</html>