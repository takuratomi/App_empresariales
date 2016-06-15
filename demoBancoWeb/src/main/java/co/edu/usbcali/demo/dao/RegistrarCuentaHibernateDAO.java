package co.edu.usbcali.demo.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import co.edu.usbcali.demo.modelo.RegistrarCuenta;

@Repository
@Scope("singleton")
public class RegistrarCuentaHibernateDAO implements IRegistrarCuentaDAO {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public void crear(RegistrarCuenta registrarCuenta) {
		sessionFactory.getCurrentSession().save(registrarCuenta);
	}

	@Override
	public void modificar(RegistrarCuenta registrarCuenta) {
		sessionFactory.getCurrentSession().update(registrarCuenta);
	}

	@Override
	public void borrar(RegistrarCuenta registrarCuenta) {
		sessionFactory.getCurrentSession().delete(registrarCuenta);
	}

	@Override
	public RegistrarCuenta consultarPorId(long regId) {
		return (RegistrarCuenta) sessionFactory.getCurrentSession().get(RegistrarCuenta.class, regId);
	}

	@Override
	public List<RegistrarCuenta> consultarTodos() {
		return sessionFactory.getCurrentSession().createCriteria(RegistrarCuenta.class).list();
	}

	@Override
	public synchronized Long getConsecutivo() {
		String sqlName="REGISTRARCUENTAS_SEQ";
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
	