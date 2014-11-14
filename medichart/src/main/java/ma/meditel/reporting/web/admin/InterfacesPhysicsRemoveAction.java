package ma.meditel.reporting.web.admin;

import java.util.ArrayList;
import java.util.List;

import ma.meditel.reporting.entities.InterfacePhysic;
import ma.meditel.reporting.metier.interfaces.IInterfacePhysicMetier;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.opensymphony.xwork2.ActionSupport;

@Component
public class InterfacesPhysicsRemoveAction extends ActionSupport {
	private static final long serialVersionUID = -4645211670660409452L;
	private static final String MESSAGE_REMOVE = "L'interface est bien supprimée.";
	
	@Autowired
	public IInterfacePhysicMetier interfacePhysicMetier;
	public List<InterfacePhysic> listInterfacesPhysics = new ArrayList<>();
	
	public String idInterfacePhysic;
	
	@Override
	public String execute() throws Exception {
		try {
			if (idInterfacePhysic != null) {
				interfacePhysicMetier.removeInterfacePhysic(Long.valueOf(idInterfacePhysic));
				
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
			listInterfacesPhysics = interfacePhysicMetier.getAllInterfacesPhysic();
			
		} catch (Exception e) {}
	}
}
