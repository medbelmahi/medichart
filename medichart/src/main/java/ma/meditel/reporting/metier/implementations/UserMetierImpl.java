package ma.meditel.reporting.metier.implementations;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ma.meditel.reporting.dao.interfaces.IUserDao;
import ma.meditel.reporting.entities.User;
import ma.meditel.reporting.metier.interfaces.IUserMetier;

@Transactional
@Service
public class UserMetierImpl implements IUserMetier {

	private IUserDao dao;
	
	@Override
	public void addUser(User u) throws Exception {
		dao.addUser(u);
	}

	@Override
	public User getUser(Long idUser) throws Exception {
		return dao.getUser(idUser);
	}

	@Override
	public User getUser(String email, String password) throws Exception {
		return dao.getUser(email, password);
	}

	@Override
	public void mergeUser(User u) throws Exception {
		dao.mergeUser(u);
	}

	@Override
	public void removeUser(Long idUser) throws Exception {
		dao.removeUser(idUser);
	}

	@Override
	public List<User> getAllUsers(User user) throws Exception {
		return dao.getAllUsers(user);
	}

	public IUserDao getDao() {
		return dao;
	}

	public void setDao(IUserDao dao) {
		this.dao = dao;
	}

	@Override
	public Long getUsersCount() {
		return dao.getUsersCount();
	}

}
