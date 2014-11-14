package ma.meditel.reporting.metier.implementations;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import ma.meditel.reporting.dao.interfaces.ITrafficPerClassDao;
import ma.meditel.reporting.entities.Traffic;
import ma.meditel.reporting.entities.TrafficClassification;
import ma.meditel.reporting.metier.interfaces.ITrafficPerClassMetier;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
public class TrafficPerClassMetierImpl implements ITrafficPerClassMetier {

	private ITrafficPerClassDao dao;
	
	
	@Override
	public List<TrafficClassification> getAllInterfaceTrafficsBetween(Long idInterface, Date startDate, Date endDate, Long classTraffic) {
		return dao.getAllInterfaceTrafficsBetween(idInterface, startDate, endDate, classTraffic);
	}

	public ITrafficPerClassDao getDao() {
		return dao;
	}

	public void setDao(ITrafficPerClassDao dao) {
		this.dao = dao;
	}
	
	@Override
	public List<TrafficClassification> getTrafficClassificationByHourForOnrDay(
			List<TrafficClassification> trafficsClassification, int marge, Date dateDebut) {
		
		Date starIterationtDate = new Date(dateDebut.getTime());
		
		Date endIterationDate = new Date(starIterationtDate.getTime() + (60*1000*marge));
		List<TrafficClassification> newListOfTraffic = new ArrayList<TrafficClassification>();
		
		
		while(starIterationtDate.compareTo(trafficsClassification.get(trafficsClassification.size()-1).getDate()) < 0 )
		{
			newListOfTraffic.add(getTheAvgOfValueBetwen_trafficClassification(starIterationtDate, endIterationDate, trafficsClassification));
			starIterationtDate.setTime(starIterationtDate.getTime() + (60*1000*marge));
			endIterationDate.setTime(endIterationDate.getTime() + (60*1000*marge));
		}
		
		
		return newListOfTraffic;
	}

	private TrafficClassification getTheAvgOfValueBetwen_trafficClassification(
			Date debut, Date fin,
			List<TrafficClassification> theList_) {
		
		
		int count = 0;
		TrafficClassification theNewListOfTraffic = new TrafficClassification();
		Double trafficsTotalValue = (double) 0;
		Boolean passe = false;
		
		theNewListOfTraffic.setDate(debut);
		theNewListOfTraffic.setMetriceValue((double) 0); 
		for(TrafficClassification t : theList_)
		{
			if(t.getDate().compareTo(debut) >= 0 &&  t.getDate().compareTo(fin) < 0)
			{
				trafficsTotalValue += t.getMetriceValue();
				if(count == 0)
				{	
					theNewListOfTraffic = new TrafficClassification(t);
					theNewListOfTraffic.setMetriceValue((double) 0);
					passe = true;
				}
				count++;
			}else
			{
				if(passe && count != 0) break;
			}
		}
		
		if(passe) theNewListOfTraffic.setMetriceValue(avgOfTrafficsValues(trafficsTotalValue,count));
		
		return theNewListOfTraffic;
	}

	private Double avgOfTrafficsValues(Double trafficsTotalValue, int count) {
		return trafficsTotalValue/(double) count;
	}

	public ArrayList<Double> listOfTrafficClassificationToDouble(List<TrafficClassification> trafficsOfDay) {
		
		ArrayList<Double> list = new ArrayList<Double>();
		
		for(TrafficClassification t : trafficsOfDay)
		{
			list.add(t.getCalculatedMetriceValue());
		}
		
		return list;
	}

	@Override
	public List<TrafficClassification> getAllInterfaceTrafficsClassificationOfTheDay(
			Long idInterface, Date dayDate, Long classTraffic) {
		return dao.getAllInterfaceTrafficsClassificationOfTheDay(idInterface, dayDate, classTraffic);
	}

	@Override
	public List<Long> getListOfIdInterfaceInClass() {
		return dao.getListOfIdInterfaceInClass();
	}
	
}
