package ma.meditel.reporting.entities;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue(User.ROLE_GUEST)
public class Guest extends User {
	private static final long serialVersionUID = 3430132135830534452L;

	public Guest() {
		super();
	}

	public Guest(String email, String password, String nom, String prenom) {
		super(email, password, nom, prenom);
	}

	@Override
	public String getRole() {
		return User.ROLE_GUEST;
	}

	@Override
	public String getRoleDescription() {
		return "Guest";
	}

}
