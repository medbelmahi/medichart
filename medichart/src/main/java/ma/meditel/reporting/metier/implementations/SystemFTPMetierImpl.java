package ma.meditel.reporting.metier.implementations;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ma.meditel.reporting.dao.interfaces.ISystemFTPDao;
import ma.meditel.reporting.entities.SystemFTP;
import ma.meditel.reporting.metier.interfaces.ISystemFTPMetier;

@Transactional
@Service
public class SystemFTPMetierImpl implements ISystemFTPMetier {
	
	private ISystemFTPDao dao;

	@Override
	public SystemFTP getSystemFTP(Long idSytemFtp) {
		return dao.getSystemFTP(idSytemFtp);
	}

	@Override
	public void mergeFTP(SystemFTP sftp) {
		dao.mergeFTP(sftp);
	}
	
	@Override
	public List<SystemFTP> getAllSystemFTPs() {
		return dao.getAllSystemFTPs();
	}

	public ISystemFTPDao getDao() {
		return dao;
	}

	public void setDao(ISystemFTPDao dao) {
		this.dao = dao;
	}

}
