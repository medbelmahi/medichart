package ma.meditel.reporting.dao.interfaces;

import java.util.Date;
import java.util.List;

import ma.meditel.reporting.entities.TrafficNotification;

public interface ITrafficNotificationDao {
	public TrafficNotification getTrafficNotification(Long idTrafficNotification);
	public TrafficNotification getTrafficNotificationWithoutId(Long idInterface, Date jourDate, String typeTraffic);
	public List<TrafficNotification> getAllTrafficNotification();
	public void mergeTrafficNoti(TrafficNotification trafficNoti);
	public void addNoti(TrafficNotification trafficNoti) throws Exception;
	public void removeNoti(Long idTrafficNoti) throws Exception;
	public void removeAllTrafficNotis() throws Exception;
	
	public Date getMaxDateTrafficNoti();
}
