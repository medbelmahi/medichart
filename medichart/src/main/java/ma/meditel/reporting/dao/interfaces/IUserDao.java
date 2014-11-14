package ma.meditel.reporting.dao.interfaces;

import java.util.List;

import ma.meditel.reporting.entities.User;

public interface IUserDao {
	public void addUser(User u) throws Exception;
	public User getUser(Long idUser) throws Exception;
	public User getUser(String email, String password) throws Exception;
	public void mergeUser(User u) throws Exception;
	public void removeUser(Long idUser) throws Exception;
	public List<User> getAllUsers(User user) throws Exception;
	public Long getUsersCount();
	
}
