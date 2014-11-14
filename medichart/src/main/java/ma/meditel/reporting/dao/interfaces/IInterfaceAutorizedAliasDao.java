package ma.meditel.reporting.dao.interfaces;

import java.util.List;

import ma.meditel.reporting.entities.InterfaceAutorizedAlias;

public interface IInterfaceAutorizedAliasDao {
	public void addInterfaceAutorizedAlias(InterfaceAutorizedAlias i);
	public InterfaceAutorizedAlias getInterfaceAutorizedAlias(Long id);
	public void removeInterfaceAutorizedAlias(Long id);
	public List<InterfaceAutorizedAlias> getAllInterfaceAutorizedAlias();
}
