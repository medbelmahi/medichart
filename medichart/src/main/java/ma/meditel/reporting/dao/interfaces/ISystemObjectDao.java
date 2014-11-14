package ma.meditel.reporting.dao.interfaces;

import ma.meditel.reporting.entities.SystemObject;

public interface ISystemObjectDao {
	public void addSystemObject(SystemObject s) throws Exception;
	public SystemObject getSystemObject(Long idSytem) throws Exception;
	public void MergeSystemObject(SystemObject s) throws Exception;
}
