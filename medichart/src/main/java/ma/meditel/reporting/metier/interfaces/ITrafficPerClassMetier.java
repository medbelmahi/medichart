package ma.meditel.reporting.metier.interfaces;

import java.util.Date;
import java.util.List;

import ma.meditel.reporting.entities.TrafficClassification;

public interface ITrafficPerClassMetier {
	public List<TrafficClassification> getAllInterfaceTrafficsBetween(Long idInterface, Date startDate, Date endDate, Long classTraffic);
	public List<TrafficClassification> getTrafficClassificationByHourForOnrDay(
			List<TrafficClassification> trafficsClassification , int marge, Date dateDebut);
	
	public List<TrafficClassification> getAllInterfaceTrafficsClassificationOfTheDay(Long idInterface, Date dayDate, Long classTraffic);
	
	public List<Long> getListOfIdInterfaceInClass();

}
