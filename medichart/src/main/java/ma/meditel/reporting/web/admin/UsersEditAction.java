package ma.meditel.reporting.web.admin;

import java.util.ArrayList;
import java.util.List;

import ma.meditel.reporting.entities.Admin;
import ma.meditel.reporting.entities.Guest;
import ma.meditel.reporting.entities.User;
import ma.meditel.reporting.metier.interfaces.IUserMetier;
import ma.meditel.reporting.util.PasswordHelper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.opensymphony.xwork2.ActionSupport;

@Component
public class UsersEditAction extends ActionSupport {
	private static final long serialVersionUID = 4083719529700654041L;
	
	@Autowired
	public IUserMetier userMetier;
	public List<String> rolesList = new ArrayList<>();
	public String defaultSelectedRole = User.ROLE_GUEST;
	
	public String idUser;
	public String nom;
	public String prenom;
	public String email;
	public String password;
	public String role;
	
	public UsersEditAction(){
		rolesList.add(User.ROLE_GUEST);
		rolesList.add(User.ROLE_ADMIN);
		
		defaultSelectedRole = role;
	}
	
	@Override
	public String execute(){
		if(nom == null){
			addActionError("404!");
			return ActionSupport.NONE;
		}
		
		//Apres validation
		User user;
		if(role.equals(User.ROLE_ADMIN)){
			user = new Admin();
		}else {
			user = new Guest();
		}
		user.setIdUser(Long.valueOf(idUser));
		user.setNom(nom);
		user.setPrenom(prenom);
		user.setEmail(email);
		user.setPassword(PasswordHelper.hashingString(password));
		
		try {
			userMetier.mergeUser(user);
			addActionMessage("Les modifications sont bien effectuées");
		} catch (Exception e) {}
		
		return ActionSupport.SUCCESS;
	}
	
	@Override
	public void validate() {
		if(nom != null){
			if(nom.trim().equals("")){
				addActionError("Vieullez remplir tous les champs");
				return;
			}
			if(prenom.trim().equals("")){
				addActionError("Vieullez remplir tous les champs");
				return;
			}
			if(email.trim().equals("")){
				addActionError("Vieullez remplir tous les champs");
				return;
			}
			if(password.trim().equals("")){
				addActionError("Vieullez remplir tous les champs");
				return;
			}
		}
	}
}
