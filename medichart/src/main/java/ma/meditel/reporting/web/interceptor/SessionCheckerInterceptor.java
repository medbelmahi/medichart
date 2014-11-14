package ma.meditel.reporting.web.interceptor;

import ma.meditel.reporting.util.SessionHelper;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.interceptor.Interceptor;

public class SessionCheckerInterceptor implements Interceptor {
	private static final long serialVersionUID = -7478187181153743491L;

	@Override
	public void destroy() {}

	@Override
	public void init() {}

	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		if(SessionHelper.isUserConnected()){
			
		
			return invocation.invoke();
		}
 
		return ActionSupport.LOGIN;
	}

}
