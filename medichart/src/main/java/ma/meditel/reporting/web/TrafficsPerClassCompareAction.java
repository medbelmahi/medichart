package ma.meditel.reporting.web;

import java.util.List;
import java.util.Map;

import ma.meditel.reporting.entities.Interface;
import ma.meditel.reporting.metier.interfaces.IInterfaceMetier;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.opensymphony.xwork2.ActionSupport;

@Component
public class TrafficsPerClassCompareAction extends ActionSupport {
	private static final long serialVersionUID = 5284488446473819812L;
	//private static final String MESSAGE_ERROR_INPUT = "Veuillez remplir tous les champs.";
	private static final String MESSAGE_ERROR_NO_INTERFACES = "Aucune interface ni disponible pour la representation.";
	//private static final String MESSAGE_NO_DATA = "Echec de la représentation: Données insuffisantes.";
	
	
	@Autowired
	public IInterfaceMetier interfaceMetier;
	
	public Map<String, List<Interface>> mapInterfaces;//multi select 
	
	
	@Override
	public String execute() {
		try {
			mapInterfaces = interfaceMetier.getAllInterfacesGroupeByNodenameActivated_IsClass();
		} catch (Exception e) {
			addActionError(MESSAGE_ERROR_NO_INTERFACES);
		}
		
		return ActionSupport.SUCCESS;
	}
	
	public void validate() {
		
	}
}
