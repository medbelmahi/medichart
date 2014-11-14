package ma.meditel.reporting.metier.implementations;

import java.util.List;

import ma.meditel.reporting.dao.interfaces.IInterfaceAutorizedAliasDao;
import ma.meditel.reporting.entities.InterfaceAutorizedAlias;
import ma.meditel.reporting.metier.interfaces.IInterfaceAutorizedAliasMetier;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
public class InterfaceAutorizedAliasMetierImpl implements IInterfaceAutorizedAliasMetier {

	private IInterfaceAutorizedAliasDao dao;
	
	@Override
	public void addInterfaceAutorizedAlias(InterfaceAutorizedAlias i) {
		dao.addInterfaceAutorizedAlias(i);
	}

	@Override
	public InterfaceAutorizedAlias getInterfaceAutorizedAlias(Long id) {
		return dao.getInterfaceAutorizedAlias(id);
	}

	@Override
	public void removeInterfaceAutorizedAlias(Long id) {
		dao.removeInterfaceAutorizedAlias(id);
	}

	@Override
	public List<InterfaceAutorizedAlias> getAllInterfaceAutorizedAlias() {
		return dao.getAllInterfaceAutorizedAlias();
	}

	public IInterfaceAutorizedAliasDao getDao() {
		return dao;
	}

	public void setDao(IInterfaceAutorizedAliasDao dao) {
		this.dao = dao;
	}

}
