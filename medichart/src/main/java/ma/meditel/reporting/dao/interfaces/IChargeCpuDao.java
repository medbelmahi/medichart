package ma.meditel.reporting.dao.interfaces;

import ma.meditel.reporting.entities.ChargeCpu;

public interface IChargeCpuDao {
	public ChargeCpu getChargeCpu(Long idChargeCpu) throws Exception;
}
