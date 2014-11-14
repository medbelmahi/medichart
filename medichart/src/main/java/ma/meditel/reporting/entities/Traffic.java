package ma.meditel.reporting.entities;

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
@Table(name="TRAFFICS")
public class Traffic {
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
	@Column(name="ID_TRAFFIC")
	private Long idTraffic;
	//@Column(name="MIB_INSTANCE")//index interface
	//private String mibInstance;
	@Column(name="METRICE_VALUE")
	private Double metriceValue;//trafic interface
	@Column(name="DATE")
	private Date date;
	@Column(name="TYPE_TRAFFIC", length=3)
	private String typeTraffic;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="ID_INTERFACE")
	private Interface iinterface;
	
	public Traffic() {
		super();
	}
	
	public Traffic(Traffic t) {
		super();
		this.setDate(t.getDate());
		this.setIdTraffic(t.getIdTraffic());
		this.setIinterface(t.getIinterface());
		this.setMetriceValue(t.getMetriceValue());
		this.setTypeTraffic(t.getTypeTraffic());
		
	}
	
	public String getTypeTraffic() {
		return typeTraffic;
	}
	
	public void setTypeTraffic(String typeTraffic) {
		this.typeTraffic = typeTraffic;
	}

	public Long getIdTraffic() {
		return idTraffic;
	}

	public void setIdTraffic(Long idTraffic) {
		this.idTraffic = idTraffic;
	}

	/*public String getMibInstance() {
		return mibInstance;
	}

	public void setMibInstance(String mibInstance) {
		this.mibInstance = mibInstance;
	}*/

	public Double getMetriceValue() {
		return metriceValue;
	}
	
	public Double getCalculatedMetriceValue(){
		return (this.getMetriceValue() * 8)/(1024*1024);
	}

	public void setMetriceValue(Double metriceValue) {
		this.metriceValue = metriceValue;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Interface getIinterface() {
		return iinterface;
	}

	public void setIinterface(Interface iinterfaces) {
		this.iinterface = iinterfaces;
	}
	
}
