package ma.meditel.reporting.metier.implementations;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ma.meditel.reporting.dao.interfaces.IChargeCpuDao;
import ma.meditel.reporting.entities.ChargeCpu;
import ma.meditel.reporting.metier.interfaces.IChargeCpuMetier;

@Transactional
@Service
public class ChargeCpuMetierImpl implements IChargeCpuMetier {

	private IChargeCpuDao dao;
	
	@Override
	public ChargeCpu getChargeCpu(Long idChargeCpu) throws Exception {
		return dao.getChargeCpu(idChargeCpu);
	}

	public IChargeCpuDao getDao() {
		return dao;
	}

	public void setDao(IChargeCpuDao dao) {
		this.dao = dao;
	}

}
