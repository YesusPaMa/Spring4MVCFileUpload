package com.websimple.springmvc.modelo;

import org.springframework.web.multipart.MultipartFile;

public class Archivo {

	MultipartFile archivo;
	
	String descripcion;

	public MultipartFile getArchivo() {
		return archivo;
	}

	public void setArchivo(MultipartFile archivo) {
		this.archivo = archivo;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}


}