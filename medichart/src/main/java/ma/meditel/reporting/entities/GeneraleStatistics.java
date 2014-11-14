package ma.meditel.reporting.entities;

public class GeneraleStatistics {
	private boolean systemState;
	private Long usersNombre;
	private Long nodesNombre;
	private Long interfacesLogicNombre;
	private Long interfacesPhysicNombre;
	
	public GeneraleStatistics() {
		super();
	}

	public boolean isSystemState() {
		return systemState;
	}

	public void setSystemState(boolean systemState) {
		this.systemState = systemState;
	}

	public Long getUsersNombre() {
		return usersNombre;
	}

	public void setUsersNombre(Long usersNombre) {
		this.usersNombre = usersNombre;
	}

	public Long getNodesNombre() {
		return nodesNombre;
	}

	public void setNodesNombre(Long nodesNombre) {
		this.nodesNombre = nodesNombre;
	}

	public Long getInterfacesLogicNombre() {
		return interfacesLogicNombre;
	}

	public void setInterfacesLogicNombre(Long interfacesLogicNombre) {
		this.interfacesLogicNombre = interfacesLogicNombre;
	}

	public Long getInterfacesPhysicNombre() {
		return interfacesPhysicNombre;
	}

	public void setInterfacesPhysicNombre(Long interfacesPhysicNombre) {
		this.interfacesPhysicNombre = interfacesPhysicNombre;
	}
	
	public String getSystemStatusString(){
		if(systemState)
			return "Activé";
		return "Désactivé";
	}
}
