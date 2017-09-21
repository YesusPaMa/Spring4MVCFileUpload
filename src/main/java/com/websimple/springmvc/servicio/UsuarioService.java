package com.websimple.springmvc.servicio;

import java.util.List;

import com.websimple.springmvc.modelo.Usuario;


public interface UsuarioService {
	
	Usuario buscarId(int id);
	
	Usuario buscarSSO(String sso);
	
	void guardarUsuario(Usuario usuario);
	
	void actualizarUsuario(Usuario usuario);
	
	void borrarUsuarioSSO(String sso);

	List<Usuario> buscarTodosUsuarios(); 
	
	boolean esUsuarioSSOUnico(Integer id, String sso);

}