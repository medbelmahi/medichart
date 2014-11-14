package ma.meditel.reporting.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="BUG_AMELIORATION")
public class BugAmelioration {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ID_BUG_AMELIORATION")
	private Long idBugAmelioration;
	@Column(name="OBJET")
	private String objet;
	@Column(name="SUJET")
	private String sujet;
	@Column(name="USER_NAME")
	private String userName;
	@Column(name="DATE")
	private Date date;
	
	public BugAmelioration() {
	}

	public Long getIdBugAmelioration() {
		return idBugAmelioration;
	}

	public void setIdBugAmelioration(Long idBugAmelioration) {
		this.idBugAmelioration = idBugAmelioration;
	}

	public String getObjet() {
		return objet;
	}

	public void setObjet(String objet) {
		this.objet = objet;
	}

	public String getSujet() {
		return sujet;
	}

	public void setSujet(String sujet) {
		this.sujet = sujet;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}
	
}
