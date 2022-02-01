package services;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NonUniqueResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;

import entities.Forecast;
import entities.Humidityofsoil;
import entities.Production;
import entities.User;
import enums.TypeOfProduct;
import exceptions.CredentialsException;
import exceptions.ForecastRetrievalException;
import exceptions.HumidityRetrievalException;
import exceptions.InsertProductionException;

@Stateless
public class UserService {
	@PersistenceContext(unitName = "BertelliSavinoVinatiProject")
	private EntityManager em;

	public UserService() {
	}

	public User checkCredentials(String mail, String pwd) throws CredentialsException, NonUniqueResultException {
		List<User> users = null;
		try {
			users = em.createNamedQuery("User.checkCredentials", User.class).setParameter(1, mail).setParameter(2, pwd)
					.getResultList();
		} catch (PersistenceException e) {
			throw new CredentialsException("[CredentialsException] ERROR: Could not verify credentials.");
		}
		if (users.isEmpty()) {
			return null;
		} else if (users.size() == 1) {
			return users.get(0);
		}
		throw new NonUniqueResultException(
				"[NonUniqueResultException] ERROR: More than one user registered with the same credentials.");
	}

	public Forecast getForecast(User user, Date date) throws ForecastRetrievalException {
		List<Forecast> forecasts = null;
		try {
			forecasts = em.createNamedQuery("Forecast.findByDate", Forecast.class).setParameter(1, date)
					.setParameter(2, user.getFarm().getArea().getId()).getResultList();
		} catch (PersistenceException e) {
			throw new ForecastRetrievalException("[ForecastRetrievalException] ERORR: Count not get today forecasts");
		}
		if (forecasts.isEmpty()) {
			return null;
		} else if (forecasts.size() == 1) {
			return forecasts.get(0);
		}
		Collections.sort(forecasts, (a, b) -> b.getCreationdate().compareTo(a.getCreationdate()));
		return forecasts.get(0);
	}

	public User getUserById(int id) {
		return em.find(User.class, id);
	}

	public Humidityofsoil getHumidity(User user) throws HumidityRetrievalException, NonUniqueResultException {
		List<Humidityofsoil> humidity = null;
		try {
			humidity = em.createNamedQuery("Humidityofsoil.findByData", Humidityofsoil.class)
					.setParameter(1, new Date()).setParameter(2, user.getFarm().getId()).getResultList();
			for (Humidityofsoil a : humidity) {
				System.out.println(a.getClassification());
			}
		} catch (PersistenceException e) {
			throw new HumidityRetrievalException("[HumidityRetrievalException] ERROR: Could not get today humidity.");
		}
		if (humidity.isEmpty()) {
			return null;
		} else if (humidity.size() == 1) {
			return humidity.get(0);
		}
		throw new NonUniqueResultException("[NonUniqueResultException] ERROR: More than one humidity datum for today.");
	}

	public User insertProduction(User user, TypeOfProduct typeOfProduct, String problemsFaced, int amount, Date date)
			throws InsertProductionException {
		user = getUserById(user.getId());
		Production production = new Production(typeOfProduct, date, amount, problemsFaced, user.getFarm());
		if(user.getFarm().getProductions() == null) {
			user.getFarm().setProductions(new ArrayList<Production>());
		}
		user.getFarm().addProduction(production);
		try {
			em.merge(user);
			em.flush();
			em.refresh(user);
		} catch (PersistenceException e) {
			throw new InsertProductionException("[InsertProductionException] Could not insert production.");
		}
		return user;
	}
	
}
