package ma.meditel.reporting.metier.implementations;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import ma.meditel.reporting.dao.interfaces.IInterfaceDao;
import ma.meditel.reporting.dao.interfaces.ITrafficDao;
import ma.meditel.reporting.dao.interfaces.ITrafficNotificationDao;
import ma.meditel.reporting.dao.interfaces.ITrafficPerClassDao;
import ma.meditel.reporting.entities.Interface;
import ma.meditel.reporting.entities.Traffic;
import ma.meditel.reporting.entities.TrafficClassification;
import ma.meditel.reporting.entities.TrafficNotification;
import ma.meditel.reporting.metier.interfaces.ITrafficNotificationMetier;
import ma.meditel.reporting.util.HelperApp;
import ma.meditel.reporting.util.TrafficsHelper;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
public class TrafficNotificationMetierImpl implements ITrafficNotificationMetier {
	
	private ITrafficNotificationDao dao;
	private ITrafficDao daoTraffic;
	private ITrafficPerClassDao daoTrafficClassificatio;
	private IInterfaceDao daoInterface;

	
	public ITrafficPerClassDao getDaoTrafficClassificatio() {
		return daoTrafficClassificatio;
	}

	public void setDaoTrafficClassificatio(
			ITrafficPerClassDao daoTrafficClassificatio) {
		this.daoTrafficClassificatio = daoTrafficClassificatio;
	}

	public ITrafficDao getDaoTraffic() {
		return daoTraffic;
	}

	public void setDaoTraffic(ITrafficDao daoTraffic) {
		this.daoTraffic = daoTraffic;
	}

	public IInterfaceDao getDaoInterface() {
		return daoInterface;
	}

	public void setDaoInterface(IInterfaceDao daoInterface) {
		this.daoInterface = daoInterface;
	}

	public ITrafficNotificationDao getDao() {
		return dao;
	}

	public void setDao(ITrafficNotificationDao dao) {
		this.dao = dao;
	}

	@Override
	public TrafficNotification getTrafficNotification(Long idTrafficNotification) {
		return dao.getTrafficNotification(idTrafficNotification);
	}

	@Override
	public List<TrafficNotification> getAllTrafficNotification() {
		
		return dao.getAllTrafficNotification();
	}

	@Override
	public void mergeTrafficNoti(TrafficNotification trafficNoti) {
		dao.mergeTrafficNoti(trafficNoti);
		
	}

	@Override
	public void addNoti(TrafficNotification trafficNoti) throws Exception {
		dao.addNoti(trafficNoti);
		
	}

	@Override
	public void removeNoti(Long idTrafficNoti) throws Exception {
		dao.removeNoti(idTrafficNoti);
		
	}

	@Override
	public void removeAllTrafficNotis() throws Exception {
		dao.removeAllTrafficNotis();
	}

	@Override
	public TrafficNotification getTrafficNotificationWithoutId(
			Long idInterface, Date jourDate, String typeTraffic) {
		return dao.getTrafficNotificationWithoutId(idInterface, jourDate, typeTraffic);
	}

	@Override
	public Boolean getSaturationTest(ArrayList<Double> listValues__, double rang2, int varScore) {
		HelperApp app = new HelperApp();
//		Double rang = app.rang(listValues__);
//		System.out.println("the rang : "+rang);
		return app.saturationExist(listValues__,rang2,varScore);
	}

	@Override
	public Date getMaxDateTrafficNoti() {
		return dao.getMaxDateTrafficNoti();
	}

	@Override
	public ArrayList<Double> listOfTrafficToDouble(List<Traffic> uneListTraffic) {
		
		ArrayList<Double> list = new ArrayList<Double>();
		
		for(Traffic t : uneListTraffic)
		{
			list.add(t.getCalculatedMetriceValue());
		}
		
		return list;
	}

	@Override
	public Boolean insertSaturationOfTrafficInOutForOneDay(Date dateOfDay,
			Long idInterface, double rang, double trafficMin, double trafficMax, int varScore, int marge) throws Exception {
		
		Boolean test = false;
		TrafficMetierImpl trafficMetier = new TrafficMetierImpl();
		List<Traffic> traffics = daoTraffic.getAllInterfaceTrafficsOfTheDay(idInterface, dateOfDay, Traffic.TRAFFIC_OUT);
		
		if(traffics != null && traffics.size() > 0)
		{
			
			Double val1 = daoTraffic.getMaxValueTrafficInADay(dateOfDay, idInterface, Traffic.TRAFFIC_OUT);
			val1 = (new HelperApp()).getCalculatedMetriceValue_inOut(val1);
			System.out.println("val1 = "+val1);
			if(val1 > trafficMin && val1 < trafficMax)
			{
				System.out.println("in the if 2 ");
				List<Traffic> trafficsOfDay = getTrafficByMargeOnDay(traffics,marge,dateOfDay);
				
				
				if(getSaturationTest(listOfTrafficToDouble(trafficsOfDay),rang, varScore))
				{
					System.out.println("stauration detected for OUT -> interface : " + idInterface+"\t date : "+dateOfDay);
					TrafficNotification trafficNottification = new TrafficNotification();
					trafficNottification.setDateJour(dateOfDay);
					trafficNottification.setIinterface(daoInterface.getInterface(idInterface));
					trafficNottification.setMaxMetriceValue(val1);
					trafficNottification.setNotificationVu(0);
					trafficNottification.setMarge(marge);
					trafficNottification.setTypeOrClassTraffic(Traffic.TRAFFIC_OUT);
					
					if(getTrafficNotificationWithoutId(trafficNottification.getIdInterface(), trafficNottification.getDateJour(), trafficNottification.getTypeOrClassTraffic()) == null)
					{
						dao.addNoti(trafficNottification);
					}
					
				}
				
				test = true;
			}
			
		}
		
		
		traffics = daoTraffic.getAllInterfaceTrafficsOfTheDay(idInterface, dateOfDay, Traffic.TRAFFIC_IN);
		if(traffics != null && traffics.size() > 0)
		{
			Double val2 = daoTraffic.getMaxValueTrafficInADay(dateOfDay, idInterface, Traffic.TRAFFIC_IN);
			val2 = (new HelperApp()).getCalculatedMetriceValue_inOut(val2);
			if(val2 > trafficMin && val2 < trafficMax)
			{
				List<Traffic> trafficsOfDay = getTrafficByMargeOnDay(traffics,marge,dateOfDay);
			
				if(getSaturationTest(listOfTrafficToDouble(trafficsOfDay), rang, varScore))
				{
					System.out.println("stauration detected for IN -> interface : " + idInterface+"\t date : "+dateOfDay);
					TrafficNotification trafficNottification = new TrafficNotification();
					trafficNottification.setDateJour(dateOfDay);
					trafficNottification.setIinterface(daoInterface.getInterface(idInterface));
					trafficNottification.setNotificationVu(0);
					trafficNottification.setMaxMetriceValue(val2);
					trafficNottification.setMarge(marge);
					trafficNottification.setTypeOrClassTraffic(Traffic.TRAFFIC_IN);
					
					if(getTrafficNotificationWithoutId(trafficNottification.getIdInterface(), trafficNottification.getDateJour(), trafficNottification.getTypeOrClassTraffic()) == null)
					{
						dao.addNoti(trafficNottification);
					}		
				}
				
				test = true;
			}
			
		}
		
		return test;
	}

	@Override
	public void updateNotificationTable(Date dayDate, String trafficType, double rang, double trafficMin, double trafficMax, int varScore, Long idNode, int marge) throws Exception {
		Date aujourdhui = new Date();
		
		
//		Scanner sc = new Scanner(System.in);
//	     int i = sc.nextInt();
	     
		List<Interface> listOfInterface = daoInterface.getAllInterfaces();
		
		if (trafficType.compareTo("InOut") == 0) {
			for (Interface inter : listOfInterface) {
				
//				System.out.println("boucle for");
				
				if(inter.getNode().getIdNode().compareTo(idNode) == 0)
				{
					System.err.println("in if");
					insertSaturationOfTrafficInOutForOneDay(dayDate,
						inter.getIdInterface(), rang, trafficMin, trafficMax,
						varScore, marge);
				}
				

			}
		} else {
			if (trafficType.compareTo("Class") == 0) {
				int i;
				for (Interface inter : listOfInterface) {
					
					if(inter.getNode().getIdNode().compareTo(idNode) == 0)
					{
						for(i=0; i < 8; i++)
						{
							insertSaturationOfTrafficInOutForOneDay_Classification(dayDate,
								inter.getIdInterface(),(long) i, rang, trafficMin,
								trafficMax, varScore, marge);
						}
					}
					

				}
			}
		}
	}
	@Override
	public void insertSaturationOfTrafficInOutForOneDay_Classification(
			Date dayDate, Long idInterface, Long classification, double rang, double trafficMin,
			double trafficMax, int varScore, int marge) throws Exception {
		
		TrafficPerClassMetierImpl trafficClassificationMetier = new TrafficPerClassMetierImpl();
		List<TrafficClassification> trafficsClassification = daoTrafficClassificatio.getAllInterfaceTrafficsClassificationOfTheDay(idInterface, dayDate, classification);
		
		if(trafficsClassification != null && trafficsClassification.size() > 0)
		{
			
			Double val1 = daoTrafficClassificatio.getMaxValueTrafficClassificationInADay(dayDate, idInterface, classification);
			val1 = (new HelperApp()).getCalculatedMetriceValue_class(val1);
			if(val1 > trafficMin && val1 < trafficMax)
			{
				List<TrafficClassification> trafficsOfDay = trafficClassificationMetier.getTrafficClassificationByHourForOnrDay(trafficsClassification, marge, dayDate);
				
				
				if(getSaturationTest(trafficClassificationMetier.listOfTrafficClassificationToDouble(trafficsOfDay),rang, varScore))
				{
					System.out.println("stauration detected for classe : "+classification+"\t -> interface : " + idInterface+"\t date : "+dayDate);
					TrafficNotification trafficNottification = new TrafficNotification();
					trafficNottification.setDateJour(dayDate);
					trafficNottification.setIinterface(daoInterface.getInterface(idInterface));
					trafficNottification.setNotificationVu(0);
					trafficNottification.setMarge(marge);
					trafficNottification.setMaxMetriceValue(val1);
					trafficNottification.setTypeOrClassTraffic(classification.toString());
					
					if(getTrafficNotificationWithoutId(trafficNottification.getIdInterface(), trafficNottification.getDateJour(), trafficNottification.getTypeOrClassTraffic()) == null)
					{
						dao.addNoti(trafficNottification);
					}
					
				}
				
			}
			
		}
		
	}

	@Override
	public List<Traffic> getTrafficByMargeOnDay(List<Traffic> theList_,
			int marge, Date dateDebut) {
		
		Date starIterationtDate = new Date(dateDebut.getTime());
		
		Date endIterationDate = new Date(starIterationtDate.getTime() + (60*1000*marge));
		List<Traffic> newListOfTraffic = new ArrayList<Traffic>();
		
		
		while(starIterationtDate.compareTo(theList_.get(theList_.size()-1).getDate()) < 0 )
		{
			newListOfTraffic.add(getTheAvgOfValueBetwen(starIterationtDate, endIterationDate, theList_));
			starIterationtDate.setTime(starIterationtDate.getTime() + (60*1000*marge));
			endIterationDate.setTime(endIterationDate.getTime() + (60*1000*marge));
		}
		return newListOfTraffic;
	}

	private static Traffic getTheAvgOfValueBetwen(Date debut, Date fin,List<Traffic> theList_)
	{
		int count = 0;
		Traffic theNewListOfTraffic = new Traffic();
		Double trafficsTotalValue = (double) 0;
		Boolean passe = false;
		
		theNewListOfTraffic.setDate(debut);
		theNewListOfTraffic.setMetriceValue((double) 0); 
		for(Traffic t : theList_)
		{
			if(t.getDate().compareTo(debut) >= 0 &&  t.getDate().compareTo(fin) < 0)
			{
				trafficsTotalValue += t.getMetriceValue();
				if(count == 0)
				{	
					theNewListOfTraffic = new Traffic(t);
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
	
	private static Double avgOfTrafficsValues(Double trafficsTotalValue, int count)
	{
		return trafficsTotalValue/(double) count;
	}

	
}
