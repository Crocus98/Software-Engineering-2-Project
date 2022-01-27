package services;

import java.util.Collections;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;

import entities.User;
import exceptions.FindBestOrWorstUserException;

@Stateless
public class DataminerService {
	@PersistenceContext(unitName = "BertelliSavinoVinatiProject")
	private EntityManager em;
	@EJB(name = "services/UserService")
	private UserService userService;
	
	public DataminerService() {
		
	}
	
	public User getBestFarmer(boolean value) throws FindBestOrWorstUserException {
		List<User> result = null;
		try {
			result = em.createNamedQuery("User.findAllFarmers",User.class).getResultList();
			Collections.sort(result, (a,b) -> b.getFarm().getProductionAmountM2().compareTo(a.getFarm().getProductionAmountM2()));
			for (int i = 0; i<result.size(); i++)
			{
				System.out.println(result.get(i).getMail());
			}
		} 
		catch (PersistenceException e) {
			throw new FindBestOrWorstUserException("ERROR: Could not find the best farmer.");
		}
		return null;
	}
	
}
