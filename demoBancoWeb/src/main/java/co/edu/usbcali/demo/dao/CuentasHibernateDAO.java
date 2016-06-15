package co.edu.usbcali.demo.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import co.edu.usbcali.demo.modelo.Cuentas;

@Repository
@Scope("singleton")
public class CuentasHibernateDAO implements ICuentasDAO {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public void crear(Cuentas cuentas) {
		sessionFactory.getCurrentSession().save(cuentas);
	}

	@Override
	public void modificar(Cuentas cuentas) {
		sessionFactory.getCurrentSession().update(cuentas);
	}

	@Override
	public void borrar(Cuentas cuentas) {
		sessionFactory.getCurrentSession().delete(cuentas);
	}

	@Override
	public Cuentas consultarPorId(String cueNumero) {
		return (Cuentas) sessionFactory.getCurrentSession().get(Cuentas.class, cueNumero);
	}

	@Override
	public List<Cuentas> consultarTodos() {
		return sessionFactory.getCurrentSession().createCriteria(Cuentas.class).list();
	}

	@Override
	public Long getConsecutivo() {
		String sqlName="CUENTAS_SEQ";
		Long consecutivo = null;
		List qlist = null;
	try {
		Session session = sessionFactory.getCurrentSession();
		Query query = session.getNamedQuery(sqlName);
		qlist = query.list();
		consecutivo = (Long)qlist.get(0);		
	} catch (org.hibernate.HibernateException e) {
		consecutivo = new Long(0);		
	}
		return consecutivo;
	}

}
