package ma.meditel.reporting.util;

import java.util.List;

import ma.meditel.reporting.entities.InterfaceAutorizedAlias;

import org.springframework.stereotype.Component;

@Component
public class InterfaceAutorizedAliasHelper {
	
	public static boolean isAliasAutorized(String alias, List<InterfaceAutorizedAlias> prefixes){
		
		for(InterfaceAutorizedAlias pre : prefixes){
			if(alias.toUpperCase().indexOf(pre.getPreAlias()) >= 0){
				return true;
			}
		}
		return false;
	}
}
