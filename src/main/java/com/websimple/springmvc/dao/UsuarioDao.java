package com.websimple.springmvc.dao;

import java.util.List;

import com.websimple.springmvc.modelo.Usuario;

public interface UsuarioDao {

	Usuario buscarId(int id);
	
	Usuario buscarSSO(String sso);
	
	void guardar(Usuario usuario);
	
	void borrarSSO(String sso);
	
	List<Usuario> buscarTodosUsuarios();

}

