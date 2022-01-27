package services;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NonUniqueResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;

import entities.User;
import exceptions.CredentialsException;


@Stateless
public class UserService {
	@PersistenceContext(unitName = "BertelliSavinoVinatiProject")
	private EntityManager em;
	
	public UserService() {
	}
	
	public User checkCredentials(String mail, String pwd) throws CredentialsException, NonUniqueResultException {
		List<User> users = null;
		try {
			users = em.createNamedQuery("User.checkCredentials", User.class).setParameter(1, mail).setParameter(2, pwd).getResultList();
		} 
		catch (PersistenceException e) {
			throw new CredentialsException("ERROR: Could not verify credentials.");
		}
		if (users.isEmpty()) {
			return null;
		}
		else if (users.size() == 1) {
			return users.get(0);
		}
		throw new NonUniqueResultException("ERROR: More than one user registered with the same credentials.");
	}
	
	public User getUserById(int id) {
		return em.find(User.class, id);
	}
	
}
