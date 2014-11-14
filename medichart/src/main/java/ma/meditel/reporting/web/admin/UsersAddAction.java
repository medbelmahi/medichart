package ma.meditel.reporting.web.admin;

import java.util.ArrayList;
import java.util.List;

import ma.meditel.reporting.entities.Admin;
import ma.meditel.reporting.entities.Guest;
import ma.meditel.reporting.entities.User;
import ma.meditel.reporting.metier.interfaces.IUserMetier;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.opensymphony.xwork2.ActionSupport;

@Component
public class UsersAddAction extends ActionSupport {
	private static final long serialVersionUID = 8412105040901907296L;
	
	private static final String MESSAGE_INPUT_EMPTY = "Veuillez remplir tous les champs obligatoires.";
	private static final String MESSAGE_EMAIL_EXIST = "L'adresse e-mail saisie est déjà utilisée pour un compte.";
	private static final String MESSAGE_USER_ADDED = "Le Compte est bien créé.";
	
	@Autowired
	public IUserMetier metierUser;
	
	public List<String> rolesList = new ArrayList<>();
	public String defaultSelectedRole = User.ROLE_GUEST;
	public String nom;
	public String prenom;
	public String email;
	public String password;
	public String role;
	
	public UsersAddAction(){
		rolesList.add(User.ROLE_GUEST);
		rolesList.add(User.ROLE_ADMIN);
	}
	
	@Override
	public String execute() throws Exception {
		if(nom == email){
			return ActionSupport.NONE;
		}
		
		//Apres validation
		return ActionSupport.SUCCESS;
	}
	
	@Override
	public void validate() {
		if(nom != null){
			//etape 1
			if(nom.trim().equals("")){
				addActionError(MESSAGE_INPUT_EMPTY);
				return;
			}
			if(prenom.trim().equals("")){
				addActionError(MESSAGE_INPUT_EMPTY);
				return;
			}
			if(email.trim().equals("")){
				addActionError(MESSAGE_INPUT_EMPTY);
				return;
			}
			if(password.trim().equals("")){
				addActionError(MESSAGE_INPUT_EMPTY);
				return;
			}
			//etape 2
			User user = null;
			if(role.equals(User.ROLE_ADMIN)){
				user = new Admin();
			}else if(role.equals(User.ROLE_GUEST)){
				user = new Guest();
			}
			
			user.setNom(nom);
			user.setPrenom(prenom);
			user.setEmail(email);
			user.setPassword(password);
			
			try {
				metierUser.addUser(user);
				addActionMessage(MESSAGE_USER_ADDED + " ( " + user.getFullName() + " ) ");
			} catch (Exception e) {
				addActionError(MESSAGE_EMAIL_EXIST + " ( " + user.getEmail() + " ) ");
			}
		}
	}
}
