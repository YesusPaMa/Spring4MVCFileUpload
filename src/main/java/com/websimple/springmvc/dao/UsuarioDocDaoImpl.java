package com.websimple.springmvc.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.websimple.springmvc.modelo.UsuarioDoc;

@Repository("usuarioDocDao")
public class UsuarioDocDaoImpl extends AbstractDao<Integer, UsuarioDoc> implements UsuarioDocDao{

	@SuppressWarnings("unchecked")
	public List<UsuarioDoc> buscarTodo() {
		Criteria crit = createEntityCriteria();
		return (List<UsuarioDoc>)crit.list();
	}

	public void guardar(UsuarioDoc documento) {
		persist(documento);
	}

	public UsuarioDoc buscarId(int id) {
		return getByKey(id);
	}

	@SuppressWarnings("unchecked")
	public List<UsuarioDoc> buscarTodosUsuarioId(int usuarioId){
		Criteria crit = createEntityCriteria();
		Criteria userCriteria = crit.createCriteria("usuario");
		userCriteria.add(Restrictions.eq("id", usuarioId));
		return (List<UsuarioDoc>)crit.list();
	}

	public void borrarId(int id) {
		UsuarioDoc documento =  getByKey(id);
		borrar(documento);
	}

}
