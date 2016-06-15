package co.edu.usbcali.demo.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import co.edu.usbcali.demo.modelo.Consignaciones;
import co.edu.usbcali.demo.modelo.ConsignacionesId;

@Repository
@Scope("singleton")
public class ConsignacionesHibernateDAO implements IConsignacionDAO {

	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public void crear(Consignaciones consignaciones) {
		sessionFactory.getCurrentSession().save(consignaciones);
	}

	@Override
	public void modificar(Consignaciones consignaciones) {
		sessionFactory.getCurrentSession().update(consignaciones);
	}

	@Override
	public void borrar(Consignaciones consignaciones) {
		sessionFactory.getCurrentSession().delete(consignaciones);
	}

	@Override
	public Consignaciones consultarPorId(ConsignacionesId consignacionesId) {
		return (Consignaciones)sessionFactory.getCurrentSession().get(Consignaciones.class, consignacionesId);
	}

	@Override
	public List<Consignaciones> consultarTodos() {
		return sessionFactory.getCurrentSession().createCriteria(Consignaciones.class).list();
	}

	@Override
	public Long getConsecutivo() {
		String sqlName="CONSIGNACIONES_SEQ";
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
