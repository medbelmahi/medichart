package ma.meditel.reporting.metier.implementations;

import java.util.List;

import ma.meditel.reporting.dao.interfaces.IInterfacePhysicDao;
import ma.meditel.reporting.entities.InterfacePhysic;
import ma.meditel.reporting.metier.interfaces.IInterfacePhysicMetier;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
public class InterfacePhysicMetierImpl implements IInterfacePhysicMetier {
	
	private IInterfacePhysicDao dao;
	
	@Override
	public void addInterfacePhysic(InterfacePhysic i, Long idNode) throws Exception {
		dao.addInterfacePhysic(i, idNode);
	}

	@Override
	public InterfacePhysic getInterfacePhysic(Long idInterfacePhysic) throws Exception {
		return dao.getInterfacePhysic(idInterfacePhysic);
	}

	@Override
	public InterfacePhysic getInterfacePhysic(String indexInterfacePhysic, String nameNode) throws Exception {
		return dao.getInterfacePhysic(indexInterfacePhysic, nameNode);
	}

	@Override
	public void removeInterfacePhysic(Long idInterfacePhysic) throws Exception {
		dao.removeInterfacePhysic(idInterfacePhysic);
	}

	@Override
	public List<InterfacePhysic> getAllInterfacesPhysic() throws Exception {
		return dao.getAllInterfacesPhysic();
	}

	@Override
	public void removeAllInterfacesPhysic() throws Exception {
		dao.removeAllInterfacesPhysic();
	}

	public IInterfacePhysicDao getDao() {
		return dao;
	}

	public void setDao(IInterfacePhysicDao dao) {
		this.dao = dao;
	}

	@Override
	public Long getInterfacesPhysicsCount() {
		return dao.getInterfacesPhysicsCount();
	}
	
}
