package ma.meditel.reporting.dao.interfaces;

import java.util.List;

import ma.meditel.reporting.entities.Interface;


public interface IInterfaceDao {
	public void addInteface(Interface i, Long idNode) throws Exception;
	public Interface getInterface(Long idInterface) throws Exception;
	public Interface getInterfaceWithCollectionIntialize(Long idInterface) throws Exception;
	public Interface getInterface(String indexInterface, String nameNode) throws Exception;
	public Interface getInterfaceWithCollectionIntialize(String indexInterface, String nameNode) throws Exception;
	public void removeInterface(Long idInterface) throws Exception;
	
	public List<Interface> getAllInterfaces() throws Exception;
	public List<Interface> getAllInterfacesActivated() throws Exception;
	public void removeAllInterfaces() throws Exception;
	Long getInterfacesLogicsCount();
	public void margeInterface(Interface inter);
}
