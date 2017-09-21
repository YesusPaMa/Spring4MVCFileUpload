package com.websimple.springmvc.servicio;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.websimple.springmvc.dao.UsuarioDao;
import com.websimple.springmvc.modelo.Usuario;

@Service("usuarioService")
@Transactional
public class UsuarioServiceImpl implements UsuarioService{

	@Autowired
	private UsuarioDao dao;

	public Usuario buscarId(int id) {
		return dao.buscarId(id);
	}

	public Usuario buscarSSO(String sso) {
		Usuario user = dao.buscarSSO(sso);
		return user;
	}

	public void guardarUsuario(Usuario usuario) {
		dao.guardar(usuario);
	}

	/*
	 * Dado que el método se ejecuta con transacción, no es necesario llamar a la actualización de hibernación explícitamente.
	 * Solo busque la entidad desde db y actualícela con los valores apropiados dentro de la transacción.
	 * Se actualizará en db una vez finaliza la transacción.
	 */
	public void actualizarUsuario(Usuario usuario) {
		Usuario entity = dao.buscarId(usuario.getId());
		if(entity!=null){
			entity.setSsoId(usuario.getSsoId());
			entity.setNombre(usuario.getNombre());
			entity.setApellido(usuario.getApellido());
			entity.setEmail(usuario.getEmail());
			entity.setUsuarioDoc(usuario.getUsuarioDoc());
		}
	}

	
	public void borrarUsuarioSSO(String sso) {
		dao.borrarSSO(sso);
	}

	public List<Usuario> buscarTodosUsuarios() {
		return dao.buscarTodosUsuarios();
	}

	public boolean esUsuarioSSOUnico(Integer id, String sso) {
		Usuario usuario = buscarSSO(sso);
		return ( usuario == null || ((id != null) && (usuario.getId() == id)));
	}
	
}
