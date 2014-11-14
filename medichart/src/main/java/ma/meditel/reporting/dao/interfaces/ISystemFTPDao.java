package ma.meditel.reporting.dao.interfaces;

import java.util.List;

import ma.meditel.reporting.entities.SystemFTP;

public interface ISystemFTPDao {
	public SystemFTP getSystemFTP(Long idSytemFtp);
	public List<SystemFTP> getAllSystemFTPs();
	public void mergeFTP(SystemFTP sftp);
}
