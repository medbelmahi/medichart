package ma.meditel.reporting.web.admin;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ma.meditel.reporting.entities.SystemFTP;
import ma.meditel.reporting.metier.interfaces.ISystemFTPMetier;

import com.opensymphony.xwork2.ActionSupport;


@Component
public class SystemFTPMergeAction extends ActionSupport {

	private static final long serialVersionUID = -8236909880689506292L;
	private static final String MESSAGE_ERROR_NO_EMPTY_CHAMP = "il faut remplir tout les champs par des valeur valides";
	@Autowired
	public ISystemFTPMetier ftpServersMetier;
	
	public Long idSystemFTP;
	public String hostAddress;
	public String username;
	public String password;
	public String path;
	public String type;
	public List<SystemFTP> ftpServers;
	
	@Override
	public String execute() throws Exception {
		
		try {
			SystemFTP sftp = new SystemFTP();
			sftp.setIdSystemFTP(idSystemFTP);
			sftp.setHostAddress(hostAddress);
			sftp.setPassword(password);
			sftp.setPath(path);
			sftp.setType(type);
			sftp.setUsername(username);
			
			
			try{
				ftpServersMetier.mergeFTP(sftp);
				addActionMessage("Enregistrement effectué ");
			}catch(Exception e){
				addActionError("erreur d'enregistrement");
			}
			
			ftpServers = ftpServersMetier.getAllSystemFTPs();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return ActionSupport.SUCCESS;
	}
	
	@Override
	public void validate() {
		if(idSystemFTP != null && hostAddress != null && username != null && password != null 
				&& path != null && type != null)
		{
			if(idSystemFTP.equals("") || hostAddress.equals("") || username.equals("") 
					|| password.equals("") || path.equals("") || type.equals(""))
			{
				addActionError(MESSAGE_ERROR_NO_EMPTY_CHAMP);
				return;
			}
		}
		
		
	}
	
}
