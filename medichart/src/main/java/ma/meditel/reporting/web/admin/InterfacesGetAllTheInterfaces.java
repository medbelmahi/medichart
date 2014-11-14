package ma.meditel.reporting.web.admin;

import java.util.List;

import ma.meditel.reporting.entities.Interface;
import ma.meditel.reporting.metier.interfaces.IInterfaceMetier;

import org.apache.velocity.exception.ParseErrorException;
import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ActionSupport;

public class InterfacesGetAllTheInterfaces extends ActionSupport {
	
	private static final long serialVersionUID = 7541893162642722753L;
	
	private static final String MESSAGE_ERROR = "Une erreur est survenue lors de l'operation";
	private static final String MESSAGE_SUCCESS = "Tous les interfaces sont supprimées.";
	
	@Autowired
	public IInterfaceMetier interfaceMetier;
	
	public List<Interface> interfaces;
	
	public String active;
	public String idInterface;
	
	public Boolean confirme;
	public Long[] interfaceIds;
	public String action;
	@Override
	public String execute() throws Exception {
		Interface inter;
		if(interfaceIds != null && action != null)
		{
			for(int i =0; i < interfaceIds.length ; i++)
			{
				inter = interfaceMetier.getInterface(interfaceIds[i]);
				if(action.compareTo("activer") == 0)
				{
					inter.setActivated(true);
				}else 
				{
					inter.setActivated(true);
				}
				
				interfaceMetier.margeInterface(inter);
			}
			
		}else if(active != null && idInterface!=null)
		{
			inter = interfaceMetier.getInterface(Long.parseLong(idInterface));
			if(active.compareTo("oui") == 0)
			{
				inter.setActivated(false);
				
			}else
			{
				inter.setActivated(true);
			}
			
			interfaceMetier.margeInterface(inter);
		}
		
		interfaces = interfaceMetier.getAllInterfaces();
		
		return ActionSupport.SUCCESS;
	}
	
	@Override
	public void validate() {
		
		if(idInterface !=null)
		{
			try
			{
				Integer.parseInt(idInterface);
			}catch(ParseErrorException ep)
			{
				addActionError("ParseErrorException");
			}
		}
		
		
	}
}
