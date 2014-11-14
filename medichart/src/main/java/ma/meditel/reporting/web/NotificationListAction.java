package ma.meditel.reporting.web;

import java.util.List;

import ma.meditel.reporting.entities.Node;
import ma.meditel.reporting.entities.TrafficNotification;
import ma.meditel.reporting.metier.interfaces.IInterfaceMetier;
import ma.meditel.reporting.metier.interfaces.INodeMetier;
import ma.meditel.reporting.metier.interfaces.ITrafficNotificationMetier;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.opensymphony.xwork2.ActionSupport;

@Component
public class NotificationListAction extends ActionSupport {

	private static final long serialVersionUID = 1648618358262216449L;

	@Autowired
	public ITrafficNotificationMetier trafficNotificationMetier;
	@Autowired
	public INodeMetier nodeMetier;
	@Autowired
	public IInterfaceMetier interfaceMetier;
	
	public List<TrafficNotification> trafficNotis;
	
	public Long idTrafficNoti;

	public  List<Node> lesNodes;
	public  List<Node> lesNodes_forClass;
	
	
	@Override
	public String execute(){
		
		try {
			
			if(idTrafficNoti != null)
			{
				trafficNotificationMetier.removeNoti(idTrafficNoti);
			}
			
			trafficNotis = trafficNotificationMetier.getAllTrafficNotification();
			
			lesNodes = interfaceMetier.getAllNodesActivated();
			lesNodes_forClass = interfaceMetier.getAllNodesOfInterfacesInClass();
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return ActionSupport.SUCCESS;
	}
	
	@SuppressWarnings("deprecation")
	@Override
	public void validate() {
		
		/*if(startDate != null){
			
			//validation date
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			try {
				sdate = formatter.parse(startDate+":00");
				edate = formatter.parse(endDate+":00");
				
				//define date interval (forcage des minutes)
				int sMinutes = (sdate.getMinutes()/5) * 5;
				int eMinutes = (edate.getMinutes()/5) * 5 + 5;
				
				sdate.setMinutes(sMinutes);
				edate.setMinutes(eMinutes);
				
				//Date should be < new date (now)
				if(edate.compareTo(new Date()) > 0){
					addActionError(MESSAGE_ERROR_DATE_3);
				}
				//sDate sould be < eDate
				if(sdate.compareTo(edate)  > 0){
					addActionError(MESSAGE_ERROR_DATE_1);
				}

				
			} catch (ParseException e) {
				addActionError(MESSAGE_ERROR_DATE_2);
			}//end dateFormat try
		}*/
		
	}
}
