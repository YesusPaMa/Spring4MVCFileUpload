<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html lang="es">

<head>
	<meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
	<title>Lista Usuarios</title>
	<link href="<c:url value='/static/css/bootstrap.css' />" rel="stylesheet"></link>
    <link href="<c:url value='/static/css/bootstrap-theme.css' />" rel="stylesheet"></link>
	<link href="<c:url value='/static/css/starter-template.css' />" rel="stylesheet"></link>
</head>

<body>
	<div class="container">
		<div class="starter-template">
			<h1>Mis Usuarios</h1>
			<div class="panel panel-default">
				<div class="panel-heading"><span class="lead"><a href="<c:url value='/newuser' />">Agregar nuevo usuario</a> </span></div>	
					<div class="tablecontainer">
					<table class="table table-striped">
						<thead>
							<tr>
								<th>Nombre</th>
								<th>Apellido</th>
								<th>Email</th>
								<th>SSO ID</th>
								<th width="100"></th>
								<th width="100"></th>
							</tr>
						</thead>
						<tbody>
						<c:forEach items="${usuarios}" var="usuario">
						<tr>
							<td>${usuario.nombre}</td>
							<td>${usuario.apellido}</td>
							<td>${usuario.email}</td>
							<td>${usuario.ssoId}</td>
							<td><a href="<c:url value='/edit-user-${usuario.ssoId}' />" class="btn btn-default" aria-label="Left Align"><span class="glyphicon glyphicon-edit" aria-hidden="true"></span></a></td>
							<td><a href="<c:url value='/delete-user-${usuario.ssoId}' />" class="btn btn-default" aria-label="Left Align"><span class="glyphicon glyphicon-trash" aria-hidden="true"></span></a></td>
						</tr>
						</c:forEach>
		    			</tbody>
		    		</table>
				</div>
			</div>
		</div>

    </div>
</body>
</html>