package services;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NonUniqueResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;

import org.apache.commons.lang3.time.DateUtils;

import classes.RankingAggregateData;
import classes.SummaryAggregateData;
import entities.Area;
import entities.Farm;
import entities.Production;
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
		if (result.size() > 0) {
			// Order the list of farmers
			if (desc == true) { // DESC
				Collections.sort(result, (a, b) -> b.compareTo(a, date));
			} else { // ASC
				Collections.sort(result, (a, b) -> a.compareTo(b, date));
			}
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
		} catch (PersistenceException e) {
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
			throw new RankingAggregateDataException("[RankingAggregateDataException] " + e.getMessage());
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
			throw new FindBestOrWorstFarmerException(
					"[FindBestOrWorstFarmerException] ERROR: Could not find the best farmer.");
		}
		if (user.isEmpty()) {
			return null;
		} else if (user.size() == 1) {
			return user.get(0);
		}
		throw new NonUniqueResultException(
				"[NonUniqueResultException] WARNING: More than one user is in first position, check manually.");
	}

	public Area getBestArea(boolean desc, Date date) throws AreaRetrievalException {
		List<Area> areas = null;
		areas = this.getAllAreas();
		if (areas.size() > 0) {
			if (desc == true) { // DESC
				Collections.sort(areas, (a, b) -> b.compareTo(a, date));
			} else { // ASC
				Collections.sort(areas, (a, b) -> a.compareTo(b, date));
			}
		}
		return areas.get(0);
	}

	public List<Area> getAllAreas() throws AreaRetrievalException {
		List<Area> areas = null;
		try {
			areas = em.createNamedQuery("Area.findAll", Area.class).getResultList();
		} catch (PersistenceException e) {
			throw new AreaRetrievalException("[AreaRetrievalException] ERROR: Could not retrieve areas.");
		}
		return areas;
	}

	@SuppressWarnings("deprecation")
	public List<SummaryAggregateData> getLastYearMonthlyProductionSummary() throws AreaRetrievalException {
		List<SummaryAggregateData> result = new ArrayList<>();
		List<Area> areas = getAllAreas();
		
		
		Date lastYear = DateUtils.addYears(new Date(), -1);
		int startingMonth = lastYear.getMonth();
		lastYear = DateUtils.setDays(lastYear, 1);
		lastYear = DateUtils.addDays(lastYear, -1);
		Date firstDayOfMonth = DateUtils.setDays(new Date(), 1);
		
		for (Area area : areas) {
			SummaryAggregateData thisArea = new SummaryAggregateData(area.getName());
			Map<Integer, Integer> data = new HashMap<>();
			
			for (Farm farm : area.getFarms()) {
				List<Production> productions = new ArrayList<>(farm.getProductions());
				
				for (Production production : productions) {
					
					if (production.getDate().after(lastYear) && production.getDate().before(firstDayOfMonth)) {
						int month = production.getDate().getMonth();
						int amount = production.getAmount();
						
						if (data.get(month) == null) {
							data.put(month, 0);
						}
						
						data.put(month, data.get(month) + amount);
					}
				}
			}
			thisArea.setValues(SummaryAggregateData.getDataFromMap(data, startingMonth));
			result.add(thisArea);
		}
		
		return result;
	}
}
