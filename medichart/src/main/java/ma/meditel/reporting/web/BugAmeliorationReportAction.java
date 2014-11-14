package ma.meditel.reporting.web;

import java.util.Date;

import ma.meditel.reporting.entities.BugAmelioration;
import ma.meditel.reporting.metier.interfaces.IBugAmeliorationMetier;
import ma.meditel.reporting.util.SessionHelper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.opensymphony.xwork2.ActionSupport;

@Component
public class BugAmeliorationReportAction extends ActionSupport {
	private static final long serialVersionUID = -5243417893521721713L;
	private static final String MESSAGE_INPUT = "Veuillez remplir tous les champs obligatoires.";
	private static final String MESSAGE_SUCCESS = "Votre proposition est bien enregistrée, Merci pour votre temps.";
	@Autowired
	public IBugAmeliorationMetier bugAmeliorationMetier;
	
	public String objet;
	public String sujet;
	
	@Override
	public String execute() throws Exception {
		if(objet==null || sujet==null){
			return ActionSupport.NONE;
		}else {
			BugAmelioration ba = new BugAmelioration();
			
			ba.setDate(new Date());
			ba.setObjet(sujet);
			ba.setSujet(objet);
			ba.setUserName(SessionHelper.getUser().getFullName());
			
			bugAmeliorationMetier.addBugAmelioration(ba);
		}
		
		addActionMessage(MESSAGE_SUCCESS);
		
		return ActionSupport.SUCCESS;
	}
	
	@Override
	public void validate() {
		if(objet!=null || sujet!=null){
			if(objet.isEmpty() || sujet.isEmpty()){
				addActionError(MESSAGE_INPUT);
			}
		}
	}
}
