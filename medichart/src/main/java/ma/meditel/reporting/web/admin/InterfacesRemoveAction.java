package ma.meditel.reporting.web.admin;

import java.util.ArrayList;
import java.util.List;

import ma.meditel.reporting.entities.Interface;
import ma.meditel.reporting.metier.interfaces.IInterfaceMetier;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.opensymphony.xwork2.ActionSupport;

@Component
public class InterfacesRemoveAction extends ActionSupport {
	private static final long serialVersionUID = -4645211670660409452L;
	private static final String MESSAGE_REMOVE = "L'interface est bien supprimée.";
	
	@Autowired
	public IInterfaceMetier interfaceMetier;
	public List<Interface> listInterfaces = new ArrayList<>();
	
	public String idInterface;
	
	@Override
	public String execute() throws Exception {
		try {
			if (idInterface != null) {
				interfaceMetier.removeInterface(Long.valueOf(idInterface));
				
				addActionMessage(MESSAGE_REMOVE);
			}
			//remplir la list avec les noms des nodes
			FillListInterfaces();
			
		} catch (Exception e) {}
		
		return ActionSupport.SUCCESS;
	}
	
	@Override
	public void validate() {}
	
	//
	private void FillListInterfaces(){
		try {
			listInterfaces = interfaceMetier.getAllInterfaces();
			
		} catch (Exception e) {}
	}
}
