package ma.meditel.reporting.dao.implementations;

import java.util.Date;
import java.util.List;

import ma.meditel.reporting.dao.interfaces.ITrafficDao;
import ma.meditel.reporting.entities.Traffic;
import ma.meditel.reporting.util.HibernateUtil;

import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

public class TrafficDaoImpl implements ITrafficDao {

	@Override
	public Traffic getTraffic(Long idTraffic) throws Exception {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			Traffic t = (Traffic) session.get(Traffic.class, idTraffic);
			if(t != null){
				Hibernate.initialize(t.getIinterface());
			}
			tx.commit();
			return t;
			
		} catch (Exception e) {
			if (tx != null) tx.rollback();
			throw e;
			
		} finally {
			session.close();
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Traffic> getAllInterfaceTrafficsBetween(Long idInterface, Date startDate, Date endDate, String typeTraffic) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			
			Criteria cr = session.createCriteria(Traffic.class);
			cr.add(Restrictions.eq("iinterface.idInterface", idInterface))
			.add(Restrictions.ge("date", startDate))
			.add(Restrictions.le("date", endDate))
			.add(Restrictions.eq("typeTraffic", typeTraffic))
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

	@Override
	public Date getMaxDateTraffic() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			Criteria cr = session.createCriteria(Traffic.class);
			cr.setProjection(Projections.max("date"));
			
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

	@Override
	public Double getMaxValueTrafficInADay(Date dateJour, Long idInterface,
			String typeTraffic) {
		
		Long HOUR = (long) (3600*1000);
		Date endDate = new Date(dateJour.getTime() + 24 * HOUR);
		
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			
			Criteria cr = session.createCriteria(Traffic.class);
			cr.add(Restrictions.eq("iinterface.idInterface", idInterface))
			.add(Restrictions.ge("date", dateJour))
			.add(Restrictions.le("date", endDate))
			.add(Restrictions.eq("typeTraffic", typeTraffic))
			.addOrder(Order.asc("date"))
			.setProjection(Projections.max("metriceValue"));
			
			tx.commit();
			
			
			
			return (Double) cr.uniqueResult();
		
		} catch (Exception e) {
			if (tx != null) tx.rollback();
			throw e;
			
		} finally {
			session.close();
		}
	
	}
	
	
	/**
	 * le format de la date doit étre comme suit : 2014-08-01 00:00:00
	 * 
	 * pour nous donnez la liste des traffic d'une interface entre : 
	 * @param dayDate : 2014-08-01 00:00:00 and dayDate + 1 jour -> 2014-08-02 00:00:00
	 */
	@Override
	public List<Traffic> getAllInterfaceTrafficsOfTheDay(Long idInterface,
			Date dayDate, String typeTraffic) {
		Long HOUR = (long) (3600*1000);
		Date endDate = new Date(dayDate.getTime() + 24 * HOUR);
		
		return getAllInterfaceTrafficsBetween(idInterface,dayDate,endDate,typeTraffic);
	}

	
	
	@Override
	public Integer getCountAllInterfaceTrafficsBetween(Long idInterface,
			Date startDate, Date endDate, String typeTraffic) {
		
		List<Traffic> theList = getAllInterfaceTrafficsBetween(idInterface,startDate,endDate,typeTraffic);
		
		return theList.size();
	}

	@Override
	public void mergeTraffic(Traffic t) {
		
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			session.merge(t);
			tx.commit();
			
		} catch (Exception e) {
			if (tx != null) tx.rollback(); throw e;
			
		} finally {
			session.close();
		}
		
	}

}
