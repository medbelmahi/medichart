package ma.meditel.reporting.dao.implementations;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import ma.meditel.reporting.dao.interfaces.ISystemObjectDao;
import ma.meditel.reporting.entities.SystemObject;
import ma.meditel.reporting.util.HibernateUtil;

public class SystemObjectDaoImpl implements ISystemObjectDao {

	@Override
	public void addSystemObject(SystemObject s) throws Exception {
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		Session session = sessionFactory.openSession();
		
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			session.save(s);
			tx.commit();
			
		} catch (Exception e) {
			if (tx != null) tx.rollback();
			throw e;
			
		} finally {
			session.close();
		}
	}

	@Override
	public SystemObject getSystemObject(Long idSytem) throws Exception {
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		Session session = sessionFactory.openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			SystemObject so = (SystemObject) session.get(SystemObject.class, idSytem);
			return so;
			
		} catch (Exception e) {
			if (tx != null) tx.rollback();
			throw e;
			
		} finally {
			sessionFactory.getCache().evictEntityRegions();
			session.close();
		}
	}

	@Override
	public void MergeSystemObject(SystemObject s) throws Exception {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			session.merge(s);
			tx.commit();
			
		} catch (Exception e) {
			if (tx != null) tx.rollback();
			throw e;
			
		} finally {
			session.close();
		}
	}

}
