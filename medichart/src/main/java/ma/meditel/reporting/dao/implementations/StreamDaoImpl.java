package ma.meditel.reporting.dao.implementations;

import java.util.Date;
import java.util.List;

import ma.meditel.reporting.dao.interfaces.IStreamDao;
import ma.meditel.reporting.entities.Stream;
import ma.meditel.reporting.util.HibernateUtil;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

public class StreamDaoImpl implements IStreamDao {

	@SuppressWarnings("unchecked")
	@Override
	public List<Stream> getAllStreams(Date startDate, Date endDate) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			
			Criteria cr = session.createCriteria(Stream.class);
			cr.add(Restrictions.ge("date", startDate))
				.add(Restrictions.le("date", endDate))
				.add(Restrictions.or(Restrictions.ge("lossPrecentMax", 0.01), Restrictions.ge("jpHiMax", 5000.0), Restrictions.ge("dpHiMax", 50000.0)));
			
			tx.commit();
			
			return cr.list();
			
		} catch (Exception e) {
			if (tx != null) tx.rollback();
			throw e;
			
		} finally {
			session.close();
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Stream> getAllStreamsBetween(Date startDate, Date endDate, String emetteur, String recepteur, String typeStream, String voix) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			
			Criteria cr = session.createCriteria(Stream.class);
			cr.add(Restrictions.ge("date", startDate))
				.add(Restrictions.le("date", endDate))
				.add(Restrictions.eq("probTransmitter", emetteur))
				.add(Restrictions.eq("probReceiver", recepteur))
				.add(Restrictions.eq("typeStream", typeStream))
				.add(Restrictions.eq("voice", voix))
				.addOrder(Order.asc("date"));
			
			tx.commit();
			
			return cr.list();
			
		} catch (Exception e) {
			if (tx != null) tx.rollback();
			throw e;
			
		} finally {
			session.close();
		}
	}
	
}
