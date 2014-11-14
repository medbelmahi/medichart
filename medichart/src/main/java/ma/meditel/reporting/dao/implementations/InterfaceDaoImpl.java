package ma.meditel.reporting.dao.implementations;

import java.util.List;

import ma.meditel.reporting.dao.interfaces.IInterfaceDao;
import ma.meditel.reporting.entities.Interface;
import ma.meditel.reporting.entities.Node;
import ma.meditel.reporting.metier.interfaces.INodeMetier;
import ma.meditel.reporting.util.HibernateUtil;

import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class InterfaceDaoImpl implements IInterfaceDao {

	@Autowired
	public INodeMetier nodeMetier;
	
	@Override
	public void addInteface(Interface i, Long idNode) throws Exception {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = null;
		try {
			//verifier si la node existe deja
			Node n = nodeMetier.getNode(idNode);
			Interface iOld = getInterface(i.getIfIndex(), n.getNodeName());
			if(iOld != null){
				throw new Exception("interface already existe");
			}
			tx = session.beginTransaction();
			SQLQuery query = session.createSQLQuery("INSERT INTO INTERFACES (ID_INTERFACE, IF_ALIAS, IF_DESCR, IF_INDEX, IF_NAME, IF_SPEED, IF_TYPE, REF_NODE) "
					+ "VALUES (NULL, :ifAlias, :ifDescr, :ifIndex, :ifName, :ifSpeed, :ifType, :refNode)");

			query.setParameter("ifAlias", i.getIfAlias());
			query.setParameter("ifDescr", i.getIfDescr());
			query.setParameter("ifIndex", i.getIfIndex());
			query.setParameter("ifName", i.getIfName());
			query.setParameter("ifSpeed", i.getIfSpeed());
			query.setParameter("ifType", i.getIfType());
			query.setParameter("refNode", idNode);
			query.executeUpdate();
			
			tx.commit();
			
		} catch (Exception e) {
			if (tx != null) tx.rollback();
			throw e;
			
		} finally {
			session.close();
		}
	}

	@Override
	public Interface getInterface(Long idInterface) throws Exception {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			Interface i = (Interface) session.get(Interface.class, idInterface);
			if(i != null){
				Hibernate.initialize(i.getNode());
			}
			return i;
			
		} catch (Exception e) {
			if (tx != null) tx.rollback();
			throw e;
			
		} finally {
			session.close();
		}
	}

	@Override
	public Interface getInterfaceWithCollectionIntialize(Long idInterface) throws Exception {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			Interface i = (Interface) session.get(Interface.class, idInterface);
			if(i != null){
				Hibernate.initialize(i.getTrafics());
				//Hibernate.initialize(i.getTraficsClassification());//a avoir!!!!!!!!
				Hibernate.initialize(i.getNode());
			}
			
			return i;
			
		} catch (Exception e) {
			if (tx != null) tx.rollback();
			throw e;
			
		} finally {
			session.close();
		}
	}

	@Override
	public Interface getInterface(String indexInterface, String nameNode) throws Exception {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = null;
		try {
			Node node = nodeMetier.getNode(nameNode);
			tx = session.beginTransaction();
			Criteria cr = session.createCriteria(Interface.class);
			cr.add(Restrictions.eq("ifIndex", indexInterface))
			  .add(Restrictions.eq("node.idNode", node.getIdNode()));
			
			Interface i = (Interface) cr.uniqueResult();
			if(i != null){
				Hibernate.initialize(i.getNode());
			}
			return i;
			
		} catch (Exception e) {
			if (tx != null) tx.rollback();
			throw e;
			
		} finally {
			session.close();
		}
	}

	@Override
	public Interface getInterfaceWithCollectionIntialize(String indexInterface, String nameNode) throws Exception {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = null;
		try {
			Node node = nodeMetier.getNode(nameNode);
			tx = session.beginTransaction();
			Criteria cr = session.createCriteria(Interface.class);
			cr.add(Restrictions.eq("ifIndex", indexInterface))
			  .add(Restrictions.eq("node.idNode", node.getIdNode()));
			
			Interface i = (Interface) cr.uniqueResult();
			if(i != null){
				Hibernate.initialize(i.getTrafics());
				Hibernate.initialize(i.getNode());
			}
			return i;
			
		} catch (Exception e) {
			if (tx != null) tx.rollback();
			throw e;
			
		} finally {
			session.close();
		}
	}

	@Override
	public void removeInterface(Long idInterface) throws Exception {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = null;
		try {
			Interface i = getInterface(idInterface);
			
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
	public List<Interface> getAllInterfaces() throws Exception {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			Criteria cr = session.createCriteria(Interface.class);
			
			List<Interface> interfaces = cr.list();
			for(Interface i : interfaces){
				Hibernate.initialize(i.getNode());
			}
			return interfaces;
			
		} catch (Exception e) {
			if (tx != null) tx.rollback();
			throw e;
			
		} finally {
			session.close();
		}
	}

	@Override
	public void removeAllInterfaces() throws Exception {
		List<Interface> interfaces = getAllInterfaces();
		
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			for(Interface i : interfaces){
				session.delete(i);
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
	public List<Interface> getAllInterfacesActivated() throws Exception {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			Criteria cr = session.createCriteria(Interface.class).add(Restrictions.eq("activated", true));
			
			@SuppressWarnings("unchecked")
			List<Interface> interfaces = cr.list();
			
			
			for(Interface i : interfaces){
				Hibernate.initialize(i.getNode());
			}
			
			tx.commit();
			
			return interfaces;
			
		} catch (Exception e) {
			if (tx != null) tx.rollback();
			throw e;
			
		} finally {
			session.close();
		}
	}
	
	@Override
	public Long getInterfacesLogicsCount() {
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		Session session = sessionFactory.openSession();
		
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			Criteria cr = session.createCriteria(Interface.class).setProjection(Projections.rowCount());
			
			return (Long) cr.uniqueResult();
			
		} catch (Exception e) {
			if (tx != null) tx.rollback(); throw e;
			
		} finally {
			session.close();
		}
	}

	@Override
	public void margeInterface(Interface inter) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			session.merge(inter);
			tx.commit();
			
		} catch (Exception e) {
			if (tx != null) tx.rollback(); throw e;
			
		} finally {
			session.close();
		}
		
	}

}
