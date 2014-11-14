package ma.meditel.reporting.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="INTERFACE_AUTORIZED_ALIAS")
public class InterfaceAutorizedAlias {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ID_AUTORIZED_ALIAS")
	private Long IdInterfaceAutorizedAlias;
	@Column(name="PRE_ALIAS")
	private String preAlias;
	
	public InterfaceAutorizedAlias() {
	}
	
	public InterfaceAutorizedAlias(String preAlias) {
		setPreAlias(preAlias);
	}
	
	public Long getIdInterfaceAutorizedAlias() {
		return IdInterfaceAutorizedAlias;
	}
	
	public void setIdInterfaceAutorizedAlias(Long idInterfaceAutorizedAlias) {
		IdInterfaceAutorizedAlias = idInterfaceAutorizedAlias;
	}
	
	public String getPreAlias() {
		return preAlias;
	}
	
	public void setPreAlias(String preAlias) {
		this.preAlias = preAlias.toUpperCase();
	}
	
}
