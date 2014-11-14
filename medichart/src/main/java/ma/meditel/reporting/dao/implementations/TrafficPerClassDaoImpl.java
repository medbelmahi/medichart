package ma.meditel.reporting.dao.implementations;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import ma.meditel.reporting.dao.interfaces.ITrafficPerClassDao;
import ma.meditel.reporting.entities.TrafficClassification;
import ma.meditel.reporting.util.HibernateUtil;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

public class TrafficPerClassDaoImpl implements ITrafficPerClassDao {

	@SuppressWarnings("unchecked")
	@Override
	public List<TrafficClassification> getAllInterfaceTrafficsBetween(Long idInterface, Date startDate, Date endDate, Long classTraffic) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			
			Criteria cr = session.createCriteria(TrafficClassification.class);
			cr.add(Restrictions.eq("iinterface.idInterface", idInterface))
			.add(Restrictions.ge("date", startDate))
			.add(Restrictions.le("date", endDate))
			.add(Restrictions.eq("tclasse.refTClasse", classTraffic))
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
	public List<TrafficClassification> getAllInterfaceTrafficsClassificationOfTheDay(Long idInterface, Date dayDate, Long classTraffic) {
		Long HOUR = (long) (3600*1000);
		Date endDate = new Date(dayDate.getTime() + 24 * HOUR);
		
		
		return getAllInterfaceTrafficsBetween(idInterface, dayDate, endDate, classTraffic);
	}

	@Override
	public Double getMaxValueTrafficClassificationInADay(Date dayDate,
			Long idInterface, Long classification) {
		Long HOUR = (long) (3600*1000);
		Date endDate = new Date(dayDate.getTime() + 24 * HOUR);
		
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			
			Criteria cr = session.createCriteria(TrafficClassification.class);
			cr.add(Restrictions.eq("iinterface.idInterface", idInterface))
			.add(Restrictions.ge("date", dayDate))
			.add(Restrictions.le("date", endDate))
			.add(Restrictions.eq("tclasse.refTClasse", classification))
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
	
	static public List<Long> displayObjectsList(List list)
    {
        Iterator iter = list.iterator();
        if (!iter.hasNext())
        {
//            System.out.println("No objects to display.");
            return null;
        }
        
        List<Long> listId = new ArrayList<Long>();
        while (iter.hasNext())
        {
            Object[] obj = (Object[]) iter.next();
//            for (int i=0;i<obj.length;i++)
//            {
            	listId.add((Long)obj[0]);
//            }
        }
        
        return listId;
    }    
	

	@SuppressWarnings("unchecked")
	@Override
	public List<Long> getListOfIdInterfaceInClass() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			
			Criteria cr = session.createCriteria(TrafficClassification.class);
			ProjectionList projList = Projections.projectionList();
			projList.add(Projections.property("iinterface.idInterface"))
			.add(Projections.groupProperty("iinterface.idInterface"));
			
			cr.setProjection(projList);
			
			tx.commit();
			
			List<Long> theList = displayObjectsList(cr.list());
			
			return theList;
		
		} catch (Exception e) {
			if (tx != null) tx.rollback();
			throw e;
			
		} finally {
			session.close();
		}
	}
	
}
