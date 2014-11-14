package ma.meditel.reporting.dao.implementations;

import java.util.List;

import ma.meditel.reporting.dao.interfaces.INodeDao;
import ma.meditel.reporting.entities.Node;
import ma.meditel.reporting.metier.interfaces.IInterfacePhysicMetier;
import ma.meditel.reporting.util.HibernateUtil;

import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class NodeDaoImpl implements INodeDao {
	
	@Autowired
	public IInterfacePhysicMetier interfacePhysicMetier;
	
	@Override
	public void addNode(Node node) throws Exception {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			session.save(node);
			tx.commit();
			
		} catch (Exception e) {
			if (tx != null) tx.rollback();
			throw e;
			
		} finally {
			session.close();
		}
	}

	@Override
	public void removeNode(Long idNode) throws Exception {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = null;
		try {
			Node node = getNode(idNode);
			
			tx = session.beginTransaction();
			session.delete(node);
			tx.commit();
			
		} catch (Exception e) {
			if (tx != null) tx.rollback();
			throw e;
			
		} finally {
			session.close();
		}
	}

	@Override
	public Node getNode(Long idNode) throws Exception {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			Node n = (Node) session.get(Node.class, idNode);
			return n;
			
		} catch (Exception e) {
			if (tx != null) tx.rollback();
			throw e;
			
		} finally {
			session.close();
		}
	}

	@Override
	public Node getNode(String nameNode) throws Exception {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			Criteria c = session.createCriteria(Node.class)
					.add(Restrictions.eq("nodeName", nameNode));
			
			return (Node) c.uniqueResult();
			
		} catch (Exception e) {
			if (tx != null) tx.rollback();
			throw e;
			
		} finally {
			session.close();
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Node> getAllNodes() throws Exception {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			Criteria c = session.createCriteria(Node.class);			
			return c.list();
			
		} catch (Exception e) {
			if (tx != null) tx.rollback();
			throw e;
			
		} finally {
			session.close();
		}
	}

	@Override
	public Node getNodeWithCollectionInitialize(Long idNode) throws Exception {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			Node n = (Node) session.get(Node.class, idNode);
			Hibernate.initialize(n.getInterfaces());
			return n;
			
		} catch (Exception e) {
			if (tx != null) tx.rollback();
			throw e;
			
		} finally {
			session.close();
		}
	}

	@Override
	public Node getNodeWithCollectionInitialize(String nameNode) throws Exception {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			Criteria c = session.createCriteria(Node.class)
					.add(Restrictions.eq("nodeName", nameNode));
			Node n = (Node) c.uniqueResult();
			Hibernate.initialize(n.getInterfaces());
			return n;
			
		} catch (Exception e) {
			if (tx != null) tx.rollback();
			throw e;
			
		} finally {
			session.close();
		}
	}

	@Override
	public void removeAllNodes() throws Exception {
		//remove first all interface physic
		interfacePhysicMetier.removeAllInterfacesPhysic();
		
		List<Node> nodes = getAllNodes();
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			for(Node node : nodes){
				session.delete(node);
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
	public Long getNodesCount() {
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		Session session = sessionFactory.openSession();
		
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			Criteria cr = session.createCriteria(Node.class).setProjection(Projections.rowCount());
			
			return (Long) cr.uniqueResult();
			
		} catch (Exception e) {
			if (tx != null) tx.rollback(); throw e;
			
		} finally {
			session.close();
		}
	}

}
 