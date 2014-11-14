package ma.meditel.reporting.web.admin;

import ma.meditel.reporting.metier.interfaces.INodeMetier;

import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ActionSupport;

public class NodeRemoveAllAction extends ActionSupport {
	private static final long serialVersionUID = 7172091819694478945L;
	private static final String MESSAGE_ERROR = "Une erreur est survenue lors de l'operation";
	private static final String MESSAGE_SUCCESS = "Tous les nodes sont supprimées.";
	
	@Autowired
	public INodeMetier nodeMetier;
	
	public Boolean confirme;
	
	@Override
	public String execute() throws Exception {
		if(confirme == null){
			return ActionSupport.NONE;
		}
		//Apres validation
		try{
			nodeMetier.removeAllNodes();
			addActionMessage(MESSAGE_SUCCESS);
			
		}catch(Exception e){
			addActionError(MESSAGE_ERROR);
		}
		
		return ActionSupport.SUCCESS;
	}
	
	@Override
	public void validate() {}
}
