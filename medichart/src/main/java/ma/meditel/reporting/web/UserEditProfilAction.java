package ma.meditel.reporting.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ma.meditel.reporting.entities.User;
import ma.meditel.reporting.metier.interfaces.IUserMetier;
import ma.meditel.reporting.util.PasswordHelper;
import ma.meditel.reporting.util.SessionHelper;

import com.opensymphony.xwork2.ActionSupport;

@Component
public class UserEditProfilAction extends ActionSupport {
	private static final long serialVersionUID = 1L;
	
	@Autowired
	public IUserMetier userMetier;
	
	private static final String MESSAGE_INPUT = "Veuillez remplir tous les champs obligatoires.";
	private static final String MESSAGE_SUCCESS = "Les changements sont bien enregistrés.";

	public String nom;
	public String prenom;
	public String email;
	public String password;
	public String role;
	
	@Override
	public String execute() throws Exception {
		if(nom == null || prenom == null ||  password == null){
			User user = SessionHelper.getUser();
			
			nom = user.getNom();
			prenom = user.getPrenom();
			password = user.getPassword();
			email = user.getEmail();
			role = user.getRoleDescription();
			
			return ActionSupport.NONE;
			
		}else {
			User user = SessionHelper.getUser();
			
			user.setNom(nom);
			user.setPrenom(prenom);
			user.setPassword(PasswordHelper.hashingString(password));
			
			email = user.getEmail();
			password = user.getPassword();
			
			userMetier.mergeUser(user);
			
			addActionMessage(MESSAGE_SUCCESS);
		}
		
		return ActionSupport.SUCCESS;
	}
	
	@Override
	public void validate() {
		if(nom != null && prenom != null && password != null){
			if(nom.isEmpty() || prenom.isEmpty() || password.isEmpty()){
				addActionError(MESSAGE_INPUT);
			}
		}
	}
}
