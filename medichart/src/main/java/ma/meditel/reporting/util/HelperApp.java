package ma.meditel.reporting.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;


public class HelperApp {

	public Date stringToDate(String str)
	{
		try
		{
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		
			Date aDate = formatter.parse(str+":00");
			
			return aDate;
		}catch(ParseException eP)
		{
			eP.printStackTrace();
			return null;
		}
	}
	
	
	private Boolean testDeuxValues(Double d0, Double d1, Double rang)
	{
		Double d = d0 - d1;
		if(Math.abs(d) < Math.abs(rang) && Math.abs(d) > 0 && (d != 0))
		{
			return true;
		}else
		{
			return false;
		}
	}
	
	public Boolean saturationExist(ArrayList<Double> listValues__,Double rang, int varScore)
	{
		Double d0, d1;
		int score;
		
		HelperApp app = new HelperApp();
		
//		for(int c = 0 ; c < listValues__.size(); c++)
//		{
//			System.err.println("val : " + listValues__.get(c));
//		}
		
		Double[] data = listValues__.toArray(new Double[listValues__.size()]);
		
		for(int i = 0;  i < data.length ; i++)
		{
			score = 0;
			d0 = data[i];
			
			for(int j = i+1; j < data.length ; j++)
			{
				d1 = data[j];
				if(testDeuxValues(d0,d1, rang))
				{
					
					score++;
				}else
				{
					break;
				}
//				System.err.println("score : "+score+"\t d0 : "+d0+" d1 : "+d1 +"\tj= "+j);
				if(score == varScore)
					{
						if((d1 - data[j-1]) != 0) 
						{
							return true;
						}
						else {
							break;
						}
					}
			}
		}
		
		return false;
	
	}
	
	
	public Double rang(ArrayList<Double> listValues__)
	{
		int i = 0;
		Double somme = (double) 0;
		if(listValues__.size() < 1) return (double) 0;
		
		HelperApp app = new HelperApp();
		
		
//		for (Double d : listValues__)
//		{
//			System.out.println(d);
//			
//			
//			somme += d;
//			i++;
//		}
		
		Double temp = somme / (double) i;
		
		
		
		temp = temp * (double) 2 / (double) 100;
		
//		if(temp < 5 ) return (double) 0;
		
//		System.out.println("temp : "+temp);
		
		return temp;
	}
	
	/*public Boolean leRang(ArrayList<Double> listValues__)
	{
		int i = 0;
		double somme = (double) 0;
		for (Double d : listValues__)
		{
			somme += d;
			i++;
		}
		
		double temp = somme / (double) i;
		
		
		
		temp = temp * (double) 1 / (double) 1000;
		
		if(temp < (double) 1) return false;
		
		return true;
	}*/
	
	public Double getCalculatedMetriceValue_class(Double val){
		return (val)/(1024*1024);
	}


	public Double getCalculatedMetriceValue_inOut(Double val1) {
		// TODO Auto-generated method stub
		return (val1 * 8)/(1024*1024);
	}
	
	public Double roundMaxMetriceValue(Double d)
	{
		return round(d, 1);
	}
	
	private double round(double value, int places) {
	    if (places < 0) throw new IllegalArgumentException();

	    long factor = (long) Math.pow(10, places);
	    value = value * factor;
	    long tmp = Math.round(value);
	    return (double) tmp / factor;
	}
}
