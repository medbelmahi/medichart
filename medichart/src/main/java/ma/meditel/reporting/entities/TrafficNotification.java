package ma.meditel.reporting.entities;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="TRAFFICS_NOTIFICATION")
public class TrafficNotification {
	//
	public static final String TRAFFIC_IN = "in";
	public static final String TRAFFIC_OUT = "out";
	
	//Constants Rows Log
	public static final int NODE_NAME 			= 2;
	public static final int MIB_INSTANCE 		= 6;//index
	public static final int METRICE_VALUE 		= 7;//debit
	public static final int DISPLAY_ATTRIBUTE 	= 8;//interface name
	public static final int FILTER_VALUE	 	= 9;//interface name
	//End
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ID_TRAFFIC_NOTIFICATION")
	private Long idTrafficNotification;
	@Column(name="DATE_Jour")
	private Date dateJour;
	@Column(name="TYPE_TRAFFIC", length=3)
	private String typeOrClassTraffic;
	@Column(name="MAX_METRICE_VALUE")
	private Double maxMetriceValue;
	@Column(name="NOTIFICATION_VU")
	private int notificationVu;
	
	@Column(name="MARGE_AGREGA")
	private int marge;
	
	public int getMarge() {
		return marge;
	}


	public String theMargeAgrega()
	{
		String s = "";
		s += marge;
		return s;
	}
	
	
	public void setMarge(int marge) {
		this.marge = marge;
	}

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="ID_INTERFACE")
	private Interface iinterface;
	
	public TrafficNotification() {
		super();
	}

	
	public Double getMaxMetriceValue() {
		return maxMetriceValue;
	}


	public void setMaxMetriceValue(Double maxMetriceValue) {
		this.maxMetriceValue = maxMetriceValue;
	}

	public Double roundMaxMetriceValue()
	{
		return round(maxMetriceValue, 2);
	}
	
	private double round(double value, int places) {
	    if (places < 0) throw new IllegalArgumentException();

	    long factor = (long) Math.pow(10, places);
	    value = value * factor;
	    long tmp = Math.round(value);
	    return (double) tmp / factor;
	}

	public Long getIdTrafficNotification() {
		return idTrafficNotification;
	}

	public void setIdTrafficNotification(Long idTrafficNotification) {
		this.idTrafficNotification = idTrafficNotification;
	}

	public Date getDateJour() {
		return dateJour;
	}

	public void setDateJour(Date dateJour) {
		this.dateJour = dateJour;
	}

	public String getTypeOrClassTraffic() {
		return typeOrClassTraffic;
	}

	public void setTypeOrClassTraffic(String typeOrClassTraffic) {
		this.typeOrClassTraffic = typeOrClassTraffic;
	}

	public int getNotificationVu() {
		return notificationVu;
	}

	public void setNotificationVu(int notificationVu) {
		this.notificationVu = notificationVu;
	}

	public Interface getIinterface() {
		return iinterface;
	}

	public void setIinterface(Interface iinterface) {
		this.iinterface = iinterface;
	}
	
	public Long getIdInterface()
	{
		return iinterface.getIdInterface();
	}
	
	public Boolean testNotificationVu()
	{
		if(notificationVu == 0) return false;
		return true;
	}
	
	public Boolean isClassType()
	{
		if((typeOrClassTraffic.compareTo(TRAFFIC_IN) != 0) && (typeOrClassTraffic.compareTo(TRAFFIC_OUT) != 0)) return true;
		return false;
	}
	
	public String getTheStartDate()
	{
		return dateJour.toString();
	}
	
	public String getTheEndDate()
	{
		Date tempDate = dateJour;
		tempDate.setHours(dateJour.getHours() + 24);
		
		return tempDate.toString();
	}
	
	public String jourSuivantDateStart()
	{
		Date tempDate = new Date(dateJour.getTime());
		tempDate.setHours(dateJour.getHours() + 24);
		
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		
		
		return formatter.format(tempDate);
	}
	
	public String jourSuivantDateEnd()
	{
		Date tempDate = new Date(dateJour.getTime());
		tempDate.setHours(dateJour.getHours() + 48);
		
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		
		
		return formatter.format(tempDate);
	}
	
	
	public String jourPrecedantDateStart()
	{
		Date tempDate = new Date(dateJour.getTime());
		tempDate.setHours(dateJour.getHours() - 24);
		
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		
		
		return formatter.format(tempDate);
	}
	
	public String jourPrecedantDateEnd()
	{
		Date tempDate = new Date(dateJour.getTime());
//		tempDate.setHours(dateJour.getHours() - 24);
		
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		
		
		return formatter.format(tempDate);
	}
	
	
}
