package ma.meditel.reporting.web;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import ma.meditel.reporting.entities.Interface;
import ma.meditel.reporting.entities.TrafficClassification;
import ma.meditel.reporting.entities.TrafficNotification;
import ma.meditel.reporting.metier.interfaces.IInterfaceMetier;
import ma.meditel.reporting.metier.interfaces.ITrafficNotificationMetier;
import ma.meditel.reporting.metier.interfaces.ITrafficPerClassMetier;
import ma.meditel.reporting.util.ChartJSONObject;
import ma.meditel.reporting.util.JSONColor;
import ma.meditel.reporting.util.TrafficsHelper;

import org.springframework.beans.factory.annotation.Autowired;

import com.google.gson.Gson;
import com.opensymphony.xwork2.ActionSupport;

public class TrafficsPerClassGrapheAction extends ActionSupport {
	private static final long serialVersionUID = -7313259148450753790L;
	
	private static final String MESSAGE_ERROR_INPUT = "Veuillez remplir tous les champs.";
	private static final String MESSAGE_INTERFACES_EMPTY = "Veuillez choisir une interface.";
	private static final String MESSAGE_TYPE_TRAFFIC_EMPTY = "Veuillez selectioner une classe de traffic.";
	private static final String MESSAGE_ERROR_DATE_1 = "Date de début doit être inférieur ou égal au Date de fin.";
	private static final String MESSAGE_ERROR_DATE_2 = "Veuillez respecter le format du date (yyyy-MM-dd hh:mm).";
	private static final String MESSAGE_ERROR_DATE_3 = "Veuillez vérifier si la date entrée en paramètre est inférieur à aujourd'hui.";
	private static final String MESSAGE_ERROR_INTERFACE_OVER_SIZE = "Echec de la représentation: Nombre d'interface superieur à 3.";
	private static final String MESSAGE_ERROR_DATA_OVER_SIZE = "Echec de la représentation: Vieullez choisir une autre valeur de marge (Graphe trop chargé).";
	private static final String MESSAGE_NO_DATA = "Echec de la représentation: Données insuffisantes pour la représentation.";

	private static final String MANQUE_DES_DONNEES = "Attention il y a un manque de données dans cette représentation graphique dans l'intervalle";
	
	@Autowired
	public IInterfaceMetier interfaceMetier;
	@Autowired
	public ITrafficPerClassMetier trafficMetier;
	@Autowired
	public ITrafficNotificationMetier trafficNotificationMetier;
	
	public String MessageManqueDesdonnee = null;
	public Long[] interfacesId;
	public String startDate, endDate;
	public String[] classTraffic;
	public String grapheType;
	public int margeAgregation = 15;
	public String niveauAgregation = "avg";
	
	public String chartData;//Chart Json String
	public TrafficNotification trafficNoti;
	public String grapheNotificationType;
	public String grapheNotificationVu;
	public List<Interface> listInterfaces;
	public Date sdate, edate;
	public String urlForGenerateCSV;
	public String grapheTypeChart_;
	
	public Boolean typeDeGraphe = true;
	public Boolean theTypeGraphe = true;
	
	public String infoGraphe = null;
	public String mesgMarge = null;
	public String mesgNiveau = null;

	public boolean booleRender;
	
	
	@SuppressWarnings("deprecation")
	@Override
	public String execute() {
		
		ChartJSONObject jsonObj = new ChartJSONObject(); 
		
		//Apres validation
		if(interfacesId != null && classTraffic != null && startDate!=null && endDate!=null && grapheType!=null){
			
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
			infoGraphe = "traffic par Classe";
			
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
			booleRender = false;
			
			//new JSON Object
			
			jsonObj.renderLabel(sdate, edate, margeAgregation);
			
			if(!jsonObj.isDatasetGratherThen(80))
			{
				jsonObj.renderLabel_V2(sdate, edate, margeAgregation);
				booleRender = true;
			}
			
			//Verfier si le graphe support l'information
			/*if(!jsonObj.isDatasetRepresentable()){
				addActionError(MESSAGE_ERROR_DATA_OVER_SIZE);
				return ActionSupport.INPUT;
			}*/
			listInterfaces = new ArrayList<Interface>();
			edate.setTime(edate.getTime() + 1000*60*margeAgregation);
			//Fill dataSets with traffics from DB
			for(int i = 0; i<interfacesId.length; i++) { //for each interface selected
				Interface _interface = null;
				for(int j = 0; j<classTraffic.length; j++) { //for each class selected (in,out)
					//get All traffics for interfacesId[i] between sdate and edate
					
					List<TrafficClassification> traffics = trafficMetier.getAllInterfaceTrafficsBetween(interfacesId[i], sdate, edate, Long.parseLong(classTraffic[j]));
					
					if(traffics.size() <= 0){
						break;
					}
					
					String noDataInterval = TrafficsHelper.noDataInTheInterval_classification(traffics,margeAgregation,sdate);
					
					/*//Fill a temporary Dataset with traffics from DB (nouvelle algorithme)
					List<Double> tempDataset = new ArrayList<>();
					int k = 0;
					for(Date d = new Date(sdate.getTime()); d.compareTo(edate)<=0; d.setMinutes(d.getMinutes() + 5)){
						tempDataset.add(traffics.get(k).getCalculatedMetriceValue());
						
						if(d.equals(traffics.get(k).getDate())){
							if(k+1 < traffics.size()) k++;
						}
					}*/
					//End fill Temporary dataset
					
					//Agregation sur margeAgregation = 15 et selon le niveauAgregation
					List<Double> finalDataset = null;
					if(niveauAgregation.equals("avg")){
						finalDataset = TrafficsHelper.newTrafficsToAVG_trafficClassification(traffics, margeAgregation,sdate);
					}else if(niveauAgregation.equals("max")){
						finalDataset = TrafficsHelper.newTrafficsClassificationToMax_Min("Max", traffics, margeAgregation,sdate);
					}else if(niveauAgregation.equals("min")){
						finalDataset = TrafficsHelper.newTrafficsClassificationToMax_Min("Min", traffics, margeAgregation,sdate);
					}else {
//						finalDataset = TrafficsHelper.TrafficsToREEL(tempDataset, margeAgregation);
					}
					//end agregation
					

					if(noDataInterval != null)
					{
						
						MessageManqueDesdonnee = MANQUE_DES_DONNEES+"\n"+noDataInterval;
						addActionMessage(MANQUE_DES_DONNEES);
						addActionMessage(noDataInterval);
						MessageManqueDesdonnee ="";
					}
					
//					finalDataset.remove(finalDataset.size()-1);
					//Set JsonObject attributes
					jsonObj.addDataset();
					jsonObj.getLastDataset().setData(finalDataset);
					//end set Attributes
					
					//Format Graphe (Title and Label and Color)
					try {
						_interface = interfaceMetier.getInterface(interfacesId[i]);
						jsonObj.getLastDataset().setLabel(_interface.getNode().getNodeName()+"_"+_interface.getIfAlias() + " (" + classTraffic[j] + ")");
						jsonObj.getLastDataset().setTitle(_interface.getNode().getNodeName()+"_"+_interface.getIfAlias() + " (" + classTraffic[j] + ")");
						jsonObj.getLastDataset().setColor(JSONColor.getLisColors(j)[i]);
						
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
		
		if(grapheNotificationType != null && grapheNotificationType != "")
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
			trafficNoti.setTypeOrClassTraffic(classTraffic[0]);
			
			
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
		if(classTraffic==null){
			addActionError(MESSAGE_TYPE_TRAFFIC_EMPTY);
			return;
		}
		if(interfacesId.length > 3){
			addActionError(MESSAGE_ERROR_INTERFACE_OVER_SIZE);
			return;
		}
		
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
	
	private String urlGenerateuForCSVFile()
	{
		String tempString="?";
		
		for(Long l : interfacesId)
		{
			tempString +="&interfacesId="+l; 
		}
		for(String s : classTraffic)
		{
			tempString +="&classTraffic="+s; 
		}
		tempString +="&margeAgregation="+margeAgregation; 
		tempString +="&niveauAgregation="+niveauAgregation; 
		tempString +="&startDate="+startDate; 
		tempString +="&endDate="+endDate;
		tempString +="&TheTraffictype=classe";
		
		return tempString;
			
	}

}
