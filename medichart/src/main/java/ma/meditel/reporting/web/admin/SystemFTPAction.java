package ma.meditel.reporting.web.admin;

import java.util.List;

import ma.meditel.reporting.entities.SystemFTP;
import ma.meditel.reporting.metier.interfaces.ISystemFTPMetier;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.opensymphony.xwork2.ActionSupport;

@Component
public class SystemFTPAction extends ActionSupport {

	private static final long serialVersionUID = -3511313319651112766L;
	
	
	@Autowired
	public ISystemFTPMetier ftpServersMetier;

	
	public List<SystemFTP> ftpServers;
	
	@Override
	public String execute() throws Exception {
		
		ftpServers = ftpServersMetier.getAllSystemFTPs();
		
		//System.out.println(ftpServers.size());
		
		return ActionSupport.NONE;
	}
	
	@Override
	public void validate() {
		
		
	}
}
