package services;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import entities.Test;

@Stateless
public class TestService {
	@PersistenceContext(unitName = "BertelliSavinoVinatiProject")
	private EntityManager em;
	
	public TestService() {
	}
	
	public void addToDb (String a) {
		try {
			Test test = new Test();
			test.setTestvalue("TestSuccessful " + a);
			em.persist(test);
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
	
}
