package services;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class DailyplanService {
	@PersistenceContext(unitName = "BertelliSavinoVinatiProject")
	private EntityManager em;
	
	public DailyplanService() {
	}
}
