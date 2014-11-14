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
@Table(name="CHARGE_CPU")
public class ChargeCpu {
	
	//contants columns
	public static final int NODE_UUID = 0;
	public static final int IP_ADDRESS = 1;
	public static final int NODE_NAME = 2;
	public static final int MIB_EXPRESSION = 3;
	public static final int TIME_STAMP = 4;
	public static final int POLL_INTREVAL = 5;
	public static final int MIB_INSTANCE = 6;
	public static final int METRICE_VALUE = 7;
	public static final int DISPLAY_ATTRIBUTE = 8;
	public static final int FILTER_VALUE = 9;
	//end Constants
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ID_CHARGE_CPU")
	private Long idChargeCpu;
	@Column(name="DATE")
	private Date date;
	@Column(name="METRICE_VALUE")
	private Double metriceValue;
	@Column(name="TYPE_CHARGE_CPU")
	private String typeChargeCpu;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="ID_INTERFACE_PHYSIC")
	private InterfacePhysic interfacePhysic;
	
	public ChargeCpu(Date date, Double metriceValue, String typeChargeCpu) {
		this.date = date;
		this.metriceValue = metriceValue;
		this.typeChargeCpu = typeChargeCpu;
	}

	public ChargeCpu() {
	}

	public Long getIdChargeCpu() {
		return idChargeCpu;
	}

	public void setIdChargeCpu(Long idChargeCpu) {
		this.idChargeCpu = idChargeCpu;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Double getMetriceValue() {
		return metriceValue;
	}

	public void setMetriceValue(Double metriceValue) {
		this.metriceValue = metriceValue;
	}

	public String getTypeChargeCpu() {
		return typeChargeCpu;
	}

	public void setTypeChargeCpu(String typeChargeCpu) {
		this.typeChargeCpu = typeChargeCpu;
	}

	public InterfacePhysic getInterfacePhysic() {
		return interfacePhysic;
	}

	public void setInterfacePhysic(InterfacePhysic interfacePhysic) {
		this.interfacePhysic = interfacePhysic;
	}
	
}
