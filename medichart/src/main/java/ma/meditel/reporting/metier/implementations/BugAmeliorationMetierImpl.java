package ma.meditel.reporting.metier.implementations;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ma.meditel.reporting.dao.interfaces.IBugAmeliorationDao;
import ma.meditel.reporting.entities.BugAmelioration;
import ma.meditel.reporting.metier.interfaces.IBugAmeliorationMetier;

@Transactional
@Service
public class BugAmeliorationMetierImpl implements IBugAmeliorationMetier {

	private IBugAmeliorationDao dao;
	
	@Override
	public void addBugAmelioration(BugAmelioration ba) {
		dao.addBugAmelioration(ba);
	}

	public IBugAmeliorationDao getDao() {
		return dao;
	}

	public void setDao(IBugAmeliorationDao dao) {
		this.dao = dao;
	}

}
