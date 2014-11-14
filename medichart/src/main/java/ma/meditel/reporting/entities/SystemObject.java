package ma.meditel.reporting.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="SYSTEM")
public class SystemObject {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ID_SYSTEM")
	private Long idSystem;
	@Column(name="NAME_SYSTEM")
	private String nameSystem;
	@Column(name="MAINTENANCE_MESSAGE")
	private String maintenanceMessage;
	@Column(name="STATUS_SYSTEM")
	private boolean status;
	
	public SystemObject() {}
	
	public SystemObject(String nameSystem, boolean status) {
		super();
		this.nameSystem = nameSystem;
		this.status = status;
	}
	public Long getIdSystem() {
		return idSystem;
	}
	public void setIdSystem(Long idSystem) {
		this.idSystem = idSystem;
	}
	public void setIsSystem(Long idSystem) {
		this.idSystem = idSystem;
	}
	public String getNameSystem() {
		return nameSystem;
	}
	public void setNameSystem(String nameSystem) {
		this.nameSystem = nameSystem;
	}
	public boolean isStatus() {
		return status;
	}
	public void setStatus(boolean status) {
		this.status = status;
	}
	
	public String getMaintenanceMessage() {
		return maintenanceMessage;
	}

	public void setMaintenanceMessage(String maintenanceMessage) {
		this.maintenanceMessage = maintenanceMessage;
	}

}
