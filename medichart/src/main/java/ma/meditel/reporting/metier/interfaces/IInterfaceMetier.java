package ma.meditel.reporting.metier.interfaces;

import java.util.List;
import java.util.Map;

import ma.meditel.reporting.entities.Interface;
import ma.meditel.reporting.entities.Node;

public interface IInterfaceMetier {
	public void addInteface(Interface i, Long idNode) throws Exception;
	public Interface getInterface(Long idInterface) throws Exception;
	public Interface getInterfaceWithCollectionIntialize(Long idInterface) throws Exception;
	public Interface getInterface(String indexInterface, String nameNode) throws Exception;
	public Interface getInterfaceWithCollectionIntialize(String indexInterface, String nameNode) throws Exception;
	public void removeInterface(Long idInterface) throws Exception;
	
	public List<Interface> getAllInterfaces() throws Exception;
	public void removeAllInterfaces() throws Exception;
	
	public Map<String, List<Interface>> getAllInterfacesGroupeByNodename() throws Exception;
	public Map<String, List<Interface>> getAllInterfacesGroupeByNodenameActivated() throws Exception;
	
	public Long getInterfacesLogicsCount();
	Map<String, List<Interface>> getAllInterfacesGroupeByNodenameActivated_IsClass()
			throws Exception;
	
	public List<Node> getAllNodesOfInterfacesInClass() throws Exception; 
	public void margeInterface(Interface inter);
	public List<Node> getAllNodesActivated() throws Exception;
}
