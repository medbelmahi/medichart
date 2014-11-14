package ma.meditel.reporting.dao.interfaces;

import java.util.Date;
import java.util.List;

import ma.meditel.reporting.entities.Traffic;
import ma.meditel.reporting.entities.TrafficClassification;

public interface ITrafficPerClassDao {
	public List<TrafficClassification> getAllInterfaceTrafficsBetween(Long idInterface, Date startDate, Date endDate, Long classTraffic);
	public List<TrafficClassification> getAllInterfaceTrafficsClassificationOfTheDay(Long idInterface, Date dayDate, Long classTraffic);
	public Double getMaxValueTrafficClassificationInADay(Date dayDate,
			Long idInterface, Long classification);
	
	public List<Long> getListOfIdInterfaceInClass();
}
