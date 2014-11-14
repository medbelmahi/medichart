package ma.meditel.reporting.entities;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="INTERFACES")
public class Interface implements Serializable {
	private static final long serialVersionUID = -3107649712473209208L;
	
	//Constants Log rows
	public static final int HOSTED_ON 	= 3;
	public static final int IF_NAME 	= 4;
	public static final int IF_TYPE 	= 5;
	public static final int IF_SPEED 	= 6;
	public static final int IF_INDEX 	= 7;
	public static final int IF_DESCR	= 8;
	public static final int IF_ALIAS	= 9;
	public static final int COLUMN_NUMBER = 19;
	//End
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ID_INTERFACE")
	private Long idInterface;
	@Column(name="IF_NAME")
	private String ifName;
	@Column(name="IF_TYPE")
	private String ifType;
	@Column(name="IF_SPEED")
	private String ifSpeed;
	@Column(name="IF_INDEX")
	private String ifIndex;
	@Column(name="IF_DESCR")
	private String ifDescr;
	@Column(name="IF_ALIAS")
	private String ifAlias;
	@Column(name="ACTIVATED", nullable=false, columnDefinition = "tinyint default false")
	private boolean activated;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="REF_NODE")
	private Node node;
	
	@OneToMany(mappedBy="iinterface", cascade={CascadeType.ALL, CascadeType.MERGE, CascadeType.PERSIST}, orphanRemoval=true)
	private Set<Traffic> trafics = new HashSet<>();
	
	@OneToMany(mappedBy="iinterface", cascade={CascadeType.ALL, CascadeType.MERGE, CascadeType.PERSIST}, orphanRemoval=true)
	private Set<TrafficClassification> traficsClassification = new HashSet<>();
	
	@OneToMany(mappedBy="iinterface", cascade={CascadeType.ALL, CascadeType.MERGE, CascadeType.PERSIST}, orphanRemoval=true)
	private Set<TrafficNotification> traficsNotification = new HashSet<>();
	
	public Interface() {
		super();
	}
	
	
	
	public Set<TrafficNotification> getTraficsNotification() {
		return traficsNotification;
	}



	public void setTraficsNotification(Set<TrafficNotification> traficsNotification) {
		this.traficsNotification = traficsNotification;
	}



	public Long getIdInterface() {
		return idInterface;
	}
	public Set<Traffic> getTrafics() {
		return trafics;
	}

	public void setTrafics(Set<Traffic> trafics) {
		this.trafics = trafics;
	}

	public void setIdInterface(Long idInterface) {
		this.idInterface = idInterface;
	}
	public Node getNode() {
		return node;
	}
	public void setNode(Node node) {
		this.node = node;
	}

	public String getIfName() {
		return ifName;
	}

	public void setIfName(String ifName) {
		this.ifName = ifName;
	}

	public String getIfType() {
		return ifType;
	}

	public void setIfType(String ifType) {
		this.ifType = ifType;
	}

	public String getIfSpeed() {
		return ifSpeed;
	}

	public void setIfSpeed(String ifSpeed) {
		this.ifSpeed = ifSpeed;
	}

	public String getIfIndex() {
		return ifIndex;
	}

	public void setIfIndex(String ifIndex) {
		this.ifIndex = ifIndex;
	}

	public String getIfDescr() {
		return ifDescr;
	}

	public void setIfDescr(String ifDescr) {
		this.ifDescr = ifDescr;
	}
	
	public String getIfAlias() {
		return ifAlias;
	}

	public void setIfAlias(String ifAlias) {
		this.ifAlias = ifAlias;
	}

	public void addTrafic(Traffic t){
		this.getTrafics().add(t);
		t.setIinterface(this);
	}

	public Set<TrafficClassification> getTraficsClassification() {
		return traficsClassification;
	}

	public void setTraficsClassification(
			Set<TrafficClassification> traficsClassification) {
		this.traficsClassification = traficsClassification;
	}

	public boolean isActivated() {
		return activated;
	}

	public void setActivated(boolean activated) {
		this.activated = activated;
	}
	
}
