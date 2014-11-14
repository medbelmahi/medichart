package ma.meditel.reporting.web;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;

import ma.meditel.reporting.entities.Interface;
import ma.meditel.reporting.entities.Node;
import ma.meditel.reporting.entities.Traffic;
import ma.meditel.reporting.entities.TrafficNotification;
import ma.meditel.reporting.metier.interfaces.IInterfaceMetier;
import ma.meditel.reporting.metier.interfaces.INodeMetier;
import ma.meditel.reporting.metier.interfaces.ITrafficMetier;
import ma.meditel.reporting.metier.interfaces.ITrafficNotificationMetier;
import ma.meditel.reporting.util.ChartJSONObject;
import ma.meditel.reporting.util.JSONColor;
import ma.meditel.reporting.util.TrafficsHelper;

import com.google.gson.Gson;
import com.opensymphony.xwork2.ActionSupport;

public class DetecterLaSaturationAction extends ActionSupport {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -3105853560175112385L;
	
	private static final String MESSAGE_ERROR_INPUT = "Veuillez remplir tous les champs.";
	private static final String MESSAGE_DATE_EMPTY = "Veuillez choisir une date.";
	private static final String MESSAGE_TYPE_TRAFFIC_EMPTY = "Veuillez selectioner un type de traffic.";
	private static final String MESSAGE_ERROR_DATE_1 = "Date de début doit être inférieur ou égal au Date de fin.";
	private static final String MESSAGE_ERROR_DATE_2 = "Veuillez respecter le format du date (yyyy-MM-dd hh:mm).";
	private static final String MESSAGE_ERROR_DATE_3 = "Veuillez vérifier si la date entrée en paramètre est inférieur à aujourd'hui.";
	private static final String MESSAGE_ERROR_INTERFACE_OVER_SIZE = "Echec de la représentation: Nombre d'interface superieur à 6.";
	private static final String MESSAGE_ERROR_DATA_OVER_SIZE = "Echec de la représentation: Vieullez choisir une autre valeur de marge (Graphe trop chargé ou trop faible).";
	private static final String MESSAGE_NO_DATA = "Echec de la représentation: Données insuffisantes.";

	private static final String MESSAGE_TRAFFIC_TYPE_EMPTY = "Veuillez choisir le type du traffic.";
	private static final String MESSAGE_TRAFFIC_MIN_MAX_EMPTY = "Veullez entrez la valeur min et max du traffic";

	private static final String SUCCESS_DETECTION = "Traitement est bien terminé veuillez actualiser la page";
	

	@Autowired
	private ITrafficNotificationMetier iTrafficNotificationMetier;
	@Autowired
	public INodeMetier nodeMetier;
	
	@Autowired
	public IInterfaceMetier interfaceMetier;
	
	public String startDate;
	
	public String TrafficType;
	public String rang, TrafficMin, TrafficMax;
	public String varScore;
	public String idNode;
	public String margeAgregation;
	
	private Date dayDate;
	
	public List<TrafficNotification> trafficNotis;
	
	public Long idTrafficNoti;
	public  List<Node> lesNodes;

	public  List<Node> lesNodes_forClass;
	
	@SuppressWarnings("deprecation")
	@Override
	public String execute() throws InterruptedException {
		//Apres validation
		
		try {
			
			iTrafficNotificationMetier.removeAllTrafficNotis();
			iTrafficNotificationMetier.updateNotificationTable(dayDate, TrafficType, Double.parseDouble(rang), Double.parseDouble(TrafficMin), Double.parseDouble(TrafficMax),Integer.parseInt(varScore), Long.parseLong(idNode), Integer.parseInt(margeAgregation));
		
			if(idTrafficNoti != null)
			{
				iTrafficNotificationMetier.removeNoti(idTrafficNoti);
			}
			
			trafficNotis = iTrafficNotificationMetier.getAllTrafficNotification();
			
//			lesNodes = nodeMetier.getAllNodes();
			lesNodes = interfaceMetier.getAllNodesActivated();
			lesNodes_forClass = interfaceMetier.getAllNodesOfInterfacesInClass();
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		addActionMessage(SUCCESS_DETECTION);
		return ActionSupport.SUCCESS;
		
		
	}
	
	@SuppressWarnings("deprecation")
	@Override
	public void validate() {
		
		if(startDate==null){
			addActionError(MESSAGE_DATE_EMPTY);
			return;
		}
		
		if(TrafficType==null){
			addActionError(MESSAGE_TYPE_TRAFFIC_EMPTY);
			return;
		}
		
		if(TrafficType==null)
		{
			addActionError(MESSAGE_TRAFFIC_TYPE_EMPTY);
			return;
		}
		
		if(TrafficMin==null || TrafficMax==null)
		{
			addActionError(MESSAGE_TRAFFIC_MIN_MAX_EMPTY);
		}
		
		if(TrafficMin!=null && TrafficMax!=null)
		{
			if(TrafficMin.isEmpty() || TrafficMax.isEmpty())
			{
				addActionError(MESSAGE_TRAFFIC_MIN_MAX_EMPTY);
				return;
			}
		}
		
		
		if(startDate==null || TrafficType==null || TrafficMin==null || TrafficMax==null || rang == null){
			addActionError(MESSAGE_ERROR_INPUT);
			return;
		}
		
		try
		{
			Double.parseDouble(TrafficMin);
			Double.parseDouble(rang);
			Double.parseDouble(TrafficMax);
			Integer.parseInt(varScore);
			Integer.parseInt(margeAgregation);
			Long.parseLong(idNode);
			
		}catch(Exception e)
		{
			addActionError(e.getMessage());
		}
		
		
		//validation date
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			
			dayDate = formatter.parse(startDate+" 00:00:00");
			
		} catch (ParseException e) {
			addActionError(MESSAGE_ERROR_DATE_2);
			return;
		}//end dateFormat try
		
	}

}
