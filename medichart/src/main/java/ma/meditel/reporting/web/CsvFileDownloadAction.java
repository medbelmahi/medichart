package ma.meditel.reporting.web;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ma.meditel.reporting.entities.Interface;
import ma.meditel.reporting.entities.Traffic;
import ma.meditel.reporting.entities.TrafficClassification;
import ma.meditel.reporting.metier.interfaces.IInterfaceMetier;
import ma.meditel.reporting.metier.interfaces.ITrafficMetier;
import ma.meditel.reporting.metier.interfaces.ITrafficPerClassMetier;
import ma.meditel.reporting.util.ChartJSONObject;
import ma.meditel.reporting.util.CsvFileHelper;
import ma.meditel.reporting.util.JSONColor;
import ma.meditel.reporting.util.TrafficsHelper;

import org.springframework.beans.factory.annotation.Autowired;

import com.google.gson.Gson;
import com.opensymphony.xwork2.ActionSupport;



public class CsvFileDownloadAction extends ActionSupport {
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -2278486383787278384L;
	
	private static final String MESSAGE_ERROR_INPUT = "Veuillez remplir tous les champs.";
	private static final String MESSAGE_INTERFACES_EMPTY = "Veuillez choisir une interface.";
	private static final String MESSAGE_TYPE_TRAFFIC_EMPTY = "Veuillez selectioner un type de traffic.";
	private static final String MESSAGE_ERROR_DATE_1 = "Date de début doit être inférieur ou égal au Date de fin.";
	private static final String MESSAGE_ERROR_DATE_2 = "Veuillez respecter le format du date (yyyy-MM-dd hh:mm).";
	private static final String MESSAGE_ERROR_DATE_3 = "Veuillez vérifier si la date entrée en paramètre est inférieur à aujourd'hui.";
	private static final String MESSAGE_ERROR_INTERFACE_OVER_SIZE = "Echec de la représentation: Nombre d'interface superieur à 6.";
	

	
	@Autowired
	public IInterfaceMetier interfaceMetier;
	@Autowired
	public ITrafficMetier trafficInOutMetier;
	@Autowired
	public ITrafficPerClassMetier trafficPerClassMetier;
	
	
	
	public Long[] interfacesId;
	public String startDate, endDate;
	public String[] typeTraffic;
	public int margeAgregation;
	public String niveauAgregation;
	public String chartData;//Chart Json String
	public List<Interface> listInterfaces;
	private Date sdate, edate;
	
	public Map<String, List<Double>> map;
	public List<String> listOfString;
	
	public  String infoGraphe;
	
	private InputStream fileInputStream;
	public String filename;
	public String TheTraffictype;

	public String[] classTraffic;
	 
	public InputStream getFileInputStream() {
		return fileInputStream;
	}
 
	
	@SuppressWarnings("deprecation")
	@Override
	public String execute() {
		//Apres validation
		if(interfacesId != null  && startDate!=null && endDate!=null){
			
			map = new HashMap<String, List<Double>>();
			
			//new JSON Object
			
			
			listInterfaces = new ArrayList<Interface>();
			edate.setTime(edate.getTime() + 1000*60*margeAgregation);
			//Fill dataSets with traffics from DB
			for(int i = 0; i<interfacesId.length; i++) { //for each interface selected
				Interface _interface = null;
				
				if(TheTraffictype.compareTo("inOut")==0)
				{
					for(int j = 0; j<typeTraffic.length; j++) { //for each type selected (in,out)
						//get All traffics for interfacesId[i] between sdate and edate
						
						List<Traffic> traffics = trafficInOutMetier.getAllInterfaceTrafficsBetween(interfacesId[i], sdate, edate, typeTraffic[j]);
						
						if(traffics.size() <= 0){
							break;
						}
						
						List<Double> finalDataset = null;
						
							if(niveauAgregation.compareTo("avg") == 0) 
							{
								finalDataset = TrafficsHelper.newTrafficsToAVG_(traffics,margeAgregation,sdate);
							}
							else
							{
								if(niveauAgregation.compareTo("min") == 0)
								{
									finalDataset = TrafficsHelper.newTrafficsToMin_(traffics,margeAgregation, sdate);
								}
								else
								{
									finalDataset = TrafficsHelper.newTrafficsToMax_(traffics,margeAgregation, sdate);
								}
							}
							
								
							try {
								_interface = interfaceMetier.getInterface(interfacesId[i]);
								
								String s = _interface.getNode().getNodeName()+"_"+_interface.getIfAlias()+"("+_interface.getIfIndex()+")_"
									+typeTraffic[j]+"_"+niveauAgregation;
								map.put(s, finalDataset);
								
								
							} catch (Exception e) {
								e.printStackTrace();
							}
							
							
							

						
						
						
					}//End TypeTraffic boucle
				}else
				{
					for(int j = 0; j<classTraffic.length; j++) { //for each class selected (in,out)
						//get All traffics for interfacesId[i] between sdate and edate
						List<TrafficClassification> traffics = trafficPerClassMetier.getAllInterfaceTrafficsBetween(interfacesId[i], sdate, edate, Long.parseLong(classTraffic[j]));
						
						if(traffics.size() <= 0){
							break;
						}
						
					
						//Agregation sur margeAgregation = 15 et selon le niveauAgregation
						List<Double> finalDataset = null;
						if(niveauAgregation.equals("avg")){
							finalDataset = TrafficsHelper.newTrafficsToAVG_trafficClassification(traffics, margeAgregation,sdate);
						}else if(niveauAgregation.equals("max")){
							finalDataset = TrafficsHelper.newTrafficsClassificationToMax_Min("Max", traffics, margeAgregation,sdate);
						}else if(niveauAgregation.equals("min")){
							finalDataset = TrafficsHelper.newTrafficsClassificationToMax_Min("Min", traffics, margeAgregation,sdate);
						}else {
//							finalDataset = TrafficsHelper.TrafficsToREEL(tempDataset, margeAgregation);
						}
						//end agregation
						
						
						
						
						//Format Graphe (Title and Label and Color)
						try {
							_interface = interfaceMetier.getInterface(interfacesId[i]);
							String s = _interface.getNode().getNodeName()+"_"+_interface.getIfAlias()+"("+_interface.getIfIndex()+")_Class:"
									+classTraffic[j]+"_"+niveauAgregation;
								map.put(s, finalDataset);
							
						} catch (Exception e) {}
						
					}//End TypeTraffic boucle
					
					listInterfaces.add(_interface);
				}
				
				
				
				listInterfaces.add(_interface);
				
			}//End interfacesId boucle
			
			
		}
		
		List<Date> listeDate = new ArrayList<Date>();
		listeDate = TrafficsHelper.getListdatebetwenSdateEdatebyMarge(sdate, edate, margeAgregation);
		SimpleDateFormat  simpleFormat = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss");
		String stemp = simpleFormat.format(new Date()).toString();
		CsvFileHelper csvFile = new CsvFileHelper(stemp+"_traffic_In_Out.csv", listeDate, map);
		System.out.println(map.size()+" liste : "+listeDate.size());
		listOfString = csvFile.writeCsvFile();
		
		filename = csvFile.getName();
		
		try {
			fileInputStream = new FileInputStream(new File(CsvFileHelper.PATH+filename));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
			return ActionSupport.SUCCESS;
		
		
	}
	
	@SuppressWarnings("deprecation")
	@Override
	public void validate() {
		
		if(interfacesId==null){
			addActionError(MESSAGE_INTERFACES_EMPTY);
			return;
		}
		if(startDate==null || endDate==null){
			addActionError(MESSAGE_ERROR_INPUT);
			return;
		}
		
		if(interfacesId.length > 6){
			addActionError(MESSAGE_ERROR_INTERFACE_OVER_SIZE);
			return;
		}
		
//		startDate = "2014-08-10 20:10:00";
//		endDate = "2014-08-18 20:10:00";
		
		
		//validation date
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			sdate = formatter.parse(startDate+":00");
			edate = formatter.parse(endDate+":00");
			
			
			//define date interval (forcage des minutes)
			int sMinutes = (sdate.getMinutes()/5) * 5;
			int eMinutes = (edate.getMinutes()/5) * 5 + 5;
			
			sdate.setMinutes(sMinutes);
			edate.setMinutes(eMinutes);
			
			//Date should be < new date (now)
			if(edate.compareTo(new Date()) > 0){
				addActionError(MESSAGE_ERROR_DATE_3);
				return;
			}
			//sDate sould be < eDate
			if(sdate.compareTo(edate)  > 0){
				addActionError(MESSAGE_ERROR_DATE_1);
				return;
			}
			
			
			
		} catch (ParseException e) {
			addActionError(MESSAGE_ERROR_DATE_2);
			return;
		}//end dateFormat try
		
	}

}
