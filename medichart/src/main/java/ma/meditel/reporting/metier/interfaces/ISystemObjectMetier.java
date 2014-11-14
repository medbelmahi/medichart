package ma.meditel.reporting.metier.interfaces;

import ma.meditel.reporting.entities.SystemObject;

public interface ISystemObjectMetier {
	public void addSystemObject(SystemObject s) throws Exception;
	public SystemObject getSystemObject(Long idSytem) throws Exception;
	public void MergeSystemObject(SystemObject s) throws Exception;
}
