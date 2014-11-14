package ma.meditel.reporting.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="TRAFFICS_CLASS")
public class TrafficClassification {
	
	//Column Constants
	public static final int NODE_UUID 		= 0;
	public static final int IP_ADDRESS 		= 1;
	public static final int NODE_NAME 		= 2;
	public static final int MIB_EXPRESSEION = 3;
	public static final int TIME_STAMP 		= 4;
	public static final int POLL_INTERVAL 	= 5;
	public static final int MIB_INSTANCE 	= 6;//interface index
	public static final int METRICE_VALUE 	= 7;//traffic par classe
	public static final int DISPLAY_ATTRIBUTE = 8;
	public static final int FILTER_VALUE 	= 9;
	//End column constants
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ID_TRAFFIC_CLASS")
	private Long IdTrafficClass;
	@Column(name="METRICE_VALUE")
	private Double metriceValue;
	@Column(name="DATE")
	private Date date;
	@Embedded
	private TClasse tclasse;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="ID_INTERFACE")
	private Interface iinterface;
	
	public TrafficClassification() {
	}

	public TrafficClassification(Double metriceValue, Date date) {
		super();
		this.metriceValue = metriceValue;
		this.date = date;
	}

	public TrafficClassification(TrafficClassification t) {
		this.setDate(t.getDate());
		this.setIdTrafficClass(t.getIdTrafficClass());
		this.setIinterface(t.getIinterface());
		this.setMetriceValue(t.getMetriceValue());
		this.setTclasse(t.getTclasse());
	}

	public Long getIdTrafficClass() {
		return IdTrafficClass;
	}

	public void setIdTrafficClass(Long idTrafficClass) {
		IdTrafficClass = idTrafficClass;
	}

	public Double getMetriceValue() {
		return metriceValue;
	}
	
	public Double getCalculatedMetriceValue(){
//		return (this.getMetriceValue() * 8)/(1024*1024);
		return (this.getMetriceValue())/(1024*1024);
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

	public TClasse getTclasse() {
		return tclasse;
	}

	public void setTclasse(TClasse tclasse) {
		this.tclasse = tclasse;
	}
	
	public Interface getIinterface() {
		return iinterface;
	}

	public void setIinterface(Interface iinterface) {
		this.iinterface = iinterface;
	}
}
