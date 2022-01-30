package services;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NonUniqueResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;

import classes.RankingAggregateData;
import entities.Area;
import entities.User;
import exceptions.AreaRetrievalException;
import exceptions.FarmersOrderingException;
import exceptions.FindBestOrWorstFarmerException;
import exceptions.RankingAggregateDataException;

@Stateless
public class DataminerService {
	@PersistenceContext(unitName = "BertelliSavinoVinatiProject")
	private EntityManager em;
	@EJB(name = "services/UserService")
	private UserService userService;

	public DataminerService() {

	}

	public List<User> getFarmersInLexicographicOrder(int limit_number, boolean desc, Area area, Date date)
			throws FarmersOrderingException { // Leave area and date null if you want data for all dates and areas.
												// Limit number = n of objects wanted (0 for all)
		List<User> result = null;
		try {
			if (area == null) {
				result = em.createNamedQuery("User.findAllFarmers", User.class).getResultList();
			} else {
				result = em.createNamedQuery("User.findAllFarmersPerArea", User.class).setParameter(1, area.getId())
						.getResultList();
			}
		} catch (PersistenceException e) {
			throw new FarmersOrderingException("[FarmersOrderingException] ERROR: Could not create a farmer ordering.");
		}

		// Order the list of farmers
		if (desc == true) { // DESC
			Collections.sort(result, (a, b) -> b.compareTo(a, date));
		} else { // ASC
			Collections.sort(result, (a, b) -> a.compareTo(b, date));
		}
		if (limit_number <= 0 || limit_number >= result.size()) {
			return result;
		}
		return result.subList(0, limit_number);
	}
	
	public Area getAreaFromId(int idArea) throws AreaRetrievalException {
		Area area = null;
		try {
			area = em.find(Area.class, idArea);
		} 
		catch (PersistenceException e) {
			throw new AreaRetrievalException("[AreaRetrievalException] ERROR: Could not find area.");
		}
		return area;
	}

	public List<RankingAggregateData> getRankingAggregateData(int limit_number, boolean desc, int idArea, Date date)
			throws RankingAggregateDataException {
		List<User> farmers = null;
		try {
			farmers = getFarmersInLexicographicOrder(limit_number, desc, getAreaFromId(idArea), date);
		} catch (FarmersOrderingException | AreaRetrievalException e) {
			throw new RankingAggregateDataException(
					"[RankingAggregateDataException] " + e.getMessage());
		}
		if (farmers == null || farmers.isEmpty()) {
			return null;
		}
		List<RankingAggregateData> data = new ArrayList<>();
		for (int i = 0; i < farmers.size(); i++) {
			data.add(new RankingAggregateData(farmers.get(i), date));
		}
		return data;
	}

	public User getBestFarmer(boolean desc, Area area, Date date) throws FindBestOrWorstFarmerException {
		List<User> user = null;
		try {
			user = getFarmersInLexicographicOrder(1, desc, area, date);
		} catch (FarmersOrderingException e) {
			throw new FindBestOrWorstFarmerException("[FindBestOrWorstFarmerException] ERROR: Could not find the best farmer.");
		}
		if (user.isEmpty()) {
			return null;
		} else if (user.size() == 1) {
			return user.get(0);
		}
		throw new NonUniqueResultException("[NonUniqueResultException] WARNING: More than one user is in first position, check manually.");
	}
	
	public List <Area> getAllAreas() throws AreaRetrievalException{
		List<Area> areas = null;
		try {
			areas = em.createNamedQuery("Area.findAll", Area.class).getResultList();
		}catch(PersistenceException e){
			throw new AreaRetrievalException("[AreaRetrievalException] ERROR: Could not retrieve areas.");
		}
		return areas;
	}
}
