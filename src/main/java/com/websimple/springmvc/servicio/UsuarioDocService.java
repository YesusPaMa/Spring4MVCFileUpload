package com.websimple.springmvc.servicio;

import java.util.List;

import com.websimple.springmvc.modelo.UsuarioDoc;

public interface UsuarioDocService {

	UsuarioDoc buscarId(int id);

	List<UsuarioDoc> buscarTodo();
	
	List<UsuarioDoc> buscarTodosUsuariosId(int id);
	
	void guardarDoc(UsuarioDoc documento);
	
	void borrarId(int id);
}
