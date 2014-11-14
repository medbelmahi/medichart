package ma.meditel.reporting.dao.implementations;

import ma.meditel.reporting.dao.interfaces.IBugAmeliorationDao;
import ma.meditel.reporting.entities.BugAmelioration;
import ma.meditel.reporting.util.HibernateUtil;

import org.hibernate.Session;
import org.hibernate.Transaction;

public class BugAmeliorationDaoImpl implements IBugAmeliorationDao {

	@Override
	public void addBugAmelioration(BugAmelioration ba) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			session.save(ba);
			tx.commit();
			
		} catch (Exception e) {
			if (tx != null) tx.rollback(); throw e;
			
		} finally {
			session.close();
		}
	}
}