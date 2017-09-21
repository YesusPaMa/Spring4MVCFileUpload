<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html lang="es">

<head>
	<meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
	<title>Formulario de Registro</title>
	<link href="<c:url value='/static/css/bootstrap.css' />" rel="stylesheet"></link>
    <link href="<c:url value='/static/css/bootstrap-theme.css' />" rel="stylesheet"></link>
	<link href="<c:url value='/static/css/starter-template.css' />" rel="stylesheet"></link>
</head>

<body>
    <div class="container">
		<div class="starter-template">
			<h1>Usuario</h1>
			<div class="panel panel-default">
				<div class="panel-body">
 					<form:form method="POST" modelAttribute="usuario" class="form-horizontal form-left col-md-12">
						<form:input type="hidden" path="id" id="id"/>
		
						<div class="row">
							<div class="form-group">
								<label class="col-md-1 control-lable" for="nombre">Nombre</label>
								<div class="col-md-11">
									<form:input type="text" path="nombre" id="nombre" class="form-control input-sm"/>
									<div class="has-error">
										<form:errors path="nombre" class="help-inline"/>
									</div>
								</div>
							</div>
						</div>

						<div class="row">
							<div class="form-group">
								<label class="col-md-1 control-lable" for="apellido">Apellido</label>
								<div class="col-md-11">
									<form:input type="text" path="apellido" id="apellido" class="form-control input-sm" />
									<div class="has-error">
										<form:errors path="apellido" class="help-inline"/>
									</div>
								</div>
							</div>
						</div>

						<div class="row">
							<div class="form-group">
								<label class="col-md-1 control-lable" for="ssoId">SSO ID</label>
								<div class="col-md-11">
									<c:choose>
										<c:when test="${editar}">
											<form:input type="text" path="ssoId" id="ssoId" class="form-control input-sm" disabled="true"/>
										</c:when>
										<c:otherwise>
											<form:input type="text" path="ssoId" id="ssoId" class="form-control input-sm" />
											<div class="has-error">
												<form:errors path="ssoId" class="help-inline"/>
											</div>
										</c:otherwise>
									</c:choose>
								</div>
							</div>
						</div>

						<div class="row">
							<div class="form-group">
								<label class="col-md-1 control-lable" for="email">Email</label>
								<div class="col-md-11">
									<form:input type="text" path="email" id="email" class="form-control input-sm" />
									<div class="has-error">
										<form:errors path="email" class="help-inline"/>
									</div>
								</div>
							</div>
						</div>

						<div class="row">
							<div class="form-actions">
								<div class="form-right col-md-12">
									<c:choose>
										<c:when test="${editar}">
											<input type="submit" value="Actualizar" class="btn btn-primary btn-sm"/> <a class="btn btn-default btn-sm" href="<c:url value='/list' />">Cancelar</a>
										</c:when>
										<c:otherwise>
											<input type="submit" value="Registrar" class="btn btn-primary btn-sm"/> <a class="btn btn-default btn-sm" href="<c:url value='/list' />">Cancelar</a>
										</c:otherwise>
									</c:choose>
								</div>
							</div>
						</div>		
					</form:form>
				</div>
				<c:if test="${editar}">
				<div class="panel-footer">
					<div class="row">
						<div class="col-md-12">
							<a class="btn btn-default col-md-12" href="<c:url value='/add-document-${usuario.id}' />">Administrar Documentos</a>
						</div>
					</div>
				</div>
				</c:if>
			</div>
		</div>
    </div><!-- /.container -->
  </body>
</html>