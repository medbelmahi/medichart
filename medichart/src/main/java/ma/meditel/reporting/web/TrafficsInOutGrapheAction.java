package ma.meditel.reporting.web;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;

import ma.meditel.reporting.entities.Interface;
import ma.meditel.reporting.entities.Traffic;
import ma.meditel.reporting.entities.TrafficNotification;
import ma.meditel.reporting.metier.interfaces.IInterfaceMetier;
import ma.meditel.reporting.metier.interfaces.ITrafficMetier;
import ma.meditel.reporting.metier.interfaces.ITrafficNotificationMetier;
import ma.meditel.reporting.util.ChartJSONObject;
import ma.meditel.reporting.util.JSONColor;
import ma.meditel.reporting.util.TrafficsHelper;

import com.google.gson.Gson;
import com.opensymphony.xwork2.ActionSupport;

public class TrafficsInOutGrapheAction extends ActionSupport {
	private static final long serialVersionUID = -7313259148450753790L;
	
	private static final String MESSAGE_ERROR_INPUT = "Veuillez remplir tous les champs.";
	private static final String MESSAGE_INTERFACES_EMPTY = "Veuillez choisir une interface.";
	private static final String MESSAGE_TYPE_TRAFFIC_EMPTY = "Veuillez selectioner un type de traffic.";
	private static final String MESSAGE_ERROR_DATE_1 = "Date de début doit être inférieur ou égal au Date de fin.";
	private static final String MESSAGE_ERROR_DATE_2 = "Veuillez respecter le format du date (yyyy-MM-dd hh:mm).";
	private static final String MESSAGE_ERROR_DATE_3 = "Veuillez vérifier si la date entrée en paramètre est inférieur à aujourd'hui.";
	private static final String MESSAGE_ERROR_INTERFACE_OVER_SIZE = "Echec de la représentation: Nombre d'interface superieur à 6.";
	private static final String MESSAGE_ERROR_DATA_OVER_SIZE = "Echec de la représentation: Vieullez choisir une autre valeur de marge (Graphe trop chargé ou trop faible).";
	private static final String MESSAGE_NO_DATA = "Echec de la représentation: Données insuffisantes pour la représentation.";

	private static final String MANQUE_DES_DONNEES = "Attention il y a un manque de données dans cette représentation graphique figurant dans l'intervalle : ";
	
	
	
	@Autowired
	public IInterfaceMetier interfaceMetier;
	@Autowired
	public ITrafficMetier trafficMetier;
	@Autowired
	public ITrafficNotificationMetier trafficNotificationMetier;
	
	
	public String MessageManqueDesdonnee = null;
	public Long[] interfacesId;
	public String startDate, endDate;
	public String[] typeTraffic;
	public String grapheType;
	public String grapheNotificationType;
	public String grapheNotificationVu;
	public int margeAgregation;
	public String niveauAgregation;
	public TrafficNotification trafficNoti;
	public String chartData;//Chart Json String
	public List<Interface> listInterfaces;
	public Date sdate, edate;
	public String grapheTypeChart_;
	
	public Boolean typeDeGraphe = true;
	public Boolean theTypeGraphe = true;
	public String mesgMarge = null;
	public String mesgNiveau = null;
	
	public Boolean booleRender = false;
	
	public String urlForGenerateCSV;
	public String infoGraphe;
	@SuppressWarnings("deprecation")
	@Override
	public String execute() {
		ChartJSONObject jsonObj = new ChartJSONObject();
		//Apres validation
		if(interfacesId != null && typeTraffic != null && startDate!=null && endDate!=null && grapheType!=null){
			
			sdate.setMinutes(0);
			edate.setMinutes(0);
			if(margeAgregation == 1440) edate.setHours(sdate.getHours());
			edate.setTime(edate.getTime() + 1000*60*margeAgregation);
			
			
			switch (margeAgregation) {
			case 5:mesgMarge="5 min ";break;
			case 15:mesgMarge="15 min ";break;
			case 30:mesgMarge="30 min ";break;
			case 60:mesgMarge="1 heur ";break;
			case 1440:mesgMarge="1 jour";break;
			
			default:
				break;
			}
			mesgNiveau = niveauAgregation;
			infoGraphe = "traffic In/Out";
			
			
			if(grapheTypeChart_.compareTo("type1")== 0)
				{
					theTypeGraphe = false;
				}else 
				{
					theTypeGraphe = true;
				}
			if(grapheType.compareTo("line") == 0)
			{
				typeDeGraphe = true;
			}else
			{
				typeDeGraphe = false;
			}
			
			

			//new JSON Object
			
			jsonObj.renderLabel(sdate, new Date(edate.getTime()-(1000*60*margeAgregation)), margeAgregation);
			
			if(!jsonObj.isDatasetGratherThen(80))
			{
				jsonObj.renderLabel_V2(sdate, edate, margeAgregation);
				booleRender = true;
			}
			
			
			//Verfier si le graphe support l'information
//			if(!jsonObj.isDatasetRepresentable()){
//				addActionError(MESSAGE_ERROR_DATA_OVER_SIZE);
//				return ActionSupport.INPUT;
//			}
			listInterfaces = new ArrayList<Interface>();
			
			
			
			//Fill dataSets with traffics from DB
			for(int i = 0; i<interfacesId.length; i++) { //for each interface selected
				Interface _interface = null;
				for(int j = 0; j<typeTraffic.length; j++) { //for each type selected (in,out)
					//get All traffics for interfacesId[i] between sdate and edate
					
					List<Traffic> traffics = trafficMetier.getAllInterfaceTrafficsBetween(interfacesId[i], sdate, edate, typeTraffic[j]);
					
					if(traffics.size() <= 0){
						break;
					}
					
					String noDataInterval = TrafficsHelper.noDataInTheIntervale(traffics,margeAgregation,sdate);
					
					List<Double> finalDataset = null;
					
						if(niveauAgregation.compareTo("avg") == 0) finalDataset = TrafficsHelper.newTrafficsToAVG_(traffics,margeAgregation,sdate);
						else
						{
							if(niveauAgregation.compareTo("min") == 0) finalDataset = TrafficsHelper.newTrafficsToMin_(traffics,margeAgregation, sdate);
							else
							{
								finalDataset = TrafficsHelper.newTrafficsToMax_(traffics,margeAgregation, sdate);
							}
						}
						
						
						
						if(noDataInterval != null)
						{
							
							MessageManqueDesdonnee = MANQUE_DES_DONNEES+"\n"+noDataInterval;
							addActionMessage(MANQUE_DES_DONNEES);
							addActionMessage(noDataInterval);
							MessageManqueDesdonnee ="";
						}
					
					/*for(Traffic t : traffics)
					{
						System.out.println(t.getMetriceValue());
						if(t.getMetriceValue().compareTo((double) 0) ==  0)
						{
							System.err.println(t.getMetriceValue());
							bb = true;
//							addActionMessage(MANQUE_DES_DONNEES);
//							MessageManqueDesdonnee = MANQUE_DES_DONNEES;
//							break;
						}
					}
					
					if(bb == false)
					{
						for(Double d : finalDataset)
						{
							System.out.println("data : "+d);
							if(d.compareTo((double) 0) ==  0)
							{
//								addActionMessage(MANQUE_DES_DONNEES);
								MessageManqueDesdonnee = MANQUE_DES_DONNEES;
//								break;
							}
						}
					}*/
					
//						finalDataset.remove(finalDataset.size()-1);
						System.err.println("size : "+finalDataset.size());
					
					//Set JsonObject attributes
					jsonObj.addDataset();
					jsonObj.getLastDataset().setData(finalDataset);
					
					//end set Attributes
					
					//Format Graphe (Title and Label and Color)
					try {
						_interface = interfaceMetier.getInterface(interfacesId[i]);
						jsonObj.getLastDataset().setLabel(_interface.getNode().getNodeName()+"_"+_interface.getIfAlias() + " (" + typeTraffic[j] + ")");
						jsonObj.getLastDataset().setTitle(_interface.getNode().getNodeName()+"_"+_interface.getIfAlias() + " (" + typeTraffic[j] + ")");
						jsonObj.getLastDataset().setColor(JSONColor.getLisColors(j)[i]);
						
						
						//end message for me
						
					} catch (Exception e) {}
					
					
					
				}//End TypeTraffic boucle
				
				listInterfaces.add(_interface);
				
			}//End interfacesId boucle
			
			//Convert JsonObject to String
			if(jsonObj.getDatasets().size() <= 0){
				addActionError(MESSAGE_NO_DATA);
			}
			
			chartData = new Gson().toJson(jsonObj);
		}
		
		urlForGenerateCSV = urlGenerateuForCSVFile();
		
		if(grapheNotificationType != null && !grapheNotificationType.equals(""))
		{
			jsonObj.renderLabel(sdate, edate, margeAgregation);
			chartData = new Gson().toJson(jsonObj);
			booleRender = false;
			trafficNoti = new TrafficNotification();
			trafficNoti.setDateJour(sdate);
			try {
				trafficNoti.setIinterface(interfaceMetier.getInterface(interfacesId[0]));
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			trafficNoti.setNotificationVu(0);
			trafficNoti.setTypeOrClassTraffic(typeTraffic[0]);
			trafficNoti.setMarge(margeAgregation);
			
			if(grapheNotificationVu == null || grapheNotificationVu == "")
			{
				TrafficNotification theNotiForMerge = trafficNotificationMetier.getTrafficNotificationWithoutId(
				trafficNoti.getIdInterface(), trafficNoti.getDateJour(), trafficNoti.getTypeOrClassTraffic());
				
				if(theNotiForMerge != null && !theNotiForMerge.testNotificationVu())
				{
					theNotiForMerge.setNotificationVu(1);
					trafficNotificationMetier.mergeTrafficNoti(theNotiForMerge);
				}
			}
			
			System.err.println(trafficNoti.getMarge());
			
			return "GrapheNotification";
		}else {
			return ActionSupport.SUCCESS;
		}
		
		
	}
	
	@SuppressWarnings("deprecation")
	@Override
	public void validate() {
		
		if(interfacesId==null){
			addActionError(MESSAGE_INTERFACES_EMPTY);
			return;
		}
		if(startDate==null || endDate==null || grapheType==null){
			addActionError(MESSAGE_ERROR_INPUT);
			return;
		}
		
		if(typeTraffic==null){
			addActionError(MESSAGE_TYPE_TRAFFIC_EMPTY);
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
			System.out.println(sdate.getMonth());
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
	
	private String urlGenerateuForCSVFile()
	{
		String tempString="?";
		
		for(Long l : interfacesId)
		{
			tempString +="&interfacesId="+l; 
		}
		for(String s : typeTraffic)
		{
			tempString +="&typeTraffic="+s; 
		}
		tempString +="&margeAgregation="+margeAgregation; 
		tempString +="&niveauAgregation="+niveauAgregation; 
		tempString +="&startDate="+startDate; 
		tempString +="&endDate="+endDate;
		tempString +="&TheTraffictype=inOut";
		
		return tempString;
			
	}

}
