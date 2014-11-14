package ma.meditel.reporting.dao.implementations;

import java.util.List;

import ma.meditel.reporting.dao.interfaces.IInterfacePhysicDao;
import ma.meditel.reporting.entities.InterfacePhysic;
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
public class InterfacePhysicDaoImpl implements IInterfacePhysicDao {

	@Autowired
	public INodeMetier nodeMetier;
	
	@Override
	public void addInterfacePhysic(InterfacePhysic i, Long idNode) throws Exception {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = null;
		try {
			//verifier si la interface existe deja
			Node n = nodeMetier.getNode(idNode);
			
			InterfacePhysic iOld = getInterfacePhysic(i.getIndexInterfacePhysic(), n.getNodeName());
			if(iOld != null){
				throw new Exception("interface already existe");
			}
			
			tx = session.beginTransaction();
			SQLQuery query = session.createSQLQuery("INSERT INTO INTERFACES_PHYSIC (ID_INTERFACE_PHYSIC, INDEX_INTERFACE_PHYSIC, NAME_INTERFACE_PHYSIC, REF_NODE) "
					+ "VALUES (NULL, :indexInterfacePhysic, :nameInterfacePhysic, :refNode)");

			query.setParameter("indexInterfacePhysic", i.getIndexInterfacePhysic());
			query.setParameter("nameInterfacePhysic", i.getNameInterfacePhysic());
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
	public InterfacePhysic getInterfacePhysic(Long idInterfacePhysic) throws Exception {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			InterfacePhysic i = (InterfacePhysic) session.get(InterfacePhysic.class, idInterfacePhysic);
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
	public InterfacePhysic getInterfacePhysic(String indexInterfacePhysic, String nameNode) throws Exception {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = null;
		try {
			Node node = nodeMetier.getNode(nameNode);
			
			tx = session.beginTransaction();
			Criteria cr = session.createCriteria(InterfacePhysic.class);
			cr.add(Restrictions.eq("indexInterfacePhysic", indexInterfacePhysic))
			  .add(Restrictions.eq("node.idNode", node.getIdNode()));
			
			InterfacePhysic i = (InterfacePhysic) cr.uniqueResult();
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
	public void removeInterfacePhysic(Long idInterfacePhysic) throws Exception {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = null;
		try {
			InterfacePhysic i = getInterfacePhysic(idInterfacePhysic);
			
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

	@Override
	public List<InterfacePhysic> getAllInterfacesPhysic() throws Exception {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			Criteria cr = session.createCriteria(InterfacePhysic.class);
			
			@SuppressWarnings("unchecked")
			List<InterfacePhysic> interfacesPhysic = cr.list();
			for(InterfacePhysic i : interfacesPhysic){
				Hibernate.initialize(i.getNode());
			}
			return interfacesPhysic;
			
		} catch (Exception e) {
			if (tx != null) tx.rollback();
			throw e;
			
		} finally {
			session.close();
		}
	}

	@Override
	public void removeAllInterfacesPhysic() throws Exception {
		List<InterfacePhysic> interfacesPhysic = getAllInterfacesPhysic();
		
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			for(InterfacePhysic i : interfacesPhysic){
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
	public Long getInterfacesPhysicsCount() {
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		Session session = sessionFactory.openSession();
		
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			Criteria cr = session.createCriteria(InterfacePhysic.class).setProjection(Projections.rowCount());
			
			return (Long) cr.uniqueResult();
			
		} catch (Exception e) {
			if (tx != null) tx.rollback(); throw e;
			
		} finally {
			session.close();
		}
	}

}
