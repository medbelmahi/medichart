package ma.meditel.reporting.entities;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue(User.ROLE_ADMIN)
public class Admin extends User {
	private static final long serialVersionUID = 2989811537071663512L;

	public Admin() {
		super();
	}

	public Admin(String email, String password, String nom, String prenom) {
		super(email, password, nom, prenom);
	}

	@Override
	public String getRole() {
		return User.ROLE_ADMIN;
	}

	@Override
	public String getRoleDescription() {
		return "Adiminstrateur";
	}
	
}
