package ma.meditel.reporting.metier.implementations;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ma.meditel.reporting.dao.interfaces.IInterfaceDao;
import ma.meditel.reporting.dao.interfaces.ITrafficPerClassDao;
import ma.meditel.reporting.entities.Interface;
import ma.meditel.reporting.entities.Node;
import ma.meditel.reporting.metier.interfaces.IInterfaceMetier;

@Transactional
@Service
public class InterfaceMetierImpl implements IInterfaceMetier {

	private IInterfaceDao dao;
	private ITrafficPerClassDao dao_TrafficClass;
	
	
	
	

	public ITrafficPerClassDao getDao_TrafficClass() {
		return dao_TrafficClass;
	}

	public void setDao_TrafficClass(ITrafficPerClassDao dao_TrafficClass) {
		this.dao_TrafficClass = dao_TrafficClass;
	}

	@Override
	public void addInteface(Interface i, Long idNode) throws Exception {
		dao.addInteface(i, idNode);
	}

	@Override
	public Interface getInterface(Long idInterface) throws Exception {
		return dao.getInterface(idInterface);
	}

	@Override
	public Interface getInterfaceWithCollectionIntialize(Long idInterface) throws Exception {
		return dao.getInterfaceWithCollectionIntialize(idInterface);
	}

	@Override
	public Interface getInterface(String indexInterface, String nameNode) throws Exception {
		return dao.getInterface(indexInterface, nameNode);
	}

	@Override
	public Interface getInterfaceWithCollectionIntialize(String indexInterface, String nameNode) throws Exception {
		return dao.getInterfaceWithCollectionIntialize(indexInterface, nameNode);
	}

	@Override
	public void removeInterface(Long idInterface) throws Exception {
		dao.removeInterface(idInterface);
	}

	@Override
	public List<Interface> getAllInterfaces() throws Exception {
		return dao.getAllInterfaces();
	}

	@Override
	public void removeAllInterfaces() throws Exception {
		dao.removeAllInterfaces();
	}
	
	@Override
	public Map<String, List<Interface>> getAllInterfacesGroupeByNodename() throws Exception {
		List<Interface> interfaces = dao.getAllInterfaces();
		Map<String, List<Interface>> map = new HashMap<>();
		
		for(Interface i : interfaces){
			String nodename = i.getNode().getNodeName();
			
			if(!map.containsKey(nodename)){
				map.put(nodename, new ArrayList<Interface>());
			}
			
			map.get(nodename).add(i);
		}
		
		return map;
	}
	
	@Override
	public Map<String, List<Interface>> getAllInterfacesGroupeByNodenameActivated() throws Exception {
		List<Interface> interfaces = dao.getAllInterfacesActivated();
		
		
		//--------------------------------------------------
		
		
		Map<String, List<Interface>> map = new HashMap<>();
		
		for(Interface i : interfaces){
			
			String nodename = i.getNode().getNodeName();
			
			
			if(!map.containsKey(nodename)){
				map.put(nodename, new ArrayList<Interface>());
			}
			
			map.get(nodename).add(i);
		}
		
		return map;
	}
	
	private Boolean existId(List<Long> idList, Long idInterface)
	{
		for(Long l : idList)
		{
			if(l.compareTo(idInterface) == 0) return true;
		}
		return false;
	}
	
	//new 2014_09_09
	@Override
	public Map<String, List<Interface>> getAllInterfacesGroupeByNodenameActivated_IsClass() throws Exception {
		List<Interface> interfaces = dao.getAllInterfacesActivated();
		
		List<Long> idList = dao_TrafficClass.getListOfIdInterfaceInClass();
		
		Map<String, List<Interface>> map = new HashMap<>();
		
		for(Interface i : interfaces){
			
			if(existId(idList, i.getIdInterface())){
				String nodename = i.getNode().getNodeName();
				
				if(!map.containsKey(nodename)){
					map.put(nodename, new ArrayList<Interface>());
				}
				
				map.get(nodename).add(i);
			}
			
		}
		
		return map;
	}
	
	public IInterfaceDao getDao() {
		return dao;
	}

	public void setDao(IInterfaceDao dao) {
		this.dao = dao;
	}

	@Override
	public Long getInterfacesLogicsCount() {
		return dao.getInterfacesLogicsCount();
	}

	@Override
	public List<Node> getAllNodesOfInterfacesInClass() throws Exception {
		List<Interface> interfaces = dao.getAllInterfacesActivated();
		
		List<Long> idList = dao_TrafficClass.getListOfIdInterfaceInClass();
		
		List<Node> listeNodes = new ArrayList<Node>();
		
		for(Interface i : interfaces){
			
			if(existId(idList, i.getIdInterface())){
				
				if(!listeNodes.contains(i.getNode()) ) 
					{
						if(i.isActivated())	listeNodes.add(i.getNode());
					}
				
			}
			
		}
		
		return listeNodes;
	}

	@Override
	public void margeInterface(Interface inter) {
		dao.margeInterface(inter);
		
	}

	@Override
	public List<Node> getAllNodesActivated() throws Exception {
		List<Interface> interfaces = dao.getAllInterfacesActivated();
		
		List<Node> tempList = new ArrayList<Node>();
		//--------------------------------------------------
		
		
		
		for(Interface i : interfaces){
			
			if(!tempList.contains(i.getNode())){
				tempList.add(i.getNode());
			}
			
		}
		
		return tempList;
	}

}
