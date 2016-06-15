package co.edu.usbcali.demo.dao;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import co.edu.usbcali.demo.modelo.TiposUsuarios;

@Repository("tiposUsuariosHibernateDAO")
@Scope("singleton")
public class TiposUsuariosHibernateDAO implements ITiposUsuariosDAO {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public void crear(TiposUsuarios tiposUsuarios) {
		sessionFactory.getCurrentSession().save(tiposUsuarios);
	}

	@Override
	public void modificar(TiposUsuarios tiposUsuarios) {
		sessionFactory.getCurrentSession().update(tiposUsuarios);
	}

	@Override
	public void borrar(TiposUsuarios tiposUsuarios) {
		sessionFactory.getCurrentSession().delete(tiposUsuarios);
	}

	@Override
	public TiposUsuarios consultarPorId(Long tusuCodigo) {
		return (TiposUsuarios)sessionFactory.getCurrentSession().get(TiposUsuarios.class, tusuCodigo);
	}

	@Override
	public List<TiposUsuarios> consultarTodos() {
		return sessionFactory.getCurrentSession().createCriteria(TiposUsuarios.class).list();
	}

}
