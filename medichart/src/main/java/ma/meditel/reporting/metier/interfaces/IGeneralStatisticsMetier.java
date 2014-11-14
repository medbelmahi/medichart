package ma.meditel.reporting.metier.interfaces;

public interface IGeneralStatisticsMetier {
	public boolean getSystemStatus();
	public Long getUsersNombre();
	public Long getNodesNombre();
	public Long getInterfacesLogicNombre();
	public Long getInterfacesPhysicNombre();
}
