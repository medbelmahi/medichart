package ma.meditel.reporting.util;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class LogReader {

	public static String[] splitLine(String line, String delimiteur) throws Exception {
		try {
			return line.split(delimiteur);
		} catch (Exception e) {
			throw e;
		}
	}
	
	public static List<String> getLinesFromLog(String filename, int start) throws Exception {
		List<String> lines = new ArrayList<>();
		
		try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
			String sCurrentLine;
 
			while ((sCurrentLine = br.readLine()) != null) {
				if(start-- <= 0){
					lines.add(sCurrentLine);
				}
			}
			return lines;
			
		} catch (Exception e) {
			throw e;
		} 
		
	}
	
	public static List<String> getLinesFromLog(String filename) throws Exception{
		return getLinesFromLog(filename, 0);
	}

}
