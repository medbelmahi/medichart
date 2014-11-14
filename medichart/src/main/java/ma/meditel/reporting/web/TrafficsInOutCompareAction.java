package ma.meditel.reporting.web;

import java.util.List;
import java.util.Map;

import ma.meditel.reporting.entities.Interface;
import ma.meditel.reporting.metier.interfaces.IInterfaceMetier;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.opensymphony.xwork2.ActionSupport;

@Component
public class TrafficsInOutCompareAction extends ActionSupport {
	private static final long serialVersionUID = 5284488446473819812L;
	private static final String MESSAGE_ERROR_NO_INTERFACES = "Aucune interface ni disponible pour la representation.";
	
	@Autowired
	public IInterfaceMetier interfaceMetier;
	
	public Map<String, List<Interface>> mapInterfaces;//multi select 
	
	
	@Override
	public String execute() {
		try {
			mapInterfaces = interfaceMetier.getAllInterfacesGroupeByNodenameActivated();
			
		} catch (Exception e) {
			addActionError(MESSAGE_ERROR_NO_INTERFACES);
		}
		return ActionSupport.SUCCESS;
	}
	
	@Override
	public void validate() {
		
	}
}
