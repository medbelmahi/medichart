package ma.meditel.reporting.metier.interfaces;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import ma.meditel.reporting.entities.Traffic;
import ma.meditel.reporting.entities.TrafficNotification;

public interface ITrafficNotificationMetier {
	public TrafficNotification getTrafficNotification(Long idTrafficNotification);
	public List<TrafficNotification> getAllTrafficNotification();
	public void mergeTrafficNoti(TrafficNotification trafficNoti);
	public void addNoti(TrafficNotification trafficNoti) throws Exception;
	public void removeNoti(Long idTrafficNoti) throws Exception;
	public void removeAllTrafficNotis() throws Exception;
	public TrafficNotification getTrafficNotificationWithoutId(Long idInterface, Date jourDate, String typeTraffic);
	
	public Boolean getSaturationTest(ArrayList<Double> listValues__, double rang2, int varScore);
	public Date getMaxDateTrafficNoti();
	
	public ArrayList<Double> listOfTrafficToDouble(List<Traffic> uneListTraffic);
	
	/**
	 * insertion des notification de saturation des traffic in et out our une journnée
	 * @throws Exception 
	 */
	public Boolean insertSaturationOfTrafficInOutForOneDay(Date dateOfDay, Long idInterface, double rang, double trafficMin, double trafficMax, int varScore, int marge) throws Exception;
	
	/**
	 * procedure d'insertion dans la table notification
	 * @param trafficMax 
	 * @param trafficMin 
	 * @param rang 
	 * @param trafficType 
	 * @param dayDate 
	 * @throws Exception 
	 */
	public void updateNotificationTable(Date dayDate, String trafficType, double rang, double trafficMin, double trafficMax, int varScore, Long idNode, int marge) throws Exception;
	void insertSaturationOfTrafficInOutForOneDay_Classification(Date dayDate,
			Long idInterface, Long classification, double rang,
			double trafficMin, double trafficMax, int varScore, int marge)
			throws Exception;
	
	public List<Traffic> getTrafficByMargeOnDay(List<Traffic> traffics, int marge, Date sDate);
}
