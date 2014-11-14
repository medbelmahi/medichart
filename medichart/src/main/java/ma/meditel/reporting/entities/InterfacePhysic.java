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
@Table(name="INTERFACES_PHYSIC")
public class InterfacePhysic implements Serializable {
	private static final long serialVersionUID = 6361366282615136534L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ID_INTERFACE_PHYSIC")
	private Long idInterfacePhysic;
	@Column(name="NAME_INTERFACE_PHYSIC")
	private String nameInterfacePhysic;
	@Column(name="INDEX_INTERFACE_PHYSIC")
	private String indexInterfacePhysic;//mibInstance
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="REF_NODE")
	private Node node;
	
	@OneToMany(mappedBy="interfacePhysic", cascade={CascadeType.ALL, CascadeType.MERGE, CascadeType.PERSIST}, orphanRemoval=true)
	private Set<ChargeCpu> chargesCpu = new HashSet<>();
	
	public InterfacePhysic() {
	}

	public InterfacePhysic(String nameInterfacePhysic, String indexInterfacePhysic) {
		this.nameInterfacePhysic = nameInterfacePhysic;
		this.indexInterfacePhysic = indexInterfacePhysic;
	}

	public Long getIdInterfacePhysic() {
		return idInterfacePhysic;
	}

	public void setIdInterfacePhysic(Long idInterfacePhysic) {
		this.idInterfacePhysic = idInterfacePhysic;
	}

	public String getNameInterfacePhysic() {
		return nameInterfacePhysic;
	}

	public void setNameInterfacePhysic(String nameInterfacePhysic) {
		this.nameInterfacePhysic = nameInterfacePhysic;
	}

	public String getIndexInterfacePhysic() {
		return indexInterfacePhysic;
	}

	public void setIndexInterfacePhysic(String indexInterfacePhysic) {
		this.indexInterfacePhysic = indexInterfacePhysic;
	}

	public Node getNode() {
		return node;
	}

	public void setNode(Node node) {
		this.node = node;
	}

	public Set<ChargeCpu> getChargesCpu() {
		return chargesCpu;
	}

	public void setChargesCpu(Set<ChargeCpu> chargesCpu) {
		this.chargesCpu = chargesCpu;
	}
	
	public void addChargeCpu(ChargeCpu cp){
		cp.setInterfacePhysic(this);
		chargesCpu.add(cp);
	}
}
