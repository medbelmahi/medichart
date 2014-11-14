package ma.meditel.reporting.web.admin;

import ma.meditel.reporting.entities.SystemObject;
import ma.meditel.reporting.metier.interfaces.ISystemObjectMetier;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.opensymphony.xwork2.ActionSupport;

@Component
public class SystemStatusAction extends ActionSupport {
	private static final long serialVersionUID = -195426570945262857L;
	
	@Autowired
	public ISystemObjectMetier systemObjectMetier;
	
	public String status;
	
	@Override
	public String execute() throws Exception {
		SystemObject s = systemObjectMetier.getSystemObject(1L);
		if(status != null){
			if(status.equals("active")){
				s.setStatus(true);
				addActionMessage("Le systeme est activé.");
			}else {
				s.setStatus(false);
				addActionMessage("Le systeme est désactivé pour la maintenance.");
			}
			systemObjectMetier.MergeSystemObject(s);
			return ActionSupport.SUCCESS;
		}
		
		return ActionSupport.NONE;
	}
	
	@Override
	public void validate() {
	}
}
