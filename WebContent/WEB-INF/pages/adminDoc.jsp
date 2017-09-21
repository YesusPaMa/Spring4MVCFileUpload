<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<!DOCTYPE html>
<html lang="es">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Actualizar/Descargar/Borrar Documentos</title>
    <!-- Bootstrap core CSS -->
    <link href="<c:url value='/static/css/bootstrap.css' />" rel="stylesheet"></link>
    <link href="<c:url value='/static/css/bootstrap-theme.css' />" rel="stylesheet"></link>
    <link href="<c:url value='/static/css/starter-template.css' />" rel="stylesheet"></link>
  </head>

  <body>

    <div class="container">
		<div class="starter-template">
			<h1>Administracion de Documentos</h1>
			<div class="panel panel-default">
				<div class="panel-heading">Subir Nuevo Documento</div>
				<div class="panel-body">
				<div class="uploadcontainer">
					<form:form method="POST" modelAttribute="archivo" enctype="multipart/form-data" class="form-horizontal form-left col-md-12">
						<div class="row">
							<div class="form-group col-md-12">
								<label class="col-md-3 control-lable" for="archivo">Subir un documento</label>
								<div class="col-md-7">
									<form:input type="file" path="archivo" id="archivo" class="form-control input-sm"/>
									<div class="has-error">
										<form:errors path="archivo" class="help-inline"/>
									</div>
								</div>
							</div>
						</div>
						<div class="row">
							<div class="form-group col-md-12">
								<label class="col-md-3 control-lable" for="file">Descripcion</label>
								<div class="col-md-7">
									<form:input type="text" path="descripcion" id="descripcion" class="form-control input-sm"/>
								</div>
								
							</div>
						</div>
				
						<div class="row">
							<div class="form-actions">
								<input type="submit" value="Subir" class="btn btn-primary btn-sm">
							</div>
						</div>
					</form:form>
				</div>
				</div>
			</div>
			
			<div class="panel panel-default">
				<div class="panel-heading">Lista de Documentos</div>
				<div class="panel-body">
					<div class="uploadcontainer">
						<div class="tablecontainer">
							<table class="table table-hover">
								<thead>
									<tr>
										<th>No.</th>
										<th>Nombre del Archivo</th>
										<th>Tipo</th>
										<th>Descripcion</th>
										<th width="100"></th>
										<th width="100"></th>
									</tr>
								</thead>
								<tbody>
									<c:forEach items="${documentos}" var="doc" varStatus="counter">
										<tr>
											<td>${counter.index + 1}</td>
											<td>${doc.nombre}</td>
											<td>${doc.tipo}</td>
											<td>${doc.descripcion}</td>
											<td><a href="<c:url value='/download-document-${usuario.id}-${doc.id}' />" class="btn btn-default custom-width">Descargar</a></td>
											<td><a href="<c:url value='/delete-document-${usuario.id}-${doc.id}' />" class="btn btn-default custom-width">Borrar</a></td>
										</tr>
									</c:forEach>
								</tbody>
							</table>
						</div>
					</div>
				</div>
			</div>
			
			<div class="form-left">
				<a class="btn btn-default custom-width" href="<c:url value='/list' />">Lista de Usuarios</a>
			</div>
		</div>
    </div><!-- /.container -->
  </body>
</html>