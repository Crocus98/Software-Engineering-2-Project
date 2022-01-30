package services;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;

import entities.Answer;
import entities.Request;
import entities.User;
import exceptions.AnswersRetrievalException;
import exceptions.RequestsRetrievalException;

@Stateless
public class RequestService {
	@PersistenceContext(unitName = "BertelliSavinoVinatiProject")
	private EntityManager em;
	
	public RequestService() {
	}
	
	public List<Request> findAllRequestPerArea(User user) throws RequestsRetrievalException {
		List<Request> requests = null;
		try {
			requests = em.createNamedQuery("Request.findAllPerArea", Request.class)
					.setParameter(1, user.getFarm().getArea().getId()).getResultList();
		}
		catch(PersistenceException e) {
			throw new RequestsRetrievalException("[RequestsRetrievalException] ERROR: Could not retrieve requests of user area.");
		}
		return requests;
	}
	
	public List<Answer> findAllAnswersOfRequest(int idAnswer) throws AnswersRetrievalException {
		List<Answer> answers = null;
		try {
			answers = em.createNamedQuery("Answer.findByRequest", Answer.class)
					.setParameter(1, idAnswer).getResultList();
		}
		catch(PersistenceException e) {
			throw new AnswersRetrievalException("[AnswersRetrievalException] ERROR: Could not retrieve answers of request");
		}
		return answers;
	}
}
