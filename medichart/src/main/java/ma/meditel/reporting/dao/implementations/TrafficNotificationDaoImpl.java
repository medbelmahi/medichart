package ma.meditel.reporting.dao.implementations;

import java.util.Date;
import java.util.List;

import ma.meditel.reporting.dao.interfaces.ITrafficNotificationDao;
import ma.meditel.reporting.entities.TrafficNotification;
import ma.meditel.reporting.util.HibernateUtil;

import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

public class TrafficNotificationDaoImpl implements ITrafficNotificationDao {

	@Override
	public TrafficNotification getTrafficNotification(Long idTrafficNotification) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			TrafficNotification trafficNoti = (TrafficNotification) session.get(TrafficNotification.class, idTrafficNotification);
			tx.commit();
			Hibernate.initialize(trafficNoti.getIinterface());
			return trafficNoti;
			
		} catch (Exception e) {
			if (tx != null) tx.rollback(); throw e;
			
		} finally {
			session.close();
		}
	}

	@Override
	public List<TrafficNotification> getAllTrafficNotification() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			Criteria cr = session.createCriteria(TrafficNotification.class);
			List<TrafficNotification> trafficNotis = cr.list();
			
			for(TrafficNotification trafficNoti : trafficNotis)
			{
				Hibernate.initialize(trafficNoti.getIinterface());
				Hibernate.initialize(trafficNoti.getIinterface().getNode());
				System.out.println(trafficNoti.getIinterface().getIfType());
			}
			
			tx.commit();
			
			return trafficNotis;
			
		} catch (Exception e) {
			if (tx != null) tx.rollback(); throw e;
			
		} finally {
			session.close();
		}
	}

	@Override
	public void mergeTrafficNoti(TrafficNotification trafficNoti) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			session.merge(trafficNoti);
			tx.commit();
			
		} catch (Exception e) {
			if (tx != null) tx.rollback(); throw e;
			
		} finally {
			session.close();
		}
	}

	@Override
	public void addNoti(TrafficNotification trafficNoti) throws Exception {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			session.save(trafficNoti);
			tx.commit();
			
		} catch (Exception e) {
			if (tx != null) tx.rollback();
			throw e;
			
		} finally {
			session.close();
		}
		
	}

	@Override
	public void removeNoti(Long idTrafficNoti) throws Exception {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = null;
		try {
			TrafficNotification trafficNoti = getTrafficNotification(idTrafficNoti);
			
			tx = session.beginTransaction();
			session.delete(trafficNoti);
			tx.commit();
			
		} catch (Exception e) {
			if (tx != null) tx.rollback();
			throw e;
			
		} finally {
			session.close();
		}
	}

	@Override
	public void removeAllTrafficNotis() throws Exception {
		List<TrafficNotification> trafficNotis = getAllTrafficNotification();
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			for(TrafficNotification traficNoti : trafficNotis){
				session.delete(traficNoti);
			}
			tx.commit();
			
		} catch (Exception e) {
			if (tx != null) tx.rollback();
			throw e;
			
		} finally {
			session.close();
		}
	}

	@Override
	public TrafficNotification getTrafficNotificationWithoutId(
			Long idInterface, Date jourDate, String typeTraffic) {

		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			
			Criteria cr = session.createCriteria(TrafficNotification.class);
			cr.add(Restrictions.eq("iinterface.idInterface", idInterface))
			.add(Restrictions.eq("typeOrClassTraffic", typeTraffic))
			.add(Restrictions.eq("dateJour", jourDate))
			.addOrder(Order.asc("dateJour"));
			
			tx.commit();
			
			TrafficNotification theTemp = (TrafficNotification) cr.uniqueResult();
			
//			if(theTemp != null)
//			{
//				System.out.println("temp -> null");
//			}else
//			{
//				System.out.println("temp -> not null");
//			}
			
			return theTemp;
		
		} catch (Exception e) {
			return null;
//			if (tx != null) tx.rollback();
			
//			throw e;
			
		} finally {
			session.close();
		}
	}

	@Override
	public Date getMaxDateTrafficNoti() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			Criteria cr = session.createCriteria(TrafficNotification.class);
			cr.setProjection(Projections.max("dateJour"));
			
			Date d = (Date) cr.uniqueResult();
			
			tx.commit();
			return d;
			
		} catch (Exception e) {
			if (tx != null) tx.rollback();
			throw e;
			
		} finally {
			session.close();
		}
	}
}
