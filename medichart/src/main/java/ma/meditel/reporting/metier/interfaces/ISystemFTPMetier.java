package ma.meditel.reporting.metier.interfaces;

import java.util.List;

import ma.meditel.reporting.entities.SystemFTP;

public interface ISystemFTPMetier {
	public SystemFTP getSystemFTP(Long idSytemFtp);
	public void mergeFTP(SystemFTP sftp);
	public List<SystemFTP> getAllSystemFTPs();
}
