package ma.meditel.reporting.metier.interfaces;

import ma.meditel.reporting.entities.ChargeCpu;

public interface IChargeCpuMetier {
	public ChargeCpu getChargeCpu(Long idChargeCpu) throws Exception;
}
