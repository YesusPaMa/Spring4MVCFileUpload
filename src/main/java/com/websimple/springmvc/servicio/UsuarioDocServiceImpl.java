package com.websimple.springmvc.servicio;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.websimple.springmvc.dao.UsuarioDocDao;
import com.websimple.springmvc.modelo.UsuarioDoc;

@Service("userDocumentService")
@Transactional
public class UsuarioDocServiceImpl implements UsuarioDocService{

	@Autowired
	UsuarioDocDao dao;

	@Override
	public UsuarioDoc buscarId(int id) {
		return dao.buscarId(id);
	}

	@Override
	public List<UsuarioDoc> buscarTodo() {
		return dao.buscarTodo();
	}

	@Override
	public List<UsuarioDoc> buscarTodosUsuariosId(int usuarioId) {
		return dao.buscarTodosUsuarioId(usuarioId);
	}
	
	@Override
	public void guardarDoc(UsuarioDoc documento){
		dao.guardar(documento);
	}

	@Override
	public void borrarId(int id){
		dao.borrarId(id);
	}
	
}
