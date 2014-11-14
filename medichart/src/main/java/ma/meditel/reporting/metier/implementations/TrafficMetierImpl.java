package ma.meditel.reporting.metier.implementations;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import ma.meditel.reporting.dao.interfaces.ITrafficDao;
import ma.meditel.reporting.entities.Traffic;
import ma.meditel.reporting.metier.interfaces.ITrafficMetier;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
public class TrafficMetierImpl implements ITrafficMetier {

	private ITrafficDao dao;
	
	@Override
	public Traffic getTraffic(Long idTraffic) throws Exception {
		return dao.getTraffic(idTraffic);
	}
	
	@Override
	public List<Traffic> getAllInterfaceTrafficsBetween(Long idInterface, Date startDate, Date endDate, String typeTraffic) {
		return dao.getAllInterfaceTrafficsBetween(idInterface, startDate, endDate, typeTraffic);
	}

	public ITrafficDao getDao() {
		return dao;
	}

	public void setDao(ITrafficDao dao) {
		this.dao = dao;
	}

	@Override
	public Date getMaxDateTraffic() {
		return dao.getMaxDateTraffic();
	}

	@Override
	public Integer getCountAllInterfaceTrafficsBetween(Long idInterface,
			Date startDate, Date endDate, String typeTraffic) {
		return dao.getCountAllInterfaceTrafficsBetween(idInterface, startDate, endDate, typeTraffic);
	}

	@Override
	public List<Traffic> getAllInterfaceTrafficsOfTheDay(Long idInterface,
			Date dayDate, String typeTraffic) {
		return dao.getAllInterfaceTrafficsOfTheDay(idInterface, dayDate, typeTraffic);
	}
	
	
	

	@SuppressWarnings("deprecation")
	@Override
	public List<Traffic> getTrafficByHourForOnrDay(List<Traffic> traffics) {
		
		int count = 0;
		int i = 0;
		Double trafficsTotalValue = (double) 0;
		int theCourrentHour = 0;
		Date tempDate;
		Boolean passe = false;
		
		List<Traffic> theNewListOfTraffics = new ArrayList<Traffic>();
		for(Traffic t : traffics)
		{
			if(t.getDate().getHours() == theCourrentHour)
			{
				trafficsTotalValue += t.getMetriceValue();
				if(count == 0 || (count == 1 && i!=0))
				{	
					theNewListOfTraffics.add(t);
					tempDate = t.getDate();
					tempDate.setMinutes(0);tempDate.setSeconds(0);
					theNewListOfTraffics.get(i).setDate(tempDate);
					theNewListOfTraffics.get(i).setMetriceValue((double) 0);
					passe = true;
				}
				count++;
//				System.out.println("theCourrentHour : " + theCourrentHour + "\tcount : "+count +"\ttrafficsTotalValue : "+trafficsTotalValue);
			}
			else
			{	
//				System.out.println("before the avg count =  " + count+"\t i = "+i);
				if(passe){
					theNewListOfTraffics.get(i).setMetriceValue(avgOfTrafficsValues(trafficsTotalValue,count));
					
					
					trafficsTotalValue = (double) 0;
					count=0;
					i++;
					theCourrentHour++;
					
					
					
					trafficsTotalValue += t.getMetriceValue();
					count++;
					passe = false;
				}
					
				
			}
		}
		if(i != 0 && i!=24 && passe) theNewListOfTraffics.get(i).setMetriceValue(avgOfTrafficsValues(trafficsTotalValue,count));
		
		return theNewListOfTraffics;
	}
	
	private Double avgOfTrafficsValues(Double trafficsTotalValue, int count)
	{
		return trafficsTotalValue/(double) count;
	}

	@Override
	public void mergeTraffic(Traffic t) {
		dao.mergeTraffic(t);
	}

	@Override
	public Double getMaxValueTrafficInADay(Date dateJour, Long idInterface,
			String typeTraffic) {
		return dao.getMaxValueTrafficInADay(dateJour, idInterface, typeTraffic);
	}
	
	

}
