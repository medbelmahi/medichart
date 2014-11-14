package ma.meditel.reporting.metier.interfaces;

import java.util.Date;
import java.util.List;

import ma.meditel.reporting.entities.Traffic;

public interface ITrafficMetier {
public Traffic getTraffic(Long idTraffic) throws Exception;
	
	public List<Traffic> getAllInterfaceTrafficsBetween(Long idInterface, Date sdate, Date edate, String typeTraffic);
	
	public List<Traffic> getAllInterfaceTrafficsOfTheDay(Long idInterface, Date dayDate, String typeTraffic);
	
	public Integer getCountAllInterfaceTrafficsBetween(Long idInterface, Date startDate, Date endDate, String typeTraffic);
	
	public List<Traffic> getTrafficByHourForOnrDay(List<Traffic> traffics);
	public void mergeTraffic(Traffic t);
	
	public Double getMaxValueTrafficInADay(Date dateJour, Long idInterface, String typeTraffic);

	public Date getMaxDateTraffic();
}
