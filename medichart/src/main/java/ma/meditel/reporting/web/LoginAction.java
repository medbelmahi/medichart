package ma.meditel.reporting.web;

import ma.meditel.reporting.entities.GeneraleStatistics;
import ma.meditel.reporting.entities.User;
import ma.meditel.reporting.metier.interfaces.IGeneralStatisticsMetier;
import ma.meditel.reporting.metier.interfaces.IUserMetier;
import ma.meditel.reporting.util.PasswordHelper;
import ma.meditel.reporting.util.SessionHelper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.opensymphony.xwork2.ActionSupport;

@Component
public class LoginAction extends ActionSupport {
	private static final long serialVersionUID = -1940027891282688455L;
	public String email;
	public String password;
	
	private User user;
	
	@Autowired
	public IUserMetier userMetier;
	
	@Autowired
	public IGeneralStatisticsMetier generalStatisticsMetier;
	
	public String execute(){
		if(email == null || password == null){
			return ActionSupport.INPUT;
		}
		//Apres validation
		SessionHelper.putUser(user);
		
		//Put generale Statistics
		GeneraleStatistics gs = new GeneraleStatistics();
		gs.setSystemState(generalStatisticsMetier.getSystemStatus());
		gs.setUsersNombre(generalStatisticsMetier.getUsersNombre());
		gs.setNodesNombre(generalStatisticsMetier.getNodesNombre());
		gs.setInterfacesLogicNombre(generalStatisticsMetier.getInterfacesLogicNombre());
		gs.setInterfacesPhysicNombre(generalStatisticsMetier.getInterfacesPhysicNombre());
		
		SessionHelper.putGeneralesStatistics(gs);
		return ActionSupport.SUCCESS;
	}
	
	public String logout(){
		SessionHelper.removeUser();
		SessionHelper.removeGeneralesStatistics();
		return ActionSupport.NONE;
	}

	public void validate() {
		if(email != null && password != null){
			if (email.trim().equals("") || password.trim().equals("")) {
				addActionError("Vieullez remplir tous les champs");
				
			}else {
				try {
					user = userMetier.getUser(email, PasswordHelper.hashingString(password));
					if(user == null){
						addActionError("Echec de connexion : Email ou Mot de passé incorrect.");
					}
				} catch (Exception e) {}
			}
		}
	}
}
