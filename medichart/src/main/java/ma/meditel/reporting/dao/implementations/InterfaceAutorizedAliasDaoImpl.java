package ma.meditel.reporting.dao.implementations;

import java.util.List;

import ma.meditel.reporting.dao.interfaces.IInterfaceAutorizedAliasDao;
import ma.meditel.reporting.entities.InterfaceAutorizedAlias;
import ma.meditel.reporting.util.HibernateUtil;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class InterfaceAutorizedAliasDaoImpl implements IInterfaceAutorizedAliasDao {

	@Override
	public void addInterfaceAutorizedAlias(InterfaceAutorizedAlias i) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			session.save(i);
			tx.commit();
			
		} catch (Exception e) {
			if (tx != null) tx.rollback();
			throw e;
			
		} finally {
			session.close();
		}
	}

	@Override
	public InterfaceAutorizedAlias getInterfaceAutorizedAlias(Long id) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			return (InterfaceAutorizedAlias)session.get(InterfaceAutorizedAlias.class, id);
			
		} catch (Exception e) {
			if (tx != null) tx.rollback();
			throw e;
			
		} finally {
			session.close();
		}
	}
	
	@Override
	public void removeInterfaceAutorizedAlias(Long id) {
		InterfaceAutorizedAlias i = getInterfaceAutorizedAlias(id);
		
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			session.delete(i);
			tx.commit();
			
		} catch (Exception e) {
			if (tx != null) tx.rollback();
			throw e;
			
		} finally {
			session.close();
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<InterfaceAutorizedAlias> getAllInterfaceAutorizedAlias() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			Criteria cr = session.createCriteria(InterfaceAutorizedAlias.class);
			return cr.list();
			
		} catch (Exception e) {
			if (tx != null) tx.rollback();
			throw e;
			
		} finally {
			session.close();
		}
	}

}
