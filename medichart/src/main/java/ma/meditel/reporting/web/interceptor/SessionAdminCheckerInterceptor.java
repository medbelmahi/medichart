package ma.meditel.reporting.web.interceptor;

import ma.meditel.reporting.entities.Admin;
import ma.meditel.reporting.util.SessionHelper;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.interceptor.Interceptor;

public class SessionAdminCheckerInterceptor implements Interceptor {
	private static final long serialVersionUID = -6567440840531518446L;

	@Override
	public void destroy() {
	}

	@Override
	public void init() {
	}

	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		if(!SessionHelper.isUserConnected()){
			return ActionSupport.LOGIN;
		}
		
		if(!(SessionHelper.getUser() instanceof Admin)){
			return ActionSupport.ERROR;
		}
 
		return invocation.invoke();
	}

}
