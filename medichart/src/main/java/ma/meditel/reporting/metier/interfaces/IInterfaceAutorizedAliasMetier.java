package ma.meditel.reporting.metier.interfaces;

import java.util.List;

import ma.meditel.reporting.entities.InterfaceAutorizedAlias;

public interface IInterfaceAutorizedAliasMetier {
	public void addInterfaceAutorizedAlias(InterfaceAutorizedAlias i);
	public InterfaceAutorizedAlias getInterfaceAutorizedAlias(Long id);
	public void removeInterfaceAutorizedAlias(Long id);
	public List<InterfaceAutorizedAlias> getAllInterfaceAutorizedAlias();
}
