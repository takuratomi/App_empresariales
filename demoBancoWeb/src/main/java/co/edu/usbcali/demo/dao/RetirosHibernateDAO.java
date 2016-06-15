package co.edu.usbcali.demo.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import co.edu.usbcali.demo.modelo.Retiros;
import co.edu.usbcali.demo.modelo.RetirosId;

@Repository
@Scope("singleton")
public class RetirosHibernateDAO implements IRetirosDAO {

	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public void crear(Retiros retiros) {
		sessionFactory.getCurrentSession().save(retiros);
	}

	@Override
	public void modificar(Retiros retiros) {
		sessionFactory.getCurrentSession().update(retiros);
	}

	@Override
	public void borrar(Retiros retiros) {
		sessionFactory.getCurrentSession().delete(retiros);
	}

	@Override
	public Retiros consultarPorId(RetirosId id) {
		return (Retiros)sessionFactory.getCurrentSession().get(Retiros.class,id);
	}

	@Override
	public List<Retiros> consultarTodos() {
		return sessionFactory.getCurrentSession().createCriteria(Retiros.class).list();
	}

	@Override
	public Long getConsecutivo() {
		String sqlName="RETIROS_SEQ";
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
