package com.websimple.springmvc.utilidades;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.websimple.springmvc.modelo.Archivo;

@Component
public class ValidaArchivo implements Validator {

	public boolean supports(Class<?> clazz) {
		return Archivo.class.isAssignableFrom(clazz);
	}

	public void validate(Object obj, Errors errors) {
		Archivo archivo = (Archivo) obj;
			
		if(archivo.getArchivo()!=null){
			if (archivo.getArchivo().getSize() == 0) {
				errors.rejectValue("file", "missing.file");
			}
		}
	}
		
	
}

