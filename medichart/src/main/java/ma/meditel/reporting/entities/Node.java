package ma.meditel.reporting.entities;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="NODES")
public class Node implements Serializable {
	private static final long serialVersionUID = 1564633534187390258L;
	
	//Constants Rows log
	public static final int DEVICE_CATEGORY		= 1;
	public static final int NODE_NAME 			= 2;
	public static final int HOST_NAME 			= 3;
	public static final int MANAGEMENT_ADDRESS 	= 4;
	public static final int DEVICE_PROFILE	 	= 6;
	public static final int SYSTEM_DESCRIPTION 	= 12;
	public static final int NODE_MANAGED_MODE 	= 13;
	public static final int SYSTEM_OBJECT_ID 	= 14;
	public static final int DEVICE_VENDOR	 	= 15;
	public static final int DEVICE_FAMILY	 	= 16;
	//End
	
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ID_NODE")
	private Long idNode;
	@Column(name="DEVICE_CAT")
	private String deviceCategory;
	@Column(name="NODE_NAME", unique=true)
	private String nodeName;
	@Column(name="MANAGEMENT_ADDRESS")
	private String managementAddress;
	@Column(name="DEVICE_PROFILE")
	private String deviceProfile;
	@Column(name="SYSTEM_DESCRIPTION")
	private String systemDescription;
	@Column(name="NODE_MANAGED")
	private String nodeManaged;
	@Column(name="SYSTEM_OBJECT_ID")
	private String systemObjectId;
	@Column(name="DEVICE_VENDOR")
	private String deviceVendor;
	@Column(name="DEVICE_FAMILY")
	private String deviceFamily;
	
	@OneToMany(mappedBy="node", cascade={CascadeType.ALL, CascadeType.MERGE, CascadeType.PERSIST}, orphanRemoval=true)
	private Set<Interface> interfaces = new HashSet<>();
	
	@OneToMany(mappedBy="node", cascade={CascadeType.ALL, CascadeType.MERGE, CascadeType.PERSIST}, orphanRemoval=true)
	private Set<Interface> interfacesPhysic = new HashSet<>();
	
	public Node() {
		super();
	}

	public Long getIdNode() {
		return idNode;
	}

	public void setIdNode(Long idNode) {
		this.idNode = idNode;
	}

	public String getDeviceCategory() {
		return deviceCategory;
	}

	public void setDeviceCategory(String deviceCategory) {
		this.deviceCategory = deviceCategory;
	}

	public String getNodeName() {
		return nodeName;
	}

	public void setNodeName(String nodeName) {
		this.nodeName = nodeName;
	}

	public String getManagementAddress() {
		return managementAddress;
	}

	public void setManagementAddress(String managementAddress) {
		this.managementAddress = managementAddress;
	}

	public String getDeviceProfile() {
		return deviceProfile;
	}

	public void setDeviceProfile(String deviceProfile) {
		this.deviceProfile = deviceProfile;
	}

	public String getSystemDescription() {
		return systemDescription;
	}

	public void setSystemDescription(String systemDescription) {
		this.systemDescription = systemDescription;
	}

	public String getNodeManaged() {
		return nodeManaged;
	}

	public void setNodeManaged(String nodeManaged) {
		this.nodeManaged = nodeManaged;
	}

	public String getSystemObjectId() {
		return systemObjectId;
	}

	public void setSystemObjectId(String systemObjectId) {
		this.systemObjectId = systemObjectId;
	}

	public String getDeviceVendor() {
		return deviceVendor;
	}

	public void setDeviceVendor(String deviceVendor) {
		this.deviceVendor = deviceVendor;
	}

	public String getDeviceFamily() {
		return deviceFamily;
	}

	public void setDeviceFamily(String deviceFamily) {
		this.deviceFamily = deviceFamily;
	}

	public Set<Interface> getInterfaces() {
		return interfaces;
	}

	public void setInterfaces(Set<Interface> interfaces) {
		this.interfaces = interfaces;
	}
	
	public Set<Interface> getInterfacesPhysic() {
		return interfacesPhysic;
	}

	public void setInterfacesPhysic(Set<Interface> interfacesPhysic) {
		this.interfacesPhysic = interfacesPhysic;
	}
	
	public void addInterface(Interface i){
		i.setNode(this);
		this.getInterfaces().add(i);
	}

}
