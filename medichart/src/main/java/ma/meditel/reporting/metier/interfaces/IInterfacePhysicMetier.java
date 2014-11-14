package ma.meditel.reporting.metier.interfaces;

import java.util.List;

import ma.meditel.reporting.entities.InterfacePhysic;

public interface IInterfacePhysicMetier {
	public void addInterfacePhysic(InterfacePhysic i, Long idNode) throws Exception;
	
	public InterfacePhysic getInterfacePhysic(Long idInterfacePhysic) throws Exception;
	public InterfacePhysic getInterfacePhysic(String indexInterfacePhysic, String nameNode) throws Exception;
	public void removeInterfacePhysic(Long idInterfacePhysic) throws Exception;
	
	public List<InterfacePhysic> getAllInterfacesPhysic() throws Exception;
	public void removeAllInterfacesPhysic() throws Exception;
	
	public Long getInterfacesPhysicsCount();
}
