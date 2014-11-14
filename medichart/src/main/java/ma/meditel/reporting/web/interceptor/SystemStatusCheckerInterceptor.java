package ma.meditel.reporting.web.interceptor;

import ma.meditel.reporting.entities.SystemObject;
import ma.meditel.reporting.entities.User;
import ma.meditel.reporting.metier.interfaces.ISystemObjectMetier;
import ma.meditel.reporting.util.SessionHelper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.Interceptor;

@Component
public class SystemStatusCheckerInterceptor implements Interceptor {
	private static final long serialVersionUID = -7478187181153743491L;
	private static final Long SYSTEM_ID = 1L;
	private static final String SYSTEM_MESSAGE="system_blocked";
	
	@Autowired
	public ISystemObjectMetier systemObjectMetier;

	@Override
	public void destroy() {}

	@Override
	public void init() {}

	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		if(SessionHelper.getUser().getRole().equals(User.ROLE_ADMIN)){
			return invocation.invoke();
		}
		
		SystemObject system = systemObjectMetier.getSystemObject(SYSTEM_ID);
		if(system.isStatus()){
			return invocation.invoke();
		}
 
		return SYSTEM_MESSAGE;
	}

}
