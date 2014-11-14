package ma.meditel.reporting.metier.implementations;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ma.meditel.reporting.dao.interfaces.ISystemObjectDao;
import ma.meditel.reporting.entities.SystemObject;
import ma.meditel.reporting.metier.interfaces.ISystemObjectMetier;

@Transactional
@Service
public class SystemObjectMetierImpl implements ISystemObjectMetier {

	private ISystemObjectDao dao;
	
	@Override
	public void addSystemObject(SystemObject s) throws Exception {
		dao.addSystemObject(s);
	}

	@Override
	public SystemObject getSystemObject(Long idSytem) throws Exception {
		return dao.getSystemObject(idSytem);
	}

	@Override
	public void MergeSystemObject(SystemObject s) throws Exception {
		dao.MergeSystemObject(s);
	}

	public ISystemObjectDao getDao() {
		return dao;
	}

	public void setDao(ISystemObjectDao dao) {
		this.dao = dao;
	}

}
