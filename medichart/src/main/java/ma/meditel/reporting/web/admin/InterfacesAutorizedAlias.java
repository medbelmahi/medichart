package ma.meditel.reporting.web.admin;

import java.util.List;

import ma.meditel.reporting.entities.InterfaceAutorizedAlias;
import ma.meditel.reporting.metier.interfaces.IInterfaceAutorizedAliasMetier;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.opensymphony.xwork2.ActionSupport;

@Component
public class InterfacesAutorizedAlias extends ActionSupport {
	private static final long serialVersionUID = -150101035265756912L;
	
	@Autowired
	public IInterfaceAutorizedAliasMetier interfaceAutorizedAliasMetier;

	public List<InterfaceAutorizedAlias> listAlias;
	public String IdAlias;//remove
	public String preAlias;//add
	
	@Override
	public String execute() throws Exception {
		return super.execute();
	}
	
	public String remove(){
		try{
			interfaceAutorizedAliasMetier.removeInterfaceAutorizedAlias(Long.valueOf(IdAlias));
		}catch(Exception e){}
		return ActionSupport.SUCCESS;
	}
	
	public String add(){
		try{
			interfaceAutorizedAliasMetier.addInterfaceAutorizedAlias(new InterfaceAutorizedAlias(preAlias));
		}catch(Exception e){}
		return ActionSupport.SUCCESS;
	}
	
	@Override
	public void validate() {
		listAlias = interfaceAutorizedAliasMetier.getAllInterfaceAutorizedAlias();
	}
}	
