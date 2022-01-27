package services;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import entities.Test;
import entities.User;

@Stateless
public class TestService {
	@PersistenceContext(unitName = "BertelliSavinoVinatiProject")
	private EntityManager em;
	@EJB(name = "services/DataminerService")
	private DataminerService dataminerService;
	
	public TestService() {
	}
	
	public void addToDb (String a) {
		try {
			;
			for(User user : dataminerService.getFarmersInLexicographicOrder(0, true, null, null)) {
				System.out.println(user.getMail());
			}
			Test test = new Test();
			test.setTestvalue("TestSuccessful " + a);
			em.persist(test);
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
	
}
