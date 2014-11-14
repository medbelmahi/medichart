package ma.meditel.reporting.web.admin;

import java.util.ArrayList;
import java.util.List;

import ma.meditel.reporting.entities.User;
import ma.meditel.reporting.metier.interfaces.IUserMetier;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.opensymphony.xwork2.ActionSupport;

@Component
public class UsersInfoAction extends ActionSupport {
	private static final long serialVersionUID = 4083719529700654041L;
	
	@Autowired
	public IUserMetier userMetier;
	
	
	public User user;
	public List<String> rolesList = new ArrayList<>();
	public String defaultSelectedRole;
	
	public String idUser;
	public String nom;
	public String prenom;
	public String email;
	public String password;
	public String role;
	
	public UsersInfoAction(){
		rolesList.add(User.ROLE_GUEST);
		rolesList.add(User.ROLE_ADMIN);
	}
	
	@Override
	public String execute() throws Exception {
		
		//Apres validation
		nom = user.getNom();
		prenom = user.getPrenom();
		email = user.getEmail();
		password = user.getPassword();
		defaultSelectedRole = user.getRole();
		
		return ActionSupport.SUCCESS;
	}
	
	@Override
	public void validate() {
		//etape 1
		if(idUser == null){
			addActionError("id Invalide");
			return;
		}
		//etape 2
		Long idUserL;
		try{
			idUserL = Long.parseLong(idUser);
			
		}catch(Exception e){
			addActionError("id Invalide");
			return;
		}
		//etape 3
		try {
			user = userMetier.getUser(idUserL);
			if(user == null){
				throw new Exception();
			}
		} catch (Exception e) {
			addActionError("id Invalide");
			return;
		}
	}
}
