package ma.meditel.reporting.dao.implementations;

import ma.meditel.reporting.dao.interfaces.IChargeCpuDao;
import ma.meditel.reporting.entities.ChargeCpu;
import ma.meditel.reporting.util.HibernateUtil;

import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class ChargeCpuDaoImpl implements IChargeCpuDao {

	@Override
	public ChargeCpu getChargeCpu(Long idChargeCpu) throws Exception {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			ChargeCpu cp = (ChargeCpu) session.get(ChargeCpu.class, idChargeCpu);
			if(cp != null){
				Hibernate.initialize(cp.getInterfacePhysic());
			}
			tx.commit();
			return cp;
			
		} catch (Exception e) {
			if (tx != null) tx.rollback();
			throw e;
			
		} finally {
			session.close();
		}
	}

}
