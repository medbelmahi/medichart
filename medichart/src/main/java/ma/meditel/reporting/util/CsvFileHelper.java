package ma.meditel.reporting.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.IteratorUtils;
import org.apache.commons.io.FileUtils;



public class CsvFileHelper {
	
	public static String PATH="//home//dcrperf//parameter//tempCSVFile//";
	private String name;
	private String separator;
	private List<String> lignes;
	private Map<String, List<Double>> listValues;
	private List<Date> listDates;
	private File csvFile;
	
	public CsvFileHelper() {
		super();
	}
	
	
	
	
	public CsvFileHelper(String string, List<Date> listeDate, Map<String, List<Double>> map) {
		this.listValues = map;
		this.listDates = listeDate;
		this.name = string;

		separator = ",";
		
		
//		csvFile = new File(path);
		
		
//		writeCsvFile();
		
	}




	public List<Date> getListDates() {
		return listDates;
	}




	public void setListDates(List<Date> listDates) {
		this.listDates = listDates;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSeparator() {
		return separator;
	}
	public void setSeparator(String separator) {
		this.separator = separator;
	}
	public List<String> getLignes() {
		return lignes;
	}
	public void setLignes(List<String> lignes) {
		this.lignes = lignes;
	}
	public Map<String, List<Double>> getListValues() {
		return listValues;
	}
	public void setListValues(Map<String, List<Double>> listValues) {
		this.listValues = listValues;
	}
	
	public List<String> writeCsvFile()
	{
		try {
			FileUtils.cleanDirectory(new File(PATH));
		} catch (IOException e1) {
			e1.printStackTrace();
		} 
		
		 File file = new File(PATH+name);
//		 File file = new File("temp//test.txt");
		 
	        boolean fileCreated = false;
	        try {
	            fileCreated = file.createNewFile();
	        } catch (IOException ioe) {
	            System.out.println("Error while creating empty file: " + ioe);
	        }
	 
	        if (fileCreated) {
	            System.out.println("Created empty file: " + file.getPath());
	        } else {
	            System.out.println("Failed to create empty file: " + file.getPath());
	        }
        
		PrintWriter writer = null;
		try {
			writer = new PrintWriter(file, "UTF-8");
		} catch (FileNotFoundException | UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		List<String> uneListe = new ArrayList<String>();
		String tempLigne = "date";
		
//		for(String s : listValues.)
		Iterator<String> i = listValues.keySet().iterator();
		List<String> listKey =  (List<String>) IteratorUtils.toList(i); 
		java.util.Collections.sort(listKey);
		for(String s : listKey)
		{
			tempLigne += ","+ s;
		}
		uneListe.add(tempLigne+"\n");
		writer.println(tempLigne);
		int j = 0;
		SimpleDateFormat  simpleFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		for(Date d : listDates)
		{
			tempLigne = simpleFormat.format(d).toString();
					
			
			for(String s : listKey)
			{
				if(j < listValues.get(s).size()) tempLigne += "," + listValues.get(s).get(j);
				else tempLigne += ",";
			}
			j++;
			uneListe.add(tempLigne+"\n");
			writer.println(tempLigne);
		}
		
		writer.close();
		return uneListe;
		
	}
	
}
