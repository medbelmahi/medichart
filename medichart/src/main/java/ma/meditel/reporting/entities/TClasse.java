package ma.meditel.reporting.entities;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class TClasse {
	//Classes constants name
	public static final String[] TRAFFICS_CLASSE_NAME = {"0", "1", "2", "3", "4", "5", "6", "7"};
	
	@Column(name="REF_TCLASSE")
	private Long refTClasse;
	@Column(name="NAME_TCLASSE")
	private String nameTClasse;
	
	public TClasse() {
		super();
	}

	public TClasse(Long refTClasse, String nameTClasse) {
		this.refTClasse = refTClasse;
		this.nameTClasse = nameTClasse;
	}

	public Long getRefTClasse() {
		return refTClasse;
	}

	public void setRefTClasse(Long refTClasse) {
		this.refTClasse = refTClasse;
	}

	public String getNameTClasse() {
		return nameTClasse;
	}

	public void setNameTClasse(String nameTClasse) {
		this.nameTClasse = nameTClasse;
	}
	
}
