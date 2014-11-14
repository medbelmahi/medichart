package ma.meditel.reporting.dao.implementations;

import java.util.List;

import ma.meditel.reporting.dao.interfaces.ISystemFTPDao;
import ma.meditel.reporting.entities.SystemFTP;
import ma.meditel.reporting.util.HibernateUtil;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class SystemFTPDaoImpl implements ISystemFTPDao {

	@Override
	public SystemFTP getSystemFTP(Long idSytemFtp) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			SystemFTP sftp = (SystemFTP) session.get(SystemFTP.class, idSytemFtp);
			tx.commit();
			
			return sftp;
			
		} catch (Exception e) {
			if (tx != null) tx.rollback(); throw e;
			
		} finally {
			session.close();
		}
	}

	@Override
	public void mergeFTP(SystemFTP sftp) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			session.merge(sftp);
			tx.commit();
			
		} catch (Exception e) {
			if (tx != null) tx.rollback(); throw e;
			
		} finally {
			session.close();
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<SystemFTP> getAllSystemFTPs() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			Criteria cr = session.createCriteria(SystemFTP.class);
			List<SystemFTP> sftp = cr.list();
			tx.commit();
			
			return sftp;
			
		} catch (Exception e) {
			if (tx != null) tx.rollback(); throw e;
			
		} finally {
			session.close();
		}
	}

}
