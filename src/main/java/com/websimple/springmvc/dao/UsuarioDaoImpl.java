package com.websimple.springmvc.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.websimple.springmvc.modelo.Usuario;

@Repository("usuarioDao")
public class UsuarioDaoImpl extends AbstractDao<Integer, Usuario> implements UsuarioDao {

	public Usuario buscarId(int id) {
		Usuario usuario = getByKey(id);
		return usuario;
	}

	public Usuario buscarSSO(String sso) {
		System.out.println("SSO : "+sso);
		Criteria crit = createEntityCriteria();
		crit.add(Restrictions.eq("ssoId", sso));
		Usuario usuario = (Usuario)crit.uniqueResult();
		return usuario;
	}

	@SuppressWarnings("unchecked")
	public List<Usuario> buscarTodosUsuarios() {
		Criteria criteria = createEntityCriteria().addOrder(Order.asc("nombre"));
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);//Para evitar duplicados.
		List<Usuario> usuario = (List<Usuario>) criteria.list();
		
		return usuario;
	}

	public void guardar(Usuario usuario) {
		persist(usuario);
	}

	public void borrarSSO(String sso) {
		Criteria crit = createEntityCriteria();
		crit.add(Restrictions.eq("ssoId", sso));
		Usuario usuario = (Usuario)crit.uniqueResult();
		borrar(usuario);
	}

}
