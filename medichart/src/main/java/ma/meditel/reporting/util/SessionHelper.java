package ma.meditel.reporting.util;

import java.util.Map;

import ma.meditel.reporting.entities.GeneraleStatistics;
import ma.meditel.reporting.entities.User;

import com.opensymphony.xwork2.ActionContext;

public class SessionHelper {
	private static final String USER_LABEL = "user";
	private static final String GENERALE_STATISTIC_LABEL = "statistics";
	
	public static User getUser(){
		Map<String, Object> session = ActionContext.getContext().getSession();
		User user = (User) session.get(USER_LABEL);
		
		return user;
	}
	
	public static void removeUser(){
		Map<String, Object> session = ActionContext.getContext().getSession();
		session.remove(USER_LABEL);
	}

	public static void putUser(User user) {
		Map<String, Object> session = ActionContext.getContext().getSession();
		session.put(USER_LABEL, user);
	}
	
	public static void putGeneralesStatistics(GeneraleStatistics gs) {
		Map<String, Object> session = ActionContext.getContext().getSession();
		session.put(GENERALE_STATISTIC_LABEL, gs);
	}
	
	public static void removeGeneralesStatistics(){
		Map<String, Object> session = ActionContext.getContext().getSession();
		session.remove(GENERALE_STATISTIC_LABEL);
	}
	
	public static boolean isUserConnected(){
		if(getUser() != null){
			return true;
		}
		
		return false;
	}
	
}
