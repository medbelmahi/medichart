package ma.meditel.reporting.web.admin;

import java.util.List;

import ma.meditel.reporting.entities.User;
import ma.meditel.reporting.metier.interfaces.IUserMetier;
import ma.meditel.reporting.util.SessionHelper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.opensymphony.xwork2.ActionSupport;

@Component
public class UsersListAction extends ActionSupport {
	private static final long serialVersionUID = 8040274770371838122L;
	
	@Autowired
	public IUserMetier userMetier;
	
	public List<User> users;
	
	@Override
	public String execute(){
		try {
			users = userMetier.getAllUsers(SessionHelper.getUser());
			
		} catch (Exception e) {}
		
		return ActionSupport.NONE;
	}
}
