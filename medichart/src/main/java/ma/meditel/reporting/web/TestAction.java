package ma.meditel.reporting.web;

import java.util.List;

import ma.meditel.reporting.metier.interfaces.IBugAmeliorationMetier;
import ma.meditel.reporting.metier.interfaces.IChargeCpuMetier;
import ma.meditel.reporting.metier.interfaces.IGeneralStatisticsMetier;
import ma.meditel.reporting.metier.interfaces.IInterfaceAutorizedAliasMetier;
import ma.meditel.reporting.metier.interfaces.IInterfaceMetier;
import ma.meditel.reporting.metier.interfaces.IInterfacePhysicMetier;
import ma.meditel.reporting.metier.interfaces.INodeMetier;
import ma.meditel.reporting.metier.interfaces.IStreamMetier;
import ma.meditel.reporting.metier.interfaces.ISystemFTPMetier;
import ma.meditel.reporting.metier.interfaces.ISystemObjectMetier;
import ma.meditel.reporting.metier.interfaces.ITrafficMetier;
import ma.meditel.reporting.metier.interfaces.ITrafficNotificationMetier;
import ma.meditel.reporting.metier.interfaces.ITrafficPerClassMetier;
import ma.meditel.reporting.metier.interfaces.IUserMetier;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.opensymphony.xwork2.ActionSupport;

@Component
public class TestAction extends ActionSupport {
	private static final long serialVersionUID = 3162720624377149389L;
	
	//Test SVN
	
	@Autowired
	public IUserMetier userMetier;
	
	@Autowired
	public INodeMetier nodeMetier;
	
	@Autowired
	public IInterfaceMetier interfaceMetier;
	
	@Autowired
	public ITrafficMetier trafficMetier;
	
	@Autowired
	public ISystemObjectMetier systemObjectMetier;
	
	@Autowired
	public IInterfacePhysicMetier interfacePhysicMetier;
	
	@Autowired
	public IChargeCpuMetier chargeCpuMetier;
	
	@Autowired
	public IInterfaceAutorizedAliasMetier interfaceAutorizedAliasMetier;
	
	@Autowired
	public ITrafficPerClassMetier trafficPerClassMetier;
	
	@Autowired
	public IStreamMetier streamMetier;
	
	@Autowired
	public IGeneralStatisticsMetier generalStatisticsMetier;
	
	@Autowired
	public IBugAmeliorationMetier bugAmeliorationMetier;
	
	@Autowired
	ISystemFTPMetier systemFTPMetier;
	
	@Autowired
	ITrafficNotificationMetier trafficNotificationMetier;
	
	public String execute(){
		
		try {
			//SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
			//Date startDate = formatter.parse("2014-06-29 17:00:00");
			//Date endDate = formatter.parse("2014-07-02 17:00:00");
			
//			System.out.println(generalStatisticsMetier.getSystemStatus());
//			System.out.println(generalStatisticsMetier.getUsersNombre());
//			System.out.println(generalStatisticsMetier.getNodesNombre());
//			System.out.println(generalStatisticsMetier.getInterfacesLogicNombre());
//			System.out.println(generalStatisticsMetier.getInterfacesPhysicNombre());
			
//			SystemFTP sftp = systemFTPMetier.getSystemFTP(1L);
//			sftp.setHostAddress("127.1.1.1");
//			
//			systemFTPMetier.mergeFTP(sftp);
			
			/*TrafficNotification trafficNoti = new TrafficNotification();
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			
			String strDateJour = "2014-08-01 00:00";
			Date dateJour = formatter.parse(strDateJour+":00");
			trafficNoti.setDateJour(dateJour);
			
			Interface inter = interfaceMetier.getInterface((long) 1235);
			trafficNoti.setIinterface(inter);
			
			trafficNoti.setNotificationVu(0);
			trafficNoti.setTypeOrClassTraffic("in");
			System.out.println("your are in the test action");
			trafficNotificationMetier.addNoti(trafficNoti);*/
			
//			System.out.println(trafficMetier.getMaxDateTraffic());
			
//			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//			Date temp = formatter.parse("2014-08-01 00:00:00");
//			trafficNotificationMetier.getTrafficNotificationWithoutId((long) 987938793, temp, "in");
			
			List<Long> theList = trafficPerClassMetier.getListOfIdInterfaceInClass();
			
			System.out.println("size : "+theList.size());
			for(Long l : theList)
			{
				System.out.println(l);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return ActionSupport.SUCCESS;
	}
	
}
