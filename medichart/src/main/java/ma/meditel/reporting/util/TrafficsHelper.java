package ma.meditel.reporting.util;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import ma.meditel.reporting.entities.Traffic;
import ma.meditel.reporting.entities.TrafficClassification;

public class TrafficsHelper {
	
	public static Traffic isListTrafficsContaineDate(List<Traffic> traffics, Date date){
		for(Traffic t : traffics){
			if(t.getDate().compareTo(date) == 0){
				return t;
			}
		}
		
		return null;
	}
	
	public static TrafficClassification isListTrafficsPerClassContaineDate(List<TrafficClassification> traffics, Date date){
		for(TrafficClassification t : traffics){
			if(t.getDate().compareTo(date) == 0){
				return t;
			}
		}
		
		return null;
	}
	//AVG banaya style
	public static List<Double> TrafficsToAVG(List<Double> data, int margeAgregation){
		List<Double> finalDataset = new ArrayList<>();
		double sum = 0;
		int divBy = margeAgregation/5;
		
		int minute = 0;
		
		for(Double d : data){
			if(minute % margeAgregation != 0){
				sum += d;
				
			}else {
				sum += d;
				sum /= divBy;
				
				if(minute == 0)//First value
					finalDataset.add(d);
				else
					finalDataset.add(sum);
				
				sum = 0;
			}
			minute += 5;
		}
		return finalDataset;
	}
	
	private static Double avgOfTrafficsValues(Double trafficsTotalValue, int count)
	{
		return trafficsTotalValue/(double) count;
	}
	
		public static String noDataInTheIntervale(List<Traffic> theList_, int marge, Date dateDebut)
		{
		
			Date starIterationtDate = new Date(dateDebut.getTime());
			
			Date endIterationDate = new Date(starIterationtDate.getTime() + (60*1000*marge));
			
			while(starIterationtDate.compareTo(theList_.get(theList_.size()-1).getDate()) < 0 )
			{
				if(!existeValueInTheIteration(starIterationtDate, endIterationDate, theList_))
				{
					System.err.println("- no Data betwen - sdate : "+starIterationtDate+"\tedate : "+endIterationDate);
					return "- Date Début : "+starIterationtDate.toLocaleString()+"\t Date Fin : "+endIterationDate.toLocaleString();
				}
				
				starIterationtDate.setTime(starIterationtDate.getTime() + (60*1000*marge));
				endIterationDate.setTime(endIterationDate.getTime() + (60*1000*marge));
				
			}
			
			return null;
		}
	
		private static boolean existeValueInTheIteration(Date debut,
				Date fin, List<Traffic> theList_) {
			
			for(Traffic t : theList_)
			{	
//				System.out.println("-existe- traffic :-> date : "+t.getDate()+" val :"+t.getMetriceValue());
				
				if(t.getDate().compareTo(debut) >= 0 &&  t.getDate().compareTo(fin) < 0)
				{
					return true;
				}
			}
			
			return false;
		}

		//new Algorithme
		public static List<Double> newTrafficsToAVG_(List<Traffic> theList_, int marge, Date dateDebut){
			
			Date starIterationtDate = new Date(dateDebut.getTime());
			
			Date endIterationDate = new Date(starIterationtDate.getTime() + (60*1000*marge));
			List<Traffic> newListOfTraffic = new ArrayList<Traffic>();
			
			
			while(starIterationtDate.compareTo(theList_.get(theList_.size()-1).getDate()) < 0 )
			{
				newListOfTraffic.add(getTheAvgOfValueBetwen(starIterationtDate, endIterationDate, theList_));
				starIterationtDate.setTime(starIterationtDate.getTime() + (60*1000*marge));
				endIterationDate.setTime(endIterationDate.getTime() + (60*1000*marge));
			}
			List<Double> finalDataset = new ArrayList<>();
			
			
			for(Traffic t : newListOfTraffic)
			{
				finalDataset.add((new HelperApp()).roundMaxMetriceValue(t.getCalculatedMetriceValue()));
			}
			
			return finalDataset;
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
		
		
	//MAX
	public static List<Double> TrafficsToMAX(List<Double> data, int margeAgregation){
		List<Double> finalDataset = new ArrayList<>();
		Double max = null;
		int minute = 0;
		
		for(Double d : data){
			if(max == null || d > max){
				max = d;
			}
			
			if(minute % margeAgregation == 0){
				finalDataset.add(max);
				max = null;
			}
			minute += 5;
		}
		return finalDataset;
	}
	
	//MIN
	public static List<Double> TrafficsToMIN(List<Double> data, int margeAgregation){
		List<Double> finalDataset = new ArrayList<>();
		Double min = null;
		int minute = 0;
		
		for(Double d : data){
			if(min == null || d < min){
				min = d;
			}
			
			if(minute % margeAgregation == 0){
				finalDataset.add(min);
				min = null;
			}
			minute += 5;
		}
		return finalDataset;
	}	
	
	//REEL
	public static List<Double> TrafficsToREEL(List<Double> data, int margeAgregation){
		List<Double> finalDataset = new ArrayList<>();
		int minute = 0;
		
		for(Double d : data){
			if(minute % margeAgregation == 0){
				finalDataset.add(d);
			}
			minute += 5;
		}
		return finalDataset;
	}

	
	
	
	public static List<Double> newTrafficsToMin_(List<Traffic> theList_, int marge, Date dateDebut)
	{
		
		Date starIterationtDate = new Date(dateDebut.getTime());
		Date endIterationDate = new Date(starIterationtDate.getTime() + (60*1000*marge));
		List<Traffic> newListOfTraffic = new ArrayList<Traffic>();
		
		while(starIterationtDate.compareTo(theList_.get(theList_.size()-1).getDate()) < 0 )
		{
			newListOfTraffic.add(getTheAvgOfValueBetwenMin(starIterationtDate, endIterationDate, theList_));
			starIterationtDate.setTime(starIterationtDate.getTime() + (60*1000*marge));
			endIterationDate.setTime(endIterationDate.getTime() + (60*1000*marge));
		}
		List<Double> finalDataset = new ArrayList<>();
		
		for(Traffic t : newListOfTraffic)
		{
			finalDataset.add((new HelperApp()).roundMaxMetriceValue(t.getCalculatedMetriceValue()));
		}
		
		return finalDataset;
	}
	
	
	private static Traffic getTheAvgOfValueBetwenMin(Date debut, Date fin,List<Traffic> theList_)
	{
		int count = 0;
		Traffic theNewListOfTraffic = new Traffic();
		double trafficsMaxValue = (double) 0;
		
		Boolean passe = false;
		
		theNewListOfTraffic.setDate(debut);
		theNewListOfTraffic.setMetriceValue((double) 0); 
		for(Traffic t : theList_)
		{
			
			if(t.getDate().compareTo(debut) >= 0 &&  t.getDate().compareTo(fin) < 0)
			{	if(count == 0) trafficsMaxValue = t.getMetriceValue();
				if(trafficsMaxValue > t.getMetriceValue()) trafficsMaxValue = t.getMetriceValue();
				passe = true;
				count++;
			}else
			{
				if(passe && count != 0) break;
			}
		}
		
		if(passe) theNewListOfTraffic.setMetriceValue(trafficsMaxValue);
		
		return theNewListOfTraffic;
	}
	
	
	
	public static List<Double> newTrafficsToMax_(List<Traffic> theList_, int marge, Date dateDebut)
	{
		
		Date starIterationtDate = new Date(dateDebut.getTime());
		Date endIterationDate = new Date(starIterationtDate.getTime() + (60*1000*marge));
		List<Traffic> newListOfTraffic = new ArrayList<Traffic>();
		
		while(starIterationtDate.compareTo(theList_.get(theList_.size()-1).getDate()) < 0 )
		{
			newListOfTraffic.add(getTheAvgOfValueBetwenMax(starIterationtDate, endIterationDate, theList_));
			starIterationtDate.setTime(starIterationtDate.getTime() + (60*1000*marge));
			endIterationDate.setTime(endIterationDate.getTime() + (60*1000*marge));
		}
		List<Double> finalDataset = new ArrayList<>();
		
		for(Traffic t : newListOfTraffic)
		{
			finalDataset.add((new HelperApp()).roundMaxMetriceValue(t.getCalculatedMetriceValue()));
		}
		
		return finalDataset;
	}
	
	
	private static Traffic getTheAvgOfValueBetwenMax(Date debut, Date fin,List<Traffic> theList_)
	{
		int count = 0;
		Traffic theNewListOfTraffic = new Traffic();
		Double trafficsMaxValue = (double) 0;
		Boolean passe = false;
		
		theNewListOfTraffic.setDate(debut);
		theNewListOfTraffic.setMetriceValue((double) 0); 
		for(Traffic t : theList_)
		{
			if(t.getDate().compareTo(debut) >= 0 &&  t.getDate().compareTo(fin) < 0)
			{
				if(trafficsMaxValue < t.getMetriceValue()) trafficsMaxValue = t.getMetriceValue();
				passe = true;
				count++;
			}else
			{
				if(passe && count != 0) break;
			}
		}
		
		if(passe) theNewListOfTraffic.setMetriceValue(trafficsMaxValue);
		
		return theNewListOfTraffic;
	}



//the avg for traffic classification

public static List<Double> newTrafficsToAVG_trafficClassification(List<TrafficClassification> theList_, int marge, Date dateDebut){
	
	Date starIterationtDate = new Date(dateDebut.getTime());
	
	Date endIterationDate = new Date(starIterationtDate.getTime() + (60*1000*marge));
	List<TrafficClassification> newListOfTraffic = new ArrayList<TrafficClassification>();
	
	
	while(starIterationtDate.compareTo(theList_.get(theList_.size()-1).getDate()) < 0 )
	{
		newListOfTraffic.add(getTheAvgOfValueBetwen_trafficClassification(starIterationtDate, endIterationDate, theList_));
		starIterationtDate.setTime(starIterationtDate.getTime() + (60*1000*marge));
		endIterationDate.setTime(endIterationDate.getTime() + (60*1000*marge));
	}
	List<Double> finalDataset = new ArrayList<>();
	
	
	for(TrafficClassification t : newListOfTraffic)
	{
		finalDataset.add((new HelperApp()).roundMaxMetriceValue(t.getCalculatedMetriceValue()));
	}
	
	return finalDataset;
}

private static TrafficClassification getTheAvgOfValueBetwen_trafficClassification(Date debut, Date fin,List<TrafficClassification> theList_)
{
	
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



public static List<Double> newTrafficsClassificationToMax_Min(String MaxMin, List<TrafficClassification> theList_, int marge, Date dateDebut)
{
	
	Date starIterationtDate = new Date(dateDebut.getTime());
	Date endIterationDate = new Date(starIterationtDate.getTime() + (60*1000*marge));
	List<TrafficClassification> newListOfTraffic = new ArrayList<TrafficClassification>();
	
	if(MaxMin.compareTo("Max") == 0)
	{
		while(starIterationtDate.compareTo(theList_.get(theList_.size()-1).getDate()) < 0 )
		{
			newListOfTraffic.add(getTheMaxValueBetwenMax(starIterationtDate, endIterationDate, theList_));
			starIterationtDate.setTime(starIterationtDate.getTime() + (60*1000*marge));
			endIterationDate.setTime(endIterationDate.getTime() + (60*1000*marge));
		}
	}
	else
	{
		if(MaxMin.compareTo("Min") == 0)
		{
			while(starIterationtDate.compareTo(theList_.get(theList_.size()-1).getDate()) < 0 )
			{
				newListOfTraffic.add(getTheMinValueBetwenMax(starIterationtDate, endIterationDate, theList_));
				starIterationtDate.setTime(starIterationtDate.getTime() + (60*1000*marge));
				endIterationDate.setTime(endIterationDate.getTime() + (60*1000*marge));
			}
		}
	}
	
	List<Double> finalDataset = new ArrayList<>();
	
	for(TrafficClassification t : newListOfTraffic)
	{
		finalDataset.add((new HelperApp()).roundMaxMetriceValue(t.getCalculatedMetriceValue()));
	}
	
	return finalDataset;
}

	private static TrafficClassification getTheMinValueBetwenMax(
			Date starIterationtDate, Date endIterationDate,
			List<TrafficClassification> theList_) {
		int count = 0;
		TrafficClassification theNewListOfTraffic = new TrafficClassification();
		Double trafficsMaxValue = (double) 0;
		Boolean passe = false;

		theNewListOfTraffic.setDate(starIterationtDate);
		theNewListOfTraffic.setMetriceValue((double) 0);
		for (TrafficClassification t : theList_) {
			if (t.getDate().compareTo(starIterationtDate) >= 0 && t.getDate().compareTo(endIterationDate) < 0) {
				if (trafficsMaxValue < t.getMetriceValue())
					trafficsMaxValue = t.getMetriceValue();
				passe = true;
				count++;
			} else {
				if (passe && count != 0)
					break;
			}
		}

		if (passe)
			theNewListOfTraffic.setMetriceValue(trafficsMaxValue);

		return theNewListOfTraffic;
	}
	
	public static List<Date> getListdatebetwenSdateEdatebyMarge(Date sDate, Date eDate, int marge)
	{
		List<Date> date = new ArrayList<Date>();
		Date D = new Date(sDate.getTime());
		eDate.setTime(eDate.getTime() - (1000*60*marge));
		while(D.compareTo(eDate) <  0)
		{
			
			Date d = new Date(D.getTime());
			date.add(d);
			D.setTime(D.getTime()+ (1000*60*marge));
		}
		
		return date;
		
	}

private static TrafficClassification getTheMaxValueBetwenMax(
		Date starIterationtDate, Date endIterationDate,
		List<TrafficClassification> theList_) {
	int count = 0;
	TrafficClassification theNewListOfTraffic = new TrafficClassification();
	double trafficsMaxValue = (double) 0;
	
	Boolean passe = false;
	
	theNewListOfTraffic.setDate(starIterationtDate);
	theNewListOfTraffic.setMetriceValue((double) 0); 
	for(TrafficClassification t : theList_)
	{
		
		if(t.getDate().compareTo(starIterationtDate) >= 0 &&  t.getDate().compareTo(endIterationDate) < 0)
		{	if(count == 0) trafficsMaxValue = t.getMetriceValue();
			if(trafficsMaxValue > t.getMetriceValue()) trafficsMaxValue = t.getMetriceValue();
			passe = true;
			count++;
		}else
		{
			if(passe && count != 0) break;
		}
	}
	
	if(passe) theNewListOfTraffic.setMetriceValue(trafficsMaxValue);
	
	return theNewListOfTraffic;
}

public static String noDataInTheInterval_classification(
		List<TrafficClassification> traffics, int margeAgregation, Date sdate) {
	Date starIterationtDate = new Date(sdate.getTime());
	
	Date endIterationDate = new Date(starIterationtDate.getTime() + (60*1000*margeAgregation));
	
	while(starIterationtDate.compareTo(traffics.get(traffics.size()-1).getDate()) < 0 )
	{
		if(!existeValueInTheIteration_classification(starIterationtDate, endIterationDate, traffics))
		{
			System.err.println("- no Data betwen - sdate : "+starIterationtDate+"\tedate : "+endIterationDate);
			return "- no Data betwen - \nsdate : "+starIterationtDate.toLocaleString()+"\nedate : "+endIterationDate.toLocaleString();
		}
		
		starIterationtDate.setTime(starIterationtDate.getTime() + (60*1000*margeAgregation));
		endIterationDate.setTime(endIterationDate.getTime() + (60*1000*margeAgregation));
		
	}
	
	return null;
}

private static boolean existeValueInTheIteration_classification(
		Date starIterationtDate, Date endIterationDate,
		List<TrafficClassification> traffics) {
	for(TrafficClassification t : traffics)
	{	
//		System.out.println("-existe- traffic :-> date : "+t.getDate()+" val :"+t.getMetriceValue());
		
		if(t.getDate().compareTo(starIterationtDate) >= 0 &&  t.getDate().compareTo(endIterationDate) < 0)
		{
			return true;
		}
	}
	
	return false;
}



}
