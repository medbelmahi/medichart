package ma.meditel.reporting.dao.implementations;

import java.util.List;

import ma.meditel.reporting.dao.interfaces.IUserDao;
import ma.meditel.reporting.entities.User;
import ma.meditel.reporting.util.HibernateUtil;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

public class UserDaoImpl implements IUserDao {

	@Override
	public void addUser(User u) throws Exception {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			session.save(u);
			tx.commit();
			
		} catch (Exception e) {
			if (tx != null) tx.rollback(); throw e;
			
		} finally {
			session.close();
		}
	}

	@Override
	public User getUser(Long idUser) throws Exception {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			return (User) session.get(User.class, idUser);
			
		} catch (Exception e) {
			if (tx != null) tx.rollback(); throw e;
			
		} finally {
			session.close();
		}
	}

	@Override
	public User getUser(String email, String password) throws Exception {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			Criteria c = session.createCriteria(User.class)
					.add(Restrictions.eq("email", email))
					.add(Restrictions.eq("password", password));
			
			User user = (User) c.uniqueResult();
			//tx.commit();
			
			return user;
			
		} catch (Exception e) {
			if (tx != null) tx.rollback(); throw e;
			
		} finally {
			session.close();
		}
	}

	@Override
	public void mergeUser(User u) throws Exception {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			session.merge(u);
			tx.commit();
			
		} catch (Exception e) {
			if (tx != null) tx.rollback(); throw e;
			
		} finally {
			session.close();
		}
	}

	@Override
	public void removeUser(Long idUser) throws Exception {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = null;
		try {
			User u = getUser(idUser);
			
			tx = session.beginTransaction();
			session.delete(u);
			tx.commit();
			
		} catch (Exception e) {
			if (tx != null) tx.rollback(); throw e;
			
		} finally {
			session.close();
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<User> getAllUsers(User user) throws Exception {
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		Session session = sessionFactory.openSession();
		
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			Criteria c = session.createCriteria(User.class).add(Restrictions.ne("idUser", user.getIdUser()));
			List<User> users = c.list();
			
			return users;
			
		} catch (Exception e) {
			if (tx != null) tx.rollback(); throw e;
			
		} finally {
			session.close();
		}
	}

	@Override
	public Long getUsersCount() {
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		Session session = sessionFactory.openSession();
		
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			Criteria cr = session.createCriteria(User.class).setProjection(Projections.rowCount());
			
			return (Long) cr.uniqueResult();
			
		} catch (Exception e) {
			if (tx != null) tx.rollback(); throw e;
			
		} finally {
			session.close();
		}
	}
	
}
