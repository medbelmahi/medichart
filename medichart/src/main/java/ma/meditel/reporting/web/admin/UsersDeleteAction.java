package ma.meditel.reporting.web.admin;

import ma.meditel.reporting.metier.interfaces.IUserMetier;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.opensymphony.xwork2.ActionSupport;

@Component
public class UsersDeleteAction extends ActionSupport {
	private static final long serialVersionUID = -5340939883633465129L;
	
	@Autowired
	public IUserMetier userMetier;
	
	public String idUser;
	
	@Override
	public String execute() throws Exception {
		//Apres validation 
		return ActionSupport.SUCCESS;
	}
	
	@Override
	public void validate() {
		if(idUser == null){
			addActionError("idUser invalide");
			
		}else {
			Long idUserL;
			try{
				idUserL = Long.valueOf(idUser);
				userMetier.removeUser(idUserL);
				addActionMessage("User bien supprimer de la base de données");
				
			}catch(Exception e){
				addActionError("idUser invalide");
			}
		}
	}
}
