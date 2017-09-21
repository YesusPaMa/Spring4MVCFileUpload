package com.websimple.springmvc.dao;

import java.util.List;

import com.websimple.springmvc.modelo.UsuarioDoc;

public interface UsuarioDocDao {

	List<UsuarioDoc> buscarTodo();
	
	UsuarioDoc buscarId(int id);
	
	void guardar(UsuarioDoc documento);
	
	List<UsuarioDoc> buscarTodosUsuarioId(int usuarioId);
	
	void borrarId(int id);
}
