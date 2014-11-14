package ma.meditel.reporting.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

@Entity
@Table(name="USERS")
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="ROLE", length=5)
abstract public class User implements Serializable {
	private static final long serialVersionUID = 1954680242600806615L;
	
	public static final String ROLE_ADMIN = "admin";
	public static final String ROLE_GUEST = "guest";
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ID_USER")
	protected Long idUser;
	@Column(name="EMAIL", unique=true)
	protected String email;
	@Column(name="PASSWORD")
	protected String password;
	@Column(name="NOM")
	protected String nom;
	@Column(name="PRENOM")
	protected String prenom;
	
	public User() {
		super();
	}
	
	public User(String email, String password, String nom, String prenom) {
		this.email = email;
		this.password = password;
		this.nom = nom;
		this.prenom = prenom;
	}
	
	public Long getIdUser() {
		return idUser;
	}
	public void setIdUser(Long idUser) {
		this.idUser = idUser;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getPrenom() {
		return prenom;
	}
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}
	
	public String getFullName(){
		return prenom + " " + nom.toUpperCase();
	}

	@Override
	public String toString() {
		return "User [idUser=" + idUser + ", email=" + email + ", password="
				+ password + ", nom=" + nom + ", prenom=" + prenom + "]";
	}
	
	public abstract String getRole();
	public abstract String getRoleDescription();
	
}
