package ma.meditel.reporting.metier.implementations;

import ma.meditel.reporting.metier.interfaces.IGeneralStatisticsMetier;
import ma.meditel.reporting.metier.interfaces.IInterfaceMetier;
import ma.meditel.reporting.metier.interfaces.IInterfacePhysicMetier;
import ma.meditel.reporting.metier.interfaces.INodeMetier;
import ma.meditel.reporting.metier.interfaces.ISystemObjectMetier;
import ma.meditel.reporting.metier.interfaces.IUserMetier;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class GeneralStatisticsMetierImpl implements IGeneralStatisticsMetier {

	@Autowired
	public ISystemObjectMetier systemMetier;
	
	@Autowired
	public IUserMetier userMetier;
	
	@Autowired
	public INodeMetier nodeMetier;
	
	@Autowired
	public IInterfaceMetier interfaceLogicMetier;
	
	@Autowired
	public IInterfacePhysicMetier interfacePhysicMetier;
	
	@Override
	public boolean getSystemStatus() {
		try {
			return systemMetier.getSystemObject(1L).isStatus();
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public Long getUsersNombre() {
		return userMetier.getUsersCount();
	}

	@Override
	public Long getNodesNombre() {
		return nodeMetier.getNodesCount();
	}

	@Override
	public Long getInterfacesLogicNombre() {
		return interfaceLogicMetier.getInterfacesLogicsCount();
	}

	@Override
	public Long getInterfacesPhysicNombre() {
		return interfacePhysicMetier.getInterfacesPhysicsCount();
	}

}
