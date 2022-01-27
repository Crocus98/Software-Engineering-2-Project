package services;

import java.util.Collections;
import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NonUniqueResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;

import entities.Area;
import entities.User;
import exceptions.FarmersOrderingException;
import exceptions.FindBestOrWorstFarmerException;

@Stateless
public class DataminerService {
	@PersistenceContext(unitName = "BertelliSavinoVinatiProject")
	private EntityManager em;
	@EJB(name = "services/UserService")
	private UserService userService;
	
	public DataminerService() {
		
	}
	
	public List<User> getFarmersInLexicographicOrder(int limit_number, boolean desc, Area area, Date date) 
			throws FarmersOrderingException { //Leave area and date null if you want data for all dates and areas
		List<User> result = null;
		try {
			if(area == null) {
				result = em.createNamedQuery("User.findAllFarmers",User.class).getResultList();
			}
			else {
				result = em.createNamedQuery("User.findAllFarmersPerArea",User.class).setParameter(1, area.getId()).getResultList();
			}
		}
		catch (PersistenceException e) {
			throw new FarmersOrderingException("ERROR: Could not create a farmer ordering.");
		}
		
		//Order the list of farmers
		if(desc == true) { //DESC
			Collections.sort(result, (a,b) -> b.compareTo(a, date));
		}
		else { //ASC
			Collections.sort(result, (a,b) -> a.compareTo(b, date));
		}
		
		return result;
	}
	
	public User getBestFarmer(boolean desc, Area area, Date date) throws FindBestOrWorstFarmerException {
		List<User> user = null;
		try {
			user = getFarmersInLexicographicOrder(1, desc, area, date);
		}
		catch(FarmersOrderingException e){
			throw new FindBestOrWorstFarmerException("ERROR: Could not find the best farmer.");
		}
		if(user.isEmpty()) {
			return null;
		}
		else if (user.size() == 1) {
			return user.get(0);
		}
		throw new NonUniqueResultException("WARNING: More than one user is in first position, check manually.");
	}
}
