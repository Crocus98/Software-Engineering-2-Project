package services;

import java.util.Collections;
import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NonUniqueResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;

import entities.Forecast;
import entities.User;
import exceptions.CredentialsException;
import exceptions.ForecastRetrievalException;

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

	public Forecast getForecast(User user) throws ForecastRetrievalException {
		List<Forecast> forecasts = null;
		try {
			forecasts = em.createNamedQuery("Forecast.findByDate", Forecast.class)
					.setParameter(1, new Date()).setParameter(2, user.getFarm().getArea().getId()).getResultList();
		} catch (PersistenceException e) {
			throw new ForecastRetrievalException("[ForecastRetrievalException] ERORR: Count not get today forecasts");
		}
		if (forecasts.isEmpty()) {
			return null;
		} else if (forecasts.size() == 1) {
			return forecasts.get(0);
		}
		Collections.sort(forecasts, (a,b) -> b.getCreationdate().compareTo(a.getCreationdate()));
		return forecasts.get(0);
	}
	

	public User getUserById(int id) {
		return em.find(User.class, id);
	}

}
